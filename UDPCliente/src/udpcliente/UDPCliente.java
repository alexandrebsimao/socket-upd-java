/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpcliente;

import java.io.*;
import java.net.*;

/**
 *
 * @author AlexandreAugusto
 */
public class UDPCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String argv[]) throws Exception{
        while(true){
            //cria o stream do teclado
            BufferedReader cadeiaUsuario = new BufferedReader(new InputStreamReader(System.in));

            //Cria o objeto do socket cliente
            DatagramSocket clienteSocket = new DatagramSocket();

            //obtem o endereço ip do servidor
            //alternativa de uso usando direto o endereço ip:
            //InetAddress enderecoIP = "127.0.0.1";
            InetAddress enderecoIP = InetAddress.getByName("localhost");
            byte[] enviaDados =     new byte[1024];
            byte[] recebeDados =    new byte[1024];

            //lê uma linha do teclado
            String sentenca = cadeiaUsuario.readLine();
            enviaDados = sentenca.getBytes();

            //Cria o objeto do pacote com os dados, endereço e porta do servidor
            DatagramPacket enviaPacote = new DatagramPacket(enviaDados, enviaDados.length, enderecoIP, 9876);
            //envia pacote
            clienteSocket.send(enviaPacote);

            //Cria o objeto do pacote a ser recebido
            DatagramPacket recebePacote = new DatagramPacket(recebeDados, recebeDados.length);
            //recebe o pacote do servidor
            clienteSocket.receive(recebePacote);

            //separa somente os dados recebidos
            String sentencaModificada = new String(recebePacote.getData());
            //imprime o retorno
            System.out.println("Datagrama UDP enviado e recebido com sucesso: " + sentencaModificada);
        }
        //fecha o cliente
        //clienteSocket.close();
        
    }
    
}
