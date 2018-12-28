package data;

import business.gConfig.Componente;
import business.gConfig.Pacote;
import business.ConfiguraFacil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacoteDAO {
	public ConfiguraFacil ConfiguraFacil;

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
        Pacote p;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM pacote WHERE id = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            p = new Pacote();
            p.setID(rs.getInt("id_pacote"));
            p.setDesconto(rs.getDouble("desconto"));

        }
        else throw new Exception("No package found for given ID");

        List<Componente> l = new ArrayList<>();

        /*st = con.prepareStatement("SELECT * FROM componentespacote WHERE id_pacote = ?;");
        st.setInt(1, id);

        rs = st.executeQuery;
        /while (rs.next()) {
        	int idC = rs.getInt("id_componente");

        	PreparedStatement stAUX = con.prepareStatement("SELECT * FROM componente WHERE id_componente = ?;");
        	stAUX.setInt(1, idC);
        	
        	ResultSet rsAUX = stAUX.executeQuery();
        	// adicionar componente à lista

        }*/


        con.close();

        return p;
    }

    public List<Pacote> list() throws Exception {
        List<Pacote> r = new ArrayList<>();
        Pacote p;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM pacote;");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            p = new Pacote();
            p.setID(rs.getInt("id_pacote"));
            p.setDesconto(rs.getDouble("desconto"));
            // falta ir buscar lista dos componentes

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