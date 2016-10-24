package dataset;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Dealer {

	private final SimpleStringProperty id;
	private final SimpleStringProperty name;
	public final static ObservableList<Dealer> oDealers = FXCollections.observableList(new ArrayList<Dealer>());
	
	static {
		for(int i=0;i<3;i++)
		{
			Dealer dealer = makeNewDealer(i);
			oDealers.add(dealer);
			Order.addSomeOrders(dealer);
		}
	}

	static public List<Dealer> dealers()
	{
		return oDealers;
	}
	
	private static Dealer makeNewDealer(int index) {
		String id = IdGenerator.noun("VD_");
		String name = id+"(Dealer)";
		Dealer dealer = new Dealer(id,name);
		return dealer;
	}


	public Dealer() {
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
	}
	
	public Dealer(String id,String name)
	{
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

	public static Dealer findById(String dealerId) {
		for(Dealer candidate : oDealers)
		{
			if(candidate.getId().equals(dealerId))
			{
				return candidate;
			}
		}
		return null;
	}	
}
