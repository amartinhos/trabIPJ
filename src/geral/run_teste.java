package geral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

public class run_teste {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Biblioteca bib = (Biblioteca) Path.read();
		
		System.out.println(bib.exportEst("c:/AR/Trab1/"));
		
		Path.write(bib);

	}

}
