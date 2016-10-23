package vls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javafx.application.Platform;

@RestController
@EnableAutoConfiguration
public class Controller {
	
	static Handler handler;

	static Logger logger = LoggerFactory.getLogger("Controller");

	public Controller() {
	}
	
	static public void setHandler(Handler aHandler)
	{
		handler = aHandler;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping("/control/vls")
	public String vls()
	{
		Platform.runLater(() -> Vls.simulationOn());
		return "Ok.";
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping("/control/live")
	public String live()
	{
		Platform.runLater(() ->Vls.simulationOff());
		return "Ok.";
	}


	@CrossOrigin(origins = "*")
	@RequestMapping("/dealers")
	public String dealers() {
		logger.warn("REQUEST /dealers");
		String response = handler.dealers();
		logger.warn("RESPONSE "+response);
		return response;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/orders")
	public String orders()
	{
		logger.warn("REQUEST /orders");
		String response = handler.orders();
		logger.warn("RESPONSE "+response);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/transfer")
	public String transfer(@RequestParam(value = "fromId") String fromId,@RequestParam(value="toId") String toId,@RequestParam(value="order") String order)
	{
		logger.warn("REQUEST /transfer Order: "+order+ " From "+fromId+" To "+toId);
		String response = handler.transfer(fromId, toId, order);
		logger.warn("RESPONSE "+response);
		return response;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/batches")
	public String batches()
	{
		logger.warn("REQUEST /batches");
		String response = handler.batches();
		logger.warn("RESPONSE "+response);
		return response;
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping("/batch")
	public String batch(@RequestParam(value = "id") String id)
	{
		logger.warn("REQUEST /batch Id "+id);
		String response = handler.batch(id);
		logger.warn("RESPONSE "+response);
		return response;
	}

}