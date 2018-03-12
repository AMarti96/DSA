import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public class Addetakemon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String mycus = request.getParameter("cookie");
        int numero = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Fachada.getInstance().addEtakemon(mycus,numero);
        response.setContentType("application/html");
        response.setCharacterEncoding("UTF-8");
        List<Integer> misetakemons=Fachada.getInstance().returnEtakemons(mycus);
        int sol= misetakemons.size();

        response.getWriter().write("Los etakemons que tienes : "+Integer.toString(sol));

    }

}
