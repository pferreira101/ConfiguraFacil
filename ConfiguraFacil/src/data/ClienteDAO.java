package data;

import business.gConta.Cliente;
import business.ConfiguraFacil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {


    public void put(int id, Cliente c) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO cliente " +
                                  "VALUES (?, ?, ?, ?)" +
                                  "ON DUPLICATE KEY UPDATE nome = ?, telemovel = ?, email = ?;");
        st.setInt(1, id);
        st.setString(2, c.getNome());
        st.setString(3, c.getEmail());
        st.setInt(4, c.getTelemovel());

        st.setString(5, c.getNome());
        st.setString(7, c.getEmail());
        st.setInt(6, c.getTelemovel());

        st.execute();

        con.close();
    }

    public Cliente get(int id) throws Exception {
        Cliente c;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            c = new Cliente();
            c.setID(rs.getInt("id_cliente"));
            c.setNome(rs.getString("nome"));
            c.setTelemovel(rs.getInt("telemovel"));
            c.setEmail(rs.getString("email"));

        }
        else throw new Exception("No user found for given mail");

        con.close();

        return c;
    }

    public List<Cliente> list() throws Exception {
        List<Cliente> r = new ArrayList<>();
        Cliente c;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM cliente;");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            c = new Cliente();
            c.setID(rs.getInt("id_cliente"));
            c.setNome(rs.getString("nome"));
            c.setTelemovel(rs.getInt("telemovel"));
            c.setEmail(rs.getString("email"));

            r.add(c);
        }

        con.close();

        return r;
    }


    public void remove(int id) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?;");
        st.setInt(1, id);

        st.executeQuery();

        con.close();
    }


    public int size() throws SQLException {
        int r = 0;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT count(*) FROM cliente;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            r = Integer.parseInt(rs.getString(1));
        }
        con.close();

        return r;
    }

}