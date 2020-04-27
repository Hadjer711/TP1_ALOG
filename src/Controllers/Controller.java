package Controllers;

import Models.GestionPatient.Patient;
import Models.GestionPatient.PatientDaoImpl;
import Models.GestionRdv.RdvDaoImpl;
import Models.Impression;
import Models.ViewModal;
import com.jfoenix.controls.*;

import database.ConnectToDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


import javafx.fxml.Initializable;



import java.net.URL;
import java.sql.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Models.GestionRdv.Rdv;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


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

    @FXML private Pane modif;

     @FXML private JFXButton ajoutRdv;

    @FXML private JFXButton supBtn;
    @FXML private JFXButton imprimBtn;
    @FXML private JFXButton editBtn;

    @FXML private JFXButton saveModif;
    @FXML private JFXTextField objModif;
    @FXML private JFXDatePicker datemodif;
    @FXML private JFXTimePicker heuremodif;
    @FXML private Label labelModif;
    @FXML private Label idmodif;
    @FXML private  Pane pane;

    @FXML private JFXComboBox<String> patientSearch= new JFXComboBox<String>();
    @FXML private JFXDatePicker choose_date;
    @FXML private JFXTimePicker choose_heure;
    @FXML private JFXDialog dialog ;

    @FXML private JFXButton filter;


    ObservableList<ViewModal> rdvTableDate= FXCollections.observableArrayList();


    PatientDaoImpl patientBDD= new PatientDaoImpl();
    RdvDaoImpl rdvBdd =new RdvDaoImpl();
    ArrayList rdvListe= new ArrayList<Rdv>();
    ArrayList patientListe= new ArrayList<Patient>();
    Rdv rdv;
    Patient patient=new Patient();
    Impression impression= new Impression();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



            java.sql.Date today= new java.sql.Date(new java.util.Date().getTime());

            initialiserData();

            ajoutRdv.setOnAction(actionEvent -> {
                Rdv rdv =new Rdv(date_rdv.getValue(), heure_rdv.getValue(), obj_rdv.getText());

                Patient patient=new Patient(nom_rdv.getText(), prenom_rdv.getText(),adr_rdv.getText(),tel_rdv.getText(), email_rdv.getText(), info_rdv.getText());
                patientBDD.createPatient(patient);
                rdvBdd.createRdv(rdv, patient);
                System.out.println(rdv);
                System.out.println(patient.getInfoMedicale());
                initialiserData();

            });


            supBtn.setOnAction(actionEvent -> {
                ViewModal item = Table.getSelectionModel().getSelectedItem();
                int id=Integer.parseInt(item.getId());
                System.out.println(id);
                rdvBdd.deleteRdv(id);
                initialiserData();

            });

            editBtn.setOnAction(actionEvent -> {
                ViewModal item = Table.getSelectionModel().getSelectedItem();
                int idmodif=Integer.parseInt(item.getId());
                modif.setVisible(true);
                labelModif.setText("Modification du RDV num"+ idmodif);


            });

            saveModif.setOnAction(actionEvent -> {
                LocalTime newheure= heuremodif.getValue();
                LocalDate newdate = datemodif.getValue();
                String newobj =objModif.getText();
                ViewModal item = Table.getSelectionModel().getSelectedItem();
                int idmodif=Integer.parseInt(item.getId());
                rdvBdd.changeDate(idmodif,newdate, newheure);
                initialiserData();
                modif.setVisible(false);


            });

            imprimBtn.setOnAction(actionEvent -> {
                ViewModal item = Table.getSelectionModel().getSelectedItem();
                int idimprm=Integer.parseInt(item.getId());
                impression.imprimerRdv(idimprm);


            });




          /*  filter.setOnAction(actionEvent -> {
               LocalDate date=choose_date.getValue();
               //LocalTime heure= choose_heure.getValue();
                Table.getItems().clear();
                int i=1;
                Connection conn= ConnectToDatabase.createConnection();
                PreparedStatement statement= null;
                try {
                    statement = conn.prepareStatement("SELECT * FROM `rdv` LEFT OUTER JOIN `patient` ON patient.id=rdv.patientId WHERE rdv.date=? ");
                    System.out.println(date);
                    statement.setDate(1, Date.valueOf(date));
                    System.out.println(statement);
                   // statement.setTime(2, Time.valueOf(heure));
                    ResultSet rs =statement.executeQuery();
                    System.out.println(rs.getString(0));
                    while (rs.next()){
                        System.out.println("gg"+ rs.getString(i));

                        rdvTableDate.add(new ViewModal(Integer.toString(rs.getInt("id")), rs.getString("nom"), rs.getString("prenom"), rs.getTime("heure").toString(), rs.getString("objet"), rs.getString("infoMedicale")));
                        System.out.println(rdvTableDate.get(i).getId());
                        i++;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                id_col.setCellValueFactory(new PropertyValueFactory<>("Id"));
                nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                prenom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
                heure_col.setCellValueFactory(new PropertyValueFactory<>("Heure"));
                objet_col.setCellValueFactory(new PropertyValueFactory<>("Objet"));
                info_col.setCellValueFactory(new PropertyValueFactory<>("Info"));

                Table.getColumns().setAll(id_col,nom_col,prenom_col,heure_col,objet_col,info_col);

                Table.setItems(rdvTableDate);


            });*/








    }

    public void initialiserData(){
        patientListe=patientBDD.getAllPatients();

        for (int j=0; j<patientListe.size(); j++){
            patient = (Patient) patientListe.get(j);
            System.out.println(patient.getNom()+patient.getPrenom());
            String item= patient.getNom()+" "+patient.getPrenom();
            patientSearch.getItems().add(item);

        }

        Table.getItems().clear();
        int i=0;
        Connection conn= ConnectToDatabase.createConnection();
        PreparedStatement statement= null;
        try {
            statement = conn.prepareStatement("SELECT * FROM `rdv` LEFT OUTER JOIN `patient` ON patient.id=rdv.patientId");

        ResultSet rs =statement.executeQuery();
        while (rs.next()){

            rdvTableDate.add(new ViewModal(Integer.toString(rs.getInt("id")), rs.getString("nom"), rs.getString("prenom"), rs.getTime("heure").toString(), rs.getString("objet"), rs.getString("infoMedicale")));
            System.out.println(rdvTableDate.get(i).getId());
            i++;
        }

        } catch (SQLException e) {
            e.printStackTrace();
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








}
