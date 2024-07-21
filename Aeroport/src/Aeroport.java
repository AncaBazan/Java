import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aeroport {
    private int id;
    private float value;
    private String name;
    private boolean status;
    private Date date;

    public Aeroport() {
    }

    public Aeroport(int id, float value, String name, boolean status, Date date) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "id = " + id +
                ", value = " + value +
                ", name = '" + name + '\'' +
                ", status = " + status +
                ", date = " + date +
                '}' +"\n";
    }

    public List<Aeroport> citireJson(String numeFisier){
        List<Aeroport> listaAeroporturi = new ArrayList<>();
        try(FileInputStream fileInputStream = new FileInputStream(numeFisier)) {
            JSONTokener jsonTokener=new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String dataExtrasa = jsonObject.getString("date");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(dataExtrasa); //+catch
                Aeroport aeroport=new Aeroport(jsonObject.getInt("id"), jsonObject.getFloat("value"),
                        jsonObject.getString("name"), jsonObject.getBoolean("status"),date); //date de la 94
                listaAeroporturi.add(aeroport);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return listaAeroporturi;
    }

}//end class
