package geral;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import DateTime.SetDateTime;

public abstract class Publicacao implements Serializable{
	
	
	protected String titulo;
	protected DateTime dataPublicacao;
	// AS AREAS PODEM SER UM ARRAY DE STRING >> MUDAR
	protected ArrayList<String> areas = new ArrayList<>();
	protected DateTime dataRecebida; 
	
	@Override
	public String toString() {
		return "Publicacao titulo=" + titulo + ", dataPublicacao=" + dataPublicacao + ", areas=" + areas
				+ ", dataRecebida=" + dataRecebida;
	}

	public Publicacao(String titulo, String dataPublicacao, ArrayList<String> areas) {
		super();
		this.titulo = titulo;
		this.dataPublicacao = SetDateTime.setDate(dataPublicacao);
		this.areas = areas;
	}
	public String getTitulo() {
		return titulo;
	}
	
	public ArrayList<String> getAreas(){
		return areas;
	}

	public DateTime getDataPublicacao() {
		return  dataPublicacao;
	}
	
	public String getInfo(){
		return "descricao textual da obra!";
	}


	

}
