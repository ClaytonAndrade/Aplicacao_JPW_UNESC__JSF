package br.com.projetofinaljpw.dao;

import br.com.projetofinaljpw.domain.Fornecedores;
import br.com.projetofinaljpw.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*@author Andrade 18/11/2017*/
public class FornecedoresDAO {

    public void salvar(Fornecedores fornecedores) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO FORNECEDORES ");
        sql.append("(descricao) ");
        sql.append("VALUES (?)");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, fornecedores.getDescricao());

        comando.executeUpdate();
    }

    public void excluir(Fornecedores fornecedores) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM FORNECEDORES ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setLong(1, fornecedores.getCodigo());

        comando.executeUpdate();
    }

    public void editar(Fornecedores fornecedores) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE FORNECEDORES ");
        sql.append("SET descricao = ? ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, fornecedores.getDescricao());
        comando.setLong(2, fornecedores.getCodigo());

        comando.executeUpdate();
    }

    public Fornecedores buscarPorCodigo(Fornecedores fornecedores) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM FORNECEDORES ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setLong(1, fornecedores.getCodigo());

        ResultSet resultSet = comando.executeQuery();

        Fornecedores retorno = null;

        if (resultSet.next()) {
            retorno = new Fornecedores();
            retorno.setCodigo(resultSet.getLong("codigo"));
            retorno.setDescricao(resultSet.getString("descricao"));
        }
        return retorno;
    }

    public ArrayList<Fornecedores> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM FORNECEDORES ");
        sql.append("ORDER BY descricao ASC ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());

        ResultSet resultSet = comando.executeQuery();

        ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

        while (resultSet.next()) {
            Fornecedores fornecedores = new Fornecedores();
            fornecedores.setCodigo(resultSet.getLong("codigo"));
            fornecedores.setDescricao(resultSet.getString("descricao"));

            lista.add(fornecedores);
        }
        return lista;
    }

    public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores fornecedores) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM FORNECEDORES ");
        sql.append("WHERE descricao LIKE ? ");
        sql.append("ORDER BY descricao ASC ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, "%" + fornecedores.getDescricao() + "%");

        ResultSet resultSet = comando.executeQuery();

        ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

        while (resultSet.next()) {
            Fornecedores f = new Fornecedores();
            f.setCodigo(resultSet.getLong("codigo"));
            f.setDescricao(resultSet.getString("descricao"));

            lista.add(f);
        }
        return lista;

    }

    public static void main(String[] args) {
       /* Fornecedores f1 = new Fornecedores();
        f1.setDescricao("Descrição 11");
        
        Fornecedores f2 = new Fornecedores();
        f2.setDescricao("Descrição 12");
        
        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
        
        try {
            fornecedoresDAO.salvar(f1);
            fornecedoresDAO.salvar(f2);
            System.out.println("Os fabricantes foram salvos com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar um dos fornecedores!");
            ex.printStackTrace();
        }*/

 /*Fornecedores f1 = new Fornecedores();
        f1.setCodigo(2L);
        
        Fornecedores f2 = new Fornecedores();
        f2.setCodigo(1L);
        
        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
        
        try {
            fornecedoresDAO.excluir(f1);
            fornecedoresDAO.excluir(f2);
            System.out.println("Os fabricantes foram removidos com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar remover um dos fornecedores!");
        }*/
 /*Fornecedores f1 = new Fornecedores();
        f1.setCodigo(6L);
        f1.setDescricao("DESCRIÇÃO 3");

        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();

        try {
            fornecedoresDAO.editar(f1);
            System.out.println("O fabricante foi editado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar editar um dos fornecedores!");
        }*/
 /*Fornecedores f1 = new Fornecedores();
        f1.setCodigo(5L);

        Fornecedores f2 = new Fornecedores();
        f2.setCodigo(3L);

        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();

        try {
            Fornecedores f3 = fornecedoresDAO.buscarPorCodigo(f1);
            Fornecedores f4 = fornecedoresDAO.buscarPorCodigo(f2);
            System.out.println("Resultado 1 " + f3);
            System.out.println("Resultado 2 " + f4);
        } catch (SQLException ex) {
            System.out.println("Select não realizado!");
            ex.printStackTrace();
        }*/
        /*FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
        try {
            ArrayList<Fornecedores> lista = fornecedoresDAO.listar();
            for (Fornecedores fornecedores : lista) {
                System.out.println("Resultado: " + fornecedores);
            }
        } catch (SQLException ex) {
            System.out.println("Select não realizado!");
            ex.printStackTrace();;
        }*/
        /*Fornecedores f1 = new Fornecedores();
        f1.setDescricao("3");
        
        FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
        try {
            ArrayList<Fornecedores> lista = fornecedoresDAO.buscarPorDescricao(f1);
            for (Fornecedores fornecedores : lista) {
                System.out.println("Resultado: " + fornecedores);
            }
        } catch (SQLException ex) {
            System.out.println("Select não realizado!");
            ex.printStackTrace();;
        }*/
    }
}
