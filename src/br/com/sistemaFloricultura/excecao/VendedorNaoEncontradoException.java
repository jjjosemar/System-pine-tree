package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class VendedorNaoEncontradoException extends SistemaFloriculturaException{
    
    public VendedorNaoEncontradoException() {
        super("Erro!!! Codigo do vendedor não existe no cadastro");
    }  
}
