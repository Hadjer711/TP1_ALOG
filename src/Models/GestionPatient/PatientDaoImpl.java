package Models.GestionPatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.ConnectToDatabase;
public class PatientDaoImpl implements PatientDao {

    private final Connection conn = ConnectToDatabase.createConnection();
    private final String SQL_CREATE_PATIENT = "INSERT INTO patient (nom, prenom, adresse, telephone, mail, infoMedicale) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SQL_GET_PATIENT_BY_ID = "SELECT * FROM patient WHERE id=?";
    private final String SQL_GET_ALL_PATIENTS = "SELECT * FROM patient";
    private final String SQL_UPDATE_PATIENT = "UPDATE patient SET nom=?, prenom=?, adresse=?, telephone=?, mail=?, infoMedicale=? WHERE id=?";
    private final String SQL_DELETE_PATIENT = "DELETE FROM patient WHERE id=?";

    @Override
    public void createPatient(Patient patient) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_PATIENT, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getPrenom());
            pstmt.setString(3, patient.getAdresse());
            pstmt.setString(4, patient.getTelephone());
            pstmt.setString(5, patient.getMail());
            pstmt.setString(6, patient.getInfoMedicale());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setPatientId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Patient getPatientById(int patientId) {
        Patient patient = new Patient();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_PATIENT_BY_ID)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    patient.setPatientId(rs.getInt(1));
                    patient.setNom(rs.getString(2));
                    patient.setPrenom(rs.getString(3));
                    patient.setAdresse(rs.getString(4));
                    patient.setTelephone(rs.getString(5));
                    patient.setMail(rs.getString(6));
                    patient.setInfoMedicale(rs.getString(7));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }

    @Override
    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> allPatients = new ArrayList();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_PATIENTS);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt(1));
                patient.setNom(rs.getString(2));
                patient.setPrenom(rs.getString(3));
                patient.setAdresse(rs.getString(4));
                patient.setTelephone(rs.getString(5));
                patient.setMail(rs.getString(6));
                patient.setInfoMedicale(rs.getString(7));
                allPatients.add(patient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPatients;
    }

    @Override
    public void updatePatient(Patient patient) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_PATIENT)) {
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getPrenom());
            pstmt.setString(3, patient.getAdresse());
            pstmt.setString(4, patient.getTelephone());
            pstmt.setString(5, patient.getMail());
            pstmt.setString(6, patient.getInfoMedicale());
            pstmt.setInt(7, patient.getPatientId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletePatient(int patientId) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PATIENT)) {
            pstmt.setInt(1, patientId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
