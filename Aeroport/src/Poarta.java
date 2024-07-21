import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Poarta {
    private int id;
    private String numePoarta;
    private float valoare;
    private Status status;
    private Date date;


    public Poarta() {
    }

    public Poarta(int id, String numePoarta, float valoare, Status status, Date date) {
        this.id = id;
        this.numePoarta = numePoarta;
        this.valoare = valoare;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumePoarta() {
        return numePoarta;
    }

    public void setNumePoarta(String numePoarta) {
        this.numePoarta = numePoarta;
    }

    public float getValoare() {
        return valoare;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
        return "Poarta{" +
                "id = " + id +
                ", numePoarta = " + numePoarta +
                ", valoare = " + valoare +
                ", status = " + status +
                ", date = " + date +
                '}' + "\n";
    }

    public List<Poarta> citireTxt(String numeFisier){
        List<Poarta> listaPorti = new ArrayList<>();
        File file =new File(numeFisier);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                var bucati = linie.split(";");

                int id = Integer.parseInt(bucati[0]);
                String numePoarta = bucati[1];
                float valoare = Float.parseFloat(bucati[2]);
                Status status = Status.valueOf(bucati[3]);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bucati[4]);

                Poarta poarta = new Poarta(id, numePoarta,valoare,status,date);
                listaPorti.add(poarta);


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return listaPorti;
    }












}//end class
