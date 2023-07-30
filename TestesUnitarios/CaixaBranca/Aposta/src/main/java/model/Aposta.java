package model;

public class Aposta {

	public static final float CUSTO_APOSTA = 50f;

	private int golsVisitante;
	private int golsMandate;
	private Usuario apostador;
	private Partida partida;

	public Aposta(Usuario apostador, Partida partida, int golsVisitante, int golsMandate) throws Exception {
		super();
		this.apostador = apostador;
		this.golsVisitante = golsVisitante;
		this.golsMandate = golsMandate;
		this.partida = partida;

		this.ValidaGolsMandante();
		this.ValidaGolsVisitante();
	}

	public int getGolsVisitante() {
		return golsVisitante;
	}

	public void setGolsVisitante(int golsVisitante) throws Exception {
		this.golsVisitante = golsVisitante;
		this.ValidaGolsVisitante();
	}

	public int getGolsMandate() {
		return golsMandate;
	}

	public void setGolsMandate(int golsMandate) throws Exception {
		this.golsMandate = golsMandate;
		this.ValidaGolsMandante();
	}

	public Usuario getApostador() {
		return apostador;
	}

	public void setApostador(Usuario apostador) {
		this.apostador = apostador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public boolean enviar() {
		if (this.partida.estaDisponivelReceberApostas() && this.apostador.temSaldoSuficiente()) {
			return this.partida.enviarAposta(this);
		}
		return false;
	}

	// Incluido Validação da quantidade de gols dos visitantes
	public void ValidaGolsVisitante() throws Exception {
		if (this.golsVisitante < 0) {
			throw new Exception("A quantidade de gols deve ser maior ou igual a 0");
		}
	}

	// Incluido Validação da quantidade de gols dos mandantes
	public void ValidaGolsMandante() throws Exception {
		if (this.golsMandate < 0) {
			throw new Exception("A quantidade de gols deve ser maior ou igual a 0");
		}
	}

}
