/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.swing.table.*;
import business.gConta.Cliente;
import business.ConfiguraFacil;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro
 */
public class ClientesFrame extends javax.swing.JFrame {

    ConfiguraFacil cf;

    /**
     * Método que atualiza a tabela dos clientes
     * @param clientes nova lista de clientes a exibir
     */
    private void updateTable(Collection<Cliente> clientes){
        DefaultTableModel model = (DefaultTableModel) display_tbl.getModel();
        Object row_data[] = new Object[3];

        // Remove todos
        model.setRowCount(0);

        // Adiciona novos
        for(Cliente c : clientes){
            row_data[0] = c.getID();
            row_data[1] = c.getNome();
            row_data[2] = c.getTelemovel();
            model.addRow(row_data);
        }
    }


    private void novo_cliente_btnActionPerformed(ActionEvent e) {
        new NovoClienteFrame(this.cf).setVisible(true);
    }

    private void display_tblMouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            int row = this.display_tbl.getSelectedRow();
            int id = (int) this.display_tbl.getModel().getValueAt(row, 0);

            Cliente selected = this.cf.clientes.get(id);

            new AlterarClienteFrame(selected).setVisible(true);
        }
    }

    private void cliente_txtKeyReleased(KeyEvent e) {
        String to_search = cliente_txt.getText();

        Collection<Cliente> clientes = this.cf.clientes.values().stream().filter(c -> c.getNome().contains(to_search))
                                                                         .collect(Collectors.toList());

        updateTable(clientes);
    }



    /**
     * Creates new form ClientesFrame
     */
    public ClientesFrame(ConfiguraFacil cf) {
        initComponents();
        this.cf = cf;
        this.cf.loadClientes();
        updateTable(cf.clientes.values());
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
        novo_cliente_btn = new JButton();
        cliente_txt = new JTextField();
        jScrollPane1 = new JScrollPane();
        display_tbl = new JTable();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        Container contentPane = getContentPane();

        //---- sair_btn ----
        sair_btn.setText("Sair");

        //---- novo_cliente_btn ----
        novo_cliente_btn.setText("Adicionar Cliente");
        novo_cliente_btn.addActionListener(e -> {
			novo_cliente_btnActionPerformed(e);
			novo_cliente_btnActionPerformed(e);
		});

        //---- cliente_txt ----
        cliente_txt.setToolTipText("Procurar cliente");
        cliente_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                cliente_txtKeyReleased(e);
            }
        });

        //======== jScrollPane1 ========
        {

            //---- display_tbl ----
            display_tbl.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null},
                },
                new String[] {
                    "ID", "Nome", "Telem\u00f3vel"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = display_tbl.getColumnModel();
                cm.getColumn(0).setPreferredWidth(10);
                cm.getColumn(2).setPreferredWidth(40);
            }
            display_tbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    display_tblMouseClicked(e);
                }
            });
            jScrollPane1.setViewportView(display_tbl);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sair_btn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                    .addComponent(novo_cliente_btn)
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cliente_txt, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(35, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cliente_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(sair_btn)
                        .addComponent(novo_cliente_btn))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JButton sair_btn;
    private JButton novo_cliente_btn;
    private JTextField cliente_txt;
    private JScrollPane jScrollPane1;
    private JTable display_tbl;
    // End of variables declaration//GEN-END:variables
}
