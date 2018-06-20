package br.com.projetofinaljpw.bean;

import br.com.projetofinaljpw.dao.FornecedoresDAO;
import br.com.projetofinaljpw.dao.ProdutosDAO;
import br.com.projetofinaljpw.domain.Fornecedores;
import br.com.projetofinaljpw.domain.Produtos;
import br.com.projetofinaljpw.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/*@author Andrade 19/11/2017*/
@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {

    private ArrayList<Produtos> itens;
    private ArrayList<Produtos> itensFiltrados;
    private ArrayList<Fornecedores> comboFornecedoreses;
    private Produtos produtos;

    public ArrayList<Fornecedores> getComboFornecedoreses() {
        return comboFornecedoreses;
    }

    public void setComboFornecedoreses(ArrayList<Fornecedores> comboFornecedoreses) {
        this.comboFornecedoreses = comboFornecedoreses;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Produtos> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Produtos> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Produtos> itens) {
        this.itens = itens;
    }

    public void carregarListagem() {
        try {
            ProdutosDAO pdao = new ProdutosDAO();
            itens = pdao.listar();
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.mensagenErro(e.getMessage());
        }
    }

    public ProdutosBean() {
    }

    public void prepararNovo() {
        try {
            produtos = new Produtos();

            FornecedoresDAO dao = new FornecedoresDAO();
            comboFornecedoreses = dao.listar();
        } catch (SQLException exception) {
            exception.printStackTrace();
            JSFUtil.mensagenErro(exception.getMessage());
        }
    }

    public void novo() {
        try {
            ProdutosDAO pdao = new ProdutosDAO();
            pdao.salvar(produtos);
            atualizarLista(pdao);
            JSFUtil.mensagenSucesso("Produto cadastrado com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    private void atualizarLista(ProdutosDAO pdao) throws SQLException {
        itens = pdao.listar();
    }

    public void excluir() {
        try {
            ProdutosDAO pdao = new ProdutosDAO();
            pdao.excluir(produtos);
            atualizarLista(pdao);
            JSFUtil.mensagenSucesso("Forncedor removido com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    public void prepararEditar() {
        try {
            FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
            comboFornecedoreses = fornecedoresDAO.listar();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    public void editar() {
        try {
            ProdutosDAO pdao = new ProdutosDAO();
            pdao.editar(produtos);
            atualizarLista(pdao);
            JSFUtil.mensagenSucesso("Produto editado com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }
}
