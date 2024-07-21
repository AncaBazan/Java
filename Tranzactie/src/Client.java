import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.Scanner;

public class Client {
    public static void main(){
        final int Port = 8080;
        final String adresaServer = "127.0.0.1";

        try(Socket socket = new Socket(adresaServer, Port)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("clientul s-a cionectat la server");

            Scanner scanner = new Scanner(System.in);
            String nume = scanner.nextLine();
            printWriter.println(nume);
            System.out.println("respuns server : " + bufferedReader.readLine());


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
