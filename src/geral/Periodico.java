package geral;

import java.util.ArrayList;

public abstract class Periodico extends Publicacao{
	
	public enum Periodicidade{
		diaria,semanal, quinzenal, mensal, trimestral, semestral, anual
	}
	
	protected Periodicidade periodicidade;

	public Periodico(String titulo, String dataPublicacao, ArrayList<String> areas, Periodicidade periodicidade2) {
		super(titulo, dataPublicacao, areas);
		this.periodicidade = periodicidade2;
	}
}
