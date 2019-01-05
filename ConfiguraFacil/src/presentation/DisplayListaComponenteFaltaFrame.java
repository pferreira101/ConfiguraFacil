/*
 * Created by JFormDesigner on Sat Dec 29 22:07:27 WET 2018
 */

package presentation;

import java.awt.event.*;
import business.gConfig.Componente;
import java.util.List;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Diogo Sobral
 */
public class DisplayListaComponenteFaltaFrame extends JFrame {

    List<Componente> lista;

    public DisplayListaComponenteFaltaFrame(List<Componente> l)  {
        initComponents();
        this.lista = l;
        updateTable();
    }

    private void updateTable(){
        DefaultTableModel model = (DefaultTableModel) tb_lista.getModel();
        Object row_data[] = new Object[2];

        // Remove todos
        model.setRowCount(0);

        // Adiciona novos
        for(Componente f : lista){
            row_data[0] = f.getID();
            row_data[1] = f.getDesignacao();

            model.addRow(row_data);
        }
    }

    private void exit_btActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pedro Moreira
        scrollPane1 = new JScrollPane();
        tb_lista = new JTable();
        exit_bt = new JButton();

        //======== this ========
        setTitle("Componentes em falta");
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- tb_lista ----
            tb_lista.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null},
                },
                new String[] {
                    "Id", "Designa\u00e7\u00e3o"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            scrollPane1.setViewportView(tb_lista);
        }

        //---- exit_bt ----
        exit_bt.setText("Sair");
        exit_bt.addActionListener(e -> exit_btActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(exit_bt, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(35, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(exit_bt)
                    .addContainerGap(8, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JScrollPane scrollPane1;
    private JTable tb_lista;
    private JButton exit_bt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
