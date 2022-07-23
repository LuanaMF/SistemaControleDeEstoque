package model;

import java.text.NumberFormat;
import java.util.Map;

/**
 * Classe da entidade Item (os itens irão compor cardápio).
 * @author Luana de Melo e Fraga
 *	
 */
public class Item {
	
	private String nome;
	private String sobre;
	private double preco;
	private String categoria;
	private String id;
	private Map<String, Double> produtosNecessarios;
	private String doubleclickrequest;
	
	// construtor
	public Item(String nome, String sobre, double preco, String categoria, String id, Map<String,Double> produtosNecessarios){
		this.nome = nome;
		this.sobre = sobre;
		this.preco = preco;
		this.categoria = categoria;
		this.id = id;
		this.produtosNecessarios = produtosNecessarios;
		this.setDoubleclickrequest("Clique duas vezes");
	}
	
	// getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	public String getPreco() {
		return NumberFormat.getCurrencyInstance().format(preco);
	}
	public Double getPrecoDouble() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public  Map<String,Double> getProdutosNecessarios() {
		return produtosNecessarios;
	}
	public void setProdutosNecessarios( Map<String,Double> produtosNecessarios) {
		this.produtosNecessarios = produtosNecessarios;
	}
	public String getDoubleclickrequest() {
		return doubleclickrequest;
	}
	public void setDoubleclickrequest(String doubleclickrequest) {
		this.doubleclickrequest = doubleclickrequest;
	}
	
	/**
	 * Método Override, usado para visualizar um objeto (dessa classe) e seus atributos.
	 * @return um <code>string</code> contendo o "modelo" de print
	 */
	@Override
	public String toString() {
		String printPreco = NumberFormat.getCurrencyInstance().format(getPrecoDouble()); // Formata para moeda
		return "ID do Item: " + getId() + " || " + "Nome do Item: " + getNome() + " || " + "Preço: " + printPreco  + " || " + "Categoria: " + getCategoria() + "\n";
	}
	
	
}
