package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.builder.CriadorDeLeilao;

public class LanceTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorZero() {
		
		Leilao leilao = new CriadorDeLeilao().para("Play 4")
				.lance(joao, 0.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarValorNegativo() {
		
		Leilao leilao = new CriadorDeLeilao().para("Iphone")
				.lance(joao, -100.0)
				.constroi();
		leiloeiro.avalia(leilao);
	}
	
	@Test
	public void deveAceitarLancesComValorPositivo() {
		
		Leilao leilao = new CriadorDeLeilao().para("Xbox 360")
				.lance(joao, 200.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(200.0, leilao.getLances().get(0).getValor(), 0.0001);
	}

}
