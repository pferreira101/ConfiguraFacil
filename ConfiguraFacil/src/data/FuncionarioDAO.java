package data;

import business.ConfiguraFacil;
import business.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
	public ConfiguraFacil ConfiguraFacil;


	public void put(int id, Funcionario f) throws SQLException, ClassNotFoundException {
        //Establish the connection
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("INSERT INTO funcionario VALUES (?, ?, ?, ?, ?);");
        st.setInt(1, id);
        st.setString(2, f.getNome());
        st.setInt(3, f.getTelemovel());
        st.setString(4, f.getEmail());
        st.setInt(5, f.getTipo());

        st.execute();

        c.close();
    }

    public Funcionario get(int id) throws Exception {
        Funcionario f;

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("SELECT * FROM funcionario WHERE id = ?;");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            f = new Funcionario();
            f.setNome(rs.getString("nome"));
            f.setPassword(rs.getString("password"));
            f.setTelemovel(rs.getInt("telemovel"));
            f.setEmail(rs.getString("email"));
            f.setTipo(rs.getInt("tipo"));
        }
        else throw new Exception("No user found for given mail");

        c.close();

        return f;
    }

    public List<Funcionario> list() throws Exception {
        List<Funcionario> r = new ArrayList<>();
	    Funcionario f;

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/configurafacil", "root", "12345");

        PreparedStatement st;
        st = c.prepareStatement("SELECT * FROM funcionario;");

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            f = new Funcionario();
            f.setID(rs.getInt("id_funcionario"));
            f.setNome(rs.getString("nome"));
            f.setPassword(rs.getString("password"));
            f.setTelemovel(rs.getInt("telemovel"));
            f.setEmail(rs.getString("email"));
            f.setTipo(rs.getInt("tipo"));

            r.add(f);

            System.out.println(f.getID()); // FIXME: 12/22/2018 DEBUGGING
            System.out.println(f.getNome()); // FIXME: 12/22/2018 DEBUGGING
            System.out.println(f.getPassword()); // FIXME: 12/22/2018 DEBUGGING
        }
        else throw new Exception("No user found for given mail");

        c.close();

        return r;
    }



}