package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.VendaDAO;
import br.com.sistemaFloricultura.entidade.Venda;
import br.com.sistemaFloricultura.excecao.NenhumResultadoException;
import br.com.sistemaFloricultura.excecao.VendedorNaoEncontradoException;
import java.util.List;

/**
 *
 * @author josemar
 */
public class VendaBO {
    
    VendaDAO vendaDAO = new VendaDAO();
    
    public void cadastrar(Venda venda) throws Exception{
        verificarCodigoVendedor(venda.getCodigoVendedor());
        vendaDAO.cadastrar(venda);
    }
    
    public void atualizar(Venda venda) throws Exception{
        verificarCodigoVendedor(venda.getCodigoVendedor());
        vendaDAO.editar(venda);
    }
    
    public void excluir(int codigo) throws Exception{
        vendaDAO.excluir(codigo);
    }
    
    public Venda buscarCodigo(int codigo) throws Exception{
        return vendaDAO.buscarCodigo(codigo);
    }
    
    public List<Venda> listar() throws Exception{
        return vendaDAO.listar();
    }

    private void verificarCodigoVendedor(int codigoVendedor) throws Exception {
        try {
            new VendedorBO().buscarCodigo(codigoVendedor);
        } catch (NenhumResultadoException ex) 
        {
            throw new VendedorNaoEncontradoException();
        }
    }
}
