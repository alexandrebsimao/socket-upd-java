/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpservidor;

import java.io.*;
import java.net.*;

/**
 *
 * @author AlexandreAugusto
 */
public class UDPServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String argv[]) throws Exception{
        //Variavel guarda a porta do servidor
        int portaServ = 9876;
        
        //Cria o socket definindo o objeto com a porta inserida acima
        DatagramSocket servidorSocket = new DatagramSocket(portaServ);
        byte[] dadosRecebidos = new byte[1024];
        byte[] dadosEnviados =  new byte[1024];
        
        //Enquanto for verdadeiro...
        while(true){
            System.out.println("Aguardando cliente...");
            //Cria um objeto do pacote a ser recebido
            DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos,dadosRecebidos.length);
            //Recebe o pacote do cliente
            servidorSocket.receive(pacoteRecebido);
            
            //Pega os dados, o endereço ip e a porta do cliente
            //para poder mandar a mensagem de volta
            String sentenca = new String(pacoteRecebido.getData());
            
            System.out.println(sentenca);
            
            InetAddress enderecoIP = pacoteRecebido.getAddress();
            int porta = pacoteRecebido.getPort();
            
            //converte a string capturada em maiúscula
            String sentencaCapturada = sentenca.toUpperCase();
            dadosEnviados = sentencaCapturada.getBytes();
            
            //cria o objeto do pacote com o endereco ip e a porta
            DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, enderecoIP, porta);
            
            //Envia ao cliente
            servidorSocket.send(pacoteEnviado);
        }
    }
    
}
