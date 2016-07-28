package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.Mensagem;
import br.com.sistemaFloricultura.entidade.Produto;
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
public class ProdutoDAO {
    
    private static final String SQL_SELECT = "SELECT * FROM PRODUTO ORDER BY DESCRICAO;";
        private static final String SQL_SELECT_PRODUTOS_VENDA = "SELECT P.* FROM VENDA AS V JOIN ITEM_VENDA AS I ON V.CODIGO = I.ID_VENDA_FK "
   + "JOIN PRODUTO AS P ON I.ID_PRODUTO_FK = P.ID_PRODUTO WHERE V.CODIGO = ?;";
    private static final String SQL_SELECT_DESCRICAO = "SELECT * FROM PRODUTO WHERE UPPER (DESCRICAO) LIKE UPPER(?);";
    private static final String SQL_SELECT_UNICO_DESCRICAO = "SELECT * FROM PRODUTO WHERE UPPER (DESCRICAO) = UPPER (?);";
    private static final String SQL_SELECT_CODIGO = "SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?;";
    private static final String SQL_INSERT = "INSERT INTO PRODUTO( DESCRICAO, VALOR_VENDA, ID_FORNECEDOR_FK, OBSERVACAO,"
                                            + " VALOR_COMPRA, ID_TIPO_FK, QUANTIDADE) VALUES (?,?,?,?,?,?,?);";
    
    private static final String SQL_UPDATE_QTDE = "UPDATE PRODUTO SET QUANTIDADE = (? - ?) WHERE ID_PRODUTO = ?;";
    private static final String SQL_DELETE = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ?";
    private static final String SQL_UPDATE = "UPDATE PRODUTO SET DESCRICAO = ?, VALOR_VENDA = ?, ID_FORNECEDOR_FK = ?, OBSERVACAO = ?, VALOR_COMPRA = ?, ID_TIPO_FK = ?, QUANTIDADE = ? WHERE ID_PRODUTO = ?";
    
    public void cadastrar(Produto produto) throws Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            int idFornecedor = produto.getIdFornecedor();
            int idTipo = produto.getIdTipo();
            
            conexao = Conexao.getConexao();

            comando = conexao.prepareStatement(SQL_INSERT);

            comando.setString(1, produto.getDescricao());
            comando.setFloat(2, produto.getValorVenda());
            
            if(idFornecedor != -1)
                comando.setInt(3, idFornecedor);
            else
                comando.setString(3, null);
            
            comando.setString(4, produto.getObservacao());
            comando.setFloat(5, produto.getValorCompra());
            
            if(idTipo != -1)
                comando.setInt(6,idTipo);
            else
                comando.setString(6, null);
            
            comando.setInt(7, produto.getQtde());

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
    
    public void editar(Produto produto) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            int idFornecedor = produto.getIdFornecedor();
            int idTipo = produto.getIdTipo();
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE);
            
            comando.setString(1, produto.getDescricao());
            comando.setFloat(2, produto.getValorVenda());
            
            if(idFornecedor != -1)
                comando.setInt(3, idFornecedor);
            else
                comando.setString(3, null);
            
            comando.setString(4, produto.getObservacao());
            comando.setFloat(5, produto.getValorCompra());
            
            if(idTipo != -1)
                comando.setInt(6,idTipo);
            else
                comando.setString(6, null);
            comando.setInt(7, produto.getQtde());
            comando.setInt(8, produto.getCodigo());
            
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
    
    public void decrementarQtde(int qtde, int codigoProduto, int qtdeAtual) throws SQLException, Exception{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_UPDATE_QTDE);

            comando.setInt(1, qtdeAtual);
            comando.setInt(2, qtde);
            comando.setInt(3, codigoProduto);

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
     
    public List<Produto> listar() throws SQLException, Exception{
        
        List<Produto> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                produtos.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return produtos;
    }
    
    public List<Produto> buscarNomes(String nome) throws SQLException, Exception{
        
        List<Produto> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_DESCRICAO);
            
            comando.setString(1, "%"+nome+"%");
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                produtos.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return produtos;
    }
    
    public Produto buscarDescricaoUnica(String nome) throws SQLException, Exception{
        
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
    
            comando = conexao.prepareStatement(SQL_SELECT_UNICO_DESCRICAO);
            
            comando.setString(1, nome);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                produto = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return produto;
    }
    
    public Produto buscarCodigo(int codigo) throws SQLException, Exception{
        
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            if(resultado.next())   
                produto = this.extrairLinha(resultado);
            else throw new NenhumResultadoException();
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return produto;
    }
    
    public List<Produto> buscarProdutosVenda(int codigo) throws SQLException, Exception{
        
        List<Produto> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_PRODUTOS_VENDA);
            
            comando.setInt(1, codigo);
            
            resultado = comando.executeQuery();
            
            while (resultado.next()) {                
                produtos.add(this.extrairLinha(resultado));
            }
            
            conexao.commit();
            
        } catch (Exception e) {
        
            if(conexao != null)
                conexao.rollback();
            throw e;
            
        }finally{
            Conexao.fecharConexoes(conexao, comando, resultado);
        }
        
        return produtos;
    }
     
    private Produto extrairLinha(ResultSet resultado) throws SQLException{
        
        Produto produto = new Produto();
        
        produto.setCodigo(resultado.getInt(1));
        produto.setDescricao(resultado.getString(2));
        produto.setValorVenda(resultado.getFloat(3));
        produto.setIdFornecedor(resultado.getInt(4));
        produto.setObservacao(resultado.getString(5));
        produto.setValorCompra(resultado.getFloat(6));
        produto.setIdTipo(resultado.getInt(7));
        produto.setQtde(resultado.getInt(8));
        
        return produto;
    }
}
