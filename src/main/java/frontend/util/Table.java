package frontend.util;

import java.util.function.Supplier;


import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Class used to manage table views.
 * Allows updating tables by calling Table.update().
 */
public class Table<T> {
    private TableView<T> tableView;
    private ObservableList<T> tableContent;
    private Supplier<ObservableList<T>> fetchContent;

    /**
     * Constructs a new table instance displaying the content provided by fetchContent.
     * This constructor already fetches the data initially and displays it.
     * <br><br>
     * If fetchContent returns null the table is displayed as empty.
     * @param tableView the tableView used to display data
     * @param fetchContent code providing newly fetched content for the table
     * @param menuItems the entries of the table view's context menu
     */
    public Table(TableView<T> tableView, Supplier<ObservableList<T>> fetchContent) {
        this.tableView = tableView;
        this.fetchContent = fetchContent;
        tableView.setItems(tableContent);
        updateTable();
    }

    /**
     * Fetches data from the server and updates the table.
     * <br><br>
     * The currently displayed data is completly overwritten with newly fetched data
     * by executing fetchContent (specified by instantiation).
     * <br><br>
     * If fetchContent returns null the table is displayed as empty.
     */
    public void updateTable() {
        tableView.setItems(fetchContent.get());
    }

    /**
     * @return the table view
     */
    TableView<T> getTable() {
        return this.tableView;
    }
}
