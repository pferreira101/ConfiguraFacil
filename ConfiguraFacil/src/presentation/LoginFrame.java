/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.Componente;
import business.ConfiguraFacil;
import business.gConta.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Pedro
 */
public class LoginFrame extends javax.swing.JFrame {

    private static ConfiguraFacil cf;


    private void login_btnActionPerformed(ActionEvent e) {
        int id = Integer.parseInt(id_txt.getText());
        String password = pw_txt.getText();

        int tipo = cf.logIn(id, password);

        if(tipo == 1) {
            this.dispose();
            Funcionario f = cf.funcionarios.get(id);
            new StandFrame(this.cf, f).setVisible(true);
        }
        else if(tipo == 2) {
            this.dispose();
            Funcionario f = cf.funcionarios.get(id);
            new FabricaFrame(this.cf, f).setVisible(true);
        }
        else if(tipo == 3){
            this.dispose();
            new FuncionariosFrame(this.cf).setVisible(true);
        }
        else this.error_txt.setText("Credenciais Inválidas");
    }


    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private void initComponents() {
        sair_btn = new JButton();
        id_txt = new JTextField();
        pw_txt = new JPasswordField();
        login_btn = new JButton();
        jLabel1 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        error_txt = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("LogIn");
        Container contentPane = getContentPane();

        //---- sair_btn ----
        sair_btn.setText("Sair");
        sair_btn.addActionListener(e -> sair_btnActionPerformed(e));

        //---- login_btn ----
        login_btn.setText("Log In");
        login_btn.addActionListener(e -> login_btnActionPerformed(e));

        //---- jLabel1 ----
        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/presentation/logo.png")));

        //---- label1 ----
        label1.setText("ID");

        //---- label2 ----
        label2.setText("Password");

        //---- error_txt ----
        error_txt.setText(" ");
        error_txt.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        error_txt.setForeground(Color.red);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label2)
                                .addComponent(label1))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(pw_txt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                .addComponent(id_txt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(sair_btn, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(login_btn)))))
                    .addContainerGap(54, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 98, Short.MAX_VALUE)
                    .addComponent(error_txt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    .addGap(89, 89, 89))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pw_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(2, 2, 2)
                    .addComponent(error_txt)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(login_btn)
                        .addComponent(sair_btn))
                    .addContainerGap(8, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void sair_btnActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* INICIA CONFIGURA FACIL */

        cf = new ConfiguraFacil();
        cf.loadFuncionarios();

        // FIXME: 12/27/2018 DEBUGGING
        Componente teste = cf.componenteDAO.get(1);
        System.out.println("Incompatível: " + teste.getIncompativeis().get(0).getDesignacao());
        System.out.println("Complementar: " + teste.getComplementares().get(0).getDesignacao());


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JButton sair_btn;
    private JTextField id_txt;
    private JPasswordField pw_txt;
    private JButton login_btn;
    private JLabel jLabel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel error_txt;
    // End of variables declaration//GEN-END:variables
}
