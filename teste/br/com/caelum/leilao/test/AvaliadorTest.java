package br.com.caelum.leilao.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// parte 1: Cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// Parte 2: Acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Parte 3: validação
		double maiorEsperado = 400;
		double menorEsperado = 250;
		double mediaEsperado = (250.0 + 300.0 + 400.0) / 3;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMedia(), 0.0001);

	}

	@Test
	public void testaMediaDeZeroLance() {

		// cenario
		Usuario ewertom = new Usuario("Ewertom");

		// acao
		Leilao leilao = new Leilao("Iphone 7");
		leilao.propoe(new Lance(ewertom, 0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// validacao
		Assert.assertEquals(0, avaliador.getMedia(), 0.0001);

	}

}
