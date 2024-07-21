import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Aventura aventura = new Aventura();
        List<Aventura> listaAventuri = aventura.citireJson("date\\aventuri.json");
        System.out.println(listaAventuri);

        System.out.println("------1------");
        List<Aventura> peste20Locuri = listaAventuri.stream().filter(x->x.getLocuri_disponibile() >= 20).collect(Collectors.toList());
        System.out.println(peste20Locuri);


        System.out.println("------2------");
        Rezervare rezervare = new Rezervare();
        List<Rezervare> listaRezervari = rezervare.citireTxt("date\\rezervari.txt");
        System.out.println(listaRezervari);


        System.out.println("---------------------");
        listaAventuri.forEach(aventura1->{
            int nr_locuri_solicitate = listaRezervari.stream().filter(rezervare1->rezervare1.getCod_aventura() ==
                    aventura1.getCod_aventura()).mapToInt(Rezervare::getNr_locuri_rezervate).sum();

            int nr_locuri_ramase = aventura1.getLocuri_disponibile() - nr_locuri_solicitate;
            if(nr_locuri_ramase >= 5){
                System.out.printf("%d %s %d\n",aventura1.getCod_aventura(),aventura1.getDenumire(),nr_locuri_ramase);

            }
        });

        System.out.println("------4------");
        final int Port = 8080;

        try(ServerSocket serverSocket = new ServerSocket(Port)) {
            System.out.println("Serversul asteapta clientii ^_^ ");

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client nou conrctat la server ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
