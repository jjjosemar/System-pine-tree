package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class UsuarioSemAcessException extends SistemaFloriculturaException{
    
    public UsuarioSemAcessException() {
        super("Acesso negado!!! Esse usuário não pode ter acesso a essa opção!");
    }
    
}
