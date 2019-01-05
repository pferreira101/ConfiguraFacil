/*
 * Created by JFormDesigner on Sat Dec 29 18:46:48 WET 2018
 */

package presentation;

import business.ConfiguraFacil;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Diogo Sobral
 */
public class EncomendaStockFame extends JFrame {
    ConfiguraFacil cf;
    TabelaStock root;
    Boolean fst_check;
    Boolean snd_check;
    Boolean thd_check;
    int id;
    int quantidade;

    public EncomendaStockFame(ConfiguraFacil cf, TabelaStock root) {
        initComponents();
        this.cf = cf;
        this.root = root;
        this.fst_check = false;
        this.snd_check = false;
        this.id = -1;
        this.quantidade = 0;
        q_comp.setVisible(false);
        label_q.setVisible(false);
    }

    public EncomendaStockFame(ConfiguraFacil cf, TabelaStock root,int id){
        initComponents();
        this.root = root;
        this.cf = cf;
        this.fst_check = true;
        this.snd_check = false;
        this.id = id;
        this.quantidade = 0;
        q_comp.setVisible(true);
        label_q.setVisible(true);
        id_comp.setText(Integer.toString(this.id));
    }

    private int decisao(){
        Object[] options = {"Adicionar", "Descartar"};

        return JOptionPane.showOptionDialog(new JFrame(), "Deseja adicionar a componente?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
    }

    private int warning(){
        return JOptionPane.showOptionDialog(new JFrame(), "Componente pretendido não está no sistema", "", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
    }

    private int erro(){
        return JOptionPane.showOptionDialog(new JFrame(), "Erro no input", "", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
    }

    private void sair(){
        this.dispose();
    }

    private void Encomenda_btnActionPerformed(ActionEvent e) {

        if (!fst_check) {
            try {
                this.id = Integer.parseInt(id_comp.getText());
                this.fst_check = true;
                boolean valid = this.cf.existeStock(this.id);

                if (valid){
                    q_comp.setVisible(true);
                    label_q.setVisible(true);
                    button1.setText("Encomendar");
                }
                else{
                    warning();
                    int d = decisao();
                    this.dispose();
                    if(d == JOptionPane.YES_OPTION)
                        new NovaComponenteFrame(this.cf, root).setVisible(true);
                    else
                        JOptionPane.showOptionDialog(new JFrame(), "Selecção cancelada", "", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
                }
            }
            catch (Exception a) {
                erro();
                sair();
            }
        }
        else if (!snd_check){
                try {
                    this.quantidade = Integer.parseInt(q_comp.getText());
                    this.cf.encomendar(this.id,this.quantidade);
                    this.root.updateTable();
                    sair();
                }
                catch (NumberFormatException b){
                    erro();
                    sair();
                }
                catch (Exception a) {
                    JOptionPane.showOptionDialog(new JFrame(), "Erro interno", "Erro", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
                    sair();
                }
            }

            //adicionar

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pedro Moreira
        id_comp = new JTextField();
        q_comp = new JTextField();
        label1 = new JLabel();
        label_q = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("Encomendar Stock");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("ID da Componente");

        //---- label_q ----
        label_q.setText("Quantidade");

        //---- button1 ----
        button1.setText("Verificar");
        button1.addActionListener(e -> Encomenda_btnActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_q))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(id_comp, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(q_comp, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGap(25, 25, 25))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(id_comp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(q_comp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_q))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JTextField id_comp;
    private JTextField q_comp;
    private JLabel label1;
    private JLabel label_q;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
