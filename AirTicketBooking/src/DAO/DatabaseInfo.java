package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInfo {

    //public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String driverName = "com.mysql.cj.jdbc.Driver";

//localhost:<PORT>  databaseName=<Tên của database vừa tạo>
    //public static String dbURL = "jdbc:sqlserver://localhost:1433;user=sa;password=duckg0946945409;databaseName=AIRTICKETBOOKING;encrypt=false";
    public static  String dbURL = "jdbc:mysql://localhost:3307/airticketbooking";
//Username và Password chừa trống, trong phần mềm sẽ nhập sau
    public static String dbUser = "root";
    public static String dbPass = "";
    
    // public void getConnect() throws ClassNotFoundException, SQLException {
    //     Class.forName(DatabaseInfo.driverName);
    //     Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL);
    //     System.out.println("CONNECTTED!");
    // }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //String dbURL = "jdbc:sqlserver://localhost:1433;user=sa;password=duckg0946945409;databaseName=AIRTICKETBOOKING;encrypt=false";
        String dbURL = "jdbc:mysql://localhost:3307/airticketbooking";
        try{
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection(dbURL,dbUser,dbPass);
            System.out.println("Connected to SQL Server" + connection.getCatalog());
        } catch (SQLException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
