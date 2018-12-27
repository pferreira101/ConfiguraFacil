package data;

import business.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComponenteDAO {

    public void put(int id, Componente c) throws SQLException, ClassNotFoundException {
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
        st = con.prepareStatement("SELECT * FROM incompativel WHERE componente = ?");
        st.setInt(1, id);

        rs = st.executeQuery();
        if(rs.next()) {
            Componente inc = new Componente();
            inc.setID(rs.getInt("incompativel"));
            st_aux = con.prepareStatement("SELECT * FROM componente WHERE id_componente = ?;");
            st_aux.setInt(1, inc.getID());

            rs_aux = st_aux.executeQuery();
            if(rs_aux.next()){
                inc.setDesignacao(rs_aux.getString("designacao"));
                inc.setPreco(rs_aux.getDouble("preco"));
                inc.setTipo(rs_aux.getInt("tipo"));

                c.getIncompativeis().add(inc);
            }

        }
        // else throw new Exception("Componente incompatível não encontrado");


        // complementar
        st = con.prepareStatement("SELECT * FROM complementar WHERE componente = ?");
        st.setInt(1, id);

        rs = st.executeQuery();
        if(rs.next()) {
            Componente comp = new Componente();
            comp.setID(rs.getInt("complementar"));
            st_aux = con.prepareStatement("SELECT * FROM componente WHERE id_componente = ?;");
            st_aux.setInt(1, comp.getID());

            rs_aux = st_aux.executeQuery();
            if(rs_aux.next()){
                comp.setDesignacao(rs_aux.getString("designacao"));
                comp.setPreco(rs_aux.getDouble("preco"));
                comp.setTipo(rs_aux.getInt("tipo"));

                c.getComplementares().add(comp);
            }

        }
        // else throw new Exception("Componente complementar não encontrado");


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