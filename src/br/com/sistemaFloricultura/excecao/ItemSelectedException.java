package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class ItemSelectedException extends SistemaFloriculturaException{

    public ItemSelectedException() {
        super("Esse item já foi adicionado na venda. Por favor selecione outro!");
    }
    
}
