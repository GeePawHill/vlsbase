package vls;

import java.util.List;

import dataset.Batch;
import dataset.Dataset;
import dataset.Dealer;
import dataset.Order;
import dataset.TransferRunnable;
import javafx.collections.ObservableList;

public class VlsHandler implements Handler {

	public VlsHandler() {
	}

	@Override
	public String dealers() {
		ObservableList<Dealer> sorted = Dataset.data.dealers().sorted();
		SmartLogItem.open("/dealers", "");
		for(Dealer dealer : sorted)
		{
			SmartLogItem.add(dealer.getId(), "");
		}
		SmartLogItem.close();
		String response = ObjectToJson.object(sorted);
		return response;
	}

	@Override
	public String orders(String id) {
		List<Order> orders = Dataset.data.orders().orders(id);
		SmartLogItem.open("/orders", id);
		for(Order order : orders)
		{
			SmartLogItem.add(order.getId(), order.getChanged());
		}
		SmartLogItem.close();
		String response = ObjectToJson.object(orders);
		return response;
	}

	@Override
	public String transfer(String fromId, String toId, String order) {
		Batch response = Dataset.data.batches().makeNewBatch();
		new TransferRunnable(0, response.getId(), fromId, toId, order).run();
		return ObjectToJson.object(response);
	}

	@Override
	public String batches() {
		String response = ObjectToJson.object(Dataset.data.batches().sorted());
		return response;
	}

	@Override
	public String batch(String id) {
		return ObjectToJson.object(Dataset.data.batches().find(id));
	}

}