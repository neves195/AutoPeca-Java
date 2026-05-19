import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
 
public class FormularioPecas extends JFrame {
 
    private JTextField txtNome;
    private JTextField txtMarca;
    private JTextField txtPreco;
    private JTextField txtQuantidade;
    private JTextField txtBusca;
 
    private JButton btCadastrar;
    private JButton btAlterar;
    private JButton btExcluir;
    private JButton btBuscar;
    private JButton btListarTodos;
 
    private JTable tabela;
    private DefaultTableModel modelo;
 
    // Guarda o nome original da peça selecionada (usado na alteração)
    private String nomeOriginalSelecionado = "";
 
    public FormularioPecas() {
        setTitle("Cadastro de Peças Automotivas");
        setSize(700, 450);
        setLayout(new BorderLayout());
 
        // PAINEL SUPERIOR
        JPanel painel = new JPanel(new GridLayout(3, 1));
 
        // Linha 1 - campos de cadastro
        JPanel linha1 = new JPanel(new FlowLayout());
        linha1.add(new JLabel("Nome:"));
        txtNome = new JTextField(10);
        linha1.add(txtNome);
        linha1.add(new JLabel("Marca:"));
        txtMarca = new JTextField(8);
        linha1.add(txtMarca);
        linha1.add(new JLabel("Preço R$:"));
        txtPreco = new JTextField(6);
        linha1.add(txtPreco);
        linha1.add(new JLabel("Qtd:"));
        txtQuantidade = new JTextField(4);
        linha1.add(txtQuantidade);
 
        // Linha 2 - botões de ação
        JPanel linha2 = new JPanel(new FlowLayout());
        btCadastrar = new JButton("Cadastrar");
        btAlterar   = new JButton("Alterar");
        btExcluir   = new JButton("Excluir");
        linha2.add(btCadastrar);
        linha2.add(btAlterar);
        linha2.add(btExcluir);
 
        // Linha 3 - busca
        JPanel linha3 = new JPanel(new FlowLayout());
        linha3.add(new JLabel("Buscar nome:"));
        txtBusca = new JTextField(15);
        linha3.add(txtBusca);
        btBuscar     = new JButton("Buscar");
        btListarTodos = new JButton("Listar Todos");
        linha3.add(btBuscar);
        linha3.add(btListarTodos);
 
        painel.add(linha1);
        painel.add(linha2);
        painel.add(linha3);
        add(painel, BorderLayout.NORTH);
 
        // TABELA
        modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Marca");
        modelo.addColumn("Preço R$");
        modelo.addColumn("Quantidade");
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
 
        // Ao clicar na tabela, preenche os campos automaticamente
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    nomeOriginalSelecionado = modelo.getValueAt(linha, 0).toString();
                    txtNome.setText(modelo.getValueAt(linha, 0).toString());
                    txtMarca.setText(modelo.getValueAt(linha, 1).toString());
                    txtPreco.setText(modelo.getValueAt(linha, 2).toString());
                    txtQuantidade.setText(modelo.getValueAt(linha, 3).toString());
                }
            }
        });
 
        // Eventos dos botões
        btCadastrar.addActionListener(new EventoCadastrar());
        btAlterar.addActionListener(new EventoAlterar());
        btExcluir.addActionListener(new EventoExcluir());
        btBuscar.addActionListener(new EventoBuscar());
        btListarTodos.addActionListener(new EventoListar());
 
        // Carrega a tabela ao abrir
        carregarTabela();
    }
 
    // Carrega todos os registros na tabela
    private void carregarTabela() {
        modelo.setRowCount(0);
        AcessoBancoDados banco = new AcessoBancoDados();
        ArrayList<Peca> lista = banco.listar();
        for (Peca p : lista) {
            modelo.addRow(new Object[]{p.getNome(), p.getMarca(), p.getPreco(), p.getQuantidade()});
        }
    }
 
    // Carrega resultado da busca por nome na tabela
    private void carregarBusca(String nome) {
        modelo.setRowCount(0);
        AcessoBancoDados banco = new AcessoBancoDados();
        ArrayList<Peca> lista = banco.buscarPorNome(nome);
        for (Peca p : lista) {
            modelo.addRow(new Object[]{p.getNome(), p.getMarca(), p.getPreco(), p.getQuantidade()});
        }
    }
 
    // Limpa os campos após uma ação
    private void limparCampos() {
        txtNome.setText("");
        txtMarca.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        txtBusca.setText("");
        nomeOriginalSelecionado = "";
    }
 
    // EVENTO - Cadastrar
    private class EventoCadastrar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nome = txtNome.getText();
            String marca = txtMarca.getText();
            String precoStr = txtPreco.getText();
            String qtdStr = txtQuantidade.getText();
 
            if (nome.isEmpty() || marca.isEmpty() || precoStr.isEmpty() || qtdStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }
 
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(qtdStr);
 
            Peca peca = new Peca(nome, marca, preco, quantidade);
            AcessoBancoDados banco = new AcessoBancoDados();
            banco.inserir(peca);
 
            JOptionPane.showMessageDialog(null, "Peça cadastrada com sucesso!");
            limparCampos();
            carregarTabela();
        }
    }
 
    // EVENTO - Alterar
    private class EventoAlterar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nomeOriginalSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecione uma peça na tabela!");
                return;
            }
 
            String nome = txtNome.getText();
            String marca = txtMarca.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());
 
            Peca peca = new Peca(nome, marca, preco, quantidade);
            AcessoBancoDados banco = new AcessoBancoDados();
            banco.alterar(nomeOriginalSelecionado, peca);
 
            JOptionPane.showMessageDialog(null, "Peça alterada com sucesso!");
            limparCampos();
            carregarTabela();
        }
    }
 
    // EVENTO - Excluir
    private class EventoExcluir implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nomeOriginalSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecione uma peça na tabela!");
                return;
            }
 
            int confirmar = JOptionPane.showConfirmDialog(null,
                "Deseja excluir a peça: " + nomeOriginalSelecionado + "?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
 
            if (confirmar == JOptionPane.YES_OPTION) {
                AcessoBancoDados banco = new AcessoBancoDados();
                banco.excluir(nomeOriginalSelecionado);
                JOptionPane.showMessageDialog(null, "Peça excluída com sucesso!");
                limparCampos();
                carregarTabela();
            }
        }
    }
 
    // EVENTO - Buscar por nome
    private class EventoBuscar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nomeBusca = txtBusca.getText();
            carregarBusca(nomeBusca);
        }
    }
 
    // EVENTO - Listar todos
    private class EventoListar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            carregarTabela();
        }
    }
}