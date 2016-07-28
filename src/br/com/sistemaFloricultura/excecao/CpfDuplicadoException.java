package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class CpfDuplicadoException extends SistemaFloriculturaException{

    public CpfDuplicadoException() {
        super("Erro!!! O cpf inserido já existe em outro cadastro");
    }
}
