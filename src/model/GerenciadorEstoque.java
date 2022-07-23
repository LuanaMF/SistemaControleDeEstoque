package model;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GerenciadorProduto.OrdenaPorData;
/**
 * Classe Gerenciadora do estoque, contendo os métodos de adicionar, atualizar e descontar estoque (entre outros méodos relacionados).
 * @see Estoque
 * @author Luana de Melo Fraga.
 *
 */
public class GerenciadorEstoque {

	/**
	 * Cadastra um objeto do tipo Estoque e o adiciona em sua lista (listaEstoque). Seu id e quantidade total são atribuidos usando os métodos
	 * de criar id e de somar a quantidade total do produto presente no atributo estoque, respectivamente.
	 * @see BancoDados
	 * @see Main
	 * @param nome - Atributo nome do novo produto estoque.
	 * @param estoque - Atributo lista de produtos do novo estoque.
	 * @param fornecedores - Atributo fonecedores, que armazena os fornecedores do novo estoque..
	 * @throws ParseException 
	 */
	public static void cadastrarProdutoEstoque(String nome, ObservableList<Produto> estoque, Map<String, String> fornecedores) throws ParseException {
		ObservableList <Estoque> lista = BancoDados.getInstance().getListaEstoque();
		String id = Main.id(lista, "estoque");
		double quantidadeTotal = somaQuantidade(estoque);
		Estoque cadasPRO = new Estoque(id,nome, quantidadeTotal, estoque, fornecedores);
		BancoDados.adicionaEmLista(lista, cadasPRO);
	}
	/**
	 * Dado um objeto Estoque, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @param nome - Nome do estoque(objeto).
	 * @param listaEstoque - Lista de produtos daquele estoque
	 * @param fornecedores - Mapa de fornecedores daquele estoque.
	 */
	public static void editarEST(Estoque estoque, String nome, ObservableList<Produto> listaEstoque, Map<String, String> fornecedores ) throws ParseException {
		estoque.setNome(nome);
		estoque.setQuantidadeTotal(somaQuantidade(estoque.getEstoque()));
		estoque.setEstoque(listaEstoque);
		estoque.setFornecedores(fornecedores);
	}
	
	/**
	 * Recebe uma lista de produtos e soma a quantidade de cada um, retornando, no final, sua quantidade total.
	 * @param lista - Lista de produtos.
	 * @return um <code>double</code> com o valor total da soma
	 */
	public static double somaQuantidade(List<Produto> lista){
        double total = 0;
        for(Produto obj : lista) {
            total += obj.getQuantidadeDouble();
        } 
        return total;
    }
	/**
	 * Método responsável por atualizar(descontar) a quantidade do estoque, dado um item, ultilizando o mapa de produtos necessários desse item como referência.
	 * Caso, ao tentar descontar o estoque referente ao item, não haver estoque o suficiente, será retornado seu id.
	 * @param item - Item vendido que, no método, pegando o seu mapa de produtos necessários, atualizará o estoque.
	 * @return uma string, que se o estoque for suficiente, retorna null, caso contrário, retorna o id do item.
	 */
	public static String descontaEstoque(Item item) {
		
		String idEstoqueInsuficiente = null;
		ObservableList<Estoque> listaEstoque = BancoDados.getInstance().getListaEstoque();
		ObservableList<Produto> produtos = BancoDados.getInstance().getListaPRO();
		Map<String, Double> referencia = item.getProdutosNecessarios(); // pega a lista de produtos necessários para aquele item
		boolean isEstoqueSuficiente = isEstoqueSuficiente(referencia);;
		int indexPRO = 0;
		ObservableList<Produto> estoque = FXCollections.observableArrayList(); // cria a lista que guardará a lista de estoque
		if(isEstoqueSuficiente) {
			for (String id : referencia.keySet()) { // percorre o mapa de produtos necessários
				indexPRO = Main.buscaPorId(listaEstoque, id);
				estoque = listaEstoque.get(indexPRO).getEstoque(); // pega a lista para descontar
				Collections.sort(estoque, new OrdenaPorData()); // ordena por data de validade
				double quantidadeADescontar = referencia.get(id); 
				while(quantidadeADescontar > 0) { // certifica de que sera descontado tudo
					if(quantidadeADescontar >= estoque.get(0).getQuantidadeDouble()) {
						quantidadeADescontar -= estoque.get(0).getQuantidadeDouble(); 
						estoque.remove(0);
					}
					else {
						double quantidadeAtual = estoque.get(0).getQuantidadeDouble();
						double novaQuantidade = quantidadeAtual -= quantidadeADescontar;
						quantidadeADescontar = 0;
						estoque.get(0).setQuantidade(novaQuantidade);
						listaEstoque.get(indexPRO).setEstoque(estoque); // atualiza a lista de estoque daquele produto
						double novaQuantidadeTotal = somaQuantidade(listaEstoque.get(indexPRO).getEstoque());
						listaEstoque.get(indexPRO).setQuantidadeTotal(novaQuantidadeTotal); // atualiza a quantidade total daquele produto
						produtos.get(indexPRO).setQuantidade(novaQuantidade);
					}	
				}
			}
		}
		else {
			idEstoqueInsuficiente = item.getId();
		}
		return 	idEstoqueInsuficiente;
	}

	/**
	 * Usado dentro do método 'descontaEstoque', ele é responsável por verificar se há estoque o suficiente, dado um mapa de produtos a serem
	 * descontados. Se há estoque o suficiente é retonado true, caso contrário, é retornado false.
	 * @param mapa - Mapa contendo os produtos e a quantidade a ser descontada.
	 * @return um <code>boolean</code> indentificando se há estoque suficiente ou não.
	 */
	private static boolean isEstoqueSuficiente(Map<String,Double> mapa) {
		boolean isEstoqueSuficiente = true;
		
		ObservableList<Estoque> listaEstoque = BancoDados.getInstance().getListaEstoque();
		
		if(listaEstoque.size() != 0 && !listaEstoque.isEmpty()) {
			for (Map.Entry<String, Double> elemento : mapa.entrySet()) {
				
				int index = Main.buscaPorId(listaEstoque, elemento.getKey());
				double quantidadeTotal = listaEstoque.get(index).getQuantidadeTotalDouble();
				double quantidadeAdescontar = elemento.getValue();
				if(quantidadeTotal < quantidadeAdescontar) {
					isEstoqueSuficiente = false;
					break;
				}
			}
		}
		return isEstoqueSuficiente;
	}
	
	/**
	 * Dada uma lista de itens (itens vendidos), o método itera essa lista e chama o método "descontaEstoque" para cada item dentro dela.
	 * O método reune os ids de todos itens em que o estoque é insuficiente em uma lista(estoqueEmFalta) que no final será retornada. 
	 * Caso haja estoque para todos os itens, a lista(estoqueEmFalta) é retornada null.
	 * @param lista - Lista de itens em que sera iterada.
	 * @return uma lista do tipo string contendo os id dos itens com estoque insuficiente, ou null, caso haja estoque para todos elementos.
	 */
	public static List<String> descontaEstoqueLista(ObservableList<Item> lista) {
		List<String> estoqueEmFalta = new ArrayList<String>();
		for(Item item: lista) { 
			String idInsuficiente = descontaEstoque(item);
			if(idInsuficiente != null) {
				estoqueEmFalta.add(idInsuficiente);
			}
			else if (idInsuficiente == null){
				estoqueEmFalta = null;
			}
		}
		return estoqueEmFalta;
	}
}