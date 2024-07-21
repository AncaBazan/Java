import java.util.List;

public class Main {
    public static void main(String[] args) {
        Produs produs = new Produs();

        List<Produs> listaProduse = produs.preluareProduse("date\\produse.txt");
        System.out.println(listaProduse);

        System.out.println(produs.getValoareTotala(listaProduse));

        produs.getTop3ProduseStream(listaProduse);
        System.out.println("------------------");
        produs.getTop3ProduseCompareTo(listaProduse);

        Produs.generareFisierTxt(listaProduse,"date\\produse_raport.txt");

        Produs.scriereInBinar(listaProduse,"date\\produse.dat");

        List<Produs>produsList = Produs.citireDinBinar("date\\produse.dat");
        System.out.println(produsList);
    }
}