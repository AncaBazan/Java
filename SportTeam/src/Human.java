import java.util.Objects;

public abstract class Human {
    private String name;
    private int birthday;

    public Human(String name, int birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return birthday == human.birthday && Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

}//end

