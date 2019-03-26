package genDataNOapplication.Utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Utils {
	

	@SuppressWarnings("static-access")
	public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        System.out.println(node.getId());
	        System.out.println(gridPane.getColumnIndex(node));
	        if(gridPane.getRowIndex(node) == null || gridPane.getColumnIndex(node) == null) {
	        	continue;
	        }
	    	if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
}
