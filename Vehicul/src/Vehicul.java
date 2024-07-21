import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;

public class Vehicul {
    private String nr_inmatriculare;
    private String marca_vehicul;
    private int nr_pasageri;

    private static final double TAXA = 20;

    public Vehicul() {
    }

    public Vehicul(String nr_inmatriculare, String marca_vehicul, int nr_pasageri) {
        this.nr_inmatriculare = nr_inmatriculare;
        this.marca_vehicul = marca_vehicul;
        this.nr_pasageri = nr_pasageri;
    }

    public String getNr_inmatriculare() {
        return nr_inmatriculare;
    }

    public void setNr_inmatriculare(String nr_inmatriculare) {
        this.nr_inmatriculare = nr_inmatriculare;
    }

    public String getMarca_vehicul() {
        return marca_vehicul;
    }

    public void setMarca_vehicul(String marca_vehicul) {
        this.marca_vehicul = marca_vehicul;
    }

    public int getNr_pasageri() {
        return nr_pasageri;
    }

    public void setNr_pasageri(int nr_pasageri) {
        this.nr_pasageri = nr_pasageri;
    }

    @Override
    public String toString() {
        return "Vehicul{" +
                "nr_inmatriculare='" + nr_inmatriculare + '\'' +
                ", marca_vehicul='" + marca_vehicul + '\'' +
                ", nr_pasageri=" + nr_pasageri +
                '}'+"\n";
    }
    //definire eguals pentru cerinta noua 2*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicul vehicul = (Vehicul) o;
        return Objects.equals(marca_vehicul, vehicul.marca_vehicul);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca_vehicul);
    }

    public List <Vehicul> preluareVehicule(String numeFisier){
        List<Vehicul> listaVehicule = new ArrayList<>();
        File file = new File(numeFisier);
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String linie= scanner.nextLine();
                String[] bucati = linie.split(",");
                String nr_inmatriculare=bucati[0];
                String marca=bucati[1];
                int nr_pasageri =Integer.parseInt(bucati[2]);
                Vehicul vehicul=new Vehicul(nr_inmatriculare,marca,nr_pasageri);

                listaVehicule.add(vehicul);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaVehicule;
    }

    public List<Vehicul> ctireJson(String numeFisier){
        List<Vehicul> listaVehicule=new ArrayList<>();
        try(FileInputStream fileInputStream = new FileInputStream(numeFisier)){

            JSONTokener jsonTokener = new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for(int i=0; i< jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Vehicul vehicul = new Vehicul(jsonObject.getString("numarInmatriculare"),jsonObject.getString("marca"),jsonObject.getInt("nrPasageri"));
                listaVehicule.add(vehicul);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaVehicule;
    }

    public boolean esteDeLux(){
        List<String> marciDeLux =new ArrayList<>();
        marciDeLux.add("Mercedes");
        marciDeLux.add("Audi");
        marciDeLux.add("BMW");

        return marciDeLux.contains(marca_vehicul);
    }

    public void generareRaportTXT(List<Vehicul>listaVehicule,String numeFisier){
        Map<String, Double> map = new HashMap<>();
        for(int i =0; i < listaVehicule.size();i++){
            String[] bucati =listaVehicule.get(i).nr_inmatriculare.split("-");
            String judet = bucati[0];

            double valoareTaxa = map.getOrDefault(judet,0.0);
            if(!listaVehicule.get(i).esteDeLux()){
                valoareTaxa += TAXA;
            }
            else{
                valoareTaxa =+ TAXA +(TAXA*0.2);
            }
            map.put(judet,valoareTaxa);
        }
        try(FileWriter fileWriter = new FileWriter(numeFisier)) {

            for(var key:map.entrySet()){
                fileWriter.write(key.getKey().toString());
                fileWriter.write(" : ");
                fileWriter.write(key.getValue().toString());
                fileWriter.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    

}//end
