
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


public class Depositar extends JFrame implements ActionListener{
    
    private JTextField txtReais;
    
    Conta conta = new Conta();
    Operations operations = new Operations();
    
    public Depositar(Conta contaOpSaldo){
    JFrame jframe= new JFrame();
        conta = contaOpSaldo;
    
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("Digite a quantidade que deseja depositar.");
        title.setBounds(110, -10, 400, 100);
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        add(title);
        
        JLabel valor = new JLabel("R$");
        valor.setBounds(100, 57, 200, 100);
        valor.setFont(new Font("Arial", Font.PLAIN, 15));

        add(valor);
        
        txtReais = new JTextField();
        
        txtReais.setBounds(125,90,250,35);
        txtReais.setFont(new Font("Arial", Font.ITALIC, 14));
        txtReais.setText("");
        add(txtReais);
        
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(125,170,250,35);
        btnDepositar.setFont(new Font("Arial", Font.BOLD, 14));
        btnDepositar.setBackground(new Color(255, 255, 255, 255));
        btnDepositar.setForeground(new Color(60, 63, 60, 255));

        add(btnDepositar);
        btnDepositar.addActionListener(this);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(125,210,250,35);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(255, 255, 255, 255));
        btnCancelar.setForeground(new Color(60, 63, 60, 255));  

        add(btnCancelar);
        btnCancelar.addActionListener(this::cancelar);
        
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Double saldoDeposito = Double.valueOf(txtReais.getText());
        operations.getConta(conta);
        conta.setSaldo(conta.getSaldo() + saldoDeposito);
        operations.updateSaldo(conta);
    }
    public void cancelar(ActionEvent event) {
        dispose();
        new ContaTela(conta);
    }
}
