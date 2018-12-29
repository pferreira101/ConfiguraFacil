package data;

import business.gConfig.Componente;
import business.gConfig.Configuracao;
import business.gConfig.Pacote;
import business.gFabrica.Encomenda;
import business.ConfiguraFacil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncomendaDAO {

    PacoteDAO pacoteDAO = new PacoteDAO();

	 public void put(int id, Encomenda e) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO encomenda VALUES (?, ?, ?);");
        st.setInt(1, id);
        st.setInt(2, e.getFuncionario());
        st.setInt(3, e.getCliente());

        st.execute();

        Configuracao conf = e.getConfig();
        List<Pacote> lP = conf.getPacotes();
        List<Componente> lC = conf.getComponentes();

        st = con.prepareStatement("INSERT INTO componentesencomenda VALUES (?, ?);");
        for(int i = 0; i < lC.size(); i++){
            st.setInt(1, id);
            st.setInt(2, lC.get(i).getID());
            st.execute();
        }

        st = con.prepareStatement("INSERT INTO pacotesencomenda VALUES (?, ?);");
        for (int i = 0; i < lP.size(); i++){
            st.setInt(1, id);
            st.setInt(2, lP.get(i).getID());
            st.execute();
        }

        con.close();
    }

    public Encomenda get(int id) throws Exception {
        List<Componente> comps = new ArrayList<>();
        List<Pacote> pacotes = new ArrayList<>();
        int cliente = 0;
        int funcionario = 0;


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        // Cliente e Funcionário
        PreparedStatement st;
        st = con.prepareStatement("SELECT cliente, funcionario FROM encomenda WHERE id_encomenda = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()){
            cliente = rs.getInt("cliente");
            funcionario = rs.getInt("funcionario");
        }



        // Componentes

        st = con.prepareStatement("SELECT c.* " +
                                  "FROM encomenda AS e INNER JOIN componentesencomenda AS ce " +
                                                       "ON e.id_encomenda = ce.encomenda " +
                                                       "INNER JOIN componente AS c " +
                                                       "ON c.id_componente = ce.componente " +
                                   "WHERE id_encomenda = ?;");
        st.setInt(1, id);

        rs = st.executeQuery();
        while(rs.next()) {
            Componente c = new Componente();
            c.setID(rs.getInt("id_componente"));
            c.setDesignacao(rs.getString("designacao"));
            c.setPreco(rs.getDouble("preco"));
            c.setTipo(rs.getInt("tipo"));

            comps.add(c);
        }

        // Pacotes

        st = con.prepareStatement("SELECT p.id_pacote " +
                                  "FROM encomenda AS e INNER JOIN pacotesencomenda AS pe " +
                                                      "ON e.id_encomenda = pe.encomenda " +
                                                      "INNER JOIN pacote AS p " +
                                                      "ON p.id_pacote = pe.pacote " +
                                  "WHERE id_encomenda = ?;");
        st.setInt(1, id);

        rs = st.executeQuery();
        while(rs.next()) {
            pacotes.add(this.pacoteDAO.get(rs.getInt("id_pacote")));
        }


        Configuracao c = new Configuracao(comps, pacotes);
        Encomenda e = new Encomenda(id, cliente, funcionario, c);

        con.close();


        return e;
    }


    public List<Encomenda> list() throws Exception {
        List<Encomenda> r = new ArrayList<>();
        Encomenda e;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM encomenda;");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            e = get(rs.getInt("id_encomenda"));

            r.add(e);

        }

        con.close();

        return r;
    }


    public void remove(int id) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM encomenda WHERE id_encomenda = ?;");
        st.setInt(1, id);

        st.executeQuery();

        // é preciso apagar os componentes e pacotes associados à encomenda nas respetivas tabelas?


        con.close();
    }


    public int size() throws SQLException {
        int r = 0;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT count(*) FROM encomenda;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            r = Integer.parseInt(rs.getString(1));
        }
        con.close();

        return r;
    }

}