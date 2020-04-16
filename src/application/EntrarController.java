package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class EntrarController implements Initializable{

	@FXML
	TextField ctNick;
	
	@FXML
	Button btEntrar, btCancelar;
	
	public ClienteController clienteController;
	
	private String nick;
	
	private void abrirChat() throws Exception {
		clienteController = new ClienteController();
		ClienteController.nick = this.nick;
		clienteController.start(new Stage());
	}
	
	@FXML
	private void cancelar() {
		System.exit(0);
	}
	
	@FXML
	private void entrar() {
		if (ctNick.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setContentText("Você precisa inserir um nick para entrar no chat!");
			alert.showAndWait();
		} else {
			nick = ctNick.getText();
			try {
				abrirChat();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
