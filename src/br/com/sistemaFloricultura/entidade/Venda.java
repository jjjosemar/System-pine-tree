package br.com.sistemaFloricultura.entidade;

import java.sql.Date;

/**
 *
 * @author josemar
 */
public class Venda {
    
    private int codigo, codigoVendedor;
    private float desconto, valorVenda;
    private java.sql.Date dataVenda;

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public float getDesconto() {
        return desconto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }
}
