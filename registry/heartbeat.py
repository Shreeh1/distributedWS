import socket
import json
import time
import errno
from pprint import pprint

def checkPorts(urls, path):
    for i in urls:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        port = i.split('/')[2].split(':')[1]
        time.sleep(1)
        r = sock.connect_ex(('127.0.0.1',int(port)))
        if r==0:
            # port is open, allow for checking another port
            sock.close()
        else:
            # port is closed, update the registry
            updateRegistry(path, i)

def updateRegistry(path, url):
    print(url)
    f = open(path)
    data = json.load(f)
    # print(data)
    for s in data['Servers']:
        if data['Servers'][s]['URL'] == url:
            break
    del data['Servers'][s]
    # del data['Servers'][s]
    o = open("registry.json",'w')
    o.write(json.dumps(data))
    # pprint(data)



def getURLS(path):
    with open(path) as infile:
        servers = json.load(infile)
        urls = []
        for i in servers['Servers']:
            urls.append(servers['Servers'][i]['URL'])
        return urls

def main():
    while(True):
        urls = getURLS('registry.json')
        checkPorts(urls,'registry.json')

if __name__ == '__main__':
    main()
