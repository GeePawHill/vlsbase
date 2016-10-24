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

public class OrderData {

	final static private ObservableList<Order> oOrders = FXCollections.observableList(new ArrayList<Order>());

	public void refresh() {
		JsonClient client = new JsonClient();
		oOrders.clear();
		oOrders.addAll(client.getCollection("/orders?id="+Login.selected.getId(), Order.class));
	}

	public Pane getTab() {
		HBox pane = new HBox();
		ToolBar bar = new ToolBar();
		Button refresh = new Button("Refresh");
		bar.getItems().add(refresh);
		refresh.setOnAction((event) -> refresh());
		bar.setOrientation(Orientation.HORIZONTAL);
		pane.getChildren().add(bar);

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
		pane.getChildren().add(orderList);
		return pane;
	}
}
