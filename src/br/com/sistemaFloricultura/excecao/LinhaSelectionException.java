package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class LinhaSelectionException extends SistemaFloriculturaException{

    public LinhaSelectionException() {
        super("Erro!!! Deve ser selecionada uma linha na tabela para realizar a operação");
    }
    
}
