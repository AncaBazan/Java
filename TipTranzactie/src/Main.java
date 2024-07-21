import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("citire txt");
        Tranzactie tranzactie = new Tranzactie();
        List<Tranzactie> listaTranzactii = tranzactie.citireTxt("date\\s01_tranzactii.txt");
        System.out.println(listaTranzactii);


        System.out.println("citire json");
        Produs produs = new Produs();
        List<Produs> listaProduse =produs.citireJson("date\\produse.json");
        System.out.println(listaProduse);

        System.out.println("CERINTA 1: " + listaProduse.size());

        float totalIntrari = 0;
        float totalIesiri = 0;

        for(Tranzactie t:listaTranzactii){
           if(t.getTipTranzactie().equals(TipTranzactie.intrare)){
               totalIntrari += t.getPret() * t.getCantitate();
           }
        }
        System.out.println("CERINTA 2: " + totalIntrari + " lei");

        //extra -> pentru toate indiferent de intrare sau iesire
        for(Tranzactie t:listaTranzactii){
            totalIesiri -= t.getPret() * t.getCantitate();
        }
        System.out.println("CERINTA 2: " + totalIesiri + " lei");


        try(FileWriter fileWriter = new FileWriter("date\\stocuri.txt")) {
            List<Produs> listaProduseSortate = listaProduse.stream().sorted(Comparator.comparing(Produs::getDenumire).reversed()).collect(Collectors.toList());
            listaProduseSortate.stream().forEach(x->{
                double totalProduseIntrari = listaProduseSortate.stream().filter(z->x.getTip().equals(TipTranzactie.intrare)).mapToDouble(y->x.getPret()*x.getCantitate()).sum();
                double totalProduseIesiri = listaProduseSortate.stream().filter(z->x.getTip().equals(TipTranzactie.iesire)).mapToDouble(y->x.getPret()*x.getCantitate()).sum();

                try {
                    fileWriter.write(x.getDenumire());
                    fileWriter.write(" ");
                    fileWriter.write(String.valueOf(x.getTip()));
                    fileWriter.write(" ");
                    if(x.getTip().equals(TipTranzactie.intrare)){
                        fileWriter.write(String.valueOf(totalProduseIntrari));
                    }
                    else{
                        fileWriter.write(String.valueOf(totalProduseIesiri));
                    }
                    fileWriter.write("\n");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("CERINTA 3: fisierul a fost generat");

        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            new Thread(new ClientHandler(serverSocket.accept(),listaTranzactii)).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

