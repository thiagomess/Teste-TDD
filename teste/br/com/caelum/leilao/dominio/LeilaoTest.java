package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.servico.builder.CriadorDeLeilao;

public class LeilaoTest {

	private Usuario thiago;
	private Usuario joao;

	@Before
	public void setUp() {
		this.thiago = new Usuario("Thiago");
		this.joao = new Usuario("João");
	}

	@Test
	public void deveReceberUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("MackBook Pro 15")
				.constroi();
		
		assertEquals(0, leilao.getLances().size());

		leilao.propoe(new Lance(thiago, 2000.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}

	@Test
	public void deveReceberVariosLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Mackbook Pro 15")
				.lance(thiago, 2000.0)
				.lance(joao, 2500.0)
				.constroi();

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(2500.0, leilao.getLances().get(1).getValor(), 0.0001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {

		Leilao leilao = new CriadorDeLeilao().para("MackBook Pro 15")
				.lance(thiago, 2000.0)
				.lance(thiago, 2100.0)
				.constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para("MackBook Pro 15")
				.lance(thiago, 2000.0)
				.lance(joao, 3000.0)
				.lance(thiago, 4000.0)
				.lance(joao, 5000.0)
				.lance(thiago, 6000.0)
				.lance(joao, 7000.0)
				.lance(thiago, 8000.0)
				.lance(joao, 9000.0)
				.lance(thiago, 10000.0)
				.lance(joao, 11000.0)
				.lance(thiago, 12000.0) // Deve ser Ignorado
				.constroi();

		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(9).getValor(), 0.0001);
	}

	@Test
	public void deveDobrarOUltimoLanceDoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para("MackBook Pro 15")
				.lance(thiago, 2000.0)
				.lance(joao, 3000.0)
				.constroi();

		leilao.dobraLance(thiago);
		leilao.dobraLance(joao);

		assertEquals(4, leilao.getLances().size());
		assertEquals(4000.0, leilao.getLances().get(2).getValor(), 0.0001);
		assertEquals(6000.0, leilao.getLances().get(3).getValor(), 0.0001);
	}

	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		
		Leilao leilao = new CriadorDeLeilao().para("MackBook Pro 15")
				.constroi();
		
		leilao.dobraLance(thiago);

		assertEquals(0, leilao.getLances().size());

	}
	
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}

}
