package dataset;

import javafx.beans.property.SimpleStringProperty;

public class Order {

	private final SimpleStringProperty id;
	private SimpleStringProperty owner;

	public Order(String id, String owner) {
		this();
		this.id.set(id);
		this.owner.set(owner);
	}

	public Order() {
		this.id = new SimpleStringProperty();
		this.owner = new SimpleStringProperty();
	}

	public SimpleStringProperty ownerProperty() {
		return owner;
	}

	public String getOwner() {
		return owner.get();
	}

	public void setOwner(String aMessage) {
		owner.set(aMessage);
	}

	public SimpleStringProperty idProperty() {
		return id;
	}

	public String getId() {
		return id.get();
	}

	public void setId(String anId) {
		id.set(anId);
	}
}
