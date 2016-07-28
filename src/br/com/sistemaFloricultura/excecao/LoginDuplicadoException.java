package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class LoginDuplicadoException extends SistemaFloriculturaException{
    
    public LoginDuplicadoException() {
        super("Erro!!! O login inserido já existe em outro cadastro");
    }
    
}
