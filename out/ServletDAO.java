package Societe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import Societe.Base.Base;
import Societe.Base.Interface;
import Societe.entite.Depense;
import Societe.entite.Prevision;

public class ServletDAO implements Interface{

    public void save(Prevision p) throws ServletException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Base.getMySQLConnection(); 
            ps = conn.prepareStatement("INSERT INTO prevision (libelle, montant) VALUES (?,?)");
            ps.setString(1, p.getLibelle());
            ps.setInt(2, p.getMontant());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException(ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void save(Depense d) throws ServletException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Base.getMySQLConnection(); 
            ps = conn.prepareStatement("INSERT INTO depense (idPrevision, montant) VALUES (?,?)");
            ps.setInt(1, d.getIdPrevion());
            ps.setInt(2, d.getMontant());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException(ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Prevision> findAllPrevisions() throws ServletException {
        List<Prevision> list = new ArrayList<>();
        String sql = "select * from prevision";
        try (PreparedStatement stmt = Base.getMySQLConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String libelle = rs.getString("libelle");
                int montant = rs.getInt("montant");
                list.add(new Prevision(id, libelle, montant));
            }
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
        return list;
    }

    public Prevision findPrevisionsById(int id) throws ServletException {
        Prevision pr = new Prevision(0, null, 0);
        String sql = "select * from prevision where id = "+id;
        try (PreparedStatement stmt = Base.getMySQLConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String libelle = rs.getString("libelle");
                int montant = rs.getInt("montant");
                pr.setId(id);
                pr.setLibelle(libelle);
                pr.setMontant(montant);
            }
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
        return pr;
    }

    public Depense findDepenseById(int idp) throws ServletException {
        Depense pr = new Depense(0, 0, 0);
        String sql = "select id,sum(montant) total from depense where idPrevision = "+idp+" group by idPrevision";
        try (PreparedStatement stmt = Base.getMySQLConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int montant = rs.getInt("total");
                pr.setId(id);
                pr.setIdPrevion(idp);
                pr.setMontant(montant);
            }
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
        return pr;
    }

    public List<Depense> findAllDepenses() throws ServletException {
        List<Depense> list = new ArrayList<>();
        String sql = "select * from depense";
        try (PreparedStatement stmt = Base.getMySQLConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idp = rs.getInt("idPrevision");
                int montant = rs.getInt("montant");
                list.add(new Depense(id, idp, montant));
            }
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
        return list;
    }
    
}
