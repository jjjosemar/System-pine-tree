package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.Vendedor;
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
public class VendedorDAO {

    private static final String SQL_SELECT = "SELECT * FROM VENDEDOR;";
    private static final String SQL_SELECT_NOME = "SELECT * FROM VENDEDOR WHERE UPPER(NOME) LIKE UPPER (?);";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM VENDEDOR WHERE CODIGO = ?;";
    private static final String SQL_SELECT_CPF = "SELECT * FROM VENDEDOR WHERE CPF = ?;";
    private static final String SQL_INSERT = "INSERT INTO VENDEDOR( NOME, CPF, TELEFONE, SEXO, "
                            + "RUA, NUMERO, BAIRRO, CIDADE, ESTADO) VALUES(?,?,?,?,?,?,?,?,?);";
    private static final String SQL_DELETE = "DELETE FROM VENDEDOR WHERE CODIGO = ?";
    private static final String SQL_UPDATE = "UPDATE VENDEDOR SET NOME = ?, CPF = ?, TELEFONE = ?, SEXO = ?, RUA = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?,  ESTADO = ? WHERE CODIGO = ?";
    
    public void cadastrar(Vendedor vendedor) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            comando = conexao.prepareStatement(SQL_INSERT);
            
            comando.setString(1, vendedor.getNome());
            comando.setString(2, vendedor.getCpf());
            comando.setString(3, vendedor.getTelefone());
            comando.setString(4, String.valueOf(vendedor.getSexo()));
            comando.setString(5, vendedor.getRua());
            comando.setString(6, vendedor.getNumero());
            comando.setString(7, vendedor.getBairro());
            comando.setString(8, vendedor.getCidade());
            comando.setString(9, vendedor.getEstado());
            
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
    
    public void editar(Vendedor vendedor) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setString(1, vendedor.getNome());
            comando.setString(2, vendedor.getCpf());
            comando.setString(3, vendedor.getTelefone());
            comando.setString(4, String.valueOf(vendedor.getSexo()));
            comando.setString(5, vendedor.getRua());
            comando.setString(6, vendedor.getNumero());
            comando.setString(7, vendedor.getBairro());
            comando.setString(8, vendedor.getCidade());
            comando.setString(9, vendedor.getEstado());
            comando.setInt(10, vendedor.getCodigo());
            
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
     
    public List<Vendedor> listar() throws SQLException, Exception{
        
        List<Vendedor> vendedores = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                vendedores.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return vendedores;
    }
    
    public List<Vendedor> buscarNome(String nome) throws SQLException, Exception{
        
        List<Vendedor> vendedores = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_NOME);
            
            comando.setString(1, "%"+nome+"%");
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                vendedores.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return vendedores;
    }
 
    public Vendedor buscarCodigo(int codigo) throws SQLException, Exception{
        
        Vendedor vendedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                vendedor = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return vendedor;
    }
    
    public Vendedor buscarCPF(String cpf) throws SQLException, Exception{
        
        Vendedor vendedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CPF);
            
            comando.setString(1, cpf);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                vendedor = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return vendedor;
    }

    private Vendedor extrairLinha(ResultSet resultado) throws SQLException{
        
        Vendedor vendedor = new Vendedor();
        
        vendedor.setNome(resultado.getString(1));
        vendedor.setCpf(resultado.getString(2));
        vendedor.setTelefone(resultado.getString(3));
        vendedor.setSexo(resultado.getString(4).charAt(0));
        vendedor.setCodigo(resultado.getInt(5));
        vendedor.setRua(resultado.getString(6));
        vendedor.setNumero(resultado.getString(7));
        vendedor.setBairro(resultado.getString(8));
        vendedor.setCidade(resultado.getString(9));
        vendedor.setEstado(resultado.getString(10));
        
        return vendedor;
    }
}
