import com.sun.source.util.ParameterNameProvider;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //a
        System.out.println("---1 citire bd ---");
        Persoana persoana = new Persoana();
        List<Persoana> listaPersoane = persoana.citierBd("date\\bursa.db");
        System.out.println(listaPersoane);

        // b
        System.out.println("---2 citire txt ---");
        Tranzactie tranzactie = new Tranzactie();
        List<Tranzactie> listaTranzactie = tranzactie.citireTxt("date\\bursa_tranzactii.txt");
        System.out.println(listaTranzactie);

        //1
        System.out.println("---3 clienti nerezidenti - cnp cu 8 sau 9 ---");
        List<Persoana> listaNerezidenti8 = listaPersoane.stream().filter(x->x.getCnp().substring(0,1).equals("8")).collect(Collectors.toList());
        List<Persoana> listaNerezidenti9 = listaPersoane.stream().filter(x->x.getCnp().substring(0,1).equals("9")).collect(Collectors.toList());

        int totalNerezidenti = listaNerezidenti8.size() + listaNerezidenti9.size();
        System.out.println("Numarul de nerezidenti este "+ totalNerezidenti);

        System.out.println("\nafisare nerezidenti + nume ");
        List<Persoana> listaNerezidenti = new ArrayList<>();
        listaNerezidenti.addAll(listaNerezidenti8);
        listaNerezidenti.addAll(listaNerezidenti9);
        for(Persoana p:listaNerezidenti){
            System.out.println(p);
        }

        //2
        System.out.println("--- 4 cate tranzactii are simbolul x ---");
        listaTranzactie.stream().collect(Collectors.groupingBy(Tranzactie::getSimbol)).forEach((x, y)->{
            System.out.println(x + " : " + y.size());
        });

        //3
        System.out.println("--- 5 salvare in txt simbolurile pentru care exista tranzactii ---");
        try(FileWriter fileWriter = new FileWriter("date\\simboluti.txt")) {
            listaTranzactie.stream().collect(Collectors.groupingBy(Tranzactie::getSimbol)).forEach((x, y)->{
                if(y.size() > 0){
                    try {
                        fileWriter.write(x + "\n");
                        System.out.println("S-a creat fisierul simboluri.txt");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //4
        System.out.println("--- 5 tcp/ip---");
        final int Port = 8080;
        try(ServerSocket serverSocket = new ServerSocket(Port)) {
            System.out.println("serverul asteapta clientii... ");

            while(true){
                Socket client = serverSocket.accept();
                System.out.println("client conectat");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

