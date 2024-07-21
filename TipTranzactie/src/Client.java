import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try(Socket socket = new Socket("127.0.0.1",8080)) {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner =new Scanner(System.in);
            System.out.println("Introduceti codul produsului: ");
            String codProdus= scanner.nextLine();
            printWriter.println(codProdus);
            String raspunsServer = bufferedReader.readLine();
            System.out.println("Raspuns server: " +raspunsServer);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
