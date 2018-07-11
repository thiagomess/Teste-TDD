package br.com.caelum.ano;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnoBissextoTest {
	
	@Test
	public void deveRetornarAnoBissexto() {
		
		AnoBissexto ano = new AnoBissexto();
		
		assertEquals(true, ano.isAnoBissexto(400));
		assertEquals(true, ano.isAnoBissexto(2012));
	}
	
	@Test
	public void naoDeveRetornarAnoBissexto() {
		
		AnoBissexto ano = new AnoBissexto();
		
		assertEquals(false, ano.isAnoBissexto(2014));
		assertEquals(false, ano.isAnoBissexto(2011));
	}
	

}
