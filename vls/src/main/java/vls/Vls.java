package vls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Vls {

	final public static BooleanProperty isSimulating = new SimpleBooleanProperty(false);
	final public static Logger logger = LoggerFactory.getLogger(Vls.class.getSimpleName());
	
	public static void toggle()
	{
		if(isSimulating.get()) simulationOff();
		else simulationOn();
		
	}
	
	public static void simulationOn()
	{
		Controller.setHandler(new ServerHandler());
		isSimulating.set(true);
		logger.warn("Simulation: On");
	}
	
	public static void simulationOff()
	{
		Controller.setHandler(new VlsHandler());
		isSimulating.set(false);
		logger.warn("Simulation: Off");
	}
}
