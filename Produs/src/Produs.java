import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Produs implements Comparable<Produs> {
    private String denumire;
    private float pret;
    private int cantitate;

    private float valoare; 


    public Produs() {
    }

    public Produs(String denumire, float pret, int cantitate, float valoare) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.valoare = valoare;
    }

    public Produs(String denumire, float pret, int cantitate) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return Objects.equals(denumire, produs.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire);
    }

    //2
    public List<Produs>preluareProduse(String numeFisier){
        List<Produs>listaProduse = new ArrayList<>();
        try{
            File file = new File(numeFisier);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                String[] bucati = linie.split(",");

                String denumire = bucati[0].trim();
                float pret = Float.parseFloat(bucati[1].trim());
                int cantitate = Integer.parseInt(bucati[2]);
                float valoare = pret * cantitate;

                Produs produs = new Produs(denumire,pret,cantitate,valoare);
                listaProduse.add(produs);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaProduse;

    }

    public float getValoareTotala(List<Produs>produsList){
        float suma = 0;
        for(Produs produs:produsList){
            suma += produs.pret * produs.cantitate;
        }
    return suma;
    }

//3
    public void getTop3ProduseStream(List<Produs>produsList){
        List<Produs> listaSortata = produsList.stream().sorted(Comparator.comparing(p->p.pret * p.cantitate)).collect(Collectors.toList());
        Collections.reverse(listaSortata);

        for(int i = 0; i < 3; i++){
            System.out.printf("%s - %.2f lei \n",listaSortata.get(i).denumire,listaSortata.get(i).pret * listaSortata.get(i).cantitate);
        }
    }

    @Override
    public int compareTo(Produs o) {
        if(this.valoare > o.valoare){
            return -1;
        } else if (this.valoare == o.valoare) {
            return 0;
        }
        return 1;
    }

    public void getTop3ProduseCompareTo(List<Produs>produsList){
        Collections.sort(produsList);
        for( int i = 0; i < 3; i++){
            System.out.printf("%s - %.2f lei \n",produsList.get(i).denumire,produsList.get(i).valoare);
        }
    }

    //4
    public static void generareFisierTxt(List<Produs>produsList,String caleFisier){
        float sumaPreturi = 0;
        try(FileWriter fileWriter = new FileWriter(caleFisier)){

            for(Produs produs:produsList){
                sumaPreturi+=produs.pret;
            }

            for(Produs produs:produsList){
                float mediePonderata = produs.pret / sumaPreturi * 100;
                fileWriter.write(produs.denumire);
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(produs.pret));
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(produs.cantitate));
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(mediePonderata));
                fileWriter.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

public static void scriereInBinar(List<Produs>produsList, String caleFisier){
        try(DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(caleFisier)))) {

            for(Produs produs:produsList){
                dataOutputStream.writeUTF(produs.denumire);
                dataOutputStream.writeFloat(produs.pret);
                dataOutputStream.writeInt(produs.cantitate);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}

public static List<Produs> citireDinBinar(String caleFisier){
        List<Produs>produsList = new ArrayList<>();
        try(DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(caleFisier)))) {
            while(true) {
                try {
                    String denumire = dataInputStream.readUTF();
                    float pret = dataInputStream.readFloat();
                    int cantitate = dataInputStream.readInt();


                    Produs produs = new Produs(denumire,pret,cantitate);
                    produsList.add(produs);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return produsList;
}

}//end
