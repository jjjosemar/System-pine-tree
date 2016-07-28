package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.ItemVenda;
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
public class ItemVendaDAO {
    
    private static final String SQL_SELECT = "SELECT * FROM ITEM_VENDA;";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM ITEM_VENDA WHERE CODIGO = ?;";
    private static final String SQL_INSERT = "INSERT INTO ITEM_VENDA( ID_VENDA_FK, QTDE, ID_PRODUTO_FK) VALUES(?, ?, ?);";
    private static final String SQL_DELETE = "DELETE FROM ITEM_VENDA WHERE CODIGO = ?";
    private static final String SQL_UPDATE = "UPDATE ITEM_VENDA SET ID_VENDA_FK = ?, QTDE = ?, ID_PRODUTO_FK = ? WHERE CODIGO = ?";
    
    public void cadastrar(ItemVenda item) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            comando = conexao.prepareStatement(SQL_INSERT);
            
            comando.setInt(1, item.getIdVenda());
            comando.setInt(2, item.getQtde());
            comando.setInt(3, item.getIdProduto());

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
    
    public void editar(ItemVenda item) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setInt(1, item.getIdVenda());
            comando.setInt(2, item.getQtde());
            comando.setInt(3, item.getIdProduto());
            comando.setInt(4, item.getCodigo());
            
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
     
    public List<ItemVenda> listar() throws SQLException, Exception{
        
        List<ItemVenda> itemVendas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                itemVendas.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return itemVendas;
    }

    public ItemVenda buscarCodigo(int codigo) throws SQLException, Exception{
        
        ItemVenda tipo = null;
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
    
    private ItemVenda extrairLinha(ResultSet resultado) throws SQLException{
        
        ItemVenda itemVenda = new ItemVenda();
        
        itemVenda.setCodigo(resultado.getInt(1));
        itemVenda.setIdVenda(resultado.getInt(2));
        itemVenda.setQtde(resultado.getInt(3));
        itemVenda.setIdProduto(resultado.getInt(4));
        
        return itemVenda;
    }
}
