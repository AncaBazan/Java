import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    public static void main(){

        final int Port = 8080;
        final String nume_server = "127.0.0.1";
        try(Socket socket = new Socket(nume_server,Port)){


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
