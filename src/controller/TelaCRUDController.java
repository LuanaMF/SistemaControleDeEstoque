package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.BancoDados;
import model.Cliente;
import model.Estoque;
import model.ExpressoesRegulares;
import model.Fornecedor;
import model.Funcionario;
import model.GerenciadorEstoque;
import model.GerenciadorFornecedor;
import model.GerenciadorFuncionario;
import model.GerenciadorItem;
import model.GerenciadorProduto;
import model.GerenciadorUsuario;
import model.GerenciadorVenda;
import model.Item;
import model.Produto;
import model.Temp;
import model.Usuario;
import model.Venda;

/**
 * Classe controller da tela "TelaCRUD" responsável por gerenciar todas ações do usuário que acontecem(ou devem acontecer) nela.
 * Responsável por gerenciar as ações de adicionar e atualizar as entidades do sistema.
 * @author Luana de Melo Fraga
 *
 */
public class TelaCRUDController {
	
	// elementos da tela
	@FXML
	private Label atributo1;
	@FXML
	private Label atributo2;
	@FXML
	private Label atributo3;
	@FXML
	private Label atributo4;
	@FXML
	private Label atributo5;
	@FXML
	private Label atributo6;
	@FXML
	private TextField textFieldAtributo1;
	@FXML
	private TextField textFieldAtributo2;
	@FXML
	private TextField textFieldAtributo3;
	@FXML
	private TextField textFieldAtributo4;
	@FXML
	private TextArea textAreaAtributo5;
	@FXML
	private TableView<Produto> tabelaAtributo5;
	@FXML
	private TableView<Item> tabelaAtributo6;
	@FXML
	private ListView listView1;
	@FXML
	private ListView listView2;
	@FXML
	private AnchorPane anchorExibirIndividuo;
	@FXML
	private Label atributo7;
	@FXML
	private TextField textFieldAtributo7;
	@FXML
	private Label labelChave;
	@FXML
	private Label labelValor;
	@FXML
	private SplitPane splitPane;
	@FXML
	private Label mensagem;
	@FXML
	private ChoiceBox<String> choiceBoxAtributo3;
	@FXML
	private Button botaoAdicionarAtributo6, botaoExcluirAtributo6, botaoAdicionarAtributo5, botaoExcluirAtributo5, botaoVoltar, botao;
	@FXML
	private ImageView imgVoltar, imgBotaoPrincipal, imgAdicionar6, imgExcluir6, imgAdicionar5, imgExcluir5;
	
	private Object itemSelecionado;
	
	private String funcaoPrincipal = Temp.getInstance().getPrincipalFuncao();
	
	private Usuario userLogado = Temp.getInstance().getUsuarioLogado();
	
	/*
	 * Método padrão initialize, onde, assim que o arquivo "fxml" que essa classe controla for carregado(iniciado), ele será executado.
	 * Nela  é carregada as imagens nos campos de ImageView,  é decidido as permissões do usuário na tela, e por fim, é chamada as funções
	 * de visualização a depender da função que a tela irá executar, função essa definida na classe "MenuController".
	 * @see MenuController
	 * @see Temp
	 * @see Image
	 */
	@FXML
	public void initialize() {
		
		Image imagVoltar = new Image(getClass().getResourceAsStream("/view/assets/voltar.png"));
		Image adicionar = new Image(getClass().getResourceAsStream("/view/assets/adicionar.png"));
		Image excluir = new Image(getClass().getResourceAsStream("/view/assets/excluir.png"));
		imgAdicionar5.setImage(adicionar);
		imgExcluir5.setImage(excluir);
		imgAdicionar6.setImage(adicionar);
		imgExcluir6.setImage(excluir);
		imgVoltar.setImage(imagVoltar);
		
		
		
		if(!userLogado.getPermissao()) {
			naoeGerente();
		}
		
		mensagem.setVisible(false);
		
		if(funcaoPrincipal.equals("Editar/Visualizar")) {
			Image imagAtualizar = new Image(getClass().getResourceAsStream("/view/assets/atualizar.png"));
			imgBotaoPrincipal.setImage(imagAtualizar);
			String classe = Temp.getInstance().getElementoSelecionado().getClass().getSimpleName();
			if(classe.equals("Cliente")) {
				exibirCliente();
			}
			else if(classe.equals("Usuario")) {
				exibirUsuario();
			}
			else if (classe.equals("Fornecedor")) {
				exibirFornecedor();
			}
			else if (classe.equals("Funcionario")) {
				exibirFuncionario();
			}
			else if(classe.equals("Venda")) {
				clickEventTabela(tabelaAtributo6);
				botaoAdicionarAtributo6.setText("Adicionar item");
				botaoExcluirAtributo6.setText("Excluir item");
				exibirVenda();
			}
			else if(classe.equals("Item")) {
				
				clickEventList(listView1);
				botaoAdicionarAtributo6.setText("Adicionar produto");
				botaoExcluirAtributo6.setText("Excluir produto");
				exibirItem();
			}
			else if(classe.equals("Estoque")) {
				
				clickEventList(listView1);
				clickEventTabela(tabelaAtributo5);
				botaoAdicionarAtributo6.setText("Adicionar fornecedor");
				botaoExcluirAtributo6.setText("Excluir fornecedor");
				botaoAdicionarAtributo5.setText("Adicionar produto");
				botaoExcluirAtributo5.setText("Excluir produto");
				exibirEstoque();
			}
		
		}
		else if(funcaoPrincipal.equals("Adicionar")) {
			
			String qualAdicionar = Temp.getInstance().getQualElementoEstaAdicionando();
			imgBotaoPrincipal.setImage(adicionar);
			
			if(qualAdicionar.equals("Usuario")) {
				
				adicionarUsuarioView();
			}
			else if(qualAdicionar.equals("Funcionario")) {
				
				adicionarFuncionarioView();
			}
            else if(qualAdicionar.equals("Cliente")) {
				
				adicionarClienteView();
			}
            else if(qualAdicionar.equals("Fornecedor")) {
				
				adicionarFornecedorView();
			}
            else if(qualAdicionar.equals("Item")) {
				
				adicionarItemView();
			}
            else if(qualAdicionar.equals("Estoque")) {
				
				adicionarEstoqueView();
			}
		}
		
	}
	
