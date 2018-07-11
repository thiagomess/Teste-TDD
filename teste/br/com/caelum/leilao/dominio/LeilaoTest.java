package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Mackbook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Thiago Gomes"), 2000.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}
	
	@Test 
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Mackbook Pro 15");
		leilao.propoe(new Lance(new Usuario("Thiago"), 2000.0));
		leilao.propoe(new Lance(new Usuario("Alex"), 2500.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(2500.0, leilao.getLances().get(1).getValor(), 0.0001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Mackbook Pro 15");
		Usuario thiago = new Usuario("Thiago");
		
		leilao.propoe(new Lance(thiago, 2000.0));
		leilao.propoe(new Lance(thiago, 2100.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Mackbook Pro 15");
		Usuario thiago = new Usuario("Thiago");
		Usuario joao = new Usuario("João");
		
		leilao.propoe(new Lance(thiago, 2000.0));
		leilao.propoe(new Lance(joao, 3000.0));

		leilao.propoe(new Lance(thiago, 4000.0));
		leilao.propoe(new Lance(joao, 5000.0));
		
		leilao.propoe(new Lance(thiago, 6000.0));
		leilao.propoe(new Lance(joao, 7000.0));
		
		leilao.propoe(new Lance(thiago, 8000.0));
		leilao.propoe(new Lance(joao, 9000.0));
		
		leilao.propoe(new Lance(thiago, 10000.0));
		leilao.propoe(new Lance(joao, 11000.0));
		
		//Deve ser Ignorado
		leilao.propoe(new Lance(thiago, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(9).getValor(), 0.0001);
	}
	
	@Test
	public void deveDobrarOUltimoLanceDoUsuario() {
		Leilao leilao = new Leilao("Mackbook Pro 15");
		Usuario thiago = new Usuario("Thiago");
		Usuario joao = new Usuario("João");
		
		leilao.propoe(new Lance(thiago, 2000.0));
		leilao.propoe(new Lance(joao, 3000.0));
		leilao.dobraLance(thiago);
		leilao.dobraLance(joao);
		
		assertEquals(4, leilao.getLances().size());
		assertEquals(4000.0, leilao.getLances().get(2).getValor(), 0.0001);
		assertEquals(6000.0, leilao.getLances().get(3).getValor(), 0.0001);
	}
	
	@Test 
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Mackbook Pro 15");
		Usuario thiago = new Usuario("Thiago");
		
		leilao.dobraLance(thiago);
		
		assertEquals(0, leilao.getLances().size());
		
	}

}
