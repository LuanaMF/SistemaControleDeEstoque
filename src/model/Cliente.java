package model;

import controller.Main;
/**
 * Classe da entidade Cliente, com os atributos: nome, cpf, email e telefone. Nela contem seu construtor
 * e os métodos de atualizar e adicionar um novo cliente.
 * @author Luana de Melo Fraga
 *
 */
public class Cliente {
	
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String id;
	
	// construtor
	public Cliente(String nome, String cpf, String email, String telefone, String id) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.setId(id);
	}
    
	// getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	@Override
	public  String toString(){	
        return   "\nNome: " + getNome() + "\nCPF: " + getCpf() + "\nEmail: " + getEmail() + "\nTelefone: " + getTelefone();
	}

	/**
	 * Dado um objeto cliente, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @param cliente - Objeto cliente a ser atualizado.
	 * @param nome - Nome do cliente.
	 * @param cpf - CPF do cliente.
	 * @param email - Email do cliente.
	 * @param telefone - Telefone do cliente.
	 */
	public void atualizarCliente(Cliente cliente, String nome, String cpf, String email, String telefone) {
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
	}
	
	/**
	 * Dada as informações necessárias (nome, cpf, email e telefone), esse método ira criar um novo
	 * objeto cliente, e logo depois adiciona-lo na lista clientes.
	 * @see BancoDados
	 * @param nome - Nome do novo cliente.
	 * @param cpf -  CPF do novo cliente.
	 * @param email -  Email do novo cliente.
	 * @param telefone -  Telefone do novo cliente.
	 */
	public static void adicionarCliente(String nome, String cpf, String email, String telefone) {
		String id = Main.id(BancoDados.getInstance().getListaClientes(), "cliente");
		Cliente cliente = new Cliente(nome, cpf, email, telefone, id);
		BancoDados.adicionaEmLista(BancoDados.getInstance().getListaClientes(), cliente);
	}
}
