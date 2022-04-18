package frontend.layout.Staff;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private Button buttonReloadData;
    
    @FXML
    private TableView<PersonData> tableViewStaff;

    @FXML
    private TableColumn<PersonData, String> columnLastName;

    @FXML
    private TableColumn<PersonData, String> columnFirstName;

    private ContextMenuTable<PersonData> table;

    static ObservableList<PersonData> staffList;

    private String sqlloadStaffData = "SELECT * FROM person WHERE role = 'staff'";
    private String sqlDeleteStaff = "DELETE FROM person where email = ?";
    
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
        columnFirstName.setCellValueFactory(new PropertyValueFactory<PersonData, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<PersonData, String>("lastName"));
        this.tableViewStaff.setItems(null);
        this.tableViewStaff.setItems(staffList);
    }

    private void editCellFactories() {
        tableViewStaff.setEditable(true);
        columnFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnFirstName.setOnEditCommit(event -> {
            PersonData staff = event.getRowValue();
            staff.setFirstName(event.getNewValue());
        });
        columnLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLastName.setOnEditCommit(event -> {
            PersonData staff = event.getRowValue();
            staff.setLastName(event.getNewValue());
        });
    }

    /**
     * Initializes the context menu.
     */
    private void setUpMenuItems() {
        this.table.addAutoUpdatingMenuItem("Löschen", (staff) -> deleteStaff());
    }


    /**
     * Executed when right clicking the table view.
     */
    public void showContextMenu() {
        this.table.supressMenuIfNoSelection();
    }
    

    public void loadStaffData(){

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
    public void deleteStaff(){
        String email = tableViewStaff.getSelectionModel().getSelectedItem().getEmail();
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlDeleteStaff);
        
            stmt.setString(1, email);
            stmt.execute();
            conn.close();
                
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
                
                

    }
    @FXML
    void initialize() {
        this.table = new ContextMenuTable<PersonData>(tableViewStaff, () -> staffList);
        this.dc = new dbConnection();
        loadStaffData();
        setUpMenuItems();
        editCellFactories();


    }
}
