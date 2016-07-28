package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class ValorInvalidoException extends SistemaFloriculturaException{

    public ValorInvalidoException() {
        super("Erro!!! Algum dos campos iseridos possuem o valor inválido");
    }   
}
