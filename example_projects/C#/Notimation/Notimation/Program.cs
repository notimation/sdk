using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;

namespace Notimation
{
    class Program
    {
        static void Main(string[] args)
        {           

            String[] arguments = Environment.GetCommandLineArgs();
            Console.WriteLine("GetCommandLineArgs: {0}", String.Join(", ", arguments));

            //String key = arguments[0];            
            //String phone = arguments[1];
            //String message = arguments[2];

            String key = "XXXXXXXXXXXXXXXXXXXXXXXXX";
            String phone = "XXXXXXXXX";            
            String message = "Prueba desde C#";

            var httpWebRequest = (HttpWebRequest)WebRequest.Create("https://api.notimation.com/api/v1/sms");
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Headers["Authorization"] = "Bearer " + key; 
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                //string jsonPost = "{\"recipient\":\"" + phone + "\",\"message\":\"" + message + "\"}";
                string jsonPost = "{\"recipient\":" + phone + ",\"message\":\"" + message + "\",\"ignore_banned\":TRUE}";
                Console.WriteLine(jsonPost);
                streamWriter.Write(jsonPost);
            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                string jsonResponse = streamReader.ReadToEnd();
                Sms sms = JsonConvert.DeserializeObject<Sms>(jsonResponse);

                if (sms.status=="success") 
                {
                    //Verificar estado de SMS
                    String sms_status = sms.data.sms_status;
                    Console.WriteLine(sms_status);

                    //Guardar el id para consultar webhook
                    String sms_id = sms.data.sms_id;
                    Console.WriteLine(sms_id);

                } else if (sms.status=="error")
                {
                    Console.WriteLine(sms.message);
                }

            }
        }

        public class Data
        {
            [JsonProperty("sms_id")]
            public string sms_id { get; set; }

            [JsonProperty("sms_status")]
            public string sms_status { get; set; }
        }

        public class Sms
        {
            [JsonProperty("status")]
            public string status { get; set; }

            [JsonProperty("message")]
            public string message { get; set; }

            [JsonProperty("data")]
            public Data data{ get; set; }
        }
    }
}





