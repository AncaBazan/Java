import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rezervare {

    private String id_rezervare;
    private int cod_aventura;
    private int nr_locuri_rezervate;

    public Rezervare() {
    }

    public Rezervare(String id_rezervare, int cod_aventura, int nr_locuri_rezervate) {
        this.id_rezervare = id_rezervare;
        this.cod_aventura = cod_aventura;
        this.nr_locuri_rezervate = nr_locuri_rezervate;
    }

    public String getId_rezervare() {
        return id_rezervare;
    }

    public void setId_rezervare(String id_rezervare) {
        this.id_rezervare = id_rezervare;
    }

    public int getCod_aventura() {
        return cod_aventura;
    }

    public void setCod_aventura(int cod_aventura) {
        this.cod_aventura = cod_aventura;
    }

    public int getNr_locuri_rezervate() {
        return nr_locuri_rezervate;
    }

    public void setNr_locuri_rezervate(int nr_locuri_rezervate) {
        this.nr_locuri_rezervate = nr_locuri_rezervate;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "id_rezervare='" + id_rezervare + '\'' +
                ", cod_aventura=" + cod_aventura +
                ", nr_locuri_rezervate=" + nr_locuri_rezervate +
                '}' + "\n";
    }

    public List<Rezervare> citireTxt(String numeFisier){
        List<Rezervare> listaRezervari = new ArrayList<>();


        try{
            File file = new File(numeFisier);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                String[] bucati = linie.split(",");

                String id_rezervare = bucati[0];
                int cod_avntura = Integer.parseInt(bucati[1]);
                int nr_locuri_rezervate = Integer.parseInt(bucati[2]);

                Rezervare rezervare = new Rezervare(id_rezervare,cod_avntura,nr_locuri_rezervate);

                listaRezervari.add(rezervare);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaRezervari;
    }
}
