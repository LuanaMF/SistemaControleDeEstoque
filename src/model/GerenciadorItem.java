package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import controller.Main;
import javafx.collections.ObservableList;

/**
 * Classe Gerenciadora de Item, contendo os métodos de adicionar e atualizar item.
 * @see Item
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorItem {
	
	/**
	 * Cadastra um objeto do tipo Item e o adiciona ee o adiciona em sua lista(que nesse caso é o cardápio).Seu id é atribuído usando o método da classe
	 * 'MainView' (id).
	 * @see BancoDados
	 * @see Main
	 * @param nome - Atributo nome do novo item.
	 * @param sobre - Atributo descrição do novo item.
	 * @param preco - Atributo preço do novo item.
	 * @param categoria - Atributo categoria do novo item.
	 * @param produtosNecessarios - Atributo produtosNecessários do novo item.
	 * @throws ParseException 
	 */ 
	public static void cardapio(String nome, String sobre, String preco, String categoria, Map<String,Double> produtosNecessarios) throws ParseException {
		ObservableList <Item> lista = BancoDados.getInstance().getCardapio();
		String id = Main.id(lista, "itens");
		DecimalFormat format = new DecimalFormat("##.##");
		format.setParseBigDecimal(true);
		BigDecimal decimal = (BigDecimal) format.parse(preco);
		Item cadasITE = new Item(nome, sobre, decimal.doubleValue(), categoria, id, produtosNecessarios);
		BancoDados.adicionaEmLista(lista, cadasITE);
	}
	
	/**
	 * Dado um objeto Item, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @see DecimalFormat
	 * @param item - Objeto item a ser atualizado.
	 * @param nome - Atributo nome do item.
	 * @param descricao - Atributo descrição do item.
	 * @param categoria - Atributo categoria do item.
	 * @param preco - Atributo preço do item.
	 * @param produtosNecessarios - Atributo produtosNecessários do item.
	 * @throws ParseException
	 */
	public static void editarITE(Item item, String nome, String descricao, String categoria, String preco, Map<String, Double> produtosNecessarios) throws ParseException {
		item.setNome(nome);
		item.setSobre(descricao);
		item.setCategoria(categoria);
		
		DecimalFormat format = new DecimalFormat("##.##");
		format.setParseBigDecimal(true);
		BigDecimal decimal = (BigDecimal) format.parse(preco);
		item.setPreco(decimal.doubleValue());
		
		item.setProdutosNecessarios(produtosNecessarios);
	}
}



