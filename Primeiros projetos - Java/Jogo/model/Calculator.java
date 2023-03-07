package model;

import java.util.Random;

public class Calculator{

    private int dificuldade;
    private int valor1;
    private int valor2;
    private int operacao;
    private int resultado;

    public Calculator(int dificuldade){

        Random aleatorio = new Random();
        //Gera um numero entre 0 e 1 - 0 SOMA - 1 SUB - 3 MULT
        this.operacao = aleatorio.nextInt(3);


        this.dificuldade = dificuldade;

        if (this.dificuldade == 1){
            //Dificuldade facil == 1
            this.valor1 = aleatorio.nextInt(10); // Numero de 0 a 9
            this.valor2 = aleatorio.nextInt(10); 
        }else if (this.dificuldade == 2){
            //Dificuldade média == 2
            this.valor1 = aleatorio.nextInt(100); // Numero de 0 a 99
            this.valor2 = aleatorio.nextInt(100);
        }else if (this.dificuldade == 3){
            //Dificuldade dificil == 3
            this.valor1 = aleatorio.nextInt(1000); // Numero de 0 a 999
            this.valor2 = aleatorio.nextInt(1000);
        }else if (this.dificuldade == 4){
            //Dificuldade insana == 4
            this.valor1 = aleatorio.nextInt(10000); // Numero de 0 a 9999
            this.valor2 = aleatorio.nextInt(10000);
        }else{
            //Modo ultra
            this.valor1 = aleatorio.nextInt(100000); // Numero de 0 a 99999
            this.valor2 = aleatorio.nextInt(100000);
        }

    }

    public boolean somar(int resposta){
        this.resultado = this.valor1 + this.valor2;
        boolean certo = false;

        if (resposta == this.getResultado()){
            System.out.println("Resposta correta!");
            certo = true;
        }else {
            System.out.println("Resposta errada!");
        }
        System.out.println(this.getValor1() + " + " + this.getValor2() + " = " + this.getResultado());
        return certo;
    }

    public boolean subtrair(int resposta){
        this.resultado = this.valor1 - this.valor2;
        boolean certo = false;

        if (resposta == this.getResultado()){
            System.out.println("Resposta correta!");
            certo = true;
        }else {
            System.out.println("Resposta errada!");
        }
        System.out.println(this.getValor1() + " - " + this.getValor2() + " = " + this.getResultado());
        return certo;
    }

    public boolean multiplicar(int resposta){
        this.resultado = this.valor1 * this.valor2;
        boolean certo = false;

        if (resposta == this.getResultado()){
            System.out.println("Resposta correta!");
            certo = true;
        }else {
            System.out.println("Resposta errada!");
        }
        System.out.println(this.getValor1() + " * " + this.getValor2() + " = " + this.getResultado());
        return certo;
    }

    public int getDificuldade() {
        return dificuldade;
    }
    
    public int getValor1() {
        return valor1;
    }
    
    public int getValor2() {
        return valor2;
    }
    
    public int getOperacao() {
        return operacao;
    }
   
    public int getResultado() {
        return resultado;
    }
    
    @Override
    public String toString() {
        String op = "Operação Invalida";
        if(this.getOperacao() == 0){
            op = "Somar";
        }else if (this.getOperacao() == 1){
            op = "Subtrair";
        }else if (this.getOperacao() == 2){
            op = "Multiplicar";
        }

        return "Valor 1: " + this.getValor1() +
               "\nValor 2: " + this.getValor2() +
               "\nDificuldade: " + this.getDificuldade() +
               "\nOperacao: " + op;
    }

    



}