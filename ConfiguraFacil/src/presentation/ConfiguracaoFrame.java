/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.ConfiguraFacil;
import business.gConfig.Componente;
import business.gConfig.Configuracao;
import business.gConfig.Pacote;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.event.*;
import javax.swing.table.*;


class Selection{

    List<Componente> comps;
    int selected;

    public Selection(){
        this.comps = new ArrayList<>();
        this.selected = -1;
    }

    public Selection(List data, int selected){
        this.comps = data;
        this.selected = selected;
    }
}


public class ConfiguracaoFrame extends javax.swing.JFrame {

    Selection[] selections;
    List<Pacote> pacotes;
    ConfiguraFacil cf;
    Configuracao config = new Configuracao();

    private void registar_btnActionPerformed(ActionEvent e) {
        if(this.config.getComponentes().size() > 0){
            this.cf.componentesToPacote(this.config, this.pacotes);
            try {
                new RegistaEncomendaFrame(this.cf, this.config).setVisible(true);
            }
            catch (Exception a){
                a.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Nenhuma componente selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sair_btnActionPerformed(ActionEvent e) {
        this.dispose();
    }


    private void type_listValueChanged(ListSelectionEvent e) {
        loadSelection(type_list.getSelectedIndex());
    }



    private void createSelections(int tipo) throws Exception {
        List<Componente> componentes = this.cf.getComponentes();

        List<Componente> comp_by_type = componentes.stream().filter(c -> c.getTipo() == tipo + 1).collect(Collectors.toList());

        Selection s = new Selection(comp_by_type, 0);

        this.selections[tipo] = s;
    }


    /**
     * Método que carrega as componentes de um tipo assim como a componente selecionada desse tipo
     * @param tipo tipo a carregar
     */
    private void loadSelection(int tipo){
        List<Componente> list = selections[tipo].comps;
        DefaultTableModel model = (DefaultTableModel) cmp_tbl.getModel();
        Object row_data[] = new Object[3];

        model.setRowCount(0);
        row_data[0] = "-"; row_data[1] = "-"; row_data[2] = "-"; model.addRow(row_data);

        for(Componente c : list) {
            row_data[0] = c.getID();
            row_data[1] = c.getDesignacao();
            row_data[2] = c.getPreco();

            model.addRow(row_data);
        }

        ListSelectionModel m = cmp_tbl.getSelectionModel();
        m.setSelectionInterval(this.selections[tipo].selected, this.selections[tipo].selected);
    }


    /**
     * Método que atualiza as Selection aquando da adição de um pacote
     */
    public void updateSelection(){
        // Verifica todas as componentes já selecionadas
        List<Componente> all_comps = this.config.getComponentes();
        // for(Pacote p : this.config.getPacotes()) all_comps.addAll(p.getComponentes());

        // Para cada componente atualiza Selection correspondente ao seu tipo
        for(Componente c : all_comps){
            int tipo = c.getTipo() - 1;

            for(int i = 0; i < this.selections[tipo].comps.size(); i++){
                Componente c_tipo = this.selections[tipo].comps.get(i);
                if(c.getID() == c_tipo.getID()){
                    this.selections[tipo].selected = i + 1;
                    break;
                }
            }
        }
    }


    /**
     * Método que atualizar os pacotes selecionados aquando da adição de uma componente
     */
    private void resetSelectionsPacote(){
        // Verifica todas as componentes já selecionadas
        List<Componente> all_comps = this.config.getComponentes();

        // Para cada pacote verifica se está contigo na config
        for(int i = 0; i < this.pacotes.size(); i++){
            boolean flag = all_comps.containsAll(this.pacotes.get(i).getComponentes());

            pacotes_tbl.getModel().setValueAt(flag, i, 1);

        }
    }


    /**
     * Método que desseleciona várias componentes da configuração
     * @param incompativeis lista a remover
     */
    private void resetSelections(List<Componente> incompativeis) {
        for(Componente c : incompativeis){
            int tipo = c.getTipo() - 1;

            this.selections[tipo].selected = 0;
        }
    }


    /**
     * Método que desseleciona uma componente de um certo tipo
     * @param tipo tipo da componente
     */
    private void resetSelections(int tipo) {
        this.selections[tipo-1].selected = 0;
        loadSelection(tipo-1);
    }


    /**
     * Método que carrega a informação de um pacote
     * @param tipo tipo do pacote
     */
    private void loadSelectionPacote(int tipo) {
        DefaultTableModel model = (DefaultTableModel) cmp_tbl2.getModel();
        model.setRowCount(0);

        List<Componente> list = this.pacotes.get(tipo).getComponentes();
        Object row_data[] = new Object[3];

        for(Componente c : list) {
            row_data[0] = c.getID();
            row_data[1] = c.getDesignacao();
            row_data[2] = c.getPreco();

            model.addRow(row_data);
        }
    }


    private void updateSelectionsComplementares(List<Componente> complementares){
        for(Componente c : complementares){
            int tipo = c.getTipo() - 1;

            for(int i = 0; i < this.selections[tipo].comps.size(); i++){
                Componente c_tipo = this.selections[tipo].comps.get(i);
                if(c.getID() == c_tipo.getID()){
                    this.selections[tipo].selected = i + 1;
                    break;
                }
            }
        }
    }

    private void cmp_tblMouseClicked(MouseEvent e) {
        int tipo = type_list.getSelectedIndex();
        int row = cmp_tbl.getSelectedRow();
        boolean flag = true;

        int old_selected = this.selections[tipo].selected;
        Componente old_componente = new Componente();
        if(old_selected > 0){
            old_componente = this.selections[tipo].comps.get(old_selected - 1);
            this.cf.removeComponente(this.config, old_componente);

            // remove comopnentes que tenham como complementar esta componente removida
            try{
                List<Componente> to_remove = new ArrayList<>();
                List<Componente> all_comps = this.config.getComponentes();
                for(Componente c : all_comps){
                    if(c.getComplementares().contains(old_componente)){
                        to_remove.add(c);
                    }
                    this.cf.removeComponentes(this.config, to_remove);
                    resetSelections(to_remove);
                }
            }
            catch (Exception e1){}
        }

        if(row > 0) {
            Componente nova_componente = this.selections[tipo].comps.get(row - 1);


            // INCOMPATIVEIS
            List<Componente> incompativeis = this.cf.checkIncompativeis(this.config, nova_componente);
            int opt = -1;

            if(incompativeis.size() > 0){
                opt = incompativeisErrorMessage(nova_componente, incompativeis);

                if (opt == JOptionPane.YES_OPTION){

                    this.selections[tipo].selected = row;
                    resetSelections(incompativeis);
                }
                else if(opt == JOptionPane.NO_OPTION){
                    this.selections[tipo].selected = old_selected;
                    flag = false;

                    ListSelectionModel m = cmp_tbl.getSelectionModel();
                    m.setSelectionInterval(old_selected, old_selected);

                    JOptionPane.showMessageDialog(new JFrame(), "Componente não adicionada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            // COMPLEMENTARES
            List<Componente> complementares = this.cf.checkComplementares(this.config, nova_componente);
            if(complementares.size() > 0 && flag){
                opt = complementaresErrorMessage(nova_componente, complementares);

                if (opt == JOptionPane.YES_OPTION ){
                    updateSelectionsComplementares(complementares);
                }
                else if(opt == JOptionPane.NO_OPTION){
                    this.selections[tipo].selected = 0;
                    flag = false;

                    ListSelectionModel m = cmp_tbl.getSelectionModel();
                    m.setSelectionInterval(0, 0);

                    JOptionPane.showMessageDialog(new JFrame(), "Componente não adicionada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(flag){
                this.cf.addComponente(this.config, nova_componente);
                this.cf.removeComponentes(this.config, incompativeis);
                this.cf.addComponentes(this.config, complementares);
                this.selections[tipo].selected = row;
                resetSelectionsPacote();
            }

        }
        else{
            this.cf.removeComponente(this.config, old_componente);
            //this.cf.removeComponentes(this.config, old_componente.getComplementares());
            //resetSelections(old_componente.getComplementares());
            resetSelectionsPacote();
            this.selections[tipo].selected = 0;
        }


    }

    private int incompativeisErrorMessage(Componente nova_componente, List<Componente> incompativeis) {
        StringBuilder s = new StringBuilder();
        s.append("Componente a adicionar (").append(nova_componente.getID()).append(" - ")
                                            .append(nova_componente.getDesignacao())
                                            .append(") incompatível com: \n");

        for(Componente c : incompativeis){
            s.append(c.getID()).append(" - ").append(c.getDesignacao()).append('\n');
        }

        Object[] options = {"Adicionar", "Descartar"};

        return JOptionPane.showOptionDialog(new JFrame(), s.toString(), "Erro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private int complementaresErrorMessage(Componente nova_componente, List<Componente> complementares) {
        StringBuilder s = new StringBuilder();
        s.append("Componente a adicionar (").append(nova_componente.getID())
                                            .append(" - ").append(nova_componente.getDesignacao())
                                            .append(") tem como complementares as componentes: \n");

        for(Componente c : complementares){
            s.append(c.getID()).append(" - ").append(c.getDesignacao()).append('\n');
        }

        Object[] options = {"Adicionar todas", "Descartar"};

        return JOptionPane.showOptionDialog(new JFrame(), s.toString(), "Erro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }



    private void pacotes_tblMouseClicked(MouseEvent e) {
        int row = pacotes_tbl.getSelectedRow();

        loadSelectionPacote(row);
    }


    private void pacotes_tbl2MouseClicked(MouseEvent e) {
        int row = pacotes_tbl.getSelectedRow();
        boolean check = (boolean) pacotes_tbl.getModel().getValueAt(row, 1);
        Pacote p = this.pacotes.get(row);
        boolean flag = true;

        try{
            if(e.getClickCount() == 2 && check == true) {
                pacotes_tbl.getModel().setValueAt(false, row, 1);
                this.config.rmComponentes(p.getComponentes());
            }
            else if(e.getClickCount() == 2 && check == false) {
                int opt = -1;

                // INCOMPATIVEIS
                List<Componente> incompativeis = this.cf.checkIncompativeis(this.config, p);

                if (incompativeis.size() > 0) {
                    List<Componente> to_remove_config = new ArrayList<>();

                    for (Componente c : incompativeis) {
                        opt = incompativeisErrorMessagePacote(c);
                        if (opt == JOptionPane.YES_OPTION) {
                            to_remove_config.add(c);
                            resetSelections(c.getTipo());
                        }
                        else {
                            flag = false;
                        }
                    }
                    this.cf.removeComponentes(this.config, to_remove_config);
                }
                else{
                    pacotes_tbl.getModel().setValueAt(true, row, 1);
                }

                // COMPLEMENTARES
                List<Componente> complementares = this.cf.checkComplementares(this.config, p);

                if(complementares.size() > 0){
                    List<Componente> to_add_config = new ArrayList<>();

                    for (Componente c : complementares) {
                        opt = complementaresErrorMessagePacote(c);
                        if (opt == JOptionPane.YES_OPTION) {
                            to_add_config.add(c);
                        }
                        else {
                            flag = false;
                        }
                    }
                    this.cf.addComponentes(this.config, to_add_config);
                }

                pacotes_tbl.getModel().setValueAt(flag, row, 1);
                this.cf.updateConfig(this.config, p);

                updateSelection();
            }
        }
        catch (Exception e1){}
    }

    private int incompativeisErrorMessagePacote(Componente c){
        StringBuilder s = new StringBuilder();
        s.append("A componente da configuração (").append(c.getID()).append(" - ")
                                                        .append(c.getDesignacao())
                                                        .append(") é incompatível com componentes a adicionar do pacote.");

        Object[] options = {"Remover componente da configuração", "Manter componente na configuração"};

        return JOptionPane.showOptionDialog(new JFrame(), s.toString(), "Erro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private int complementaresErrorMessagePacote(Componente c){
        StringBuilder s = new StringBuilder();
        s.append("A componente (").append(c.getID()).append(" - ")
                .append(c.getDesignacao())
                .append(") é complementar de componentes do pacote.");

        Object[] options = {"Adicionar Componente", "Descartar Componente"};

        return JOptionPane.showOptionDialog(new JFrame(), s.toString(), "Erro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private void confgO_btnActionPerformed(ActionEvent e) {
        String s = (String)JOptionPane.showInputDialog(
                this,
                "Por favor introduza o orçamento disponível.",
                "Configuração Ótima",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
        try {
            Configuracao config = this.cf.calculaConfig(Double.parseDouble(s));
            new RegistaEncomendaFrame(this.cf,config).setVisible(true);
        }
        catch (Exception a){
            JOptionPane.showMessageDialog(new JFrame(), "Configuração ótima cancelada", "Campos por preencher", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Creates new form ConfiguraFrame
     */
    public ConfiguracaoFrame(ConfiguraFacil cf) throws Exception {
        initComponents();
        this.cf = cf;
        this.selections = new Selection[6];
        for(int i = 0; i < 6; i++){
            createSelections(i);
        }

        this.pacotes = cf.getPacotes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Henrique Pereira
    private void initComponents() {
        sair_btn = new JButton();
        registar_btn = new JButton();
        jScrollPane1 = new JScrollPane();
        type_list = new JList<>();
        jScrollPane2 = new JScrollPane();
        cmp_tbl = new JTable();
        jScrollPane3 = new JScrollPane();
        pacotes_tbl = new JTable();
        jScrollPane4 = new JScrollPane();
        cmp_tbl2 = new JTable();
        confgO_btn = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configura\u00e7\u00e3o");
        Container contentPane = getContentPane();

        //---- sair_btn ----
        sair_btn.setText("Sair");
        sair_btn.addActionListener(e -> sair_btnActionPerformed(e));

        //---- registar_btn ----
        registar_btn.setText("Registar Encomenda");
        registar_btn.addActionListener(e -> registar_btnActionPerformed(e));

        //======== jScrollPane1 ========
        {

            //---- type_list ----
            type_list.setModel(new AbstractListModel<String>() {
                String[] values = {
                    "1 - Pintura",
                    "2 - Jantes",
                    "3 - Pneus",
                    "4 - Motoriza\u00e7\u00e3o",
                    "5 - Vidros",
                    "6 - Estofos"
                };
                @Override
                public int getSize() { return values.length; }
                @Override
                public String getElementAt(int i) { return values[i]; }
            });
            type_list.addListSelectionListener(e -> type_listValueChanged(e));
            jScrollPane1.setViewportView(type_list);
        }

        //======== jScrollPane2 ========
        {

            //---- cmp_tbl ----
            cmp_tbl.setModel(new DefaultTableModel(
                new Object[][] {
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
            cmp_tbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cmp_tblMouseClicked(e);
                }
            });
            jScrollPane2.setViewportView(cmp_tbl);
        }

        //======== jScrollPane3 ========
        {

            //---- pacotes_tbl ----
            pacotes_tbl.setModel(new DefaultTableModel(
                new Object[][] {
                    {"1 - Sport", false},
                    {"2 - Comfort", false},
                    {"3 - Off-Road", false},
                    {"4 - Executive", false},
                    {"5 - Classic", false},
                    {"6 - Economic", false},
                },
                new String[] {
                    "Pacote", " "
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    Object.class, Boolean.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = pacotes_tbl.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(1).setResizable(false);
                cm.getColumn(1).setPreferredWidth(1);
            }
            pacotes_tbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pacotes_tblMouseClicked(e);
                    pacotes_tbl2MouseClicked(e);
                }
            });
            jScrollPane3.setViewportView(pacotes_tbl);
        }

        //======== jScrollPane4 ========
        {

            //---- cmp_tbl2 ----
            cmp_tbl2.setModel(new DefaultTableModel(
                new Object[][] {
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
            jScrollPane4.setViewportView(cmp_tbl2);
        }

        //---- confgO_btn ----
        confgO_btn.setText("Configura\u00e7\u00e3o \u00d3tima");
        confgO_btn.addActionListener(e -> confgO_btnActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(sair_btn)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addComponent(confgO_btn)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(registar_btn))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 20, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                    .addGap(21, 21, 21)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(registar_btn)
                        .addComponent(confgO_btn)
                        .addComponent(sair_btn))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Henrique Pereira
    private JButton sair_btn;
    private JButton registar_btn;
    private JScrollPane jScrollPane1;
    private JList<String> type_list;
    private JScrollPane jScrollPane2;
    private JTable cmp_tbl;
    private JScrollPane jScrollPane3;
    private JTable pacotes_tbl;
    private JScrollPane jScrollPane4;
    private JTable cmp_tbl2;
    private JButton confgO_btn;
    // End of variables declaration//GEN-END:variables
}
