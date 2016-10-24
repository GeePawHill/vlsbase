package dataset;

import javafx.beans.property.SimpleStringProperty;

public class Dealer {

	private final SimpleStringProperty id;
	private final SimpleStringProperty name;

	public Dealer() {
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
	}

	public Dealer(String id, String name) {
		this();
		this.id.set(id);
		this.name.set(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String aMessage) {
		name.set(aMessage);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String anId) {
		id.set(anId);
	}

}
