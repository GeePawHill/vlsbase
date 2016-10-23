package server;

import java.util.HashMap;

public class BatchResponse {

	final static public HashMap<String,BatchResponse> responses = new HashMap<>();
	private static int nextId = 300;
	
	public String id;
	public String status;
	public int delay;

	public BatchResponse(int delay)
	{
		this(String.format("AA%1$05d", nextId++),"Pending",delay);
	}
	
	public BatchResponse(String id, String status) {
		this(id,status,0);
	}


	public BatchResponse(String id, String status,int delay) {
		this.id = id;
		this.status = status;
		this.delay = delay;
	}
}
