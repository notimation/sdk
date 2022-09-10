Send an SMS with Notimation API using Java
```java
String token = "";
String phone = "";
OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "recipient=" + phone + "&message=Notimations desde snippets&ignore_banned=1");
Request request = new Request.Builder()
  .url("https://api.notimation.com/api/v1/sms")
  .method("POST", body)
  .addHeader("Content-Type", "application/json")
  .addHeader("Accept", "application/json")
  .addHeader("Authorization", "Bearer " + token)
  .build();
Response response = client.newCall(request).execute();
```