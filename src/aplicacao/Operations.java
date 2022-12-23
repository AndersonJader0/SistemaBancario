package aplicacao;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Operations {
    Connection con;
    PreparedStatement stmt;
    ResultSet rset;
    
    ValidadorCPF verificador = new ValidadorCPF();
    
    //CRUD
    //Cria uma nova conta (verifica o tipo da conta também).
    public void add(Conta conta) {
        con = ConexaoMySQL.getConnection();
        stmt = null;
        String sql = "insert into tabelacontas(dono,cpf,tipoConta,senha,data) values (?, ?, ?, ?, ?)";
        try{
            
                if ("Corrente".equals(conta.getTipo()) || "Poupança".equals(conta.getTipo())){
            stmt =(PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, conta.getDono());
            stmt.setString(2, conta.getCpf());
            stmt.setString(3, conta.getTipo());
            stmt.setString(4, conta.getSenha());
            stmt.setString(5, conta.getData());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Conta registrada com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null, "Tipo da conta desconhecida!");
                }
            
        } catch (SQLException e){
            JOptionPane.showConfirmDialog(null, "Erro ao salvar os dados: " + e.toString());
        }finally{
            try{
                if(stmt != null){
                stmt.close();
                }
                if(stmt != null){
                con.close();
                }
            }catch (SQLException e){
           }
    }
    }
    
    //Verifica se uma conta não existe para registra-la.
    public List<ValidadorCPF> verificarAdd(ValidadorCPF validador, Conta conta){
        String sql = "select * from tabelacontas ";
        List<ValidadorCPF> contasCriadas = new ArrayList<ValidadorCPF>();
        con = null;
        stmt = null;
        rset = null;
        
         try{
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sql);
            rset = stmt.executeQuery();
            
            while(rset.next()) {
                ValidadorCPF cpfsExistentes = new ValidadorCPF();
                cpfsExistentes.setCpf(rset.getString("cpf"));
                contasCriadas.add(cpfsExistentes);
            }
            
            stmt.execute();
            stmt.close();
           
            if(contasCriadas.contains(validador)){           
              
              JOptionPane.showMessageDialog(null, "CPF já em uso!");
                
            }else{
             add(conta);
            }
}catch(SQLException e){
        }finally{
            try{
        
            if(rset != null){
                rset.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(con != null){
                con.close();
            }
        
         }catch(Exception e){
             e.printStackTrace();
         }
        }
        return contasCriadas;
    }
    
    //Retorna uma lista de todas as contas criadas usando um arraylist.
    public List<Conta> getContas(){
        String sql = "select * from tabelacontas";
        List<Conta> contaCliente = new ArrayList<Conta>();
        con = null;
        stmt = null;
        rset = null;
        
        try{
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sql);
            rset = stmt.executeQuery();
            while(rset.next()) {
                Conta conta = new Conta();

                conta.setDono(rset.getString("dono"));
                conta.setCpf(rset.getString("cpf"));
                conta.setTipo(rset.getString("tipoconta"));
                conta.setSenha(rset.getString("senha"));
                conta.setData(rset.getString("data"));
                contaCliente.add(conta);
            }
        }catch(SQLException e){
            System.out.println("Ocorreu alguma exception do banco de dados");
        }finally{
            try{
        
            if(rset != null){
                rset.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(con != null){
                con.close();
            }
        
         }catch(Exception e){
             e.printStackTrace();
         }
        }
        return contaCliente;
    } 
    
    //Modifica dados da conta (Verifica o tipo da conta).
    public void update(Conta conta){
        
        try{
            
            String sql = "update tabelacontas set dono = ?, tipoconta = ?, senha = ?" + 
                "where cpf = ?";
            
            con = ConexaoMySQL.getConnection();
            
            stmt = (PreparedStatement) con.prepareStatement(sql);
            
                if("Corrente".equals(conta.getTipo()) || "Poupança".equals(conta.getTipo())){
            stmt.setString(1, conta.getDono());
            stmt.setString(2, conta.getTipo());
            stmt.setString(3, conta.getSenha());
            stmt.setString(4, conta.getCpf());
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Conta alterada com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null,"Conta não alterada: tipo da conta desconhecida!");
                }
            
        }catch(SQLException e){
            System.err.println("Não foi possível atualizar os dados da pessoa no banco!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally {
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(con!=null){
                    con.close();
                }
               
                }catch(SQLException e){
            }
        }
        
    }
    
    //Ambas estão ligadas
    //Simplesmente deleta a conta.
    public void delete(Conta conta){ 
        if(conta.getSaldo() >= 0){
            
        try{
            String sql = "DELETE FROM tabelacontas WHERE cpf = ?";
        
        con = ConexaoMySQL.getConnection();
        
        stmt = (PreparedStatement) con.prepareStatement(sql);
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, conta.getCpf());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!.");
        }catch(SQLException e){
        }finally{
            try {
                if(stmt != null){
                    stmt.close();
                }
                if(con != null){
                    con.close();
                }
            
                
        }catch(SQLException e){
            }
        }
        }else{
            JOptionPane.showMessageDialog(null, "Ainda há saldo na conta! Não foi possível excluir.");
    
    }
    }
    //Verifica se existe conta para apagar.
    public List<Conta> verificarConta(Conta conta){
        String sql = "select * from tabelacontas ";
        List<Conta> contasCriadas = new ArrayList<Conta>();
        con = null;
        stmt = null;
        rset = null;
        
         try{
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sql);
            rset = stmt.executeQuery();
            
            while(rset.next()) {
                Conta conta2 = new Conta();
                conta2.setCpf(rset.getString("cpf"));
                conta2.setSenha(rset.getString("senha"));
                contasCriadas.add(conta2);
            }
            
            stmt.execute();
            stmt.close();
           
            if(contasCriadas.contains(conta)){           
              
              delete(conta);
                
            }else{
             JOptionPane.showMessageDialog(null, "Conta não existe!.");
            }
}catch(SQLException e){
        }finally{
            try{
        
            if(rset != null){
                rset.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(con != null){
                con.close();
            }
        
         }catch(Exception e){
             e.printStackTrace();
         }
        }
        return contasCriadas;
    }
    
    
    //Retorna uma conta específica do banco de dados (caso tenha) e preenche o objeto para manipulação.
    public List<Conta> getConta(Conta conta){
        String sql = "select * from tabelacontas";
        List<Conta> buscarContaUnica = new ArrayList<Conta>();
        con = null;
        stmt = null;
        rset = null;
        
         try{
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sql);
            rset = stmt.executeQuery();
            
            while(rset.next()) {
                Conta conta2 = new Conta();
                conta2.setCpf(rset.getString("cpf"));
                conta2.setSenha(rset.getString("senha"));
                buscarContaUnica.add(conta2);
            }
           
            if(buscarContaUnica.contains(conta)){
            
            try{
            con = null;
            stmt = null;
            rset = null;
            String sqll = "select * from tabelacontas where cpf = ?";
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sqll);
            stmt.setString(1, conta.getCpf());
            rset = stmt.executeQuery();
            
            rset.next();
            conta.setDono(rset.getString("dono"));
            conta.setCpf(rset.getString("cpf"));
            conta.setTipo(rset.getString("tipoconta"));
            conta.setSenha(rset.getString("senha"));
            conta.setSaldo(rset.getDouble("saldo"));
            conta.setData(rset.getString("data"));
            
            stmt.execute();
            stmt.close();
                
            
            }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi possível acessar a conta no banco de dados");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
                
            }else{
             JOptionPane.showMessageDialog(null, "Conta não encontrada!.");
            }

         }catch(SQLException e){
        }finally{
            try{
        
            if(rset != null){
                rset.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(con != null){
                con.close();
            }
        
         }catch(Exception e){
             e.printStackTrace();
         }
        }
        return buscarContaUnica;
    }
    
    //Atualiza o saldo bancário no banco de dados.
    public void updateSaldo(Conta conta){
        
    
        try{
            String sql = "update tabelacontas set saldo = ?" + 
                "where cpf = ?";
            con = ConexaoMySQL.getConnection();
            
            stmt = (PreparedStatement) con.prepareStatement(sql);
            verificador.verificarCpf(conta.getCpf());
            
            stmt.setDouble(1, conta.getSaldo());
            stmt.setString(2, conta.getCpf());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Saldo atualizado com sucesso!");
        }catch(HeadlessException | SQLException e){
            System.err.println("Não foi possível realizar o depósito!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }finally {
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(con!=null){
                    con.close();
                }
               
                }catch(SQLException e){
            }
        }
 }
    
    //Para buscar a conta específica e o saldo de sua conta.
    public void getContaDeposito(Conta contaBusca){
        String sql = "select * from tabelacontas";
        List<Conta> buscarContaUnica = new ArrayList<Conta>();
        con = null;
        stmt = null;
        rset = null;
        
        Conta conta2 = new Conta();
        
         try{
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sql);
            rset = stmt.executeQuery();
            
            while(rset.next()) {
                
                conta2.setCpf(rset.getString("cpf"));
                buscarContaUnica.add(conta2);
            }
            
            try{
            con = null;
            stmt = null;
            rset = null;
            String sqll = "select * from tabelacontas where cpf = ?";
            con = ConexaoMySQL.getConnection();
            stmt = (PreparedStatement) con.prepareStatement(sqll);
            stmt.setString(1, contaBusca.getCpf());
            rset = stmt.executeQuery();
           
            
            rset.next();
            contaBusca.setDono(rset.getString("dono"));
            contaBusca.setCpf(rset.getString("cpf"));
            contaBusca.setTipo(rset.getString("tipoconta"));
            contaBusca.setTipo(rset.getString("senha"));
            contaBusca.setSaldo(rset.getDouble("saldo"));
            
         
            }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi possível acessar a conta no banco de dados");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}

         }catch(SQLException e){
        }finally{
            try{
        
            if(rset != null){
                rset.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(con != null){
                con.close();
            }
        
         }catch(Exception e){
             e.printStackTrace();
         }
        }
    }
    
    //Pega o cpf de um objeto e verifica se existe tal cpf dps verifica a senha desse objeto pra vê se
    //bate com a senha desse cpf.
    //preenche o objeto também com os dados.
    //vai ser usado pra login, alterar e deletar.
    
}