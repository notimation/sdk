Send an SMS with Notimation API using Nodejs

Native
```js
var https = require('follow-redirects').https;
var fs = require('fs');

var qs = require('querystring');

var token = '';
var phone = '';

var options = {
  'method': 'POST',
  'hostname': 'api.notimation.com',
  'path': '/api/v1/sms',
  'headers': {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Authorization': 'Bearer ' + token 
  },
  'maxRedirects': 20
};

var req = https.request(options, function (res) {
  var chunks = [];

  res.on("data", function (chunk) {
    chunks.push(chunk);
  });

  res.on("end", function (chunk) {
    var body = Buffer.concat(chunks);
    console.log(body.toString());
  });

  res.on("error", function (error) {
    console.error(error);
  });
});

var postData = qs.stringify({
  'recipient': phone,
  'message': 'Notimations desde snippets',
  'ignore_banned': '1'
});

req.write(postData);

req.end();
```


Request
```js
var token = '';
var phone = '';

var request = require('request');
var options = {
  'method': 'POST',
  'url': 'https://api.notimation.com/api/v1/sms',
  'headers': {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Authorization': 'Bearer ' + token
  },
  form: {
    'recipient': phone,
    'message': 'Notimations desde snippets',
    'ignore_banned': '1'
  }
};
request(options, function (error, response) { 
  if (error) throw new Error(error);
  console.log(response.body);
});

```

