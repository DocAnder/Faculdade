package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Calculator;

public class Game {

    //Atributos da classe Game - Por isso estão declarados fora da main.
    static BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
    static int pontos = 0;
    static Calculator calc;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        
        //Para acessar metodos estáticos, usamos NOME da classe + nome metodo.
        Game.jogar();
        
    }

    public static void jogar() throws NumberFormatException, IOException{
        System.out.println("Informe o nível de dificuldade desejado [1, 2, 3, 4]: ");
        int dificuldade = Integer.parseInt(Game.leitor.readLine());
        Game.calc = new Calculator(dificuldade);

        System.out.println("Informe o resultado para a seguinte operação: ");

        //SOMAR
        if(calc.getOperacao() == 0){
            System.out.println(calc.getValor1() + " + " + calc.getValor2());
            int resposta = Integer.parseInt(Game.leitor.readLine());

            if (calc.somar(resposta)) {
                Game.pontos += 1;
                System.out.println("Você tem " + Game.pontos + " ponto(s).");
            }
        }//SUBTRAIR
        else if(calc.getOperacao() == 1){
            System.out.println(calc.getValor1() + " - " + calc.getValor2());
            int resposta = Integer.parseInt(Game.leitor.readLine());

            if (calc.subtrair(resposta)) {
                Game.pontos += 1;
                System.out.println("Você tem " + Game.pontos + " ponto(s).");
            }
        }//MULTIPLICAR
        else if(calc.getOperacao() == 2){
            System.out.println(calc.getValor1() + " * " + calc.getValor2());
            int resposta = Integer.parseInt(Game.leitor.readLine());

            if (calc.multiplicar(resposta)) {
                Game.pontos += 1;
                System.out.println("Você tem " + Game.pontos + " ponto(s).");
            }
        }

        System.out.println("Deseja continuar? [1 - Sim, 0 - Não]");
        int resp = Integer.parseInt(Game.leitor.readLine());

        if(resp == 1){
            Game.jogar();
        }else{
            System.out.println("Até a proxima!");
            System.exit(0);
        }

    }


}
