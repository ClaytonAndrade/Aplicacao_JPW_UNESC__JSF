package br.com.projetofinaljpw.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*@author Andrade 18/11/2018*/
public class ConexaoFactory {
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String URL = "jdbc:mysql://localhost:8889/cosmeticos";
    //private static final String SENHA = "admin";
    //private static final String URL = "jdbc:mysql://localhost:3306/cosmeticos";

    public static Connection conetar() throws SQLException{
        //resolução de problema de conflito com tomcat para algumas maquinas
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        return conexao;
    }
    
    
    /*public static void main(String[] args) {
        
        try{
            Connection connection = ConexaoFactory.conetar();
            System.out.println("Conexâo Realizada");
        }catch(SQLException exception){
            exception.printStackTrace();
            System.out.println("Conexâo Não Realizada");
        }
    }*/
}
        