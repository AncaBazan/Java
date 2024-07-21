import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;

public class Programare implements Comparable<Programare> {

    //1
    private String disciplina;
    private int zi;
    private int interval;
    private Tip tip;
    private String formatie;

    private List<Programare> listaProgramari = new ArrayList<>();

    public Programare() {
    }

    public Programare(String disciplina, int zi, int interval, Tip tip, String formatie) throws Exception {
        this.disciplina = disciplina;
        this.zi = zi;
        this.interval = interval;
        this.tip = tip;
        this.formatie = formatie;
    }

    public int getZi() throws Exception {
        if(this.zi < 1 || this.zi > 5){
            throw new Exception("Ziua trebuie sa fie intre 1 si 5");
        }
        return this.zi;
    }

    public int getInterval() throws Exception {
        if(this.interval < 1 || this.interval > 8){
            throw new Exception("Intervalul trebuie sa fie intre 1 si 8");
        }
        return this.interval;
    }


    @Override
    public int compareTo(Programare o) {
       if(this.zi < o.zi){
           return -1;
       }
       else if(this.zi == o.zi && this.interval < o.interval){
           return -1;
       }
       else if(this.zi == o.zi && this.interval == o.interval){
           return 0;
       }
       return 1;

    }

    //2
    public void citireDinFisier(String numeFisier) {
        File file = new File(numeFisier);

        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                String[] bucati = linie.split(",");
                String disciplina = bucati[0];

                int zi  = Integer.parseInt(bucati[1]);
                int interval = Integer.parseInt(bucati[2]);

                Tip tip = Tip.valueOf(bucati[3]);
                String formatie = bucati[4];

                Programare programare = new Programare(disciplina,zi,interval,tip,formatie);
                listaProgramari.add(programare);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
public void afisareNrCursuriSiSeminarii(){
    int nrCursuri = 0;
    int nrSeminarii = 0;

    for(Programare p: listaProgramari){
        if(p.tip == Tip.CURS){
            nrCursuri++;
        }
        else nrSeminarii++;
    }
    System.out.printf("Nr de cursuri este %d, iar nr de seminarii este %d\n",nrCursuri,nrSeminarii);

}
    //3
    public void afisareDisciplinaComplet() throws Exception {
        Map< String, int[]> map = new HashMap<>();
                for(Programare p: listaProgramari){
                    String disciplina = p.disciplina;
                    int[] frecvente =map.getOrDefault(disciplina,new int[]{0,0});
                    if(p.tip == Tip.CURS){
                        frecvente[0]++;
                    }
                    else frecvente[1]++;

                    map.put(disciplina,frecvente);
                }
                for(Programare p: listaProgramari){
                    String disciplina = p.disciplina;
                    int[] frecvente = map.get(disciplina);
                    if(frecvente[0]== 1 && frecvente[1] == 2){
                        System.out.printf("%s\n tip: %s zile: %d interval: %d\n",disciplina,p.tip,p.getZi(),p.getInterval());
                    }

                }
    }


}//end
