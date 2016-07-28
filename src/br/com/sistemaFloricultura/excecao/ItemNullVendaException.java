package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class ItemNullVendaException extends SistemaFloriculturaException{
    
    public ItemNullVendaException() {
        super("Erro!!! Não é possivel realizar uma venda sem itens adicionados");
    }
}
