package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.FornecedorDAO;
import br.com.sistemaFloricultura.entidade.Fornecedor;
import br.com.sistemaFloricultura.excecao.CnpjDuplicadoException;
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
public class FornecedorBO {
    FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    public void cadastrar(Fornecedor fornecedor) throws Exception{
    
        verificarFornecedorParaCadastro(fornecedor);
        fornecedorDAO.cadastrar(fornecedor);
    }
    
    public void excluir(int codigo) throws Exception{
        fornecedorDAO.excluir(codigo);
    }
    
    public List<Fornecedor> listar() throws Exception{
        return fornecedorDAO.listar();
    }
    
    public void editar(Fornecedor fornecedor) throws Exception{
        
        verificarFornecedorParaEditar(fornecedor);
        fornecedorDAO.editar(fornecedor);
    }

    private void verificarFornecedorParaCadastro(Fornecedor fornecedor) throws Exception {
        try {
            buscarNomeUnico(fornecedor.getNome());
            
            throw new NomeDuplicadoException();
            
        } catch (NenhumResultadoException ex) {
        }
        
        try {
            buscarCNPJ(fornecedor.getCnpj());
            
            throw new CnpjDuplicadoException();
            
        } catch (NenhumResultadoException ex) {
        }
    }

    public Fornecedor buscarNomeUnico(String nome) throws Exception {
        return fornecedorDAO.buscarNomeUnico(nome);
    }

    public Fornecedor buscarCNPJ(String cnpj) throws Exception {
        return fornecedorDAO.buscarPorCnpj(cnpj);
    }

    private void verificarFornecedorParaEditar(Fornecedor fornecedor) throws Exception {

        try {
            
            if(fornecedor.getCodigo() != fornecedorDAO.buscarNomeUnico(fornecedor.getNome()).getCodigo())
                throw new NomeDuplicadoException();
        
            if(fornecedor.getCodigo() != fornecedorDAO.buscarPorCnpj(fornecedor.getCnpj()).getCodigo())
                throw new CnpjDuplicadoException();
        } catch (NenhumResultadoException e) {
        }
    }

    public List<Fornecedor> buscarNome(String nome) throws Exception {
        return fornecedorDAO.buscarNome(nome);
    }
    
    public Fornecedor buscarCodigo(int codigo) throws Exception{
        return fornecedorDAO.buscarCodigo(codigo);
    }
}
