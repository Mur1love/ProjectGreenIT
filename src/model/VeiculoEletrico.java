package model;

public class VeiculoEletrico implements ConsomeEnergia {

	private String nome;
	private double kmRodadosMes;
	private double consumoPorKm;

	public VeiculoEletrico(String nome, double kmRodadosMes, double consumoPorKm) {
		this.nome = nome;
		setKmRodadosMes(kmRodadosMes);
		setConsumoPorKm(consumoPorKm);
	}

	@Override
	public double calcularConsumoMensal() {
		return kmRodadosMes * consumoPorKm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getKmRodadosMes() {
		return kmRodadosMes;
	}

	public void setKmRodadosMes(double kmRodadosMes) {
		if (kmRodadosMes < 0) {
			System.out.println("Valor invalido para kmRodadosMes: numero negativo nao e aceito. Valor anterior mantido.");
			return;
		}
		this.kmRodadosMes = kmRodadosMes;
	}

	public double getConsumoPorKm() {
		return consumoPorKm;
	}

	public void setConsumoPorKm(double consumoPorKm) {
		if (consumoPorKm < 0) {
			System.out.println("Valor invalido para consumoPorKm: numero negativo nao e aceito. Valor anterior mantido.");
			return;
		}
		this.consumoPorKm = consumoPorKm;
	}
}
