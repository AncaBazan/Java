import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {

        //a
        System.out.println("citire txt");
        Produse produse = new Produse();
        List<Produse> listaProduse = produse.citireTxt("date\\produse.txt");
        System.out.println(listaProduse);

        //b
        System.out.println("citire json");
        Tranzactii tranzactii = new Tranzactii();
        List<Tranzactii> listaTranzactii = tranzactii.citireJson("date\\tranzactii.json");
        System.out.println(listaTranzactii);

        //1
        System.out.println("ex 1 numarul de produse");
        System.out.println(listaProduse.size());

        //extra
        System.out.println("pret peste 20");
        List<Produse> listaProdusePeste20 = listaProduse.stream().filter(x->x.getPret() > 20).collect(Collectors.toList());
        System.out.println(listaProdusePeste20);

        System.out.println("lista sub 20");
        List<Produse> listaPretSub20Descrescator = listaProduse.stream().filter(x->x.getPret() < 20)
                .sorted(Comparator.comparing(Produse::getCod_produs).reversed()).collect(Collectors.toList());
        System.out.println(listaPretSub20Descrescator);

        System.out.println("ex 2");
        List<Produse> listaSortataDupaNume = listaProduse.stream().sorted(Comparator.comparing(Produse::getDenumire)).collect(Collectors.toList());
        System.out.println(listaSortataDupaNume);

        System.out.println("extra json");
        int totalCantitateIntrare = listaTranzactii.stream().filter(x->x.getTipTranzactie().equals("intrare")).mapToInt(Tranzactii::getCantitate).sum();
        int totalCantitateIesire = listaTranzactii.stream().filter(x->x.getTipTranzactie().equals("iesire")).mapToInt(Tranzactii::getCantitate).sum();

        System.out.println("intrare -> " + totalCantitateIntrare);
        System.out.println("iesire -> " + totalCantitateIesire);

        System.out.println("ex 3");
        Map<String, Integer> tranzactiiMap = new HashMap<>();
        try(FileWriter fileWriter = new FileWriter("date\\lista.txt")) {
            listaProduse.stream().forEach(x -> {
                int nrTranzactii = (int) listaTranzactii.stream().filter(y -> y.getCodProdus() == x.getCod_produs()).count();
                tranzactiiMap.put(x.getDenumire(), nrTranzactii);
            });

            tranzactiiMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry -> {
                        try {
                            fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ex 4");
        listaProduse.forEach(x -> {
            double totalStoc = listaTranzactii.stream()
                    .filter(y -> y.getCodProdus() == x.getCod_produs())
                    .mapToDouble(y -> x.getPret() * y.getCantitate())
                    .sum();
            System.out.println("Produs: " + x.getDenumire() + " - Valoare Stoc: " + totalStoc);
        });

    }

}