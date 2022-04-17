package frontend.layout;

import frontend.data.Staff;
import frontend.util.BackendExceptionHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import frontend.util.ContextMenuTable;
import frontend.util.FxHelper;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class StaffController extends MainController {

    @FXML
    private Button buttonAddStaff;

    @FXML
    private TableView<Staff> tableViewStaff;

    @FXML
    private TableColumn<Staff, String> columnPersonalLastName;

    @FXML
    private TableColumn<Staff, String> columnPersonalFirstName;

    private ContextMenuTable<Staff> table;

    static ObservableList<Staff> staffList;

    @FXML
    public void openStaffPopUp() {
        buttonAddStaff.setDisable(true);
        disableAllButtons();

        FxHelper.displayPopUp("Staff_PopUp", "Reservierungs_Style", () -> {
            buttonAddStaff.setDisable(false);
            reloadStaffTable();
            reenableButtons();
        });
    }

    @FXML
    void initialize() {
        reloadStaffTable();
        this.table = new ContextMenuTable<Staff>(tableViewStaff, () -> staffList);

        setUpCellFactories();
        setUpMenuItems();
        editCellFactories();

        assert buttonAddStaff != null : "fx:id=\"ButtonAddStaff\" was not injected: check your FXML file 'Staff.fxml'.";
        assert tableViewStaff != null : "fx:id=\"staffTab\" was not injected: check your FXML file 'Staff.fxml'.";
        assert columnPersonalFirstName != null
                : "fx:id=\"nameCol\" was not injected: check your FXML file 'Staff.fxml'.";

    }

    public void reloadStaffTable() {
        staffList = BackendExceptionHandler.executeReturnNullIfException(() -> Staff.fetchAll());

    }

    /**
     * Sets the columns' cell factories.
     */
    private void setUpCellFactories() {
        columnPersonalFirstName.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
        columnPersonalLastName.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
    }

    private void editCellFactories() {
        tableViewStaff.setEditable(true);
        columnPersonalFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPersonalFirstName.setOnEditCommit(event -> {
            Staff staff = event.getRowValue();
            staff.setFirstName(event.getNewValue());
        });
        columnPersonalLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPersonalLastName.setOnEditCommit(event -> {
            Staff staff = event.getRowValue();
            staff.setLastName(event.getNewValue());
        });
    }

    /**
     * Initializes the context menu.
     */
    private void setUpMenuItems() {
        this.table.addAutoUpdatingMenuItem("Löschen", (staff) -> tableViewStaff.getItems().removeAll(staff));
    }

    /**
     * Executed when right clicking the table view.
     */
    public void showContextMenu() {
        this.table.supressMenuIfNoSelection();
    }

}
