import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		// separar a entrada dos textos em blocos
		try (Scanner sc = new Scanner(System.in)) {
		double oper1= 0;
		double oper2= 0;
		
			// Permite a escolha de numeros a ser calculado
		System.out.print("Digite o primeiro numero ");
		oper1 = sc.nextInt ();
		System.out.print("Digite o segundo numero ");
		oper2 = sc.nextInt();
		
		//1-somar 2-subtrair 3-multiplicar 4-dividir
		double soma = oper1+oper2;
		double subtra��o = oper1-oper2;
		double multiplica��o = oper1*oper2;
		double divis�o = oper1/oper2;
		
		// Mostrar o resultado de soma, subtra��o, multiplica��o e divis�o separadamente
		System.out.print(" Sua Soma � = " +soma);
		System.out.print(" \n Sua Subtra��o � = " + subtra��o);
		System.out.print(" \n Sua Multiplica��o � = " + multiplica��o);
		System.out.print(" \n Sua Divis�o � = " + divis�o);
		
		String result=""; 

        	//Conex�o com o Servidor
            Socket clientSocket = new Socket("192.168.15.7", 9090);
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Enviando os dados // Mudei operacao por result para que possa executar as opera��es 
            socketSaidaServer.writeBytes(result+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            result=messageFromServer.readLine();
            
            System.out.println("resultado="+result);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

   
	}

}
