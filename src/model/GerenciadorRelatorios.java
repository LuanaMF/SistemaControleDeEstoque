package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controller.Main;


/**
 * Classe Gerenciadora de Reatórios, contendo todos métodos usados para gerar os relatórios.
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorRelatorios {
	/**
	 * Gera um pdf, dado um título, um parágrafo( lista/mapa contendo as infomações solicitadas pelo usuário) e um nome para o arquivo.
	 * @see Document
	 * @see PdfWriter
	 * @param nomeDoArquivo - Nome do arquivo.
	 * @param titulo - Título do documento
	 * @param paragrafo - Conteúdo do documento
	 */
	public static void geraPDF(String nomeDoArquivo, String paragrafo, String titulo) {
		Document document = new Document(); // cria o documento
        try {

            PdfWriter.getInstance(document, new FileOutputStream(nomeDoArquivo)); 
            document.open();

            // adiciona parágrafo
            document.add(new Paragraph(paragrafo));
            document.addTitle(titulo);
        }
        catch(DocumentException f) {
            System.err.println(f.getMessage());
        }
        catch(IOException ioe) {
           System.err.println(ioe.getMessage());
        }
        
        document.close();
	}
	/**
	 * Reuni todas as compras realizadas em um determinado período de tempo, definido pelo usuário, em uma lista que irá para o relatório.
	 * @param inicio - Data inicio do periodo de tempo.
	 * @param fim - Data fim do periodo de tempo.
	 * @return a lista que irá para o relatório.
	 * @throws ParseException
	 */
	public static List<Venda> vendasPorPeriodo(Date inicio, Date fim) throws ParseException { 
		List<Venda> lista = BancoDados.getInstance().getListaVEN();
		List<Venda> vendasPeriodo = new ArrayList<Venda>(); // lista a ser retornada
		Date data = new Date();
		for(Venda venda: lista) { //itera a lista
			data = venda.getData_Horario();
			if(data.equals(inicio) || data.equals(fim)) { // verifica se a data da venda é igual a de inicio ou fim
				vendasPeriodo.add(venda);
			}
			if(data.before(fim) && data.after(inicio)) { // verifica se a data da venda está entre a d de inicio e fim
				vendasPeriodo.add(venda);
			}
		}
		return vendasPeriodo;
	}
	/**
	 * Reuni todas as compras realizadas contendo um item de uma categoria especifica de prato(sobremesa, prato principal etc),
	 * definida pelo usuário, em uma lista que irá para o relatório.
	 * @param tipo - Categoria a ser procurada
	 * @return lista que irá para o relatório.
	 */
	public static List<Venda> vendasTipoPrato(String tipo) {
		List<Venda> lista = BancoDados.getInstance().getListaVEN();
		List<Venda> vendasPrato = new ArrayList<Venda>();
		for(Venda venda: lista) { // itera a lista de venda
			List<Item> itens = venda.getItens();
			for(Item item: itens) { // itera a lista de itens daquela venda
				if(item.getCategoria().equalsIgnoreCase(tipo)) { // verifica se há algum da categpria especificada
					vendasPrato.add(venda);
					break;
				}
			}
		}
		return vendasPrato;
	}
	/**
	 * Consulta o estoque de um determinado produto.
	 * @param id - Id do produto.
	 * @return lista contendo o estoque do produto, que irá para o relatório.
	 */
	public static List<Produto> estoquePorProduto(String id) {
		List<Estoque> lista = BancoDados.getInstance().getListaEstoque();
		String str = Main.separaId(id, "index");
		int index = Integer.parseInt(str);
		List<Produto> estoquePorProduto = lista.get(index).getEstoque();
		return estoquePorProduto;
	}
	/**
	 * Reuni todo estoque a vencer em um determinado período de tempo, definido pelo usuário, em uma lista que irá para o relatório.
	 * @param inicio - Data inicio do periodo de tempo.
	 * @param fim - Data fim do periodo de tempo.
	 * @return a lista que irá para o relatório.
	 * @throws ParseException
	 */
	public static List<Produto> estoqueAVencer(Date inicio, Date fim) throws ParseException {
		List<Produto> lista = BancoDados.getInstance().getListaPRO();
		List<Produto> estoqueAvencer = new ArrayList<Produto>();
		Date data = new Date();
		for(Produto produto: lista) { //itera a lista
			data = produto.getValidadeDate();
			if(data.equals(inicio) || data.equals(fim)) { // verifica se a validade do produto é igual a data de inicio ou fim.
				estoqueAvencer.add(produto);
			}
			if(data.before(fim) && data.after(inicio)) { // verifica se a validade está entre a data de inicio e fim
				estoqueAvencer.add(produto);
			}
		}
		return estoqueAvencer;	
		
	}
	/**
	 * Reuni todos os fornecedores de um determinado produto, definido pelo usuário, em um mapa que irá para o relatório.
	 * @param id - Id do produto
	 * @return Mapa contendo os fornecedores daquele produto que irá para o relatório.
	 */
	public static Map<String, String> fornecedorPorProduto(String id) {
		List<Estoque> lista = BancoDados.getInstance().getListaEstoque();
		String str = Main.separaId(id, "index");
		int index = Integer.parseInt(str);
		Map<String,String> fornecedorPorProduto = lista.get(index).getFornecedores();
		return fornecedorPorProduto;
	}
	/**
	 * Reuni todos os produtos de um determinado fornecedor, definido pelo usuário, em um mapa que irá para o relatório.
	 * @param id - Id do fornecedor
	 * @return Mapa contendo os produtos fornecidos pelo fornecedor informado, que irá para o relatório.
	 */
	public static Map<String, String> fornecedorPorFornecedor(String id) {
		List<Estoque> lista = BancoDados.getInstance().getListaEstoque();
		Map<String, String> fornecedoresPorFornecedor = new HashMap<String, String>();
		for(Estoque estoque: lista) {
			Map<String, String> fornecedores = estoque.getFornecedores();
			fornecedores.entrySet().forEach(entry->{ // itera o mapa
				if(entry.getKey().equalsIgnoreCase(id)) {
					fornecedoresPorFornecedor.put(estoque.getId(), estoque.getNome());
				}  
			}); 	
		}
		return fornecedoresPorFornecedor;
	}
	
}
