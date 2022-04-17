package frontend.util;

import java.util.function.Consumer;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

/**
 * Class used to display context menus.
 * Only works for tables (could not use Control class as it does not implement getSelectionModel()).
 */
public class ContextMenuContainer<T> {
    private ContextMenu menu;
    private TableView<T> tableView;
    private Table<T> table;

    /**
     * Constructs a new ContextMenuContainer by constructing a ContextMenu and adding it to the table.
     * @param table the table for which a context menu should be set up
     */
    public ContextMenuContainer(Table<T> table) {
        this.table = table;
        this.tableView = table.getTable();
        this.menu = new ContextMenu();
        tableView.setContextMenu(menu);
    }

    /**
     * Returns the currently selected table row as an instance of the type displayed by the table view.
     * @return the currently selected item
     */
    private T selectedItem() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Adds a new menu item / entry to the context menu.
     * The currently selected table entry is passed to onSelect when this menu items is selected.
     * @param text the displayed item text
     * @param onSelect executed with the currently selected table entry when this menu item is selected
     */
    public void addMenuItem(String text, Consumer<T> onSelect) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction((event) -> onSelect.accept(selectedItem()));
        menu.getItems().add(menuItem);
    }

    /**
     * Adds a new menu item / entry to the context menu.
     * The currently selected table entry is passed to onSelect when this menu items is selected.
     * <br><br>
     * The table is automatically updated after executing onSelect by fetching data from
     * the server and overwriting the table contents with it.
     * <br><br>
     * Equivalent to invoking Table.update() within onSelect.
     * @param text the displayed item text
     * @param onSelect executed with the currently selected table entry when this menu item is selected
     */
    public void addAutoUpdatingMenuItem(String text, Consumer<T> onSelect) {
        addMenuItem(text, (selectedItem) -> {
            onSelect.accept(selectedItem);
            table.updateTable();
        });
    }

    /**
     * Supresses the context menu from showing by calling ContextMenu.hide() if no selection exists.
     * Also displays an error message to the user if no selection exists.
     */
    public void supressIfNoSelection() {
        if (selectedItem() == null) {
            this.menu.hide();
            //TODO set appropriate error message
            //TODO use a helper method to display error pop-ups
            FxHelper.displayPopUp("Error_PopUp", () -> {
                //do nothing on close
            });
        }
    }

}
