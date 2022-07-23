package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que armazena as expressões regulares do sistema, implementado usando o padrão de design Singleton, onde limita a classe
 * em apenas uma instancia, que pode ser usada em todo o projeto.
 * @see Pattern
 * @see Matcher
 * @author Luana de Melo Fraga
 *
 */
public class ExpressoesRegulares {
	
	private String cpf =  "(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})";
	private String email = "^[A-Za-z0-9+_.-]+@(.+)$";
	private String telefone = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}";
	private String cnpj = "\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}";
	private String idCliente = "(?i)cli-\\d+";
	private String idFornecedor = "(?i)for-\\d+";
	private String idProduto = "(?i)pro-\\d+";
	private String idItem = "(?i)ite-\\d+";
	private String idEstoque = "(?i)est-\\d+";
	private String data = "\\d{2}/\\d{2}/\\d{4}";
	private String hora = "\\d{2}:\\d{2}:\\d{2}";
	private Pattern p;
	private Matcher matcher;
    private static ExpressoesRegulares instance;
	
	// construtor privado
	private ExpressoesRegulares() {
		
	}
	/**
	 * Método referente ao padrão Singleton. Nele é verificado, primeiramente, se a instancia (única)
	 * da classe ja foi criada, se não, ela é instanciada. Por fim é retornada a instancia.
	 * @return instancia única da classe.
	 */
	public static synchronized ExpressoesRegulares getInstance() {
		if(instance == null) {
			instance = new ExpressoesRegulares();
		}
		return instance;
	}
	
	/**
	 * Verifica se o cpf informado(text) é compatível com a expressão regular(modelo) de cpf, ou seja, verifica se é um cpf válido, retornando
	 * o valor booleano do método "matcher", que será verdadeiro caso for um cpf válido e falso se não.
	 * @param text - Cpf a passar pela verificação.
	 * @return o resultado booleano do método "matcher"
	 */
	public boolean matchesCPF(String text) {
		p = Pattern.compile(cpf);
	          
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	
	/**
	 * Verifica se o email informado(text) é compatível com a expressão regular(modelo) de email, ou seja, verifica se é um email válido, retornando
	 * o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Email a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesEmail(String text) {
		p = Pattern.compile(email);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o telefone informado(text) é compatível com a expressão regular(modelo) de telefone, ou seja, verifica se é um telefone válido, retornando
	 * o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Telefone a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesTelefone(String text) {
		p = Pattern.compile(telefone);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o cnpj informado(text) é compatível com a expressão regular(modelo) de cnpj, ou seja, verifica se é um cnpj válido, retornando
	 * o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Cnpj a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesCNPJ(String text) {
		p = Pattern.compile(cnpj);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o id do objeto Cliente informado(text) é compatível com a expressão regular(modelo) de id de Cliente, ou seja, 
	 * verifica se é um id do tipo Cliente válido, retornando o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Id de Cliente a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesIdCliente(String text) {
		p = Pattern.compile(idCliente);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o id do objeto Forncedor informado(text) é compatível com a expressão regular(modelo) de id de Forncedor, ou seja, 
	 * verifica se é um id do tipo Forncedor válido, retornando o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Id de Forncedor a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesIdForncedor(String text) {
		p = Pattern.compile(idFornecedor);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o id do objeto Produto informado(text) é compatível com a expressão regular(modelo) de id de Produto, ou seja, 
	 * verifica se é um id do tipo Produto válido, retornando o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Id de Produto a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesIdProduto(String text) {
		p = Pattern.compile(idProduto);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o id do objeto Item informado(text) é compatível com a expressão regular(modelo) de id de Item, ou seja, 
	 * verifica se é um id do tipo Item válido, retornando o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Id de Item a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesIdItem(String text) {
		p = Pattern.compile(idItem);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se o id do objeto Estoque informado(text) é compatível com a expressão regular(modelo) de id de Estoque, ou seja, 
	 * verifica se é um id do tipo Estoque válido, retornando o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Id de Estoque a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesIdEstoque(String text) {
		p = Pattern.compile(idEstoque);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se a data informado(text) é compatível com a expressão regular(modelo) da data, ou seja, verifica se é uma data válida, retornando
	 * o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Data a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesData(String text) {
		p = Pattern.compile(data);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
	/**
	 * Verifica se a hora informado(text) é compatível com a expressão regular(modelo) da hora, ou seja, verifica se é uma hora válida, retornando
	 * o valor booleano do método "matcher", que será verdadeiro caso for válido e falso se não.
	 * @param text - Hora a passar pela verificação.
	 * @return o resultado booleano do método "matcher".
	 */
	public boolean matchesHora(String text) {
		p = Pattern.compile(hora);
        
	    matcher = p.matcher(text);
	    return matcher.matches();
	}
}
