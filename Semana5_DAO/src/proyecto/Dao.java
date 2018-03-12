import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * Created by Adri on 10/10/2016.
 */
public abstract class Dao {

    java.sql.Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsa");

    PreparedStatement prst;

    StringBuffer sb = new StringBuffer();
    Field [] fields;
    Method m;

    protected Dao() throws SQLException {
    }

    private String getMethod(String m) {
        m= m.substring(0,1).toUpperCase()+m.substring(1);
     return "get"+m;
    }

    public void insert()  {

        sb = new StringBuffer();
        sb.append("INSERT INTO ").append(this.getClass().getSimpleName());
        System.out.println(sb.toString());


        fields = this.getClass().getFields();

        sb.append(" (");
        int atrib = 0;
        for (Field f :fields) {
            System.out.println(f.getName());
            sb.append(f.getName()).append(",");
            atrib ++;
        }

        sb.replace(sb.length()-1,sb.length(),") VALUES (");
        for (Field ff:fields){
            sb.append("?,");
        }
        sb.replace(sb.length()-1,sb.length(),") ");

        System.out.println("QUERY: "+sb.toString());

        for (Field f :fields) {

            try {
                m = this.getClass().getMethod(getMethod(f.getName()), null);
                Object ret = m.invoke(this, null);
                if (ret instanceof String) {

                    System.out.println("res : "+ret.toString());
                }
                if (ret instanceof Integer){
                    int id = (int) ret;
                    System.out.println("res : "+id);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public  void update() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {

        sb = new StringBuffer();
        sb.append("UPDATE ").append(this.getClass().getSimpleName()+" SET ");

        System.out.println(this.getClass().getSimpleName());

        fields= this.getClass().getFields();
        for(Field f : fields)
        {
            if(f.getGenericType().toString().equals("int") ) {
                sb.append(f.getName() + "=?, ");
            }
            else{
                sb.append(f.getName() + "='?',");
            }

        }

        sb.replace(sb.length()-1,sb.length()," WHERE ");
        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                sb.append(f.getName().toString()+"=?;");
            }
        }
        System.out.println(sb.toString());

       // prst.executeQuery(sb.toString());
    }

    public  void delete() throws SQLException {

        sb= new StringBuffer();
        sb.append("DELETE FROM ").append(this.getClass().getSimpleName()+" WHERE ");

        System.out.println(this.getClass().getSimpleName());

        fields= this.getClass().getFields();

        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                sb.append(f.getName().toString()+"=?;");
            }
        }
        System.out.println(sb.toString());

       // prst.executeQuery(sb.toString());

    }

    public ResultSet select() throws SQLException {


        sb= new StringBuffer();
        sb.append("SELECT * FROM ").append(this.getClass().getSimpleName()+" WHERE ");

        System.out.println(this.getClass().getSimpleName());
        fields= this.getClass().getFields();
        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                sb.append(f.getName().toString()+"=?;");
            }
        }
        System.out.println(sb.toString());

        ResultSet rs= prst.executeQuery(sb.toString());
        return rs;


    }



}
