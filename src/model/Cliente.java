package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Thread {

	private String nome;
	private Socket cliente;
	private ObjectOutputStream escritor;
	private ObjectInputStream leitor;
	private List<String> mensagens;

	public Cliente(String nome, String ipServidor, Integer porta) {
		this.nome = nome;
		configurarPorta(ipServidor, porta);
	}

	public void configurarPorta(String ipServidor, Integer porta) {
		try {
			cliente = new Socket(ipServidor, porta);
			escritor = new ObjectOutputStream(cliente.getOutputStream());
			mensagens = new ArrayList<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNome() {
		return nome;
	}

	public void enviarMsg(String msg) {
		try {
			escritor.writeUTF(this.nome + ": " + msg);
			escritor.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void adicionarMsg(String msg) {
		this.mensagens.add(msg);
	}
	
	public List<String> getMensagens() {
		return mensagens;
	}
	
	private void encerrarConexao() {
		try {
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sair() {
		encerrarConexao();
	}

	@Override
	public void run() {
		try {
			String msg;
			leitor = new ObjectInputStream(cliente.getInputStream());
			while (true) {
				msg = leitor.readUTF();
				adicionarMsg(msg);
				System.out.println(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
