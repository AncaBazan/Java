import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tranzactie {
    private int cod_produs;
    private int cantitate;
    private TipTranzactie tipTranzactie;
    private float pret;

    public Tranzactie() {
    }

    public Tranzactie(int cod_produs, int cantitate, TipTranzactie tipTranzactie, float pret) {
        this.cod_produs = cod_produs;
        this.cantitate = cantitate;
        this.tipTranzactie = tipTranzactie;
        this.pret = pret;
    }

    public int getCod_produs() {
        return cod_produs;
    }

    public void setCod_produs(int cod_produs) {
        this.cod_produs = cod_produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public TipTranzactie getTipTranzactie() {
        return tipTranzactie;
    }

    public void setTipTranzactie(TipTranzactie tipTranzactie) {
        this.tipTranzactie = tipTranzactie;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "cod_produs = " + cod_produs +
                ", cantitate = " + cantitate +
                ", tipTranzactie = " + tipTranzactie +
                ", pret = " + pret +
                '}' + "\n";
    }

    public List<Tranzactie> citireTxt(String numeFisier){
        List<Tranzactie> listaTranzactii=new ArrayList<>();
        File file = new File(numeFisier);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String linie=scanner.nextLine();
                var bucati= linie.split(",");

                int cod_produs=Integer.parseInt(bucati[0]);
                int cantitate =Integer.parseInt(bucati[1]);
                TipTranzactie tipTranzactie=TipTranzactie.valueOf(bucati[2]);
                float pret = Float.parseFloat(bucati[3]);

                Tranzactie tranzactie=new Tranzactie(cod_produs,cantitate,tipTranzactie,pret);
                listaTranzactii.add(tranzactie);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaTranzactii;
    }
    

}//end class

