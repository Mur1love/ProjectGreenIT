package model;

public abstract class Equipamento implements ConsomeEnergia {

	private String nome;
	private String patrimonio;
	private double potenciaWatts;

	public Equipamento(String nome, String patrimonio, double potenciaWatts) {
		this.nome = nome;
		this.patrimonio = patrimonio;
		setPotenciaWatts(potenciaWatts);
	}

	public void exibirDados() {
		System.out.println("Nome: " + nome + " | Patrimonio: " + patrimonio + " | Potencia: " + potenciaWatts + " W");
	}

	@Override
	public abstract double calcularConsumoMensal();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public double getPotenciaWatts() {
		return potenciaWatts;
	}

	public void setPotenciaWatts(double potenciaWatts) {
		if (potenciaWatts < 0) {
			System.out.println("Valor invalido para potenciaWatts: numero negativo nao e aceito. Valor anterior mantido.");
			return;
		}
		this.potenciaWatts = potenciaWatts;
	}
}
