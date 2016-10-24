package dataset;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Orders {
	private static final int SEED = 1027;

	static public class OrderSorter implements Comparator<Order> {

		@Override
		public int compare(Order o1, Order o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}

	private final ObservableList<Order> base = FXCollections.observableList(new ArrayList<Order>());

	public final SortedList<Order> oOrders = new SortedList<>(base, new OrderSorter());
	private final Random random = new Random(SEED);

	public List<Order> findByOwner(String id) {
		ArrayList<Order> result = new ArrayList<>();
		for (Order order : base) {
			if (order.getOwner().equals(id)) {
				result.add(order);
			}
		}
		return result;
	}

	public void addSomeOrders(Dealer dealer) {
		int count = random.nextInt(5) + 1;
		for (int i = 0; i < count; i++) {
			Order order = makeNewOrder(dealer);
			base.add(order);
		}
	}

	private Order makeNewOrder(Dealer dealer) {
		String id = IdGenerator.name("VO_");
		Order order = new Order(id, dealer.getId());
		return order;
	}

	public Order findById(String id) {
		for (Order order : base) {
			if (order.getId().equals(id)) {
				return order;
			}
		}
		return null;
	}

	public void setOwner(Order order, String owner) {
		order.setOwner(owner);
		order.setChanged(LocalDateTime.now().toString());

	}

	public Object orders(String id) {
		ArrayList<Order> result = new ArrayList<>();
		for (Order order : base) {
			if (order.getOwner().equals(id)) {
				result.add(order);
			}
		}
		return result;
	}

	public ObservableList<Order> sorted() {
		return oOrders;
	}
	
	public ObservableList<Order> base() {
		return base;
	}
	
	public void reset(List<Dealer> dealers)
	{
		random.setSeed(SEED);
		base.clear();
		for(Dealer dealer : dealers)
		{
			addSomeOrders(dealer);
		}
	}

}
