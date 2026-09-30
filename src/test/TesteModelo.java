package test;

import model.Computador;
import model.ConsomeEnergia;
import model.Iluminacao;
import model.Reciclavel;
import model.Servidor;
import model.VeiculoEletrico;

public class TesteModelo {

	public static void main(String[] args) {
		Computador computador = new Computador("PC-01", "PAT-001", 150);
		Servidor servidor = new Servidor("SRV-01", "PAT-002", 400, 70);
		Iluminacao iluminacao = new Iluminacao("Lampadas Setor A", "PAT-003", 100, 0.30);
		VeiculoEletrico veiculo = new VeiculoEletrico("Fiat 500e", 1200, 0.18);

		computador.exibirDados();
		servidor.exibirDados();
		iluminacao.exibirDados();

		System.out.printf("Computador: %.2f kWh | pegada: %.2f kg CO2%n", computador.calcularConsumoMensal(),
				computador.calcularPegadaCarbono());
		System.out.printf("Servidor: %.2f kWh%n", servidor.calcularConsumoMensal());
		System.out.printf("Iluminacao: %.2f kWh%n", iluminacao.calcularConsumoMensal());
		System.out.printf("Veiculo Eletrico: %.2f kWh%n", veiculo.calcularConsumoMensal());

		ConsomeEnergia[] itens = { computador, servidor, iluminacao, veiculo };
		double total = 0;
		double pegada = 0;
		for (ConsomeEnergia item : itens) {
			total += item.calcularConsumoMensal();
			if (item instanceof Reciclavel reciclavel) {
				pegada += reciclavel.calcularPegadaCarbono();
			}
		}
		System.out.printf("Consumo total: %.2f kWh (esperado ~557,28)%n", total);
		System.out.printf("Pegada de carbono: %.2f kg CO2 (esperado ~13,2)%n", pegada);

		System.out.println("--- Teste de validacao (valores negativos) ---");
		servidor.setCargaProcessamento(-10);
		System.out.printf("cargaProcessamento apos setter negativo: %.2f (esperado 70.00)%n",
				servidor.getCargaProcessamento());
		computador.setPotenciaWatts(-5);
		System.out.printf("potenciaWatts apos setter negativo: %.2f (esperado 150.00)%n",
				computador.getPotenciaWatts());
	}
}
