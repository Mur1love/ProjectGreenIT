package model;

public class Servidor extends EquipamentoEletronico {

	private double cargaProcessamento;

	public Servidor(String nome, String patrimonio, double potenciaWatts, double cargaProcessamento) {
		super(nome, patrimonio, potenciaWatts);
		setCargaProcessamento(cargaProcessamento);
	}

	@Override
	public double calcularConsumoMensal() {
		double consumoBase = (getPotenciaWatts() * 24 * 30) / 1000;
		double adicionalPorCarga = consumoBase * (cargaProcessamento / 100) * 0.05;
		return consumoBase + adicionalPorCarga;
	}

	public double getCargaProcessamento() {
		return cargaProcessamento;
	}

	public void setCargaProcessamento(double cargaProcessamento) {
		if (cargaProcessamento < 0) {
			System.out.println("Valor invalido para cargaProcessamento: numero negativo nao e aceito. Valor anterior mantido.");
			return;
		}
		this.cargaProcessamento = cargaProcessamento;
	}
}
