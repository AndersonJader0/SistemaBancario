
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

public class Login extends JFrame implements ActionListener{
    
    private JTextField txtCpf;
    private JTextField txtSenha;
    
    Conta conta = new Conta();
    ValidadorCPF validador = new ValidadorCPF();
    Operations operations = new Operations();
    
    public Login(){
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("Preencha os campos para acessar sua conta.");
        title.setBounds(92, -20, 400, 100);
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
        
        JLabel senha = new JLabel("Senha");
        senha.setBounds(75, 87, 200, 100);
        senha.setFont(new Font("Arial", Font.PLAIN, 15));

        add(senha);
        
        txtSenha = new JTextField();
        
        txtSenha.setBounds(125,120,250,35);
        txtSenha.setFont(new Font("Arial", Font.ITALIC, 14));
        txtSenha.setText("");
        add(txtSenha);
        
        JButton btnAcessar = new JButton("Acessar");
        btnAcessar.setBounds(125,170,250,35);
        btnAcessar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAcessar.setBackground(new Color(255, 255, 255, 255));
        btnAcessar.setForeground(new Color(60, 63, 60, 255));

        add(btnAcessar);
        btnAcessar.addActionListener(this);
        
         JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(125,210,250,35);
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
        String senha = txtSenha.getText();
        validador.verificarCpf(cpf);
        
        if(validador.getExiste() == true){
            conta.setCpf(cpf);
            conta.setSenha(senha);
            operations.getConta(conta);
            
            if(conta.getDono() != null){
                dispose();
                new ContaTela(conta);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
        } 
    }

    private void voltar(ActionEvent e) {
        dispose();
        new Menu();
    }    
}
