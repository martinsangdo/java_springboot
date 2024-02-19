#find campaign, adgroups having recommendations
import requests
from pymongo import MongoClient

#-----
def query_page(page_index):
    # post_url = "https://apigateway.dolenglish.vn/public/search-transform/api/filter?contentGroup=DICTATION"
    # post_url = 'https://apigateway.dolenglish.vn/public/search-transform/api/filter'  #reading practice
    post_url = 'https://apigateway.dolenglish.vn/public/search-transform/api/filter/samples'  #writing practice

    json_data = {
        "query": "",
        "filters": {
            "all": [
                {
                    "content_group": "WRITING"
                },
                {
                    "type": "WRITING_TASK_2_ACADEMIC"
                }
            ]
        },
        "facets": {
            "topic": [
                {
                    "type": "value"
                }
            ],
            "chart_type": [
                {
                    "type": "value"
                }
            ],
                    "year": [
                        {
                            "type": "value"
                        }
                    ]
        },
        "page": {
            "current": page_index,
            "size": 9
        },
        "sort": [
            {
                "year_quarter": "desc"
            },
            {
                "created_at": "desc"
            }
        ]
    }
    #
    r = requests.post(post_url, headers={}, json=json_data)
    list = r.json()['results']
    # print(list)
    return list
#-------
client = MongoClient('localhost:27017')
db_client = client['martin_projects']['ielts_writing_task_2']

ids = []
for page_index in range(1, 37):
    list = query_page(page_index)
    for item in list:
        link = 'https://tuhocielts.dolenglish.vn/' + item['url']['raw']
        record = db_client.find_one({'link':link, 'is_done':{'$ne': True}})
        if record is None:
            #not existed
            print(link)
        # else:
        #     print('existed: ' + link)

