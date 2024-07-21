import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- citire json---");
        Candidat  candidat = new Candidat();
        List<Candidat> listaCandidati = candidat.citireJson("date\\candidati.json");
        System.out.println(listaCandidati);

        System.out.println("---citire txt---");
        Liceu liceu = new Liceu();
        List<Liceu> listaLicee = liceu.citireTxt("date\\licee.txt");
        System.out.println(listaLicee);

        System.out.println(" ex1 - nr candidati cu medie >= 9");
        int nr_candidati_medie_cel_putin_peste9 = listaCandidati.stream().filter(x->x.getMedie() >= 9).collect(Collectors.toList()).size();
        System.out.println(nr_candidati_medie_cel_putin_peste9);


        System.out.println(" ex2 - lista descrescatoare dupa total de locuri");
        listaLicee.stream().sorted(Comparator.comparingInt(Liceu::getNrTotalLocuti).reversed()).forEach(System.out::println);


        System.out.println(" ex4 - scriere in BD");

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:date\\candidati.db");
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists candidati (cod_candidat Integer, nume_candidat String, medie double, nr_optiuni Integer)");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into candidati(cod_candidat, nume_candidat, medie, nr_optiuni) values(?,?,?,?)");
            for(Candidat candidat1 :listaCandidati){
                preparedStatement.setInt(1,candidat1.getCod_candidat());
                preparedStatement.setString(2,candidat1.getNume_candidat());
                preparedStatement.setDouble(3,candidat1.getMedie());
                preparedStatement.setInt(4,candidat1.getOptiuni().size());

                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}