package vls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class VlsHandler implements Handler {
	
	public VlsHandler() {
	}

	@Override
	public String dealers() {
		String response = ObjectToJson.object(Dealer.dealers());
		return response;
	}
	
	@Override
	public String orders()
	{
		String response = ObjectToJson.object(Order.orderList);
		return response;
	}

	@Override
	public String transfer(String fromId,String toId, String order)
	{
		BatchResponse response = new BatchResponse(0);
		BatchResponse.responses.put(response.id,response);
		new TransferRunnable(0,response.id, fromId, toId, order).run();
		return ObjectToJson.object(response);
	}

	@Override
	public String batches()
	{
		List<BatchResponse> result = new ArrayList<>();
		for(Entry<String,BatchResponse> entry : BatchResponse.responses.entrySet())
		{
			result.add(entry.getValue());
		}
		String response = ObjectToJson.object(result);
		return response;
	}

	
	@Override
	public String batch(String id)
	{
		BatchResponse response = BatchResponse.responses.get(id);
		if(response==null)
		{
			response = new BatchResponse(id,"Error","Unknown Response");
		}
		return ObjectToJson.object(response);
	}

}