
package aplicacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Cadastro extends JFrame implements ActionListener{
    private JRadioButton radioCorrente, radioPoupanca;
    private RadioButtonHandler handler, handler2;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtSenha;
    
    ValidadorCPF validador = new ValidadorCPF();
    Conta conta = new Conta();
    Operations operations = new Operations();
    
    public Cadastro(){
    
    JFrame jFrame = new JFrame("Cadastro");
    handler = new RadioButtonHandler();
    
   
        setSize(500, 355);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel jLabel = new JLabel("Registre sua conta bancária.");
        jLabel.setBounds(150, -20, 200, 100);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        add(jLabel);
        
        JLabel nome = new JLabel("Nome");
        nome.setBounds(80, 27, 200, 100);
        nome.setFont(new Font("Arial", Font.PLAIN, 15));

        add(nome);
        
        txtNome = new JTextField();
        
        txtNome.setBounds(125,60,250,35);
        txtNome.setFont(new Font("Arial", Font.ITALIC, 14));
        txtNome.setText("");
        add(txtNome);
        
        JLabel cpf = new JLabel("CPF");
        cpf.setBounds(90, 67, 200, 100);
        cpf.setFont(new Font("Arial", Font.PLAIN, 15));

        add(cpf);
        
        txtCpf = new JTextField();
        
        txtCpf.setBounds(125,100,250,35);
        txtCpf.setFont(new Font("Arial", Font.ITALIC, 14));
        txtCpf.setText("");
        add(txtCpf);
        
        JLabel senha = new JLabel("Senha");
        senha.setBounds(75, 107, 200, 100);
        senha.setFont(new Font("Arial", Font.PLAIN, 15));

        add(senha);
        
        txtSenha = new JTextField();
        
        txtSenha.setBounds(125,140,250,35);
        txtSenha.setFont(new Font("Arial", Font.ITALIC, 14));
        txtSenha.setText("");
        add(txtSenha);
        
        
        
        radioCorrente = new JRadioButton("Corrente", false);
        radioCorrente.setBounds(125,180,100,30);
        add(radioCorrente);
        
        radioCorrente.addItemListener(handler);
        
        
        radioPoupanca = new JRadioButton("Poupança", false);
        radioPoupanca.setBounds(290,180,100,30); 
        add(radioPoupanca);
        radioPoupanca.addItemListener(handler2);
         
        ButtonGroup bg = new ButtonGroup();
	bg.add(radioCorrente);
	bg.add(radioPoupanca);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(125,230,250,35);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setBackground(new Color(255, 255, 255, 255));
        btnCadastrar.setForeground(new Color(60, 63, 60, 255));

        add(btnCadastrar);
        btnCadastrar.addActionListener(this);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(125,270,250,35);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(255, 255, 255, 255));
        btnVoltar.setForeground(new Color(60, 63, 60, 255));  

        add(btnVoltar);
        btnVoltar.addActionListener(this::voltar);
        
        
        setVisible(true);
        
        
        radioPoupanca.addItemListener(handler);
        
    }       

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(radioPoupanca.isSelected()){
            String cpf1 = txtCpf.getText();
            conta.setCpf(cpf1);
            validador.verificarCpf(conta.getCpf());
            
            if(validador.getExiste() == true){

            
            String tipo = "Poupança";
        conta.setTipo(tipo);
        String dono = txtNome.getText();
        String senha = txtSenha.getText();
        Date data = new Date();
        conta.setData(data.toString());
        conta.setDono(dono);
        conta.setCpf(cpf1);
        conta.setSenha(senha);
        
        validador.setCpf(cpf1);
        
       
        operations.verificarAdd(validador, conta);
            }else{
                JOptionPane.showMessageDialog(null, "Cpf inválido!");
            }
       
        }else if(radioCorrente.isSelected()){
            
            String cpf1 = txtCpf.getText();
            conta.setCpf(cpf1);
            validador.verificarCpf(conta.getCpf());
            
            if(validador.getExiste() == true){
            
            
                String tipo = "Corrente";
        conta.setTipo(tipo);
        String dono = txtNome.getText();
        String senha = txtSenha.getText();
        Date data = new Date();
        conta.setData(data.toString());
        conta.setDono(dono);
        conta.setSenha(senha);
        
        validador.setCpf(cpf1);
        
        operations.verificarAdd(validador, conta);
            }else{
                JOptionPane.showMessageDialog(null, "Cpf inválido!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Tipo da conta não selecionado!");
        }
        
    }
    
    private void radioPoupanca_Performed(java.awt.event.ActionEvent evt) {
        
    }
    
    private void voltar(ActionEvent actionEvent){
        dispose();
        new Menu();
    }
    
    private class RadioButtonHandler implements ItemListener{

        private RadioButtonHandler() {
        }
    public void itemStateChanged(ItemEvent event){
        if(radioPoupanca.isSelected()){
            JOptionPane.showMessageDialog(null, "Conta poupança selecionada.");
        }
        if(radioCorrente.isSelected()){
            JOptionPane.showMessageDialog(null, "Conta corrente selecionada.");
        }
    }
    }
}