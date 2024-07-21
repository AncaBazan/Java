import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produse {
    private int cod_produs;
    private String denumire;
    private Double pret;

    public Produse() {
    }

    public Produse(int cod_produs, String denumire, Double pret) {
        this.cod_produs = cod_produs;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getCod_produs() {
        return cod_produs;
    }

    public void setCod_produs(int cod_produs) {
        this.cod_produs = cod_produs;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produse{" +
                "cod_produs = " + cod_produs +
                ", denumire = '" + denumire + '\'' +
                ", pret = " + pret +
                '}' + "\n";
    }

    public List<Produse> citireTxt(String numeFisier){
        List<Produse> listaProduse=new ArrayList<>();
        File file = new File(numeFisier);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                var bucati = linie.split(",");

                int cod_produs =Integer.parseInt(bucati[0]);
                String denumire=bucati[1];
                Double pret = Double.parseDouble(bucati[2]);

                Produse produse = new Produse(cod_produs, denumire, pret);
                listaProduse.add(produse);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaProduse;
    }


}//end class
