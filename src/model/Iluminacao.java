package model;

public class Iluminacao extends Equipamento {

	private double percentualEconomiaLED;

	public Iluminacao(String nome, String patrimonio, double potenciaWatts, double percentualEconomiaLED) {
		super(nome, patrimonio, potenciaWatts);
		setPercentualEconomiaLED(percentualEconomiaLED);
	}

	@Override
	public double calcularConsumoMensal() {
		double consumoBase = (getPotenciaWatts() * 8 * 30) / 1000;
		return consumoBase - (consumoBase * percentualEconomiaLED);
	}

	public double getPercentualEconomiaLED() {
		return percentualEconomiaLED;
	}

	public void setPercentualEconomiaLED(double percentualEconomiaLED) {
		if (percentualEconomiaLED < 0) {
			System.out.println("Valor invalido para percentualEconomiaLED: numero negativo nao e aceito. Valor anterior mantido.");
			return;
		}
		this.percentualEconomiaLED = percentualEconomiaLED;
	}
}
