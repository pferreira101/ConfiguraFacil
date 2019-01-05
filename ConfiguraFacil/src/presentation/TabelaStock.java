/*
 * Created by JFormDesigner on Sat Dec 29 17:42:52 WET 2018
 */

package presentation;

import java.awt.event.*;
import business.ConfiguraFacil;
import business.gFabrica.Stock;
import java.util.*;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Diogo Sobral
 */
public class TabelaStock extends JFrame {

    ConfiguraFacil cf;
    Map<String,Stock> stock;

    public void updateTable(){
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        Object row_data[] = new Object[3];
        this.stock = cf.getStockList();

        // Remove todos
        model.setRowCount(0);

        // Adiciona novos
        for(Map.Entry<String, Stock> k : this.stock.entrySet()){
            row_data[0] = k.getValue().getID();
            row_data[1] = k.getKey();
            row_data[2] = k.getValue().getQuantidade();
            model.addRow(row_data);
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table1.getModel());
        table1.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    public TabelaStock(ConfiguraFacil cf) {
        initComponents();
        this.cf = cf;
        updateTable();
    }

    private void SairActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void atu_btnActionPerformed(ActionEvent e) {
        new EncomendaStockFame(this.cf, this).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pedro Moreira
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        Sair = new JButton();
        atu_btn = new JButton();

        //======== this ========
        setName("frame18");
        setTitle("Stock");
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", null, ""},
                },
                new String[] {
                    "ID", "Designa\u00e7\u00e3o", "Stock"
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
            scrollPane1.setViewportView(table1);
        }

        //---- Sair ----
        Sair.setText("Sair");
        Sair.addActionListener(e -> SairActionPerformed(e));

        //---- atu_btn ----
        atu_btn.setText("Atualizar Stock");
        atu_btn.addActionListener(e -> atu_btnActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Sair, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                    .addComponent(atu_btn)
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(Sair)
                        .addComponent(atu_btn))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pedro Moreira
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton Sair;
    private JButton atu_btn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
