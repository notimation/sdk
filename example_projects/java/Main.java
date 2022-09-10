
public class Main {

	public static void main(String[] args) {
		
		NotimationClient client = new NotimationClient("TOKEN HERE");
		Message message = new Message("PHONE NUMBER HERE","SMS TEXT HERE");
		MessageResponse response = client.sendMessage(message);
		System.out.println(response);
		
	}

}
