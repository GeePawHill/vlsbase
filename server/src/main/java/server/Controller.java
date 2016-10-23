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
	public Batch transfer(@RequestParam(value = "fromId") String fromId,@RequestParam(value="toId") String toId,@RequestParam(value="order") String order)
	{
		int delay = random.nextInt(20) + 5;
		Batch response = new Batch(delay);
		Batch.responses.put(response.id,response);
		TransferRunnable transfer = new TransferRunnable(delay,response.id, fromId, toId, order);
		new Thread(transfer).start();
		return response;
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping("/batches")
	public List<Batch> batches()
	{
		List<Batch> result = new ArrayList<>();
		for(Entry<String,Batch> entry : Batch.responses.entrySet())
		{
			result.add(entry.getValue());
		}
		return result;
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping("/batch")
	public Batch batch(@RequestParam(value = "id") String id)
	{
		Batch response = Batch.responses.get(id);
		if(response==null)
		{
			response = new Batch(id,"Error","Unknown Response");
		}
		return response;
	}

}
