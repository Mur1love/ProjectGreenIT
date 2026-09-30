package view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Computador;
import model.ConsomeEnergia;
import model.Iluminacao;
import model.Reciclavel;
import model.Servidor;
import model.VeiculoEletrico;

public class TelaMonitoramentoTiVerde extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String TIPO_COMPUTADOR = "Computador";
	private static final String TIPO_SERVIDOR = "Servidor";
	private static final String TIPO_ILUMINACAO = "Iluminação";
	private static final String TIPO_VEICULO = "Veículo Elétrico";

	private final ArrayList<ConsomeEnergia> equipamentos = new ArrayList<>();

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPatrimonio;
	private JTextField txtPotencia;
	private JTextField txtCarga;
	private JTextField txtEconomia;
	private JTextField txtKm;
	private JTextField txtConsumoPorKm;
	private JComboBox<String> cbTipoEquipamento;
	private JLabel lblPatrimonio;
	private JLabel lblPotencia;
	private JPanel pnlCards;
	private CardLayout cards;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblTotalConsumo;
	private JLabel lblPegadaCarbono;

	public TelaMonitoramentoTiVerde() {
		setTitle("Sistema de Monitoramento de TI Verde");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 24, 50, 20);
		contentPane.add(lblTipo);

		cbTipoEquipamento = new JComboBox<>();
		cbTipoEquipamento.setModel(new DefaultComboBoxModel<String>(
				new String[] { TIPO_COMPUTADOR, TIPO_SERVIDOR, TIPO_ILUMINACAO, TIPO_VEICULO }));
		cbTipoEquipamento.setBounds(80, 20, 230, 26);
		contentPane.add(cbTipoEquipamento);
		cbTipoEquipamento.addItemListener(e -> sincronizarTela());

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 64, 150, 20);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(180, 60, 200, 26);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblPatrimonio = new JLabel("Patrimônio:");
		lblPatrimonio.setBounds(20, 104, 150, 20);
		contentPane.add(lblPatrimonio);

		txtPatrimonio = new JTextField();
		txtPatrimonio.setBounds(180, 100, 200, 26);
		contentPane.add(txtPatrimonio);
		txtPatrimonio.setColumns(10);

		lblPotencia = new JLabel("Potência (W):");
		lblPotencia.setBounds(20, 144, 150, 20);
		contentPane.add(lblPotencia);

		txtPotencia = new JTextField();
		txtPotencia.setBounds(180, 140, 200, 26);
		contentPane.add(txtPotencia);
		txtPotencia.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar Equipamento");
		btnAdicionar.setBounds(420, 60, 220, 30);
		contentPane.add(btnAdicionar);
		btnAdicionar.addActionListener(e -> adicionarEquipamento());

		cards = new CardLayout();
		pnlCards = new JPanel(cards);
		pnlCards.setBounds(20, 180, 620, 80);
		contentPane.add(pnlCards);

		pnlCards.add(new JPanel(), TIPO_COMPUTADOR);

		JPanel cardServidor = new JPanel();
		cardServidor.setLayout(null);
		JLabel lblCarga = new JLabel("Carga de Processamento (%):");
		lblCarga.setBounds(0, 4, 200, 20);
		cardServidor.add(lblCarga);
		txtCarga = new JTextField();
		txtCarga.setBounds(210, 0, 200, 26);
		cardServidor.add(txtCarga);
		txtCarga.setColumns(10);
		pnlCards.add(cardServidor, TIPO_SERVIDOR);

		JPanel cardIluminacao = new JPanel();
		cardIluminacao.setLayout(null);
		JLabel lblEconomia = new JLabel("Economia LED (fração, ex: 0.30):");
		lblEconomia.setBounds(0, 4, 200, 20);
		cardIluminacao.add(lblEconomia);
		txtEconomia = new JTextField();
		txtEconomia.setBounds(210, 0, 200, 26);
		cardIluminacao.add(txtEconomia);
		txtEconomia.setColumns(10);
		pnlCards.add(cardIluminacao, TIPO_ILUMINACAO);

		JPanel cardVeiculo = new JPanel();
		cardVeiculo.setLayout(null);
		JLabel lblKm = new JLabel("km Rodados no Mês:");
		lblKm.setBounds(0, 4, 200, 20);
		cardVeiculo.add(lblKm);
		txtKm = new JTextField();
		txtKm.setBounds(210, 0, 200, 26);
		cardVeiculo.add(txtKm);
		txtKm.setColumns(10);
		JLabel lblConsumoPorKm = new JLabel("Consumo (kWh/km):");
		lblConsumoPorKm.setBounds(0, 44, 200, 20);
		cardVeiculo.add(lblConsumoPorKm);
		txtConsumoPorKm = new JTextField();
		txtConsumoPorKm.setBounds(210, 40, 200, 26);
		cardVeiculo.add(txtConsumoPorKm);
		txtConsumoPorKm.setColumns(10);
		pnlCards.add(cardVeiculo, TIPO_VEICULO);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 275, 620, 130);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new Object[] { "Nome", "Tipo", "Consumo Mensal (kWh)" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JButton btnCalcularConsumo = new JButton("Calcular Consumo Total");
		btnCalcularConsumo.setBounds(20, 425, 200, 30);
		contentPane.add(btnCalcularConsumo);
		btnCalcularConsumo.addActionListener(e -> calcularConsumoTotal());

		lblTotalConsumo = new JLabel("Consumo total: -");
		lblTotalConsumo.setBounds(240, 430, 360, 20);
		contentPane.add(lblTotalConsumo);

		JButton btnCalcularPegada = new JButton("Calcular Pegada de Carbono");
		btnCalcularPegada.setBounds(20, 465, 200, 30);
		contentPane.add(btnCalcularPegada);
		btnCalcularPegada.addActionListener(e -> calcularPegadaCarbono());

		lblPegadaCarbono = new JLabel("Pegada de carbono: -");
		lblPegadaCarbono.setBounds(240, 470, 360, 20);
		contentPane.add(lblPegadaCarbono);

		sincronizarTela();
	}

	private void sincronizarTela() {
		String tipo = tipoSelecionado();
		boolean veiculo = TIPO_VEICULO.equals(tipo);
		lblPatrimonio.setVisible(!veiculo);
		txtPatrimonio.setVisible(!veiculo);
		lblPotencia.setVisible(!veiculo);
		txtPotencia.setVisible(!veiculo);
		cards.show(pnlCards, tipo);
	}

	private String tipoSelecionado() {
		return (String) cbTipoEquipamento.getSelectedItem();
	}

	private void adicionarEquipamento() {
		String nome = txtNome.getText().trim();
		if (nome.isEmpty()) {
			avisar("Informe o nome do item.");
			return;
		}

		ConsomeEnergia item = criarItem(nome);
		if (item == null) {
			return;
		}
		equipamentos.add(item);
		tableModel.addRow(new Object[] { nome, tipoSelecionado(), formatar(item.calcularConsumoMensal()) + " kWh" });
		limparCampos();
	}

	private ConsomeEnergia criarItem(String nome) {
		String tipo = tipoSelecionado();
		switch (tipo) {
		case TIPO_VEICULO:
			Double km = lerNumero(txtKm, "km rodados no mês", Double.POSITIVE_INFINITY);
			Double consumoPorKm = lerNumero(txtConsumoPorKm, "consumo por km", Double.POSITIVE_INFINITY);
			if (km == null || consumoPorKm == null) {
				return null;
			}
			return new VeiculoEletrico(nome, km, consumoPorKm);
		default:
			String patrimonio = txtPatrimonio.getText().trim();
			if (patrimonio.isEmpty()) {
				avisar("Informe o código de patrimônio do equipamento.");
				return null;
			}
			Double potencia = lerNumero(txtPotencia, "potência em watts", Double.POSITIVE_INFINITY);
			if (potencia == null) {
				return null;
			}
			switch (tipo) {
			case TIPO_COMPUTADOR:
				return new Computador(nome, patrimonio, potencia);
			case TIPO_SERVIDOR:
				Double carga = lerNumero(txtCarga, "carga de processamento", 100);
				return carga == null ? null : new Servidor(nome, patrimonio, potencia, carga);
			case TIPO_ILUMINACAO:
				Double economia = lerNumero(txtEconomia, "economia LED (fração de 0 a 1)", 1);
				return economia == null ? null : new Iluminacao(nome, patrimonio, potencia, economia);
			default:
				avisar("Tipo de item desconhecido.");
				return null;
			}
		}
	}

	private Double lerNumero(JTextField campo, String rotulo, double maximo) {
		String texto = campo.getText().trim().replace(',', '.');
		if (texto.isEmpty()) {
			avisar("Informe o campo: " + rotulo + ".");
			return null;
		}
		try {
			double valor = Double.parseDouble(texto);
			if (valor < 0) {
				avisar("O valor de " + rotulo + " não pode ser negativo.");
				return null;
			}
			if (valor > maximo) {
				avisar("O valor de " + rotulo + " deve ser no máximo " + formatar(maximo) + ".");
				return null;
			}
			return valor;
		} catch (NumberFormatException ex) {
			avisar("Valor inválido no campo: " + rotulo + ". Digite um número.");
			return null;
		}
	}

	private void calcularConsumoTotal() {
		if (equipamentos.isEmpty()) {
			avisar("Nenhum item cadastrado ainda.");
			return;
		}
		double total = 0;
		for (ConsomeEnergia item : equipamentos) {
			total += item.calcularConsumoMensal();
		}
		lblTotalConsumo.setText("Consumo total estimado: " + formatar(total) + " kWh/mês");
	}

	private void calcularPegadaCarbono() {
		if (equipamentos.isEmpty()) {
			avisar("Nenhum item cadastrado ainda.");
			return;
		}
		double total = 0;
		for (ConsomeEnergia item : equipamentos) {
			if (item instanceof Reciclavel reciclavel) {
				total += reciclavel.calcularPegadaCarbono();
			}
		}
		lblPegadaCarbono.setText("Pegada de carbono: " + formatar(total) + " kg CO2/mês");
	}

	private void limparCampos() {
		txtNome.setText("");
		txtPatrimonio.setText("");
		txtPotencia.setText("");
		txtCarga.setText("");
		txtEconomia.setText("");
		txtKm.setText("");
		txtConsumoPorKm.setText("");
		txtNome.requestFocus();
	}

	private void avisar(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Dados inválidos", JOptionPane.WARNING_MESSAGE);
	}

	private String formatar(double valor) {
		return String.format("%.2f", valor);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaMonitoramentoTiVerde frame = new TelaMonitoramentoTiVerde();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
