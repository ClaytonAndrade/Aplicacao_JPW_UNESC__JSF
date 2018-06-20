package br.com.projetofinaljpw.bean;

import br.com.projetofinaljpw.dao.FornecedoresDAO;
import br.com.projetofinaljpw.domain.Fornecedores;
import br.com.projetofinaljpw.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/*@author Andrade 19/11/2017 */
@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

    private Fornecedores fornecedor;
    private ArrayList<Fornecedores> itens;
    private ArrayList<Fornecedores> itensFiltrados;

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ArrayList<Fornecedores> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Fornecedores> itens) {
        this.itens = itens;
    }

    public ArrayList<Fornecedores> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public FornecedoresBean() {
    }

    @PostConstruct
    public void prepararPesquisa() {
        try {
            FornecedoresDAO forDao = new FornecedoresDAO();
            itens = forDao.listar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    public void prepararNovo() {
        fornecedor = new Fornecedores();
    }

    public void novo() {
        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            fdao.salvar(fornecedor);
            atualizarLista(fdao);
            JSFUtil.mensagenSucesso("Forncedor cadastrado com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    public void excluir() {
        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            fdao.excluir(fornecedor);
            atualizarLista(fdao);
            JSFUtil.mensagenSucesso("Forncedor removido com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    public void editar() {
        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            fdao.editar(fornecedor);
            atualizarLista(fdao);
            JSFUtil.mensagenSucesso("Forncedor editado com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.mensagenErro(ex.getMessage());
        }
    }

    private void atualizarLista(FornecedoresDAO fdao) throws SQLException {
        itens = fdao.listar();
    }

}
