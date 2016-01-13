package geral;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import geral.Periodico.Periodicidade;
import geral.Tese.TipoTese;
import geral.Utilizador.User;
import interfaceGrafica.Login;

public class Main {
	
	public static void main(String[]args) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException{
		ObjectOutputStream write;
		
		Biblioteca bib = new Biblioteca("uc"); 
		Utilizador chefe = new Utilizador("Miguel Serra", "pearljam", "mcserra",User.colaborador); 
		Utilizador chefe1 = new Utilizador("Joao TR", "password", "user",User.leitor); 
		Utilizador chefe2 = new Utilizador("Joao Pedro", "password", "chefe",User.Bibliotecario_chefe); 
		bib.addUtilizador(chefe1);
		bib.addUtilizador(chefe2);
		bib.addUtilizador(chefe);
		ArrayList<String> areas = new ArrayList<>();
		areas.add("j");
		areas.add("k");
		ArrayList<String> areas1 = new ArrayList<>();
		areas1.add("k");
		Tese tese1 = new Tese("titulo", "10/10/1987",areas,1223,123,"s","y", TipoTese.Doutoramento);
		Tese tese2 = new Tese("titulo2", "10/10/1987",areas1,1223,123,"s","y", TipoTese.Doutoramento);
		Jornal jornal = new Jornal("Jornal1", "10/10/1988", areas1, Periodicidade.anual);
		bib.addPublicacao(tese1);
		bib.addPublicacao(tese2);
		bib.addPublicacao(jornal);
		
		Path.setPath("c:/AR/trab1/biblioteca");	
		Path.write(bib);
		data_teste.popula();
	}

}
