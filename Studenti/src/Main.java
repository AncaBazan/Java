import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("----1----");
        Student student =new Student();
        List<Student> listaStudenti = student.citireTxt("data\\studenti.txt");
        System.out.println(listaStudenti);

        System.out.println("----2----");
        System.out.println(listaStudenti.size());

        System.out.println("----3----");
        List<Student> listaSortata = student.sortareDescrescatoareDupaNota(listaStudenti);
        System.out.println(listaSortata);

        System.out.println("----4----");
        Student.generareRaportTxt(listaStudenti, "data\\raport.txt");

        Student.generareRaportCuStream(listaStudenti, "data\\raportStream.txt");

        System.out.println("------extra-----");
        List<Student> disciplinaRomana = listaStudenti.stream().filter(x->x.getDisciplina().equals("Romana")).collect(Collectors.toList());
        System.out.println(disciplinaRomana);

        System.out.println("------extra2-----");
        List<Student> studentiCuPrimaLiteraA = listaStudenti.stream().filter(x->x.getNume().substring(0,1).equals("A")).sorted(Comparator.comparing(Student::getNume)).collect(Collectors.toList());
        System.out.println(studentiCuPrimaLiteraA);

        System.out.println("------extra3-----");
        System.out.println(student.medieAritmeticaStudenti(listaStudenti));

        System.out.println("------extra4-----");
        int nrDisciplinePeste5 = (int) listaStudenti.stream().filter(x->x.getNota()>5).count();
        System.out.println(nrDisciplinePeste5);

        System.out.println("------extra5-----");
        List<Student> listStudentiNoataPeste7 = listaStudenti.stream().filter(x->x.getNota()>=7).collect(Collectors.toList());
        System.out.println(listStudentiNoataPeste7);

        System.out.println("------extra6-----");
        List<Student> listStudentiPeste7Descrescator = listaStudenti.stream()
                       .filter(x->x.getNota()>=7).sorted(Comparator.comparing(Student::getNota)
                        .reversed()).collect(Collectors.toList());
        System.out.println(listStudentiPeste7Descrescator );

        System.out.println("------extra7-----");
        List<Student> listaFiltrareSiSortare = listaStudenti.stream()
                .filter(x->x.getNota() >=7).sorted(Comparator.comparing(Student::getNume)
                        .reversed()).collect(Collectors.toList());
        System.out.println(listaFiltrareSiSortare);

    }
}