package model;

/**
 * Classe da entidade funcionário (funcionários do estabelecimento, podendo ser gerente ou 'Outro').
 * @see Usuário
 * @author Luana de Melo Fraga.
 *
 */
public class Funcionario {
	private String id;
	private String nome;
	private String cargo;
	
	
	public Funcionario(String nome, String cargo, String id) {
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
	}
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Usado para printar um objeto dessa classe.
	 * @return um <code>string</code> contendo o "modelo" de print
	 */
	public  String toString(){
		return  "\nId do Funcion�rio: " + getId() + "\nNome: " + 
    		getNome() + "\nCargo: " + getCargo();
	}
}