
package model;

/**
 * Classe da entidade fornecedor (responsável por fornecer os produtos para compos os itens do prato).
 * @author Luana de Melo Fraga
 *
 */
public class Fornecedor {
	private String cnpj;
	private String nome;
	private String endereco;
	private String id;
	
	// constutor
	public Fornecedor(String cnpj, String nome, String endereco, String id) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.id = id;
	}
	
	// getters e setters
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCnpj() {
		return cnpj;
	}
	public String getNome() {
		return nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Método Override, usado para visualizar um objeto (dessa classe) e seus atributos.
	 * @return um <code>string</code> contendo o "modelo" de print
	 */
	public  String toString(){
        return  "\nId do Fornecedor: " + getId() + "\nNome: " + 
        		getNome() + "\nCNPJ: " + getCnpj() + "\nEndere�o: " + getEndereco();
        
	}
}
