package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.matematica.MatematicaMaluca;

public class MatematicaMalucaTest {
	
	@Test
	public void multiplicaNumeroMaiorQue30() {
		
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int maiorQue30 = 31*4;
		
		assertEquals(maiorQue30, matematica.contaMaluca(31));
		
	}

	@Test 
	public void multiplicaNumeroMaiorQue10() {
		
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int maiorQue10 = 11 * 3;
		
		assertEquals(maiorQue10, matematica.contaMaluca(11));
	}
	@Test
	public void multiplicaNumeroMenorQUe10() {
		
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int menorQue10 = 9*2;
		
		assertEquals(menorQue10, matematica.contaMaluca(9));
		
	}
}
