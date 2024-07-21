import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Persoana {
    private int cod;
    private String cnp;
    private String nume;

    public Persoana() {
    }

    public Persoana(int cod, String cnp, String nume) {
        this.cod = cod;
        this.cnp = cnp;
        this.nume = nume;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Persoana {" +
                "cod = " + cod +
                ", CNP = '" + cnp + '\'' +
                ", nume = '" + nume + '\'' +
                '}' + "\n";
    }

    public List<Persoana> citierBd (String numeFisier){
        List<Persoana> listaPersoane = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:"+ numeFisier)) {

            Statement statement = connection.createStatement();
            statement.execute("select * from persoane");
            ResultSet resultSet = statement.getResultSet();

            while(resultSet.next()){
                Persoana persoana = new Persoana(resultSet.getInt(1),
                        resultSet.getString(2),resultSet.getString(3));

                listaPersoane.add(persoana);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPersoane;
    }

}//end class
