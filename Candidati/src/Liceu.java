import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Liceu {

    private int cod_liceu;
    private String denumire;
    private int nr_specializari;
    private Map<Integer, Integer> nr_locuri_specializare;

    public Liceu() {
    }

    public Liceu(int cod_liceu, String denumire, int nr_specializari, Map<Integer, Integer> nr_locuri_specializare) {
        this.cod_liceu = cod_liceu;
        this.denumire = denumire;
        this.nr_specializari = nr_specializari;
        this.nr_locuri_specializare = nr_locuri_specializare;
    }

    public int getCod_liceu() {
        return cod_liceu;
    }

    public void setCod_liceu(int cod_liceu) {
        this.cod_liceu = cod_liceu;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNr_specializari() {
        return nr_specializari;
    }

    public void setNr_specializari(int nr_specializari) {
        this.nr_specializari = nr_specializari;
    }

    public Map<Integer, Integer> getNr_locuri_specializare() {
        return nr_locuri_specializare;
    }

    public void setNr_locuri_specializare(Map<Integer, Integer> nr_locuri_specializare) {
        this.nr_locuri_specializare = nr_locuri_specializare;
    }

    @Override
    public String toString() {
        return "Liceu{" +
                "cod_liceu=" + cod_liceu +
                ", denumire='" + denumire + '\'' +
                ", nr_specializari=" + nr_specializari +
                ", nr_locuri_specializare=" + nr_locuri_specializare +
                '}' + "\n";
    }

    public List<Liceu> citireTxt(String numeFisier){
        List<Liceu> listaLicee = new ArrayList<>();
        File file = new File(numeFisier);
        try{
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                Scanner scannerLinie1 = new Scanner(scanner.nextLine());
                Scanner scannerLinie2 = new Scanner(scanner.nextLine());

                scannerLinie1.useDelimiter(",");
                scannerLinie2.useDelimiter(",");


                int cod_liceu = scannerLinie1.nextInt();
                String denumire = scannerLinie1.next();
                int nr_specializari =scannerLinie1.nextInt();

                Map<Integer, Integer> map = new HashMap<>();
                for(int i=0; i<nr_specializari; i++){
                    map.put(scannerLinie2.nextInt(), scannerLinie2.nextInt());
                }

                Liceu liceu = new Liceu(cod_liceu, denumire, nr_specializari, map);
                listaLicee.add(liceu);


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaLicee;
    }

    public int getNrTotalLocuti(){
        return nr_locuri_specializare.values().stream().mapToInt(Integer::intValue).sum();
    }
















}//end class
