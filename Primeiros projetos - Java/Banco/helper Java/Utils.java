package helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {
    
    //Formatador de datas
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //Formatador de valores
    static NumberFormat nf = new DecimalFormat("R$ #,##0.00",
                            new DecimalFormatSymbols(new Locale("pt", "BR")));

    //Recebe um dado do tipo DATE e transforma no formato BR que definimos na linha 15
    public static String dateParaString(Date data){
        //PQ Utils? - Nome da classe onde o formatador (sdf) esta, sendo ele um metodo estatico.
        return Utils.sdf.format(data);
    }

    //Recebe uma string e transforma em data
    public static Date stringParaData(String data){
        try{
            return Utils.sdf.parse(data);
        }catch(ParseException e){
            return null;
        }
        
        
    }

    //Recebe um valor Double e formata ele no formato da linha 18
    public static String doubleParaString(Double valor){
        return Utils.nf.format(valor);
    }

    public static Double stringParaDouble(String valor){
        try {
            return (Double)Utils.nf.parse(valor);
        } catch (ParseException e) {
            return null;
        }
    }

    //Função para pausar alguns segundos o programa
    public static void pausar(int segundos){
        try{
            TimeUnit.SECONDS.sleep(segundos);
        }catch(InterruptedException e){
            System.out.println("Erro ao pausar por " + segundos + " segundos.");
        }
    }

}
