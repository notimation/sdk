Send an SMS with Notimation API using Javascript
```javascript

var token = "";
var phone = "";

var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");
myHeaders.append("Accept", "application/json");
myHeaders.append("Authorization", "Bearer " + token);

var urlencoded = new URLSearchParams();
urlencoded.append("recipient", phone);
urlencoded.append("message", "Notimations desde snippets");
urlencoded.append("ignore_banned", "1");

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: urlencoded,
  redirect: 'follow'
};

fetch("https://api.notimation.com/api/v1/sms", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```