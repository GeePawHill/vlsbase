package server;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

	private static ArrayList<Dealer> dealerList=null;
	
	static {
		dealerList = new ArrayList<>();
		for(int i=0;i<5;i++)
		{
			Dealer dealer = makeNewDealer(i);
			dealerList.add(dealer);
			Order.addSomeOrders(dealer);
		}
	}

	public String id;
	public String name;
	
	static public List<Dealer> dealers()
	{
		return dealerList;
	}
	
	private static Dealer makeNewDealer(int index) {
		String id = IdGenerator.noun("D_");
		String name = id+"(Dealer)";
		Dealer dealer = new Dealer(id,name);
		return dealer;
	}

	public Dealer(String id,String name)
	{
		this.id = id;
		this.name = name;
	}

	public static Dealer findById(String dealerId) {
		for(Dealer candidate : dealerList)
		{
			if(candidate.id.equals(dealerId))
			{
				return candidate;
			}
		}
		return null;
	}	
}
