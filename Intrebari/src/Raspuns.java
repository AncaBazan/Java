import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Raspuns {

    private String numeStudent;
    private int id;
    private String raspuns;

    public Raspuns() {
    }

    public Raspuns(String numeStudent, int id, String raspuns) {
        this.numeStudent = numeStudent;
        this.id = id;
        this.raspuns = raspuns;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaspuns() {
        return raspuns;
    }

    public void setRaspuns(String raspuns) {
        this.raspuns = raspuns;
    }

    @Override
    public String toString() {
        return "Raspuns{" +
                "numeStudent = '" + numeStudent + '\'' +
                ", id = " + id +
                ", raspuns = '" + raspuns + '\'' +
                '}' + "\n";
    }

    public void scriereBD(String cale, List<Raspuns> raspunsuri) {
        try {
            Connection connection = DriverManager.getConnection(cale);
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS RASPUNSURI (nume_student text,id integer,raspuns text)");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RASPUNSURI(nume_student, id, raspuns) VALUES(?, ?, ?)");
            for (Raspuns r : raspunsuri) {
                preparedStatement.setString(1, r.getNumeStudent());
                preparedStatement.setInt(2, r.getId());
                preparedStatement.setString(3, r.getRaspuns());
                preparedStatement.execute();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Raspuns> citireBD(String numeFisier){
        List<Raspuns>listaRaspunsuri = new ArrayList<>();
        try(Connection connection=DriverManager.getConnection(numeFisier)) {
            Statement statement= connection.createStatement();
            ResultSet resultSet =statement.executeQuery("SELECT * FROM RASPUNSURI");

            while(resultSet.next()){
                Raspuns raspuns1 = new Raspuns(resultSet.getString(1),resultSet.getInt(2),
                        resultSet.getString(3));
                listaRaspunsuri.add(raspuns1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaRaspunsuri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Raspuns raspuns = (Raspuns) o;
        return Objects.equals(numeStudent, raspuns.numeStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeStudent);
    }


}//end class