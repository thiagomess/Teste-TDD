package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.FiltroDeLances;

public class FiltroDeLancesTest {
	
	@Test
	public void deveSelecionarLancesEntre1000E3000() {
		
		Usuario joao =  new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
		
		List<Lance> resultado = filtro.filtra(Arrays.asList( 
				new Lance(joao, 200.0),
				new Lance(joao, 1000.0),
				new Lance(joao, 3000.0),
				new Lance(joao, 2000.0)));
		
		assertEquals(1, resultado.size());
		assertEquals(2000.0, resultado.get(0).getValor(), 0.0001);
	}
	
	@Test
	public void deveSelecionarLancesEntre500E700() {
		
		Usuario joao = new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
			
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 500.0),
				new Lance(joao, 650.0),
				new Lance(joao, 700.0),
				new Lance(joao, 800.0)));
		
		assertEquals(1, resultado.size());
		assertEquals(650.0, resultado.get(0).getValor(), 0.0001);
		
	}
	
	@Test
	public void deveSelecionarLanceMaiorQue5000() {
		
		Usuario joao = new Usuario("Joao");
		FiltroDeLances filtro = new FiltroDeLances();
		
		 List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 5000.0),
				new Lance(joao, 6000.0),
				new Lance(joao, 200.0)));
		 
		 assertEquals(1, resultado.size());
		 assertEquals(6000.0, resultado.get(0).getValor(), 0.0001);
	}
	
	@Test
	public void deveDescartarLancesMenoresQue500() {
		
		Usuario joao = new Usuario("Joao");
		FiltroDeLances filtro = new FiltroDeLances();
		
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 400.0),
				new Lance(joao, 300.0),
				new Lance(joao, 0)));
		
		assertEquals(0, resultado.size());
		
		
	}
	
	@Test
	public void deveDescartarLancesEntre800a1000() {
		
		Usuario joao = new Usuario("joao");
		FiltroDeLances filtro = new FiltroDeLances();
		
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 800.0),
				new Lance(joao, 900.0),
				new Lance(joao, 1000.0)));
		
		assertEquals(0, resultado.size());
		
	}
	
	@Test
	public void deveDescartarLancesEntre3000E5000() {
		
		Usuario joao = new Usuario("joao");
		FiltroDeLances filtro = new FiltroDeLances();
		
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 3500.0),
				new Lance(joao, 4000.0),
				new Lance(joao, 5000.0)));
		
		assertEquals(0, resultado.size());
	}

}
