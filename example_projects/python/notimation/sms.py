import requests
import json

url = 'https://api.notimation.com/api/v1/sms'
payload = {
	'recipient' : 'XXXXXXXXXX',
	'message': 'Hola desde python',
	'ignore_banned': 1
}
headers = {
	'Accept': 'application/json',
    'Content-Type': 'application/json',
	'Authorization': 'Bearer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'
}
r = requests.post(url, data=json.dumps(payload), headers=headers)
print(r.content)