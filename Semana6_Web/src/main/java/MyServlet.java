import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adri on 21/10/2016.
 */

@WebServlet (name = "MyServlet")
public class MyServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

        int fibo1 = 1;
        int fibo2 = 1;
        int num = Integer.parseInt(req.getParameter("num"));
        ServletOutputStream out =res.getOutputStream();

        StringBuffer sb = new StringBuffer();

        sb.append("<html><link href = \"estilo.css\" rel = \"stylesheet\"></html>");

        sb.append("<html><body><h1>Has solicitado "+num+" sucesiones</h1></body></html>");
        sb.append("<html><body>" +
                "<table border='1' style='margin: 0 auto;'>\n" +
                "  <tr>\n" +
                "    <th>1</th>\n" +
                "    <td>"+fibo1+"</th>\n" +
                "  </tr>");
        for(int i=2;i<=num;i++){
            sb.append(
                    "  <tr>\n" +
                    "    <th>"+i+" </th>\n" +
                    "    <td>"+fibo2+"</td>\n" +
                    "  </tr>" + "</body></html>");
            fibo2 = fibo1 + fibo2;
            fibo1 = fibo2 - fibo1;
        }

        out.println(sb.toString());
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

        Float operando1= Float.valueOf((req.getParameter("o1")));
        Float operando2= Float.valueOf((req.getParameter("o2")));
        String operacion=req.getParameter("operacion");
        ServletOutputStream out =res.getOutputStream();
        StringBuffer sb = new StringBuffer();

        sb.append("<html><link href = \"http://localhost:8080/estilo.css\" rel = \"stylesheet\">");
        double sol=0;
        if("SUMA".equals(operacion)){
            sol=operando1+operando2;
        }
        if("RESTA".equals(operacion)){
            sol=operando1-operando2;
        }
        if("MULTIPLICACION".equals(operacion)){
            sol=operando1*operando2;
        }
        if("DIVISION".equals(operacion)){
            sol=operando1/operando2;
        }
        sb.append("<body><h1>EL RESULTADO ES: ");
        sb.append(+sol+"</h1></body></html>");
        out.print(sb.toString());

    }
}
