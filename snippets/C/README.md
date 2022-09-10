Send an SMS with Notimation API using C
```C
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>



CURL *curl;
CURLcode res;

char* concat(const char *s1, const char *s2)
{
    char *result = malloc(strlen(s1) + strlen(s2) + 1); // +1 for the null-terminator
    // in real code you would check for errors in malloc here
    strcpy(result, s1);
    strcat(result, s2);
    return result;
}

curl = curl_easy_init();
if(curl) {
  curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, "POST");
  curl_easy_setopt(curl, CURLOPT_URL, "https://api.notimation.com/api/v1/sms");
  curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
  curl_easy_setopt(curl, CURLOPT_DEFAULT_PROTOCOL, "https");
  struct curl_slist *headers = NULL;
  headers = curl_slist_append(headers, "Content-Type: application/json");
  headers = curl_slist_append(headers, "Accept: application/json");

  char* token = concat("Authorization: Bearer ", "");
  char number[10] = "";
  char* phone = concat("recipient=", number);

  headers = curl_slist_append(headers, token);
  curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

  const char *data = concat(phone, "&message=Notimations%20desde%20snippets&ignore_banned=1");

  curl_easy_setopt(curl, CURLOPT_POSTFIELDS, data);
  res = curl_easy_perform(curl);
}
curl_easy_cleanup(curl);
```