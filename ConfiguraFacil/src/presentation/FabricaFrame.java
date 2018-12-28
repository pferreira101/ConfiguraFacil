/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.ConfiguraFacil;
import business.gConta.Funcionario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 *
 * @author Pedro
 */
public class FabricaFrame extends javax.swing.JFrame {

    ConfiguraFacil cf;
    Funcionario funcionario;



    private void stock_btnActionPerformed(ActionEvent e) throws Exception {
        new AtualizarStockFrame(this.cf).setVisible(true);
    }


    private void encomendas_btnActionPerformed(ActionEvent e) throws Exception {
        new EncomendasFrame(this.cf).setVisible(true);
    }

    private void sair_btnActionPerformed(ActionEvent e) {
        this.dispose();
        new LoginFrame().setVisible(true);
    }


    /**
     * Creates new form FabricaFrame
     */
    public FabricaFrame(ConfiguraFacil cf, Funcionario f) {
        initComponents();
        this.cf = cf;
        this.funcionario = f;
        this.login_lbl.setText("Logged in as " + f.getNome());
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
        jLabel1 = new JLabel();
        encomendas_btn = new JButton();
        stock_btn = new JButton();
        sair_btn = new JButton();
        login_lbl = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("F\u00e1brica");
        Container contentPane = getContentPane();

        //---- jLabel1 ----
        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jLabel1.setIcon(new ImageIcon(getClass().getResource("logo.png")));
        jLabel1.setText("- F\u00c1BRICA");

        //---- encomendas_btn ----
        encomendas_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        encomendas_btn.setText("Visualizar Encomendas");
        encomendas_btn.addActionListener(e -> {
            try {
                encomendas_btnActionPerformed(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //---- stock_btn ----
        stock_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        stock_btn.setText("Atualizar Stock");
        stock_btn.addActionListener(e -> {
            try {
                stock_btnActionPerformed(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //---- sair_btn ----
        sair_btn.setText("Sair");
        sair_btn.addActionListener(e -> sair_btnActionPerformed(e));

        //---- login_lbl ----
        login_lbl.setFont(new Font("Tahoma", Font.PLAIN, 8));
        login_lbl.setText("Logged in as");
        login_lbl.setHorizontalAlignment(SwingConstants.RIGHT);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(encomendas_btn, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                        .addComponent(stock_btn, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(76, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                    .addGap(63, 63, 63))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sair_btn)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                    .addComponent(login_lbl, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                    .addComponent(encomendas_btn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(stock_btn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(sair_btn)
                            .addGap(7, 7, 7))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(login_lbl)
                            .addGap(2, 2, 2))))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JLabel jLabel1;
    private JButton encomendas_btn;
    private JButton stock_btn;
    private JButton sair_btn;
    private JLabel login_lbl;
    // End of variables declaration//GEN-END:variables
}
