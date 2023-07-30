package model;

import org.junit.Assert;
import org.junit.Test;

public class TesteUsuario {

	// Verifica se uma exceção é disparada quando um email é inválido
	@Test(expected = Exception.class)
	public void teste_Email_Invalido() throws Exception {

		Usuario user1 = new Usuario("sheilagmail.com", "12345678910", "XXXX.");

	}

	// Verifica se uma exceção é disparada quando um CPF é inválido
	@Test(expected = Exception.class)
	public void teste_CPF_Invalido() throws Exception {

		Usuario user1 = new Usuario("sheila@gmail.com", "123456789100", "XXXX$");
	}

	// Verifica se uma exceção é disparada quando um password é inválido
	@Test(expected = Exception.class)
	public void teste_Password_Invalido() throws Exception {

		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "12345");
	}

	// Verifica se após um usuário ser cadastrado sua carteira recebe 200 moedas
	@Test
	public void teste_Usuario_Cadastro_Moedas() throws Exception {

		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Assert.assertEquals(200, user1.getMoedas(), 0);

	}

	//Verifica se a aposta está bloqueada após a criação da partida.
	@Test
	public void teste_Partida_Bloqueada() throws Exception {
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Assert.assertTrue(!partida.estaDisponivelReceberApostas());
	}

	//Verifica se a aposta está liberada.
	@Test
	public void teste_Partida_Liberada() throws Exception {
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		partida.liberarApostas();
		Assert.assertTrue(partida.estaDisponivelReceberApostas());
	}

	//Verifica se é disparada uma exceção caso o nome do campeonato não seja definido.
	@Test(expected = Exception.class)
	public void teste_Partida_Campeonato_Invalido() throws Exception {
		Partida partida = new Partida(null, "Real", "Barcelona");

	}

	//Verifica se é disparada uma exceção caso o nome do time visitante não seja definido.
	@Test(expected = Exception.class)
	public void teste_Partida_Time_Visitante_Invalido() throws Exception {
		Partida partida = new Partida("Champions", null, "Barcelona");

	}

	//Verifica se é disparada uma exceção caso o nome do time mandante não seja definido.
	@Test(expected = Exception.class)
	public void teste_Partida_Time_Mandante_Invalido() throws Exception {
		Partida partida = new Partida("Champions", "Real", "");

	}

	//Verifica se é disparada uma exceção caso a quantidade de gols do time mandante seja menor que 0.
	@Test(expected = Exception.class)
	public void teste_Aposta_Gols_Mandante_Invalido() throws Exception {
		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Aposta aposta = new Aposta(user1, partida, -1, 1);

	}

	//Verifica se é disparada uma exceção caso a quantidade de gols do time visitante seja menor que 0.
	@Test(expected = Exception.class)
	public void teste_Aposta_Gols_Visitante_Invalido() throws Exception {
		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Aposta aposta = new Aposta(user1, partida, 1, -1);

	}

	//Verifica se o custo da aposta é igual a 50f.
	@Test
	public void teste_Custo_Aposta() throws Exception {
		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Aposta aposta = new Aposta(user1, partida, 1, 1);
		partida.liberarApostas();
		aposta.enviar();

		Assert.assertEquals(150, user1.getMoedas(), 0);

	}

	//Verifica se a aposta não é enviada se ainda não estiver liberada.
	@Test
	public void teste_Aposta_NãoLiberada() throws Exception {
		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Aposta aposta = new Aposta(user1, partida, 1, 1);

		Assert.assertFalse(aposta.enviar());

	}

	//Verifica se a aposta não é enviada se o usuário não tiver saldo suficiente.
	@Test
	public void teste_Aposta_Usuario_Saldo_Insuficiente() throws Exception {
		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Aposta aposta = new Aposta(user1, partida, 1, 1);
		partida.liberarApostas();
		aposta.enviar();
		Aposta aposta2 = new Aposta(user1, partida, 2, 1);
		aposta2.enviar();
		Aposta aposta3 = new Aposta(user1, partida, 1, 2);
		aposta3.enviar();
		Aposta aposta4 = new Aposta(user1, partida, 3, 2);

		Assert.assertFalse(aposta4.enviar());

	}

	//Verifica se a aposta não é enviada se o usuário não tiver saldo suficiente.
	@Test
	public void teste_Enviar_Aposta() throws Exception {
		Usuario user1 = new Usuario("sheila@gmail.com", "12345678910", "1234$5");
		Partida partida = new Partida("Champions", "Real", "Barcelona");
		Aposta aposta = new Aposta(user1, partida, 1, 1);
		partida.liberarApostas();

		Assert.assertTrue(aposta.enviar());

	}

}
