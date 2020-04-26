import Models.ViewModal;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public ObservableList<ViewModal> rdvTableDate= FXCollections.observableArrayList();





    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/View.fxml"));
        primaryStage.setTitle("RDV Medical");

        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
       /* try {
            Connection conn= ConnectToDatabase.createConnection();
            PreparedStatement statement= conn.prepareStatement("SELECT * FROM `rdv` WHERE objet =?");

            java.sql.Date today= new java.sql.Date(new java.util.Date().getTime());
            System.out.println(today);
            statement.setString(1,"objet");
            ResultSet rs =statement.executeQuery();
             int i=0;
             System.out.println(rs.first());

            while (rs.next()){

                System.out.println(rs.getString("heure"));
                //this.rdvTableDate.add(new ViewModal(i, rs.getString("nom"), rs.getString("prenom"), rs.getTime("heure").toString(), rs.getString("objet"), rs.getString("infoMedicale")));
                i++;
            }}
        catch (SQLException e){
            e.printStackTrace();
        }*/

        launch(args);
    }
}
