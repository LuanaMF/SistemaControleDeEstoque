package model;

import controller.Main;
import javafx.collections.ObservableList;

/**
 * Classe Gerenciadora de Funcionário, contendo os métodos de adicionar e atualizar funcionário.
 * @see Funcionario
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorFuncionario {
	/**
	 * Cadastra um objeto do tipo Funcionário e o adiciona em sua lista(listaFUN), assim como atribui seu id  usando o método da classe
	 * 'MainView' (id) a depender do cargo do novo funcionário (gerente ou 'outro').
	 * @param nome - Atributo nome do novo funcionário.
	 * @param cargo - Atributo cargo do novo funcionário.
	 */
	public static void cadastrarFUN(String nome, String cargo) {
		ObservableList<Funcionario> lista = BancoDados.getInstance().getListaFUN();
		String id = null;
		if (cargo.equalsIgnoreCase("gerente")) {
			id = Main.id(lista, "Gerente");
		}else {
			id = Main.id(lista, "Outro");
		}
		Funcionario cadasFUN = new Funcionario(nome, cargo, id);
		BancoDados.adicionaEmLista(lista, cadasFUN); // adiciona na lista de funcionario
	}
	/**
	 * Dado um objeto Funcionário, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @param funcionario - Objeto Funcionário a ser atualizado.
	 * @param nome - Atributo nome do funcionário.
	 * @param cargo - Atributo cargo do funcionário.
	 */
	public static void editarFUN(Funcionario funcionario, String nome, String cargo) {
		funcionario.setNome(nome);
    	funcionario.setCargo(cargo);
	}
}
