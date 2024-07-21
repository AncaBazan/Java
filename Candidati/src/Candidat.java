import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Candidat {
    private int cod_candidat;
    private String nume_candidat;
    private double medie;
    private Map<Integer, List<Integer>> optiuni;

    public Candidat() {
    }

    public Candidat(int cod_candidat, String nume_candidat, double medie, Map<Integer, List<Integer>> optiuni) {

        this.cod_candidat = cod_candidat;
        this.nume_candidat = nume_candidat;
        this.medie = medie;
        this.optiuni = optiuni;
    }

    public int getCod_candidat() {
        return cod_candidat;
    }

    public void setCod_candidat(int cod_candidat) {
        this.cod_candidat = cod_candidat;
    }

    public String getNume_candidat() {
        return nume_candidat;
    }

    public void setNume_candidat(String nume_candidat) {
        this.nume_candidat = nume_candidat;
    }

    public double getMedie() {
        return medie;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public Map<Integer, List<Integer>> getOptiuni() {
        return optiuni;
    }

    public void setOptiuni(Map<Integer, List<Integer>> optiuni) {
        this.optiuni = optiuni;
    }

    @Override
    public String toString() {
        return "Candidat{" +
                "cod_candidat=" + cod_candidat +
                ", nume_candidat='" + nume_candidat + '\'' +
                ", medie=" + medie +
                ", optiuni=" + optiuni +
                '}' + "\n";
    }


   public List<Candidat> citireJson(String numeFisier){
        List<Candidat> listaCandidat = new ArrayList<>();
        try(FileInputStream fileInputStream = new FileInputStream(numeFisier)) {
            JSONTokener jsonTokener=new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                JSONArray jsonArrayOptiuni = new JSONArray(new JSONTokener(jsonObject.getJSONArray("optiuni").toString()));
                Map<Integer,List<Integer>> map = new HashMap<>();

                for(int j=0; j< jsonArrayOptiuni.length(); j++){ //pentru map
                    JSONObject jsonObjectOptiuneCurenta = jsonArrayOptiuni.getJSONObject(j);
                    map.put(jsonObjectOptiuneCurenta.getInt("cod_liceu"),new ArrayList<>());
                    map.get(jsonObjectOptiuneCurenta.getInt("cod_liceu")).add(jsonObjectOptiuneCurenta.getInt("cod_specializare"));

                }

                Candidat candidat = new Candidat(jsonObject.getInt("cod_candidat"),
                        jsonObject.getString("nume_candidat"), jsonObject.getDouble("media"), map);

                listaCandidat.add(candidat);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


       return listaCandidat;
   }












}//end class
