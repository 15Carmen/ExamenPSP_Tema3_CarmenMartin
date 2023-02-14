package ejercicios.ejercicio1;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Gestor extends Thread{

    //Decalramos variables globales
    private Socket socket;
    private OutputStream os;
    private InputStream is;

    //Creamos un constructor
    public Gestor (Socket socket){this.socket = socket;}

    @Override
    public void run() {
        realizarProceso();
    }

    public void realizarProceso(){

        //Declaracion de variables
        OutputStreamWriter osw;
        BufferedWriter bw;
        InputStreamReader isr;
        BufferedReader br;
        String operacion;

        try {
            os = this.socket.getOutputStream();
            osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            bw = new BufferedWriter(osw);
            is = this.socket.getInputStream();

            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);

            //Leo la operacion introducida por el cliente
            operacion = br.readLine();

            //Hacemos los calculos y le mandamos el resultado de los mismos al cliente
            bw.write(hacerCalculos(operacion));
            bw.newLine();
            bw.flush();

            //Cerramos flujo de datos
            bw.close();
            br.close();
            osw.close();
            isr.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.err.println("Error al conectarse con el cliente");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que va a hacer una opracion con los numeros introducidos por el usuario según el símbolo que lea
     * @param operacion
     * @return el resultado de la operacion introducida por el cliente. Si el simbolo que ha introducido no es uno de
     * los simbolos de operaciones (+,-,*,/) se devuelve 0
     */
    public String hacerCalculos(String operacion){

        //Declaramos las variables
        String resultado = "0";
        int num1, num2;

        if (operacion.contains(";")){
            String simboloOperacion = operacion.split(";")[1];
            num1 = Integer.parseInt(operacion.split(";")[0]);
            num2 = Integer.parseInt(operacion.split(";")[2]);

            switch (simboloOperacion){
                case "+":
                    resultado = "El resultado de la suma es: ";
                    resultado += String.valueOf(num1+num2);
                    break;
                case "-":
                    resultado = "El resultado de la resta es: ";
                    resultado = String.valueOf(num1-num2);
                    break;
                case "*":
                    resultado = "El resultado de la multiplicacion es: ";
                    resultado = String.valueOf(num1*num2);
                    break;
                case "/":
                    resultado = "El resultado de la division es: ";
                    resultado = String.valueOf(num1/num2);
                    break;
                default:
                    System.out.println(resultado);
            }
        }

        return resultado;

    }
}
