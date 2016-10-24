package vls;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class SmartLogView {
	
	public Tab getTab() {
		Tab tab = new Tab("Smart Log");
		HBox pane = new HBox();
		TreeTableView<SmartLogItem> tree = new TreeTableView<>();
		TreeTableColumn<SmartLogItem, String> labelCol = new TreeTableColumn<>("Item");
		labelCol.setPrefWidth(150);
		labelCol.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<SmartLogItem, String> param) -> new ReadOnlyStringWrapper(
						param.getValue().getValue().getLabel()));
		
//		labelCol.setCellFactory(new Callback<TreeTableColumn<SmartLogItem,String>,TreeTableCell<SmartLogItem,String>>() {
//
//			@Override
//			public TreeTableCell<SmartLogItem, String> call(TreeTableColumn<SmartLogItem, String> param) {
//				TreeTableCell<SmartLogItem,String> result = new TreeTableCell<SmartLogItem,String>();
//				TreeTableView<SmartLogItem> treeTable = param.getTreeTableView();
//				param.getValue();
//                TreeItem<SmartLogItem> treeItem = treeTable.getTreeItem();
//                result.setText(treeItem.getValue().getLabel());
//				result.setFont(new Font("System",16d));
//				return result;
//			} });
//				
				
		tree.setStyle("-fx-font: 16px 'Consolas';");
		tree.getColumns().add(labelCol);

		TreeTableColumn<SmartLogItem, String> valueCol = new TreeTableColumn<>("Item");
		valueCol.setPrefWidth(150);
		valueCol.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<SmartLogItem, String> param) -> new ReadOnlyStringWrapper(
						param.getValue().getValue().getValue()));
		tree.getColumns().add(valueCol);
		tree.setRoot(SmartLogItem.root);
		tree.setShowRoot(false);

		pane.getChildren().add(tree);
		tab.setContent(pane);
		return tab;
	}
}
