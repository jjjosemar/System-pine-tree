package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.FornecedorDAO;
import br.com.sistemaFloricultura.DAO.VendedorDAO;
import br.com.sistemaFloricultura.entidade.Fornecedor;
import br.com.sistemaFloricultura.entidade.Vendedor;
import br.com.sistemaFloricultura.excecao.CnpjDuplicadoException;
import br.com.sistemaFloricultura.excecao.CpfDuplicadoException;
import br.com.sistemaFloricultura.excecao.NenhumResultadoException;
import br.com.sistemaFloricultura.excecao.NomeDuplicadoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author josemar
 */
public class VendedorBO {

    VendedorDAO vendedorDAO = new  VendedorDAO();
    
    public void cadastrar(Vendedor vendedor) throws Exception{
    
        verificarVendedorParaCadastro(vendedor);
        vendedorDAO.cadastrar(vendedor);
    }
    
    public void excluir(int codigo) throws Exception{
        
        // implementar verificação de referencia na tabela vendas
        vendedorDAO.excluir(codigo);
    }
    
    public List<Vendedor> listar() throws Exception{
        return vendedorDAO.listar();
    }
    
    public void editar(Vendedor vendedor) throws Exception{
        
        verificarVendedorParaEditar(vendedor);
        vendedorDAO.editar(vendedor);
    }

    private void verificarVendedorParaCadastro(Vendedor vendedor) throws Exception {
        try {
            
            buscarCpf(vendedor.getCpf());
            throw new CpfDuplicadoException();
            
        } catch (NenhumResultadoException ex) {
        
        }
    }

    public Vendedor buscarCodigo(int codigo) throws Exception {
        return vendedorDAO.buscarCodigo(codigo);
    }

    private void verificarVendedorParaEditar(Vendedor vendedor) throws Exception {

        try {
            
            if(vendedor.getCodigo() != vendedorDAO.buscarCPF(vendedor.getCpf()).getCodigo())
                throw new CpfDuplicadoException();
        
        } catch (NenhumResultadoException e) {
        }
    }

    public List<Vendedor> buscarNome(String nome) throws Exception {
        return vendedorDAO.buscarNome(nome);
    }

    public Vendedor buscarCpf(String cpf) throws Exception {
        return vendedorDAO.buscarCPF(cpf);
    }
}
