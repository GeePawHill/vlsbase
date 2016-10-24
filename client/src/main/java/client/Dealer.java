package client;

import javafx.beans.property.SimpleStringProperty;

public class Dealer {

	private final SimpleStringProperty id;
	private SimpleStringProperty name;

	public Dealer() {
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
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
	
	@Override
	public String toString()
	{
		return name.getValue();
	}

}
