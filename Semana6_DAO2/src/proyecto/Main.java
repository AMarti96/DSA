package proyecto;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        users u = new users("toni", 1,"Calle 23");
        u.insert();
        //u.update();
        //u.delete();
        //u.select();

    }
}
