Send an SMS with Notimation API using Objective-C
```obj-c
#import <Foundation/Foundation.h>

dispatch_semaphore_t sema = dispatch_semaphore_create(0);

NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:@"https://api.notimation.com/api/v1/sms"]
  cachePolicy:NSURLRequestUseProtocolCachePolicy
  timeoutInterval:10.0];
NSDictionary *headers = @{
  @"Content-Type": @"application/json",
  @"Accept": @"application/json",
  @"Authorization": @"Bearer XXXXXXXXXXXXXXXXXXXXXXXXXXX"
};

[request setAllHTTPHeaderFields:headers];
NSMutableData *postData = [[NSMutableData alloc] initWithData:[@"recipient=XXXXXXXXXX" dataUsingEncoding:NSUTF8StringEncoding]];
[postData appendData:[@"&message=Notimations desde snippets" dataUsingEncoding:NSUTF8StringEncoding]];
[postData appendData:[@"&ignore_banned=1" dataUsingEncoding:NSUTF8StringEncoding]];
[request setHTTPBody:postData];

[request setHTTPMethod:@"POST"];

NSURLSession *session = [NSURLSession sharedSession];
NSURLSessionDataTask *dataTask = [session dataTaskWithRequest:request
completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
  if (error) {
    NSLog(@"%@", error);
  } else {
    NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *) response;
    NSError *parseError = nil;
    NSDictionary *responseDictionary = [NSJSONSerialization JSONObjectWithData:data options:0 error:&parseError];
    NSLog(@"%@",responseDictionary);
    dispatch_semaphore_signal(sema);
  }
}];
[dataTask resume];
dispatch_semaphore_wait(sema, DISPATCH_TIME_FOREVER);
```