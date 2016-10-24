package vls;

import dataset.Batch;
import dataset.Dataset;
import dataset.TransferRunnable;

public class VlsHandler implements Handler {

	public VlsHandler() {
	}

	@Override
	public String dealers() {
		String response = ObjectToJson.object(Dataset.data.dealers().sorted());
		return response;
	}

	@Override
	public String orders(String id) {
		String response = ObjectToJson.object(Dataset.data.orders().orders(id));
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