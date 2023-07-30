import static org.junit.Assert.*;

import org.junit.Test;

import model.Aposta;

import model.Usuario;

import model.Partida;

public class CaixaPreta {

	@Test
	public void test() {
		assertEquals(true, true);
	}
	
	//TOPICO 1
	
	@Test
	public void test_usuario_cpf() {
		Usuario user = new Usuario();
		user.setCpf("501.220.590-18");
		
		assertEquals("501.220.590-18", user.getCpf());
	}
	
	@Test
	public void test_limit_cpf() {
		Usuario user = new Usuario();
		user.setCpf("501.220.590-18");
		String cpf = user.getCpf().replaceAll("\\D", "");
		
		assertEquals(cpf.length(), 11);
	}
	
	
	@Test
	public void test_email_user() {
		Usuario user = new Usuario();
		user.setEmail("teste@teste.com");
		assertTrue(user.getEmail().contains("@"));
		char[] email = user.getEmail().toCharArray();
		assertNotNull(email[user.getEmail().indexOf('@') + 1]);
	}
	
	@Test
	public void test_password_user() {
		Usuario user = new Usuario();
		user.setPassword("senha123_");
		char[] senha = user.getPassword().toCharArray();
		boolean existSpecial = false;
		
		for(int i=0; i < user.getPassword().length(); i++) {
			if(!Character.isLetterOrDigit(senha[i])) {
				existSpecial = true;
				break;
			}
		}
		
		assertTrue(existSpecial);	
	}
	
	//TOPICO 2
	
	@Test
	public void test_create_user_coins() {
		Usuario user = new Usuario();
		assertTrue(user.getMoedas() == 200);
	}
	
	//TOPICO 3
	
	@Test
	public void test_block_bet() {
		Usuario user = new Usuario();
		Partida partida = new Partida();
		Aposta aposta = new Aposta();
		user.setCpf("501.220.590-18");
		user.setEmail("teste@teste.com");
		user.setPassword("senha123_");
		partida.setCampeonato("Campeonato Mundial");
		partida.setTimeVisitante("Real Madrid");
		partida.setTimeMandante("Vasco da Gama");
		aposta.setApostador(user);
		aposta.setGolsMandate(6);
		aposta.setGolsVisitante(2);
		aposta.setPartida(partida);	
		assertFalse(aposta.enviar());
	}
	
	//TOPICO 4
	
	@Test
	public void test_open_bet() {
		Usuario user = new Usuario();
		Partida partida = new Partida();
		Aposta aposta = new Aposta();
		user.setCpf("501.220.590-18");
		user.setEmail("teste@teste.com");
		user.setPassword("senha123_");
		partida.setCampeonato("Campeonato Mundial");
		partida.setTimeVisitante("Real Madrid");
		partida.setTimeMandante("Vasco da Gama");
		aposta.setApostador(user);
		aposta.setGolsMandate(6);
		aposta.setGolsVisitante(2);
		aposta.setPartida(partida);	
		assertFalse(aposta.enviar());
		partida.liberarApostas();
		assertTrue(aposta.enviar());
	}
	
	//TOPICO 6
	
	@Test
	public void test_bet_price() {
		Usuario user = new Usuario();
		float start = user.getMoedas();
		user.diminuirMoedas();
		assertTrue(start - user.getMoedas() == 50);
	}
	
	@Test
	public void test_bet_price_fail() {
		Usuario user = new Usuario();
		float start = user.getMoedas();
		user.diminuirMoedas();
		assertFalse(start - user.getMoedas() == 10);
	}
	
	//TOPICO 7
	
	@Test
	public void test_bet_allow() {
		Partida partida = new Partida();
		partida.liberarApostas();			
		
		assertTrue(partida.estaDisponivelReceberApostas());
		
	}
	
	@Test
	public void test_bet_denied() {
		Partida partida = new Partida();	
		assertFalse(partida.estaDisponivelReceberApostas());
		
	}
	
	@Test
	public void test_bet_released() {
		Partida partida = new Partida();
		Usuario user = new Usuario();
		Aposta aposta = new Aposta();
		user.setCpf("501.220.590-18");
		user.setEmail("teste@teste.com");
		user.setPassword("senha123_");
		float quantMoedas = user.getMoedas();
		
		partida.liberarApostas();		
		
		int quantGolsVisitante = 3;
		aposta.setGolsVisitante(quantGolsVisitante);
		int quantGolsMandante = 1;
		aposta.setGolsMandate(quantGolsMandante);
		aposta.setPartida(partida);
		aposta.setApostador(user);
		
		assertTrue(aposta.enviar());
		
	}
	
}
