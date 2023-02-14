package ejercicios.ejercicio1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        //Declaracion de variables
        int puerto = 41600;
        String ip = "192.168.0.177";
        Socket cliente;
        OutputStream os;
        OutputStreamWriter osw;
        BufferedWriter bw;
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        String operacion;

        //Declaramos el scanner para poder leer por consola
        Scanner scanner = new Scanner(System.in);

        try {
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            cliente = new Socket(InetAddress.getByName(ip), puerto);

            // 2 - Abrir flujos de lectura y escritura

            os = cliente.getOutputStream();
            osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            bw = new BufferedWriter(osw);

            is = cliente.getInputStream();
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);

            System.out.println("(ClienteTCP) Conexión establecida");

            // 3 - Intercambiar datos con el servidor
            System.out.println("Introduzca la oopracion que desea realizar (numero1;simboloOperacion;numero2)");
            operacion = scanner.next();

            bw.write(operacion);
            bw.newLine();
            bw.flush();

            operacion = br.readLine();
            System.out.println(operacion);
            System.out.println();

            // 4 - Cerrar flujos de lectura y escritura
            bw.close();
            br.close();
            osw.close();
            isr.close();
            is.close();
            os.close();

            // 5 - Cerrar la conexión
            System.out.println("(ClienteTCP) Cerrando conexiones...");
            cliente.close();
            System.out.println("(ClienteTCP) Conexiones cerradas...");

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}

