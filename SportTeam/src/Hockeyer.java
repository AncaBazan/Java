import java.util.Objects;

public class Hockeyer extends Human implements IHitpuck,ITackle {

    private boolean IsPremium;
    private String nrTrophy;

    public Hockeyer(String name, int birthday, String nrTrophy) {
        super(name, birthday);
        this.nrTrophy=nrTrophy;
        int trophyValue=Integer.parseInt(nrTrophy);
        if(trophyValue>=2){
            this.IsPremium=true;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "IsPremium=" + IsPremium +
                ", nrTrophy='" + nrTrophy + '\'' +
                '}';
    }

    public boolean isPremium() {
        return IsPremium;
    }

    public void setPremium(boolean premium) {
        IsPremium = premium;
    }

    public String getNrTrophy() {
        return nrTrophy;
    }

    public void setNrTrophy(String nrTrophy) {
        this.nrTrophy = nrTrophy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hockeyer hockeyer = (Hockeyer) o;
        return IsPremium == hockeyer.IsPremium && Objects.equals(nrTrophy, hockeyer.nrTrophy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), IsPremium, nrTrophy);
    }

    @Override
    public void hitpuck() {
        System.out.println("Can Hitpuck ! ");
    }

    @Override
    public void tackle() {
        System.out.println("Can tackle ! ");
    }
}//end
