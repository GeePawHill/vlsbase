package dataset;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Batch {

	private static int nextId = 300;
	public final static ObservableList<Batch> oBatches = FXCollections.observableList(new ArrayList<Batch>());
	
	private final SimpleStringProperty id;
	private SimpleStringProperty status;
	private SimpleStringProperty message;
	private SimpleStringProperty delay;
	
	public Batch(String delay)
	{
		this(String.format("B_%1$05d", nextId++),"Pending","",delay);
	}
	
	public Batch(String id, String status,String message) {
		this(id,status,message,"0");
	}

	public Batch(String id, String status,String message,String delay) {
		this();
		this.id.set(id);
		this.status.set(status);
		this.message.set(message);
		this.delay.set(delay);
	}

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

	public static List<Batch> all() {
		return oBatches;
	}

	public static Batch makeNewBatch() {
		Batch result = new Batch("0");
		oBatches.add(result);
		return result;
	}

	public static Batch find(String id) {
		for(Batch candidate : oBatches)
		{
			if(candidate.getId().equals(id)) return candidate;
		}
		return new Batch(id,"Error","Unknown Batch");
	}

	public static void replace(String responseId, String status, String message) {
		for(Batch candidate : oBatches)
		{
			if(candidate.getId().equals(responseId))
			{
				oBatches.remove(candidate);
				oBatches.add(new Batch(responseId,status,message,"0"));
				return;
			}
		}
		throw new RuntimeException("Attempt to replace non-existent batch.");
	}
}
