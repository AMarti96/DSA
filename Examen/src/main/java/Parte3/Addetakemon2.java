package Parte3;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adri on 17/11/2016.
 */
public class Addetakemon2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Criatura> atrapados = new ArrayList<Criatura>();
        String mycus = request.getParameter("cookie");
        int numero = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        customers cu = new customers(mycus);
        String sol = null;

        try {
            cu = Fachada2.getInstance().returnUsuario(mycus);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Fachada2.getInstance().addEtakemon(cu, numero);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            atrapados = Fachada2.getInstance().returnEtakemons(cu);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.getWriter().write("Los etakemons que tienes: " + atrapados.size());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        List<String> ret = new ArrayList<String>();
        List<Criatura> sol = null;
        String nombre = request.getParameter("cookie");
        customers us = null;
        try {
            us = Fachada2.getInstance().returnUsuario(nombre);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            sol = Fachada2.getInstance().returnEtakemons(us);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Gson gsonSender = new Gson();
        String gson = gsonSender.toJson(sol);
        Gson gsonReceiver = new Gson();
        List obj = gsonReceiver.fromJson(gson, List.class);
        for (int i = 0; i < obj.size(); i++) {
            String pok = obj.get(i).toString();
            String[] part = pok.split("}");
            String[] poke = part[0].split("=");
            ret.add(poke[1]);
        }

        String json2 = new Gson().toJson(ret);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json2);


    }


}

