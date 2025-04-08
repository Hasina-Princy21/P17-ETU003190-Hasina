package com.itu;

import java.io.IOException;
// import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Societe.DAO.ServletDAO;
import Societe.entite.Prevision;

public class PrevisionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletDAO s = new ServletDAO();
        List<Prevision> list = s.findAllPrevisions();

        req.setAttribute("list", list);

        RequestDispatcher dispat = req.getRequestDispatcher("/depense.jsp");
        dispat.forward(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String libelle = req.getParameter("libelle");
        int montant = Integer.parseInt(req.getParameter("montant"));

        // PrintWriter out = res.getWriter();
        // out.println(libelle +"  "+montant);
        ServletDAO s = new ServletDAO();
        Prevision p = new Prevision(0, libelle, montant);
        s.save(p);

        RequestDispatcher dispat = req.getRequestDispatcher("/index.html");
        dispat.forward(req,res);
   }
}