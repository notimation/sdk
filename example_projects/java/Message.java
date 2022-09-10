
public class Message {

	// TODO Falta lo de "programmed_at" y "expire_at"
	
	private String recipient; // Destinatario
	private String message; // Mensaje
	private Integer serviceId; // Opcional. Es para indicar que servicio usar para mandar el mensaje. 

	public Message(String recipient, String message) {
		this.recipient = recipient;
		this.message = message;
		this.serviceId = null;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

}
