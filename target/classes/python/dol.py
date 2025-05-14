#find campaign, adgroups having recommendations
import requests
from pymongo import MongoClient

#-----
def query_page(page_index):
    # post_url = "https://apigateway.dolenglish.vn/public/search-transform/api/filter?contentGroup=DICTATION"
    # post_url = 'https://apigateway.dolenglish.vn/public/search-transform/api/filter'  #reading practice
    post_url = 'https://apigateway.dolenglish.vn/public/search-transform/api/filter/samples'  #writing & speaking practice

    json_data = {
        "query": "",
        "filters": {
            "all": [
                {
                    "type": "SPEAKING_PART_3"
                }
            ]
        },
        "facets": {
            "topic": [
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
# client = MongoClient('localhost:27017')
# db_client = client['martin_projects']['ielts_writing_task_2']

ids = []
for page_index in range(1, 6):
    list = query_page(page_index)
    for item in list:
        link = 'https://tuhocielts.dolenglish.vn/' + item['url']['raw']
        print(link)

