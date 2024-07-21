import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)  {
        System.out.println("---a---");
        Apartament apartament = new Apartament();
        List<Apartament> listaApartamente = apartament.citireTxt("date\\intretinere_apartamente.txt");
        System.out.println(listaApartamente);

        System.out.println("---b---");
        Factura factura = new Factura();
        List<Factura> listaFacturi = factura.citireJson("date\\intretinere_facturi.json");
        System.out.println(listaFacturi);

        System.out.println("---1 nr ap cu suprafața maximă---");
        List<Apartament> listSortateDupaSuprafata = listaApartamente.stream().sorted(Comparator.comparing
                (Apartament::getSuprafata).reversed()).collect(Collectors.toList());
        System.out.println(listSortateDupaSuprafata);
        int nrApSuprafataMaxima = listSortateDupaSuprafata.get(0).getNr_apartamente();
        System.out.println(nrApSuprafataMaxima);

        System.out.println("---2 persoane care locuiesc în imobil---");
        int sumaPersoaneImobil = 0;
        for(int i=0; i<listaApartamente.size(); i++){
            sumaPersoaneImobil += listaApartamente.get(i).getNr_persoane();
        }
        System.out.println(sumaPersoaneImobil);

        System.out.println("---3 valoarea totală a facturilor pe fiecare tip de repartizare---");
        listaFacturi.stream().collect(Collectors.groupingBy(Factura::getRepartizare)).forEach((x,y)->{
            System.out.println(x + " " + y.stream().mapToInt(Factura::getValoare).sum());
        });

        System.out.println("--- 4 ---");
        Map<String, Integer> map = new HashMap<>();
        listaFacturi.stream().collect(Collectors.groupingBy(Factura::getRepartizare)).forEach((x,y)->{
            int valoareTotala = y.stream().mapToInt(Factura::getValoare).sum();
            map.put(x,valoareTotala);
        });
        try(FileWriter fileWriter = new FileWriter("date\\tabele_intretinere.txt")) {
            fileWriter.write("Număr apartament, Suprafata, Persoane, Cheltuieli Suprafata, Cheltuieli Persoane, Cheltuieli Apartament, Total de plata " );
            fileWriter.write("\n");
            listSortateDupaSuprafata.forEach(x->{
                try {
                    fileWriter.write(String.valueOf(x.getNr_apartamente()));
                    fileWriter.write(" ");
                    fileWriter.write(String.valueOf(x.getSuprafata()));
                    fileWriter.write(" ");
                    fileWriter.write(String.valueOf(x.getNr_persoane()));
                    fileWriter.write(" ");
                    ///cheltuieli pers
                    fileWriter.write(String.valueOf(x.getSuprafata()*map.get("suprafata")));
                    fileWriter.write(" ");
                    fileWriter.write(String.valueOf(x.getNr_persoane()*map.get("persoana")));
                    fileWriter.write(" ");
                    //cheltuieli ap
                    fileWriter.write(String.valueOf(x.getSuprafata()*map.get("suprafata")+x.getNr_apartamente()*map.get("persoana")));
                    fileWriter.write("\n");
                    //totalul e suma tuturor -> facuta


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---extra---");
        System.out.println("--- sa se afiseze nr de facturi repartizate pe suprafata si nr persoana ---");
        int nrFacturiPeSuprafata = (int) listaFacturi.stream().filter(x->x.getRepartizare().equals("suprafata")).count();
        int nrFacturiPePersoana = (int) listaFacturi.stream().filter(x->x.getRepartizare().equals("persoana")).count();

        System.out.println("persoana-> " +nrFacturiPePersoana);
        System.out.println("suprafata-> " + nrFacturiPeSuprafata);

        System.out.println("--- afisare facturi care au mai mare de 200 si sortata crescator dupa denumire ---");
        List<Factura> listaFacturiSortateSiFiltrata = listaFacturi.stream().filter(x->x.getValoare()>200)
                .sorted(Comparator.comparing(Factura::getDenumire)).collect(Collectors.toList());
        System.out.println(listaFacturiSortateSiFiltrata);

        System.out.println("---sa afisam faactura cu litera A si sortata descrescator dupa valoare---");
        List<Factura> listaCuLiteraA = listaFacturi.stream().filter(x->x.getDenumire().substring(0,1).equals("A")).
                sorted(Comparator.comparing(Factura::getValoare).reversed()).collect(Collectors.toList());
        System.out.println(listaCuLiteraA);


        System.out.println("--- de afisat doar acele facturi a caror denumire nu contine spatii goale ---");
        List<Factura> listaCareNUContineSpatiiGoale = listaFacturi.stream().filter(x->!x.getDenumire().contains(" ")).collect(Collectors.toList());
        System.out.println(listaCareNUContineSpatiiGoale);

        System.out.println("--- de afisat facturile a caror denumire contine cel putin 10 caractere ---");
        List<Factura> listaCelPutin10Caractere = listaFacturi.stream().filter(x->x.getDenumire().length()>=10).collect(Collectors.toList());
        System.out.println(listaCelPutin10Caractere);

        System.out.println("--- sa se afiseze numarul de facturi a carei denumirei are cel mult 10 caractere ---");
        int nrFacturiCelMult10Caractere = (int) listaFacturi.stream().filter(x->x.getDenumire().length()<=10).count();
        System.out.println(nrFacturiCelMult10Caractere);

        System.out.println("--- sa se afiseze media aritmetica dupa valoare si afisare lista facturi care au val peste medie si nr de facturi care indeplineste aceasta conditie ---");
        double medieAritmetica = 0.0;
        int sumaValori = 0;
        for(int i=0; i<listaFacturi.size(); i++){
            sumaValori+= listaFacturi.get(i).getValoare();

        }
        medieAritmetica = sumaValori/listaFacturi.size();

        //peste medie
        double finalMedieAritmetica = medieAritmetica;
        List<Factura> listaFacturaPesteMedie = listaFacturi.stream().filter(x->x.getValoare()> finalMedieAritmetica).collect(Collectors.toList());
        System.out.println(listaFacturaPesteMedie);
        System.out.println("nr facturi peste medie: " + listaFacturaPesteMedie.size());
        System.out.println("media aritmetica este: " + medieAritmetica);

    }

}//end
