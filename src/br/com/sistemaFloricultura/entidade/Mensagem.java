package br.com.sistemaFloricultura.entidade;

import javax.swing.JOptionPane;

/**
 *
 * @author josemar
 */
public class Mensagem {
    
    public static void msgInformacao(String msg){
        JOptionPane.showMessageDialog(null, msg,
                                          "Mensagem de informação",
                                          JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int msgQuestionamento(String msg){
        return JOptionPane.showConfirmDialog(null, msg);
    }
    
    public static void msgErro(String msg){
        JOptionPane.showMessageDialog(null, msg,
                                          "Mensagem de comfirmação",
                                          JOptionPane.ERROR_MESSAGE);
    }
}
