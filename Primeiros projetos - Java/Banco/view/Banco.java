package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import helper.Utils;
import model.Cliente;
import model.Conta;

public class Banco {
    
    static String nome = "Banco Bolado";
    static BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Conta> contas;

    public static void main(String[] args) throws IOException {
        
        Banco.contas = new ArrayList<Conta>();
        Banco.menu();

    }

    public static void menu() throws IOException{
        int opcao = 0;
        System.out.println("================================");
        System.out.println("============= ATM ==============");
        System.out.println("========" + Banco.nome + "========");
        System.out.println("Selecione uma opção no menu: ");
        System.out.println("1 - Criar conta.");
        System.out.println("2 - Efetuar saque.");
        System.out.println("3 - Efetuar deposito.");
        System.out.println("4 - Efetuar transferência.");
        System.out.println("5 - Listar contas.");
        System.out.println("6 - Sair.");

        try {
            opcao = Integer.parseInt(Banco.leitor.readLine());
        } catch (Exception e) {
            System.out.println("Informe uma opção válida.");
            Utils.pausar(1);
            Banco.menu();
        }

        switch (opcao) {
            case 1:
                Banco.criarConta();
                break;
            case 2:
                Banco.efetuarSaque();
                break;
            case 3:
                Banco.efetuarDeposito();
                break;
            case 4:
                Banco.efetuarTransferencia();
                break;
            case 5:
                Banco.listarContas();
                break;
            case 6:
                System.out.println("Até a próxima!");
                Utils.pausar(2);
                System.exit(0);        
            default:
                System.out.println("Opção invalida.");
                Utils.pausar(2);
                Banco.menu();
                break;
        }

    }

    public static void criarConta() throws IOException{
        System.out.println("Informe os dados do cliente: ");
        System.out.println("Nome: ");
        String nome = Banco.leitor.readLine();
        System.out.println("Email: ");
        String email = Banco.leitor.readLine();
        System.out.println("CPF: ");
        String cpf = Banco.leitor.readLine();
        System.out.println("Data nascimento: ");
        String data = Banco.leitor.readLine();
        Cliente cliente = new Cliente(nome, email, cpf, Utils.stringParaData(data));
        Conta conta = new Conta(cliente);
        Banco.contas.add(conta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("Dados da conta criada: ");
        System.out.println(conta);
        Utils.pausar(4);
        Banco.menu();
    }

    public static void efetuarSaque() throws NumberFormatException, IOException{
        System.out.println("Informe o numero da conta: ");
        int numero = Integer.parseInt(Banco.leitor.readLine());
        
        Conta conta = Banco.buscarContaPorNumero(numero);

        if (conta != null){
            System.out.println("Informe o valor para saque: ");
            Double valor = Double.parseDouble(Banco.leitor.readLine());
            conta.sacar(valor);
        }else {
            System.out.println("Não foi encontrada conta numero " + numero);
        }
        Utils.pausar(3);
        Banco.menu();
    }

    public static void efetuarDeposito() throws NumberFormatException, IOException{
        System.out.println("Informe o numeor da conta para deposito: ");
        int numero = Integer.parseInt(Banco.leitor.readLine());
        Conta conta = Banco.buscarContaPorNumero(numero);

        if (conta != null){
            System.out.println("Informe o valor para deposito: ");
            Double valor = Double.parseDouble(Banco.leitor.readLine());
            conta.depositar(valor);
        }else {
            System.out.println("Não foi encontrada conta numero " + numero);
        }
        Utils.pausar(3);
        Banco.menu();

    }
    public static void efetuarTransferencia() throws NumberFormatException, IOException{
        System.out.println("Informe o numero da sua conta: ");
        int numero_saida = Integer.parseInt(Banco.leitor.readLine());
        Conta conta_saida = Banco.buscarContaPorNumero(numero_saida);
        if (conta_saida != null) {  
            System.out.println("Informe o numero da conta destino: ");
            int numero_entrada = Integer.parseInt(Banco.leitor.readLine());
            Conta conta_entrada = Banco.buscarContaPorNumero(numero_entrada);
            if (conta_entrada != null) {
                System.out.println("Informe o valor da transferência: ");
                Double valor = Double.parseDouble(Banco.leitor.readLine());
                conta_saida.transferir(conta_entrada, valor);
            }else{
                System.out.println("A conta destino numero " + numero_entrada + " não foi encontrada.");
            }

        }else{
            System.out.println("Não foi encontrada a conta " + numero_saida);
        }
        Utils.pausar(3);
        Banco.menu();


    }
    public static void listarContas() throws IOException{
        
        if (Banco.contas.size() > 0) {
            System.out.println("Listagem de contas.");
            System.out.println();
            for (Conta conta : Banco.contas) {
                System.out.println(conta);
                System.out.println();
            }
        }else{
            System.out.println("Não existem contas cadastradas ainda!");
        }
        Utils.pausar(3);
        Banco.menu();
    }

    private static Conta buscarContaPorNumero(int numero){
        Conta c = null;
        if (Banco.contas.size() > 0) {
           for (Conta conta : contas) {
                if(conta.getNumero() == numero){
                    c = conta;
                }
           } 
        }

        return c;
    }

}
