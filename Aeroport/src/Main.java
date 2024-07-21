import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("citire Json");
        Aeroport aeroport = new Aeroport();
        List<Aeroport> listaAeroporturi = aeroport.citireJson("date\\aeroport.json");
        System.out.println(listaAeroporturi);

        System.out.println("exercitii");

        //sa se afiseze aeroporturile sortate dupa nume descrescator
        System.out.println("sortare nume");
        List<Aeroport> listaSortataDupaNumeDesc = listaAeroporturi.stream().sorted(Comparator.comparing(Aeroport::getName).reversed()).collect(Collectors.toList());
        System.out.println(listaSortataDupaNumeDesc);

        //sa afisam aeroporturile care au valoarea mai mica decat 100, si statusul "true"
        System.out.println("aeroporturi mai mici decat 100");
        List<Aeroport> listaValoarePeste100 = listaAeroporturi.stream().filter(x->x.getValue()<100).filter(y->y.isStatus() == true).collect(Collectors.toList());
        System.out.println(listaValoarePeste100);

        //sa afisam aeroporturile care au valoarea mai mare decat valoarea medie
        System.out.println("aeroporturi cu valoarea mai mare decat media");
        //varianta 1
        float suma = 0;
        float medie = 0;

        for(Aeroport a:listaAeroporturi){
            suma += a.getValue();
        }
        medie = suma/ listaAeroporturi.size();
        System.out.println(medie);

        //varianta 2
        float sumaStream = (float) listaAeroporturi.stream().mapToDouble(Aeroport::getValue).sum();
        float medieStream = sumaStream / listaAeroporturi.size();
        System.out.println(medieStream);

        System.out.println("rezultat");
        float finalMedie = medie;
        List<Aeroport> listaPesteMedie = listaAeroporturi.stream().filter(x->x.getValue() > finalMedie).collect(Collectors.toList());
        System.out.println(listaPesteMedie);


        //sa se afiseze aeroporturile a caror data este inainte de august 08 2023
        System.out.println("lista dupa data de august 2023");
        String dataExtrasa = "2023-08-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dataExtrasa);//surround with                    //after
        List<Aeroport> listaDataDupaAugust = listaAeroporturi.stream().filter(x->x.getDate().before(date)).collect(Collectors.toList());
        System.out.println(listaDataDupaAugust);


        //sa se afiseze aeroporturile care a caror nume contine spatii
        System.out.println("nume cu spatii");
        List<Aeroport> listaNumeCuSpatii = listaAeroporturi.stream().filter(x->x.getName().contains(" ")).collect(Collectors.toList());
        System.out.println(listaNumeCuSpatii);

        //sa se afiseze aeroporturile a caror nume incepe cu litera H
        System.out.println("aeroporturi cu litera H");
        List<Aeroport> lista1NumeCuH = listaAeroporturi.stream().filter(x->x.getName().substring(0,1).equals("H")).collect(Collectors.toList());
        List<Aeroport> lista2NumeCuH = listaAeroporturi.stream().filter(x->x.getName().startsWith("H")).collect(Collectors.toList());
        System.out.println(lista1NumeCuH);

        //aeroporturi a caror nume are cel putin 10 caractere
        System.out.println("nume peste 10 caractere");
        List<Aeroport> listaPeste10Caractere = listaAeroporturi.stream().filter(x->x.getName().length() >=10).collect(Collectors.toList());
        System.out.println(listaPeste10Caractere);

        System.out.println("cate -> " + listaPeste10Caractere.size());

        System.out.println("\n\n\n");
        Poarta poarta =new Poarta();
        List<Poarta> listaPorti = poarta.citireTxt("date\\poarta.txt");
        System.out.println(listaPorti);

        //sa facem suma valorii aeroportului care are acelasi id cu id-ul unei porti
        System.out.println("aeroportul care are acelasi id cu poarta");
        listaAeroporturi.stream().forEach(x->{
            float valoare = (float) listaPorti.stream().filter(y->y.getId() == x.getId()).mapToDouble(Poarta::getValoare).sum();
            System.out.printf("%s %.2f \n", x.getName(), valoare);
        });


        //sa se scrie in fisierul txt numele aeroportului si valoarea pentru aeroporturile care au id-ul unei porti
        System.out.println("valoare si id");
        try(FileWriter fileWriter= new FileWriter("date\\raport.txt")) {
            listaAeroporturi.stream().forEach(x->{
                float valoare = (float) listaPorti.stream().filter(y->y.getId() == x.getId()).mapToDouble(Poarta::getValoare).sum();
                try {
                    fileWriter.write(x.getName());
                    fileWriter.write(" ");
                    fileWriter.write(String.valueOf(valoare));
                    fileWriter.write("\n");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n\nfisierul raport.txt a fost creat");


    }
}
