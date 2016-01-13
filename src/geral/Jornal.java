package geral;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Jornal extends Periodico{

	public Jornal(String titulo, String dataPublicacao, ArrayList<String> areas, Periodicidade periodicidade) {
		super(titulo, dataPublicacao, areas, periodicidade);
	}


}
