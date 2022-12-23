package aplicacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame implements ActionListener{
    private final JFrame jFrame;
    private final JLabel jLabel;
    private final JButton btnAbrirConta;
    private final JButton btnAcessarConta;
    private final JButton btnSair;
    
    public Menu() {
       
        jFrame = new JFrame("Menu");
        
        setSize(700, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        
        jLabel = new JLabel("Sistema Bancário Inove");
        jLabel.setBounds(216, 0, 400, 100);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        add(jLabel);

        btnAbrirConta = new JButton("Abrir conta");
        btnAbrirConta.setBounds(170,90,350,45);
        btnAbrirConta.setFont(new Font("Arial", Font.BOLD, 20));
        btnAbrirConta.setForeground(new Color(255, 255, 255, 255));
        btnAbrirConta.setBackground(new Color(60, 63, 60, 255));

        add(btnAbrirConta);
        btnAbrirConta.addActionListener(this);
        
        btnAcessarConta = new JButton("Acessar conta");
        btnAcessarConta.setBounds(170,140,350,45);
        btnAcessarConta.setFont(new Font("Arial", Font.BOLD, 20));
        btnAcessarConta.setForeground(new Color(255, 255, 255, 255));
        btnAcessarConta.setBackground(new Color(60, 63, 60, 255));

        add(btnAcessarConta);
        btnAcessarConta.addActionListener(this::acessarConta);
        
        JButton btnAlterar = new JButton("Alterar conta");
        btnAlterar.setBounds(170,190,350,45);
        btnAlterar.setFont(new Font("Arial", Font.BOLD, 20));
        btnAlterar.setForeground(new Color(255, 255, 255, 255));
        btnAlterar.setBackground(new Color(60, 63, 60, 255));

        add(btnAlterar);
        btnAlterar.addActionListener(this::alterar);
        
        JButton btnDeletar = new JButton("Deletar conta");
        btnDeletar.setBounds(170,240,350,45);
        btnDeletar.setFont(new Font("Arial", Font.BOLD, 20));
        btnDeletar.setForeground(new Color(255, 255, 255, 255));
        btnDeletar.setBackground(new Color(60, 63, 60, 255));

        add(btnDeletar);
        btnDeletar.addActionListener(this::deletar);
        
        btnSair = new JButton("Sair");
        btnSair.setBounds(170,290,350,45);
        btnSair.setFont(new Font("Arial", Font.BOLD, 20));
        btnSair.setForeground(new Color(255, 255, 255, 255));
        btnSair.setBackground(new Color(60, 63, 60, 255));

        add(btnSair);
        btnSair.addActionListener(this::sair);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new Cadastro();
    }
    public void acessarConta(ActionEvent actionEvent){
        dispose();
        new Login();
    }
    public void alterar(ActionEvent actionEvent){
        dispose();
        new Alterar();
    }
    public void deletar(ActionEvent actionEvent){
        dispose();
        new Deletar();
    }
    public void sair(ActionEvent actionEvent){
        System.exit(0);
    }
}