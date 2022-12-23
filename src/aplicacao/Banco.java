    
package aplicacao;

import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Banco {

    public static void main(String[] args) {
        
        aplicacao();
        
    }
       public static void aplicacao(){
                      Operations operations = new Operations();
                      ValidadorCPF validador = new ValidadorCPF();
                      Conta conta = new Conta();

           int opcao;
        do { 
            opcao = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                 Digite 1, caso você queira queira abrir uma conta.
                                                                 
                                                                 Digite 2, Alterar algum dado de alguma conta.
                                                                 
                                                                 Digite 3, caso você queira fechar a sua conta existente.
                                                                 
                                                                 Digite 4, caso você queira listar todas as contas.
                                                                 
                                                                 Digite 5, caso você  queira buscar uma conta específica.
                                                                 
                                                                 Digite 0, caso você queira sair do programa!"""));
            if (opcao == 1) {
                
                String cpfDono = JOptionPane.showInputDialog("Digite o cpf: ");
                conta.setCpf(cpfDono);
                validador.verificarCpf(conta.getCpf());
                
                if (validador.getExiste() == true){
                
                String dono = JOptionPane.showInputDialog("Digite o seu nome: ");
                
                String tipo = JOptionPane.showInputDialog("Digite o tipo da conta('CC' para conta corrente e"
                        +  " 'CP' para conta poupança):");
                
                String senha = JOptionPane.showInputDialog("Digite a senha: ");
                
                Date data = new Date();
                
                
                conta.setDono(dono);
                conta.setTipo(tipo);
                conta.setSenha(senha);
                conta.setData(data.toString());
                
               // operations.add(conta, conta1);
                
                }else{
                    JOptionPane.showMessageDialog(null, "Cpf inválido!");
                }    
                
            } else if(opcao == 2){
                
                String cpfProcura = JOptionPane.showInputDialog("Digite o Cpf da conta: ");
                conta.setCpf(cpfProcura);
                String senhaProcura = JOptionPane.showInputDialog("Digite a senha: ");
                conta.setSenha(senhaProcura);
                
                validador.verificarCpf(conta.getCpf());
                //Busca a conta no banco de dados.
                operations.getConta(conta);
                //Printa a conta buscada.
                System.out.println(conta);
                
                if(validador.getExiste() == true){
                
                String novoDono = JOptionPane.showInputDialog("Digite o novo dono: ");
                String novoTipo = JOptionPane.showInputDialog("digite o novo tipo da conta 'cc' ou 'cp':");
                String novaSenha = JOptionPane.showInputDialog("Digite a nova senha: ");
                
                conta.setDono(novoDono);
                conta.setTipo(novoTipo);
                conta.setSenha(novaSenha);
                
                //Atualiza as informações no banco de dados.
                operations.update(conta);
                }else{
                    JOptionPane.showMessageDialog(null, "Cpf inválido!");
                }
                
                
            }else if(opcao == 3){
                String cpf = JOptionPane.showInputDialog("Digite o cpf da conta: ");

                conta.setCpf(cpf);
                
                
                validador.verificarCpf(cpf);
                if(validador.getExiste() == true){
                    String senha = JOptionPane.showInputDialog("Digite a senha: ");
                    conta.setSenha(senha);
                operations.verificarConta(conta);
                }else{
                    JOptionPane.showMessageDialog(null, "Cpf não cadastrado!");
                }
            }
            
            else if(opcao == 4){
                operations.getContas();
                for (Iterator<Conta> it = operations.getContas().iterator(); it.hasNext();) {
                    conta = it.next();
                    System.out.println("Dono: "  + conta.getDono());
                    System.out.println("Cpf: "  + conta.getCpf());
                    System.out.println("Tipo: "  + conta.getTipo());
                    System.out.println("Data: "  + conta.getData());
                }
            }else if(opcao == 5){
                String cpf = JOptionPane.showInputDialog("Digite o cpf da conta: ");
                String senha = JOptionPane.showInputDialog("Digite a senha: ");
                conta.setCpf(cpf);
                conta.setSenha(senha);
                validador.verificarCpf(conta.getCpf());
                
                operations.getConta(conta);
                if(validador.getExiste() == true){
                
                
                int option = Integer.parseInt(JOptionPane.showInputDialog("Digite a operação que deseja realizar"+
                        "\n1 - Depósito" +
                        "\n2 - Saque" +
                        "\n3 - Transferência" +
                        "\n4 - Ver conta" +
                        "\n5 - Voltar"));
                
                if(option == 1){
                double saldoDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade que deseja depositar"));
                operations.getConta(conta);
                conta.setSaldo(conta.getSaldo() + saldoDeposito);
                operations.updateSaldo(conta);
                }else if(option == 2){
                double saldoSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade que deseja sacar"));
                operations.getConta(conta);
                
                if(conta.getSaldo() >= saldoSaque){
                conta.setSaldo(conta.getSaldo() - saldoSaque);
                operations.updateSaldo(conta);
                }else{
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
                }

            }else if(option == 3){
                String cpfDeposito = JOptionPane.showInputDialog("Digite o cpf da conta que deseja depositar: ");
                
                Conta contaBusca = new Conta();
                
                contaBusca.setCpf(cpfDeposito);
                
                //aqui ele encontra a conta e pega o saldo existente na conta.
                operations.getContaDeposito(contaBusca);
                System.out.println(contaBusca);
                
                Double transferencia = Double.valueOf(JOptionPane.showInputDialog("Digite a quantidade que deseja depositar: "));
               
                contaBusca.setSaldo(contaBusca.getSaldo() + transferencia);
                
                operations.getConta(conta);
                
                conta.setSaldo(conta.getSaldo() - transferencia);
                
                operations.updateSaldo(conta);
                
                operations.updateSaldo(contaBusca);
                
                
               JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                
            }else if (option == 4){
                operations.getConta(conta);
                    System.out.println(conta);
            }
                }
            }
        }while (opcao != 0);  
    }
}