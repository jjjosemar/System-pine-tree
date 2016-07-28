package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.ProdutoDAO;
import br.com.sistemaFloricultura.entidade.Mensagem;
import br.com.sistemaFloricultura.entidade.Produto;
import br.com.sistemaFloricultura.excecao.NenhumResultadoException;
import br.com.sistemaFloricultura.excecao.NomeDuplicadoException;
import java.util.List;

/**
 *
 * @author josemar
 */
public class ProdutoBO {
    
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void cadastrar(Produto produto) throws Exception{
        
        verificarProdutoParaCadastro(produto);
        produtoDAO.cadastrar(produto);
    }
    
    public List<Produto> listarProdutosVenda(int codigoVenda) throws Exception{
        return produtoDAO.buscarProdutosVenda(codigoVenda);
    }
    public void excluir(int codigo) throws Exception{
        
        produtoDAO.excluir(codigo);
    }
    
    public void editar(Produto produto) throws Exception{
        
        verificarProdutoParaEditar(produto);
        produtoDAO.editar(produto);
    }
    
    public void decrementarQtde(int qtde, int codigoProduto, int qtdeAtual) throws Exception{
        
        produtoDAO.decrementarQtde(qtde,codigoProduto,qtdeAtual);
    }
    
    public List<Produto> listar() throws Exception{
        return produtoDAO.listar();        
    }
    
    public List<Produto> procurarNomes(String nome) throws Exception{
        return produtoDAO.buscarNomes(nome);        
    }
    
    public Produto procurarNome(String nome) throws Exception{
        return produtoDAO.buscarDescricaoUnica(nome);
    }
    
    public Produto procurarCodigo(int codigo) throws Exception{
        return produtoDAO.buscarCodigo(codigo);
    }
    
    private void verificarProdutoParaCadastro(Produto produto) throws Exception {
        
        try {
            procurarNome(produto.getDescricao());
            
            throw new NomeDuplicadoException();
            
        } catch (NenhumResultadoException ex) {
        }
        
    }
    
    private void verificarProdutoParaEditar(Produto produto) throws Exception {

        try {

            if(produto.getCodigo() != produtoDAO.buscarDescricaoUnica(produto.getDescricao()).getCodigo()){
                throw new NomeDuplicadoException();
            }
            
        } catch (NenhumResultadoException e) {
        }
    }
}
