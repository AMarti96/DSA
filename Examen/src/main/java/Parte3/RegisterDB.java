package Parte3;

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
 * Created by Adri on 18/11/2016.
 */
public class RegisterDB extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> val = new ArrayList<String>();
        String nombre = request.getParameter("name");
        String password = request.getParameter("password");
        String sol = null;
        customers cu = new customers(nombre, password);

        try {
            sol= cu.select2();
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

        if (sol == null) {
            try {
                cu.insert();
                sol= "Añadido correctamente";

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            sol = "Ya existe el usuario";
        }
        response.setContentType("application/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(sol);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String nombre = request.getParameter("name");
        String password = request.getParameter("password");
        String sol = null;
        customers cu = new customers(nombre, password);

        try {
            sol= cu.select2();
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

        if (sol != null) {

            sol= "Puedes Pasar";
        }
        else{
            sol = "No puedes pasar";
        }
        response.setContentType("application/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(sol);

    }
}
