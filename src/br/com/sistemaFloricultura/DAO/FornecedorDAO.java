package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.Fornecedor;
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
public class FornecedorDAO {
    

    private static final String SQL_SELECT = "SELECT * FROM FORNECEDOR;";
    private static final String SQL_SELECT_NOME = "SELECT * FROM FORNECEDOR WHERE UPPER(NOME) LIKE UPPER (?);";
    private static final String SQL_SELECT_UNICO_NOME = "SELECT * FROM FORNECEDOR WHERE UPPER(NOME) = UPPER(?);";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM FORNECEDOR WHERE ID_FORNECEDOR = ?;";
    private static final String SQL_SELECT_CNPJ = "SELECT * FROM FORNECEDOR WHERE CNPJ = ?;";
    private static final String SQL_INSERT = "INSERT INTO FORNECEDOR( NOME, TELEFONE, "
                            + "ESTADO, CNPJ, RUA, BAIRRO, CIDADE, NUMERO) VALUES(?,?,?,?,?,?,?,?);";
    private static final String SQL_DELETE = "DELETE FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";
    private static final String SQL_UPDATE = "UPDATE FORNECEDOR SET NOME = ?, TELEFONE = ?, ESTADO = ?, CNPJ = ?, RUA = ?, BAIRRO = ?, CIDADE = ?, NUMERO = ?  WHERE ID_FORNECEDOR = ?";
    
    public void cadastrar(Fornecedor fornecedor) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            comando = conexao.prepareStatement(SQL_INSERT);
            
            comando.setString(1, fornecedor.getNome());
            comando.setString(2, fornecedor.getTelefone());
            comando.setString(3, fornecedor.getEstado());
            comando.setString(4, fornecedor.getCnpj());
            comando.setString(5, fornecedor.getRua());
            comando.setString(6, fornecedor.getBairro());
            comando.setString(7, fornecedor.getCidade());
            comando.setString(8, fornecedor.getNumero());

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
    
    public void editar(Fornecedor fornecedor) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setString(1, fornecedor.getNome());
            comando.setString(2, fornecedor.getTelefone());
            comando.setString(3, fornecedor.getEstado());
            comando.setString(4, fornecedor.getCnpj());
            comando.setString(5, fornecedor.getRua());
            comando.setString(6, fornecedor.getBairro());
            comando.setString(7, fornecedor.getCidade());
            comando.setString(8, fornecedor.getNumero());
            comando.setInt(9, fornecedor.getCodigo());
            
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
     
    public List<Fornecedor> listar() throws SQLException, Exception{
        
        List<Fornecedor> fornecedores = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                fornecedores.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return fornecedores;
    }
    
    public List<Fornecedor> buscarNome(String nome) throws SQLException, Exception{
        
        List<Fornecedor> fornecedores = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_NOME);
            
            comando.setString(1, "%"+nome+"%");
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                fornecedores.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return fornecedores;
    }
    
    public Fornecedor buscarPorCnpj(String cnpj) throws SQLException, Exception{
        
        Fornecedor fornecedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CNPJ);
            
            comando.setString(1, cnpj);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                fornecedor = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return fornecedor;
    }
    
    public Fornecedor buscarNomeUnico(String nome) throws SQLException, Exception{
        
        Fornecedor fornecedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
    
            comando = conexao.prepareStatement(SQL_SELECT_UNICO_NOME);
            
            comando.setString(1, nome);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                fornecedor = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return fornecedor;
    }
    
    public Fornecedor buscarCodigo(int codigo) throws SQLException, Exception{
        
        Fornecedor fornecedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                fornecedor = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return fornecedor;
    }
    
    private Fornecedor extrairLinha(ResultSet resultado) throws SQLException{
        
        Fornecedor fornecedor = new Fornecedor();
        
        fornecedor.setCodigo(resultado.getInt(1));
        fornecedor.setNome(resultado.getString(2));
        fornecedor.setTelefone(resultado.getString(3));
        fornecedor.setEstado(resultado.getString(4));
        fornecedor.setCnpj(resultado.getString(5));
        fornecedor.setRua(resultado.getString(6));
        fornecedor.setBairro(resultado.getString(7));
        fornecedor.setCidade(resultado.getString(8));
        fornecedor.setNumero(resultado.getString(9));
        
        return fornecedor;
    }
}
