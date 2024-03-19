package dal;

import java.sql.*;

public class mod_conexao {
    public static Connection conector(){
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url    = "jdbc:mysql://localhost:3307/bancojavalogin";
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e){
            return null;
        }
    }
}