	/**
	 * Acionado ao botão principal da tela ser clicado, ele define a ação real desse botão -adicionar ou atualizar - a depender do texto
	 * encontrado nele, definido nos métodos de exibição de cada entidade.
	 * @throws ParseException
	 */
	public void botaoAction() throws ParseException {
		String action = botao.getText();
		System.out.println(action);
		if(action.equals("Atualizar")) {
			atualizar();
		}
		else if(action.equals("Adicionar")) {
			adicionar();
		}
		
	}
	/**
	 * Chamado no método acionado ao clicar no botão principal, caso a função principal da tela esteja como "atualizar", nela é extraído o nome 
	 * da classe que pertence o produto selecionado, e então, chama o método de atualização da entidade em questão.
	 * @see Temp
	 * @throws ParseException
	 */
	private void atualizar() throws ParseException {
		String classe = Temp.getInstance().getElementoSelecionado().getClass().getSimpleName();
		if(classe.equals("Cliente")) {
			atualizarCliente();
		}
		else if(classe.equals("Usuario")) {
			atualizarUsuario();
		}
		else if(classe.equals("Fornecedor")) {
			atualizarFornecedor();
		}
		else if(classe.equals("Funcionario")) {
			atualizarFuncionario();
		}
		else if(classe.equals("Venda")) {
			atualizarVenda();
		}
		else if(classe.equals("Item")) {
			atualizarItem();
		}
		else if(classe.equals("Estoque")) {
			
			   atualizarEstoque();
			
		}
	}
	/**
	 * Chamado no método acionado ao clicar no botão principal, caso a função principal da tela esteja como "adicionar". Nela é usado o atributo
	 * da classe "Temp" chamado 'qualElementoEstaAdicionando' para indentificar qual entidade sera adicionada, e então, chamar seu respectivo
	 * método.
	 * @see Temp
	 * @throws ParseException
	 */
	private void adicionar() throws ParseException {
		String qualAdicionar = Temp.getInstance().getQualElementoEstaAdicionando();
		System.out.println(qualAdicionar);
		if(qualAdicionar.equals("Usuario")) {
			
			adicionarUsuario();
			
		}
        if(qualAdicionar.equals("Funcionario")) {
			
			adicionarFuncionario();
			
		}
        if(qualAdicionar.equals("Cliente")) {
			
			adicionarCliente();
			
		}
        if(qualAdicionar.equals("Fornecedor")) {
			
			adicionarFornecedor();
			
		}
        if(qualAdicionar.equals("Item")) {
			
			adicionarItem();
			
		}
        if(qualAdicionar.equals("Estoque")) {
			
			adicionarEstoque();
			
		}
	}
	
	/**
	 * Método acionado quando o usuário clica no botão de voltar, que direciona o usuário a página de exibir listas novamente.
	 * @see MenuController
	 */
	public void voltar() {
		
		MenuController m = new MenuController();
		m.setPagina("/view/xml/ExibirListas.fxml", anchorExibirIndividuo);
		
		
	}
	
	/**
	 * Método chamado em "initialize", caso o usuário não for gerente, que desabilita botões e edição, já que ele não tem permissão
	 * para tal.
	 */
	private void naoeGerente() {
		
		botao.setVisible(false);
		botao.setDisable(true);
		textFieldAtributo1.setEditable(false);
		textFieldAtributo2.setEditable(false);
		textFieldAtributo3.setEditable(false);
		textFieldAtributo4.setEditable(false);
		textFieldAtributo7.setEditable(false);
		textAreaAtributo5.setEditable(false);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
	}
	
	/**
	 * Exibi as informações de um objeto Cliente, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
	 * É mostrado atributo por atributo.
	 * @see Cliente
	 */
	private void exibirCliente() {
		Cliente cliente = (Cliente) Temp.getInstance().getElementoSelecionado();
		
		botaoVoltar.setText("Voltar");
		botao.setText("Atualizar");
		atributo1.setText("Nome: ");
		textFieldAtributo1.setText(cliente.getNome());
		
		atributo2.setText("CPF: ");
		textFieldAtributo2.setText(cliente.getCpf());
		
		atributo3.setText("Email: ");
		textFieldAtributo3.setText(cliente.getEmail());
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo4.setText("Telefone: ");
		textFieldAtributo4.setText(cliente.getTelefone());
		
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		
		atributo6.setVisible(false);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
	}
	
	/**
	 * Exibi os atributos de um objeto Cliente, com os campos de TextField vazios para que o usuário possa preencher as informações.
	 * É usado para adicionar Cliente.
	 * @see Cliente
	 */
	private void adicionarClienteView() {
		
		
		atributo1.setText("Nome: ");
		
		
		atributo2.setText("CPF: ");
		
		
		atributo3.setText("Email: ");
		
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo4.setText("Telefone: ");
		
		botaoVoltar.setText("Voltar");
		botao.setText("Adicionar");
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		atributo6.setVisible(false);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
	}
	
