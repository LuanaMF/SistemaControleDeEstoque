package model;

import controller.Main;

/**
 * Classe Gerenciadora de Usuário, contendo o método de adicionar usuário e entre outros.
 * @see Usuario
 * @author Luana de Melo Fraga
 *
 */
public class GerenciadorUsuario {

	/**
	 * Cadastra um objeto do tipo Fornecedor e o adiciona ee o adiciona em sua lista (listaFOR). Seu id e permissão são atribuídos usando o método da classe
	 * 'MainView' (id), e verificaPermissao(método local), respectivamente.
	 * @param id - Id do funcionário, que agora sera o login do novo usuário.
	 * @param senha - Senha do novo usuário.
	 */
	public static void cadastrarUsuario(String id, String senha) {
		boolean permissao = GerenciadorUsuario.verificaPermissao(id);
		Usuario usuario = new Usuario(id, senha, permissao);
		BancoDados.getInstance().getUsers().add(usuario); // adiciona na lista de usuários
	}
	
	/** 
	 * Verifica se a senha digititada pelo usuário corresponde ao seu ID.
	 * @param id - Id do usuário
	 * @param senha - Senha fornecida pelo usuário
	 * @return um  <code>boolean</code>, onde true significa que a senha está correta, e false que a senha está incorreta ou que o id não existe..
	 */
	public static boolean verificaLogin(String id, String senha){
		int index = Main.buscaPorId(BancoDados.getInstance().getUsers(), id);
		boolean bol = false;
		
		if(index != -1) {
			
			if(BancoDados.getInstance().getUsers().get(index).getSenha().equals(senha)) {
				
				bol = true;
			}else {
				
				bol = false;
			}
		}
		return bol;		
	}
	/**
	 * Verifica se o usuário tem permissão para acessar as opções avançadas do sistema.
	 * @see Main
	 * @param id - Id do usuário
	 * @return - um  <code>boolean</code>, onde true signifca que tem permissão, e false significa o contrário.
	 */
	public static boolean verificaPermissao(String id) {
		boolean bol = false;
		String categoria = Main.separaId(id, "categoria");
		if(categoria.equals("GER")) {
			bol = true;
		}
		else {
			bol = false;
		}
		return bol;
	}
}
