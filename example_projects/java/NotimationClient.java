
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotimationClient {

	private static final String POST = "POST";
	private static final String BASE_PATH = "https://api.notimation.com/api/v1/";
	private static final String SMS = BASE_PATH + "sms";
	private static final MediaType JSON = MediaType.parse("application/json");
			
	private String token;
	
	public NotimationClient(String token) {
		this.token = token;
	}
	
	public MessageResponse sendMessage(Message message) {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create(new Gson().toJson(message), JSON);
		Request request = this.defaultBuilder(SMS).method(POST, body).build();
		try {
			Response response = client.newCall(request).execute();
			MessageResponse messageResponse = new Gson().fromJson(response.body().string(),MessageResponse.class);
			messageResponse.setHttpCode(response.code());
			return messageResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	private Request.Builder defaultBuilder(String endpoint) {
		return new Request.Builder()
			.url(endpoint)
			.addHeader("Content-Type", "application/json")
			.addHeader("Accept", "application/json")
			.addHeader("Authorization", "Bearer " + this.token);
	}
	
}
