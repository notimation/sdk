
import com.google.gson.Gson;

public class MessageResponse {

	public class Data {
		
		private Long sms_id;
		private String sms_status;
		private String message;

		public Long getSmsId() {
			return sms_id;
		}

		public void setSmsId(Long smsId) {
			this.sms_id = smsId;
		}

		public String getSmsStatus() {
			return sms_status;
		}

		public void setSmsStatus(String smsStatus) {
			this.sms_status = smsStatus;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	private int httpCode;
	private String status;
	private Data data;
	private String message;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
