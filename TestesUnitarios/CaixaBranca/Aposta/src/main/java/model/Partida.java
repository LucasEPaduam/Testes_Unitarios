package model;

import java.util.ArrayList;

public class Partida {

	private String campeonato;
	private String timeVisitante;
	private String timeMandante;
	private String status;

	private ArrayList<Aposta> apostas;

	public Partida(String campeonato, String timeVisitante, String timeMandante) throws Exception {
		super();
		this.campeonato = campeonato;
		this.timeVisitante = timeVisitante;
		this.timeMandante = timeMandante;
		this.status = "aposta_bloqueada";
		this.apostas = new ArrayList<Aposta>();

		this.ValidaCampeonato();
		this.ValidaMandante();
		this.ValidaVisitante();
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) throws Exception {
		this.campeonato = campeonato;
		this.ValidaCampeonato();
	}

	public String getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(String timeVisitante) throws Exception {
		this.timeVisitante = timeVisitante;
		this.ValidaVisitante();
	}

	public String getTimeMandante() {
		return timeMandante;
	}

	public void setTimeMandante(String timeMandante) throws Exception {
		this.timeMandante = timeMandante;
		this.ValidaMandante();
	}

	public String getStatus() {
		return this.status;
	}

	public void liberarApostas() {
		this.status = "apostas_abertas";
	}

	public boolean estaDisponivelReceberApostas() {
		return this.status == "apostas_abertas";
	}

	public int getNumeroApostas() {
		return this.apostas.size();
	}

	public boolean enviarAposta(Aposta aposta) {
		int apostas_anteriores = this.getNumeroApostas();
		this.apostas.add(aposta);
		aposta.getApostador().diminuirMoedas();
		return apostas_anteriores < this.getNumeroApostas();
	}

	// Incluido Validação do nome do campeonato
	public void ValidaCampeonato() throws Exception {
		if (this.campeonato.trim().isEmpty()) {
			throw new Exception("Informe o nome do campeonato");
		}
	}

	// Incluido Validação do nome do visitante
	public void ValidaVisitante() throws Exception {
		if (this.timeVisitante.trim().isEmpty()) {
			throw new Exception("Informe o nome do Time Visitante");
		}
	}

	// Incluido Validação do nome do Mandante
	public void ValidaMandante() throws Exception {
		if (this.timeMandante.trim().isEmpty()) {
			throw new Exception("Informe o nome do Time Mandante");
		}
	}

}
