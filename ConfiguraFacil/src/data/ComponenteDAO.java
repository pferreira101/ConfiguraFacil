package data;


import business.gConfig.Componente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComponenteDAO {

    public void put(int id, Componente c) throws SQLException {
        //Establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;

        // Insere na tabela 'Componente'
        st = con.prepareStatement("INSERT INTO componente VALUES (?, ?, ?, ?);");
        st.setInt(1, id);
        st.setString(2, c.getDesignacao());
        st.setDouble(3, c.getPreco());
        st.setInt(4, c.getTipo());

        st.execute();


        // Insere na tabela 'Incompativeis'
        st = con.prepareStatement("INSERT INTO incompativel VALUES (?, ?)");
        for(Componente inc : c.getIncompativeis()){
            st.setInt(1, c.getID());
            st.setInt(2, inc.getID());

            st.execute();
        }


        // Insere na tabela 'Complementares'
        st = con.prepareStatement("INSERT INTO incompativel VALUES (?, ?)");
        for(Componente comp : c.getComplementares()){
            st.setInt(1, c.getID());
            st.setInt(2, comp.getID());

            st.execute();
        }


        con.close();
    }

    public Componente get(int id) throws Exception {
        Componente c;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st, st_aux;
        ResultSet rs, rs_aux;

        // atributos base 'Componente'
        st = con.prepareStatement("SELECT * FROM componente WHERE id_componente = ?;");
        st.setInt(1, id);

        rs = st.executeQuery();
        if(rs.next()) {
            c = new Componente();
            c.setID(id);
            c.setDesignacao(rs.getString("designacao"));
            c.setPreco(rs.getDouble("preco"));
            c.setTipo(rs.getInt("tipo"));
        }
        else throw new Exception("Componente não encontrado");


        // incompativeis
        st = con.prepareStatement("SELECT comp.* " +
                                  "FROM incompativel AS i INNER JOIN componente AS c " +
                                                         "ON i.componente = c.id_componente " +
                                                         "INNER JOIN componente AS comp " +
                                                         "ON i.incompativel = comp.id_componente " +
                                  "WHERE c.id_componente = ?");
        st.setInt(1, id);

        rs = st.executeQuery();
        while(rs.next()) {
            Componente inc = new Componente();
            inc.setID(rs.getInt("id_componente"));
            inc.setDesignacao(rs.getString("designacao"));
            inc.setPreco(rs.getDouble("preco"));
            inc.setTipo(rs.getInt("tipo"));

            c.getIncompativeis().add(inc);
        }


        // complementar
        st = con.prepareStatement("SELECT comp.* " +
                                  "FROM complementar AS co INNER JOIN componente AS c " +
                                                         "ON co.componente = c.id_componente " +
                                                         "INNER JOIN componente AS comp " +
                                                         "ON co.complementar = comp.id_componente " +
                                  "WHERE c.id_componente = ?");
        st.setInt(1, id);

        rs = st.executeQuery();
        while(rs.next()) {
            Componente compl = new Componente();
            compl.setID(rs.getInt("id_componente"));
            compl.setDesignacao(rs.getString("designacao"));
            compl.setPreco(rs.getDouble("preco"));
            compl.setTipo(rs.getInt("tipo"));

            c.getComplementares().add(compl);
        }

        con.close();

        return c;
    }

    public List<Componente> list() throws Exception {
        List<Componente> r = new ArrayList<>();
        Componente c;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM componente;");

        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            c = this.get(rs.getInt("id_componente"));

            r.add(c);
        }

        con.close();

        return r;
    }



    public int size() throws SQLException {
        int r = 0;
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("SELECT count(*) FROM componente;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            r = Integer.parseInt(rs.getString(1));
        }
        c.close();

        return r;
    }

}