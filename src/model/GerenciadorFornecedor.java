package model;

import controller.Main;
import javafx.collections.ObservableList;

/**
 * Classe Gerenciadora de Fornecedor, contendo os métodos de adicionar e atualizar fornecedor.
 * @see Fornecedor
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorFornecedor {
	
	/**
	 * Cadastra um objeto do tipo Fornecedor e o adiciona ee o adiciona em sua lista (listaFOR).Seu id é atribuído usando o método da classe
	 * 'MainView' (id).
	 * @see BancoDados
	 * @see Main
	 * @param cnpj - Atributo CNPJ do novo fornecedor.
	 * @param nome - Atributo nome do novo fornecedor.
	 * @param endereco - Atributo endereço do novo fornecedor.
	 */
	public static void cadastrarFOR(String cnpj, String nome, String endereco) {
		ObservableList<Fornecedor> lista = BancoDados.getInstance().getListaFOR();
		String id = Main.id(lista, "fornecedor");
		Fornecedor cadasFOR = new Fornecedor(cnpj, nome, endereco, id);
		BancoDados.adicionaEmLista(lista, cadasFOR);
	}
	/**
	 * Dado um objeto Fornecedor, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @param fornedor - Objeto fornecedor a ser atualizado.
	 * @param nome - Atributo nome do fornecedor.
	 * @param cnpj - Atributo cnpj do fornecedor.
	 * @param endereco - Atributo endereço do fornecedor.
	 */
	public static void editarFOR(Fornecedor fornecedor, String nome, String cnpj, String endereco) {
		fornecedor.setNome(nome);
    	fornecedor.setCnpj(cnpj);
    	fornecedor.setEndereco(endereco);
	}
}
