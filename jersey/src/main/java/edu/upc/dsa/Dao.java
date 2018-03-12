package edu.upc.dsa;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao {

    PreparedStatement prst;
    StringBuffer command= new StringBuffer();
    Field[] fields;
    Method m;
    private Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Connection connect=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect=DriverManager.getConnection("jdbc:mysql://localhost/DSA?useLegacyDatetimeCode=false&serverTimezone=America/New_York","root","root");
        return  connect;

    }

    public void addParams(PreparedStatement prst) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        int i=1;
        for(Field f:fields){

            m=this.getClass().getMethod(getMethod((f.getName())),null);
            Object ret=m.invoke(this,null);

            if(ret instanceof String){
                prst.setString(i, (String) ret);
                System.out.println("res:"+ret.toString());
            }
            if(ret instanceof Integer){

                String id= ret.toString();
                int id2=Integer.parseInt(id);
                prst.setInt(i, id2);
                System.out.println("res:"+id);
            }
            i++;

        }
    }
    public void setParams(PreparedStatement prst) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        int i=1;
        for(Field f:fields){

            m=this.getClass().getMethod(getMethod((f.getName())),null);
            Object ret=m.invoke(this,null);

            if(ret instanceof String){
                prst.setString(i, (String) ret);
                System.out.println("res:"+ret.toString());
            }
            if(ret instanceof Integer){

                String id= ret.toString();
                int id2=Integer.parseInt(id);
                prst.setInt(i, id2);
                prst.setInt(4,id2);
                System.out.println("res:"+id);
            }
            i++;

        }
    }
    private  String getMethod(String m){

        m =m.substring(0, 1).toUpperCase() +m.substring(1);
        return "get"+m;

    }

    public void insert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException, ClassNotFoundException {

        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("INSERT INTO ").append(this.getClass().getSimpleName()+" (");

        System.out.println(this.getClass().getSimpleName());
        fields= this.getClass().getFields();

        for(Field f : fields)
        {
            command.append(f.getName()+",");

        }

        command.replace(command.length()-1,command.length(),") VALUES (");
        for(Field f : fields)
        {
            command.append("?,");

        }
        command.replace(command.length()-1,command.length(),");");

        System.out.println(command.toString());

        // CHANGE
        String query=command.toString();
        prst= con.prepareStatement(query);
        this.addParams(prst);
        prst.execute();

    }
    public  void update() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {

        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("UPDATE ").append(this.getClass().getSimpleName()+" SET ");

        System.out.println(this.getClass().getSimpleName());

        fields= this.getClass().getFields();
        for(Field f : fields)
        {
            command.append(f.getName() + "=?, ");

        }
        command.replace(command.length()-2,command.length()," WHERE ");
        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                command.append(f.getName().toString()+"=?;");
            }
        }

        String query=command.toString();
        prst= con.prepareStatement(query);
        this.setParams(prst);

        prst.execute();
    }
    public  void delete() throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {


        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("DELETE FROM ").append(this.getClass().getSimpleName()+" WHERE ");

        System.out.println(this.getClass().getSimpleName());

        fields= this.getClass().getFields();

        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                command.append(f.getName().toString()+"=?;");
            }
        }
        System.out.println(command.toString());
        String query=command.toString();
        prst= con.prepareStatement(query);
        prst.execute();

    }

    public List<track> select() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<track>lst=new ArrayList<>();
        StringBuffer sb=new StringBuffer();
        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("SELECT * FROM ").append(this.getClass().getSimpleName());

        String query=command.toString();
        prst= con.prepareStatement(query);
        ResultSet rs= prst.executeQuery();
        rs.beforeFirst();
        ResultSetMetaData rsmd= rs.getMetaData();
        rs.next();
        for(int i=1;i<rsmd.getColumnCount()+1;i++){

            try {
                track tr=new track();
                if (rsmd.getColumnTypeName(i).equals("VARCHAR")) {
                    sb.append(rs.getString(i));
                    tr.setSinger(rs.getString(i));
                    i++;
                }
                if (rsmd.getColumnTypeName(i).equals("VARCHAR")) {
                    sb.append(rs.getString(i));
                    tr.setTitle(rs.getString(i));
                    lst.add(tr);

                }
                if(i==rsmd.getColumnCount()){
                    rs.next();
                    i=0;
                }



            }
            catch (Exception e){

            }

        }
return lst;
    }

}
