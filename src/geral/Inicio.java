package geral;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

import geral.Periodico.Periodicidade;
import geral.Tese.TipoTese;
import geral.Utilizador.User;
import interfaceGrafica.Login;

public class Inicio {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("INICIO");

		Path.setPath((new java.io.File( "." ).getCanonicalPath())+File.separatorChar+"biblioteca");
		Biblioteca b = (Biblioteca) Path.read();
		
		if (b==null) {			
			b = new Biblioteca("BIBLIOTECA"); 
			Utilizador chefe = new Utilizador("Administrador", "password", "chefe",User.Bibliotecario_chefe); 
			b.addUtilizador(chefe);
		}
		
		(new Login(b)).setVisible(true);				
		
	}

}
