import java.util.Objects;

public class Basketballer extends Human implements IDunk, IBodyslam {
    private int nrPoints;
    private TeamPozition teamPozition;

    public Basketballer(String name, int birthday, int nrPoints) {
        super(name, birthday);
        this.nrPoints = nrPoints;
        if (nrPoints >= 1 && nrPoints <= 5) {
            this.teamPozition = TeamPozition.FUNDAS;
        } else if (nrPoints > 5 && nrPoints <= 15) {
            this.teamPozition = TeamPozition.EXTREMA;
        } else this.teamPozition = TeamPozition.ATACANT;

    }

    public int getNrPoints() {
        return nrPoints;
    }

    public void setNrPoints(int nrPoints) {
        this.nrPoints = nrPoints;
    }

    public TeamPozition getTeamPozition() {
        return teamPozition;
    }

    public void setTeamPozition(TeamPozition teamPozition) {
        this.teamPozition = teamPozition;
    }

    @Override
    public String toString() {
        return super.toString() +
                "nrPoints=" + nrPoints +
                ", teamPozition=" + teamPozition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Basketballer that = (Basketballer) o;
        return nrPoints == that.nrPoints && teamPozition == that.teamPozition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nrPoints, teamPozition);
    }

    @Override
    public void bodyslam() {
        System.out.println("Can bodyslam ! ");
    }

    @Override
    public void duck() {
        System.out.println("Can duck ! ");
    }


}//end
