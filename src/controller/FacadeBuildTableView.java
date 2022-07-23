package controller;

import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BancoDados;
import model.Cliente;
import model.Estoque;
import model.Fornecedor;
import model.Funcionario;
import model.Item;
import model.Produto;
import model.Usuario;
import model.Venda;

/**
 * Classe contendo os métodos completos para preencher a TableView da tela de exibir listas, implementado usando o padrão de design Facade,
 *  que "facilita" a interface, deixando o processo de preencher a TableView mais simples (chamando apenas um método).
 * @author Luana de Melo Fraga 
 *
 */
public class FacadeBuildTableView {
	
	
	private static ObservableList observableList;
	
	/**
	 * Gera a tabela de vendas, pegando a TableView da tela como parâmetros, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de vendas.
	 * @see Venda
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @return a TableView do tipo Venda ja completa.
	 */
	public static TableView<Venda> tableViewVendas(TableView<Venda> tabela) { //
		
		TableColumn<Venda, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Venda, String> colunaData = new TableColumn<>("Data");
		colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
		
		TableColumn<Venda, String> colunaHora = new TableColumn<>("Hora");
		colunaHora.setCellValueFactory(new PropertyValueFactory<>("horario"));
		
		TableColumn<Venda, String> colunaCliente = new TableColumn<>("Cliente");
		colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		
		TableColumn<Venda, String> colunaPreco = new TableColumn<>("Total");
		colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Venda, String> colunaModoDePagamento = new TableColumn<>("M/ Pagamento");
		colunaModoDePagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
		
		TableColumn<Venda, String> colunaItens = new TableColumn<>("Itens");
		colunaItens.setCellValueFactory(new PropertyValueFactory<>("doubleclickrequest"));
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaData, colunaHora, colunaItens, colunaCliente, colunaPreco, colunaModoDePagamento));
		
		observableList = BancoDados.getInstance().getListaVEN();
		tabela.setItems(observableList);
		
		return tabela;
	}
	/**
	 * Gera a tabela de funcionários, pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de funcionários.
	 * @see Funcionario
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @return a TableView do tipo Funcionario ja completa.
	 */
	public static TableView<Funcionario> tableViewFuncionarios(TableView<Funcionario> tabela){ //
		
		TableColumn<Funcionario, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Funcionario, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Funcionario, String> colunaCargo = new TableColumn<>("Cargo");
		colunaCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaCargo));
		
		observableList = BancoDados.getInstance().getListaFUN();
		tabela.setItems(observableList);
		
		return tabela;
		
	}
	
	/**
	 * Gera a tabela de Usuarios, pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de usuários.
	 * @see Usuario
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @return a TableView do tipo Usuario ja completa.
	 */
	public static TableView<Usuario> tableViewUsuarios(TableView<Usuario> tabela) { //
		
		TableColumn<Usuario, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Usuario, String> colunaSenha = new TableColumn<>("Senha");
		colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senhaTable"));
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaSenha));
		
		observableList = BancoDados.getInstance().getUsers();
		tabela.setItems(observableList);
		
		return tabela;		
		
	}
	
	/**
	 * Gera a tabela de Clientes, pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de clientes.
	 * @see Cliente
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @return a TableView do tipo Cliente ja completa.
	 */
	public static TableView<Cliente> tableViewClientes(TableView<Cliente> tabela) { //
		
		TableColumn<Cliente, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Cliente, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cliente, String> colunaCpf = new TableColumn<>("CPF");
		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Cliente, String> colunaEmail = new TableColumn<>("Email");
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Cliente, String> colunaTelefone = new TableColumn<>("Telefone");
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaCpf, colunaEmail, colunaTelefone));
		
		observableList = BancoDados.getInstance().getListaClientes();
		tabela.setItems(observableList);
		
		return tabela;
			
	}
	
	/**
	 * Gera a tabela de fornecedores, pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de fornecedores.
	 * @see Fornecedor
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @return a TableView do tipo Fornecedor ja completa.
	 */
	public static TableView<Fornecedor> tableViewFornecedores(TableView<Fornecedor> tabela) { //
		
		TableColumn<Fornecedor, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Fornecedor, String> colunaCnpj = new TableColumn<>("CNPJ");
		colunaCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		
		TableColumn<Fornecedor, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Fornecedor, String> colunaEndereco = new TableColumn<>("Endereço");
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaCnpj, colunaEndereco));
		
		observableList = BancoDados.getInstance().getListaFOR();
		tabela.setItems(observableList);
		
		return tabela;
	}
	/**
	 * Gera a tabela de produtos, pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de produtos.
	 * @see Produto
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @param lista - Lista de produtos a ser exibida.
	 * @return a TableView do tipo Produto ja completa.
	 */

	public static TableView<Produto> tableViewProdutos(TableView<Produto> tabela, ObservableList<Produto> lista) {
		
		TableColumn<Produto, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Produto, String> colunaValidade = new TableColumn<>("Validade");
		colunaValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
		
		TableColumn<Produto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Produto, String> colunaQuantidade = new TableColumn<>("Em estoque");
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		TableColumn<Produto, String> colunaPreco = new TableColumn<>("Preço");
		colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaValidade, colunaQuantidade, colunaPreco));
		
		observableList = lista;
		tabela.setItems(observableList);
		
		return tabela;
	}
	/**
	 * Gera a tabela de itens(cardapio), pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com o cardápio.
	 * @see Item
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @param lista - Lista de itens a ser exibida.
	 * @return a TableView do tipo Item ja completa.
	 */

	public static TableView<Item> tableViewCardapio(TableView<Item> tabela, ObservableList<Item> lista) { //
		
		TableColumn<Item, String> colunaDescricao = new TableColumn<>("Descrição");
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("sobre"));
		
		TableColumn<Item, String> colunaCategoria = new TableColumn<>("Categoria");
		colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		
		TableColumn<Item, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Item, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Item, String> colunaPreco = new TableColumn<>("Preço");
		colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Item, String> colunaProdutosNecessarios = new TableColumn<>("P/ Necessários");
		colunaProdutosNecessarios.setCellValueFactory(new PropertyValueFactory<>("doubleclickrequest"));
		
		
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaDescricao, colunaCategoria, colunaPreco, colunaProdutosNecessarios));
		
		observableList = lista;
		tabela.setItems(observableList);
		
		return tabela;
	}
	/**
	 * Gera a tabela de estoque, pegando a TableView da tela como parâmetro, nele é adicionado as colunas adequadas, e atribui o que deve ser mostrada
	 * em cada uma. Por fim, preenche a tabela com a lista de estoque.
	 * @see Estoque
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @return a TableView do tipo Estoque ja completa.
	 */

	public static TableView<Estoque> tableViewEstoque(TableView<Estoque> tabela) {
		
		TableColumn<Estoque, String> colunaQuantidadeTotal = new TableColumn<>("Em estoque");
		colunaQuantidadeTotal.setCellValueFactory(new PropertyValueFactory<>("quantidadeTotal"));
		
		TableColumn<Estoque, String> colunaFornecedores = new TableColumn<>("Fornecedores");
		colunaFornecedores.setCellValueFactory(new PropertyValueFactory<>("doubleclickrequest"));
		
		TableColumn<Estoque, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Estoque, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Estoque, String> colunaEstoque = new TableColumn<>("Produtos");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<>("doubleclickrequest"));
			
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaQuantidadeTotal, colunaFornecedores, colunaEstoque));
		
		observableList = BancoDados.getInstance().getListaEstoque();
		tabela.setItems(observableList);
		
		return tabela;
	}
	/**
	 * Gera a tabela de itens, mas dessa vez sem os produtos necessários, pegando a TableView da tela como parâmetro, nele é adicionado 
	 * as colunas adequadas, e atribui o que deve ser mostrada em cada uma. Por fim, preenche a tabela com a lista de itens.
	 * @see Item
	 * @see TableView
	 * @see ExibirListasController
	 * @param tabela - TableView da tela 'ExibirListas' para ser preenchida.
	 * @param lista - Lista de itens a ser exibida.
	 * @return a TableView do tipo Item ja completa.
	 */

	public static TableView<Item> tableViewCardapioSemProdutos(TableView<Item> tabela, ObservableList<Item> lista) { //
		
		TableColumn<Item, String> colunaDescricao = new TableColumn<>("Descrição");
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("sobre"));
		
		TableColumn<Item, String> colunaCategoria = new TableColumn<>("Categoria");
		colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		
		TableColumn<Item, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Item, String> colunaId = new TableColumn<>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Item, String> colunaPreco = new TableColumn<>("Preço");
		colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tabela.getColumns().addAll(Arrays.asList(colunaId, colunaNome, colunaDescricao, colunaCategoria, colunaPreco));
		
		observableList = lista;
		tabela.setItems(observableList);
		
		return tabela;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
