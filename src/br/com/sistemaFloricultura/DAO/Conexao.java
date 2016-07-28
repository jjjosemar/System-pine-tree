package br.com.sistemaFloricultura.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author josemar
 */
public class Conexao {
    private static final String USUARIO = "root"; 
    private static final String SENHA = "123";
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    private static final String URL = "jdbc:hsqldb:file:BD/BD_FLORICULTURA;shutdown=true";
    
    public static java.sql.Connection getConexao() throws Exception{
        java.sql.Connection conexao = null;
        
        try {
            Class.forName(DRIVER).newInstance();
            
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (Exception e) {
            throw e;
        }
        
        return conexao;     
    }
    
    public static void fecharConexoes(java.sql.Connection conexao,Statement comando,ResultSet resultado) throws SQLException{
        if(resultado != null && !resultado.isClosed())
            resultado.close();
        
        fecharConexoes(conexao, comando);
    }
    
    public static void fecharConexoes(java.sql.Connection conexao,Statement comando) throws SQLException{
        
        if(conexao!= null && !conexao.isClosed())
            conexao.close();
        
        if(comando!= null && !comando.isClosed())
            comando.close();
    }
}
