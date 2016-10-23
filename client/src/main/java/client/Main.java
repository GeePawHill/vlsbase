package client;

import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application
{
	
	@Override
	public void init() throws Exception
	{
		super.init();
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		BorderPane root = new BorderPane();
		TextArea area = new TextArea();
		area.setWrapText(true);
		Font font = Font.font("Consolas",FontWeight.BOLD,20d);
		area.setFont(font);
		area.setMinHeight(300d);
		
		TabPane tabs = new TabPane();
		root.setCenter(tabs);
		tabs.getTabs().add(new AllData().getTab());
		
		TextAreaAppender.setTextArea(area);
		root.setBottom(area);
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		stage.show();
		LoggerFactory.getLogger(Main.class).debug("Log started.");
	}
	
    public static void main(String[] args) throws Exception {
        Application.launch(Main.class,args);
    }

}
