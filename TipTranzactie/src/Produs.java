import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Produs {
    private String denumire;
    private int codProdus;
    private int cantitate;
    private TipTranzactie tip;
    private float pret;

    public Produs() {
    }

    public Produs(String denumire, int codProdus, int cantitate, TipTranzactie tip, float pret) {
        this.denumire = denumire;
        this.codProdus = codProdus;
        this.cantitate = cantitate;
        this.tip = tip;
        this.pret = pret;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
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

    public TipTranzactie getTip() {
        return tip;
    }

    public void setTip(TipTranzactie tip) {
        this.tip = tip;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "denumire = '" + denumire + '\'' +
                ", codProdus = " + codProdus +
                ", cantitate = " + cantitate +
                ", tip = " + tip +
                ", pret = " + pret +
                '}' + "\n";
    }

    public List<Produs> citireJson(String numeFisier){
        List<Produs> listaProduse=new ArrayList<>();
        try(FileInputStream fileInputStream=new FileInputStream(numeFisier)) {
            JSONTokener jsonTokener=new JSONTokener(fileInputStream);
            JSONArray jsonArray=new JSONArray(jsonTokener);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                Produs produs=new Produs(jsonObject.getString("denumire"),jsonObject.getInt("codProdus"),jsonObject.getInt("cantitate"),
                        TipTranzactie.valueOf(jsonObject.getString("tip")),jsonObject.getFloat("pret"));
                listaProduse.add(produs);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listaProduse;
    }


}
