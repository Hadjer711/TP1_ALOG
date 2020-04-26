package Controllers;

import Models.GestionPatient.Patient;
import Models.GestionPatient.PatientDaoImpl;
import Models.GestionRdv.RdvDaoImpl;
import Models.ViewModal;
import com.jfoenix.controls.*;

import database.ConnectToDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


import javafx.fxml.Initializable;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import Models.GestionRdv.Rdv;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class Controller implements Initializable {

    @FXML
    private TableView<ViewModal> Table;

    @FXML
    private TableColumn<ViewModal,String> id_col;

    @FXML
    private TableColumn<ViewModal, String> nom_col;

    @FXML
    private TableColumn<ViewModal, String> prenom_col;
    @FXML
    private TableColumn<ViewModal, String> heure_col;
    @FXML
    private TableColumn<ViewModal, String> objet_col;

    @FXML
    private TableColumn<ViewModal, String> info_col;

    @FXML
    private JFXButton AjoutRdvBtn;

    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane cible;

    @FXML private JFXDatePicker date_rdv;
    @FXML private JFXTimePicker heure_rdv;
    @FXML private JFXTextArea obj_rdv;
    @FXML private JFXTextField nom_rdv;
    @FXML private JFXTextField tel_rdv;
    @FXML private JFXTextField prenom_rdv;
    @FXML private JFXTextField email_rdv;
    @FXML private JFXTextArea info_rdv;
    @FXML private JFXTextField adr_rdv;

     @FXML private JFXButton ajoutRdv;

    @FXML private JFXButton supBtn;
    @FXML private JFXButton imprimBtn;
    @FXML private JFXButton editBtn;



    ObservableList<ViewModal> rdvTableDate= FXCollections.observableArrayList();


    PatientDaoImpl patientBDD= new PatientDaoImpl();
    RdvDaoImpl rdvBdd =new RdvDaoImpl();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int i=0;
        try {
            java.sql.Date today= new java.sql.Date(new java.util.Date().getTime());

            Connection conn= ConnectToDatabase.createConnection();
            PreparedStatement statement= conn.prepareStatement("SELECT * FROM `rdv` INNER JOIN `patient` WHERE objet =?");

            System.out.println(today);
            statement.setString(1,"objet");
            ResultSet rs =statement.executeQuery();


            while (rs.next()){

                rdvTableDate.add(new ViewModal(Integer.toString(i), rs.getString("nom"), rs.getString("prenom"), rs.getTime("heure").toString(), rs.getString("objet"), rs.getString("infoMedicale")));
                System.out.println(rdvTableDate.get(i).getId());
                i++;
            }


            id_col.setCellValueFactory(new PropertyValueFactory<>("Id"));
            nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            prenom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            heure_col.setCellValueFactory(new PropertyValueFactory<>("Heure"));
            objet_col.setCellValueFactory(new PropertyValueFactory<>("Objet"));
            info_col.setCellValueFactory(new PropertyValueFactory<>("Info"));

             Table.getColumns().setAll(id_col,nom_col,prenom_col,heure_col,objet_col,info_col);

            Table.setItems(rdvTableDate);

            ajoutRdv.setOnAction(actionEvent -> {
                Rdv rdv =new Rdv(date_rdv.getValue(), heure_rdv.getValue(), obj_rdv.getText());
                Patient patient=new Patient(nom_rdv.getText(), prenom_rdv.getText(),adr_rdv.getText(),tel_rdv.getText(), email_rdv.getText(), info_rdv.getText());
                patientBDD.createPatient(patient);
                rdvBdd.createRdv(patient,rdv);
                System.out.println(rdv.getObjet());
                System.out.println(patient.getInfoMedicale());

            });


            supBtn.setOnAction(actionEvent -> {
                ViewModal item = Table.getSelectionModel().getSelectedItem();
                int id=Integer.parseInt(item.getId());
                System.out.println(id);
                rdvBdd.deleteRdv(id);

            });


        }
        catch (SQLException e){
            e.printStackTrace();
        }








    }






}
