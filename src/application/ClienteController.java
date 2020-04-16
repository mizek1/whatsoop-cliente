package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;

public class ClienteController implements Initializable {
	
	@FXML
	TextField ctMensagem;
	
	@FXML
	Button btEnviar, btAtualizar, btSair;
	
	@FXML
	ListView<String> lvDisplay;
	
	private Cliente cliente;
	public static String nick;

	private void entrar() {
		cliente = new Cliente(nick, "localhost", 6000);
		cliente.start();
		cliente.enviarMsg(nick + " entrou no chat!");
	}
	
	@FXML
	private void sair() {
		cliente.sair();
		System.exit(0);
	}
	
	@FXML
	private void enviarMensagem() {
		if (ctMensagem.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setContentText("Você precisa inserir uma mensagem antes de enviá-la!");
			alert.showAndWait();
		} else {
			cliente.enviarMsg(ctMensagem.getText());
		}
	}
	
	@FXML
	private void atualizarMensagens() {
		lvDisplay.getItems().clear();
		lvDisplay.getItems().addAll(cliente.getMensagens());
	}
	
	public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		entrar();
	}
	
}
