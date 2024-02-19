#http://lxml.de/lxmlhtml.html
from lxml import html
import requests
import time
#https://github.com/Anorov/cloudflare-scrape
import cfscrape


#######################
def parse_page(page_index):
    scraper = cfscrape.create_scraper()  # returns a CloudflareScraper instance
    html_content = scraper.get("https://study4.com/tests/ielts/?term=reading+test&page="+str(page_index)).content
    tree = html.fromstring(html_content)
    tags = tree.xpath('//a[@class="text-dark"]')
    # print(tags)
    #traverve in revert order, latest movie should be on top
    for tag in tags:#[::-1]:
        # print tag.text_content()
        print("https://study4.com" + tag.attrib['href'])
#######################
for x in range(1,8):
    parse_page(x)

