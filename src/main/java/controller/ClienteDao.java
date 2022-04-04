package controller;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private static final String SELECT_CLIENTE_POR_ID = "select id,nome,email,pais,situacao from cliente where id =?";
    private static final String DELETE_USERS_SQL = "delete from cliente where id = ?;";

    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost/jspbase?user=postgres&password=postgres");
            System.out.println("conexão funcionando" + con);
        } catch(Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public static int save (Cliente e){
        int status=0;
        try{
            Connection con= ClienteDao.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into cliente (nome, email, pais, situacao) values (?,?,?,?)");
            ps.setString(1,e.getNome());
            ps.setString(2,e.getEmail());
            ps.setString(3,e.getPais());
            ps.setString(4,e.getSituacao());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    public static List<Cliente> readAll(){
        List<Cliente> list=new ArrayList<Cliente>();

        try{
            Connection con= ClienteDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from cliente ORDER BY id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Cliente e = new Cliente(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5));
                list.add(e);
                System.out.println(rs.getString(2));
            }
            con.close();
        } catch(Exception e){e.printStackTrace();}

        return list;
    }

    public static int update(Cliente e){
        int status=0;
        try{
            Connection con= ClienteDao.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update cliente set nome=?, email=?, pais=?, situacao=? where id=?");
            ps.setString(1,e.getNome());
            ps.setString(2,e.getEmail());
            ps.setString(3,e.getPais());
            ps.setString(4,e.getSituacao());
            ps.setInt(5,e.getId());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    public Cliente selectCliente(int id) {
        Cliente cliente = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENTE_POR_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String pais = rs.getString("pais");
                String situacao = rs.getString("situacao");
                cliente = new Cliente(id, nome, email, pais, situacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cliente;
    }

    public static boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
