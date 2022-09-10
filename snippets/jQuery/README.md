Send an SMS with Notimation API using jQuery
```js

var token = "";
var phone = "";

var settings = {
  "url": "https://api.notimation.com/api/v1/sms",
  "method": "POST",
  "timeout": 0,
  "headers": {
    "Content-Type": "application/json",
    "Accept": "application/json",
    "Authorization": "Bearer " + token
  },
  "data": {
    "recipient": phone,
    "message": "Notimations desde snippets",
    "ignore_banned": "1"
  }
};

$.ajax(settings).done(function (response) {
  console.log(response);
});
```