import os.path

from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError
from pymongo import MongoClient

# If modifying these scopes, delete the file token.json.
SCOPES = ["https://www.googleapis.com/auth/spreadsheets.readonly"]

# The ID and range of a sample spreadsheet.
SAMPLE_SPREADSHEET_ID = "1ePt1lbCJuXXRr2M-7LKEorHz6FYxCpGORTppAZNob6w"

TAB_NAMES = ["writing", "collocation", "vocabulary", "sentences", "idioms", "uncommon"]
DATA_RANGE = "!A1:B10000"
# SAMPLE_RANGE_NAME = "writing!A1:B4"  #tab name & range

#================================================
def main():
    """Shows basic usage of the Sheets API.
    Prints values from a sample spreadsheet.
    """
    creds = None
    # The file token.json stores the user's access and refresh tokens, and is
    # created automatically when the authorization flow completes for the first
    # time.
    if os.path.exists("token.json"):
        creds = Credentials.from_authorized_user_file("token.json", SCOPES)
    # If there are no (valid) credentials available, let the user log in.
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                "martin_sangdo_game_scrap_desktop_app_client_secret_981126453477-u8rdb2ve0cl0bj4j2sfgipub3sivdq7h.apps.googleusercontent.com.json", SCOPES
            )
            creds = flow.run_local_server(port=0)
        # Save the credentials for the next run
        with open("token.json", "w") as token:
            token.write(creds.to_json())

    try:
        service = build("sheets", "v4", credentials=creds)
        #
        client = MongoClient('localhost:27017')
        db_client = client['martin_projects']['vocabulary']
        db_client.delete_many({})
        # Call the Sheets API
        sheet = service.spreadsheets()
        #scan through all tabs
        for tab in TAB_NAMES:
            print('parsing tab: ' + tab)
            rows = []
            range_ = tab + DATA_RANGE
            result = (
                sheet.values()
                .get(spreadsheetId=SAMPLE_SPREADSHEET_ID, range=range_)
                .execute()
            )
            rows = result.get("values", [])
            save_db(db_client, tab, rows)
    except HttpError as err:
        print(err)
#================================================
def save_db(db_client, tab, rows):
    #clear entire collection
    print('saving tab: ' + tab + ' with row total: ' + str(len(rows)))
    #insert data
    row = 0
    for row_data in rows:
        if(len(row_data) > 0):
            #this row has values
            for col in [0, 1]:   #there are only 2 columns
                if col < len(row_data):
                    item = {
                        'vi': '',
                        'en': ''
                    }
                    has_data = split_words(row_data[col], item)
                    if (has_data):
                        detail = {
                            'tab': tab,
                            'col': col,
                            'row': row,
                            'vi': item['vi'].strip(),
                            'en': item['en'].strip()
                        }
                        add_words(db_client, detail)
        row = row + 1
#================================================
def split_words(word, item):
    has_data = False
    words = word.split(':')
    if (len(words) == 1 and words[0] != ''):
        #no english
        item['vi'] = words[0]
        item['en'] = ''
        has_data = True
    if (len(words) == 2):
        item['vi'] = words[0]
        item['en'] = words[1]
        has_data = True
    if (len(words) > 2):
        item['vi'] = words[0]
        item['en'] = ''
        has_data = True
        for idx in [1 , len(words)-1]:
            item['en'] = item['en'] + ' ' + words[idx]
    return has_data
#================================================
#upsert movie detail
def add_words(db_client, detail):
    db_client.insert_one(detail)
#================================================
if __name__ == "__main__":
    main()
