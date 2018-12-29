package data;

import business.gFabrica.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    public void put(int id, int quantidade) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO stock " +
                                  "VALUES (?, ?)" +
                                  "ON DUPLICATE KEY UPDATE quantidade = ?;");

        st.setInt(1, id);
        st.setInt(2, quantidade);

        st.setInt(3, quantidade);

        st.execute();

        con.close();
    }

    public void put(int id, Stock s) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO stock " +
                "VALUES (?, ?)" +
                "ON DUPLICATE KEY UPDATE quantidade = ?;");

        st.setInt(1, id);
        st.setInt(2, s.getQuantidade());

        st.setInt(3, s.getQuantidade());

        st.execute();

        con.close();
    }

    public Stock get(int id) throws Exception {
        int quantidade = 0;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM stock WHERE componente = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            quantidade = rs.getInt("quantidade");
        }
        else throw new Exception("No stock found");

        con.close();

        Stock s = new Stock(id,quantidade);

        return s;
    }

    public List<Stock> list() throws Exception {
        List<Stock> r = new ArrayList<>();
        Stock s;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM stock;");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            s = new Stock();
            s.setID(rs.getInt("componente"));
            s.setQuantidade(rs.getInt("quantidade"));

            r.add(s);
        }

        con.close();

        return r;
    }

    public boolean containsKey(int cod){
        boolean value = false;
        try{
            Stock s = this.get(cod);
            value = true;
        }
        catch (Exception e){
            value = false;
        }

        return value;
    }


    public int size() throws SQLException {
        int r = 0;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT count(*) FROM stock;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            r = Integer.parseInt(rs.getString(1));
        }
        con.close();

        return r;
    }
}