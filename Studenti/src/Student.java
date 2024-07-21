import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class Student {
    private String nume;
    private String disciplina;
    private int nota;

    public Student() {
    }

    public Student(String nume, String disciplina, int nota) {
        this.nume = nume;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", nota=" + nota +
                '}' + "\n";
    }

    public List<Student> citireTxt(String numeFisier){
        List<Student> listaStudenti = new ArrayList<>();
        File file = new File(numeFisier);
        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String linie = scanner.nextLine();
                var bucati = linie.split(",");

                String nume =bucati[0];
                String disciplina=bucati[1];
                int nota = Integer.parseInt(bucati[2]);

                Student student = new Student(nume, disciplina, nota);
                listaStudenti.add(student);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaStudenti;
    }
//3
    public List<Student> sortareDescrescatoareDupaNota(List<Student>listaStudenti){
        List<Student> listaSortata = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String disciplinaCitita = scanner.nextLine();

        for(Student s: listaStudenti ){
            if(s.disciplina.equals(disciplinaCitita)) {
                listaSortata.add(s);
            }
        }
        return listaSortata.stream().sorted(Comparator.comparing(Student::getNota).reversed()).collect(Collectors.toList());
    }

    public static void generareRaportTxt(List<Student>listaStudenti, String numeFisier) {

        Map<String, Integer> map = new HashMap<>();
        try(FileWriter fileWriter = new FileWriter(numeFisier)){

            for(int i = 0; i < listaStudenti.size(); i++){
                String disciplina = listaStudenti.get(i).disciplina;
                int nrAparitiiNote = map.getOrDefault(disciplina,0);

                if(disciplina.equals(listaStudenti.get(i).disciplina)){
                    nrAparitiiNote++;
                }
                map.put(disciplina, nrAparitiiNote);
            }

            for(var x:map.entrySet()){
                fileWriter.write(x.getKey().toString());
                fileWriter.write(" : ");
                fileWriter.write(x.getValue().toString());
                fileWriter.write("\n");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generareRaportCuStream(List<Student> listaStudenti, String numeFisier){
        try(FileWriter fileWriter = new FileWriter(numeFisier)) {
            Map<String, Long> map = listaStudenti.stream().collect(Collectors.groupingBy(Student::getDisciplina, Collectors.counting()));

            for(var x:map.entrySet()){
                fileWriter.write(x.getKey().toString());
                fileWriter.write(" : ");
                fileWriter.write(x.getValue().toString());
                fileWriter.write("\n");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

public double medieAritmeticaStudenti(List<Student> listaStudenti) {

    List<Student> listaSortata = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String disciplinaCitita = scanner.nextLine();

    for (Student s : listaStudenti) {
        if (s.disciplina.equals(disciplinaCitita)) {
            listaSortata.add(s);
        }
    }
    int sumaNote = listaSortata.stream().mapToInt(Student::getNota).sum();
    double medieAritmetica = sumaNote / listaSortata.size();
    return medieAritmetica;

}

}//end

