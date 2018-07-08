package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		Usuario joao = new Usuario("Jo�o");
		Usuario jose = new Usuario("Jos�");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// Parte 2: Acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Parte 3: valida��o
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
	
	@Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("Jo�o");
		Usuario maria = new Usuario("Maria");
		Usuario carlos = new Usuario("Carlos");
		Usuario luiza = new Usuario("Luiza");
		
		Leilao leilao = new Leilao("Play 3");
		leilao.propoe(new Lance(joao, 1400.0));
		leilao.propoe(new Lance(maria, 1200.0));
		leilao.propoe(new Lance(carlos, 1350.0));
		leilao.propoe(new Lance(luiza, 1250.0));	
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(1400.0, maiores.get(0).getValor(), 0.0001);
		assertEquals(1350.0, maiores.get(1).getValor(), 0.0001);
		assertEquals(1250.0, maiores.get(2).getValor(), 0.0001);
		
	}
	
	@Test
	public void deveEntenderLeilaoSemLances() {
		
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("ps3");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> lista = leiloeiro.getTresMaiores();
		
		assertEquals(0, lista.size());
	}

}
