package servlets; /**
 * Created by Adri on 11/11/2016.
 */
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="MyServlet3")
public class MyServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


            float operando1 = Float.parseFloat(request.getParameter("operador1"));
            float operando2 = Float.parseFloat(request.getParameter("operador2"));
            String operacion = request.getParameter("operacion");
            double sol = 0;
            if ("Sumar".equals(operacion)) {
                sol = operando1 + operando2;

            }
            if ("Restar".equals(operacion)) {
                sol = operando1 - operando2;

            }
            if ("Multiplicar".equals(operacion)) {
                sol = operando1 * operando2;

            }
            if ("Dividir".equals(operacion)) {
                sol = operando1 / operando2;

            }

            PrintWriter out = response.getWriter();
            out.println(sol);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int fibo1 = 1;
        int fibo2 = 1;
        int j = 0;
        int num = Integer.parseInt(request.getParameter("sucesiones"));
        List<Integer> fibo= new ArrayList<Integer>();
        fibo.add(fibo1);
        for (int i = 2; i <= num; i++) {
            fibo.add(fibo2);
            fibo2 = fibo1 + fibo2;
            fibo1 = fibo2 - fibo1;
        }
        String json = new Gson().toJson(fibo);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}
