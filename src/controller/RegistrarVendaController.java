package controller;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.BancoDados;
import model.Cliente;
import model.ExpressoesRegulares;
import model.GerenciadorEstoque;
import model.GerenciadorVenda;
import model.Item;

/**
 * Classe controller da tela "RegistrarVenda" responsável por gerenciar todas ações do usuário que acontecem(ou devem acontecer) nela.
 * @author Luana de Melo Fraga
 *
 */
public class RegistrarVendaController {
	
	public RegistrarVendaController() {
		
	}
	
	
	ObservableList<String> modos = FXCollections.observableArrayList("Dinheiro", "Pix", "Cartão de débito", "Cartão de Crédito");
	// elementos da tela
	@FXML
	private TextField idItem;
	@FXML
	private Button adicionarItens;
	@FXML
	private Button registraVenda;
	@FXML
	private ListView<Item> listItensVendidos;
	@FXML 
	private Label msgErro;
	@FXML 
	private Label somaCompra;
	@FXML
	private Button cancelarRegistro;
	@FXML
	private Button mode;
	@FXML
	private AnchorPane rightAnchor;
	@FXML
	private ChoiceBox<String> modoPagamento;
	@FXML
	private TextField idCliente;
	
	/**
	 * Método padrão initialize, onde, assim que o arquivo "fxml" que essa classe controla for carregado(iniciado), ele será executado.
	 * Nele é carregado o elemento ChoiceBox com os modos de pagamento possíveis.
	 */
	@FXML
	private void initialize() {
		modoPagamento.setItems(modos);
	}

	/**
	 * Acionado ao clicar no botão de "Adicionar", este método adiciona um item a ListView de itens vendidos na compra em questão.
	 */
	@FXML
	public void preencherItens() {
		
		msgErro.setText("");
		boolean validaIdItem = ExpressoesRegulares.getInstance().matchesIdItem(idItem.getText());
		if(validaIdItem) {
			int id = Main.buscaPorId(BancoDados.getInstance().getCardapio(), idItem.getText());
			if(id == -1) {
				msgErro.setTextFill(Color.RED);
				msgErro.setText("Item não encontrado! Verifique o id digitado.");
			}
			else {
				adicionaItem(id);
			}
		}
		else {
			msgErro.setText("Id de Item inválido!");
		}
		
	} 
	
	/**
	 * Acionado ao clicar no botão de "Registrar Venda", este método é responsável por, primeiramente, checar se todas informações
	 * estão corretas e depois chamar o método que criará um novo objeto do tipo venda.
	 * @throws ParseException
	 */
	public void registrarVenda() throws ParseException{
		
		msgErro.setText(" ");
		boolean tudoCerto = verificaInfomacoesVenda();
		
		if(tudoCerto) {
			
			List<String> estoqueInsuficiente = GerenciadorEstoque.descontaEstoqueLista(listItensVendidos.getItems());
			
			if(estoqueInsuficiente == null){
				criaVenda();
				msgErro.setTextFill(Color.GREEN);
				msgErro.setText("Compra registrada com sucesso!");
				removeTudo();
			}
			else if(estoqueInsuficiente.size() > 0 && estoqueInsuficiente != null) {
				Main.informationAlert("A venda não foi concluída porque o estoque é insuficiente"
						+ " para os seguintes itens: " + estoqueInsuficiente);
			}
		}
	}
	/**
	 * Acionado ao clicar no botão de "Cancelar", nele é chamado o método de remover tudo, ou seja, deixa todos os campos vazios.
	 */
	public void cancelaVenda() {
		removeTudo();
	}
	
	

	
	
	
	/**
	 * Remove tudo o que foi digitado/selecionado/adicionado, deixando todos os campos da tela vazios.
	 */
	private void removeTudo() {
		listItensVendidos.getItems().clear();
		modoPagamento.setValue("");
		idItem.setText("");
		somaCompra.setText("");
	}
	
	/**
	 * Método responsável por chamar e preecher os requisitos do método da classe gerenciadora de vendas, onde contém
	 * a função de cadastrar uma venda, ou seja, este método cria um novo objeto do tipo venda.
	 * @throws ParseException
	 */
	private void criaVenda() throws ParseException {
		
	
		Calendar c = Calendar.getInstance();
		Date data_horario = c.getTime();
		NumberFormat formata = NumberFormat.getCurrencyInstance();
		Double preco = formata.parse(somaCompra.getText()).doubleValue();
		ObservableList<Item> itens = FXCollections.observableArrayList(listItensVendidos.getItems());
		Cliente cliente;
		if(idCliente.getText().isEmpty() || idCliente.getText().isBlank() ) {
			cliente = null;
		}
		if(!idCliente.getText().isBlank() && !idCliente.getText().isEmpty()) {
			int index = Main.buscaPorId(BancoDados.getInstance().getListaClientes(), idCliente.getText());
			if(index == -1) {
				cliente = null;
			}
			else {
				cliente = BancoDados.getInstance().getListaClientes().get(index);
			}
		}
		else {
			cliente= null;
		}
		
		GerenciadorVenda.cadastrarVEN(data_horario, itens, preco, modoPagamento.getValue().toString(), cliente);
	}
	/**
	 * Método usado para inserir um objeto item na ListView de itens vendidos.
	 * @param id - Id do item a ser adicionado.
	 */
	private void adicionaItem(int id) {
		Item itemVendido = BancoDados.getInstance().getCardapio().get(id);
		listItensVendidos.getItems().add(itemVendido);
	    somaCompra.setText(somaPreco(listItensVendidos.getItems()));
	}
	/**
	 * Recebe uma lista de itens e soma o preço de cada um, retornando, no final, seu total.
	 * @param lista - Lista de itens.
	 * @return um <code>double</code> com o valor total da soma
	 */
	private static String somaPreco(List<Item> lista){
        double total = 0;
        for(Item obj : lista) {
            total += obj.getPrecoDouble();
        } 
        return NumberFormat.getCurrencyInstance().format(total);
    }
	  /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a venda. 
     * Verifica se há algum campo vazio, e se todos formatos estão corretos (id do Cliente).
     * @see ExpressoesRegulares
     * @return um boolean que será true se todas informações estiverem corretas, e false se ocorrer o contrário.
     */
    private boolean verificaInfomacoesVenda() {
    	boolean tudoCerto = false;
    	
    	boolean tudoPreenchido = !listItensVendidos.getItems().isEmpty() && modoPagamento.getValue() != null && !modoPagamento.getValue().isEmpty();
    	boolean validaIdCliente = true;
    	if(!idCliente.getText().isBlank() && !idCliente.getText().isEmpty()) {
    		validaIdCliente = ExpressoesRegulares.getInstance().matchesIdCliente(idCliente.getText());
    	}
    	
    	if(tudoPreenchido && validaIdCliente) {
    		tudoCerto = true;
    	}
    	else if(!tudoPreenchido) {
    		tudoCerto = false;
    		msgErro.setText("Todos campos devem estar preenchidos para registrar a venda!");
    		msgErro.setTextFill(Color.RED);
    		msgErro.setVisible(true);
    	}
    	else if(!validaIdCliente) {
    		tudoCerto = false;
    		msgErro.setText("Id de cliente inválido! Verifique se o id é correspondente a um cliente");
    		msgErro.setTextFill(Color.RED);
    		msgErro.setVisible(true);
    	}
    	return tudoCerto;
    }
	
}
