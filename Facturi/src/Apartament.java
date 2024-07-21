import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Apartament {
    private int nr_apartamente;
    private int suprafata;
    private int nr_persoane;

    public Apartament() {
    }

    public Apartament(int nr_apartamente, int suprafata, int nr_persoane) {
        this.nr_apartamente = nr_apartamente;
        this.suprafata = suprafata;
        this.nr_persoane = nr_persoane;
    }

    public int getNr_apartamente() {
        return nr_apartamente;
    }

    public void setNr_apartamente(int nr_apartamente) {
        this.nr_apartamente = nr_apartamente;
    }

    public int getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(int suprafata) {
        this.suprafata = suprafata;
    }

    public int getNr_persoane() {
        return nr_persoane;
    }

    public void setNr_persoane(int nr_persoane) {
        this.nr_persoane = nr_persoane;
    }

    @Override
    public String toString() {
        return "Apartament{" +
                "nr_apartamente=" + nr_apartamente +
                ", suprafata=" + suprafata +
                ", nr_persoane=" + nr_persoane +
                '}'+ "\n";
    }

    public List<Apartament> citireTxt(String numeFisier){
        List<Apartament> listaApartamente = new ArrayList<>();
        File file = new File(numeFisier);
        try{
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                var bucati = linie.split(",");

                int nr_apartamente = Integer.parseInt(bucati[0]);
                int suprafata = Integer.parseInt(bucati[1]);
                int nr_persoane = Integer.parseInt(bucati[2]);

                Apartament apartament = new Apartament(nr_apartamente, suprafata,nr_persoane);
                listaApartamente.add(apartament);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaApartamente;
    }


}//end class
