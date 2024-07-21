import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("citire txt");
        Intrebari intrebari=new Intrebari();
        List<Intrebari> listaIntrebari=intrebari.citireTxt("date\\intrebari.txt");
        System.out.println(listaIntrebari);

        System.out.println("citire Bd");
        Raspuns raspuns =new Raspuns();
        List<Raspuns> listaCitireBd=raspuns.citireBD("jdbc:sqlite:date\\raspunsuri.db");
        System.out.println(listaCitireBd);


        System.out.println("ex 1");
        List<Intrebari> listaIntrebariSortate = listaIntrebari.stream().sorted(Comparator.comparing(Intrebari::getId)).collect(Collectors.toList());
        System.out.println(listaIntrebariSortate);
        listaIntrebariSortate.forEach(x->{
            System.out.println(x.getId() + " raspuns-> " + x.getRaspuns() + " punctaj-> " + x.getPunctaj());
        });

        System.out.println("ex 2");
        List<Raspuns> listaStudentiParticipanti=listaCitireBd.stream().distinct().collect(Collectors.toList());
        System.out.println(listaStudentiParticipanti);

        List<String> numeStudenti=new ArrayList<>();
        for(Raspuns r:listaStudentiParticipanti){
            numeStudenti.add(r.getNumeStudent());
        }
        System.out.println("afisare nume studenti");
        System.out.println(numeStudenti);


        System.out.println("ex 3");
        int punctajMaxim = listaIntrebari.stream().mapToInt(Intrebari::getPunctaj).sum();
        System.out.println("punctaj maxim: " +punctajMaxim);


        Map<String,Float> map =new HashMap<>();

        try(FileWriter fileWriter =new FileWriter("date\\note.txt")) {
            listaCitireBd.forEach(x->{
                int punctajStudent = listaIntrebari.stream().filter(y->y.getId() == x.getId())
                        .filter(y->y.getRaspuns().equals(x.getRaspuns())).mapToInt(Intrebari::getPunctaj).sum();

                float nota = (float) 10 * punctajStudent /punctajMaxim;


                float sumaNote = map.getOrDefault(x.getNumeStudent(),0f);
                sumaNote+=nota;
                map.put(x.getNumeStudent(),sumaNote);

            });

            for(var entry: map.entrySet()){
                fileWriter.write(entry.getKey());
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(entry.getValue()));
                fileWriter.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}