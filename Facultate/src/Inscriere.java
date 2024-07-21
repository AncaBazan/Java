import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Inscriere {
    private long cnp_candidat;
    private String nume_candidat;
    private double nota_bacalaureat;
    private int cod_specializare_aleasa;
    private Date data_inscriere;

    public Inscriere() {
    }

    public Inscriere(long cnp_candidat, String nume_candidat, double nota_bacalaureat, int cod_specializare_aleasa, Date data_inscriere) {
        this.cnp_candidat = cnp_candidat;
        this.nume_candidat = nume_candidat;
        this.nota_bacalaureat = nota_bacalaureat;
        this.cod_specializare_aleasa = cod_specializare_aleasa;
        this.data_inscriere = data_inscriere;
    }

    public long getCnp_candidat() {
        return cnp_candidat;
    }

    public void setCnp_candidat(long cnp_candidat) {
        this.cnp_candidat = cnp_candidat;
    }

    public String getNume_candidat() {
        return nume_candidat;
    }

    public void setNume_candidat(String nume_candidat) {
        this.nume_candidat = nume_candidat;
    }

    public double getNota_bacalaureat() {
        return nota_bacalaureat;
    }

    public void setNota_bacalaureat(double nota_bacalaureat) {
        this.nota_bacalaureat = nota_bacalaureat;
    }

    public int getCod_specializare_aleasa() {
        return cod_specializare_aleasa;
    }

    public void setCod_specializare_aleasa(int cod_specializare_aleasa) {
        this.cod_specializare_aleasa = cod_specializare_aleasa;
    }

    public Date getData_inscriere() {
        return data_inscriere;
    }

    public void setData_inscriere(Date data_inscriere) {
        this.data_inscriere = data_inscriere;
    }

    @Override
    public String toString() {
        return "Inscriere{" +
                "cnp_candidat= " + cnp_candidat +
                ", nume_candidat= '" + nume_candidat + '\'' +
                ", nota_bacalaureat= " + nota_bacalaureat +
                ", cod_specializare_aleasa= " + cod_specializare_aleasa +
                ", data_inscriere= " + data_inscriere +
                '}'+"\n";
    }

    public List<Inscriere> citireTxt(String numeFisier){
        List<Inscriere> listaInscrieri = new ArrayList<>();
        try {
            File file = new File(numeFisier);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                var bucati = linie.split(",");

                Long cnp_candidat = Long.parseLong(bucati[0]);
                String nume_candidat = bucati[1];
                Double nota_bacalaureat = Double.parseDouble(bucati[2]);
                int cod_specializare_aleasa = Integer.parseInt(bucati[3]);
                Date data_inscriere =  new SimpleDateFormat("dd.MM.yyyy").parse(bucati[4]);

                Inscriere inscriere = new Inscriere(cnp_candidat, nume_candidat, nota_bacalaureat, cod_specializare_aleasa, data_inscriere);
                listaInscrieri.add(inscriere);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return listaInscrieri;
    }


}//end class
