package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.rmi.CORBA.Util;

import model.Produto;
import helper.Utils;

public class Mercado {
    
    private static BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Produto> produtos;

    //Com MAP vamos controlar o produto + a quantidade deles 
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) throws IOException {
        produtos = new ArrayList<Produto>();
        carrinho = new HashMap<Produto, Integer>();
        Mercado.menu();

    }

    private static void menu() throws IOException{
        System.out.println("==========================================");
        System.out.println("============== Bem-Vindo(a) ==============");
        System.out.println("==========================================");
        System.out.println("Selecione uma opção abaixo: ");
        System.out.println("1 - Cadastrar produtos");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Comprar produto");
        System.out.println("4 - Visualizar carrinho");
        System.out.println("5 - Sair do sistema");
        int opcao = 0;
        try {
            opcao = Integer.parseInt(Mercado.leitor.readLine());
        } catch (Exception e) {
            Mercado.menu();
        }

        switch (opcao) {
            case 1:
                Mercado.cadastrarProduto();
                break;
            case 2:
                Mercado.listarProduto();
                break;
            case 3:
                Mercado.comprarProduto();
                break;
            case 4:
                Mercado.visualizarCarrinho();
                break;
            case 5:
                System.out.println("Volte sempre!");
                Utils.pausar(2);
                System.exit(0);
            default:
                System.out.println("Opçao invalida!");
                Utils.pausar(2);
                Mercado.menu();
                break;
        }

    }

    private static void cadastrarProduto() throws IOException{
        System.out.println("Cadastro de Produto");
        System.out.println("===================");
        System.out.println("Informe o nome do produto: ");
        String nome = Mercado.leitor.readLine();
        System.out.println("Informe o preço do produto: ");
        Double preco = Double.parseDouble(Mercado.leitor.readLine());

        Produto produto = new Produto(nome, preco);
        Mercado.produtos.add(produto);

        System.out.println("O produto " + produto.getNome() + " foi cadastrado com sucesso!");
        Utils.pausar(2);
        Mercado.menu();

    }

    private static void listarProduto() throws IOException{
        if(Mercado.produtos.size() > 0){
            for (Produto produto : Mercado.produtos) {
                System.out.println(produto);
                System.out.println();
            }
        }else{
            System.out.println("Ainda não foram cadastrados produtos.");
        }
        Utils.pausar(2);
        Mercado.menu();
    }

    private static void comprarProduto() throws IOException{
        if (Mercado.carrinho.size() > 0) {
            System.out.println("Informe o codigo do produto que deseja comprar: ");
            System.out.println();

            System.out.println("========== Produtos disponíveis ==========");
            for (Produto produto : Mercado.produtos) {
                System.out.println(produto);
            }
            int codigo = Integer.parseInt(Mercado.leitor.readLine());
            boolean tem = false;
            for (Produto produto : Mercado.produtos) {
                if(produto.getCodigo() == codigo){
                    int quant = 0;
                    try{
                        quant = Mercado.carrinho.get(produto);
                        Mercado.carrinho.put(produto, quant + 1);
                    }catch(NullPointerException e){
                        Mercado.carrinho.put(produto, 1);
                    }
                    System.out.println("O produto " + produto.getNome() + " foi adicionado ao carinho.");
                    tem = true;
                }
                if (tem){
                    System.out.println("Deseja adicionar mais produtos?");
                    System.out.println("Informe 1 para sim e 0 para não.");
                    int op = Integer.parseInt(Mercado.leitor.readLine());

                    if (op == 1){
                        Mercado.comprarProduto();
                    }else{
                        System.out.println("Por favor aguarde enquanto fechamos seu pedido.");
                        Utils.pausar(2);
                        Mercado.fecharPedido();
                    }
                }else{
                    System.out.println("Não foi encontrado o produto com o codigo informado.");
                    Utils.pausar(2);
                    Mercado.menu();
                }
            }

        }else{
            System.out.println("Ainda não existem produtos cadastrados.");
            Utils.pausar(2);
            Mercado.menu();
        }
    }

    private static void visualizarCarrinho() throws IOException{
        if (Mercado.carrinho.size() > 0) {
            System.out.println("Produtos no carrinho...");
            for (Produto produto : Mercado.carrinho.keySet()) {
                System.out.println("Produto: " + produto + "\nQuantidade: " + Mercado.carrinho.get(produto));
            }
        }else{
            System.out.println("Ainda não existem produtos no carrinho.");
        }
        Utils.pausar(2);
        Mercado.menu();
    }

    private static void fecharPedido() throws IOException{
        Double valorTotal = 0.0;
        System.out.println("Produtos do Carrinho.");
        System.out.println("=======================");
        for (Produto produto : Mercado.carrinho.keySet()) {
            int quant = Mercado.carrinho.get(produto);
            valorTotal += produto.getPreco() * quant;
            System.out.println(produto);
            System.out.println("Quantidade: " + quant);
            System.out.println("---------------------");
        }
        System.out.println("O valor total da compra foi: " + Utils.doubleParaString(valorTotal));
        Mercado.carrinho.clear();
        System.out.println("Obrigado pela preferência.");
        Utils.pausar(2);
        Mercado.menu();
    }


}
