package geral;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import geral.Periodico.Periodicidade;
import geral.Tese.TipoTese;

public class data_teste {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//popula();
		
		Path.setPath((new java.io.File( "." ).getCanonicalPath())+File.separatorChar+"biblioteca");

		Biblioteca bib = (Biblioteca) Path.read();
		
		bib.exportEst((new java.io.File( "." ).getCanonicalPath())+File.separatorChar+"export.csv");
		
		String s="asasa";
		
			
	}
	
	static void popula() throws IOException{

		Path.setPath((new java.io.File( "." ).getCanonicalPath())+File.separatorChar+"biblioteca");

		Biblioteca bib = (Biblioteca) Path.read();

		ArrayList<String> areas = new ArrayList<>();
		areas.add("j");
		areas.add("k");
		Tese teseA = new Tese("TeseA", "10/10/1987",areas,1223,123,"s","y", TipoTese.Doutoramento);
		Tese teseB = new Tese("TeseB", "10/10/1987",areas,1223,123,"s","y", TipoTese.Doutoramento);
		bib.addPublicacao(teseA);
		bib.addPublicacao(teseB);
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"07/10/2014","25/10/2014"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/11/2014","25/11/2014"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/12/2014","10/12/2014"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"20/12/2014","30/12/2014"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/01/2015","10/01/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"11/01/2015","15/01/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/01/2015","20/01/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"21/01/2015","25/01/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/02/2015","10/02/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"20/02/2015","10/03/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/03/2015","15/03/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/03/2015","20/03/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/04/2015","20/05/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"25/05/2015","10/06/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"15/06/2015","20/06/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"21/06/2015","23/06/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/07/2015","17/07/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/08/2015","10/08/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"11/08/2015","15/08/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/08/2015","20/08/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"21/08/2015","25/08/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/09/2015","10/09/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"20/09/2015","10/10/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/10/2015","15/11/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/10/2015","20/11/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/11/2015","20/12/2015"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"25/12/2015","02/01/2016"));
		teseA.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/01/2016","07/01/2016"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/11/2014","25/11/2014"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/12/2014","10/12/2014"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"20/12/2014","30/12/2014"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/01/2015","10/01/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"11/01/2015","15/01/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/01/2015","20/01/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"21/01/2015","25/01/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/02/2015","10/02/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"20/02/2015","10/03/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/03/2015","15/03/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/03/2015","20/03/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/04/2015","20/05/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"25/05/2015","10/06/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"15/06/2015","20/06/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"21/06/2015","23/06/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/07/2015","17/07/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/08/2015","10/08/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"11/08/2015","15/08/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/08/2015","20/08/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"21/08/2015","25/08/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/09/2015","10/09/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"20/09/2015","10/10/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/10/2015","15/11/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"17/10/2015","20/11/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"12/11/2015","20/12/2015"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"25/12/2015","02/01/2016"));
		teseB.requisita(new Emprestimo(bib.getUtilizadores().get(0),"05/01/2016","07/01/2016"));
		Path.write(bib);
	}

}
