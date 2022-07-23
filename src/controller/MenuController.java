package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import model.FacadeGerarRelatorio;
import model.Temp;

/**
 * Classe controller da tela principal, responsável por gerenciar todas ações do usuário no menu.
 * @author Luana de Melo Fraga
 *
 */
public class MenuController {
	
	// elementos da tela
	@FXML
	private AnchorPane rightAnchor;
	@FXML
	private ChoiceBox<String> modoPagamento;
	@FXML
	private ImageView imgMenu, imgTelaPrincipal, imgRegistrarVenda, imgRelatorios,imgExibir, imgConta;
	
	private Dialog<?> dlg = new Dialog<>();
	
	/**
	 * Método padrão initialize, onde, assim que o arquivo "fxml" que essa classe controla for carregado(iniciado), ele será executado.
	 * Nele é carregado as imagens em campos de ImageView.
	 */
	@FXML
	public void initialize() {
		Image imagemMenu = new Image(getClass().getResourceAsStream("/view/assets/menu.png"));
		Image imagTelaPrincipal = new Image(getClass().getResourceAsStream("/view/assets/estoque.png"));
		Image imagVenda = new Image(getClass().getResourceAsStream("/view/assets/carrinho.png"));
		Image imagConta = new Image(getClass().getResourceAsStream("/view/assets/cona.png"));
		Image imagRelatorio = new Image(getClass().getResourceAsStream("/view/assets/pdf.png"));
		Image imagExibir = new Image(getClass().getResourceAsStream("/view/assets/exibir.png"));
		imgTelaPrincipal.setImage(imagTelaPrincipal);
		imgMenu.setImage(imagemMenu);
		imgRegistrarVenda.setImage(imagVenda);
		imgConta.setImage(imagConta);
		imgRelatorios.setImage(imagRelatorio);
		imgExibir.setImage(imagExibir);
	}

	/**
	 * Acionada em clicar em Exibir -> Vendas
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de venda deverá ser exibida.
	 */
	public void listarVenda() {
		
		Temp.getInstance().setQualTableViewExibir("Vendas");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
	}
	/**
	 * Acionada em clicar em Exibir -> Funcionarios
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de funcionários deverá ser exibida.
	 */
	public void listarFuncionarios() {
		
		Temp.getInstance().setQualTableViewExibir("Funcionarios");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
	}
	/**
	 * Acionada em clicar em Exibir -> Usuarios
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de usuários deverá ser exibida.
	 */
	public void listarUsuarios() {
		
		Temp.getInstance().setQualTableViewExibir("Usuarios");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
	}
	/**
	 * Acionada em clicar em Exibir -> Clientes
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de clientes deverá ser exibida.
	 */
	public void listarClientes(){
		Temp.getInstance().setQualTableViewExibir("Clientes");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
		
	}
	/**
	 * Acionada em clicar em Exibir -> Fornecedores
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de fornecedores deverá ser exibida.
	 */
	public void listarFornecedores(){
		Temp.getInstance().setQualTableViewExibir("Fornecedores");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
		
	}
	/**
	 * Acionada em clicar em Exibir -> Cardápio
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de itens deverá ser exibida.
	 */
	public void listarCardapio(){
		Temp.getInstance().setQualTableViewExibir("Cardapio");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
		
	}
	/**
	 * Acionada em clicar em Exibir -> Estoque
	 * Direciona o usuário a tela de exibir listas e usando a classe 'Temp' e seu atributo "QualTableViewExibir", estabelece que a TableView
	 * de estoque deverá ser exibida.
	 */
	public void listarEstoque(){
		Temp.getInstance().setQualTableViewExibir("Estoque");
		setPagina("/view/xml/ExibirListas.fxml", rightAnchor);
		
	}
	
