Send an SMS with Notimation API using C++
```C#
var client = new RestClient("https://api.notimation.com/api/v1/sms");
client.Timeout = -1;
var token = "";
var phone = "";
var request = new RestRequest(Method.POST);
request.AddHeader("Content-Type", "application/json");
request.AddHeader("Accept", "application/json");
request.AddHeader("Authorization", "Bearer " + token);
request.AddParameter("recipient", phone);
request.AddParameter("message", "Notimations desde snippets");
request.AddParameter("ignore_banned", "1");
IRestResponse response = client.Execute(request);
Console.WriteLine(response.Content);
```