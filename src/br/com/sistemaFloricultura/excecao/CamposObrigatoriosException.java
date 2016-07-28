package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class CamposObrigatoriosException extends SistemaFloriculturaException{

    public CamposObrigatoriosException() {
        super("Erro! Todos os campos obrigatórios devem ser preenchidos!!!");
    }
}
