import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Specializare {
    private int cod;
    private String denumire;
    private int locuri;

    public Specializare() {
    }

    public Specializare(int cod, String denumire, int locuri) {
        this.cod = cod;
        this.denumire = denumire;
        this.locuri = locuri;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getLocuri() {
        return locuri;
    }

    public void setLocuri(int locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString() {
        return "Specializare{" +
                "cod= " + cod +
                ", denumire= '" + denumire + '\'' +
                ", locuri= " + locuri +
                '}'+ "\n";
    }

    public List<Specializare> citireBD(String numeFisier){
        List<Specializare> listaSpecializare = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(numeFisier);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select* from specializari");

            while(resultSet.next()){
                Specializare specializare = new Specializare(resultSet.getInt(1),
                        resultSet.getString(2),resultSet.getInt(3));

                listaSpecializare.add(specializare);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaSpecializare;
    }
    

}//end class
