import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(){
        final int PORT = 8080;
        final String adresaServer = "127.0.0.1";

        try(Socket socket = new Socket(adresaServer, PORT)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("client conectat la server");

            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("introduceti denumirea specializarii");
                String specializare = scanner.nextLine();
                printWriter.println(specializare);
                System.out.println("raspuns server -> nr locuri ramase: " + bufferedReader.readLine());
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
