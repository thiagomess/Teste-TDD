package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import br.com.caelum.leilao.servico.builder.CriadorDeLeilao;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	//O @Before 
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// parte 1: Cenario

	
		Leilao leilao = new CriadorDeLeilao().para("PlayStation 3 Novo")
				.lance(joao, 250.0)
				.lance(maria,300.0)
				.lance(jose, 400.0)
				.constroi();

		// Parte 2: Acao
		leiloeiro.avalia(leilao);

		// Parte 3: validação
		double mediaEsperado = (250.0 + 300.0 + 400.0) / 3;

		Assert.assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(250.0, leiloeiro.getMenorLance(), 0.0001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMedia(), 0.0001);

	}

	@Test
	public void testaMediaDeZeroLance() {

		// acao

		Leilao leilao = new CriadorDeLeilao().para("Iphone 7")
				.lance(joao, 0.0)
				.constroi();
		leiloeiro.avalia(leilao);

		// validacao
		Assert.assertEquals(0, leiloeiro.getMedia(), 0.0001);

	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
		
		Leilao leilao = new CriadorDeLeilao().para("PlayStation 3 Novo")
				.lance(joao, 400.0)
				.lance(maria, 300.0)
				.lance(joao, 200.0)
				.lance(maria, 100.0)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 1400.0)
                .lance(maria, 1200.0)
                .lance(joao, 1350.0)
                .lance(maria, 1250.0)
                .constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(1400.0, maiores.get(0).getValor(), 0.0001);
		assertEquals(1350.0, maiores.get(1).getValor(), 0.0001);
		assertEquals(1250.0, maiores.get(2).getValor(), 0.0001);

	}

	@Test
	public void deveEntenderLeilaoSemLances() {

		Leilao leilao = new Leilao("ps3");
		leiloeiro.avalia(leilao);

		List<Lance> lista = leiloeiro.getTresMaiores();
		assertEquals(0, lista.size());
	}

}
