package model;

public class Usuario {

	private String email;
	private String cpf;
	private String password;
	private float moedas;
	private static final String special = "!@_$%&*./#?";

	public Usuario(String email, String cpf, String password) throws Exception {
		super();
		this.email = email;
		this.cpf = cpf;
		this.password = password;
		this.moedas = 200f;

		this.ValidaCpf();
		this.ValidaEmail();
		this.ValidaPassword();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		this.email = email;
		this.ValidaEmail();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Exception {
		this.cpf = cpf;
		this.ValidaCpf();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = password;
		this.ValidaPassword();
	}

	public boolean temSaldoSuficiente() {
		return this.moedas > Aposta.CUSTO_APOSTA;
	}

	public float getMoedas() {
		return this.moedas;
	}

	public void diminuirMoedas() {
		this.moedas = this.moedas - Aposta.CUSTO_APOSTA;
	}

	public void ValidaEmail() throws Exception {
		if (!this.email.contains("@")) {
			throw new Exception("Email inválido");
		}
	}

	// Incluido Validação do nome do CPF
	public void ValidaCpf() throws Exception {
		if (this.cpf.length() > 11 || this.cpf.contains(".") || this.cpf.contains("-")) {
			throw new Exception("CPF inválido. O CPF deve conter apenas números");
		}
	}

	// Incluido Validação do password
	public void ValidaPassword() throws Exception {
		boolean containsSpecial = false;
		for (char c : this.password.toCharArray()) {
			for (int i = 0; i < special.length(); i++) {
				if (c == special.charAt(i)) {
					containsSpecial = true;
				}
			}
		}
		if (!containsSpecial) {
			throw new Exception("Password deve conter ao menos 1 caractere especial");
		}

	}

}
