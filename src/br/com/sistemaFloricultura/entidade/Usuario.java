package br.com.sistemaFloricultura.entidade;

/**
 *
 * @author josemar
 */
public class Usuario {
    private String nome,senha, login;
    private char cargo;
    private int codigo;

    public String getNome() {
        return nome;
    }

    public char getCargo() {
        return cargo;
    }

    public void setCargo(char cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return login;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
