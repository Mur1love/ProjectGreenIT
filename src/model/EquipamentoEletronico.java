package model;

public abstract class EquipamentoEletronico extends Equipamento {

	private double fatorEmissaoCO2;

	public EquipamentoEletronico(String nome, String patrimonio, double potenciaWatts) {
		super(nome, patrimonio, potenciaWatts);
		this.fatorEmissaoCO2 = 0.5;
	}

	public double getFatorEmissaoCO2() {
		return fatorEmissaoCO2;
	}

	public void setFatorEmissaoCO2(double fatorEmissaoCO2) {
		if (fatorEmissaoCO2 < 0) {
			System.out.println("Valor invalido para fatorEmissaoCO2: numero negativo nao e aceito. Valor anterior mantido.");
			return;
		}
		this.fatorEmissaoCO2 = fatorEmissaoCO2;
	}
}
