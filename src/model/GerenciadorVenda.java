package model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.Main;
import javafx.collections.ObservableList;

/**
 * Classe Gerenciadora de Venda, contendo os métodos de adicionar e atualizar venda.
 * @see Venda
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorVenda {
	/**
	 * Cadastra um objeto do tipo Venda, o adiciona em sua lista(listaVEN), e gera um documento com as informações da venda cadastrada.
	 * Seu id é atribuído usando o método da classe 'MainView' (id).
	 * @see BancoDados
	 * @see Main
	 * @see GerenciadorRelatorios
	 * @see DateFormat
	 * @param data_horario - Atributo Data e horario da nova compra.
	 * @param itens - Atributo lista de itens da nova compra.
	 * @param preco - Atributo preço da nova compra.
	 * @param pagamento - Atributo método de pagamento da nova compra.
	 * @param cliente - Atributo cliente da nova compra.
	 */
	public static void cadastrarVEN(Date data_horario, ObservableList <Item>itens, double preco, String pagamento, Cliente cliente) {
		ObservableList<Venda> lista = BancoDados.getInstance().getListaVEN();
		String id = Main.id(lista, "venda");
		Venda cadasVEN = new Venda(data_horario, itens, preco, pagamento, id, cliente);
		DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		GerenciadorRelatorios.geraPDF("Nota_Venda.pdf", cadasVEN.toString(), "Venda de: " + formataData.format(data_horario));
		BancoDados.adicionaEmLista(lista, cadasVEN);
	}
	/**
	 * Dado um objeto Venda, ele atualiza atributo por atributo do objeto, usando os dados fornecidos.
	 * @param venda - Objeto venda a ser atualizado.
	 * @param data - Data da compra.
	 * @param hora - Hora da compra.
	 * @param total - Atributo preço da compra.
	 * @param modoPagamento - Atributo método de pagamento da compra.
	 * @param cliente - Atributo cliente da compra.
	 * @param itens - Atributo lista de itens da compra.
	 * @throws ParseException.
	 */
	public static void editarVEN(Venda venda, String data, String hora, String total, String modoPagamento, Cliente cliente, ObservableList<Item> itens) throws ParseException{
		String dataEHora = data + " " + hora;
		DateFormat df = DateFormat.getDateTimeInstance();
		Date data_horario = df.parse(dataEHora);
		venda.setData_horario(data_horario);
		
		
		DecimalFormat format = new DecimalFormat("##.##");
		format.setParseBigDecimal(true);
		BigDecimal decimal = (BigDecimal) format.parse(total);
		venda.setPreco(decimal.doubleValue());
		
		venda.setPagamento(modoPagamento);
	    if(cliente != null) {
	    	venda.setCliente(cliente);
	    }
		
		venda.setItens(itens);

	}
	
}
