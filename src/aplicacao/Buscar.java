
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

/**
 *
 * @author ander
 */
public class Buscar extends JFrame implements ActionListener{
    
    private JTextField txtCpf;
    
    Conta conta = new Conta();
    Conta contaOpValor = new Conta();
    Operations operations = new Operations();
    ValidadorCPF validador = new ValidadorCPF();
    
    public Buscar(Conta contaOpSaldo){
        JFrame buscar = new JFrame();
        
        contaOpValor = contaOpSaldo;
    
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("Preencha os campos para acessar a conta do destinário.");
        title.setBounds(60, -20, 400, 100);
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        add(title);
        
        JLabel cpf = new JLabel("CPF");
        cpf.setBounds(90, 47, 200, 100);
        cpf.setFont(new Font("Arial", Font.PLAIN, 15));

        add(cpf);
        
        txtCpf = new JTextField();
        
        txtCpf.setBounds(125,80,250,35);
        txtCpf.setFont(new Font("Arial", Font.ITALIC, 14));
        txtCpf.setText("");
        add(txtCpf);
        
        
        JButton btnConfirmar = new JButton("Buscar");
        btnConfirmar.setBounds(125,130,250,35);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmar.setBackground(new Color(255, 255, 255, 255));
        btnConfirmar.setForeground(new Color(60, 63, 60, 255));

        add(btnConfirmar);
        btnConfirmar.addActionListener(this);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(125,180,250,35);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(255, 255, 255, 255));
        btnVoltar.setForeground(new Color(60, 63, 60, 255));  

        add(btnVoltar);
        btnVoltar.addActionListener(this::voltar);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         String cpf = txtCpf.getText();
        validador.verificarCpf(cpf);
        
        if(validador.getExiste() == true){
            conta.setCpf(cpf);
            operations.getContaDeposito(conta);
            
            if(conta.getDono() != null){
                dispose();
                new Transferir(conta, contaOpValor);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
        }
    }
    public void voltar(ActionEvent event) {
        dispose();
        new Menu();
    }
    
}