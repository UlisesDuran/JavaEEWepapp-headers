package com.uduran.apiservlet.webapp.headers.controllers;

import com.uduran.apiservlet.webapp.headers.models.Producto;
import com.uduran.apiservlet.webapp.headers.services.ProductoService;
import com.uduran.apiservlet.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            String servletPath = req.getServletPath();
            boolean isXls = servletPath.contains(".xls");

            if (isXls) {
                resp.setContentType("application/vnd.ms-excel");
                resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
            }
            if (!isXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Listado de productos</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Listado de productos!</h1>");
                out.println("        <p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a xls</a></p>");
                out.println("        <p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar Json</a></p>");
            }
            out.println("        <table>");
            out.println("        <tr>");
            out.println("        <th>id</th>");
            out.println("        <th>nombre</th>");
            out.println("        <th>tipo</th>");
            out.println("        <th>precio</th>");
            out.println("        </tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                out.println("<td>" + p.getPrecio() + "</td>");
                out.println("</tr>");
            });
            if (!isXls) {
                out.println("         </table>");
                out.println("    </body>");
                out.println("</html>");
            }

        }
    }
}
