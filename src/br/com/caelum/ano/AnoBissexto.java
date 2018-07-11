package br.com.caelum.ano;


public class AnoBissexto {
	
	boolean isAnoBissexto(int ano) {
		
		if ((ano % 4) == 0 && (ano % 100) !=0 ) {
			return true;
		}else if ((ano % 400) == 0) {
			return true;
		}
		
		return false;
		
	}
	
}
