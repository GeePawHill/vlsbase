package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	private static Random random = new Random(1013);

	@CrossOrigin(origins = "*")
	@RequestMapping("/dealers")
	public List<Dealer> dealers()
	{
		return Dealer.dealers();
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/orders")
	public List<Order> orders()
	{
		return Order.orderList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/transfer")
	public BatchResponse transfer(@RequestParam(value = "fromId") String fromId,@RequestParam(value="toId") String toId,@RequestParam(value="order") String order)
	{
		int delay = random.nextInt(20) + 5;
		BatchResponse response = new BatchResponse(delay);
		BatchResponse.responses.put(response.id,response);
		TransferRunnable transfer = new TransferRunnable(delay,response.id, fromId, toId, order);
		new Thread(transfer).start();
		return response;
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping("/batches")
	public List<BatchResponse> batches()
	{
		List<BatchResponse> result = new ArrayList<>();
		for(Entry<String,BatchResponse> entry : BatchResponse.responses.entrySet())
		{
			result.add(entry.getValue());
		}
		return result;
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping("/batch")
	public BatchResponse batch(@RequestParam(value = "id") String id)
	{
		BatchResponse response = BatchResponse.responses.get(id);
		if(response==null)
		{
			response = new BatchResponse(id,"Error","Unknown Response");
		}
		return response;
	}

}
