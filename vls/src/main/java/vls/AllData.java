package vls;

import dataset.Batch;
import dataset.Dataset;
import dataset.Dealer;
import dataset.Order;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class AllData {

	public void reset() {
		Dataset.data.reset();
	}

	public Tab getTab() {
		Tab tab = new Tab("All Data");
		HBox pane = new HBox();

		TableView<Dealer> dealerList = new TableView<>();
		dealerList.setItems(Dataset.data.dealers().sorted());
		dealerList.setMinWidth(150d);

		TableColumn<Dealer, String> dealerCol = new TableColumn<Dealer, String>("Id");
		dealerCol.setCellValueFactory(new PropertyValueFactory<Dealer, String>("id"));
		dealerList.getColumns().add(dealerCol);
		dealerList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		dealerList.setStyle("-fx-font: 16px 'Consolas';");
		pane.getChildren().add(dealerList);

		TableView<Batch> batchList = new TableView<Batch>();
		TableColumn<Batch, String> idCol = new TableColumn<Batch, String>("Id");
		idCol.setCellValueFactory(new PropertyValueFactory<Batch, String>("id"));
		idCol.setMinWidth(150d);
		idCol.setMaxWidth(150d);
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
		batchList.setItems(Dataset.data.batches().sorted());
		batchList.setStyle("-fx-font: 16px 'Consolas';");
		pane.getChildren().add(batchList);

		TableView<Order> orderList = new TableView<>();
		orderList.setItems(Dataset.data.orders().sorted());
		
		TableColumn<Order, String> orderIdCol = new TableColumn<Order, String>("Id");
		orderIdCol.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
		orderIdCol.setMinWidth(150d);
		orderIdCol.setMaxWidth(150d);
		
		TableColumn<Order, String> orderOwnerCol = new TableColumn<Order, String>("Owner");
		orderOwnerCol.setCellValueFactory(new PropertyValueFactory<Order, String>("owner"));
		orderOwnerCol.setMinWidth(200d);
		orderOwnerCol.setMaxWidth(200d);

		TableColumn<Order, String> changedCol = new TableColumn<Order, String>("Changed");
		changedCol.setCellValueFactory(new PropertyValueFactory<Order, String>("changed"));
		changedCol.setMinWidth(300d);
		
		orderList.getColumns().add(orderIdCol);
		orderList.getColumns().add(orderOwnerCol);
		orderList.getColumns().add(changedCol);
		
		orderList.setStyle("-fx-font: 16px 'Consolas';");
		pane.getChildren().add(orderList);
		tab.setContent(pane);
		return tab;
	}
}
