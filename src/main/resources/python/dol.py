#find campaign, adgroups having recommendations
import requests
#-----
def query_page(page_index):
    # post_url = "https://apigateway.dolenglish.vn/public/search-transform/api/filter?contentGroup=DICTATION"
    post_url = 'https://apigateway.dolenglish.vn/public/search-transform/api/filter'  #reading practice
    json_data = {
        "query": "",
        "filters": {
            "all": [
                {
                    "content_group": "PRACTICE_PASSAGE"
                }
            ]
        },
        "facets": {
            "topic": [
                {
                    "type": "value"
                }
            ],
            "displayed_question_type": [
                {
                    "type": "value"
                }
            ]
        },
        "page": {
            "current": page_index,
            "size": 15
        },
        "sort": [
            {
                "ordering": "desc"
            }
        ]
    }
    #
    r = requests.post(post_url, headers={}, json=json_data)
    list = r.json()['results']
    # print(list)
    return list
#-------
ids = []
for page_index in range(14, 20):
    list = query_page(page_index)
    for item in list:
        ids.append(item['id']['raw'])

# print(ids)
str_ids = ','.join(ids)
# print(str_ids)
# get_url = 'https://apigateway.dolenglish.vn/public/page-management/api/dictations/overview?ids='+str_ids+'&size='+str(len(ids))
title_index = 1

for id in ids:
    get_url = 'https://apigateway.dolenglish.vn/public/page-management/api/page/tests/PRACTICE_TEST/'+id #reading
    r = requests.get(get_url)
    item = r.json()
    # list = r.json()['content']
    # for item in list:
    print('https://tuhocielts.dolenglish.vn/' + item['pages'][0]['url'] + ' ('+str(title_index)+')')
    title_index = title_index + 1
