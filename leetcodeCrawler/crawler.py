# coding=utf-8
import requests
import json
import selenium

from bs4 import BeautifulSoup

session = requests.Session()
COOKIES = None


def login4cookies():
    #
    url = 'https://leetcode.com'
    cookies = session.get(url).cookies
    for cookie in cookies:
        if cookie.name == 'csrftoken':
            csrftoken = cookie.value

    URL = 'https://leetcode.com/accounts/login/'

    header = {
        "accept": "application/json, text/plain, */*",
        "accept-encoding": "gzip, deflate, br",
        "accept-language": "zh-CN,zh;q=0.9,en;q=0.8",
        "origin": "https://leetcode.com",
        "referer": "https://leetcode.com/",
        "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36",
        "x-requested - with": "XMLHttpRequest",
    }
    username = "DEHUAGAN"
    password = "FLOWERMANasd111"

    payloads = {
        'csrfmiddlewaretoken': csrftoken,
        'login': username,
        'password': password,
        'next': '/'
    }
    session.post(URL, data=payloads, headers=header)
    COOKIES = session.cookies

    return COOKIES

if __name__ == '__main__':
    print login4cookies()