package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.ProdutoTipoFornecedorDAO;
import br.com.sistemaFloricultura.entidade.ProdutoTipoFornecedorJOIN;
import java.util.List;

/**
 *
 * @author josemar
 */
public class ProdutoTipoFornecedorBO {
    ProdutoTipoFornecedorDAO produtotTipoFornecedorDAO = new ProdutoTipoFornecedorDAO();
    
    public List<ProdutoTipoFornecedorJOIN> listar() throws Exception{
        return produtotTipoFornecedorDAO.listar();
    }
    
    public List<ProdutoTipoFornecedorJOIN> buscarDescricaoProduto(String nomeProduto) throws Exception{
        return produtotTipoFornecedorDAO.buscarDescricaoProduto(nomeProduto);
    }
    
    public ProdutoTipoFornecedorJOIN buscarCodigoProduto(int codigo) throws Exception{
        return produtotTipoFornecedorDAO.buscarCodigo(codigo);
    }
    
    public List<ProdutoTipoFornecedorJOIN> buscarNomesTipo(String nomeTipo) throws Exception{
        return produtotTipoFornecedorDAO.buscarNomesTipos(nomeTipo);
    }
    
    public List<ProdutoTipoFornecedorJOIN> buscarDescricaoProdutosDisponiveis(String nomeProduto) throws Exception{
        return produtotTipoFornecedorDAO.buscarDescricaoProdutoProdutoDisponivel(nomeProduto);
    }
    
    public ProdutoTipoFornecedorJOIN buscarCodigoProdutosDisponivel(int codigo) throws Exception{
        return produtotTipoFornecedorDAO.buscarCodigoProdutoDisponivel(codigo);
    }
    
    public List<ProdutoTipoFornecedorJOIN> buscarTiposProdutosDisponiveis(String nomeTipo) throws Exception{
        return produtotTipoFornecedorDAO.buscarNomesTiposProdutoDisponiveis(nomeTipo);
    }
}
