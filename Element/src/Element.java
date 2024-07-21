import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Element implements Comparable <Element> {
    private int indexLinie;
    private int indexColoana;
    private double valoareElem;

    public Element() {
    }

    public Element(int indexLinie, int indexColoana, double valoareElem) {
        this.indexLinie = indexLinie;
        this.indexColoana = indexColoana;
        this.valoareElem = valoareElem;
    }

    public int getIndexLinie() {
        return indexLinie;
    }

    public void setIndexLinie(int indexLinie) {
        this.indexLinie = indexLinie;
    }

    public int getIndexColoana() {
        return indexColoana;
    }

    public void setIndexColoana(int indexColoana) {
        this.indexColoana = indexColoana;
    }

    public double getValoareElem() {
        return valoareElem;
    }

    public void setValoareElem(double valoareElem) {
        this.valoareElem = valoareElem;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Element{" +
                "indexLinie=" + indexLinie +
                ", indexColoana=" + indexColoana +
                ", valoareElem=" + valoareElem +
                '}'+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return indexLinie == element.indexLinie && indexColoana == element.indexColoana && Double.compare(valoareElem, element.valoareElem) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexLinie, indexColoana, valoareElem);
    }


    @Override
    public int compareTo(Element o) {
        if(this.valoareElem < o.valoareElem){
            return -1;
        }
        else if (this.valoareElem == o.valoareElem){
            return 0;
        }
        else return 1;
    }


//2
    public List<Element> preluareDate(String numeFisier){
        List<Element> listaElem =new ArrayList<>();
        try{
            File file =new File(numeFisier);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                String []bucati = linie.split(",");
                int indexLinie = Integer.parseInt(bucati[0]);
                int indexColoana = Integer.parseInt(bucati[1]);
                double valElem = Double.parseDouble(bucati[2]);

                Element element = new Element(indexLinie,indexColoana,valElem);
                listaElem.add(element);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaElem;
    }

    public int getNrElemNegative(String numeFisier){
        List<Element> listaElem = preluareDate(numeFisier);
        int contor = 0;
        for(Element element:listaElem){
            if(element.valoareElem < 0){
                contor++;
            }
        }
         return contor;
    }

//4
public void generareFisierTxt(String numeFisier, String fisierOutput){
    List<Element> elementList=preluareDate(numeFisier);
    try(FileWriter fileWriter=new FileWriter(fisierOutput)){
        for(Element element: elementList){
            if(element.indexLinie==element.indexColoana){
                fileWriter.write(String.valueOf(element));
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

//5
    public static void generareFisierBinar(List<Element> listaElem, String numeFisier) throws FileNotFoundException {
        try(DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(numeFisier)))){
            for(Element element:listaElem){
                if(element.indexLinie == element.indexColoana){
                    dataOutputStream.writeDouble(element.valoareElem);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List <Double> citireDinBinar(String numeFisier){
        List<Double> elementeDiagonalaPrincipala = new ArrayList<>();
        try(DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(numeFisier)))){
            while (true){
                try{
                    double valoareElement = dataInputStream.readDouble();
                    elementeDiagonalaPrincipala.add(valoareElement);
                }
                catch (EOFException e){
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return elementeDiagonalaPrincipala;
    }

}//end
