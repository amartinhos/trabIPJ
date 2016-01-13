package interfaceGrafica;

import java.util.ArrayList;

import geral.Biblioteca;
import geral.Publicacao;
import geral.Requisitavel;
import geral.Utilizador;

public class GetLists {

	public static ArrayList<Publicacao> getAllPubs(Biblioteca b) {
		return b.getPublicacao();
	}
	
	public static ArrayList<Utilizador> getAllUsers(Biblioteca b) {
		return b.getUtilizadores();
	}
	
	public static ArrayList<Utilizador> getUserByTiTulo(Biblioteca biblioteca, String titulo) {
	
		String[] title = titulo.toLowerCase().split(" ");		
		ArrayList<Utilizador> publs = new ArrayList<>();	
		
		for (Utilizador utilizador : biblioteca.getUtilizadores()) {	
			String[] pubSplit = utilizador.getNome().toLowerCase().split(" ");
			for (String string : pubSplit) {					
				for (String string1 : title) {
					if (string.contains(string1) && !string1.equals("")) {
						publs.add(utilizador);
					}
				}
			}		
		}
		return publs;
	}
	

	public static ArrayList<Publicacao> getLoanedPubs(Biblioteca b) {
		ArrayList<Publicacao> pubs = new ArrayList<>();
		for (Publicacao publicacao : b.getPublicacao()) {
			if (publicacao instanceof Requisitavel && !((Requisitavel) publicacao).isDisponivel()) {
				pubs.add(publicacao);
			}
		}
		return pubs;
	}

	public static ArrayList<Publicacao> getPubsByTiTulo(Biblioteca b, String titulo, ArrayList<Publicacao> pubs) {
		String[] title = titulo.toLowerCase().split(" ");
		ArrayList<Publicacao> publs = new ArrayList<>();
		for (Publicacao publicacao : pubs) {
			String[] pubSplit = publicacao.getTitulo().toLowerCase().split(" ");
			for (String string : pubSplit) {
				for (String string1 : title) {
					if (string.contains(string1) && !string1.equals("")) {
						publs.add(publicacao);
					}
				}
			}
		}
		return publs;
	}

	public static ArrayList<Publicacao> getByArea(Biblioteca biblioteca, String area) {
		ArrayList<Publicacao> publs = new ArrayList<>();
		for (Publicacao publicacao : biblioteca.getPublicacao()) {
			for (String string : publicacao.getAreas()) {
				if (area.compareToIgnoreCase(string) == 0) {
					publs.add(publicacao);
				}
			}
		}
		return publs;
	}

	public static ArrayList<Publicacao> getMine(Biblioteca biblioteca, Utilizador utilizador) {
		ArrayList<Publicacao> publs = new ArrayList<>();
		for (Publicacao publicacao : biblioteca.getPublicacao()) {
			if (publicacao instanceof Requisitavel && utilizador.equals(((Requisitavel) publicacao).getUtilizador())) {
				publs.add(publicacao);
			}
		}
		return publs;
	}

}
