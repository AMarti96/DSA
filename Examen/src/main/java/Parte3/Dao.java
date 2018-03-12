package Parte3;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.List;


/**
 * Created by Turpitude on 10/10/2016.
 */
public abstract class Dao {

    PreparedStatement prst;
    StringBuffer command= new StringBuffer();
    Field[] fields;
    Method m;
    private Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Connection connect=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect=DriverManager.getConnection("jdbc:mysql://localhost/dsa?useLegacyDatetimeCode=false&serverTimezone=America/New_York","root","root");
        return  connect;

    }

    public  void setName(PreparedStatement prst) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        int i=1;
        for(Field f:fields){

            m=this.getClass().getMethod(getMethod((f.getName())),null);
            Object ret=m.invoke(this,null);

            if(ret instanceof String){

                if(f.getName().equals("name")) {
                    String name = ret.toString();
                    prst.setString(i, name);
                }
            }
            i++;

        }
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
    public void addParams2(PreparedStatement prst) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
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
            if (ret instanceof List){
                prst.setObject(i,null);
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
                   if(f.getName().equals("name")) {
                       String id = ret.toString();
                       prst.setString(2,id);

                   }

            }
            if(ret instanceof List){

                List<Integer> atrapados = (List<Integer>) ret;
                String json = new Gson().toJson(atrapados);
                prst.setObject(1,json);

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


        String query=command.toString();
        prst= con.prepareStatement(query);
        this.addParams2(prst);
        prst.execute();

    }
    public  void update() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {

        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("UPDATE ").append(this.getClass().getSimpleName()+" SET ");


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


        fields= this.getClass().getFields();

        for(Field f :fields){
            if(f.getName().toString().equals("id")){
                command.append(f.getName().toString()+"=?;");
            }
        }

        String query=command.toString();
        prst= con.prepareStatement(query);
        this.setName(prst);
        prst.execute();

    }

    public customers select() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("SELECT * FROM ").append(this.getClass().getSimpleName()+" WHERE ");
        StringBuffer tablas = new StringBuffer();


        fields= this.getClass().getFields();
        for(Field f :fields){
            if(f.getName().toString().equals("name")){
                command.append(f.getName().toString()+"=?;");
            }
        }

        String query=command.toString();
        prst= con.prepareStatement(query);
        this.setName(prst);
        ResultSet rs= prst.executeQuery();
        ResultSetMetaData rsmd= rs.getMetaData();
        rs.next();

       for (int i = 1;i!=0;i++){
           try{
               if (rsmd.getColumnTypeName(i).equals("INT")){
                   tablas.append(rs.getInt(i)+",");}
               if (rsmd.getColumnTypeName(i).equals("VARCHAR")){
                   tablas.append(rs.getString(i)+",");
               }
               if (i == rsmd.getColumnCount()){
                   rs.next();
                   i=-1;
               }

           }
           catch (Exception e){

           }
       }
       tablas.append("-");
        String [] parts = tablas.toString().split("-");
        String [] parts2 = parts[0].split(",");
        customers nuevocus = new customers(parts2[1],parts2[2]);
        return nuevocus;

    }
    public String select2() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("SELECT * FROM ").append(this.getClass().getSimpleName()+" WHERE ");
        StringBuffer tablas = new StringBuffer();

        System.out.println(this.getClass().getSimpleName());
        fields= this.getClass().getFields();
        for(Field f :fields){
            if(f.getName().toString().equals("name")){
                command.append(f.getName().toString()+"=? AND ");
            }
            if(f.getName().toString().equals("password")){
                command.append(f.getName().toString()+"=?;");
            }
        }
        System.out.println(command.toString());
        String query=command.toString();
        prst= con.prepareStatement(query);
        this.addParams(prst);
        ResultSet rs= prst.executeQuery();
        ResultSetMetaData rsmd= rs.getMetaData();
        rs.next();
        int j = rsmd.getColumnCount();

        for(int i=1;i<rsmd.getColumnCount();i++){
            try {

                if (rsmd.getColumnTypeName(i).equals("VARCHAR")) {
                    System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getString(i));
                    tablas.append(rs.getString(i)+",");
                }
                if(i==rsmd.getColumnCount()){
                    rs.next();
                    i=0;
                }
            }
            catch (Exception e){

            }
        }
        try {
            tablas.replace(tablas.length() - 1, tablas.length(), "-");
        }
        catch (Exception e){
            return null;
        }
        return tablas.toString();

    }
    public void updateEtakemon() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("UPDATE ").append(this.getClass().getSimpleName()+" SET ");

        fields= this.getClass().getFields();
        for(Field f :fields){
            if(f.getName().toString().equals("pokemon")){
                command.append(f.getName().toString()+"=? ");
            }
        }
        command.replace(command.length()-1,command.length()," WHERE ");
        for (Field f : fields){
            if(f.getName().toString().equals("name")){
                command.append(f.getName().toString()+"=?; ");
            }

        }
        String query=command.toString();
        prst= con.prepareStatement(query);
        this.setParams(prst);
        prst.execute();


    }
    public List<Criatura> selectEtakmons() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        List<Criatura> atrapados = new ArrayList<Criatura>();
        Connection con= this.getConnection();
        command= new StringBuffer();
        command.append("SELECT pokemon FROM ").append(this.getClass().getSimpleName()+" WHERE ");

        fields= this.getClass().getFields();
        for(Field f :fields){
            if(f.getName().toString().equals("name")){
                command.append(f.getName().toString()+"=?; ");
            }
        }
        System.out.println(command.toString());
        String query=command.toString();
        prst= con.prepareStatement(query);
        this.setName(prst);
        ResultSet rs= prst.executeQuery();
        ResultSetMetaData rsmd= rs.getMetaData();
        rs.next();
        int j = rsmd.getColumnCount();

        for(int i=1;i<rsmd.getColumnCount()+1;i++){
            try {

                if (rsmd.getColumnTypeName(i).equals("JSON")) {
                    Gson gson = new Gson();
                    atrapados = gson.fromJson(rs.getString(i),List.class);
            }
                if(i==rsmd.getColumnCount()){
                    rs.next();
                    i=0;
                }
            }
            catch (Exception e){

            }
        }

        return atrapados;

    }

}
