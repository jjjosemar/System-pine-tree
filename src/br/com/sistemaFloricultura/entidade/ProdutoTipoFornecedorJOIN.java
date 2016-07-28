package br.com.sistemaFloricultura.entidade;

/**
 *
 * @author josemar
 */
public class ProdutoTipoFornecedorJOIN extends Produto{
    String nomeFornecedor, nomeTipo;

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }
}
