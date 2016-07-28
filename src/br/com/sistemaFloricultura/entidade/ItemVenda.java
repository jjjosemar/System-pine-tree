package br.com.sistemaFloricultura.entidade;

import br.com.sistemaFloricultura.excecao.QtdeUnitarioException;

/**
 *
 * @author josemar
 */
public class ItemVenda {
    
    private int codigo, qtde, idVenda, idProduto;

    public int getCodigo() {
        return codigo;
    }

    public int getQtde() {
        return qtde;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setQtde(int qtde) {
        
        if(qtde <= 0)
            throw new QtdeUnitarioException();
        
        this.qtde = qtde;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
 
}
