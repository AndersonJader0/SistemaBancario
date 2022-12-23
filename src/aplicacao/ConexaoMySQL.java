package aplicacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/cliente2";
    private static final String USER = "root";
    private static final String PASS = "????";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException("Erro na conexão com o Banco de Dados(Driver não encontrado): " + e);
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro na conexão com o Banco de Dados: " + e);
        }
    }
 
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try {
            if(stmt != null) {
                stmt.close();
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao fechar a conexão com o Banco de Dados: " + e);
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao fechar a conexão com o Banco de Dados: " + e);
        }
    }

    
}