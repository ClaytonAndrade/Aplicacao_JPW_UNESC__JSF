package br.com.projetofinaljpw.dao;

import br.com.projetofinaljpw.domain.Fornecedores;
import br.com.projetofinaljpw.domain.Produtos;
import br.com.projetofinaljpw.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*@author Andrade 19/11/2017*/
public class ProdutosDAO {

    public void salvar(Produtos produtos) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PRODUTOS ");
        sql.append("(descricao, quantidade, preco, FORNECEDORES_codigo) ");
        sql.append("VALUES (?, ?, ?, ?)");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, produtos.getDescricao());
        comando.setLong(2, produtos.getQuantidade());
        comando.setDouble(3, produtos.getPreco());
        comando.setLong(4, produtos.getFornecedores().getCodigo());

        comando.executeUpdate();
    }

    public void excluir(Produtos produtos) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM PRODUTOS ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setLong(1, produtos.getCodigo());

        comando.executeUpdate();
    }

    public void editar(Produtos produtos) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PRODUTOS ");
        sql.append("SET descricao = ? , quantidade = ?, preco = ?, FORNECEDORES_codigo = ?");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, produtos.getDescricao());
        comando.setLong(2, produtos.getQuantidade());
        comando.setDouble(3, produtos.getPreco());
        comando.setLong(4, produtos.getFornecedores().getCodigo());
        comando.setLong(5, produtos.getCodigo());

        comando.executeUpdate();
    }

   
    public ArrayList<Produtos> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao ");
        sql.append("FROM PRODUTOS AS p ");
        sql.append("INNER JOIN FORNECEDORES AS f on f.codigo = p.FORNECEDORES_codigo ");

        Connection conexao = ConexaoFactory.conetar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());

        ResultSet resultSet = comando.executeQuery();

        ArrayList<Produtos> lista = new ArrayList<Produtos>();

        while (resultSet.next()) {
            Fornecedores fornecedores = new Fornecedores();
            fornecedores.setCodigo(resultSet.getLong("f.codigo"));
            fornecedores.setDescricao(resultSet.getString("f.descricao"));

            Produtos produtos = new Produtos();
            produtos.setCodigo(resultSet.getLong("p.codigo"));
            produtos.setDescricao(resultSet.getString("p.descricao"));
            produtos.setQuantidade(resultSet.getLong("p.quantidade"));
            produtos.setPreco(resultSet.getDouble("p.preco"));
            produtos.setFornecedores(fornecedores);

            lista.add(produtos);
        }
        return lista;
    }

    
}
