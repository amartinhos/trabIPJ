package geral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;

public class Biblioteca implements Serializable {
	
	//private Info info;
	private String nome;
	private int LastBarCode;
	private ArrayList<Utilizador> users = new ArrayList<>();
	private ArrayList<Publicacao> publicacoes = new ArrayList<>();

	public Biblioteca(String nome) {
		this.nome = nome;
		LastBarCode = 1000;
	}

	public void addUtilizador(Utilizador u) {
		users.add(u);
	}

	public Utilizador getUtilizador(String username, String password) {
		for (Utilizador user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	
	public ArrayList<Utilizador> getUtilizadores(){
		return users;
	}
	
	public void addPublicacao(Publicacao publicacao){
		publicacoes.add(publicacao);
	}
	
	public ArrayList<Publicacao> getPublicacao(){
		return publicacoes;
	}

	public String exportEst(String path){

		try {
			DateTime date = new DateTime();
			
			FileWriter fwt = null;
			fwt = new FileWriter(new File(path));
	
			BufferedWriter fW = new BufferedWriter(fwt);
			
			fW.write("TITULO,DATA PUBLICACAO");
					
			for(int i = 12; i>0;i--){
				fW.write(","+date.minusMonths(i).toString("MM/yyyy"));
			}
			fW.write(",TOTAL REQUISICOES,MIN DIAS, MAX DIAS,DIAS MEDIOS,INFO");
			fW.newLine();
			
			
			for(Publicacao pubTemp:this.publicacoes){
				if (pubTemp instanceof Requisitavel) {
					String linha = ((Requisitavel) pubTemp).estatistica();
					fW.write(linha);
					fW.newLine();
				}	
			}			
			fW.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "Erro na criaco do ficheiro, verifique o caminho e se o ficheiro não esta a ser utilizado por outra aplicacao!";
		}
		
		return "Ficheiro criado com sucesso!";
		
		
	}
	
}


