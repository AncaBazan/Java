import org.json.JSONArray;
import org.json.JSONTokener;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    //1
    public static Map<Integer,Sectie> citireJson(String numeFisier){
        Map<Integer,Sectie> sectie=new HashMap<>();

        try (FileInputStream fileInputStream=new FileInputStream(numeFisier)){
            JSONTokener jsonTokener=new JSONTokener(fileInputStream);
            JSONArray jsonArray=new JSONArray(jsonTokener);
            for(int i=0;i<jsonArray.length();i++){
                var jsonSectie=jsonArray.getJSONObject(i);
                sectie.put(jsonSectie.getInt("cod_sectie"),new Sectie(jsonSectie.getInt("cod_sectie"),
                        jsonSectie.getString("denumire"),jsonSectie.getInt("numar_locuri")));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sectie;
    }
    //2
    public static List<Pacient> citirePacient(String numeFisier){
        List<Pacient>pacientList=new ArrayList<>();
        try {
            File file=new File(numeFisier);
            Scanner scanner=new Scanner(file);

            while (scanner.hasNextLine()){
                String linie=scanner.nextLine();
                String[] bucati=linie.split(",");

                long cnp=Long.parseLong(bucati[0]);
                String nume=bucati[1];
                int varsta=Integer.parseInt(bucati[2]);
                int codSrctie=Integer.parseInt(bucati[3]);

                pacientList.add(new Pacient(cnp,nume,varsta,codSrctie));

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return pacientList;
    }
    public static void main(String[] args) {
        Map<Integer,Sectie> map=new HashMap<>();
        map=citireJson("date\\sectii.json");
        map.entrySet().stream().forEach(x-> System.out.println(x.getValue().toString()));

        System.out.println("\n=======================================\n");

        map.entrySet().stream().filter(x->x.getValue().getNrLocuri()>5)
                .forEach(x-> System.out.println(x.getValue().toString()));

        System.out.println("\n=======================================\n");

        List<Pacient> listaPacienti=citirePacient("Date\\pacienti.txt");
        System.out.println(listaPacienti);

        System.out.println("\n=======================================\n");

        System.out.printf("%s %s %s %s ","cod sectie "," denumire sectie "," numar locuri ", " varsta medie ");

        Map<Integer,List<Pacient>> mapList=listaPacienti.stream().collect(Collectors.groupingBy(Pacient::getCodSetie));

        Map<Integer,PacientPerSectie> pacientPerSectieMap=new HashMap<>();
        for(var i:mapList.entrySet()){
            double medie=0.0;
            for(var pacient:i.getValue()){
                medie+=pacient.getVarsta();
            }
            medie /=i.getValue().size();
            pacientPerSectieMap.put(i.getKey(),new PacientPerSectie(i.getKey(),medie));
        }
        Map<Integer,Sectie> sectiiSortate = map;
        pacientPerSectieMap.entrySet().stream().map(x->x.getValue()).sorted((s1, s2)->Double.compare(s2.getVarstaMedie(),
                s1.getVarstaMedie())).forEach(s->{
            System.out.printf("%d ", s.getCod());

            sectiiSortate.entrySet().stream().filter(y->y.getKey() == s.getCod()).forEach(y->{
                System.out.printf("%s ", y.getValue().getDenumire());
                System.out.printf("%d ", y.getValue().getNrLocuri());
            });
            System.out.println(s.toString());
        });
        System.out.println("------------ex3------------");

        Map<Integer,Integer> nrPacientiPerSectie = new HashMap<>();
        try( FileWriter fileWriter = new FileWriter("raport.txt")) {
            map.entrySet().stream().map(x->x.getValue()).forEach(s->{

                try {
                    fileWriter.write(String.valueOf(s.getCodSectie()));
                    fileWriter.write(" ");
                    fileWriter.write(s.getDenumire());
                    fileWriter.write(" ");

                    mapList.entrySet().stream().filter(x->x.getKey()==s.getCodSectie()).forEach(y-> {
                        try {
                            fileWriter.write(" ");
                            fileWriter.write(String.valueOf(y.getValue().size()));
                            fileWriter.write("\n ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                });

        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

}//end