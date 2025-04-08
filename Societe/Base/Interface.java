package Societe.Base;

import java.util.List;

import javax.servlet.ServletException;

import Societe.entite.Depense;
import Societe.entite.Prevision;

public interface Interface {
    public abstract void save(Prevision p) throws ServletException;
    public abstract void save(Depense d) throws ServletException;
    public abstract List<Prevision> findAllPrevisions() throws ServletException;
    public abstract List<Depense> findAllDepenses() throws ServletException;
    public abstract Prevision findPrevisionsById(int id) throws ServletException;
    public abstract Depense findDepenseById(int id) throws ServletException;
    // public abstract void save(Depense d) throws ServletException;
}
