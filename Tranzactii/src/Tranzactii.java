import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tranzactii {
    private int codProdus;
    private int cantitate;
    private TipTranzactie tipTranzactie;

    public Tranzactii() {
    }

    public Tranzactii(int codProdus, int cantitate, TipTranzactie tipTranzactie) {
        this.codProdus = codProdus;
        this.cantitate = cantitate;
        this.tipTranzactie = tipTranzactie;
    }

    public int getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(int codProdus) {
        this.codProdus = codProdus;
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

    @Override
    public String toString() {
        return "Tranzactii{" +
                "codProdus=" + codProdus +
                ", cantitate=" + cantitate +
                ", tipTranzactie=" + tipTranzactie +
                '}' + "\n";
    }

    public List<Tranzactii> citireJson(String numeFisier){
        List<Tranzactii> listaTranzactii = new ArrayList<>();
        try(FileInputStream fileInputStream = new FileInputStream(numeFisier)) {
            JSONTokener jsonTokener = new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Tranzactii tranzactii = new Tranzactii(jsonObject.getInt("codProdus"),
                        jsonObject.getInt("cantitate"),TipTranzactie.valueOf(jsonObject.getString("tip")));

                listaTranzactii.add(tranzactii);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaTranzactii;
    }
    

}//end class
