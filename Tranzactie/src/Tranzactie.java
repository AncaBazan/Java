import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tranzactie {
    private int cod;
    private String simbol;

    private TipTranzactie tranzactie;
    private int cantitate;
    private float pret;

    public Tranzactie() {
    }

    public Tranzactie(int cod, String simbol, TipTranzactie tranzactie, int cantitate, float pret) {
        this.cod = cod;
        this.simbol = simbol;
        this.tranzactie = tranzactie;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }


    public TipTranzactie getTranzactie() {
        return tranzactie;
    }

    public void setTranzactie(TipTranzactie tranzactie) {
        this.tranzactie = tranzactie;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
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
                "cod=" + cod +
                ", simbol='" + simbol + '\'' +
                ", tranzactie=" + tranzactie +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}' + "\n";
    }

    public List<Tranzactie> citireTxt (String numeFisier){
        List<Tranzactie> listTranzactie =new ArrayList<>();
        try{
            File file = new File(numeFisier);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                var bucati = linie.split(",");

                int cod = Integer.parseInt(bucati[0]);
                 String simbol = bucati[1];
                 TipTranzactie tipTranzactie = TipTranzactie.valueOf(bucati[2]);
                 int cantitate = Integer.parseInt(bucati[3]);
                 float pret = Float.parseFloat(bucati[4]);


                Tranzactie tranzactie = new Tranzactie(cod,simbol,tipTranzactie,cantitate,pret);
                listTranzactie.add(tranzactie);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listTranzactie;
    }
    

}//end class
