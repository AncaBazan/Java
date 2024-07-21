import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String denumire;
    private String repartizare;
    private int valoare;

    public Factura() {
    }

    public Factura(String denumire, String repartizare, int valoare) {
        this.denumire = denumire;
        this.repartizare = repartizare;
        this.valoare = valoare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getRepartizare() {
        return repartizare;
    }

    public void setRepartizare(String repartizare) {
        this.repartizare = repartizare;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "denumire='" + denumire + '\'' +
                ", repartizare='" + repartizare + '\'' +
                ", valoare=" + valoare +
                '}'+ "\n";
    }

    public List<Factura> citireJson(String numeFisier){
        List<Factura> listaFactura = new ArrayList<>();
        try(FileInputStream fileInputStream = new FileInputStream(numeFisier)) {
            JSONTokener jsonTokener = new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Factura factura = new Factura(jsonObject.getString("denumire"),
                        jsonObject.getString("repartizare"), jsonObject.getInt("valoare"));

                listaFactura.add(factura);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaFactura;
    }


}//end class
