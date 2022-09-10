Send an SMS with Notimation API using Ruby

Net::Http
```ruby
require "uri"
require "net/http"

url = URI("https://api.notimation.com/api/v1/sms")

https = Net::HTTP.new(url.host, url.port);
https.use_ssl = true

token = ''
phone = ''

request = Net::HTTP::Post.new(url)
request["Content-Type"] = "application/json"
request["Accept"] = "application/json"
request["Authorization"] = "Bearer " + token
request.body = "recipient=" + phone + "&message=Notimations%20desde%20snippets&ignore_banned=1"

response = https.request(request)
puts response.read_body
```