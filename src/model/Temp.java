package model;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
/**
 * Classe de dados temporários(dados diversos que precisam ser acessados em diferente partes do programa) do sistema, implementado 
 * usando o padrão de design Singleton, onde limita a classe em apenas uma instancia, que pode ser usada em todo o projeto
 * @author Luana de Melo Fraga
 *
 */
public class Temp {
	
	private Usuario usuarioLogado;
	private String qualTableViewExibir;
	private Object elementoSelecionado;
	private String qualFuncaoDialog;
	private String qualRelatorioGerar;
	private TableView tabelaQueSeraAdicionadaOObjeto;
	private ListView listViewQueSeraAdicionadaAChave, listViewQueSeraAdicionadoOValor;
	private String qualElementoEstaAdicionando;
	private String principalFuncao;
	private Produto produtoSelecionado;
	private static Temp instance;
	
	// construtor privado
	private Temp() {
		
	}
	
	/**
	 * Método referente ao padrão Singleton. Nele é verificado, primeiramente, se a instancia (única)
	 * da classe ja foi criada, se não, ela é instanciada. Por fim é retornada a instancia.
	 * @return instancia única da classe.
	 */
	public static synchronized Temp getInstance() {
		if(instance == null) {
			instance = new Temp();
		}
		return instance;
	}
	
	//getters e setters
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	public String getQualTableViewExibir() {
		return qualTableViewExibir;
	}


	public void setQualTableViewExibir(String qualTableViewExibir) {
		this.qualTableViewExibir = qualTableViewExibir;
	}


	public Object getElementoSelecionado() {
		
		
		return elementoSelecionado;
	}


	public void setElementoSelecionado(Object elementoSelecionado) {
		this.elementoSelecionado = elementoSelecionado;
	}


	public String getQualFuncaoDialog() {
		return qualFuncaoDialog;
	}


	public void setQualFuncaoDialog(String qualFuncaoDialog) {
		this.qualFuncaoDialog = qualFuncaoDialog;
	}


	public String getQualRelatorioGerar() {
		return qualRelatorioGerar;
	}


	public void setQualRelatorioGerar(String qualRelatorioGerar) {
		this.qualRelatorioGerar = qualRelatorioGerar;
	}


	public TableView getTabelaQueSeraAdicionadaOObjeto() {
		return tabelaQueSeraAdicionadaOObjeto;
	}


	public void setTabelaQueSeraAdicionadaOObjeto(TableView tabelaQueSeraAdicionadaOObjeto) {
		this.tabelaQueSeraAdicionadaOObjeto = tabelaQueSeraAdicionadaOObjeto;
	}

	public ListView getListViewQueSeraAdicionadoOValor() {
		return listViewQueSeraAdicionadoOValor;
	}


	public void setListViewQueSeraAdicionadoOValor(ListView listViewQueSeraAdicionadoOValor) {
		this.listViewQueSeraAdicionadoOValor = listViewQueSeraAdicionadoOValor;
	}


	public ListView getListViewQueSeraAdicionadaAChave() {
		return listViewQueSeraAdicionadaAChave;
	}


	public void setListViewQueSeraAdicionadaAChave(ListView listViewQueSeraAdicionadaAChave) {
		this.listViewQueSeraAdicionadaAChave = listViewQueSeraAdicionadaAChave;
	}


	public String getQualElementoEstaAdicionando() {
		return qualElementoEstaAdicionando;
	}


	public void setQualElementoEstaAdicionando(String qualElementoEstaAdicionando) {
		this.qualElementoEstaAdicionando = qualElementoEstaAdicionando;
	}


	public String getPrincipalFuncao() {
		return principalFuncao;
	}


	public void setPrincipalFuncao(String principalFuncao) {
		this.principalFuncao = principalFuncao;
	}


	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}


}
