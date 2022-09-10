Send an SMS with Notimation API using cURL
```bash
token=""
phone=""
curl --location --request POST 'https://api.notimation.com/api/v1/sms' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Authorization: Bearer ${token}' \
--data-urlencode 'recipient=${phone}' \
--data-urlencode 'message=Notimations desde snippets' \
--data-urlencode 'ignore_banned=1'
```