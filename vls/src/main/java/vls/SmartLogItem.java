package vls;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;

public class SmartLogItem {
	public static TreeItem<SmartLogItem> root = new TreeItem<>(new SmartLogItem("Label", "Value"));
	private static TreeItem<SmartLogItem> current= root;
	
	private final SimpleStringProperty label;
	private final SimpleStringProperty value;
	
	public static void open(String label,String value)
	{
		TreeItem<SmartLogItem> item = new TreeItem<SmartLogItem>(new SmartLogItem(label,value));
		current.getChildren().add(item);
		current = item;
	}
	
	public static void add(String label,String value)
	{
		TreeItem<SmartLogItem> item = new TreeItem<SmartLogItem>(new SmartLogItem(label,value));
		current.getChildren().add(item);
	}
	
	public static void close()
	{
		current=current.getParent();
	}

	public SmartLogItem(String label, String value) {
		this();
		this.label.set(label);
		this.value.set(value);
	}

	public SmartLogItem() {
		this.label = new SimpleStringProperty();
		this.value = new SimpleStringProperty();
	}

	public SimpleStringProperty valueProperty() {
		return value;
	}

	public String getValue() {
		return value.get();
	}

	public void setValue(String aMessage) {
		value.set(aMessage);
	}

	public SimpleStringProperty labelProperty() {
		return label;
	}

	public String getLabel() {
		return label.get();
	}

	public void setLabel(String anId) {
		label.set(anId);
	}
}
