import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aventura {

    private int cod_aventura;
    private String denumire;
    private float tarif;
    private int locuri_disponibile;

    public Aventura() {
    }

    public Aventura(int cod_aventura, String denumire, float tarif, int locuri_disponibile) {
        this.cod_aventura = cod_aventura;
        this.denumire = denumire;
        this.tarif = tarif;
        this.locuri_disponibile = locuri_disponibile;
    }

    public int getCod_aventura() {
        return cod_aventura;
    }

    public void setCod_aventura(int cod_aventura) {
        this.cod_aventura = cod_aventura;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public int getLocuri_disponibile() {
        return locuri_disponibile;
    }

    public void setLocuri_disponibile(int locuri_disponibile) {
        this.locuri_disponibile = locuri_disponibile;
    }

    @Override
    public String toString() {
        return "Aventura{" +
                "cod_aventura=" + cod_aventura +
                ", denumire='" + denumire + '\'' +
                ", tarif=" + tarif +
                ", locuri_disponibile=" + locuri_disponibile +
                '}' + "\n";
    }

    // citire jSon
    public List<Aventura> citireJson(String numeFisier) {
        List<Aventura> listaAventuri = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(numeFisier)) {

            JSONTokener jsonTokener = new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);
            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Aventura aventura=new Aventura(jsonObject.getInt("cod_aventura"),jsonObject.getString("denumire"),
                        jsonObject.getFloat("tarif"), jsonObject.getInt("locuri_disponibile"));
                listaAventuri.add(aventura);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaAventuri;
    }
}