	/**
	 * Ao clicar no botão "Registrar Venda", o usuário é direcionado a tela de registrar venda.
	 * 
	 */
	public void setPaginaRegistrarVenda() {

		setPagina("/view/xml/RegistrarVenda.fxml", rightAnchor); 
	}
	/**
	 * Ao clicar em Conta -> Sair, o usuário é direcionado a tela de login novamente.
	 * @throws IOException
	 */
	public void sairDaConta() throws IOException {
		Main m = new Main();
		m.telaLogin();
	}
	/**
	 * Muda o conteúdo de uma AnchorPAne (mais espeificamente a direita) para a tela fornecida.
	 * @param fxml - Arquivo referente a tela a ser exiibida.
	 * @param anchor - AnchorPane a ter conteúdo mudado.
	 */
	public void setPagina(String fxml, AnchorPane anchor) {
		Node node = null;
		try {
			node = (Node)FXMLLoader.load(getClass().getResource(fxml));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		anchor.getChildren().setAll(node);
	}
	
	/**
	 * Gera um relatório contendo todas as vendas realizadas
	 * @see FacadeGerarRelatorio
	 */
    public void relatorioTodasVendas() {
    	
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioTodasVendas();
    	if(isEmpty == null) {
    		Main.informationAlert("Não há vendas cadastradas!");
    	}

	}
    /**
	 * Gera um relatório contendo todo o estoque.
	 * @see FacadeGerarRelatorio
	 */
    public void relatorioEstoque() {
    	
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioEstoque();
    	if(isEmpty == null) {
    		Main.informationAlert("A lista de estoque está vazia!");
    	}
    }
    /**
     * Chama o PopUp do sistema para conseguir que o usuário forneça a categoria que vai ser usada para gerar no relatório.
     * @see PopUpController
     * @throws IOException
     */
    public void pegarCategoria() throws IOException {
    	
    	Temp.getInstance().setQualFuncaoDialog("pegarCategoria");
    	Temp.getInstance().setQualRelatorioGerar("VendasPorCategoria");
    	dialog();
    	
    }
    /**
   	 * Gera um relatório contendo todas as vendas de uma dada categoria de item.
   	 * @see FacadeGerarRelatorio
   	 * @param categoria - Categoria escolhida para gerar o relatório
   	 */
    public void relatorioVendasPorCategoria(String categoria){
    	
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioVendasPorCategoria(categoria);
    	if(isEmpty == null) {
    		Main.informationAlert("Nenhuma venda desta categoria encontrada!");
    	}
        
    }
    /**
     * Chama o PopUp do sistema para conseguir que o usuário forneça o id do produto que vai ser usada para gerar no relatório.
     * @see PopUpController
     * @throws IOException
     */
    public void pegarProdutoREstoque() throws IOException {
    	Temp.getInstance().setQualFuncaoDialog("pegarIdProduto");
    	Temp.getInstance().setQualRelatorioGerar("EstoquePorProduto");
    	dialog();
    }
    /**
   	 * Gera um relatório contendo todo o estoque de um determinado produto.
   	 * @see FacadeGerarRelatorio
   	 * @param idProduto - id do produto escolhido para gerar o relatório
   	 */
    public void relatorioEstoquePorProduto(String idProduto){
    	
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioEstoquePorProduto(idProduto);
    	if(isEmpty == null) {
    		Main.informationAlert("Não há estoque deste produto cadastrada!");
    	}
        
    }
    /**
     * Chama o PopUp do sistema para conseguir que o usuário forneça o id do produto que vai ser usada para gerar no relatório.
     * @see PopUpController
     * @throws IOException
     */
    public void pegarProdutoRFornecedor() throws IOException {
        Temp.getInstance().setQualFuncaoDialog("pegarIdProduto");
    	Temp.getInstance().setQualRelatorioGerar("FornecedorPorProduto");
    	dialog();
    }
    /**
   	 * Gera um relatório contendo todo os fornecedores de um determinado produto.
   	 * @see FacadeGerarRelatorio
   	 * @param idProdutof - id do produto escolhido para gerar o relatório
   	 */
    public void relatorioFornecedorPorProduto(String idProdutof) {
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioFornecedorPorProduto(idProdutof);
    	if(isEmpty == null) {
    		Main.informationAlert("Algo deu errado!");
    	}
    }
    /**
     * Chama o PopUp do sistema para conseguir que o usuário forneça o id do fornecedor que vai ser usada para gerar no relatório.
     * @see PopUpController
     * @throws IOException
     */
    public void pegarIdFornecedor() throws IOException {
    	Temp.getInstance().setQualFuncaoDialog("pegarIdFornecedor");
     	Temp.getInstance().setQualRelatorioGerar("FornecedorPorFornecedor");
     	dialog();
    }
    /**
   	 * Gera um relatório contendo todos produtos de um determinado fornecedor.
   	 * @see FacadeGerarRelatorio
   	 * @param idFornecedor - id do fornecedor escolhido para gerar o relatório
   	 */
    public void relatorioFornecedorPorFornecedor(String idFornecedor) {
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioFornecedorPorFonecedor(idFornecedor);
    	if(isEmpty == null) {
    		Main.informationAlert("Não há produtos desse fornecedor");
    	}
    }
    /**
     * Chama o PopUp do sistema para conseguir que o usuário forneça a data de inicio e fim de um período, que vai ser usada para gerar no relatório.
     * @see PopUpController
     * @throws IOException
     */
    public void pegarDatasRVendas() throws IOException {
    	Temp.getInstance().setQualFuncaoDialog("pegarDatas");
     	Temp.getInstance().setQualRelatorioGerar("VendasPorPeriodo");
     	dialog();
    }
    /**
   	 * Gera um relatório contendo todas vendas de um determinado período.
   	 * @see FacadeGerarRelatorio
   	 * @param dataInicio - Data inicio, representando o início do período
   	 * @param dataFim - Data fim, representando o fim do período
   	 */
    public void relatorioVendasPorPeriodo(Date dataInicio, Date dataFim) throws ParseException {
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioVendasPorPeriodo(dataInicio, dataFim);
    	if(isEmpty == null) {
    		Main.informationAlert("Não há vendas desse período");
    	}
    }
    /**
     * Chama o PopUp do sistema para conseguir que o usuário forneça a data de inicio e fim de um período, que vai ser usada para gerar no relatório.
     * @see PopUpController
     * @throws IOException
     */
    public void pegarDatasREstoque() throws IOException {
    	Temp.getInstance().setQualFuncaoDialog("pegarDatas");
     	Temp.getInstance().setQualRelatorioGerar("EstoqueAVencer");
     	dialog();
    }
    /**
   	 * Gera um relatório contendo todas produtos a vencer em um determinado período.
   	 * @see FacadeGerarRelatorio
   	 * @param dataInicio - Data inicio, representando o início do período
   	 * @param dataFim - Data fim, representando o fim do período
   	 */
    public void relatorioEstoqueAVencer(Date dataInicio, Date dataFim) throws ParseException {
    	String isEmpty = FacadeGerarRelatorio.geraRelatorioEstoqueAVencer(dataInicio, dataFim);
    	if(isEmpty == null) {
    		Main.informationAlert("Não há produtos a vencer neste período");
    	}
    }
    /**
     * Método que cria e exibe a tela PopUp.
     * @throws IOException
     */
    public void dialog() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/xml/PopUp.fxml"));
    	Window window = dlg.getDialogPane().getScene().getWindow();
    	window.setOnCloseRequest(event -> window.hide());
    	dlg.setDialogPane(loader.load());
    	dlg.show();
    	
    }
    
   
}
