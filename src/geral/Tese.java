package geral;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.joda.time.DateTime;

public class Tese extends NaoPeriodico{
	
	public enum TipoTese{Mestrado, Doutoramento}
	
	private TipoTese tipo;
	private String orientador;
	private String autor;
	
	public Tese(String titulo, String dataPublicacao, ArrayList<String> areas, int codBarras, int diasRequisicaoMax,
					String autor, String orientador, TipoTese tipo) {
		
		super(titulo, dataPublicacao, areas, codBarras, diasRequisicaoMax);
		this.autor = autor;
		this.orientador = orientador;
		this.tipo = tipo;
		this.disponivel = true;	
	}

	@Override
	public String toString() {
		return "Titulo: "+ this.titulo +", Autor: " + this.autor + " Data Publicacao";
	}

	
	
	public String getAutor(){
		return autor;
	}
	
	public String estado(){
		if(this.disponivel) return "disponivel";
		else return "indisponivel";
	}

	@Override
	public Utilizador getUtilizador() {
		if (this.isDisponivel()) {
			return null;
		}
		else{
			return (emprestimos.get((emprestimos.size()-1))).getUtilizador();
		}
	}
	
	public TipoTese getEnum(){
		return tipo;
	}

	
}
