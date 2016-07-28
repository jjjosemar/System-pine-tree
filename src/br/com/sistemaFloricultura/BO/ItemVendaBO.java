package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.ItemVendaDAO;
import br.com.sistemaFloricultura.entidade.ItemVenda;
import java.util.List;

/**
 *
 * @author josemar
 */
public class ItemVendaBO {
    
    ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
    
    public void cadastrar(ItemVenda itemVenda) throws Exception{
        itemVendaDAO.cadastrar(itemVenda);
    }
    
    public void editar(ItemVenda itemVenda) throws Exception{
        itemVendaDAO.editar(itemVenda);
    }
    
    public void excluir(int codigo) throws Exception{
        itemVendaDAO.excluir(codigo);
    }
    
    public List<ItemVenda> listar() throws Exception{
        return itemVendaDAO.listar();
    }
}
