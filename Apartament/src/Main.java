

public class Main {
    public static void main(String[] args) {

        Apartament apartament = new Apartament();

        apartament.citesteUnFisier("imobil.txt");


        System.out.println("Total suprafata imobil: " + apartament.calculSuprafataTotala());
        System.out.println("Total numar persoane: " + apartament.calculTotalPersoane());

        apartament.afisareNrCamere();

        System.out.println("------cerinta 3------");
        apartament.afisarePersoanePeTipApartament();

        apartament.generareFisierText();

    }
}