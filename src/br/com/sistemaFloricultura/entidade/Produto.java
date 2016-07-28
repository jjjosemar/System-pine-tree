package br.com.sistemaFloricultura.entidade;

import br.com.sistemaFloricultura.excecao.ValorInvalidoException;

/**
 *
 * @author josemar
 */
public class Produto {
    
    private Integer codigo, idFornecedor, qtde, idTipo;
    private float valorVenda, valorCompra;
    private String descricao, observacao;

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        
        if(qtde < 0 )
            throw new ValorInvalidoException();
        
        this.qtde = qtde;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public void setValorVenda(float valor) {
        if(valor < 0)
            throw new ValorInvalidoException();
        
        this.valorVenda = valor;
    }
    
    public void setValorCompra(float valor) {
        if(valor < 0)
            throw new ValorInvalidoException();
        
        this.valorCompra = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", idFornecedor=" + idFornecedor + ", qtde=" + qtde + ", idTipo=" + idTipo + ", valorVenda=" + valorVenda + ", valorCompra=" + valorCompra + ", descricao=" + descricao + ", observacao=" + observacao + '}';
    }
}
