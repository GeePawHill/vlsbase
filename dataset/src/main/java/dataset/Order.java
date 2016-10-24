package dataset;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;

public class Order {

	private final SimpleStringProperty id;
	private final SimpleStringProperty owner;
	private final SimpleStringProperty changed;

	public Order(String id, String owner) {
		this();
		this.id.set(id);
		this.owner.set(owner);
		this.changed.set(LocalDateTime.of(2016, 10,24,3,9).toString());
	}

	public Order() {
		this.id = new SimpleStringProperty();
		this.owner = new SimpleStringProperty();
		this.changed = new SimpleStringProperty();
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
	
	public SimpleStringProperty changedProperty() {
		return changed;
	}

	public String getChanged() {
		return changed.get();
	}

	public void setChanged(String anId) {
		changed.set(anId);
	}

}
