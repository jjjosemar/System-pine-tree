package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.Venda;
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
public class VendaDAO {
    
    private static final String SQL_SELECT = "SELECT * FROM VENDA;";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM VENDA WHERE CODIGO = ?;";
    private static final String SQL_INSERT = "INSERT INTO VENDA( DESCONTO, DATA_VENDA, ID_VENDEDOR_FK, VALOR_VENDA) VALUES(?,?,?,?);";
    private static final String SQL_DELETE = "DELETE FROM VENDA WHERE CODIGO = ?";
    private static final String SQL_UPDATE = "UPDATE VENDA SET DESCONTO = ?, DATA_VENDA = ?, ID_VENDEDOR_FK = ? WHERE CODIGO = ?";
    private static final String SQL_BUSCAR_VENDEDORES = "SELECT V.NOME FROM VENDA JOIN VENDEDOR AS V ON VENDA.ID_VENDEDOR_FK = V.CODIGO";
    public void cadastrar(Venda venda) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            comando = conexao.prepareStatement(SQL_INSERT);
            
            comando.setFloat(1, venda.getDesconto());
            comando.setDate(2, venda.getDataVenda());
            comando.setInt(3, venda.getCodigoVendedor());
            comando.setFloat(4, venda.getValorVenda());
            
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
    
    public void editar(Venda venda) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setFloat(1, venda.getDesconto());
            comando.setDate(2, venda.getDataVenda());
            comando.setInt(3, venda.getCodigoVendedor());
            comando.setInt(4, venda.getCodigo());
            
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
     
    public List<Venda> listar() throws SQLException, Exception{
        
        List<Venda> venda = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                venda.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return venda;
    }
 
    public Venda buscarCodigo(int codigo) throws SQLException, Exception{
        
        Venda venda = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                venda = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return venda;
    }
    
    private Venda extrairLinha(ResultSet resultado) throws SQLException{
        
        Venda venda = new Venda();
        
        venda.setCodigo(resultado.getInt(1));
        venda.setDesconto(resultado.getFloat(2));
        venda.setDataVenda(resultado.getDate(3));
        venda.setCodigoVendedor(resultado.getInt(4));
        venda.setValorVenda(resultado.getFloat(5));
        
        return venda;
    }
}

