package frontend.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Class used to manage tables featuring a context menu.
 * The context menu has to be set up with {@link #addMenuItem(String, Consumer)}.
 */
public class ContextMenuTable<T> extends Table<T> {

    private ContextMenuContainer<T> contextMenuContainer;

    /**
     * Constructs a new ContextMenuTable instance displaying the content provided by fetchContent.
     * The context menu has to be set up with {@link #addMenuItem(String, Consumer)}.
     * <br><br>
     * If fetchContent returns null the table is displayed as empty.
     * @param tableView the tableView used to display data
     * @param fetchContent code providing newly fetched content for the table
     */
    public ContextMenuTable(TableView<T> tableView, Supplier<ObservableList<T>> fetchContent) {
        super(tableView, fetchContent);
        this.contextMenuContainer = new ContextMenuContainer<T>((Table<T>) this);
    }

    /**
    * Adds a new menu item / entry to the table view's context menu.
    * The currently selected table entry is passed to onSelect when this menu items is selected.
    * @param text the displayed item text
    * @param onSelect executed with the currently selected table entry when this menu item is selected
    */
    public void addMenuItem(String text, Consumer<T> onSelect) {
        contextMenuContainer.addMenuItem(text, onSelect);
    }

    /**
     * Adds a new menu item / entry to the context menu.
     * The currently selected table entry is passed to onSelect when this menu items is selected.
     * <br><br>
     * The table is automatically updated after executing onSelect.
     * @param text the displayed item text
     * @param onSelect executed with the currently selected table entry when this menu item is selected
     */
    public void addAutoUpdatingMenuItem(String text, Consumer<T> onSelect) {
        contextMenuContainer.addAutoUpdatingMenuItem(text, onSelect);
    }

    /**
    * Supresses the context menu from showing by calling ContextMenu.hide() if no selection exists.
    * Also displays an error message to the user if no selection exists.
    */
    public void supressMenuIfNoSelection() {
        contextMenuContainer.supressIfNoSelection();
    }
}
