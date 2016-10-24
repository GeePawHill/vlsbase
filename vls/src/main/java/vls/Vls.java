package vls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Vls {

	final public static BooleanProperty isSimulating = new SimpleBooleanProperty(false);
	final public static Logger logger = LoggerFactory.getLogger(Vls.class.getSimpleName());
	
	private static Handler handler;
	final private static ServerHandler serverHandler = new ServerHandler();
	final private static VlsHandler vlsHandler = new VlsHandler();
	
	public static void toggle()
	{
		if(isSimulating.get()) simulationOff();
		else simulationOn();
	}
	
	public static Handler handler()
	{
		return handler;
	}
	
	public static void simulationOn()
	{
		handler = vlsHandler;
		isSimulating.set(true);
		logger.warn("Simulation: On");
	}
	
	public static void simulationOff()
	{
		handler = serverHandler;
		isSimulating.set(false);
		logger.warn("Simulation: Off");
	}
}
