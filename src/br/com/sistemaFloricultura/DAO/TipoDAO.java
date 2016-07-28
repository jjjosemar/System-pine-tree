package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.Tipo;
import br.com.sistemaFloricultura.excecao.NenhumResultadoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josemar
 */
public class TipoDAO {
 
    private static final String SQL_SELECT = "SELECT * FROM TIPO ORDER BY NOME;";
    private static final String SQL_SELECT_NOME = "SELECT * FROM TIPO WHERE UPPER (NOME) = UPPER (?);";
    private static final String SQL_SELECT_NOMES = "SELECT * FROM TIPO WHERE UPPER (NOME) LIKE UPPER (?);";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM TIPO WHERE CODIGO = ?;";
    private static final String SQL_INSERT = "INSERT INTO TIPO( NOME) VALUES(?);";
    private static final String SQL_DELETE = "DELETE FROM TIPO WHERE CODIGO = ?";
    private static final String SQL_UPDATE = "UPDATE TIPO SET NOME = ? WHERE CODIGO = ?";
    
    public void cadastrar(Tipo tipo) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            comando = conexao.prepareStatement(SQL_INSERT);
            
            comando.setString(1, tipo.getNome());

            comando.execute();
            
            conexao.commit();
            
        } catch (Exception e) {
            
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando);
        }
    }
    
    public void editar(Tipo tipo) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setString(1, tipo.getNome());
            comando.setInt(2, tipo.getCodigo());
            
            comando.execute();
            
            conexao.commit();
            
        } catch (Exception e) {
            
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando);
        }
    }
    
    public void excluir(int codigo) throws SQLException, Exception{
     
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_DELETE);
            
            comando.setInt(1, codigo);
            
            comando.execute();
            
            conexao.commit();            
    
        }catch(Exception e){
            
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando);
        }    
    }
     
    public List<Tipo> listar() throws SQLException, Exception{
        
        List<Tipo> tipos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                tipos.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return tipos;
    }

    public Tipo buscarCodigo(int codigo) throws SQLException, Exception{
        
        Tipo tipo = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                tipo = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return tipo;
    }
    
    public Tipo buscarNome(String nome) throws SQLException, Exception{
        
        Tipo tipo = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_NOME);
            
            comando.setString(1, nome);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                tipo = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return tipo;
    }
    
    public List<Tipo> buscarNomes(String nome) throws SQLException, Exception{
        
        List<Tipo> tipo = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_NOMES);
            
            comando.setString(1, "%"+nome+"%");
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                tipo.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return tipo;
    }
     
    private Tipo extrairLinha(ResultSet resultado) throws SQLException{
        
        Tipo tipo = new Tipo();
        
        tipo.setCodigo(resultado.getInt(1));
        tipo.setNome(resultado.getString(2));
        
        return tipo;
    }
}
