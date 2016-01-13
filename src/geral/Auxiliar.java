package geral;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Days;

import DateTime.SetDateTime;

public class Auxiliar {
	public static String estatistica(Publicacao pub, ArrayList<Emprestimo> emprestimos ){
		
		
		String estat_obra = pub.titulo+","+pub.dataPublicacao.toString("dd/MM/yyyy");
		// testando git
		if (emprestimos.size()!=0){
			int meses[]=new int[12];
			int max=0;
			int min=0;
			int total=0;
			int k=0;
	

			for (;k<emprestimos.size() && 
					emprestimos.get(k).getDataReq().isBefore(SetDateTime.setDate("01/"+(new DateTime().minusMonths(12).getMonthOfYear())+"/"+(new DateTime().minusMonths(12).getYear())));k++){
			}
			
			if (emprestimos.size()>k && emprestimos.get(k).getDataReq().isBefore(SetDateTime.setDate("01/"+(new DateTime().getMonthOfYear())+"/"+(new DateTime().getYear())))) {
				meses[emprestimos.get(k).getDataReq().getMonthOfYear()-1]++;
				max = Days.daysBetween(emprestimos.get(k).getDataReq(), emprestimos.get(k).getDataEnt()).getDays();
				min = max;
				total=max;
			}
			for(int i=k+1;i<emprestimos.size() && emprestimos.get(i).getDataReq().isBefore(SetDateTime.setDate("01/"+(new DateTime().getMonthOfYear())+"/"+(new DateTime().getYear())));i++){
				Emprestimo empTemp = emprestimos.get(i);
				meses[empTemp.getDataReq().getMonthOfYear()-1]++;
				int duracao = Days.daysBetween(empTemp.getDataReq(), empTemp.getDataEnt()).getDays();
				if (duracao>max) {
					max=duracao;
				} else if (duracao < min) {
					min = duracao;
				}
				total = total + duracao;
			}
			
			for (int i:meses){
				estat_obra=estat_obra+","+i;
			}
			estat_obra=estat_obra+","+(emprestimos.size()-k)+","+min+","+max+","+total/(emprestimos.size()-k)+","+pub.getInfo();
		}else {
			estat_obra = estat_obra+",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"+pub.getInfo();
		}
		return estat_obra;
		
	}


}
