package proyecto;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * Created by Adri on 10/10/2016.
 */
public abstract class Dao {

    PreparedStatement pstm;

    StringBuffer sb = new StringBuffer();
    Field [] fields;
    Method m;

    private Connection getConnection() {
        Connection connect=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/dsa?useLegacyDatetimeCode=false&serverTimezone=America/New_York","root","root");
            System.out.println("Connection!!!!");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }

        return connect;
    }

    private String getMethod(String m) {
        m= m.substring(0,1).toUpperCase()+m.substring(1);
     return "get"+m;
    }

    public  void setId(PreparedStatement pstm) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        int i=1;
        for(Field f:fields){

            m=this.getClass().getMethod(getMethod((f.getName())),null);
            Object ret=m.invoke(this,null);

            if(ret instanceof Integer){

                if(f.getName().equals("id")) {
                    String id = ret.toString();
                    int id2 = Integer.parseInt(id);
                    pstm.setInt(i, id2);
                    System.out.println("res:" + id);
                }
            }
            i++;

        }
    }
    public void setParams(PreparedStatement pstm) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        int i=1;
        for(Field f:fields){

            m=this.getClass().getMethod(getMethod((f.getName())),null);
            Object ret=m.invoke(this,null);

            if(ret instanceof String){
                pstm.setString(i, (String) ret);
                System.out.println("res:"+ret.toString());
            }
            if(ret instanceof Integer){

                String id= ret.toString();
                int id2=Integer.parseInt(id);
                pstm.setInt(i, id2);
                pstm.setInt(4,id2);
                System.out.println("res:"+id);
            }
            i++;

        }
    }

    public void addParams(PreparedStatement pstm) throws SQLException {

        int contador = 0;

        for (Field f :fields) {

            try {
                m = this.getClass().getMethod(getMethod(f.getName()), null);
                Object ret = m.invoke(this, null);

                if (ret instanceof String) {

                    if (contador == 0) {

                        pstm.setString(2,ret.toString());
                    }
                    else {
                        pstm.setString(3,ret.toString());
                    }
                    contador ++;
                }
                if (ret instanceof Integer){
                    int id = (int) ret;
                    pstm.setInt(1,id);
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

    public void insert()  {

        Connection conn = this.getConnection();

        sb = new StringBuffer();
        sb.append("INSERT INTO ").append(this.getClass().getSimpleName());
        System.out.println(sb.toString());

        fields = this.getClass().getFields();

        sb.append(" (");
        for (Field f :fields) {
            System.out.println(f.getName());
            sb.append(f.getName()).append(",");

        }

        sb.replace(sb.length()-1,sb.length(),") VALUES (");
        for (Field ff:fields){
            sb.append("?,");
        }
        sb.replace(sb.length()-1,sb.length(),") ");

        System.out.println("**QUERY: "+sb.toString());

        String query =sb.toString();

        try {
            pstm = conn.prepareStatement(query);

            this.addParams(pstm);

            System.out.println("QUERY GUD: " + pstm.toString());

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public  void update() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {

        Connection con = this.getConnection();
        sb = new StringBuffer();
        sb.append("UPDATE ").append(this.getClass().getSimpleName()).append(" SET ");

        System.out.println(this.getClass().getSimpleName());

        fields= this.getClass().getFields();
        for(Field f : fields)
        {
                sb.append(f.getName()).append("=?, ");
        }

        sb.replace(sb.length()-2,sb.length()," WHERE ");
        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                sb.append(f.getName().toString()).append("=?;");
            }
        }
        System.out.println(sb.toString());
        String query = sb.toString();
        pstm = con.prepareStatement(query);
        this.setParams(pstm);
        System.out.println("QUERY GUD: " + pstm.toString());
        pstm.execute();
    }

    public  void delete() throws SQLException {

        Connection con = this.getConnection();
        sb= new StringBuffer();
        sb.append("DELETE FROM ").append(this.getClass().getSimpleName()).append(" WHERE ");

        System.out.println(this.getClass().getSimpleName());

        fields= this.getClass().getFields();

        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                sb.append(f.getName().toString()).append("=?;");
            }
        }
        System.out.println(sb.toString());
        String query = sb.toString();

        try {
            pstm = con.prepareStatement(query);
            this.setId(pstm);
            System.out.println("QUERY GUD: " + pstm.toString());
            pstm.execute();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void select() throws SQLException {

        Connection con = this.getConnection();
        sb= new StringBuffer();
        sb.append("SELECT * FROM ").append(this.getClass().getSimpleName()).append(" WHERE ");

        System.out.println(this.getClass().getSimpleName());
        fields= this.getClass().getFields();
        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                sb.append(f.getName().toString()).append("=?;");
            }
        }
        System.out.println(sb.toString());
        String query = sb.toString();

        try {
            pstm = con.prepareStatement(query);
            this.setId(pstm);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ResultSet rs= pstm.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        for(int i=1;i<rsmd.getColumnCount()+1;i++){
            try {

                if (rsmd.getColumnTypeName(i).equals("INT")) {
                    System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getInt(i));
                }
                if (rsmd.getColumnTypeName(i).equals("VARCHAR")) {
                    System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getString(i));
                }
                if(i==rsmd.getColumnCount()){
                    rs.next();
                    i=0;
                }
            }
            catch (Exception e){

            }

        }

    }

}
