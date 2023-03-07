package view;

import helper.Utils;
import model.Cliente;
import model.Conta;

public class Teste {
    
    public static void main(String[] args) {
        
        Cliente antonio = new Cliente(
        "Antonio Carlos",
        "toninho@gmail.com",
        "44699565972",
        Utils.stringParaData("17/05/1981"));

        Cliente raianny = new Cliente(
        "Raiany Proença", 
        "raianny@gmail.com",
        "38825805934",
        Utils.stringParaData("09/04/1996"));

        //System.out.println(antonio);
        //System.out.println();
        //System.out.println(raianny);

        Conta conta1 = new Conta(raianny);
        Conta conta2 = new Conta(antonio);

        //Depositando valores
        conta1.depositar(500.00);
        conta2.depositar(500.00);
        System.out.println(conta1);
        System.out.println();
        System.out.println(conta2);
        

        //Sacando valores dentro do saldo da conta
        conta1.sacar(300.00);

        //Sacando valor maior que o saldo (usa limite)
        conta2.setLimite(400.00);
        conta2.sacar(600.00);

        //Transferindo entre contas
        conta1.transferir(conta2, 100.00);

    }
    

}
