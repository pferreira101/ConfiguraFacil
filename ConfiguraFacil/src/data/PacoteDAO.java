package data;

import business.gConfig.Componente;
import business.gConfig.Pacote;
import business.ConfiguraFacil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacoteDAO {
	public ComponenteDAO componenteDAO = new ComponenteDAO();

	public void put(int id, Pacote p) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO pacote VALUES (?, ?);");
        st.setInt(1, id);
        st.setDouble(2, p.getDesconto());

        st.execute();

        List<Componente> l = p.getComponentes();

        st = con.prepareStatement("INSERT INTO componentespacote VALUES (?, ?);");
        for (int i=0;i<l.size();i++){
        	st.setInt(1, id);
        	st.setInt(2, l.get(i).getID());
        	st.execute();
        }

        con.close();
    }

    public Pacote get(int id) throws Exception {
        List<Componente> comps = new ArrayList<>();
        double desconto = 0;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT c.id_componente " +
                                  "FROM componente AS c INNER JOIN componentespacote AS cp " +
                                                       "ON c.id_componente = cp.componente " +
                                                       "INNER JOIN pacote as p " +
                                                       "ON cp.pacote = p.id_pacote " +
                                  "WHERE p.id_pacote = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            Componente c = componenteDAO.get(rs.getInt("c.id_componente"));
            comps.add(c);
        }

        st = con.prepareStatement("SELECT desconto FROM pacote WHERE id_pacote = ?;");
        st.setInt(1, id);

        rs = st.executeQuery();
        if(rs.next()) desconto = rs.getDouble("desconto");



        con.close();

        return new Pacote(id, comps, desconto);
    }

    public List<Pacote> list() throws Exception {
        List<Pacote> r = new ArrayList<>();
        Pacote p;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM pacote;");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            p = this.get(rs.getInt("id_pacote"));
            r.add(p);

            System.out.println(p.getID()); // FIXME: 12/22/2018 DEBUGGING
        }

        con.close();

        return r;
    }


    public void remove(int id) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM pacote WHERE id_pacote = ?;");
        st.setInt(1, id);

        // é preciso apagar os componentes associados ao pacote na respetiva tabela?

        st.executeQuery();

        con.close();
    }


    public int size() throws SQLException {
        int r = 0;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT count(*) FROM pacote;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            r = Integer.parseInt(rs.getString(1));
        }
        con.close();

        return r;
    }

}