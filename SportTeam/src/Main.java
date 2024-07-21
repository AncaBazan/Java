
public class Main {
    public static void main(String[] args) {
        Footballer footballer = new Footballer("Ionel",1998,20,2000);
        footballer.kickball();
        footballer.tackle();
        System.out.println(footballer);

        Basketballer basketballer = new Basketballer("Mihai",2000,15);
        basketballer.bodyslam();
        basketballer.duck();
        System.out.println(basketballer);

        Hockeyer hockeyer = new Hockeyer("Bogdan",2001,"2");
        hockeyer.hitpuck();
        hockeyer.tackle();
        System.out.println(hockeyer);

    }


}
