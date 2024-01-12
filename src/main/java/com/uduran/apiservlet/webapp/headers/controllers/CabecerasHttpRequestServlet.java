package com.uduran.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String methodHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String serverName = req.getServerName();
        String ip = req.getLocalAddr();
        Integer port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme+"://"+host+contextPath+servletPath;

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("       <meta charset=\"UTF-8\">");
        out.println("       <title>Cabeceras HTTP Request</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Cabeceras HTTP Request!</h1>");
        out.println("        <ul>");
        out.println("            <li>" + methodHttp + "</li>");
        out.println("            <li>" + requestUri + "</li>");
        out.println("            <li>" + requestUrl + "</li>");
        out.println("            <li>" + contextPath + "</li>");
        out.println("            <li>" + servletPath + "</li>");
        out.println("            <li>" + ip + "</li>");
        out.println("            <li>" + serverName + "</li>");
        out.println("            <li>" + port + "</li>");
        out.println("            <li>" + scheme + "</li>");
        out.println("            <li>" + host + "</li>");
        out.println("            <li>" + url + "</li>");

        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String cabecera = headerNames.nextElement();
            out.println("        <li>" + cabecera + ": " + req.getHeader(cabecera) + "</li>");
        }
        out.println("        </ul>");
        out.println("    </body>");
        out.println("</html>");
        out.close();
    }
}
