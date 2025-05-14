#http://lxml.de/lxmlhtml.html
from lxml import html
import requests
import time
#https://github.com/Anorov/cloudflare-scrape
import cfscrape


#######################
def parse_page(_from):
    scraper = cfscrape.create_scraper()  # returns a CloudflareScraper instance
    html_content = scraper.get("https://www.ielts-mentor.com/cue-card-sample?start="+str(_from)).content
    tree = html.fromstring(html_content)
    tags = tree.xpath('//td[@class="list-title"]/a')
    # print(tags)
    #traverve in revert order, latest movie should be on top
    for tag in tags:#[::-1]:
        # print tag.text_content()
        print("https://www.ielts-mentor.com" + tag.attrib['href'])
#######################
for x in range(0, 42):
    parse_page(x*20)

