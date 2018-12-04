import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
port = 3000
r = sock.connect_ex(('127.0.0.1', port))
sock.close()
print(r)
