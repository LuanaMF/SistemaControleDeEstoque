package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe de dados do sistema, implementado usando o padrão de design Singleton, onde limita a classe
 * em apenas uma instancia, que pode ser usada em todo o projeto. Nela há armazenada as listas de todas
 * entidades do sistema (Vendas, Fornecedor, Item, Estoque, Produto, Usuario, Funcionario e Cliente).
 * 
 * @author Luana de melo fraga
 *
 */
public final class BancoDados {
         
	private ObservableList<Venda> listaVEN = FXCollections.observableArrayList();
	private ObservableList<Fornecedor> listaFOR = FXCollections.observableArrayList();
	private ObservableList<Item> cardapio = FXCollections.observableArrayList();
	private ObservableList<Estoque> listaEstoque = FXCollections.observableArrayList();
	private ObservableList<Funcionario> listaFUN = FXCollections.observableArrayList();
	private ObservableList<Usuario> users = FXCollections.observableArrayList();
	private ObservableList<Produto> listaPRO = FXCollections.observableArrayList();
	private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
	private static BancoDados instance;
	
	// construtor privado
	private BancoDados() {
		
	}
	
	/**
	 * Método referente ao padrão Singleton. Nele é verificado, primeiramente, se a instancia (única)
	 * da classe ja foi criada, se não, ela é instanciada. Por fim é retornada a instancia.
	 * @return instancia única da classe.
	 */
	public static synchronized BancoDados getInstance() {
		if(instance == null) {
			instance = new BancoDados();
		}
		return instance;
	}
     // getters e setters
	public ObservableList<Venda> getListaVEN() {
		return listaVEN;
	}
	public void setListaVEN(ObservableList<Venda> listaVEN) {
		this.listaVEN = listaVEN;
	}
	public ObservableList<Fornecedor> getListaFOR() {
		return listaFOR;
	}
	public void setListaFOR(ObservableList<Fornecedor> listaFOR) {
		this.listaFOR = listaFOR;
	}
	public ObservableList<Item> getCardapio() {
		return cardapio;
	}
	public void setCardapio(ObservableList<Item> cardapio) {
		this.cardapio = cardapio;
	}
	public ObservableList<Estoque> getListaEstoque() {
		return listaEstoque;
	}
	public void setListaEstoque(ObservableList<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}
	public ObservableList<Funcionario> getListaFUN() {
		return listaFUN;
	}
	public void setListaFUN(ObservableList<Funcionario> listaFUN) {
		this.listaFUN = listaFUN;
	}
	public ObservableList<Usuario> getUsers() {
		return users;
	}
	public void setUsers(ObservableList<Usuario> users) {
		this.users = users;
	}

	public ObservableList<Produto> getListaPRO() {
		return listaPRO;
	}

	public void setListaPRO(ObservableList<Produto> listaPRO) {
		this.listaPRO = listaPRO;
	}

	public ObservableList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ObservableList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	/**
	 * Exclui um objeto específico, dada uma lista do seu tipo onde se encontra o objeto.
	 * @param lista - Lista onde se encontra o objeto.
	 * @param elemento - Objeto a ser excluído.
	 */
	public static void excluir(ObservableList<?> lista, Object elemento) {
		lista.remove(elemento);
	}
	
	/**
	 * Dado um objeto e uma lista do mesmo tipo, o objeto é inserido nesta lista.
	 * @param lista - Lista onde será inserido o objeto.
	 * @param elemento - Objeto que será inserido.
	 */
	public static void adicionaEmLista(ObservableList lista, Object elemento) {
		lista.add(elemento);
	}
	
	
	
	
}
