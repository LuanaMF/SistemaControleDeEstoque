package model;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Estoque, onde seu objeto será o produto (de maneira geral), contendo uma lista como atributo que conterá todos registros desse produto,
 * comprado com fornecedores para compor os itens do cardápio.
 * @author Luana de Melo Fraga
 *
 */
public class Estoque {
	private String id;
	private String nome;
	private double quantidadeTotal;
	private Map<String, String> fornecedores;
	private ObservableList<Produto> estoque = FXCollections.observableArrayList();
	private String doubleclickrequest;
	
	// coonstrutor
	public Estoque(String id,String nome, double quantidadeTotal, ObservableList<Produto> estoque, Map<String,String> fornecedores) {
		this.id = id;
		this.nome = nome;
		this.quantidadeTotal = quantidadeTotal;
		this.estoque = estoque;
		this.setFornecedores(fornecedores);
		this.setDoubleclickrequest("Clique duas vezes");
	}
	
	/**
	 * Método get usado exclusivamente para visualização do atributo "quantidadeTotal" do objeto 
	 * Estoque na TableView, onde, caso não haja estoque (lista de produtos do objeto estoque em questão estiver vazia), ira mostrar
	 * "0.0", caso contrario irá mostrar sua quantidade total em kilos.
	 * 
	 * @return - String quantidade, contendo a quantidade total em estoque.
	 */
	public String getQuantidadeTotal() {
		String quantidade = " ";
		if(this.getEstoque().isEmpty() && this.getEstoque().size() == 0) {
			quantidade = "0.0";
		}
		else {
			quantidade = GerenciadorEstoque.somaQuantidade(this.getEstoque()) /1000 + "kg";
		}
		return quantidade;
	}
	
	/**
	 * Método que retorna a quantidade total como double, e que também verifica se há estoque
	 * ( se a lista de produtos do objeto estoque em questão estiver vazia), e caso estiver, zera o
	 * atributo.
	 * @return - Quantidade total como double.
	 */
	public Double getQuantidadeTotalDouble() {
		if(this.getEstoque().isEmpty() || this.getEstoque().size() == 0) {
			this.quantidadeTotal = 0.0;
		}
		return quantidadeTotal;
	}
	// getters e setters padrões
	public void setQuantidadeTotal(double quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ObservableList<Produto> getEstoque() {
		return estoque;
	}
	public void setEstoque(ObservableList<Produto> estoque) {
		this.estoque = estoque;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, String> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(Map<String, String> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public String getDoubleclickrequest() {
		return doubleclickrequest;
	}
	public void setDoubleclickrequest(String doubleclickrequest) {
		this.doubleclickrequest = doubleclickrequest;
	}
	/**
	 *  Método Override, usado para visualizar um objeto (dessa classe) e seus atributos.
	 * @return um <code>string</code> contendo o "modelo" de print
	 */
	public  String toString(){	
        return   "\nId: " + getId() + "\nNome: " + getNome() + "\nQuantidade: " + getQuantidadeTotal();
	}
	
	
}

