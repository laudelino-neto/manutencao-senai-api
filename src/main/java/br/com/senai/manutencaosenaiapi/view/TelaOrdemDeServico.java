package br.com.senai.manutencaosenaiapi.view;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.entity.OrdemDeServico;
import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.Tecnico;
import br.com.senai.manutencaosenaiapi.service.ClienteService;
import br.com.senai.manutencaosenaiapi.service.OrdemDeServicoService;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TecnicoService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class TelaOrdemDeServico extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private OrdemDeServicoService ordemService;
	
	private JComboBox<Cliente> cbCliente;
	
	private JComboBox<Tecnico> cbTecnico;
	
	private JComboBox<Peca> cbPeca;
	
	public void carregarCombos() {
		List<Cliente> clientes = clienteService.listarTodos();
		for (Cliente cliente : clientes) {
			cbCliente.addItem(cliente);
		}
		List<Tecnico> tecnicos = tecnicoService.listarTodos();
		for (Tecnico tecnico : tecnicos) {
			cbTecnico.addItem(tecnico);
		}
		List<Peca> pecas = pecaService.listarTodas();
		for (Peca peca : pecas) {
			cbPeca.addItem(peca);
		}
	}
	
	public void apresentarTela() {
		this.setVisible(true);
		this.carregarCombos();
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaOrdemDeServico() throws ParseException {		
		setResizable(false);
		setTitle("Ordens de Serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		
		cbCliente = new JComboBox<>();		
		
		JLabel lblNewLabel_1 = new JLabel("Técnico");
		
		cbTecnico = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("Abertura");
		
		JFormattedTextField fmtAbertura = new JFormattedTextField(
				new MaskFormatter("##/##/####"));
		
		JFormattedTextField ftmEncerramento = new JFormattedTextField(
				new MaskFormatter("##/##/####"));
		
		JLabel lblNewLabel_3 = new JLabel("Encerramento");
		
		JTextArea jtaProblema = new JTextArea();
		
		JTextArea jtaSolucao = new JTextArea();
		
		JLabel lblNewLabel_4 = new JLabel("Detalhamento do problema");
		
		JLabel lblNewLabel_5 = new JLabel("Detalhamento da solução");
		
		JLabel lblNewLabel_6 = new JLabel("Peça");
		
		cbPeca = new JComboBox();
		
		JButton btnAddPeca = new JButton("Adicionar");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRemoverPeca = new JButton("Remover Peça Selecionada");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemDeServico novaOrdem = new OrdemDeServico();
				Cliente clienteSelecionado = (Cliente)cbCliente.getSelectedItem();
				novaOrdem.setCliente(clienteSelecionado);
				Tecnico tecnicoSelecionado = (Tecnico)cbTecnico.getSelectedItem();
				novaOrdem.setTecnico(tecnicoSelecionado);
				novaOrdem.setDescricaoDoProblema(jtaProblema.getText());
				String[] camposDaData = fmtAbertura.getText().split("/");
				LocalDate dataDeAbertura = LocalDate.of(
						Integer.parseInt(camposDaData[2]), 
						Integer.parseInt(camposDaData[1]), 
						Integer.parseInt(camposDaData[0]));
				novaOrdem.setDataDeAbertura(dataDeAbertura);
				novaOrdem.setPecasDoReparo(new ArrayList<>());
				ordemService.inserir(novaOrdem);
				JOptionPane.showMessageDialog(
						contentPane, "Ordem salva com sucesso");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(jtaProblema, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jtaSolucao, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
								.addComponent(cbTecnico, 0, 570, Short.MAX_VALUE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(fmtAbertura, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3)
										.addComponent(ftmEncerramento, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addGap(138)
									.addComponent(lblNewLabel_5))
								.addComponent(cbCliente, 0, 570, Short.MAX_VALUE)
								.addComponent(lblNewLabel_6)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(cbPeca, 0, 476, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddPeca))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(390)
							.addComponent(btnRemoverPeca, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(484, Short.MAX_VALUE)
							.addComponent(btnSalvar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbCliente, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbTecnico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fmtAbertura, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(ftmEncerramento, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jtaSolucao, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtaProblema, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbPeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddPeca))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoverPeca)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(btnSalvar))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
