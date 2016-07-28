package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class QtdeUnitarioException extends SistemaFloriculturaException{
    
    public QtdeUnitarioException() {
        super("Erro!!! A Quantidade de unidades deve ser maior que 0 e menor do que a quantidade disponível em estoque ");
    }
}
