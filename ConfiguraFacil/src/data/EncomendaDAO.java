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
        Encomenda e;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = con.prepareStatement("SELECT * FROM encomenda WHERE id_encomenda = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            e = new Encomenda();
            e.setID(rs.getInt("id_encomenda"));
            e.setFuncionario(rs.getInt("funcionario"));
            e.setCliente(rs.getInt("cliente"));
        }
        else throw new Exception("No order found for given ID");

        // Componentes

        st = con.prepareStatement("SELECT * FROM componentesencomenda WHERE encomenda = ?");
        st.setInt(1, id);

        rs = st.executeQuery();
        while(rs.next()){
            PreparedStatement st_aux;
            st_aux = con.prepareStatement("SELECT * FROM componente WHERE id_componente = ?;");
            st_aux.setInt(1, rs.getInt("componente"));
            
            ResultSet rs_aux = st_aux.executeQuery();
            while (rs_aux.next()){
                Componente c = new Componente();
                c.setID(id);
                c.setDesignacao(rs_aux.getString("designacao"));
                c.setPreco(rs_aux.getDouble("preco"));
                c.setTipo(rs_aux.getInt("tipo"));
                
                e.getComponentes().add(c);
            }
        }

        // FIXME: 12/28/2018 FALTA PACOTE
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
            e = new Encomenda();
            e.setID(rs.getInt("id_encomenda"));
            e.setFuncionario(rs.getInt("funcionario"));
            e.setCliente(rs.getInt("cliente"));

            // falta ir buscar pacotes e componentes (fiz um bcd no DAO do Pacote)

            r.add(e);

            System.out.println(e.getID()); // FIXME: 12/22/2018 DEBUGGING
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