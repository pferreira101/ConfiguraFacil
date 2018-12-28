/*
 * Created by JFormDesigner on Fri Dec 28 15:30:56 GMT 2018
 */

package presentation;

import java.awt.event.*;
import javax.swing.table.*;

import business.gConfig.Componente;
import business.gFabrica.Encomenda;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Pedro Moreira
 */
public class EncomendaDetalhesFrame extends JFrame {

    Encomenda e;


    private void updateTable(List<Componente> comps){
        DefaultTableModel model = (DefaultTableModel) cmp_tbl.getModel();
        Object row_data[] = new Object[3];

        // Remove todos
        model.setRowCount(0);

        // Adiciona novos
        for(Componente c : comps){
            row_data[0] = c.getID();
            row_data[1] = c.getDesignacao();
            row_data[2] = c.getPreco();
            model.addRow(row_data);
        }
    }


    public EncomendaDetalhesFrame(Encomenda e) {
        initComponents();
        this.e = e;
        updateTable(e.getAllComponentes());
    }

    private void sair_btnActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pedro Moreira
        scrollPane1 = new JScrollPane();
        cmp_tbl = new JTable();
        sair_btn = new JButton();

        //======== this ========
        setTitle("Detalhes Encomenda");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- cmp_tbl ----
            cmp_tbl.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null},
                    {null, null, null},
                },
                new String[] {
                    "ID", "Designa\u00e7\u00e3o", "Pre\u00e7o"
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
            scrollPane1.setViewportView(cmp_tbl);
        }

        //---- sair_btn ----
        sair_btn.setText("Sair");
        sair_btn.addActionListener(e -> sair_btnActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(312, Short.MAX_VALUE)
                    .addComponent(sair_btn)
                    .addGap(12, 12, 12))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                    .addComponent(sair_btn)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JScrollPane scrollPane1;
    private JTable cmp_tbl;
    private JButton sair_btn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
