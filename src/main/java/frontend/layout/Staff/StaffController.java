package frontend.layout.Staff;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import frontend.data.PersonData;
import frontend.data.Staff;
import frontend.dbUtil.dbConnection;
import frontend.util.BackendExceptionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import frontend.util.ContextMenuTable;
import frontend.util.FxHelper;
import frontend.util.MainController;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class StaffController extends MainController {

    @FXML
    private Button buttonAddStaff;

    @FXML
    private TableView<PersonData> tableViewStaff;

    @FXML
    private TableColumn<PersonData, String> columnPersonalLastName;

    @FXML
    private TableColumn<PersonData, String> columnPersonalFirstName;

    private ContextMenuTable<PersonData> table;

    static ObservableList<PersonData> staffList;

    private String sqlloadStaffData = "SELECT * FROM person";

    
    private dbConnection dc;
    
    @FXML
    public void openStaffPopUp() {
        buttonAddStaff.setDisable(true);
        disableAllButtons();

        FxHelper.displayPopUp("Staff_PopUp", "Reservierungs_Style", () -> {
            buttonAddStaff.setDisable(false);
            reenableButtons();
        });
    }

    


    /**
     * Sets the columns' cell factories.
     */
    private void setUpCellFactories() {
        columnPersonalFirstName.setCellValueFactory(new PropertyValueFactory<PersonData, String>("firstName"));
        columnPersonalLastName.setCellValueFactory(new PropertyValueFactory<PersonData, String>("lastName"));
        //this.tableViewStaff.setItems(null);
        this.tableViewStaff.setItems(staffList);
    }

    private void editCellFactories() {
        tableViewStaff.setEditable(true);
        columnPersonalFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPersonalFirstName.setOnEditCommit(event -> {
            PersonData staff = event.getRowValue();
            staff.setFirstName(event.getNewValue());
        });
        columnPersonalLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPersonalLastName.setOnEditCommit(event -> {
            PersonData staff = event.getRowValue();
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
    

    private void loadStaffData(){

        try {
            
            Connection conn = dbConnection.getConnection();
            this.staffList = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sqlloadStaffData);
            while (rs.next()) {
                this.staffList.add(new PersonData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        
        
        
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        setUpCellFactories();
    }

    @FXML
    void initialize() {
        this.dc = new dbConnection();
        loadStaffData();
        //FIXME: setUpMenuItems();
        editCellFactories();

        assert buttonAddStaff != null : "fx:id=\"ButtonAddStaff\" was not injected: check your FXML file 'Staff.fxml'.";
        assert tableViewStaff != null : "fx:id=\"staffTab\" was not injected: check your FXML file 'Staff.fxml'.";
        assert columnPersonalFirstName != null
                : "fx:id=\"nameCol\" was not injected: check your FXML file 'Staff.fxml'.";

    }
}
