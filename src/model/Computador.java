package model;

public class Computador extends EquipamentoEletronico implements Reciclavel {

	public Computador(String nome, String patrimonio, double potenciaWatts) {
		super(nome, patrimonio, potenciaWatts);
	}

	@Override
	public double calcularConsumoMensal() {
		return (getPotenciaWatts() * 8 * 22) / 1000;
	}

	@Override
	public double calcularPegadaCarbono() {
		return calcularConsumoMensal() * getFatorEmissaoCO2();
	}
}
