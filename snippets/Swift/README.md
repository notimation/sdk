Send an SMS with Notimation API using Swift
```js
import Foundation

var semaphore = DispatchSemaphore (value: 0)

let parameters = "recipient=XXXXXXXXXX&message=Notimations%20desde%20snippets&ignore_banned=1"
let postData =  parameters.data(using: .utf8)

var request = URLRequest(url: URL(string: "https://api.notimation.com/api/v1/sms")!,timeoutInterval: Double.infinity)
request.addValue("application/json", forHTTPHeaderField: "Content-Type")
request.addValue("application/json", forHTTPHeaderField: "Accept")
request.addValue("Bearer XXXXXXXXXXXXXXXXXXXXXXXXXXXX", forHTTPHeaderField: "Authorization")

request.httpMethod = "POST"
request.httpBody = postData

let task = URLSession.shared.dataTask(with: request) { data, response, error in 
  guard let data = data else {
    print(String(describing: error))
    return
  }
  print(String(data: data, encoding: .utf8)!)
  semaphore.signal()
}

task.resume()
semaphore.wait()
```