import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Empregado {
    private int id;
    private String nome;
    private int idade;
    private String cargo;
    private String email;
    private String telefone;
    private String alergias;
    private String condicaoMedica;

    public Empregado(int id, String nome, int idade, String cargo, String email, String telefone, String alergias, String condicaoMedica) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.alergias = alergias;
        this.condicaoMedica = condicaoMedica;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Nome: %-15s | Idade: %-3d | Cargo: %-15s | Email: %-20s | Telefone: %-15s | Alergias: %-20s | Condição Médica: %-20s",
                id, nome, idade, cargo, email, telefone, alergias, condicaoMedica);
    }
}

class GerenciadorEmpregados {
    private List<Empregado> empregados;

    public GerenciadorEmpregados() {
        this.empregados = new ArrayList<>();
    }

    public void adicionarEmpregado(Empregado empregado) {
        empregados.add(empregado);
    }

    public List<Empregado> getEmpregados() {
        return empregados;
    }
}

public class Main extends JFrame {
    private GerenciadorEmpregados gerenciador;
    private JTextField idField, nomeField, idadeField, cargoField, emailField, telefoneField, alergiasField, condicaoMedicaField;
    private JTextArea empregadosArea;

    public Main() {
        gerenciador = new GerenciadorEmpregados();
        initUI();
    }

    private void initUI() {
        setTitle("Gerenciamento de Empregados");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor do painel

        // Componentes de entrada
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Idade:"));
        idadeField = new JTextField();
        inputPanel.add(idadeField);

        inputPanel.add(new JLabel("Cargo:"));
        cargoField = new JTextField();
        inputPanel.add(cargoField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        inputPanel.add(telefoneField);

        inputPanel.add(new JLabel("Alergias:"));
        alergiasField = new JTextField();
        inputPanel.add(alergiasField);

        inputPanel.add(new JLabel("Condição Médica:"));
        condicaoMedicaField = new JTextField();
        inputPanel.add(condicaoMedicaField);

        // Botões
        JButton adicionarButton = new JButton("Adicionar Empregado");
        adicionarButton.addActionListener(new AdicionarEmpregadoAction());
        inputPanel.add(adicionarButton);

        JButton listarButton = new JButton("Listas de Empregados");
        listarButton.addActionListener(new ListarEmpregadosAction());
        inputPanel.add(listarButton);

        add(inputPanel, BorderLayout.NORTH);

        // Área de texto para listar empregados
        empregadosArea = new JTextArea();
        empregadosArea.setEditable(false);
        empregadosArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor da área de texto
        add(new JScrollPane(empregadosArea), BorderLayout.CENTER);
    }

    private class AdicionarEmpregadoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(idField.getText());
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String cargo = cargoField.getText();
            String email = emailField.getText();
            String telefone = telefoneField.getText();
            String alergias = alergiasField.getText();
            String condicaoMedica = condicaoMedicaField.getText();

            Empregado empregado = new Empregado(id, nome, idade, cargo, email, telefone, alergias, condicaoMedica);
            gerenciador.adicionarEmpregado(empregado);

            // Limpar campos de entrada
            idField.setText("");
            nomeField.setText("");
            idadeField.setText("");
            cargoField.setText("");
            emailField.setText("");
            telefoneField.setText("");
            alergiasField.setText("");
            condicaoMedicaField.setText("");

            JOptionPane.showMessageDialog(Main.this, "Empregado cadastrado com sucesso!");
        }
    }

    private class ListarEmpregadosAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Empregado> empregados = gerenciador.getEmpregados();
            empregadosArea.setText("");
            for (Empregado empregado : empregados) {
                empregadosArea.append(empregado.toString() + "\n");
            }
            if (empregados.isEmpty()) {
                empregadosArea.setText("Nenhum empregado cadastrado no sistema.");
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}
