package vls;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext context;
	static Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());
	private Button mode;

	@Override
	public void init() throws Exception {
		super.init();
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		root.setTop(makeTools());
		root.setBottom(makeLog());
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		stage.show();
		List<String> args = getParameters().getRaw();
		context = SpringApplication.run(Main.class, args.toArray(new String[0]));
		logger.warn("VLS Running.");
		Vls.simulationOn();
		Vls.simulationOff();
	}

	private Node makeTools() {
		ToolBar bar = new ToolBar();
		mode = new Button("");
		bar.getItems().add(mode);
		Vls.isSimulating.addListener((observable, oldValue, newValue) -> {
			if (newValue)
				mode.setText("Mode: Vls");
			else
				mode.setText("Mode: Live");
		});
		mode.setOnAction((event) -> changeMode());

		bar.setOrientation(Orientation.HORIZONTAL);
		return bar;
	}

	public void changeMode() {
		Vls.toggle();
	}

	private TextArea makeLog() {
		TextArea area = new TextArea();
		area.setWrapText(true);
		Font font = Font.font("Consolas", FontWeight.BOLD, 20d);
		area.setFont(font);
		TextAreaAppender.setTextArea(area);
		return area;
	}

	@Override
	public void stop() throws Exception {
		logger.warn("VLS Shutting down.");
		super.stop();
		context.close();
	}

	public static void main(String[] args) throws Exception {
		Application.launch(Main.class, args);
	}

}
