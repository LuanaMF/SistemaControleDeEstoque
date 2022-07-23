package controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.BancoDados;
import model.Temp;
import model.Usuario;

/**
 * Classe controller da tela "ExibirListas" responsável por gerenciar todas açoes do usuário que acontecem(ou devem acontecer) nela.
 * @author Luana de Melo Fraga
 *
 */
public class ExibirListasController {
	
	//elementos da tela
	@FXML
	private TableView table;
	@FXML
	private Label labelExibir;
	@FXML
	private AnchorPane anchorExibirLista;
	@FXML
	private Button botaoAdicionar, botaoExcluir;
	@FXML
	private ImageView imgAdicionar, imgExcluir;
	
	private Usuario userLogado = Temp.getInstance().getUsuarioLogado();
	
	/*
	 * Método padrão initialize, onde, assim que o arquivo "fxml" que essa classe controla for carregado(iniciado), ele será executado.
	 * Nela é carregada as imagens nos campos de ImageView, é decidido as permissões do usuário na tela, e por fim, é chamada a função
	 * de preenher a TableView.
	 * @see Image
	 */
	@FXML
	public void initialize() {
		
		Image adicionar = new Image(getClass().getResourceAsStream("/view/assets/adicionar.png"));
		Image excluir = new Image(getClass().getResourceAsStream("/view/assets/excluir.png"));
		imgAdicionar.setImage(adicionar);
		imgExcluir.setImage(excluir);
		
		botaoAdicionar.setText("Adicionar");
		botaoExcluir.setText("Excluir");
		if(!userLogado.getPermissao()) {
			botaoAdicionar.setVisible(false);
			botaoAdicionar.setDisable(true);
			botaoExcluir.setVisible(false);
			botaoExcluir.setDisable(true);
		}
		preencheTableView();
		clickEvent();
		
	}
	/**
	 * Evento pertencente ao botão de Excluir da tela, que chama o método local privado "excluir".
	 */
	public void excluirElemento() {
		excluir();
	}
	
	/**
	 * Chamado no método "initialize", este é responsável por preencher a TableView presente na tela, de acordo com o conteúdo presente na classe
	 * 'Temp', no atributo "qualTableViewExibir", que é atribuído na classe 'MenuController', e é responsável por forneer o tipo de objeto a ser
	 * exibido.
	 * @see Temp
	 * @see MenuController
	 * @see FacadeBuildTableView
	 */
	private void preencheTableView(){
		
		String qualTableView = Temp.getInstance().getQualTableViewExibir();
		
		if(qualTableView.equals("Vendas")) {
			
			botaoAdicionar.setVisible(false);
			botaoAdicionar.setDisable(true);
			labelExibir.setText("Vendas");
			table = FacadeBuildTableView.tableViewVendas(table);
		}
		else if(qualTableView.equals("Funcionarios")) {
			
			labelExibir.setText("Funcionários");
			Temp.getInstance().setQualElementoEstaAdicionando("Funcionario");
			table = FacadeBuildTableView.tableViewFuncionarios(table);
		}
		else if(qualTableView.equals("Usuarios")) {
			
			labelExibir.setText("Usuários");
			Temp.getInstance().setQualElementoEstaAdicionando("Usuario");
			table = FacadeBuildTableView.tableViewUsuarios(table);
		}
		else if(qualTableView.equals("Clientes")) {
			labelExibir.setText("Clientes");
			Temp.getInstance().setQualElementoEstaAdicionando("Cliente");
			table = FacadeBuildTableView.tableViewClientes(table);
		}
		else if(qualTableView.equals("Fornecedores")) {
			labelExibir.setText("Fornecedores");
			Temp.getInstance().setQualElementoEstaAdicionando("Fornecedor");
			table = FacadeBuildTableView.tableViewFornecedores(table);
		}
		else if(qualTableView.equals("Cardapio")) {
			labelExibir.setText("Cardápio");
			Temp.getInstance().setQualElementoEstaAdicionando("Item");
			table = FacadeBuildTableView.tableViewCardapio(table, BancoDados.getInstance().getCardapio());
		}
		else if(qualTableView.equals("Estoque")) {
			labelExibir.setText("Estoque");
			Temp.getInstance().setQualElementoEstaAdicionando("Estoque");
			table = FacadeBuildTableView.tableViewEstoque(table);
		}
	}
	
	/**
	 * Método acionado ao clicar o botão de adicionar, onde irá direcionar o usuário a tela de CRUD, depois de ter sinalizado
	 * a função(adicionar) que a tela deverá performar.
	 * @see MenuController
	 * @see Temp
	 */
	public void direcionaTelaCrud() {
	   MenuController m = new MenuController();
	   Temp.getInstance().setPrincipalFuncao("Adicionar");
	   m.setPagina("/view/xml/TelaCRUD.fxml", anchorExibirLista);
 	   
	}
	/**
	 * Chamado dentro do método local público, que é acionado ao apertar o botão de excluir, depois de acessar o elemento selecionado, definido
	 * na função "clickEvent", ele logo em seguida chama a classe BancoDados que excluirá o objeto da lista.
	 * @see Temp
	 * @see BancoDados
	 */
	private void excluir() {
		
		Object elemento = Temp.getInstance().getElementoSelecionado();
		String classeDoElemento = elemento.getClass().getSimpleName();
		ObservableList<?> lista = null;
		
		if(classeDoElemento.equals("Usuario")) {
			lista = BancoDados.getInstance().getUsers();
		}
		else if(classeDoElemento.equals("Venda")) {
			lista = BancoDados.getInstance().getListaVEN();
		}
		else if(classeDoElemento.equals("Funcionario")) {
			lista = BancoDados.getInstance().getListaFUN();
		}
		else if(classeDoElemento.equals("Fornecedor")) {
			lista = BancoDados.getInstance().getListaFOR();
		}
		else if(classeDoElemento.equals("Cliente")) {
			lista = BancoDados.getInstance().getListaClientes();
		}
		else if(classeDoElemento.equals("Estoque")) {
			lista = BancoDados.getInstance().getListaEstoque();
		}
		else if(classeDoElemento.equals("Item")) {
			lista = BancoDados.getInstance().getCardapio();
		}
		
		BancoDados.excluir(lista, elemento);
	}
	/**
	 * Método responsável por observar a TableView e salvar o elemento clicado caso seja o caso de apenas um click, caso seja dois, o usuário
	 * será direcionado a tela CRUD onde será capaz de visualizar o elemento em detalhes.
	 * @see Temp
	 * @see MenuController
	 * @see MouseEvent
	 */
	private void clickEvent() {
		MenuController m = new MenuController();
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			 
		    @Override
		    public void handle(MouseEvent click) {
		        	
		           Object itemSelected = table.getSelectionModel()
		                                                    .getSelectedItem();
		           Temp.getInstance().setElementoSelecionado(itemSelected);
		           if (click.getClickCount() == 2) {
		        	   Temp.getInstance().setPrincipalFuncao("Editar/Visualizar");
		        	   m.setPagina("/view/xml/TelaCRUD.fxml", anchorExibirLista);
		        	   
		           }
		        
		    }
		});
	}
	
		
		
		
		
		
		
		
	
}
