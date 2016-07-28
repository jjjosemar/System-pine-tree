package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.TipoDAO;
import br.com.sistemaFloricultura.entidade.Tipo;
import br.com.sistemaFloricultura.excecao.NenhumResultadoException;
import br.com.sistemaFloricultura.excecao.NomeDuplicadoException;
import java.util.List;

/**
 *
 * @author josemar
 */
public class TipoBO {
    
    TipoDAO tipoDAO = new TipoDAO();
    
    public void cadastrar(Tipo tipo) throws Exception{
       
        verificarEstoqueParaCadastro(tipo);
        tipoDAO.cadastrar(tipo);
    }
    
    public void atualizar(Tipo tipo) throws Exception{
        
        verificarEstoqueParaAtualizar(tipo);
        tipoDAO.editar(tipo);
    }
    
    public List<Tipo> buscarNomes(String nome) throws Exception{
        return tipoDAO.buscarNomes(nome);
    }

    private void verificarEstoqueParaCadastro(Tipo tipo) throws Exception {
        
        try {
            
            buscarNome(tipo.getNome());
            throw new NomeDuplicadoException();
            
        } catch (NenhumResultadoException ex) {
        }
    }
    
    public void excluir(int codigo) throws Exception{
        
        tipoDAO.excluir(codigo);
    }

    public Tipo buscarNome(String nome) throws Exception {
        return tipoDAO.buscarNome(nome);
    }
    
    public List<Tipo> listar() throws Exception{
        return tipoDAO.listar();
    }
    
    public Tipo buscarCodigo(int codigo) throws Exception{
        return tipoDAO.buscarCodigo(codigo);
    }

    private void verificarEstoqueParaAtualizar(Tipo tipo) throws Exception {
       
        try {
            
            if(tipo.getCodigo() != buscarNome(tipo.getNome()).getCodigo())
                throw new NomeDuplicadoException();
            
        } catch (NenhumResultadoException ex) {
        }
    }
}
