import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Element element = new Element();
        List<Element> elementList = element.preluareDate("matricerara.csv");
        System.out.println(elementList);


        System.out.println(element.getNrElemNegative("matricerara.csv"));

        element.generareFisierTxt("matricerara.csv","matrice.txt");

        Element.generareFisierBinar(elementList,"diagonal.dat");

        List<Double>elementeDiagonalaPrincipala = Element.citireDinBinar("diagonal.dat");
        System.out.println(elementeDiagonalaPrincipala);
    }
}