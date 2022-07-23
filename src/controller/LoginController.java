package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.BancoDados;
import model.ExpressoesRegulares;
import model.GerenciadorUsuario;
import model.Temp;
import model.Usuario;

import java.io.IOException;

/**
 * Classe controller da tela "Login" responsável por gerenciar todas ações do usuário que acontecem(ou devem acontecer) nela.
 * @author Luana de Melo Fraga
 *
 */
public class LoginController {
	
	
	public LoginController() {
		
	}
	
	// elementos da tela
	@FXML
	private Button botaoLogin;
	
	@FXML
	private TextField idUsuario;
	
	@FXML
	private PasswordField senhaUsuario;
	
	@FXML
	private Label mensagem;
	@FXML
	private ImageView warningIcon, imgSeta, loginIcon, imageBotao;
	
	/**
	 * Método padrão initialize, onde, assim que o arquivo "fxml" que essa classe controla for carregado(iniciado), ele será executado.
	 * Nele é carregado as imagens em campos de ImageView.
	 */
	@FXML
    public void initialize() {
		
		Image imagem = new Image(getClass().getResourceAsStream("/view/assets/SetaLogin.png"));
		Image imagemWarning = new Image(getClass().getResourceAsStream("/view/assets/warning.png"));
		Image loginIcone = new Image(getClass().getResourceAsStream("/view/assets/loginIcon.png"));
		Image botaoIcone = new Image(getClass().getResourceAsStream("/view/assets/botaoLogin.png"));
		imgSeta.setImage(imagem);
		warningIcon.setImage(imagemWarning);
		loginIcon.setImage(loginIcone);
		imageBotao.setImage(botaoIcone);
	}
	/**
	 * Inicializado ao clicar no botão de login, que chama o método local privado responsável por fazer login.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void logar() throws InterruptedException, IOException {
		checaLogin();
		
	}
	
	/**
	 * Método que checa se as informações de login (id e senha), numa chamada de método da classe gerenciadora de usuários, e
	 * a depender de seu retorno, direcionar o usuário a tela principal do sistema ou exibir uma mensagem de erro.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void checaLogin() throws InterruptedException, IOException {
		
		Main m = new Main();
		boolean tudoCerto = verificaInformacoesLogin();
		if(tudoCerto) {
			boolean verifica = GerenciadorUsuario.verificaLogin(idUsuario.getText(), senhaUsuario.getText());
			
	        if(verifica == true) {
	        	
	        	int index = Main.buscaPorId(BancoDados.getInstance().getUsers(), idUsuario.getText());
	        	if(index != -1) {
	        		Usuario user = BancoDados.getInstance().getUsers().get(index);
	        		Temp.getInstance().setUsuarioLogado(user);
	        	}
	        	
	        	m.mudaTela("/view/xml/NovaTelaPrincipal.fxml");
	        }
			else {
				warningIcon.setVisible(true);
				mensagem.setTextFill(Color.RED);
				mensagem.setText("ID ou senha incorreta!");
			}
		}
		
	}
	/**
     * Responsável por verificar informações fornecidas pelo usuário em relação a produto. 
     * Verifica se há algum campo vazio.
     * @see ExpressoesRegulares
     * @see PopUpController
     * @return um boolean que será true se todas informações estiverem preenchidas, e false se ocorrer o contrário.
     */
	private boolean verificaInformacoesLogin() {
		boolean tudoCerto = false;
		boolean tudoPreenchido = !idUsuario.getText().isBlank() && !idUsuario.getText().isEmpty() &&
				                 !senhaUsuario.getText().isBlank() && !senhaUsuario.getText().isEmpty();
		
		if(tudoPreenchido) {
			tudoCerto = true;
		}
		else if(!tudoPreenchido) {
			warningIcon.setVisible(true);
			mensagem.setTextFill(Color.RED);
			mensagem.setText("Digite o id e senha para entrar!");
		}
		return tudoCerto;
	}
}
