package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BancoDados;
import model.ExpressoesRegulares;
import model.Fornecedor;
import model.Item;
import model.Produto;
import model.Temp;

/**
 * Classe controller do PopUp do sistema, responsável por gerenciar todas ações do usuário que acontecem(ou devem acontecer) nela.
 * @author Luana de Melo Fraga
 *
 */
public class PopUpController {
	
    // elementos da tela
	@FXML
	private Label label1;
	@FXML
	private Label label2, label3, label4;
	@FXML
	private TextField textField1;
	@FXML
	private TextField textField2, textField3, textField4;
	@FXML
	private ChoiceBox<String> choiceBox1;
	@FXML
	private Label mensagem;
	@FXML
	private Button botao;
	
	private List<Produto> produtosEstoque = new ArrayList<Produto>();
	
	/**
	 *  Método padrão initialize, onde, assim que o arquivo "fxml" que essa classe controla for carregado(iniciado), ele será executado.
	 *  Nela é chamado o método "configuraPopUp" que gerencia a interface da dela.
	 */
	@FXML
	private void initialize() {
		
		configuraPopUp(Temp.getInstance().getQualFuncaoDialog());
	}
	
	/**
	 * Responsável por gerenciar a interface do popUp (o que o usuário irá ver), a depender a função do popUp, atribuida anteriormente
	 * no sistema, antes da tela ser iniciada.
	 * @see TelaCRUDController
	 * @see ExibirListasController
	 * @param qualFuncao - Função do popUp
	 */
	private void configuraPopUp(String qualFuncao) {
	    	
	    	if(qualFuncao.equals("pegarIdProduto")) {
	    		
	    		label1.setText("ID do produto: ");
	    		label2.setVisible(false);
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		textField2.setVisible(false);
	    		textField2.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Gerar relatório");
	    		
	    	}
	    	else if(qualFuncao.equals("pegarIdFornecedor")) {
	    		
	    		label1.setText("ID do fornecedor: ");
	    		label2.setVisible(false);
	    		textField2.setVisible(false);
	    		textField2.setDisable(true);
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Gerar relatório");
	    		
	    	}
	    	else if(qualFuncao.equals("pegarIdItem")) {
	    		
	    		label1.setText("ID do Item: ");
	    		label2.setVisible(false);
	    		textField2.setVisible(false);
	    		textField2.setDisable(true);
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Adicionar item");
	    	}
	    	else if(qualFuncao.equals("pegarDatas")) {
	    		
	    		label1.setText("Data início: ");
	    		label2.setText("Data fim: ");
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Gerar relatório");
	    		
	    	}
	        else if(qualFuncao.equals("pegarCategoria")) {
	    		
	        	choiceBox1.setItems(categorias());
	    		label1.setText("Categoria: ");
	    		textField1.setVisible(false);
	    		textField1.setDisable(true);
	    		label2.setVisible(false);
	    		textField2.setVisible(false);
	    		textField2.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Gerar relatório");
	    		
	    	}
	        else if(qualFuncao.equals("pegarIdProdutoNecessario")) {
	        	label1.setText("Id do produto: ");
	    		label2.setText("Qnt(ml, g): ");
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Adicionar produto");
	        }
            else if(qualFuncao.equals("pegarIdFornecedorEstoque")) {
	    		
	    		label1.setText("ID do fornecedor: ");
	    		label2.setVisible(false);
	    		textField2.setVisible(false);
	    		textField2.setDisable(true);
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setVisible(false);
	    		textField3.setVisible(false);
	    		textField3.setDisable(true);
	    		label4.setVisible(false);
	    		textField4.setVisible(false);
	    		textField4.setDisable(true);
	    		botao.setText("Adicionar fornecedor");
	    		
	    	}
            else if(qualFuncao.equals("editarProduto")) {
	    		Produto produto = Temp.getInstance().getProdutoSelecionado();
	    		
            	label1.setText("Nome: ");
        		textField1.setText(produto.getNome());
        		label2.setText("Quantidade: ");
        		String quantidade = String.valueOf(produto.getQuantidadeDouble());
        		textField2.setText(quantidade);
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setText("Preço: ");
	    		String valor = String.format("%,.2f", produto.getPrecoDouble());
	    		textField3.setText(valor);
	    		label4.setText("Validade: ");
	    		textField4.setText(produto.getValidade());
	    		
	    		botao.setText("Atualizar");
	    		
	    	}
            else if(qualFuncao.equals("adicionarProduto")) {
	    		Produto produto = Temp.getInstance().getProdutoSelecionado();
	    		
            	label1.setText("Nome: ");
        		label2.setText("Quantidade: ");
	    		choiceBox1.setVisible(false);
	    		choiceBox1.setDisable(true);
	    		label3.setText("Preço: ");
	    		label4.setText("Validade: ");
	    		
	    		botao.setText("Adicionar");
	    		
	    	}
         
	 }
	
