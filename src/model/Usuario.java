package model;

/**
 * Classe da entidade Usuario (funcionários do estabelecimento que usarão o sistem), contendo três 
 * principais atributos: id, senha e permissão, onde juntos "decidem" as ações do usuário no sistema.
 * @author Luana de Melo Fraga
 *
 */
public class Usuario {
	
	private String id;
	private String senha;
	private String senhaTable;
	private boolean permissao;
	
	//construtor
	public Usuario(String id, String senha, boolean permissao) {
		this.id = id;
		this.senha = senha;
		this.permissao = permissao;
	}
	
	//getters e setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getPermissao() {
		return permissao;
	}

	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}
	
	public void atualizaSenha(Usuario user, String senha) {
		user.setSenha(senha);
	}
	/**
	 * Método get usado exclusivamente para visualização do atributo "senha" do objeto Usuário, onde, 
	 * a depender se o usuário logado é gerente ou não, a senha poderá(gerentes) ou não(outros) ser visualizada.
	 * @return a senha correspondente ao resultado da verificação "if"
	 */
	public String getSenhaTable() {
		
		String senhaTable = "*****";
		if(Temp.getInstance().getUsuarioLogado() != null) {
			boolean bol = GerenciadorUsuario.verificaPermissao(Temp.getInstance().getUsuarioLogado().getId());
			if(bol == true) {
				senhaTable = senha;
			}
		}
		return senhaTable;
	}

	public void setSenhaTable(String senhaTable) {
		this.senhaTable = senhaTable;
	}

	
}
