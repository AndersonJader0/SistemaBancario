package aplicacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Transferir extends JFrame implements ActionListener{
    
    private JTextField txtReais;
    
    Operations operations = new Operations();
    ValidadorCPF validador = new ValidadorCPF();
    
    Conta conta = new Conta();
    Conta contaOpValor = new Conta();
    
    
    public Transferir(Conta conta, Conta contaOpValor){
        this.conta = contaOpValor;
        this.contaOpValor = conta;
        
        JFrame jFrame = new JFrame("Inove.");
   
        setSize(500, 445);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel jLabel = new JLabel("Conta do destinário.");
        jLabel.setBounds(175, -5, 200, 100);
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
        
        JLabel title = new JLabel("Digite a quantidade que deseja transferir.");
        title.setBounds(110, 150, 300, 100);
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        add(title);
        
        JLabel valor = new JLabel("R$");
        valor.setBounds(100, 217, 200, 100);
        valor.setFont(new Font("Arial", Font.PLAIN, 15));

        add(valor);
        
        txtReais = new JTextField();
        
        txtReais.setBounds(125,250,250,35);
        txtReais.setFont(new Font("Arial", Font.ITALIC, 14));
        txtReais.setText("");
        add(txtReais);
        
        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(125,310,250,35);
        btnTransferir.setFont(new Font("Arial", Font.BOLD, 14));
        btnTransferir.setBackground(new Color(255, 255, 255, 255));
        btnTransferir.setForeground(new Color(60, 63, 60, 255));

        add(btnTransferir);
        btnTransferir.addActionListener(this);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(125,350,250,35);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(255, 255, 255, 255));
        btnCancelar.setForeground(new Color(60, 63, 60, 255));  

        add(btnCancelar);
        btnCancelar.addActionListener(this::cancelar);
        
        setVisible(true);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     Double transferencia = Double.valueOf(txtReais.getText());
     
     
     if(conta.getSaldo() >= transferencia){
     conta.setSaldo(conta.getSaldo() - transferencia);
     contaOpValor.setSaldo(contaOpValor.getSaldo() + transferencia);
     
     operations.updateSaldo(conta);
     operations.updateSaldo(contaOpValor);
     }else{
    JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
}
    }

    private void cancelar(ActionEvent event) {
     dispose();
     new ContaTela(conta);
    }
}
