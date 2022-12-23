
package aplicacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ContaTela extends JFrame implements ActionListener{
    
    Conta contaOpSaldo = new Conta();

    public ContaTela(Conta conta){
        contaOpSaldo = conta;
        
        JFrame jFrame = new JFrame();
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel jLabel = new JLabel("Dados Bancários.");
        jLabel.setBounds(185, -5, 200, 100);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 17));

        add(jLabel);
        
        JLabel dono = new JLabel("Dono:");
        dono.setBounds(90, 40, 200, 100);
        dono.setFont(new Font("Arial", Font.PLAIN, 15));

        add(dono);
        
        JLabel txtNome = new JLabel();
        
        txtNome.setBounds(140,40,200,100);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNome.setText(conta.getDono());
        add(txtNome);
        
        JLabel cpf = new JLabel("CPF:");
        cpf.setBounds(90, 70, 200, 100);
        cpf.setFont(new Font("Arial", Font.PLAIN, 15));

        add(cpf);
        
        JLabel txtCpf = new JLabel();
        
        txtCpf.setBounds(140,70,200,100);
        txtCpf.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCpf.setText(conta.getCpf());
        add(txtCpf);
        
        JLabel tipo = new JLabel();
        tipo.setBounds(90, 100, 200, 100);
        tipo.setFont(new Font("Arial", Font.PLAIN, 15));
        tipo.setText("Tipo:");

        add(tipo);
        
        JLabel txtTipo = new JLabel();
        
        txtTipo.setBounds(140,100,200,100);
        txtTipo.setFont(new Font("Arial", Font.PLAIN, 15));
        txtTipo.setText(conta.getTipo());
        add(txtTipo);
        
        JLabel data = new JLabel();
        data.setBounds(90, 130, 200, 100);
        data.setFont(new Font("Arial", Font.PLAIN, 15));
        data.setText("Data:");

        add(data);
        
        JLabel txtData = new JLabel();
        
        txtData.setBounds(140,130,400,100);
        txtData.setFont(new Font("Arial", Font.PLAIN, 15));
        txtData.setText(conta.getData());
        add(txtData);
        
        JLabel saldo = new JLabel();
        saldo.setBounds(90, 160, 200, 100);
        saldo.setFont(new Font("Arial", Font.PLAIN, 15));
        saldo.setText("Saldo: R$");
        

        add(saldo);
        
        JLabel txtSaldo = new JLabel();
        
        txtSaldo.setBounds(160,160,200,100);
        txtSaldo.setFont(new Font("Arial", Font.PLAIN, 15));
        txtSaldo.setText(Double.toString(conta.getSaldo()));
        add(txtSaldo);
        
        JLabel title = new JLabel("Opções bancárias.");
        title.setBounds(183, 200, 300, 100);
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        add(title);
        
        JButton btnSacar = new JButton("Sacar");
        btnSacar.setBounds(120,270,250,35);
        btnSacar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSacar.setBackground(new Color(255, 255, 255, 255));
        btnSacar.setForeground(new Color(60, 63, 60, 255));
        add(btnSacar);
        btnSacar.addActionListener(this::sacar);
        
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(120,310,250,35);
        btnDepositar.setFont(new Font("Arial", Font.BOLD, 14));
        btnDepositar.setBackground(new Color(255, 255, 255, 255));
        btnDepositar.setForeground(new Color(60, 63, 60, 255));
        add(btnDepositar);
        btnDepositar.addActionListener(this::depositar);
        
        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(120,350,250,35);
        btnTransferir.setFont(new Font("Arial", Font.BOLD, 14));
        btnTransferir.setBackground(new Color(255, 255, 255, 255));
        btnTransferir.setForeground(new Color(60, 63, 60, 255));
        add(btnTransferir);
        btnTransferir.addActionListener(this::transferir);
        
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(120,390,250,35);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(255, 255, 255, 255));
        btnVoltar.setForeground(new Color(60, 63, 60, 255));
        add(btnVoltar);
        btnVoltar.addActionListener(this::voltar);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
     private void sacar(ActionEvent event) {
        dispose();
        new Sacar(contaOpSaldo);
    }
    
    private void depositar(ActionEvent event) {
        dispose();
        new Depositar(contaOpSaldo);
    }

    private void transferir(ActionEvent event) {
        dispose();
        new Buscar(contaOpSaldo);
    }

    private void voltar(ActionEvent e) {
        dispose();
        new Menu();
    }
}