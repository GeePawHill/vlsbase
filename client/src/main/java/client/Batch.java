package client;

import javafx.beans.property.SimpleStringProperty;

public class Batch {
	private final SimpleStringProperty id;
	private SimpleStringProperty status;
	private SimpleStringProperty message;
	private SimpleStringProperty delay;

	public Batch() {
		this.id = new SimpleStringProperty();
		this.status = new SimpleStringProperty();
		this.message = new SimpleStringProperty();
		this.delay = new SimpleStringProperty();
	}

	public String getMessage() {
		return message.get();
	}

	public void setMessage(String aMessage) {
		message.set(aMessage);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String anId) {
		id.set(anId);
	}
	
	public String getStatus() {
		return status.get();
	}

	public void setStatus(String anId) {
		status.set(anId);
	}
	
	public String getDelay() {
		return delay.get();
	}
	
	public void setDelay(String aDelay)
	{
		delay.set(aDelay);
	}

}
