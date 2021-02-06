# coding=utf-8
import pymysql
import json
def pre(db):
    cursor = db.cursor()
    cursor.execute("select version()")
    data = cursor.fetchone()
    print "database version:%s"%data

def insert(db):
    with open('data.json', 'r') as f:
        data = json.loads(f.read())
        data_list = data['data']
        # print data_list
        for text in data_list:
            review = json.loads(text)
            # print type(review['content'])
            if review['content'] is None:
                continue
            res = []
            res.append((review['title'],review['difficulty'],review['content']))
            print res
            inesrt_re = "insert into problem(title,difficulty, content) values(%s,%s,%s)"
            cursor = db.cursor()
            cursor.executemany(inesrt_re, res)
            db.commit()
        # print str(json.loads(data_list[0])['content'])



if __name__ == "__main__":
    db = pymysql.connect("localhost","root","12345678","gcode",charset='utf8')
    cursor = db.cursor()
    # pre(db)

    insert(db)
    cursor.close()