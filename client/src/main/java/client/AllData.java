package client;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class AllData {

	final static private ObservableList<Dealer> oDealers = FXCollections.observableList(new ArrayList<Dealer>());
	final static private ObservableList<Order> oOrders = FXCollections.observableList(new ArrayList<Order>());
	final static private ObservableList<Batch> oBatches = FXCollections.observableList(new ArrayList<Batch>());

	public void refresh() {
		JsonClient client = new JsonClient();
		oDealers.clear();
		oDealers.addAll(client.getCollection("http://localhost:8090/dealers", Dealer.class));
		oBatches.clear();
		oBatches.addAll(client.getCollection("http://localhost:8090/batches", Batch.class));
		oOrders.clear();
		oOrders.addAll(client.getCollection("http://localhost:8090/orders", Order.class));
	}

	public Tab getTab() {
		Tab tab = new Tab("All Dealers");
		HBox pane = new HBox();
		ToolBar bar = new ToolBar();
		Button refresh = new Button("Refresh");
		bar.getItems().add(refresh);
		refresh.setOnAction((event) -> refresh());
		bar.setOrientation(Orientation.HORIZONTAL);
		pane.getChildren().add(bar);
		TableView<Dealer> dealerList = new TableView<>();
		dealerList.setItems(oDealers);
		dealerList.setMinWidth(100d);
		dealerList.setMaxWidth(100d);

		TableColumn<Dealer, String> dealerCol = new TableColumn<Dealer, String>("Id");
		dealerCol.setCellValueFactory(new PropertyValueFactory<Dealer, String>("id"));
		dealerList.getColumns().add(dealerCol);
		dealerList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		pane.getChildren().add(dealerList);

		TableView<Batch> batchList = new TableView<Batch>();
		TableColumn<Batch, String> idCol = new TableColumn<Batch, String>("Id");
		idCol.setCellValueFactory(new PropertyValueFactory<Batch, String>("id"));
		idCol.setMinWidth(100d);
		idCol.setMaxWidth(100d);
		TableColumn<Batch, String> statusCol = new TableColumn<Batch, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<Batch, String>("status"));
		statusCol.setMinWidth(100d);
		statusCol.setMaxWidth(100d);
		TableColumn<Batch, String> messageCol = new TableColumn<Batch, String>("Message");
		messageCol.setCellValueFactory(new PropertyValueFactory<Batch, String>("message"));

		batchList.getColumns().add(idCol);
		batchList.getColumns().add(statusCol);
		batchList.getColumns().add(messageCol);
		batchList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		batchList.setMinWidth(500d);
		batchList.setMaxWidth(500d);
		batchList.setItems(oBatches);
		pane.getChildren().add(batchList);

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
		tab.setContent(pane);
		return tab;
	}
}
