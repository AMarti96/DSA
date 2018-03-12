import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adri on 16/11/2016.
 */
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> val = new ArrayList<String>();
        String nombre = request.getParameter("name");
        String password = request.getParameter("password");

        customers cu = new customers(nombre, password);
        String sol = null;
        try {
            cu = Fachada.getInstance().returnUsuario(nombre);

        } catch (Exception e){
            e.printStackTrace();
        }

        if (cu == null) {
            try {
                Fachada.getInstance().addUsuario(nombre,password);
                sol= "Añadido correctamente";

            } catch (Exception e){
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
            cu = Fachada.getInstance().returnUsuario(nombre);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (cu.nombre != null) {

                sol="Puedes Pasar";
        }
        else{
            sol = "No puedes pasar";
        }
        response.setContentType("application/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(sol);

    }
}

