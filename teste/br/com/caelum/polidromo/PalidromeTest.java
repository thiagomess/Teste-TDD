package br.com.caelum.polidromo;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.polidromo.Palindromo;

public class PalidromeTest {

	@Test
	public void deveIdentificarPalindromoEFiltrarCaracteresInvalidos() {

		Palindromo palindromo = new Palindromo();

		boolean resultado = palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos");

		Assert.assertTrue(resultado);
	}

	@Test
	public void deveIdentificarPalindromo() {
		Palindromo p = new Palindromo();

		boolean resultado = p.ehPalindromo("Anotaram a data da maratona");
		Assert.assertTrue(resultado);
	}

	@Test
	public void deveIdentificarSeNaoEhPalindromo() {
		Palindromo p = new Palindromo();

		boolean resultado = p.ehPalindromo("E preciso amar as pessoas como se nao houvesse amanha");
		Assert.assertFalse(resultado);
	}

}
