package model;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import controller.Main;

/**
 * Classe contendo os métodos completos para gerar um relatório, implementado usando o padrão de design Facade, que "facilita" a interface,
 * deixando o processo de gerar um relatório mais simples (chamando apenas um método).
 * @author Luana de Melo Fraga
 *
 */
public class FacadeGerarRelatorio {
	
	/**
	 * Gera relatório de vendas.
	 * Ultiliza do método da classe 'MainView' chamado 'listaString' que transforma uma lista de objeto em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "listaVendas" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "listaVendas" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioTodasVendas() {
		
		String listaVendas = Main.listaString(BancoDados.getInstance().getListaVEN());
		if(listaVendas == "_______________________") {
			listaVendas = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Vendas_Realizadas.pdf", listaVendas, "Vendas");
		}
		return listaVendas;
	}
	
	/**
	 * Gera relatório de estoque.
	 * Ultiliza do método da classe 'MainView' chamado 'listaString' que transforma uma lista de objeto em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "estoque" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "estoque" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioEstoque() {
       
		String estoque = Main.listaString(BancoDados.getInstance().getListaEstoque());
		if(estoque == "_______________________") {
			estoque = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Estoque.pdf", estoque, "Estoque");
		}
		return estoque;
	}
	
	/**
	 * Gera relatório de vendas por categoria.
	 * Ultiliza do método da classe 'MainView' chamado 'listaString' que transforma uma lista de objeto em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "vendasPorCategoria" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "vendasPorCategoria" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioVendasPorCategoria(String categoria) {
		
		List<Venda> vendasPorCategoria = GerenciadorRelatorios.vendasTipoPrato(categoria);
		String listaVendasPorCategoria = Main.listaString(vendasPorCategoria);
		if(listaVendasPorCategoria == "_______________________") {
			listaVendasPorCategoria = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Vendas_" + categoria + ".pdf", listaVendasPorCategoria, "Vendas de " + categoria);
		}
		return listaVendasPorCategoria;
	}
	
	/**
	 * Gera relatório de Estoque por produto.
	 * Ultiliza do método da classe 'MainView' chamado 'listaString' que transforma uma lista de objeto em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "estoquePorProduto" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "estoquePorProduto" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioEstoquePorProduto(String idProduto) {
		
		List<Produto> estoquePorProduto = GerenciadorRelatorios.estoquePorProduto(idProduto);
		String listaEstoquePorProduto = Main.listaString(estoquePorProduto);
		if(listaEstoquePorProduto == "_______________________") {
			listaEstoquePorProduto = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Estoque_" + idProduto + ".pdf", listaEstoquePorProduto, "Estoque do produto " + idProduto);
		}
		return listaEstoquePorProduto;
	}
	
	/**
	 * Gera relatório de Fornecedores por produto.
	 * Ultiliza do método da classe 'MainView' chamado 'mapString' que transforma um mapa em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "fornecedorPorProduto" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "fornecedorPorProduto" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioFornecedorPorProduto(String idProdutof) {
		
		
		Map<String, String> fornecedorPorProduto = GerenciadorRelatorios.fornecedorPorProduto(idProdutof);
		String listaFornecedorPorProduto = Main.mapString(fornecedorPorProduto, "ID", "Nome"); 
		System.out.println(listaFornecedorPorProduto);
		if(listaFornecedorPorProduto == null) {
			listaFornecedorPorProduto = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Fornecedores_" + idProdutof + ".pdf", listaFornecedorPorProduto, "Fornecedores do produto " + idProdutof);
		}
		return listaFornecedorPorProduto;
	}
	/**
	 * Gera relatório de Fornecedores por fornecedor.
	 * Ultiliza do método da classe 'MainView' chamado 'mapString' que transforma um mapa em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "fornecedorPorFornecedor" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "fornecedorPorFornecedor" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioFornecedorPorFonecedor(String idFornecedor) {
		
		Map<String, String> fornecedorPorFornecedor = GerenciadorRelatorios.fornecedorPorFornecedor(idFornecedor);
		String listaFornecedorPorFornecedor = Main.mapString(fornecedorPorFornecedor, "ID", "Nome");
		
		if(listaFornecedorPorFornecedor == null) {
			listaFornecedorPorFornecedor = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Fornecedores_" + idFornecedor + ".pdf", listaFornecedorPorFornecedor, "Produtos do fornecedor " + idFornecedor);
		}
		return listaFornecedorPorFornecedor;
	}
	/**
	 * Gera relatório de vendas por período.
	 * Ultiliza do método da classe 'MainView' chamado 'listaString' que transforma uma lista de objeto em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "vendaPorPeriodo" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "vendaPorPeriodo" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioVendasPorPeriodo(Date dataInicio, Date dataFim) throws ParseException {
		List<Venda> vendaPorPeriodo = GerenciadorRelatorios.vendasPorPeriodo(dataInicio, dataFim);
		String listaPorPeriodo = Main.listaString(vendaPorPeriodo);
		if(listaPorPeriodo == "_______________________") {
			listaPorPeriodo = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Vendas_PorPeriodo.pdf", listaPorPeriodo, "De " + dataInicio + " Até " + dataFim);
		}
		return listaPorPeriodo;
	} 
	/**
	 * Gera relatório de estoque a vencer.
	 * Ultiliza do método da classe 'MainView' chamado 'listaString' que transforma uma lista de objeto em uma string, e a retorna.
	 * Caso a lista é retornada "vazia", a string "estoqueAvencer" será null. Após verificar se o retorno do método não está vazio, o documento
	 * é gerado.
	 * @return a string "estoqueAvencer" para indentificar se o documento foi criado.
	 */
	public static String geraRelatorioEstoqueAVencer(Date dataInicio, Date dataFim) throws ParseException {
		List<Produto> estoqueAvencer = GerenciadorRelatorios.estoqueAVencer(dataInicio, dataFim);
		
		String listaAvencer = Main.listaString(estoqueAvencer);
		if(listaAvencer == "_______________________") {
			listaAvencer = null;
		}
		else {
			GerenciadorRelatorios.geraPDF("Estoque_A_Vencer.pdf", listaAvencer, "De " + dataInicio + " Até " + dataFim);
		}
		return listaAvencer;
	}
	
}   
