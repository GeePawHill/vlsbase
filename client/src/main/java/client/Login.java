package client;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {
	
	static Dealer selected;
	private TableView<Dealer> dealers;
	private Stage login;

	public void run() {
		login = new Stage(StageStyle.UTILITY);
		login.initModality(Modality.APPLICATION_MODAL);
		GridPane root = new GridPane();
		root.setVgap(4);
		root.setHgap(15);
		root.setPadding(new Insets(5, 5, 5, 5));
		Label label = new Label("Dealer:");
		root.add(label, 1, 0);
		dealers = new TableView<>();
		dealers.setItems(populateDealers());
		TableColumn<Dealer, String> dealerCol = new TableColumn<Dealer, String>("Name");
		dealerCol.setCellValueFactory(new PropertyValueFactory<Dealer, String>("name"));
		dealers.getColumns().add(dealerCol);
		dealers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		root.add(dealers, 2, 0);
		Button doLogin = new Button("Login");
		doLogin.setOnAction((event) -> tryLogin());
		root.add(doLogin, 2, 2);
		login.setScene(new Scene(root, 400d, 400d));
		login.showAndWait();
	}

	private void tryLogin() {
		selected = dealers.getSelectionModel().getSelectedItem();
		if(selected==null) return;
		login.close();
	}

	private ObservableList<Dealer> populateDealers() {
		List<Dealer> dealers = new JsonClient().getCollection("/dealers", Dealer.class);
		ObservableList<Dealer> result = FXCollections.observableList(dealers);
		return result;
	}

}
