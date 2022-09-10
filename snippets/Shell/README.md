Send an SMS with Notimation API using Shell

wget
```sh
token=""
phone=""
wget --no-check-certificate --quiet \
  --method POST \
  --timeout=0 \
  --header 'Content-Type: application/json' \
  --header 'Accept: application/json' \
  --header 'Authorization: Bearer ${token}' \
  --body-data 'recipient=${phone}&message=Notimations%20desde%20snippets&ignore_banned=1' \
   'https://api.notimation.com/api/v1/sms'
```

Httpie
```sh
token=""
phone=""
http --ignore-stdin --form --follow --timeout 3600 POST api.notimation.com/api/v1/sms \
 'recipient'='${phone}' \
 'message'='Notimations desde snippets' \
 'ignore_banned'='1' \
 Content-Type:'application/json' \
 Accept:'application/json' \
 Authorization:'Bearer ${token}'
```