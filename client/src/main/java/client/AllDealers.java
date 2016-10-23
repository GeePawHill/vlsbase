package client;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class AllDealers {
	

	final static private ObservableList<String> oDealers = FXCollections.observableList(new ArrayList<String>()); 
	final static private ObservableList<String> oOrders = FXCollections.observableList(new ArrayList<String>());
	
	public void refresh()
	{
		JsonClient client = new JsonClient();
		oDealers.clear();
		Collection<Dealer> dealers = client.getCollection("http://localhost:8090/dealers", Dealer.class);
		for(Dealer dealer : dealers)
		{
			oDealers.add(dealer.name);
		}
	}

	public Tab getTab() {
		Tab tab = new Tab("All Dealers");
		HBox pane = new HBox();
		ToolBar bar = new ToolBar();
		Button refresh = new Button("Refresh");
		bar.getItems().add(refresh);
		refresh.setOnAction((event)->refresh());
		bar.setOrientation(Orientation.HORIZONTAL);
		pane.getChildren().add(bar);
		ListView<String> dealerList = new ListView<>();
		dealerList.setItems(oDealers);
		oDealers.add("id");
		pane.getChildren().add(dealerList);
		
		ListView<String> orderList = new ListView<>();
		orderList.setItems(oOrders);
		oOrders.add("OrderId");
		pane.getChildren().add(orderList);
		
		tab.setContent(pane);
		return tab;
	}
}
