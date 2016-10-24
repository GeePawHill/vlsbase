package client;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Transfer {

	private Stage transfer;
	ObservableList<Dealer> dealers;
	ObservableList<Order> orders = FXCollections.observableList(new ArrayList<Order>());
	private ComboBox<Dealer> from;
	private ComboBox<Dealer> to;
	private ComboBox<Order> order;
	private Label statusLabel;
	private Button doTransfer;
	private Button doCancel;
	private Label messageLabel;

	public void run() {
		transfer = new Stage(StageStyle.UTILITY);
		transfer.initModality(Modality.APPLICATION_MODAL);
		GridPane root = new GridPane();
		root.setVgap(4);
		root.setHgap(15);
		root.setPadding(new Insets(5, 5, 5, 5));
		Label fromLabel = new Label("From:");
		root.add(fromLabel, 1, 0);
		dealers = populateDealers();
		from = new ComboBox<>();
		from.setEditable(true);
		from.setItems(dealers);
		root.add(from, 2, 0);

		Label toLabel = new Label("To:");
		root.add(toLabel, 1, 1);
		to = new ComboBox<>();
		to.setItems(dealers);
		to.setEditable(true);
		root.add(to, 2, 1);

		Label orderLabel = new Label("Order:");
		root.add(orderLabel, 1, 2);
		order = new ComboBox<>();
		order.setItems(orders);
		order.focusedProperty().addListener((observable, oldValue, newValue) -> orderFocused(newValue));
		order.setEditable(true);
		root.add(order, 2, 2);

		statusLabel = new Label("");
		statusLabel.setFont(new Font("System", 20d));
		root.add(statusLabel, 2, 3);

		messageLabel = new Label("");
		messageLabel.setFont(new Font("System", 20d));
		root.add(messageLabel, 2, 4);

		doTransfer = new Button("Transfer");
		doTransfer.setOnAction((event) -> tryTransfer());
		root.add(doTransfer, 2, 5);
		doCancel = new Button("Cancel");
		root.add(doCancel, 1, 5);
		doCancel.setOnAction((event) -> transfer.close());

		transfer.setScene(new Scene(root, 400d, 200d));
		transfer.showAndWait();
	}

	private void orderFocused(Boolean newValue) {
		if (newValue) {
			String fromId = from.getEditor().getText();
			if (fromId == null || fromId.isEmpty())
				return;
			List<Order> rawOrders = new JsonClient().getCollection("/orders?id=" + fromId, Order.class);
			orders.clear();
			orders.addAll(rawOrders);
		}
	}

	private void tryTransfer() {
		String fromId = from.getEditor().getText();
		if (fromId == null || fromId.isEmpty())
			return;
		String toId = to.getEditor().getText();
		if (toId == null || toId.isEmpty())
			return;
		String o = order.getEditor().getText();
		if (o == null || o.isEmpty())
			return;
		tryTransfer(fromId, toId, o);
	}

	private void tryTransfer(String fromId, String toId, String id) {
		doTransfer.disableProperty().set(true);
		new Thread(() -> transferRun(fromId, toId, id)).start();
	}

	private void transferRun(String fromId, String toId, String id) {
		Batch batch = new JsonClient().get("/transfer?fromId=" + fromId + "&toId=" + toId + "&order=" + id,
				Batch.class);
		for (int i = 0; i < 15; i++) {
			batch = new JsonClient().get("/batch?id=" + batch.getId(), Batch.class);
			final String status = batch.getStatus().equals("Pending") ? "Pending "+i : batch.getStatus();
			final String message = batch.getMessage();
			Platform.runLater( ()->statusLabel.setText(status));
			if (batch.getStatus().equals("Success")) {
				Platform.runLater(() -> {
					doCancel.setText("Finish");
					doTransfer.disableProperty().set(true);
				});
				return;
			}
			if (batch.getStatus().equals("Error")) {
				Platform.runLater(() -> {
					messageLabel.setText(message);
					doCancel.setText("Finish");
					doTransfer.disableProperty().set(true);
				});
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException expected) {
			}
		}
		Platform.runLater(() -> {
			statusLabel.setText("Check later");
			doTransfer.disableProperty().set(true);
			doCancel.setText("Finish");
		});
	}

	private ObservableList<Dealer> populateDealers() {
		List<Dealer> dealers = new JsonClient().getCollection("/dealers", Dealer.class);
		ObservableList<Dealer> result = FXCollections.observableList(dealers);
		return result;
	}
}
