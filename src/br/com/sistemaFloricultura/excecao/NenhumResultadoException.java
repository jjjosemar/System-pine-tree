package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class NenhumResultadoException extends SistemaFloriculturaException{

    public NenhumResultadoException() {
        super("Nenhum resultado foi encontrado!");
    }   
}
