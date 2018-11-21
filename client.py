from zeep import Client
import urllib.request
import json
from pprint import pprint
import random

def soapCall(wsdl, op):
    print(wsdl)
    client = Client(wsdl)
    a = random.randint(1,101)
    b = random.randint(1,101)
    if op == 'add':
        print(a,b)
        print(client.service.getSum(a,b))
    if op == 'subtract':
        print(a,b)
        print(client.service.getDifference(a,b))
    if op == 'multiply':
        print(a,b)
        print(client.service.getProduct(a,b))
    if op == 'divide':
        print(a,b)
        print(client.service.getDivide(a,b))
    if op == 'square':
        print(a)
        print(client.service.getSquare(a))
    if op == 'cube':
        print(a)
        print(client.service.getCube(a))
def restCall(op):
    print(op)
    url = 'http://127.0.0.1:3000/getServices/'+op
    with urllib.request.urlopen(url) as response:
        res = response.read()
        encoding = response.info().get_content_charset('utf-8')
        data = json.loads(res.decode(encoding))
        server = random.choice(data)
        print(server['services'])
        return server['URL']

def main():
    operations = ['add','subtract','multiply','divide','square','cube']
    for i in range(100):
        op = random.choice(operations)
        wsdl = restCall(op)
        soapCall(wsdl, op)
        # soapService = soapCall('http://127.0.0.1:8080/soapWS/calc.wsdl')


if __name__ == '__main__':
    main()
