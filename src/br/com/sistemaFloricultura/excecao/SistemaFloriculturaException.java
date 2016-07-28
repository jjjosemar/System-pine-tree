package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class SistemaFloriculturaException extends RuntimeException{

    public SistemaFloriculturaException(String message) {
        super(message);
    }
    
    public static String messageErro() {
        return "Erro! não foi possível realizar a operação!";
    }
}
