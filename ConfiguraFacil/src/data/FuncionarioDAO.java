package data;

import business.ConfiguraFacil;
import business.gConta.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {


	public void put(int id, Funcionario f) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("INSERT INTO funcionario " +
                                "VALUES (?, ?, ?, ?, ?, ?) " +
                                "ON DUPLICATE KEY UPDATE nome = ?, telemovel = ?, email = ?, tipo = ?, password = ?;");
        st.setInt(1, id);
        st.setString(2, f.getNome());
        st.setInt(3, f.getTelemovel());
        st.setString(4, f.getEmail());
        st.setInt(5, f.getTipo());
        st.setString(6, f.getPassword());

        st.setString(7, f.getNome());
        st.setInt(8, f.getTelemovel());
        st.setString(9, f.getEmail());
        st.setInt(10, f.getTipo());
        st.setString(11, f.getPassword());

        st.execute();

        c.close();
    }

    public Funcionario get(int id) throws Exception {
        Funcionario f;

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("SELECT * FROM funcionario WHERE id_funcionario = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            f = new Funcionario();
            f.setID(rs.getInt("id_funcionario"));
            f.setNome(rs.getString("nome"));
            f.setPassword(rs.getString("password"));
            f.setTelemovel(rs.getInt("telemovel"));
            f.setEmail(rs.getString("email"));
            f.setTipo(rs.getInt("tipo"));
        }
        else throw new Exception("Funcionário não encontrado");

        c.close();

        return f;
    }

    public boolean containsFunc(int id){
        try{
            Funcionario f = this.get(id);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    public List<Funcionario> list() throws Exception {
        List<Funcionario> r = new ArrayList<>();
	    Funcionario f;

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("SELECT * FROM funcionario;");

        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            f = new Funcionario();
            f.setID(rs.getInt("id_funcionario"));
            f.setNome(rs.getString("nome"));
            f.setPassword(rs.getString("password"));
            f.setTelemovel(rs.getInt("telemovel"));
            f.setEmail(rs.getString("email"));
            f.setTipo(rs.getInt("tipo"));

            r.add(f);
        }

        c.close();

        return r;
    }


    public void remove(int id) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("DELETE FROM funcionario WHERE id_funcionario = ?;");
        st.setInt(1, id);

        st.execute();

        c.close();
    }


    public int size() throws SQLException {
        int r = 0;
	    Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("SELECT MAX(id_funcionario) FROM funcionario;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            r = Integer.parseInt(rs.getString(1));
        }
        c.close();

        return r;
    }



}