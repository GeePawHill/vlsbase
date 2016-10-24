package vls;

public class ServerHandler implements Handler {

	public ServerHandler() {
	}

	public String dealers() {
		String response = new StringClient().get("http://localhost:8090/dealers");
		return response;
	}
	
	public String orders(String id)
	{
		String response = new StringClient().get("http://localhost:8090/orders?id="+id);
		return response;
	}

	public String transfer(String fromId,String toId, String order)
	{
		String response = new StringClient().get("http://localhost:8090/transfer?fromId="+fromId+"&toId="+toId+"&order="+order);
		return response;
	}

	public String batches()
	{
		String response = new StringClient().get("http://localhost:8090/batches");
		return response;
	}

	
	public String batch(String id)
	{
		String response = new StringClient().get("http://localhost:8090/batch?id="+id);
		return response;
	}

}