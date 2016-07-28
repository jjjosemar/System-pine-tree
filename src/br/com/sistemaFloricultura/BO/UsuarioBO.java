package br.com.sistemaFloricultura.BO;

import br.com.sistemaFloricultura.DAO.UsuarioDAO;
import br.com.sistemaFloricultura.entidade.Usuario;
import br.com.sistemaFloricultura.excecao.LoginDuplicadoException;
import br.com.sistemaFloricultura.excecao.UsuarioNaoEncontradoException;
import java.util.List;

/**
 *
 * @author josemar
 */
public class UsuarioBO {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public void atualizar(Usuario usuario) throws Exception{
        usuarioDAO.atualizar(usuario);
    }
    
    public Usuario buscarUsuarioPorLogin(String login) throws Exception{
        return usuarioDAO.buscarUsuarioPorLogin(login);
    }
    
    public Usuario buscarUsuarioPorCodigo(int codigo) throws Exception{
        return usuarioDAO.buscarUsuarioPorCodigo(codigo);
    }
    
    public void cadastrar(Usuario usuario) throws Exception{
        verificarUsuarioCadastro(usuario);
        usuarioDAO.cadastrar(usuario);
    }
    
    public List<Usuario> listar() throws Exception{
        return usuarioDAO.listar();
    }
    
    public void excluir(String login) throws Exception{
        
        usuarioDAO.buscarUsuarioPorLogin(login);
        usuarioDAO.deletar(login);
    }

    private void verificarUsuarioCadastro(Usuario usuario) throws Exception {
        try {
            usuarioDAO.buscarUsuarioPorLogin(usuario.getLogin());
            
            throw new  LoginDuplicadoException();
            
        } catch (UsuarioNaoEncontradoException e) {
        }
    }
}
