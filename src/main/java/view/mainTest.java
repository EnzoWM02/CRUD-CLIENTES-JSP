package view;

import controller.ClienteDao;
import model.Cliente;

public class mainTest {

    public static void main(String[] args) {
        ClienteDao dao = new ClienteDao();
        Cliente cli = dao.selectCliente(1);
        System.out.println(cli.getNome());
        Cliente cliAdicionar = new Cliente("enzoTeste", "enzo@gmail", "canada", "ativo");
        ClienteDao.save(cliAdicionar);
    }

}
