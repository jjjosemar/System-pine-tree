package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class NomeDuplicadoException extends SistemaFloriculturaException{

    public NomeDuplicadoException() {
        super("Erro!!! O nome inserido já existe em outro cadastro");
    }
    
}
