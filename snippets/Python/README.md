Send an SMS with Notimation API using Python

Request
```python
import requests

url = "https://api.notimation.com/api/v1/sms"
token = ''
phone = ''
payload = 'recipient=' + phone + '&message=Notimations%20desde%20snippets&ignore_banned=1'
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer ' + token
}

response = requests.request("POST", url, headers=headers, data = payload)

print(response.text.encode('utf8'))
```


http.client
```python
import http.client
import mimetypes
token = ''
phone = ''
conn = http.client.HTTPSConnection("api.notimation.com")
payload = 'recipient=' + phone + '&message=Notimations%20desde%20snippets&ignore_banned=1'
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer ' + token
}
conn.request("POST", "/api/v1/sms", payload, headers)
res = conn.getresponse()
data = res.read()
print(data.decode("utf-8"))
```