package model;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe da entidade produto. Os objetos dessa classe são usados para compor o atributo(lista)
 * estoque da classe Estoque.
 * @see Estoque
 * @author Luana de Melo Fraga
 *
 */
public class Produto {
	
	private String nome;
	private String id;
	private Date validade;
	private double quantidade;
	private double preco;
	
	//construtor
	public Produto(String nome, String id, double quantidade,double preco, Date validade) {
		this.setNome(nome);
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
		this.validade = validade;
	}
    
	/**
	 * Método get usado exclusivamente para visualização do atributo data do objeto Produto
	 * na TableView. Nele a data do objeto é passada para o formato padrão "dd/mm/yyyy"
	 * 
	 * @return String contendo a data já formatada.
	 */
	public String getValidade() {
		DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		String data = formataData.format(validade);
		return data;
	}
	
	//getters e setters
	public String getQuantidade() {
		return quantidade/ 1000 + "kg";
	}
	public Double getQuantidadeDouble() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getValidadeDate() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getPreco() {
		
		return NumberFormat.getCurrencyInstance().format(preco);
	}
    public double getPrecoDouble() {
		
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	
	/**
	 * Método Override, usado para visualizar um objeto (dessa classe) e seus atributos.
	 * @return um <code>string</code> contendo o "modelo" de print
	 */
	@Override
	public String toString() {
		DateFormat formataData = DateFormat.getDateInstance();
		return   "\nID: " + getId() + "\nNome: " + getNome() + "\nValidade: " + formataData.format(validade) + "\nQuantidade(em Kg/L): " + 0.001*getQuantidadeDouble();
	}
}