	/**
	 * Percorre o cardápio (lista de itens) e preenche uma lista do tipo String com todas categorias presentes.
	 * E usado para preencher uma ChoiceBox.
	 * @return a lista contendo as categorias.
	 */
	private ObservableList<String> categorias() {
	    	
	    	List<Item> cardapio = BancoDados.getInstance().getCardapio();
	    	ObservableList<String> categorias = FXCollections.observableArrayList();
	    	
	    	for (Item item: cardapio) {
	    		if(categorias.contains(item.getCategoria())) {
	    			continue;
	    		}
	    		else{categorias.add(item.getCategoria());}
	    	}
	    	
	    	return categorias;
	}
	
	/**
	 * Método acionado quando o botão(único) da tela é clicado.
	 * A depender da função estabelecida para o PopUp, esse método pode gerar um relatório (indiretamente, chamando um método da MainView),
	 * adicionar uma entidade a uma tabela ou listView, ou então, cadastrar ou atualizar produto (indiretamente, chamando um método da classe
	 * TelaCRUDController).
	 * @see TelaCRUDController
	 * @see MenuController
	 * @see BancoDados
	 * @see Main
	 * @see ExpressoesRegulares
	 * @throws ParseException
	 */
	public void botaoEvent() throws ParseException {
		
		MenuController m = new MenuController();
		
		if(botao.getText().equals("Gerar relatório")) {
			String qualRelatorio = Temp.getInstance().getQualRelatorioGerar();
			
			if(qualRelatorio.equals("VendasPorCategoria")) {
				if(choiceBox1.getValue() != null) {
					m.relatorioVendasPorCategoria(choiceBox1.getValue());
				}
				else {
					
					mensagem.setText("Escolha a categoria para gerar o relatório!");
				}
			}
			else if(qualRelatorio.equals("EstoquePorProduto")) {
				
				if(!textField1.getText().isEmpty() && !textField1.getText().isBlank()) {
					boolean validaIdEstoque = ExpressoesRegulares.getInstance().matchesIdEstoque(textField1.getText());
					if(validaIdEstoque) {
						int index = Main.buscaPorId(BancoDados.getInstance().getListaEstoque(), textField1.getText());
						if(index == -1) {
							mensagem.setText("Produto não encontrado! Verifique o id e tente novamente");
					    }
						m.relatorioEstoquePorProduto(textField1.getText());
					}
					else {
						mensagem.setText("Id inválido para protudo do estoque! Id deve começar com 'EST'");
					}
				}
				else {
					mensagem.setText("Informe o id do produto(estoque) para gerar o relatorio");
				}
			}
				
				
		  else if(qualRelatorio.equals("FornecedorPorProduto")) {
				
				if(!textField1.getText().isEmpty() && !textField1.getText().isBlank()) {
					boolean validaIdEstoque = ExpressoesRegulares.getInstance().matchesIdEstoque(textField1.getText());
					if(validaIdEstoque) {
						int index = Main.buscaPorId(BancoDados.getInstance().getListaEstoque(), textField1.getText());
						if(index == -1) {
							mensagem.setText("Produto não encontrado! Verifique o id e tente novamente");
						}
						m.relatorioFornecedorPorProduto(textField1.getText());
					}
					else {
						mensagem.setText("Id inválido para protudo do estoque! Id deve começar com 'EST'");
					}
				}
				else {
					mensagem.setText("Digite o id do produto para gerar o relatório!");
				}
			}
			else if(qualRelatorio.equals("FornecedorPorFornecedor")) {
				
				if(!textField1.getText().isEmpty() && !textField1.getText().isBlank()) {
					boolean validaIdFornecedor = ExpressoesRegulares.getInstance().matchesIdForncedor(textField1.getText());
					if(validaIdFornecedor) {
						int index = Main.buscaPorId(BancoDados.getInstance().getListaFOR(), textField1.getText());
						if(index == -1) {
							mensagem.setText("Fornecedor não encontrado! Verifique o id e tente novamente");
						}
						else {
						    m.relatorioFornecedorPorFornecedor(textField1.getText());
						}
					}
					else {
						mensagem.setText("Id inválido para fornecedor!");
					}
				}
				else {
					mensagem.setText("Digite o id do fornecedor para gerar o relatório!");
				}
			}
			else if(qualRelatorio.equals("VendasPorPeriodo")) {
				
				if(!textField1.getText().isEmpty() && !textField1.getText().isBlank() && !textField2.getText().isEmpty() && !textField2.getText().isBlank()) {
					boolean validaData = ExpressoesRegulares.getInstance().matchesData(textField1.getText()) && ExpressoesRegulares.getInstance().matchesData(textField2.getText());
					if(validaData) {
						SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
						Date dataInicio = new Date();
						Date dataFim = new Date();
						dataInicio = f.parse(textField1.getText());
						dataFim = f.parse(textField2.getText());
						m.relatorioVendasPorPeriodo(dataInicio, dataFim);
					}
					else {
						mensagem.setText("Formato de data inválido! Formato aceito: dd/mm/aaaa");
					}
				}
				else {
					mensagem.setText("Digite a data de inicio e fim para gerar o relatório!");
				}
			}
			else if(qualRelatorio.equals("EstoqueAVencer")) {
				
				if(!textField1.getText().isEmpty() && !textField1.getText().isBlank() && !textField2.getText().isEmpty() && !textField2.getText().isBlank()) {
					boolean validaData = ExpressoesRegulares.getInstance().matchesData(textField1.getText()) && ExpressoesRegulares.getInstance().matchesData(textField2.getText());
					if(validaData) {
						SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
						Date dataInicio = new Date();
						Date dataFim = new Date();
						dataInicio = f.parse(textField1.getText());
						dataFim = f.parse(textField2.getText());
						m.relatorioEstoqueAVencer(dataInicio, dataFim);
					}
					else {
						mensagem.setText("Formato de data inválido! Formato aceito: dd/mm/aaaa");
					}
				}
				else {
					mensagem.setText("Digite a data de inicio e fim para gerar o relatório!");
				}
			}
		}
		else if(botao.getText().equals("Adicionar item")) {
			
			
			if(!textField1.getText().isBlank() && !textField1.getText().isEmpty()) {
				boolean validaId = ExpressoesRegulares.getInstance().matchesIdItem(textField1.getText());
				if(validaId) {
					int index = Main.buscaPorId(BancoDados.getInstance().getCardapio(), textField1.getText());
					if(index == -1) {
						mensagem.setText("Item não encontrado! Verifique o id e tente novamente");
					}
					else {
					    Item item = BancoDados.getInstance().getCardapio().get(index);
					    Temp.getInstance().getTabelaQueSeraAdicionadaOObjeto().getItems().add(item);
					}
				}
				else {
					mensagem.setText("Id de item inválido! Verifique se o id é correspondente a um item");
				}
			}
			else {
				mensagem.setText("Digite o Id do item para adicioná-lo a venda!");
			}
			
		}
		else if(botao.getText().equals("Adicionar produto")) {
			
			if(!textField1.getText().isEmpty() && !textField1.getText().isBlank() && !textField2.getText().isEmpty() && !textField2.getText().isBlank()) {
				boolean validaIdEstoque = ExpressoesRegulares.getInstance().matchesIdEstoque(textField1.getText());
				if(validaIdEstoque) {
					int index = Main.buscaPorId(BancoDados.getInstance().getCardapio(), textField1.getText());
					if(index == -1) {
						mensagem.setText("Produto não encontrado! Verifique o id e tente novamente");
					}
					else {
					    Temp.getInstance().getListViewQueSeraAdicionadaAChave().getItems().add(textField1.getText());
					    Temp.getInstance().getListViewQueSeraAdicionadoOValor().getItems().add(textField2.getText());
					}
				}
				else {
					mensagem.setText("Id de estoque inválido! Verifique se o id é correspondente a um elemento do estoque");
				}
			}
			else {
				mensagem.setText("Digite o Id do produto e a quantidade necessária para adicioná-lo a venda!");
			}
		}
        else if(botao.getText().equals("Adicionar fornecedor")) {
			
			if(!textField1.getText().isEmpty() && !textField1.getText().isBlank()) {
				boolean validaIdFornecedor = ExpressoesRegulares.getInstance().matchesIdForncedor(textField1.getText());
				if(validaIdFornecedor) {
					int index = Main.buscaPorId(BancoDados.getInstance().getListaFOR(), textField1.getText());
					if(index == -1) {
						mensagem.setText("Fornecedor não encontrado! Verifique o id e tente novamente");
					}
					else {
						Fornecedor fornecedor = BancoDados.getInstance().getListaFOR().get(index);
					    Temp.getInstance().getListViewQueSeraAdicionadaAChave().getItems().add(textField1.getText());
					    Temp.getInstance().getListViewQueSeraAdicionadoOValor().getItems().add(fornecedor.getNome());
					}
				}
				else {
					mensagem.setText("Id de fornecedor inválido! Verifique se o id é pertecente a um fornecedor.");
				}
			}
			else {
				mensagem.setText("Digite o Id do produto e a quantidade necessária para adicioná-lo a venda!");
			}
			
		}
        else if(botao.getText().equals("Atualizar")) {
        	TelaCRUDController t = new TelaCRUDController();
        	t.atualizarProduto(textField1.getText(), textField3.getText(), textField4.getText(), textField2.getText(), mensagem);
        	int index = Temp.getInstance().getTabelaQueSeraAdicionadaOObjeto().getItems().indexOf(Temp.getInstance().getProdutoSelecionado());
        	Temp.getInstance().getTabelaQueSeraAdicionadaOObjeto().getItems().set(index, Temp.getInstance().getProdutoSelecionado());
        }
        else if(botao.getText().equals("Adicionar")) {
        	TelaCRUDController t = new TelaCRUDController();
        	boolean tudoCerto = t.adicionarProduto(textField1.getText(), textField3.getText(), textField4.getText(), textField2.getText(), mensagem);
        	if(tudoCerto) {
	        	Produto pro = Temp.getInstance().getProdutoSelecionado();
	        	produtosEstoque.add(pro);
	    		ObservableList<Produto> obList = FXCollections.observableArrayList(produtosEstoque);
	    		Temp.getInstance().getTabelaQueSeraAdicionadaOObjeto().setItems(obList);
        	}
        }
	}
}
