#find campaign, adgroups having recommendations
import requests
#-----
def query_page(page_index):
    post_url = "https://apigateway.dolenglish.vn/public/search-transform/api/filter?contentGroup=DICTATION"
    json_data = {
        "query": "",
        "filters": {
            "all": [
                {
                    "program": "IELTS"
                },
                {
                    "type": "AUDIO"
                }
            ]
        },
        "page": {
            "current": page_index,
            "size": 15
        },
        "sort": [
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
ids = []
for page_index in range(25, 30):
    list = query_page(page_index)
    for item in list:
        ids.append(item['id']['raw'])

# print(ids)
str_ids = ','.join(ids)
# print(str_ids)

get_url = 'https://apigateway.dolenglish.vn/public/page-management/api/dictations/overview?ids='+str_ids+'&size='+str(len(ids))
r = requests.get(get_url)
list = r.json()['content']
title_index = 1
for item in list:
    print('https://tuhocielts.dolenglish.vn' + item['pages'][0]['url'] + ' ('+str(title_index)+')')
    title_index = title_index + 1
