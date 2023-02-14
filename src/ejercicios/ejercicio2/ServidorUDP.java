package ejercicios.ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class ServidorUDP {

    //Creo las variables.
    static DatagramSocket socket;
    static DatagramPacket packet;
    static BufferedWriter bufferedWriter;
    static String mensaje;
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        try {

            System.out.println("Creacion del socket del servidor");
            DatagramSocket socket = new DatagramSocket(41500);

            System.out.println("Creacion del array de bytes");

            while (true) {
                byte[] buffer = new byte[64];

                System.out.println("Creacion del datagrama del servidor");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                System.out.println("A la espera de recibir datagrama");
                socket.receive(packet);

                System.out.println("Leemos el mensaje");
                String mensaje = new String(packet.getData()).trim();

                switch (mensaje){
                    case "CREATE":
                        System.out.println("Introduzca el codigo y el nombre del alumno: ");
                        mensaje = sc.next();
                        byte[] mensajeAEnviar = mensaje.getBytes();
                        DatagramSocket socketEnviar = new DatagramSocket();

                        DatagramPacket packetMensaje = new DatagramPacket(mensajeAEnviar, mensajeAEnviar.length,packet.getAddress(), packet.getPort());

                        socketEnviar.send(packetMensaje);
                }



            }


        } catch (SocketException e) {
            System.out.println("Error en la creacion del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error en la recepcion del paquete");
            e.printStackTrace();
        }
    }
}
