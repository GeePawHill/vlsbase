package server;

import java.util.HashMap;

public class Batch {

	final static public HashMap<String,Batch> responses = new HashMap<>();
	private static int nextId = 300;
	
	public String id;
	public String status;
	public String message;
	public String delay;

	public Batch(int delay)
	{
		this(String.format("AA%1$05d", nextId++),"Pending","",Integer.toString(delay));
	}
	
	public Batch(String id, String status,String message) {
		this(id,status,message,"0");
	}

	public Batch(String id, String status,String message,String delay) {
		this.id = id;
		this.status = status;
		this.message = message;
		this.delay = delay;
	}
}
