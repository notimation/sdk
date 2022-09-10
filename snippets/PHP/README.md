Send an SMS with Notimation API using PHP

cURL
```php
<?php 

// create curl resource
$endpoint = 'https://api.notimation.com/api/v1/sms';
$token = "";
$headers = [
  'Content-Type: application/json',
  'Accept: application/json',
  'Authorization: Bearer {$token}',
];
$data = [
  'recipient' => $dto,
  'message' => $message,
];
$body = json_encode($data, JSON_UNESCAPED_UNICODE);
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $endpoint);
curl_setopt($ch, CURLOPT_POSTFIELDS, $body);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$output = curl_exec($ch);
curl_close($ch);

echo "\tresponse: {$output} \n";
```