import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FootballSportTeam {
    private String name;
    private String description;

    private List<Footballer> ListFootballers=new ArrayList<>();

    public FootballSportTeam(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Footballer> getListFootballers() {
        return ListFootballers;
    }

    public void setListFootballers(List<Footballer> listFootballers) {
        ListFootballers = listFootballers;
    }

    @Override
    public String toString() {
        return "FootballSportTeam{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ListFootballers=" + ListFootballers +
                '}';
    }

    public boolean addFootballer(Footballer footballer){
        if(ListFootballers.contains(footballer)){
            System.out.println("Fotbalistul deja exista in lista");
            return false;
        }
        ListFootballers.add(footballer);
        System.out.println("Fotbalistul a fost adaugat cu succes");
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballSportTeam that = (FootballSportTeam) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(ListFootballers, that.ListFootballers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ListFootballers);
    }


}//end
