
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        System.out.println("--- citire bd ---");
        Specializare specializare = new Specializare();
        List<Specializare> listaSpecializare = specializare.citireBD("jdbc:sqlite:date\\facultate.db");

        System.out.println(listaSpecializare);

        System.out.println("--- citire txt ---");
        Inscriere inscriere = new Inscriere();
        List<Inscriere> listaInscrieri = inscriere.citireTxt("date\\inscrieri.txt");
        System.out.println(listaInscrieri);

        System.out.println("--- 1 afisare nr total de locuri disponibile ---");
        int nr_total_facultate = listaSpecializare.stream().mapToInt(Specializare::getLocuri).sum();
        System.out.println(nr_total_facultate);

        System.out.println("--- 2 in urma inscrierilor mai au cel putin 10 locuri disponibile ---");

        listaSpecializare.forEach(x->{
            int nrLocuriOcupate = listaInscrieri.stream().filter(y->y.getCod_specializare_aleasa() ==
                    x.getCod()).collect(Collectors.toList()).size();

            int nrLocuriRamase = x.getLocuri() - nrLocuriOcupate;

            if(nrLocuriRamase >= 10){
                System.out.println(x.getCod() + " " + x.getDenumire() + " " + nrLocuriRamase);
            }
        });


        System.out.println("--- 3 sa se salveze in json nr de inscrieri pentru fiecare specializare si val media notelor ---");
        try(FileWriter fileWriter = new FileWriter("date\\inscrieri_specializari.json")) {

            JSONArray jsonArray = new JSONArray();
            listaSpecializare.forEach(x->{
                int nrInscrieri = listaInscrieri.stream().filter(y->y.getCod_specializare_aleasa() ==
                        x.getCod()).collect(Collectors.toList()).size();

                double medie = listaInscrieri.stream().filter(y->y.getCod_specializare_aleasa() ==
                        x.getCod()).collect(Collectors.toList()).stream().collect(Collectors.averagingDouble(Inscriere::getNota_bacalaureat));

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("cod_specializare", x.getCod());
                jsonObject.put("denumire", x.getDenumire());
                jsonObject.put("numar_inscrieri", nrInscrieri);
                jsonObject.put("medie", medie);

                jsonArray.put(jsonObject);

            });
            fileWriter.write(jsonArray.toString(4));



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--- fisierul s-a creat ---");



        System.out.println("--- 4 + 5 tcp/ip  ---");
        final int PORT = 8080;
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("serverul asteapta conexiunea de la clienti...");

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("client conectat");

                new Thread(()->{
                    procesareCerere(socket, listaSpecializare, listaInscrieri);

                }).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void procesareCerere(Socket socket, List<Specializare> listaSpecializari, List<Inscriere> listaInscrieri){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

            while(true){
                String specializare = bufferedReader.readLine();
                Specializare specializare1 = listaSpecializari.stream().filter(x->x.getDenumire().equalsIgnoreCase(specializare))
                        .collect(Collectors.toList()).stream().findFirst().get();

                int nrInscrieri = listaInscrieri.stream().filter(y->y.getCod_specializare_aleasa() ==
                        specializare1.getCod()).collect(Collectors.toList()).size();

                printWriter.println(specializare1.getLocuri() - nrInscrieri);
                System.out.println("cerere procesata cu succes");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}//end
