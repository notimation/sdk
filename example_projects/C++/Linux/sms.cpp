#include "sms.h"
#include <iostream>
#include "json.hpp"

using json = nlohmann::json;

void sms(std::string const& phone, std::string const& message) {

  //std::cout << "Hello, " << name << "!\n";

	json j_object = {"recipient": + phone + ,"message": + message };

	std::cout << json_data.dump();

  	/*CURL *curl;
  	CURLcode res;
   
   	curl_global_init(CURL_GLOBAL_ALL); 
	  
	curl = curl_easy_init();
	if(curl) {
	    
		curl_easy_setopt(curl, CURLOPT_URL, "https://api.notimation.com/api/v1/sms");
	    
		curl_easy_setopt(curl, CURLOPT_POSTFIELDS, json_data.dump().c_str());

	    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, "name=daniel&project=curl");
	     
	    res = curl_easy_perform(curl);
	    
	    if(res != CURLE_OK)
	      fprintf(stderr, "curl_easy_perform() failed: %s\n",
	              curl_easy_strerror(res));
	 
	    
	    curl_easy_cleanup(curl);
	  }
	  
	  curl_global_cleanup();*/

}
