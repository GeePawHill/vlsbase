package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public List<Order> orders(@RequestParam(value="id") String id)
	{
		return Order.orders(id);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/transfer")
	public Batch transfer(@RequestParam(value = "fromId") String fromId,@RequestParam(value="toId") String toId,@RequestParam(value="order") String order)
	{
		int delay = random.nextInt(15) + 5;
		Batch response = new Batch(delay);
		Batch.responses.put(response.id,response);
		TransferRunnable transfer = new TransferRunnable(delay,response.id, fromId, toId, order);
		new Thread(transfer).start();
		return response;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/withPost", method = { RequestMethod.POST, RequestMethod.GET } )
	public String withPost(@RequestBody(required=false) String body, final HttpServletRequest request)
	{
		return "Posted: "+body + " with header cookie: " + request.getHeader("MyCookie");
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
