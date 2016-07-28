package br.com.sistemaFloricultura.DAO;

import br.com.sistemaFloricultura.entidade.ProdutoTipoFornecedorJOIN;
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
public class ProdutoTipoFornecedorDAO {
    private String SQL_SELECT = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR ORDER BY P.DESCRICAO;";
    
    private String SQL_SELECT_CODIGO = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR WHERE P.ID_PRODUTO = ?;";
    
    private String SQL_SELECT_NOMES = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR WHERE UPPER (P.DESCRICAO) LIKE UPPER(?) ORDER BY P.DESCRICAO;";
    
    private String SQL_SELECT_TIPOS = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR WHERE UPPER (T.NOME) LIKE UPPER(?) ORDER BY T.NOME;";
    
    private String SQL_SELECT_CODIGO_DISPONIVEL = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR WHERE P.ID_PRODUTO = ? AND P.QUANTIDADE > 0;";
    
    private String SQL_SELECT_NOMES_DISPONIVEIS = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR WHERE UPPER (P.DESCRICAO) LIKE UPPER(?) AND P.QUANTIDADE > 0 ORDER BY P.DESCRICAO;";
    
    private String SQL_SELECT_TIPOS_DISPONIVEIS = "SELECT P.ID_PRODUTO, P.DESCRICAO, P.VALOR_VENDA, P.VALOR_COMPRA, P.OBSERVACAO, F.NOME, T.NOME, P.QUANTIDADE\n" +
                                "FROM PRODUTO AS P LEFT JOIN TIPO AS T ON P.ID_TIPO_FK = T.CODIGO LEFT JOIN FORNECEDOR AS F\n" +
                                "ON P.ID_FORNECEDOR_FK = F.ID_FORNECEDOR WHERE UPPER (T.NOME) LIKE UPPER(?) AND P.QUANTIDADE > 0 ORDER BY T.NOME;";
    
    public List<ProdutoTipoFornecedorJOIN> listar() throws Exception{
        
        List<ProdutoTipoFornecedorJOIN> produtos = new ArrayList<>();
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
    
    public ProdutoTipoFornecedorJOIN buscarCodigo(int codigoProduto) throws Exception{
        
        ProdutoTipoFornecedorJOIN produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO);
            
            comando.setInt(1, codigoProduto);
            
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
    
    public List<ProdutoTipoFornecedorJOIN> buscarDescricaoProduto(String nomeProduto) throws Exception{
        
        List<ProdutoTipoFornecedorJOIN> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_NOMES);
            
            comando.setString(1, "%"+nomeProduto+"%");
            
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
    
    public List<ProdutoTipoFornecedorJOIN> buscarNomesTipos(String nomeTipo) throws Exception{
        
        List<ProdutoTipoFornecedorJOIN> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_TIPOS);
            
            comando.setString(1, "%"+nomeTipo+"%");
            
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
    
    public ProdutoTipoFornecedorJOIN buscarCodigoProdutoDisponivel(int codigoProduto) throws Exception{
        
        ProdutoTipoFornecedorJOIN produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_CODIGO_DISPONIVEL);
            
            comando.setInt(1, codigoProduto);
            
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
    
    public List<ProdutoTipoFornecedorJOIN> buscarDescricaoProdutoProdutoDisponivel(String nomeProduto) throws Exception{
        
        List<ProdutoTipoFornecedorJOIN> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_NOMES_DISPONIVEIS);
            
            comando.setString(1, "%"+nomeProduto+"%");
            
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
    
    public List<ProdutoTipoFornecedorJOIN> buscarNomesTiposProdutoDisponiveis(String nomeTipo) throws Exception{
        
        List<ProdutoTipoFornecedorJOIN> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        try {
            
            conexao = Conexao.getConexao();
            
            comando = conexao.prepareStatement(SQL_SELECT_TIPOS_DISPONIVEIS);
            
            comando.setString(1, "%"+nomeTipo+"%");
            
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
    
    private ProdutoTipoFornecedorJOIN extrairLinha(ResultSet resultado) throws SQLException{
        
        ProdutoTipoFornecedorJOIN produto = new ProdutoTipoFornecedorJOIN();
        
        produto.setCodigo(resultado.getInt(1));
        produto.setDescricao(resultado.getString(2));
        produto.setValorVenda(resultado.getFloat(3));
        produto.setValorCompra(resultado.getFloat(4));
        produto.setObservacao(resultado.getString(5));
        produto.setNomeFornecedor(resultado.getString(6));
        produto.setNomeTipo(resultado.getString(7));
        produto.setQtde(resultado.getInt(8));
        
        return produto;
    }
}
