package dataset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Order {

	static public class OrderSorter implements Comparator<Order>
	{

		@Override
		public int compare(Order o1, Order o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}
	
	
	private final static ObservableList<Order> unsorted = FXCollections.observableList(new ArrayList<Order>());
	
	public final static SortedList<Order> oOrders = new SortedList<>(unsorted,new OrderSorter());
	private final static Random random = new Random(1027);

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
	
	public SimpleStringProperty ownerProperty()
	{
		return owner;
	}

	public String getOwner() {
		return owner.get();
	}

	public void setOwner(String aMessage) {
		owner.set(aMessage);
	}

	public SimpleStringProperty idProperty()
	{
		return id;
	}
	
	public String getId() {
		return id.get();
	}

	public void setId(String anId) {
		id.set(anId);
	}

	public static List<Order> findByOwner(String dealerId) {
		ArrayList<Order> result = new ArrayList<>();
		for (Order order : unsorted) {
			if (order.getOwner().equals(dealerId)) {
				result.add(order);
			}
		}
		return result;
	}

	public static void addSomeOrders(Dealer dealer) {
		int count = random.nextInt(5) + 1;
		for (int i = 0; i < count; i++) {
			Order order = makeNewOrder(dealer);
			unsorted.add(order);
		}

	}

	private static Order makeNewOrder(Dealer dealer) {
		String id = IdGenerator.name("VO_");
		Order order = new Order(id, dealer.getId());
		return order;
	}

	public static Order findById(String orderId) {
		for (Order order : unsorted) {
			if (order.getId().equals(orderId)) {
				return order;
			}
		}
		return null;
	}

	public static List<Order> all() {
		return oOrders;
	}

	public static void setOwner(Order order, String owner) {
//		unsorted.remove(order);
		order.setOwner(owner);
//		unsorted.add(order);
	}
}