	/**
	 * Responável por cadastar um cliente no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método da 
	 * própria classe Cliente, que passado as informações digitada pelo usuário nos campos de textos, cria o novo cliente.
	 * @see Cliente
	 */
	private void adicionarCliente() {
		boolean tudoCerto = verificaInformacoesCliente();
		
		if(tudoCerto) {
			Cliente.adicionarCliente(textFieldAtributo1.getText(), textFieldAtributo2.getText(), textFieldAtributo3.getText(), textFieldAtributo4.getText());
			mensagem.setText("Cliente adicionado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
		}
	}
	
	/**
	 * Responsável por atualizar um cliente do sistema. Depois de verificar se os campos estam preenchidos corretamente,chama o método da 
	 * própria classe Cliente, que passado as informações digitada pelo usuário nos campos de textos, atualiza o cliente passado, atributo
	 * por atributo.
	 * @see Cliente
	 */
	private void atualizarCliente(){
		
		boolean tudoCerto = verificaInformacoesCliente();
		if(tudoCerto) {
			Cliente cliente = (Cliente) Temp.getInstance().getElementoSelecionado();
			cliente.atualizarCliente(cliente, textFieldAtributo1.getText(), textFieldAtributo2.getText(), textFieldAtributo3.getText(), textFieldAtributo4.getText());
			mensagem.setText("Cliente atualizado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
		}
	}
	
	
	
	
	/**
	 * Exibi as informações de um objeto Usuario, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
	 * É mostrado atributo por atributo.
	 * @see Usuario
	 */
    private void exibirUsuario() {
    	Usuario user = (Usuario) Temp.getInstance().getElementoSelecionado();
    	
    	botaoVoltar.setText("Voltar");
    	botao.setText("Atualizar");
    	
    	atributo1.setText("ID: ");
    	textFieldAtributo1.setText(user.getId());
    	textFieldAtributo1.setEditable(false);
    	
    	atributo2.setText("Senha: ");
    	textFieldAtributo2.setText(user.getSenha());
    	
    	atributo3.setVisible(false);
		textFieldAtributo3.setVisible(false);
		textFieldAtributo3.setDisable(true);
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo4.setVisible(false);
		textFieldAtributo4.setVisible(false);
		textFieldAtributo4.setDisable(true);
		
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		
		atributo6.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    /**
	 * Responsável por atualizar um usuário do sistema. Depois de verificar se os campos estam preenchidos corretamente,chama o método da 
	 * própria classe Usuario, que passado as informações digitada pelo usuário nos campos de textos, atualiza o usuário passado, atributo
	 * por atributo (nesse caso, apenas a senha).
	 * @see Usuario
	 */
    private void atualizarUsuario() {
    	boolean tudoCerto = verificaInformacoesUsuario();
    	if(tudoCerto) {
	    	Usuario user = (Usuario) Temp.getInstance().getElementoSelecionado();
	    	user.atualizaSenha(user, textFieldAtributo2.getText());
	    	mensagem.setText("Senha atualizada com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    /**
	 * Exibi os atributos de um objeto Usuario, com os campos de TextField vazios para que o usuário possa preencher as informações.
	 * É usado para adicionar Usuario.
	 * @see Usuario
	 */
    private void adicionarUsuarioView() {
    	
    	mensagem.setText(" ");
    	botaoVoltar.setText("Voltar");
    	atributo1.setText("ID: ");
    	atributo2.setText("Senha: ");
    	
    	botao.setText("Adicionar");
    	atributo3.setVisible(false);
		textFieldAtributo3.setVisible(false);
		textFieldAtributo3.setDisable(true);
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo4.setVisible(false);
		textFieldAtributo4.setVisible(false);
		textFieldAtributo4.setDisable(true);
		
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		
		atributo6.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
	 * Responável por cadastar um usuário no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método da 
	 * da classe "GerenciadorUsuario", que passado as informações digitadas nos campos de textos, cria o novo usuário.
	 * @see Usuario
	 */
    private void adicionarUsuario() {
    	boolean tudoCerto = verificaInformacoesUsuario();
    	if(tudoCerto) {
    		int index = Main.buscaPorId(BancoDados.getInstance().getListaFUN(), textFieldAtributo1.getText());
    		if(index != -1) {
    			int usuarioExiste = Main.buscaPorId(BancoDados.getInstance().getUsers(), textFieldAtributo1.getText());
    			if(usuarioExiste == -1) {
	    			GerenciadorUsuario.cadastrarUsuario(textFieldAtributo1.getText(), textFieldAtributo2.getText());
	    			mensagem.setText("Usuario adicionado com sucesso!");
	    			mensagem.setTextFill(Color.GREEN);
	    			mensagem.setVisible(true);
    			}
    			else if (usuarioExiste != -1 && usuarioExiste > 0){
    				mensagem.setText("Usuário já existe!");
    				System.out.println(usuarioExiste);
        			mensagem.setTextFill(Color.RED);
        			mensagem.setVisible(true);
    			}
    		}		
    		else {
    			mensagem.setText("Funcionario não existe!");
    			mensagem.setTextFill(Color.RED);
    			mensagem.setVisible(true);
    		}
    	}
    }
    
    
    
    
    

	/**
	 * Exibi as informações de um objeto Fornecedor, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
	 * É mostrado atributo por atributo.
	 * @see Fornecedor
	 */
    private void exibirFornecedor() {
    	Fornecedor fornecedor = (Fornecedor) Temp.getInstance().getElementoSelecionado();
    	botaoVoltar.setText("Voltar");
    	botao.setText("Atualizar");
    	
    	atributo4.setVisible(false);
    	textFieldAtributo4.setVisible(false);
    	textFieldAtributo4.setDisable(true);
    	textFieldAtributo4.setEditable(false);
    	
    	atributo1.setText("Nome: ");
		textFieldAtributo1.setText(fornecedor.getNome());
    	
		atributo2.setText("CNPJ: ");
		textFieldAtributo2.setText(fornecedor.getCnpj());
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo3.setText("Endereço: ");
		textFieldAtributo3.setText(fornecedor.getEndereco());
		
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		
		atributo6.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
   	 * Exibi os atributos de um objeto Fornecedor, com os campos de TextField vazios para que o usuário possa preencher as informações.
   	 * É usado para adicionar Fornecedor.
   	 * @see Fornecedor
   	 */
    private void adicionarFornecedorView() {
    	
    	atributo1.setText("Nome: ");	
		atributo2.setText("CNPJ: ");
		
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo3.setText("Endereço: ");
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		
    	botaoVoltar.setText("Voltar");
    	botao.setText("Adicionar");
    	atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		atributo4.setVisible(false);
    	textFieldAtributo4.setVisible(false);
    	textFieldAtributo4.setDisable(true);
    	textFieldAtributo4.setEditable(false);
    	
		
		atributo6.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
	 * Responável por cadastar um fornecedor no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método da 
	 * da classe "GerenciadorFornecedor", que, passado as informações digitadas nos campos de textos, cria o novo fornecedor.
	 * @see Fornecedor
	 */
    private void adicionarFornecedor() {
    	boolean tudoCerto = verificaInformacoesFornecedor();
    	if(tudoCerto) {
    		GerenciadorFornecedor.cadastrarFOR(textFieldAtributo2.getText(), textFieldAtributo1.getText(), textFieldAtributo3.getText());
    		mensagem.setText("Fornecedor cadastrado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    /**
   	 * Responsável por atualizar um fornecedor do sistema. Depois de verificar se os campos estam preenchidos corretamente, chama o método da 
   	 * classe "GerenciadorFornecedor", que, passado as informações digitada pelo usuário nos campos de textos, atualiza o fornecedor passado, atributo
   	 * por atributo.
   	 * @see Fornecedor
   	 */
    private void atualizarFornecedor() {	
    	
    	boolean tudoCerto = verificaInformacoesFornecedor();
    	if(tudoCerto) {
	    	Fornecedor fornecedor = (Fornecedor) Temp.getInstance().getElementoSelecionado();
	    	GerenciadorFornecedor.editarFOR(fornecedor, textFieldAtributo1.getText(), textFieldAtributo2.getText(), textFieldAtributo3.getText());
	    	mensagem.setText("Fornecedor atualizado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}

    }
    
    
    
    /**
	 * Exibi as informações de um objeto Funcionario, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
	 * É mostrado atributo por atributo.
	 * @see Funcionario
	 */
    private void exibirFuncionario() {
    	
    	ObservableList<String> funcoes = FXCollections.observableArrayList();
    	funcoes.addAll("Gerente", "Outro");
    	choiceBoxAtributo3.setItems(funcoes);
    	
    	Funcionario funcionario = (Funcionario) Temp.getInstance().getElementoSelecionado();
    	botaoVoltar.setText("Voltar");
    	botao.setText("Atualizar");
    	atributo1.setText("ID: ");
    	textFieldAtributo1.setText(funcionario.getId());
    	textFieldAtributo1.setEditable(false);
    	
    	atributo2.setText("Nome: ");
		textFieldAtributo2.setText(funcionario.getNome());
		
		atributo3.setText("Cargo: ");
		choiceBoxAtributo3.setValue(funcionario.getCargo());
		textFieldAtributo3.setVisible(false);
		textFieldAtributo3.setDisable(true);
		
		atributo4.setVisible(false);
		textFieldAtributo4.setVisible(false);
		textFieldAtributo4.setDisable(true);
		
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		
		atributo6.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    	
    }
    
    /**
   	 * Exibi os atributos de um objeto Funcionario, com os campos de TextField vazios para que o usuário possa preencher as informações.
   	 * É usado para adicionar Funcionario.
   	 * @see Funcionario
   	 */
    private void adicionarFuncionarioView() {
    	ObservableList<String> funcoes = FXCollections.observableArrayList();
    	funcoes.addAll("Gerente", "Outro");
    	choiceBoxAtributo3.setItems(funcoes);
    	
    	atributo1.setText("Nome: ");
    	atributo3.setText("Cargo: "); 
    	
    	atributo2.setVisible(false);
    	
    	botaoVoltar.setText("Voltar");
    	botao.setText("Adicionar");
    	textFieldAtributo3.setVisible(false);
		textFieldAtributo3.setDisable(true);
		textFieldAtributo2.setText("algumacoisa");
		textFieldAtributo2.setVisible(false);
		textFieldAtributo2.setDisable(true);
		atributo4.setVisible(false);
		textFieldAtributo4.setVisible(false);
		textFieldAtributo4.setDisable(true);
		
		atributo5.setVisible(false);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
    	atributo6.setVisible(false);
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo6.setVisible(false);
		botaoAdicionarAtributo6.setDisable(true);
		botaoExcluirAtributo6.setVisible(false);
		botaoExcluirAtributo6.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
   	 * Responável por cadastar um funcionario no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método 
   	 * da classe "GerenciadorFuncionario", que, passado as informações digitadas nos campos de textos, cria o novo funcionario.
   	 * @see Funcionario
   	 */
    private void adicionarFuncionario() {
    	boolean tudoCerto = verificaInformacoesFuncionario();
    	
    	if(tudoCerto) {
    		GerenciadorFuncionario.cadastrarFUN(textFieldAtributo1.getText(), choiceBoxAtributo3.getValue());
    		mensagem.setText("Funcionário adicionado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    /**
   	 * Responsável por atualizar um forneceodr do sistema. Depois de verificar se os campos estam preenchidos corretamente, chama o método da 
   	 * classe "GerenciadorFuncionario", que, passado as informações digitada pelo usuário nos campos de textos, atualiza o funcionario passado, atributo
   	 * por atributo.
   	 * @see Funcionario
   	 */
    private void atualizarFuncionario() {
    	boolean tudoCerto = verificaInformacoesFuncionario();
    	Funcionario funcionario = (Funcionario) Temp.getInstance().getElementoSelecionado();
    	
    	if(tudoCerto) {
	    	GerenciadorFuncionario.editarFUN(funcionario, textFieldAtributo2.getText(), choiceBoxAtributo3.getValue());
	    	mensagem.setText("Funcionário atualizado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    
    
    /**
	 * Exibi as informações de um objeto Venda, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
	 * É mostrado atributo por atributo.
	 * @see Venda
	 */
    private void exibirVenda() {
    	
    	ObservableList<String> modos = FXCollections.observableArrayList();
    	modos.addAll("Dinheiro", "Pix", "Cartão de débito", "Cartão de Crédito");
    	choiceBoxAtributo3.setItems(modos);
    	
    	Venda venda = (Venda) Temp.getInstance().getElementoSelecionado();
    	botaoVoltar.setText("Voltar");
    	botao.setText("Atualizar");
    	atributo1.setText("ID: ");
    	textFieldAtributo1.setText(venda.getId());
    	textFieldAtributo1.setEditable(false);
    	
    	atributo2.setText("Data: ");
		textFieldAtributo2.setText(venda.getData());
		
		atributo7.setText("Hora: ");
		textFieldAtributo7.setText(venda.getHorario());
		
		atributo3.setText("Modo de pagamento: ");
		choiceBoxAtributo3.setValue(venda.getPagamento());
		
		textFieldAtributo3.setVisible(false);
		textFieldAtributo3.setDisable(true);
		
		atributo4.setText("Total da compra: ");
		String valor = String.format("%,.2f", venda.getPrecoDouble());
		textFieldAtributo4.setText(valor);
		
		atributo5.setText("Cliente: ");
		if(venda.getClienteObjeto() == null){
			textAreaAtributo5.setText(" ");
		}
		else {
			textAreaAtributo5.setText(venda.getClienteObjeto().getId());
		}
		
		atributo6.setText("Itens da compra: ");
		tabelaAtributo6 = FacadeBuildTableView.tableViewCardapioSemProdutos(tabelaAtributo6, venda.getItens());
		
		splitPane.setVisible(false);
		splitPane.setDisable(true);
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		labelChave.setVisible(false);
		labelValor.setVisible(false);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
   	 * Responsável por atualizar uma venda do sistema. Depois de verificar se os campos estam preenchidos corretamente, e de atribuir corretamente o cliente
   	 * da compra, é chamado o método da classe "GerenciadorVenda", que, passado as informações digitada pelo usuário nos campos de textos, atualiza a venda passada, atributo
   	 * por atributo.
   	 * @see Venda
   	 */
    private void atualizarVenda() throws ParseException {
    	
    	Cliente cliente = null;
    	boolean clienteExiste = false;
    	ObservableList<Item> obList;
    	boolean tudoCerto = verificaInformacoesVenda();
    	if(tudoCerto) {
	    	if(textAreaAtributo5.getText().isBlank() && textAreaAtributo5.getText().isEmpty()) {
	    		cliente = null;
	    	}
	    	else {
	    		int index = Main.buscaPorId(BancoDados.getInstance().getListaClientes(), textAreaAtributo5.getText());
		    	if (index != -1) {
		    		clienteExiste = true;
		    		cliente = BancoDados.getInstance().getListaClientes().get(index);
		    	}
		    	else {
		    		 clienteExiste = false;
		    	}
		    }
	    	Venda venda = (Venda) Temp.getInstance().getElementoSelecionado();
	    	obList = tabelaAtributo6.getItems();
	    	GerenciadorVenda.editarVEN(venda, textFieldAtributo2.getText(), textFieldAtributo7.getText(), textFieldAtributo4.getText(), choiceBoxAtributo3.getValue(), cliente, obList);
	    	
	    	if(!clienteExiste){
	    		venda.setCliente(null);
	    		Main.informationAlert("Aviso: A venda foi atualizada, porém o cliente informado não existe! "
	    				+ "O cliente da venda ficará vazio até que informe um id válido.");
	    	}
	    	mensagem.setText("Venda atualizada com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    	
    }
    
    
    /**
     * Método acionado ao clicar no botão 'Adicionar' da TableView/ListView do atributo6, onde, a depender de qual elemento a tabela ou listView esteja exibindo, 
     * irá chamar o popUp do sistema e passar a função de pegar o id da entidade em questão, que o usuário deseja adicionar na tabela. Por fim,
     * é feito a chamada do Pop-Up.
     * @see MenuController
     * @see PopUpController
     * @throws IOException
     */
    public void adicionarUpdate() throws IOException {
    	MenuController m = new MenuController();
    	if(botaoAdicionarAtributo6.getText().equals("Adicionar item")){
	    	Temp.getInstance().setQualFuncaoDialog("pegarIdItem"); 
	    	Temp.getInstance().setTabelaQueSeraAdicionadaOObjeto(tabelaAtributo6);
	    	m.dialog();
    	}
    	else if(botaoAdicionarAtributo6.getText().equals("Adicionar produto")){
    		Temp.getInstance().setQualFuncaoDialog("pegarIdProdutoNecessario"); 
	    	Temp.getInstance().setListViewQueSeraAdicionadaAChave(listView1);
	    	Temp.getInstance().setListViewQueSeraAdicionadoOValor(listView2);
	    	m.dialog();
    	}
    	else if(botaoAdicionarAtributo6.getText().equals("Adicionar fornecedor")) {
    		Temp.getInstance().setQualFuncaoDialog("pegarIdFornecedorEstoque"); 
	    	Temp.getInstance().setListViewQueSeraAdicionadaAChave(listView1);
	    	Temp.getInstance().setListViewQueSeraAdicionadoOValor(listView2);
	    	m.dialog();
    	}
    }

    /**
     * Método acionado ao clicar no botão 'Excluir' da TableView/ListView do atributo6, onde, a depender de qualelemento a tabela ou listView esteja exibindo, 
     * irá exluir a entidade selecionada.
     * @see MenuController
     * @see PopUpController
     * @throws IOException
     */
    public void excluirUpdate() {
    	
    	if(itemSelecionado == null) {
    		mensagem.setText("Selecione o item para excluí-lo. Caso esteja selecionando na ListView da direita, selecione na ListView da esquerda");
    	}
    	else {
	    	if(botaoExcluirAtributo6.getText().equals("Excluir item")){
		    	Item item = (Item) itemSelecionado;
		    	tabelaAtributo6.getItems().remove(item);
	    	}
	    	else if(botaoExcluirAtributo6.getText().equals("Excluir produto")){
	    		
	    		Object obj = itemSelecionado;
	    		int index = listView1.getItems().indexOf(obj);
	    		listView1.getItems().remove(index);
	    		listView2.getItems().remove(index);
	    	}
	    	if(botaoExcluirAtributo6.getText().equals("Excluir fornecedor")){
	    		Object obj = itemSelecionado;
	    		int index = listView1.getItems().indexOf(obj);
	    		listView1.getItems().remove(index);
	    		listView2.getItems().remove(index);
	    	
    	    }
    	}
    }
    
    
    /**
   	 * Exibi as informações de um objeto Item, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
   	 * É mostrado atributo por atributo.
   	 * @see Item
   	 */
    private void exibirItem() {
    	
    	Item item = (Item) Temp.getInstance().getElementoSelecionado();
    	botaoVoltar.setText("Voltar");
    	botao.setText("Atualizar");
    	
    	atributo4.setVisible(false);
    	textFieldAtributo4.setDisable(true);
    	textFieldAtributo4.setVisible(false);
    	
    	
    	atributo1.setText("Nome: ");
		textFieldAtributo1.setText(item.getNome());
		
		atributo2.setText("Categoria: ");
		textFieldAtributo2.setText(item.getCategoria());
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo3.setText("Preço: ");
		String valor = String.format("%,.2f", item.getPrecoDouble());
		textFieldAtributo3.setText(valor);
		
		atributo5.setText("Descrição: ");
		textAreaAtributo5.setText(item.getSobre());
		
		atributo6.setText("Produtos necessários");
		
		Map<String, Double> produtosNecessarios = item.getProdutosNecessarios(); 
		labelChave.setText("ID");
		labelValor.setText("Quantidade");
		
		preencheListViewProdutosNecessarios(produtosNecessarios);
		
		botaoAdicionarAtributo6.setText("Adicionar produto");
		botaoExcluirAtributo6.setText("Excluir produto");
		tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
   	 * Exibi os atributos de um objeto Item, com os campos de TextField vazios para que o usuário possa preencher as informações.
   	 * É usado para adicionar Item.
   	 * @see Item
   	 */
    private void adicionarItemView() {
    	
    	atributo1.setText("Nome: ");
	
		atributo2.setText("Categoria: ");
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo3.setText("Preço: ");
		
		atributo5.setText("Descrição: ");
		
		atributo6.setText("Produtos necessários");
		labelChave.setText("ID");
		labelValor.setText("Quantidade");
		
		botaoAdicionarAtributo6.setText("Adicionar produto");
		botaoExcluirAtributo6.setText("Excluir produto");
    	botaoVoltar.setText("Voltar");
    	botao.setText("Adicionar");
    	
    	atributo4.setVisible(false);
    	textFieldAtributo4.setDisable(true);
    	textFieldAtributo4.setVisible(false);
    	tabelaAtributo5.setVisible(false);
		tabelaAtributo5.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
		botaoAdicionarAtributo5.setVisible(false);
		botaoAdicionarAtributo5.setDisable(true);
		botaoExcluirAtributo5.setVisible(false);
		botaoExcluirAtributo5.setDisable(true);
    }
    
    /**
   	 * Responável por cadastar um item no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método 
   	 * da classe "GerenciadorItem", que, passado as informações digitadas nos campos de textos, cria o novo item.
   	 * É usado o método "passaListViewParaMapStringDouble" para pegar os dads das ListViews e colocar no mapa.
   	 * @see Item
   	 */
    private void adicionarItem() throws ParseException {
    	
    	boolean tudoCerto = verificaInformacoesItem();
    	if(tudoCerto) {
    		Map<String, Double> pNecessarios = passaListViewParaMapStringDouble();
    		GerenciadorItem.cardapio(textFieldAtributo1.getText(), textAreaAtributo5.getText(), textFieldAtributo3.getText(), textFieldAtributo2.getText(), pNecessarios);
    		mensagem.setText("Item adicionado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    /**
   	 * Responsável por atualizar um item do sistema. Depois de verificar se os campos estam preenchidos corretamente, é chamado o método da classe "GerenciadorItem", que, 
   	 * passado as informações digitadas pelo usuário nos campos de textos, atualiza o item passado, atributo por atributo.
   	 * É usado o método "passaListViewParaMapStringDouble" para pegar os dads das ListViews e colocar no mapa.
   	 * @see Item
   	 */
    private void atualizarItem() throws ParseException {
    	
    	boolean tudoCerto = verificaInformacoesItem();
    	Item item = (Item) Temp.getInstance().getElementoSelecionado();
    	Map<String, Double> pNecessarios = passaListViewParaMapStringDouble();
    	if(tudoCerto) {
    		GerenciadorItem.editarITE(item, textFieldAtributo1.getText(), textFieldAtributo2.getText(), textAreaAtributo5.getText(), textFieldAtributo3.getText(), pNecessarios);
    		mensagem.setText("Item atualizado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    
    
    /**
   	 * Exibi as informações de um objeto Estoque, fornecido pela classe "Temp" e estabelecido na classe "ExibirListasController".
   	 * É mostrado atributo por atributo.
   	 * @see Estoque
   	 */
    private void exibirEstoque() {
    	Estoque estoque = (Estoque) Temp.getInstance().getElementoSelecionado();
    	
    	tabelaAtributo5.getColumns().clear();
    	listView1.getItems().clear();
    	listView2.getItems().clear();
    	
    	botaoVoltar.setText("Voltar");
    	mensagem.setText(" ");
    	
    	atributo3.setVisible(false);
    	textFieldAtributo3.setVisible(false);
    	textFieldAtributo3.setDisable(true);
    	
    	atributo1.setText("Nome: ");
		textFieldAtributo1.setText(estoque.getNome());
		
		atributo2.setText("Total em estoque: ");
		textFieldAtributo2.setText(estoque.getQuantidadeTotalDouble().toString());
		textFieldAtributo2.setEditable(false);
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
		atributo5.setText("Produtos: ");
		tabelaAtributo5 = FacadeBuildTableView.tableViewProdutos(tabelaAtributo5, estoque.getEstoque());
		
		atributo6.setText("Fornecedores: ");
		labelChave.setText("ID");
		labelValor.setText("Nome");
		preencheListViewProdutos(estoque.getFornecedores());
		
		atributo4.setVisible(false);
		textFieldAtributo4.setVisible(false);
		textFieldAtributo4.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);

		botao.setText("Adicionar");
    }
    
    /**
   	 * Exibi os atributos de um objeto Estoque, com os campos de TextField vazios para que o usuário possa preencher as informações.
   	 * É usado para adicionar Estoque.
   	 * @see Estoque
   	 */
    private void adicionarEstoqueView() {
    	
    	atributo1.setText("Nome: ");
    	botaoVoltar.setText("Voltar");
    	
		atributo2.setText("Total em estoque: ");
		textFieldAtributo2.setText("A quantidade total irá aparecer quando cadastrar o estoque");
		
		atributo5.setText("Produtos: ");
		tabelaAtributo5 = FacadeBuildTableView.tableViewProdutos(tabelaAtributo5, null);
		tabelaAtributo5.setVisible(true);
		tabelaAtributo5.setDisable(false);
		atributo5.setVisible(true);
		
		atributo6.setText("Fornecedores: ");
		labelChave.setText("ID");
		labelValor.setText("Nome");
		
		textFieldAtributo2.setEditable(false);
		choiceBoxAtributo3.setVisible(false);
		choiceBoxAtributo3.setDisable(true);
		
    	atributo3.setVisible(false);
    	textFieldAtributo3.setVisible(false);
    	textFieldAtributo3.setDisable(true);
    	atributo4.setVisible(false);
		textFieldAtributo4.setVisible(false);
		textFieldAtributo4.setDisable(true);
		tabelaAtributo6.setVisible(false);
		tabelaAtributo6.setDisable(true);
		textAreaAtributo5.setVisible(false);
		textAreaAtributo5.setDisable(true);
		atributo7.setVisible(false);
		textFieldAtributo7.setVisible(false);
		textFieldAtributo7.setDisable(true);
        
		botao.setText("Adicionar");
		atributo6.setVisible(true);
		splitPane.setVisible(true);
		splitPane.setDisable(false);
		labelChave.setVisible(true);
		labelValor.setVisible(true);
		botaoAdicionarAtributo6.setVisible(true);
		botaoAdicionarAtributo6.setDisable(false);
		botaoAdicionarAtributo6.setText("Adicionar fornecedor");
		botaoExcluirAtributo6.setVisible(true);
		botaoExcluirAtributo6.setDisable(false);
		botaoExcluirAtributo6.setText("Excluir fornecedor");
		botaoAdicionarAtributo5.setVisible(true);
		botaoAdicionarAtributo5.setDisable(false);
		botaoAdicionarAtributo5.setText("Adicionar produto");
		botaoExcluirAtributo5.setText("Excluir produto");
		botaoExcluirAtributo5.setVisible(true);
		botaoExcluirAtributo5.setDisable(false);
    }
    
    /**
   	 * Responável por cadastar um item no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método 
   	 * da classe "GerenciadorEstoque", que, passado as informações digitadas nos campos de textos, cria o novo Estoque.
   	 * É usado o método "passaListViewParaMapStringString" para pegar os dads das ListViews e colocar no mapa.
   	 * @see Estoque
   	 */
    private void adicionarEstoque() throws ParseException {
    	boolean tudoCerto = verificaInformacoesEstoque();
    	if(tudoCerto) {
    		ObservableList<Produto> listaProdutos = tabelaAtributo5.getItems();
        	Map<String, String> fornecedores = passaListViewParaMapStringString();
        	GerenciadorEstoque.cadastrarProdutoEstoque(textFieldAtributo1.getText(), listaProdutos, fornecedores);
        	mensagem.setText("Estoque adicionado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    /**
   	 * Responsável por atualizar um estoque do sistema. Depois de verificar se os campos estam preenchidos corretamente, é chamado o método da classe "GerenciadorEstoque", que, 
   	 * passado as informações digitadas pelo usuário nos campos de textos, atualiza o estoque passado, atributo por atributo.
   	 * É usado o método "passaListViewParaMapStringString" para pegar os dads das ListViews e colocar no mapa.
   	 * @see Estoque
   	 */
    private void atualizarEstoque() throws ParseException {
    	boolean tudoCerto = verificaInformacoesEstoque();
    	ObservableList<Produto> listaProdutos;
    	Map<String, String> fornecedores = passaListViewParaMapStringString();
    	if(tudoCerto) {
    		Estoque estoque = (Estoque) Temp.getInstance().getElementoSelecionado();
    		listaProdutos = tabelaAtributo5.getItems();
    		GerenciadorEstoque.editarEST(estoque, textFieldAtributo1.getText(), listaProdutos, fornecedores);
    		mensagem.setText("Estoque atualizado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
        }
    }
    
    /**
     * Exclui o produto selecionado da tabela.
     */
    public void excluirProduto() {
    	if(itemSelecionado == null) {
    		mensagem.setText("Selecione um produto para excluir!");
    	}
    	else {
	    	Produto produto = (Produto) itemSelecionado;
	    	tabelaAtributo5.getItems().remove(produto);
    	}
    }
    
    
    /**
     * Seta a função do popUp para "adicionarProduto" e também sinaliza em qual tabela sera adicionado, logo em seguida o PopUp é chamado
     * Acionado ao ser clicado o botão de Adicionar o produto da TableView do atributo6.
     * @see MenuController
     * @see Temp
     * @see PopUpController
     * @throws IOException
     */
    public void adicionarProdutoBotaoAction() throws IOException {
    	MenuController m = new MenuController();
    	Temp.getInstance().setQualFuncaoDialog("adicionarProduto");
    	Temp.getInstance().setTabelaQueSeraAdicionadaOObjeto(tabelaAtributo5);
    	m.dialog();

    }
    
    /**
   	 * Responável por cadastar um produto no sistema. Depois de verificar se os campos foram preenchidos corretamente, chama o método 
   	 * da classe "GerenciadorProduto", que, passado as informações digitadas nos campos de textos, cria o novo produto.
   	 * Método é chamado na classe "PopUpController".
   	 * @see PopUpController
   	 * @see Produto
   	 */
    public boolean adicionarProduto(String nome, String preco, String validade, String quantidade, Label mensagem) throws ParseException {
    	boolean tudoCerto = verificaInformacoesProduto(nome, quantidade, preco, validade, mensagem);
    	if(tudoCerto) {
    		Produto pro = GerenciadorProduto.cadastrarPRO(nome, quantidade, preco, validade);
    		Temp.getInstance().setProdutoSelecionado(pro);
    		mensagem.setText("Produto adicionado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
			
    	}
    	else {
    		tudoCerto = false;
    	}
    	return tudoCerto;
    }
    
    /**
   	 * Responsável por atualizar um produto do sistema. Depois de verificar se os campos estam preenchidos corretamente, é chamado o método da classe "GerenciadorProduto", que, 
   	 * passado as informações digitadas pelo usuário nos campos de textos, atualiza o produto passado, atributo por atributo.
   	 * Método é chamado na classe "PopUpController".
   	 * @see PopUpController
   	 * @see Item
   	 */
    public void atualizarProduto(String nome, String preco, String validade, String quantidade, Label mensagem) throws ParseException {
    	Produto produto = (Produto) Temp.getInstance().getProdutoSelecionado();
    	Estoque estoque = (Estoque) Temp.getInstance().getElementoSelecionado();
    	boolean tudoCerto = verificaInformacoesProduto(nome, quantidade, preco, validade, mensagem);
    	
    	if(tudoCerto) {
    		GerenciadorProduto.editarPRO(produto, nome, preco, validade, quantidade);
    		mensagem.setText("Produto atualizado com sucesso!");
			mensagem.setTextFill(Color.GREEN);
			mensagem.setVisible(true);
    	}
    }
    
    
    /**
     * Passa as informações presentes no mapa de tipo <String, Double> para as listView1 e listView2.
     * @param mapa - Mapa a ser extraído o conteúdo.
     */
    private void preencheListViewProdutosNecessarios(Map<String, Double> mapa){
    	
    	for (Map.Entry<String, Double> elemento : mapa.entrySet()) {
		    
    		listView1.getItems().add(elemento.getKey());
    		listView2.getItems().add(elemento.getValue());
		}
    }
    
    /**
     * Passa as informações presentes no mapa de tipo <String, String> para as listView1 e listView2.
     * @param mapa - Mapa a ser extraído o conteúdo.
     */
    private void preencheListViewProdutos(Map<String, String> mapa) {
    	
        for (Map.Entry<String, String> elemento : mapa.entrySet()) {
		    
    		listView1.getItems().add(elemento.getKey());
    		listView2.getItems().add(elemento.getValue());
		}
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a cliente. 
     * Verifica se há algum campo vazio, e se todos formatos estão corretos (cpf, email, telefone).
     * @see ExpressoesRegulares
     * @return um boolean que será true se todas informações estiverem corretas, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesCliente() {
    	
    	boolean tudoCerto = false;
    	
    	boolean todoPreenchido = !textFieldAtributo1.getText().isEmpty() && !textFieldAtributo1.getText().isBlank() && 
    			!textFieldAtributo2.getText().isEmpty() && !textFieldAtributo2.getText().isBlank() && 
    			!textFieldAtributo3.getText().isEmpty() && !textFieldAtributo3.getText().isBlank() && 
    			!textFieldAtributo4.getText().isEmpty() && !textFieldAtributo4.getText().isBlank();
    	
    	boolean validaCpf = ExpressoesRegulares.getInstance().matchesCPF(textFieldAtributo2.getText());
		boolean validaEmail = ExpressoesRegulares.getInstance().matchesEmail(textFieldAtributo3.getText());
		boolean validaTelefone = ExpressoesRegulares.getInstance().matchesTelefone(textFieldAtributo4.getText());
		
		if(validaCpf && validaEmail && validaTelefone && todoPreenchido) {
			tudoCerto = true;
		}
		else if(!validaCpf) {
			tudoCerto = false;
			mensagem.setText("CPF inválido! Verifique se há espaços em branco antes ou depois.");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
		}
		else if(!validaEmail) {
			tudoCerto = false;
			mensagem.setText("Email inválido! Verifique se há espaços em branco antes ou depois.");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
		}
		else if(!validaTelefone) {
			
			mensagem.setText("Telefone inválido! Verifique se há espaços em branco antes ou depois.");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
			tudoCerto = false;
		}
		else if(!todoPreenchido) {
			tudoCerto = false;
			mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
		}
		return tudoCerto;
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a usuário. 
     * Verifica se há algum campo vazio.
     * @return um boolean que será true se nenhum campo estiver vazio, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesUsuario() {
    	boolean tudoCerto = false;
    	boolean preenchido = !textFieldAtributo1.getText().isBlank() && !textFieldAtributo1.getText().isEmpty() && !textFieldAtributo2.getText().isBlank() && !textFieldAtributo2.getText().isEmpty();
    	
    	if(preenchido) {
    		tudoCerto = true;
    	}
    	else if(!preenchido) {
    		tudoCerto = false;
    		mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	return tudoCerto;
    }
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a fornecedor. 
     * Verifica se há algum campo vazio, e se todos formatos estão corretos (cnpj).
     * @see ExpressoesRegulares
     * @return um boolean que será true se todas informações estiverem corretas, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesFornecedor(){
    	
    	boolean tudoCerto = false;
    	boolean todoPreenchido = !textFieldAtributo1.getText().isEmpty() && !textFieldAtributo1.getText().isBlank() && 
    			!textFieldAtributo2.getText().isEmpty() && !textFieldAtributo2.getText().isBlank() && 
    			!textFieldAtributo3.getText().isEmpty() && !textFieldAtributo3.getText().isBlank();
    	boolean validaCnpj = ExpressoesRegulares.getInstance().matchesCNPJ(textFieldAtributo2.getText());
    	
    	if(validaCnpj && todoPreenchido) {
    		tudoCerto = true;
    	}
    	else if(!validaCnpj) {
    		tudoCerto = false;
    		mensagem.setText("CNPJ inválido! Verifique se há espaços em branco antes ou depois.");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	else if(!todoPreenchido) {
    		tudoCerto = false;
    		mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	return tudoCerto;
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a funcionario. 
     * Verifica se há algum campo vazio.
     * @return um boolean que será true se nenhum campo estiver vazio, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesFuncionario() {
    	boolean tudoCerto = false;
    	boolean preenchido = !textFieldAtributo1.getText().isBlank() && !textFieldAtributo1.getText().isEmpty() &&
    			             !textFieldAtributo2.getText().isBlank() && !textFieldAtributo2.getText().isEmpty()
    			             && !choiceBoxAtributo3.getValue().isEmpty() && !choiceBoxAtributo3.getValue().isBlank();
    	
    	if(preenchido) {
    		tudoCerto = true;
    	}
    	else if(!preenchido) {
    		tudoCerto = false;
    		mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	return tudoCerto;
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a venda. 
     * Verifica se há algum campo vazio, e se todos formatos estão corretos (preço, data, hora, id do cliente).
     * @see ExpressoesRegulares
     * @return um boolean que será true se todas informações estiverem corretas, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesVenda(){
    	
    	boolean tudoCerto = false;
    	
    	boolean todoPreenchido = !textFieldAtributo2.getText().isEmpty() && !textFieldAtributo2.getText().isBlank() && 
    			!choiceBoxAtributo3.getValue().isEmpty() && !choiceBoxAtributo3.getValue().isBlank() && 
    			!textFieldAtributo4.getText().isEmpty() && !textFieldAtributo4.getText().isBlank() &&
    			!textAreaAtributo5.getText().isEmpty() && !textAreaAtributo5.getText().isBlank();
    	
    	boolean validaPreco = !textFieldAtributo3.getText().contains(".");
    	boolean validaData = ExpressoesRegulares.getInstance().matchesData(textFieldAtributo2.getText());
    	boolean validaHora = ExpressoesRegulares.getInstance().matchesHora(textFieldAtributo7.getText());
    	boolean validaIdCliente = ExpressoesRegulares.getInstance().matchesIdCliente(textAreaAtributo5.getText());
    	
        if(textAreaAtributo5.getText().isBlank() || textAreaAtributo5.getText().isEmpty()) {
    		validaIdCliente = true;
    	}
    	
    	if(validaPreco && todoPreenchido && validaData && validaHora && validaIdCliente) {
    		tudoCerto = true;
    	}
    	else if(!validaPreco) {
    		tudoCerto = false;
    		mensagem.setText("Preço inválido! Se estiver usando ponto (.) use vírgula (,) no lugar!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	else if(!todoPreenchido) {
    		tudoCerto = false;
    		mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	else if(!validaData) {
    		tudoCerto = false;
    		mensagem.setText("Data inválida! A data precisa estar nesse formato: dd/mm/aaaa");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	else if(!validaHora) {
    		tudoCerto = false;
    		mensagem.setText("Hora inválida! A Hora precisa estar nesse formato: hh:mm:ss");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	else if(!validaIdCliente) {
    		tudoCerto = false;
    		mensagem.setText("Id de Cliente inválido! Verifique se há espaços em branco antes ou depois");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	return tudoCerto;
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação ao item. 
     * Verifica se há algum campo vazio, e se todos formatos estão corretos (preço).
     * @return um boolean que será true se todas informações estiverem corretas, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesItem(){
    	
    	boolean tudoCerto = false;
    	boolean todoPreenchido = !textFieldAtributo1.getText().isEmpty() && !textFieldAtributo1.getText().isBlank() && 
    			!textFieldAtributo2.getText().isEmpty() && !textFieldAtributo2.getText().isBlank() && 
    			!textFieldAtributo3.getText().isEmpty() && !textFieldAtributo3.getText().isBlank() &&
    			!textAreaAtributo5.getText().isEmpty() && !textAreaAtributo5.getText().isBlank() && !listView1.getItems().isEmpty();
    	boolean validaPreco = !textFieldAtributo3.getText().contains(".");
    	
    	if(validaPreco && todoPreenchido) {
    		tudoCerto = true;
    	}
    	else if(!validaPreco) {
    		mensagem.setText("Preço inválido! Se estiver usando ponto (.) use vírgula (,) no lugar!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	else if(!todoPreenchido) {
    		tudoCerto = false;
    		mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	return tudoCerto;
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a estoque. 
     * Verifica se há algum campo vazio.
     * @return um boolean que será true se nenhum campo estiver vazio, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesEstoque(){
    	
    	boolean tudoCerto = false;
    	boolean todoPreenchido = !textFieldAtributo1.getText().isEmpty() && !textFieldAtributo1.getText().isBlank() &&
    			                 !tabelaAtributo5.getItems().isEmpty() && !listView1.getItems().isEmpty();
    	
    	if(todoPreenchido) {
    		tudoCerto = true;
    	}
    	else if(!todoPreenchido) {
    		tudoCerto = false;
    		mensagem.setText("Todos campos devem estar preenchidos!");
			mensagem.setTextFill(Color.RED);
			mensagem.setVisible(true);
    	}
    	return tudoCerto;
    }
    
    /**
     * Responsável por verificar informações fornecidas pelo usuário em relação a produto. 
     * Verifica se há algum campo vazio, e se todos formatos estão corretos (preço, validade).
     * @see ExpressoesRegulares
     * @see PopUpController
     * @return um boolean que será true se todas informações estiverem corretas, e false se ocorrer o contrário.
     */
    private boolean verificaInformacoesProduto(String nome, String quantidade, String preco, String validade, Label msg){
    	
    	boolean tudoCerto = false;
    	boolean todoPreenchido = !nome.isEmpty() && !nome.isBlank() && 
    			!quantidade.isEmpty() && !quantidade.isBlank() && 
    			!preco.isEmpty() && !preco.isBlank() &&
    			!validade.isEmpty() && !validade.isBlank();
    	boolean validaPreco = !preco.contains(".");
    	boolean validaValidade = ExpressoesRegulares.getInstance().matchesData(validade);
    	
    	if(validaPreco && todoPreenchido && validaValidade) {
    		tudoCerto = true;
    	}
    	else if(!validaPreco) {
    		msg.setText("Preço inválido! Se estiver usando ponto (.) use vírgula (,) no lugar!");
			msg.setTextFill(Color.RED);
			msg.setVisible(true);
    	}
    	else if(!todoPreenchido) {
    		tudoCerto = false;
    		msg.setText("Todos campos devem estar preenchidos!");
    		msg.setTextFill(Color.RED);
    		msg.setVisible(true);
    	}
    	else if(!validaValidade) {
    		tudoCerto = false;
    		msg.setText("Data inválida! Formato aceito: dd/mm/aaaa. Verifique se há espaços em branco antes ou depois");
    		msg.setTextFill(Color.RED);
    		msg.setVisible(true);
    	}
    	return tudoCerto;
    }
    /**
     * Passa as informações presentes nas listView1 e listView2 para um mapa de tipo <String, Double>. 
     */
    private Map<String, Double> passaListViewParaMapStringDouble(){
    	Map<String, Double> mapa = new HashMap<String, Double>();
    	for(Object chave: listView1.getItems()) {
    		for(Object valor: listView2.getItems()) {
    			double valorDouble = Double.parseDouble(valor.toString());
    			mapa.put(chave.toString(), valorDouble);
    		}
    	}
    	return mapa;
    }
    
    /**
     * Passa as informações presentes nas listView1 e listView2 para um mapa de tipo <String, String>. 
     */
    private Map<String, String> passaListViewParaMapStringString(){
    	Map<String, String> mapa = new HashMap<String, String>();
    	for(Object chave: listView1.getItems()) {
    		for(Object valor: listView2.getItems()) {
    			mapa.put(chave.toString(), valor.toString());
    		}
    	}
    	return mapa;
    }
    
    /**
     * Método responsável por observar a TableView informada e salvar o elemento clicado caso seja o caso de apenas um click, caso seja dois, o usuário
	 * será direcionado ao PopUp e as próximas informações serão exibidas lá.
	 * @see Temp
	 * @see PopController
	 * @see MouseEvent
     * @param tabela - TableView a ser observada.
     */
    private void clickEventTabela(TableView tabela) {
    	MenuController m = new MenuController();
    	tabela.setOnMouseClicked(new EventHandler<MouseEvent>() { 
		    @Override
		    public void handle(MouseEvent click) {
		          itemSelecionado = tabela.getSelectionModel().getSelectedItem();
		          String classe = Temp.getInstance().getElementoSelecionado().getClass().getSimpleName();
		          if(classe.equals("Estoque")) {
		        	  if (click.getClickCount() == 2) {
		        		  tabela.getSelectionModel().getSelectedItem();
		        		  Temp.getInstance().setQualFuncaoDialog("editarProduto"); 
                          Produto produto = (Produto) itemSelecionado;
		        		  Temp.getInstance().setProdutoSelecionado(produto);
		        		  Temp.getInstance().setTabelaQueSeraAdicionadaOObjeto(tabelaAtributo5);
		      	    	  try {
							m.dialog();
						  } catch (IOException e) {
							e.printStackTrace();
						  }
			           }
		          }
		    }
		});
    }
    /**
     * Método responsável por observar a ListView e salvar o elemento clicado.
     * @see MouseEvent
     * @param list - ListView a ser observado.
     */
    private void clickEventList(ListView list) {
    	
    	list.setOnMouseClicked(new EventHandler<MouseEvent>() { 
		    @Override
		    public void handle(MouseEvent click) {
		          itemSelecionado = list.getSelectionModel().getSelectedItem();
		    }
		});
    }

}
