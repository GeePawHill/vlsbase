package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {

	private static ArrayList<Order> orderList = null;
	private static Random random = new Random(1027);

	static {
		orderList = new ArrayList<>();
	}

	public String id;
	public String owner;

	public Order(String id,String owner) {
		this.id = id;
		this.owner = owner;
	}

	public static List<Order> find(String dealerId) {
		ArrayList<Order> result = new ArrayList<>();
		for(Order order : orderList)
		{
			if(order.owner.equals(dealerId))
			{
				result.add(order);
			}
		}
		return result;
	}

	public static void addSomeOrders(Dealer dealer) {
		int count = random.nextInt(8) + 5;
		for (int i = 0; i < count; i++) {
			Order order = makeNewOrder(dealer);
			orderList.add(order);
		}

	}

	private static Order makeNewOrder(Dealer dealer) {
		String id = IdGenerator.name("O_");
		Order order = new Order(id, dealer.id);
		return order;
	}

	public static Order findById(String orderId) {
		for(Order order : orderList)
		{
			if(order.id.equals(orderId))
			{
				return order;
			}
		}
		return null;
	}
}
