package Models.GestionRdv;

import Models.GestionPatient.Patient;
import Models.GestionPatient.PatientDaoImpl;
import database.ConnectToDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RdvDaoImpl implements RdvDao {
    private final Connection conn = ConnectToDatabase.createConnection();
    private final String SQL_CREATE_RDV = "INSERT INTO rdv (patientId, date, heure, objet) VALUES (?, ?, ?, ?)";
    private final String SQL_GET_RDV_BY_ID = "SELECT * FROM rdv WHERE id=?";
    private final String SQL_GET_ALL_RDVS_PATIENT = "SELECT * FROM rdv WHERE patientId=?";
    private final String SQL_GET_ALL_RDVS_JOUR = "SELECT * FROM rdv WHERE date=?";
    private final String SQL_UPDATE_RDV = "UPDATE rdv SET patientId=?, date=?,heure=?, objet=? WHERE id=?";
    private final String SQL_DELETE_RDV = "DELETE FROM rdv WHERE id=?";

    @Override
    public void createRdv(Rdv rdv,Patient patient) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_RDV, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, patient.getPatientId());
            pstmt.setDate(2, rdv.getDate());
            pstmt.setTime(3, rdv.getHeure());
            pstmt.setString(4, rdv.getObjet());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rdv.setRdvId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Rdv getRdvById(int rdvId) {
        Rdv rdv = new Rdv();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_RDV_BY_ID)) {
            pstmt.setInt(1, rdvId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    rdv.setRdvId(rs.getInt(1));
                    rdv.setPatientId(rs.getInt(2));
                    rdv.setDate(rs.getDate(3));
                    rdv.setHeure(rs.getTime(5));
                    rdv.setObjet(rs.getString(4));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rdv;
    }

    @Override
    public ArrayList<Rdv> getAllRdvsPatient(int patientId) {
        ArrayList<Rdv> allRdvsPatient = new ArrayList();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_RDVS_PATIENT)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Rdv rdv = new Rdv();
                    rdv.setRdvId(rs.getInt(1));
                    rdv.setPatientId(patientId);
                    rdv.setDate(rs.getDate(3));
                    rdv.setHeure(rs.getTime(5));
                    rdv.setObjet(rs.getString(4));
                    allRdvsPatient.add(rdv);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allRdvsPatient;
    }

    @Override
    public ArrayList<Rdv> getAllRdvsJour(Date jour) {
        ArrayList<Rdv> allRdvsJour = new ArrayList();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_RDVS_JOUR)) {
            pstmt.setDate(1, jour);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Rdv rdv = new Rdv();
                    rdv.setRdvId(rs.getInt(1));
                    rdv.setPatientId(rs.getInt(2));
                    rdv.setDate(rs.getDate(3));
                    rdv.setHeure(rs.getTime(5));
                    rdv.setObjet(rs.getString(4));
                    allRdvsJour.add(rdv);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allRdvsJour;
    }

    @Override
    public void updateRdv(Rdv rdv) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_RDV)) {
            pstmt.setInt(1, rdv.getPatientId());
            pstmt.setDate(2, rdv.getDate());
            pstmt.setTime(3, rdv.getHeure());
            pstmt.setString(4, rdv.getObjet());
            pstmt.setInt(5, rdv.getRdvId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RdvDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void changeDate(int rdvId, Date jour, Time heure) {
        Rdv rdv = getRdvById(rdvId);
        rdv.setDate(jour);
        rdv.setHeure(heure);
        updateRdv(rdv);
    }

    @Override
    public void deleteRdv(int rdvId) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_RDV)) {
            pstmt.setInt(1, rdvId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RdvDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
