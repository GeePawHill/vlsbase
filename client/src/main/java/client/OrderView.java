package client;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class OrderView {

	final static private ObservableList<Order> oOrders = FXCollections.observableList(new ArrayList<Order>());
	private Label dealerName;

	public void refresh() {
		JsonClient client = new JsonClient();
		oOrders.clear();
		oOrders.addAll(client.getCollection("/orders?id="+Login.selected.getId(), Order.class));
		dealerName.setText(Login.selected.getName());
	}

	public Pane getTab() {
		VBox vbox = new VBox();
		vbox.getChildren().add(makeTools());
		vbox.getChildren().add(makeOrderView());
		return vbox;
	}

	private HBox makeOrderView() {
		HBox hbox = new HBox();
		TableView<Order> orderList = new TableView<>();
		orderList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		orderList.setItems(oOrders);

		TableColumn<Order, String> orderIdCol = new TableColumn<Order, String>("Id");
		orderIdCol.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
		orderIdCol.setMinWidth(100d);
		orderIdCol.setMaxWidth(100d);
		orderList.getColumns().add(orderIdCol);

		TableColumn<Order, String> changedCol = new TableColumn<Order, String>("Changed");
		changedCol.setCellValueFactory(new PropertyValueFactory<Order, String>("changed"));
		changedCol.setMinWidth(300d);
		orderList.getColumns().add(changedCol);

		hbox.getChildren().add(orderList);
		return hbox;
	}

	private ToolBar makeTools() {
		ToolBar bar = new ToolBar();
		dealerName = new Label("");
		bar.getItems().add(dealerName);
		Button refresh = new Button("Refresh");
		bar.getItems().add(refresh);
		refresh.setOnAction((event)-> refresh());
		Button transfer = new Button("Transfer");
		bar.getItems().add(transfer);
		transfer.setOnAction((event) -> transfer());
		bar.setOrientation(Orientation.HORIZONTAL);
		return bar;
	}

	private void transfer() {
		new Transfer().run();
		refresh();
	}
}
