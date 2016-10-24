package client;

import javafx.beans.property.SimpleStringProperty;

public class Order {
	private final SimpleStringProperty id;
	private SimpleStringProperty owner;

	public Order() {
		this.id = new SimpleStringProperty();
		this.owner = new SimpleStringProperty();
	}

	public String getOwner() {
		return owner.get();
	}

	public void setOwner(String aMessage) {
		owner.set(aMessage);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String anId) {
		id.set(anId);
	}
	
	@Override
	public String toString() {
		return id.get();
	}
}
