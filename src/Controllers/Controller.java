package Controllers;

import Models.GestionPatient.Patient;
import Models.ViewModal;
import com.jfoenix.controls.JFXButton;
import com.sun.tools.javac.Main;
import database.ConnectToDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import Models.GestionRdv.Rdv;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ListeRdvController implements Initializable {

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



    ObservableList<ViewModal> rdvTableDate= FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int i=0;
        try {
            Connection conn= ConnectToDatabase.createConnection();
            PreparedStatement statement= conn.prepareStatement("SELECT * FROM `rdv` JOIN `patient` WHERE objet =?");
            java.sql.Date today= new java.sql.Date(new java.util.Date().getTime());
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


        }
        catch (SQLException e){
            e.printStackTrace();
        }







    }




    public void changeScreen(javafx.event.ActionEvent event) {

        try {
            Parent ajoutRdv= FXMLLoader.load(getClass().getResource("Views/AjouterRdv.fxml"));
            Scene ajoutRdvScene= new Scene(ajoutRdv, 1000, 650);
            Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(ajoutRdvScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
