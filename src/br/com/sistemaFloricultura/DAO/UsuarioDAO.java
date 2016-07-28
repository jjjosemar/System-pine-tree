package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.Usuario;
import br.com.sistemaFloricultura.excecao.UsuarioNaoEncontradoException;
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
public class UsuarioDAO {
    
    private static final String SQL_SELECT = "SELECT * FROM USUARIO;";
    private static final String SQL_SELECT_LOGIN = "SELECT * FROM USUARIO WHERE LOGIN = ?;";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM USUARIO WHERE CODIGO = ?;";
    private static final String SQL_INSERT = "INSERT INTO USUARIO(SENHA, LOGIN, NOME, CARGO) VALUES(?,?,?,?);";
    private static final String SQL_DELETE = "DELETE FROM USUARIO WHERE LOGIN = ?";
    private static final String SQL_UPDATE = "UPDATE USUARIO SET LOGIN = ?, SENHA = ?, NOME = ? , CARGO = ? WHERE CODIGO = ?";
    
    public void cadastrar(Usuario usuario) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_INSERT);
            
            comando.setString(1, usuario.getSenha());
            comando.setString(2, usuario.getLogin());
            comando.setString(3, usuario.getNome());
            comando.setString(4, ""+usuario.getCargo());

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
    
     public void atualizar(Usuario usuario) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setString(1, usuario.getLogin());
            comando.setString(2, usuario.getSenha());
            comando.setString(3, usuario.getNome());
            comando.setString(4, ""+usuario.getCargo());
            comando.setInt(5, usuario.getCodigo());
            
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
    
    public void deletar(String login) throws SQLException, Exception{
     
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_DELETE);
            
            comando.setString(1, login);
            
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
     
    public List<Usuario> listar() throws SQLException, Exception{
        
        List<Usuario> usuarios = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                usuarios.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return usuarios;
    }
    
    public Usuario buscarUsuarioPorLogin(String login) throws SQLException, Exception{
        
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_LOGIN);
            
            comando.setString(1, login);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                usuario = this.extrairLinha(resultado);
            else throw new UsuarioNaoEncontradoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return usuario;
    }
    
    public Usuario buscarUsuarioPorCodigo(int codigo) throws SQLException, Exception{
        
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                usuario = this.extrairLinha(resultado);
            else throw new UsuarioNaoEncontradoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return usuario;
    }
    
    private Usuario extrairLinha(ResultSet resultado) throws SQLException{
        
        Usuario usuario = new Usuario();
        
        usuario.setSenha(resultado.getString(1));
        usuario.setNome(resultado.getString(2));
        usuario.setCodigo(resultado.getInt(3));
        usuario.setLogin(resultado.getString(4));
        usuario.setCargo(resultado.getString(5).charAt(0));
        
        return usuario;
    }
}
