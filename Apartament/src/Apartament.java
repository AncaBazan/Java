import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Apartament {
    private int nrApartament;
    private int suprafata;
    private int nrPersoane;

    //1
    private static final int SUPRAFATA_CAMERA = 20;
    private List<Apartament>listaApartamente = new ArrayList<>();
    public int getNumarCamere(){
        return suprafata/SUPRAFATA_CAMERA;
    }

    public void afisareNrCamere(){
        for (int i=0; i<listaApartamente.size(); i++){
            System.out.println("Apartamentul cu nr: " + listaApartamente.get(i).nrApartament +
                    " are " + listaApartamente.get(i).getNumarCamere() + " camere");
        }
    }

    //2

    public void citesteUnFisier(String numeFisier){
        try{
            File file = new File(numeFisier);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                String[] vectorParti = linie.split(",");

                int nrApartament = Integer.parseInt(vectorParti[0]);
                int suprafata = Integer.parseInt(vectorParti[1]);
                int nrPersoane = Integer.parseInt(vectorParti[2]);

                Apartament ap = new Apartament();
                ap.nrApartament = nrApartament;
                ap.suprafata = suprafata;
                ap.nrPersoane = nrPersoane;

                listaApartamente.add(ap);

            }
            scanner.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public int calculSuprafataTotala(){
        int suprafataTotala = 0;

        for(int i = 0; i < listaApartamente.size(); i++){
            suprafataTotala += listaApartamente.get(i).suprafata;
        }
        return suprafataTotala;
    }

    public int calculTotalPersoane(){
        int totalPersoane = 0;
        for(Apartament apt : listaApartamente){
            totalPersoane += apt.nrPersoane;
        }
        return totalPersoane;
    }


    //3
    public void afisarePersoanePeTipApartament(){
        Map<Integer,Integer> map1 = new HashMap<>();
        for(Apartament ap :listaApartamente){
            int nrCamere = ap.getNumarCamere();
            int nrPersoane = ap.nrPersoane;
            int totalPersoane = map1.getOrDefault(nrCamere,0);

            map1.put(nrCamere,totalPersoane + nrPersoane);
        }
        for(Map.Entry<Integer,Integer> element:map1.entrySet()){
            int nrCamere = element.getKey();
            int nrPersoane = element.getValue();
            System.out.println("Apartamente cu "+ nrCamere + " camere "+ nrPersoane +" persoane. ");
        }
    }
    //4
public void generareFisierText(){
        Scanner scanner = new Scanner(System.in);
    double cheltuieliPerPers = scanner.nextDouble();
    try(PrintWriter pw = new PrintWriter("raport.txt")){
        for(Apartament ap: listaApartamente ){
            double cotaCheltuieli = cheltuieliPerPers * ap.nrPersoane;

            pw.printf("%d %5.2f lei %n",ap.nrApartament,cotaCheltuieli);
        }
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
scanner.close();

}

}//end











