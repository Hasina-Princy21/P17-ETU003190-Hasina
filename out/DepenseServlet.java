package com.itu;

import java.io.IOException;
import java.io.PrintWriter;
// import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Societe.DAO.ServletDAO;
import Societe.entite.Depense;
import Societe.entite.Prevision;

public class DepenseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletDAO s = new ServletDAO();
        List<Prevision> listP = s.findAllPrevisions();
        List<Depense> listD = s.findAllDepenses();
        req.setAttribute("listP", listP);
        req.setAttribute("listD", listD);
        RequestDispatcher dispat = req.getRequestDispatcher("/list.jsp");
        dispat.forward(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        int idP = Integer.parseInt(req.getParameter("idprevision"));
        int montant = Integer.parseInt(req.getParameter("montant"));

        ServletDAO s = new ServletDAO();
        Prevision pr = s.findPrevisionsById(idP);
        Depense dep = s.findDepenseById(idP);
        if (dep.getId() != 0) {
            int reste = dep.getMontant() + montant;
            if (reste > pr.getMontant()) {
                out.println("Votre solde a depasser les totals de prevision");
            }else{
                Depense d = new Depense(0, idP, montant);
                s.save(d);
                List<Prevision> list = s.findAllPrevisions();
                req.setAttribute("list", list);
                RequestDispatcher dispat = req.getRequestDispatcher("/depense.jsp");
                dispat.forward(req,res);
            }
        }else{
            Prevision p = s.findPrevisionsById(idP);
            if (p.getMontant() < montant) {
                out.println("Solde invalide ou superieur au prevision");
            }else{
                Depense d = new Depense(0, idP, montant);
                s.save(d);
                List<Prevision> list = s.findAllPrevisions();
                req.setAttribute("list", list);
                RequestDispatcher dispat = req.getRequestDispatcher("/depense.jsp");
                dispat.forward(req,res);
            }
        }
    }
}