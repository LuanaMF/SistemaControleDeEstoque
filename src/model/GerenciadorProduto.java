package model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import controller.Main;
import javafx.collections.ObservableList;

/**
 * Classe Gerenciadora de Produto, contendo os métodos de adicionar e atualizar fornecedor.
 * @see Fornecedor
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorProduto {
	
	/**
	 * Classe que implementa comparator para fazer ordenação.
	 * @author Luana de Melo Fraga
	 *
	 */
	 static class OrdenaPorData implements Comparator<Produto> {
		 	
		 	/**
		 	 * Compara data por data, método usado juntamente com "Collenctions.sort()".
		 	 * @param a - Produto a ser comparado.
		 	 * @param b - Próximo produto a ser comparado.
		 	 */
			public int compare(Produto a, Produto b) {
				return a.getValidade().compareTo(b.getValidade());
			}
	 }
	 /**
	 * Cadastra um objeto do tipo Produto e o adiciona em sua lista(listaPRO).
	 * @param nome - Atributo nome do novo produto.
	 * @param quantidade - Atributo quantidade do novo produto.
	 * @param preco - Atributo preço do novo produto.
	 * @param validade - Atributo validade do novo produto.
	 * @throws ParseException 
	 */
	public static Produto cadastrarPRO(String nome, String quantidade,String preco, String validade) throws ParseException {
		
		ObservableList<Produto> lista = BancoDados.getInstance().getListaPRO();
		String id = Main.id(lista, "produto");
		DecimalFormat format = new DecimalFormat("##.##");
		format.setParseBigDecimal(true);
		BigDecimal decimal = (BigDecimal) format.parse(preco);
		double quantidadeDouble = Double.parseDouble(quantidade);
		DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formataData.parse(validade);
		Produto cadasEST = new Produto(nome, id, quantidadeDouble, decimal.doubleValue(), data);
		BancoDados.adicionaEmLista(lista, cadasEST);
		return cadasEST;
	}
	
	/**
	 * Dado um objeto Produto, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @param produto - Objeto produto a ser atualizado.
	 * @param nome - Atributo nome do produto.
	 * @param preco - Atributo preço do produto.
	 * @param validade - Atributo validade do produto.
	 * @param quantidade- Atributo quantidade do produto.
	 * @throws ParseException
	 */
    public static void editarPRO(Produto produto, String nome, String preco, String validade, String quantidade) throws ParseException {
    	
    	produto.setNome(nome);
    	DecimalFormat format = new DecimalFormat("##.##");
		format.setParseBigDecimal(true);
		BigDecimal decimal = (BigDecimal) format.parse(preco);
		produto.setPreco(decimal.doubleValue());
		
		DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formataData.parse(validade);
		produto.setValidade(data);
		
		double quantidadeDouble = Double.parseDouble(quantidade);
		produto.setQuantidade(quantidadeDouble);
		
		
    }
}