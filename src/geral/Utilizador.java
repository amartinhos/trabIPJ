package geral;

import java.io.Serializable;


public class Utilizador implements Serializable{

	public enum User {Bibliotecario_chefe, colaborador, leitor}
	private User tipo;
	private String nome;
	private String password;
	private String username;

	public Utilizador(String nome, String password, String username, User tipo) {
		this.nome = nome;
		this.password = password;
		this.username = username;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public User getTipo(){
		return tipo;
	}
	
}
