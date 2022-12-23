
package aplicacao;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Conta {
    
    private String cpf;
    private String tipo;
    private String dono;
    private String senha;
    private double saldo;
    private String data;
    
    Operations opCliente = new Operations();
    static NumberFormat formatandoValores = new DecimalFormat("#,##0,00");
    
    public static String doubleToString(Double valor){
        return formatandoValores.format(valor);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    Conta() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpfDono) {
        this.cpf = cpfDono;
    } 
    
    public String getTipo() {
        return tipo;
    }

    
    //Define o tipo da conta como Corrente ou Poupança.
    public void setTipo(String tipo) {
        if(tipo.equals("CP)") || tipo.equals("cp") || tipo.equals("Poupança")){
        this.tipo = "Poupança";
        }else if(tipo.equals("CC") || tipo.equals("cc") || tipo.equals("Corrente")){
            this.tipo = "Corrente";
        }
    }
    
    public String getDono() {
        return dono;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    //Verificação de senha (Sobrescrita do método equals).
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Conta)) {
            return false;
        }
        final Conta other = (Conta) obj;
        return this.getSenha().equals(other.getSenha());
    }
    
    
    //Mostrar estado atual.
    @Override
    public String toString(){
        return "\nDono: " + this.getDono() +
                "\nCpf: " + this.getCpf() +
                "\nTipo: " + this.getTipo() +
                "\nSaldo: " + doubleToString(this.getSaldo());
    }
}