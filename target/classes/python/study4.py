#http://lxml.de/lxmlhtml.html
from lxml import html
import requests
import time
#https://github.com/Anorov/cloudflare-scrape
import cfscrape


#######################
def parse_page(page_index):
    scraper = cfscrape.create_scraper()  # returns a CloudflareScraper instance
    html_content = scraper.get("https://study4.com/tests/ielts/?term=practice+listening+test&page="+str(page_index)).content
    tree = html.fromstring(html_content)
    tags = tree.xpath('//a[@class="text-dark"]')
    # print(html_content)
    #traverve in revert order, latest items should be on top
    for tag in tags:#[::-1]:
        # print tag.text_content()
        print("https://study4.com" + tag.attrib['href'])
#######################
for x in range(1,3):
    parse_page(x)

