import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		
	String result="";
    try {

       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true) ;

        //ENVIO DOS PARAMETROS
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        
        int oper1= 15;
		int oper2= 15;
        writer.write("oper1=15 & oper2=15 & operacao=3"); 
        
        //1-somar 2-subtrair 3-multiplicar 4-dividir
		int soma = oper1+oper2;
		int subtra��o = oper1-oper2;
		int multiplica��o = oper1*oper2;
		int divis�o = oper1/oper2;
		

        
        writer.flush();
        writer.close();
        os.close();

        int responseCode=conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            //RECBIMENTO DOS PARAMETROS

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            result = response.toString();
            System.out.println("Resposta do Servidor PHP= "+result);
            
           // Resultado de soma, subtra��o, multiplica��o e divis�o separadamente
            System.out.println(" Sua Soma � = " +soma);
    		System.out.println(" Sua Subtra��o � = " + subtra��o);
    		System.out.println(" Sua Multiplica��o � = " + multiplica��o);
    		System.out.println(" Sua Divis�o � = " + divis�o);
            
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
