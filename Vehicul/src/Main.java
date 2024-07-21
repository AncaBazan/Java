import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Vehicul vehicul = new Vehicul();
        List<Vehicul> listaVehiculeTxt = vehicul.preluareVehicule("date\\parcare.txt");
        System.out.println(listaVehiculeTxt);

        System.out.println("-----------------------");

        List<Vehicul> listaVehiculeJson = vehicul.ctireJson("date\\parcari.json");
        System.out.println(listaVehiculeJson);

        System.out.println("---1---");

        List<Vehicul> vehiculePeste4 = listaVehiculeJson.stream().filter(x->x.getNr_pasageri()>4).collect(Collectors.toList());
        System.out.println(vehiculePeste4);

        System.out.println("---2---");

        List<Vehicul> listaVehiculeUnice = listaVehiculeJson.stream().filter(x->x.getMarca_vehicul().equals("Audi")).distinct().collect(Collectors.toList());
        System.out.println(listaVehiculeUnice);

        System.out.println("---3---");
        int totalNrPasageri = 0;
        for(Vehicul vehicul1:listaVehiculeJson){
            totalNrPasageri += vehicul1.getNr_pasageri();
        }
        float mediePasageri = (float) totalNrPasageri /listaVehiculeJson.size();
        List<Vehicul> listaVehiculSubMedie = listaVehiculeJson.stream().filter(x->x.getNr_pasageri()<mediePasageri).collect(Collectors.toList());
        System.out.println(mediePasageri);
        System.out.println(listaVehiculSubMedie);

        System.out.println("---1poz---");
        int vehiculDeLux = (int) listaVehiculeJson.stream().filter(x-> x.esteDeLux()).count();
        System.out.println(vehiculDeLux);

        System.out.println("---2poz---");
        int nrTotalPsageri = 0;
        for(Vehicul vehicul1:listaVehiculeTxt){
            nrTotalPsageri += vehicul1.getNr_pasageri();
        }
        System.out.printf("%d vehicule cu %d pasageri",listaVehiculeTxt.size(),nrTotalPsageri);

        System.out.println("\n---3poz---");
        int contorDeLux = 0;
        for(Vehicul vehicul1:listaVehiculeTxt){
            if(vehicul1.esteDeLux()){
                contorDeLux += vehicul1.getNr_pasageri();
            }
        }
        int pasageriAlteVehicule = nrTotalPsageri - contorDeLux;
        System.out.printf("Vehicule de lux: %d pasageri \nAlte vehicule: %d pasageri",contorDeLux,pasageriAlteVehicule);


        System.out.println("\n---4poz---");
        vehicul.generareRaportTXT(listaVehiculeTxt,"date\\raportParcare.txt");
        System.out.println("S-a generat fisierul raportParcare.txt");

    }
}

