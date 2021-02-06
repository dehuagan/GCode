# coding=utf-8
import json
import codecs
from crawler import login4cookies
from requests import Session
from selenium import webdriver
import time

DRIVER_PATH = "/usr/local/chromedriver"
driver = webdriver.Chrome(DRIVER_PATH)


def index():
    # 首次请求，让selenium 知道自己在哪个域
    driver.get('https://leetcode.com')
    # 获取cookies
    # cookies = login4cookies()
    # print(cookies)
    questions = {'csrftoken':'czrlD8SXkV1czISN8GYxKv3wKXfS4mvtbGFcJM8OOyTma7wtnS9BJaPfXrix12Ec',
                 'LEETCODE_SESSION':'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiMjYwMjc4MSIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiMjMzZDQzZjE3OGM1NGFjYjI0ZDFkMzZiZjcyZGJiYTM5MjAwZjI4ZCIsImlkIjoyNjAyNzgxLCJlbWFpbCI6ImRlaHVhZ2FuMDFAZ21haWwuY29tIiwidXNlcm5hbWUiOiJERUhVQUdBTiIsInVzZXJfc2x1ZyI6IkRFSFVBR0FOIiwiYXZhdGFyIjoiaHR0cHM6Ly9hc3NldHMubGVldGNvZGUuY29tL3VzZXJzL2Zsb3dlcm1hbjEyMy9hdmF0YXJfMTU4MDI4MTg5Ni5wbmciLCJyZWZyZXNoZWRfYXQiOjE2MTI0MjQ0NDQsImlwIjoiMjE4LjE1LjIxOC4xNjYiLCJpZGVudGl0eSI6IjY0YjhkMmY1Yjc2Mzk4YzM5OGY3MWI3NDVjY2I2MGM1Iiwic2Vzc2lvbl9pZCI6NjIyNTE4MywiX3Nlc3Npb25fZXhwaXJ5IjoxMjA5NjAwfQ.VzF2PgJIlMutT4ytmYp-L3ma6BUtWFRFt6Z3HMjSaJM'}
    # questions = {}
    # for cookie in cookies:
    #     # 提取如下两个cookie 字段，只有这俩有用
    #     if cookie.name == 'csrftoken' or cookie.name == 'LEETCODE_SESSION':
    #         questions[cookie.name] = cookie.value
    # print "questions"
    # print questions
    # webdriver 添加cookie
    for name, value in questions.items():
        driver.add_cookie({
            "domain": '.leetcode.com',
            "name": name,
            "value": value,
            "path": '/',
            "expire": None
        })
    # 带cookie 再次请求
    url = "https://leetcode.com/problemset/all/?status=Solved"
    driver.get(url)
    questions = {}
    # 开始爬题目
    # 这个循环爬取中文标题和题目链接，存入questions 字典
    count = 0
    while True:
        if count==3:
            break
        count += 1


        tbody = driver.find_element_by_tag_name("tbody")
        print "ready to print tbody\n"
        # print tbody
        q_list = tbody.find_elements_by_tag_name('tr')
        for q in q_list:

            td = q.find_elements_by_tag_name('td')
            print td
            if td[0].get_attribute("value") == 'ac':
                question = td[2].find_element_by_tag_name('a')
                difficulty = td[5].find_elements_by_tag_name('span')[0].text
                tmp_list = []
                tmp_list.append(difficulty)
                tmp_list.append(question.get_attribute('href'))
                questions[question.text] = tmp_list

        print(questions)
        try:
            elem = driver.find_element_by_class_name('reactable-next-page')
            elem.click()
        except:
            break


    whole_content = []


        # 从questions 中取出链接
    for title, list in questions.items():
        q = {}
        q['title'] = title
        q['link'] = list[1]
        q['difficulty'] = list[0]
        # 请求链接，获取题目内容
        driver.get(list[1])
        time.sleep(2)
        content = driver.find_elements_by_xpath("//div[@class='content__u3I1 question-content__JfgR']")
        text = ''
        for t in content:
            text += t.text
        q['content'] = text
        print(q['content'])
        whole_content.append(json.dumps(q, ensure_ascii=False))
            # f.write(json.dumps(q, ensure_ascii=False))
    problems = {}
    problems['data'] = whole_content
    problems['name'] = 'whole problems'
    with codecs.open('data.json', 'a+', 'utf-8') as f:
        f.write(json.dumps(problems,ensure_ascii=False))

    driver.quit()


index()
