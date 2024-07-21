import java.util.Objects;

public class Footballer extends Human implements IKickball,ITackle {

    private int nrGoals;
    private double salary;

    public Footballer(String name, int birthday,int nrGoals, double salary) {
        super(name, birthday);
        this.nrGoals=nrGoals;
        this.salary=salary;
    }

    @Override
    public void kickball() {
        System.out.println("Can kick ball ! ");
    }

    @Override
    public void tackle() {
        System.out.println("Can tackle ! ");
    }

    public int getNrGoals() {
        return nrGoals;
    }

    public void setNrGoals(int nrGoals) {
        this.nrGoals = nrGoals;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() +
                "nrGoals=" + nrGoals +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Footballer that = (Footballer) o;
        return nrGoals == that.nrGoals && Double.compare(salary, that.salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nrGoals, salary);
    }


}//end

