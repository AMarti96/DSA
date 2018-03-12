import java.sql.SQLException;

/**
 * Created by Adri on 10/10/2016.
 */
public class UserDao extends Dao {

    public int id;
    public  String name;
    public String address;


    public UserDao (String masmaso, int num,String adress) throws SQLException {
        super();
        this.name = masmaso;
        this.id = num;
        this.address = adress;

    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public String getAddress(){
        return address;
    }

}
