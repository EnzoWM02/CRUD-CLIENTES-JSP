package controller;

import model.Cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class ClienteServlet extends HttpServlet {

    private ClienteDao dao;

    public ClienteServlet() {
        this.dao = new ClienteDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    mostrarNovoForm(request, response);
                    break;
                case "/insert":
                    inserir(request, response);
                    break;
                case "/edit":
                    mostrarFormEdicao(request, response);
                    break;
                case "/update":
                    atualizar(request, response);
                    break;
                case "/delete":
                    deletar(request, response);
                    break;
                default:
                    listarCliente(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void mostrarNovoForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
        dispatcher.forward(request, response);
    }

    private void inserir(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        String situacao = request.getParameter("situacao");
        Cliente cliente = new Cliente(nome, email, pais, situacao);
        ClienteDao.save(cliente);
        response.sendRedirect("listar");
    }

    private void mostrarFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cli = dao.selectCliente(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
        request.setAttribute("Cliente", cli);
        dispatcher.forward(request, response);
    }

    private void listarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cliente> listarCliente = ClienteDao.readAll();
        request.setAttribute("listarCliente", listarCliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-list.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        String situacao = request.getParameter("situacao");

        Cliente cli = new Cliente(id, nome, email, pais, situacao);
        ClienteDao.update(cli);
        response.sendRedirect("list");
    }

    private void deletar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ClienteDao.deleteUser(id);
        response.sendRedirect("list");

    }

}
