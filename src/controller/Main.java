package controller;
	
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.BancoDados;
import model.Cliente;
import model.ExpressoesRegulares;
import model.GerenciadorEstoque;
import model.GerenciadorFornecedor;
import model.GerenciadorFuncionario;
import model.GerenciadorItem;
import model.GerenciadorProduto;
import model.GerenciadorUsuario;
import model.Item;
import model.Produto;
import model.Venda;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe main do projeto que o inicia, tanto em questões de interface, quanto em questões de código.
 * @author Luana de Melo Fraga
 *
 */
public class Main extends Application {
	
	private static Stage palco;
	
	/**
	 * Método padrão de uma aplicação java, que inicia a interface do programa.
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		palco = primaryStage;
		palco.setResizable(false);
		
		telaLogin();
		palco.show();
	}
	
	/**
	 * Método usado em todo o sistema para trocar a tela sendo exibida.
	 * @param fxml - Arquivo fxml que representa a nova tela a ser exibida.
	 * @throws IOException
	 */
	public void mudaTela(String fxml) throws IOException {
		Parent r = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(r,850,600);
		String css = this.getClass().getResource("/view/telaPrincipal.css").toExternalForm();
		String cssL = this.getClass().getResource("/view/light-theme.css").toExternalForm();
		scene.getStylesheets().add(css);
		scene.getStylesheets().add(cssL);
		palco.setScene(scene);
	}
	public Stage getPalco() {
		return palco;
	}
	/**
	 *Método que, quando chamado, volta a exibir a tela de login.
	 * @throws IOException
	 */
	public void telaLogin() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/xml/Login.fxml"));
		Scene scene = new Scene(root,700,600);
		String css = this.getClass().getResource("/view/login.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		palco.setScene(scene);
	}
	
