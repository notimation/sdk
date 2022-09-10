    
    const request = require('request');
    
    const phone = "XXXXXXXXXX"; //número 10 digitos    
    const payload = "prueba desde node";    

    var headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'
    };

    var dataString = '{"recipient":' + phone + ',"message":"' + payload + '"}';  

    var options = {
        url: 'https://api.notimation.com/api/v1/sms',
        method: 'POST',
        headers: headers,
        body: dataString
    };  
        
    request(options, function (error, response, body) {

        if (!error) {

            var body = JSON.parse(body);
            var data = body.data;

            var sms_id = data.sms_id;            
            console.log("sms_id: " + sms_id);

            var status = body.status;
            console.log("status: " + status);           
        
        }       

        JSON.stringify("response_callback: " + response);
        JSON.stringify("body_callback: " + body);
        JSON.stringify("error_callback: " + error);

    });
      
      
    
  