package client;

import javafx.beans.property.SimpleStringProperty;

public class Order {
	private final SimpleStringProperty id;
	private SimpleStringProperty owner;
	private SimpleStringProperty changed;

	public Order() {
		this.id = new SimpleStringProperty();
		this.owner = new SimpleStringProperty();
		this.changed = new SimpleStringProperty();
	}
	
	public String getChanged() {
		return changed.get();
	}
	
	public void setChanged(String value) {
		changed.set(value);
	}
	
	public SimpleStringProperty changedProperty()
	{
		return changed;
	}

	public String getOwner() {
		return owner.get();
	}

	public void setOwner(String aMessage) {
		owner.set(aMessage);
	}
	
	public SimpleStringProperty ownerProperty() {
		return owner;
	}

	public String getId() {
		return id.get();
	}

	public void setId(String anId) {
		id.set(anId);
	}
	
	public SimpleStringProperty idProperty() {
		return id;
	}
	
	@Override
	public String toString() {
		return id.get();
	}
}
