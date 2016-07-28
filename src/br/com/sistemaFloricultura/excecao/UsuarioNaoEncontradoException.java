package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class UsuarioNaoEncontradoException extends SistemaFloriculturaException{

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado");
    }
}
