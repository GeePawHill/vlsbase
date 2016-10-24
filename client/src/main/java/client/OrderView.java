package client;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class OrderView {

	final static private ObservableList<Order> oOrders = FXCollections.observableList(new ArrayList<Order>());

	public void refresh() {
		JsonClient client = new JsonClient();
		oOrders.clear();
		oOrders.addAll(client.getCollection("/orders?id="+Login.selected.getId(), Order.class));
	}

	public Pane getTab() {
		VBox vbox = new VBox();
		ToolBar bar = new ToolBar();
		Button transfer = new Button("Transfer");
		bar.getItems().add(transfer);
		transfer.setOnAction((event) -> transfer());
		bar.setOrientation(Orientation.HORIZONTAL);
		vbox.getChildren().add(bar);
		HBox hbox = new HBox();
		vbox.getChildren().add(hbox);
		TableView<Order> orderList = new TableView<>();
		orderList.setItems(oOrders);
		TableColumn<Order, String> orderIdCol = new TableColumn<Order, String>("Id");
		orderIdCol.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
		orderIdCol.setMinWidth(100d);
		orderIdCol.setMaxWidth(100d);
		TableColumn<Order, String> orderOwnerCol = new TableColumn<Order, String>("Owner");
		orderOwnerCol.setCellValueFactory(new PropertyValueFactory<Order, String>("owner"));
		orderList.getColumns().add(orderIdCol);
		orderList.getColumns().add(orderOwnerCol);
		hbox.getChildren().add(orderList);
		return vbox;
	}

	private void transfer() {
		new Transfer().run();
		refresh();
	}
}