	/**
	 * Método usado em todo o sistema que exibi um alerta (popUp) com as informações passadas.
	 * @param text - Informações a serem exibidas no alerta.
	 */
	public static void informationAlert(String text) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(null);
		a.setHeaderText(null);
		a.setContentText(text);
		a.show();
	}

	/**
	 * Cria o Id de cada objeto, usando as três primeiras letras de sua categoria + seu index na lista de objetos do seu tipo.
	 * @param lista - Lista de objetos 
	 * @param tipo - Categoria do objeto
	 * @return uma <code>string</code> contendo o id criado.
	 */
	
	public static String id(List<?> lista, String tipo) {
		String id = null;
		if(tipo == "venda") {
			if (lista.isEmpty()){
				id = ("VEN-0");
			}
			else {
				id = "VEN" + "-" + lista.size();	
			}
		}
		else if(tipo == "fornecedor") {
			if (lista.isEmpty()){
				id = ("FOR-0");
			}
			else {
				id = "FOR" + "-" + lista.size();	
			}	
		}
		else if(tipo == "itens") {
			if (lista.isEmpty()){
				id = ("ITE-0");
			}
			else {
				id = "ITE" + "-" + lista.size();	
			}
		}
		else if(tipo == "produto") {
			if (lista.isEmpty()){
				id = ("PRO-0");
			}
			else {
				id = "PRO" + "-" + lista.size();	
			}
		}
		else if(tipo == "estoque") {
			if (lista.isEmpty()){
				id = ("EST-0");
			}
			else {
				id = "EST" + "-" + lista.size();	
			}
		}
		else if(tipo == "Outro") {
			if (lista.isEmpty()){
				id = ("FUN-0");
			}
			else {
				id = "FUN" + "-" + lista.size();	
			}
		}
		else if(tipo == "Gerente"){
			if (lista.isEmpty()){
				id = ("GER-0");
			}
			else {
				id = "GER" + "-" + lista.size();	
			}
		}
		else if(tipo == "cliente"){
			if (lista.isEmpty()){
				id = ("CLI-0");
			}
			else {
				id = "CLI" + "-" + lista.size();	
			}
		}
		return id;
	}

	/**
	 * Transforma uma lista do tipo List a uma String.
	 * @param lista - Lista a ser passada para string
	 * @return um  <code>string</code> contendo a lista ja transformada.
	 */
	public static String listaString(List<?> lista) {
		String stri = "_______________________";
		
		
		for (Object object : lista) {
			stri += object + "\n_______________________";
			
			
		}
		return stri;
	}
	/**
	 * Transforma um Map de qualquer tipagem em uma String.
	 * @param mapa - Mapa a ser transformado em string.
	 * @param chave - Nome representando o que as chaves do mapa são
	 * @param valor - Nome representando o que os valores do mapa são
	 * @return um  <code>string</code> contendo o mapa ja transformado.
	 */
	public static String mapString(Map<?, ?> mapa, String chave, String valor) {
		String stri = " ";
		
		if(mapa.isEmpty()) {
			stri = null;
		}
		for (Entry<?, ?> pair : mapa.entrySet()) {
			
		    stri += chave + ": " + pair.getKey();
		    stri += " | ";
		    stri += valor + ": " + pair.getValue();
		    stri += "\n ";
		}
		
		
		return stri;
	}

	/**
	 * Recebe um id e um "dado", contendo o id e o tipo de resultado necessário - sendo index ou categoria - respectivamente, separa o id
	 * usando o "-" como referência, e, dependendo do tipo, retorna parte do id.
	 * @param id - Id a ser separado.
	 * @param dado - Variável especificando o tipo do resultado necessário
	 * @return uma variável contendo parte do id.
	 */
	public static String separaId(String id, String dado) {
		String str = null;
		String[] splitted = id.split("-");
		if (dado == "index" ) {
			str = splitted[1];
		}
		else if(dado == "categoria") {
			str = splitted[0];
		}
		return str.toUpperCase();
	}

	/**
	 * Faz a busca de um elemento, dada a lista e seu id, caso o elemento exista, é retornado seu index, caso contrário é retornado -1.
	 * @param lista - Lista onde será feito a busca.
	 * @param id - Id do elemento a ser buscado.
	 * @return um Integer contendo o index do elemento ou o número -1 indicando que ele não existe.
	 */
	public static int buscaPorId(List<?> lista, String id) {
		String string = separaId(id, "index");
		int index = Integer.parseInt(string);
		int foiEncontrado = index;
		if(lista.size() == 0 || lista.size() == index || lista.size() < index || lista.get(index) == null ) {
			foiEncontrado = -1;
		}
		return foiEncontrado;
	}
    /**
     * Método main padrão java, onde é iniciado programa em termos de código.
     * @param args
     * @throws ParseException
     */
	public static void main(String[] args) throws ParseException {
		
		//instancia de usuário
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		GerenciadorUsuario.cadastrarUsuario("GER-0", "1234"); // usuário inicial(admin)
		//GerenciadorUsuario.cadastrarUsuario("FUN-0", "123");
		
		//instancia de item do cardápio
		Map<String, Double> produtosNecessarios2 = new HashMap<String, Double>();
		produtosNecessarios2.put("EST-0", 500.0);
		
		BancoDados.getInstance().getCardapio().add(new Item("macarrao", "bom", 12.50, "sobremesa", "ite-0", produtosNecessarios2));
		GerenciadorItem.cardapio("Macarrão", "Delicioso macarrão alho e oléo com camarão", "29,90", "Massas", produtosNecessarios2);
		
		//instancia de venda
		ObservableList <Item> itens = FXCollections.observableArrayList();
		itens.add(BancoDados.getInstance().getCardapio().get(0));
		Cliente cliente1 = null;
		BancoDados.getInstance().getListaVEN().add(new Venda(formatar.parse("21/06/2022"), itens , 19.90, "Pix", "VEN-0", cliente1));
		
		//instancia de funcionario
		GerenciadorFuncionario.cadastrarFUN("Luana de Melo Fraga", "Gerente");
		
		//instancia de cliente
		Cliente cliente = new Cliente("Flávia", "08440164548", "flavia@gmail.com", "(75)91234-5678", "CLI-0");
		BancoDados.getInstance().getListaClientes().add(cliente);
		
		//instancia de fornecedor
		GerenciadorFornecedor.cadastrarFOR("12345678945612", "Matheus Silva", "Avenida A, Rua B, Bairro Flores");
		
		//instancia de produto
		Produto produto = new Produto("Leite", "PRO-0", 5000, 20.90, formatar.parse("21/06/2024"));
		BancoDados.getInstance().getListaPRO().add(produto);
		//instancia de Estoque
		Map<String, String> fornecedores = new HashMap<String, String>();
		fornecedores.put("for-0", "Matheus Silva");
		fornecedores.put("for-1", "Julia Santos");
		GerenciadorEstoque.cadastrarProdutoEstoque("Leite", BancoDados.getInstance().getListaPRO(), fornecedores);
		
		String id = "CLI-0";
		System.out.println(ExpressoesRegulares.getInstance().matchesIdCliente(id));
		
		launch(args);
	}
}
