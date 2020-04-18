package sample;

import Models.GestionPatient.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;
import Models.GestionRdv.Rdv;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ListeRdvController implements Initializable {

    @FXML
    private TableView<Rdv> listeRdvTable;

    @FXML
    private TableColumn<Rdv,Integer> id_col;

    @FXML
    private TableColumn<Rdv, Patient> patient_col;

    @FXML
    private TableColumn<Rdv, LocalDate> date_col;
    @FXML
    private TableColumn<Rdv, LocalTime> heure_col;
    @FXML
    private TableColumn<Rdv, String> objet_col;
    @FXML
    private TableColumn<Rdv, Button> update_col;

    @FXML
    private TableColumn<Rdv, Button> delete_col;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadDate();


    }

    private void initTable(){
        initCol();
    }

    private void initCol(){
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        //patient_col.setCellValueFactory(new PropertyValueFactory<>("patient"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        heure_col.setCellValueFactory(new PropertyValueFactory<>("heure"));
        objet_col.setCellValueFactory(new PropertyValueFactory<>("objet"));
        update_col.setCellValueFactory(new PropertyValueFactory<>("update"));
        delete_col.setCellValueFactory(new PropertyValueFactory<>("delete"));

        //editTableCol();
    }

   /* private void editTableCol(){
        date_col.setCellValueFactory(TextFieldTableCell.forTableColumn());
        date_col.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue());
        });


        heure_col.setCellValueFactory(TextFieldTableCell.forTableColumn());
        heure_col.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setHeure(e.getNewValue());
        });

        objet_col.setCellValueFactory(TextFieldTableCell.forTableColumn());
        objet_col.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setObjet(e.getNewValue());
        });

        listeRdvTable.setEditable(true);

    }*/

    private void loadDate(){
        ObservableList<Rdv> rdvTableDate= FXCollections.observableArrayList();
        for (int i=0; i<7; i++){
            rdvTableDate.add(new Rdv(i, LocalDate.now(), LocalTime.now(), "test", new Button("update"), new Button("delete")));
        }

        listeRdvTable.setItems(rdvTableDate);
    }
}
