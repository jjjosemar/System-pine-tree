package br.com.sistemaFloricultura.excecao;

/**
 *
 * @author josemar
 */
public class CnpjDuplicadoException extends SistemaFloriculturaException{

    public CnpjDuplicadoException() {
        super("Erro!!! O CNPJ já existe em outro cadastro");
    }
    
}
