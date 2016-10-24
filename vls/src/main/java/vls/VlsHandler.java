package vls;

import dataset.Batch;
import dataset.Dealer;
import dataset.Order;
import dataset.TransferRunnable;

public class VlsHandler implements Handler {
	
	public VlsHandler() {
	}

	@Override
	public String dealers() {
		String response = ObjectToJson.object(Dealer.dealers());
		return response;
	}
	
	@Override
	public String orders(String id)
	{
		String response = ObjectToJson.object(Order.orders(id));
		return response;
	}

	@Override
	public String transfer(String fromId,String toId, String order)
	{
		Batch response = Batch.makeNewBatch();
		new TransferRunnable(0,response.getId(), fromId, toId, order).run();
		return ObjectToJson.object(response);
	}

	@Override
	public String batches()
	{
		String response = ObjectToJson.object(Batch.all());
		return response;
	}

	
	@Override
	public String batch(String id)
	{
		return ObjectToJson.object(Batch.find(id));
	}

}