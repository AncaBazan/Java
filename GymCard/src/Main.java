import java.util.Date;

public class Main {
    public static void main(String[] args) {
    MembershipCard membershipCard = new MembershipCard() {
        @Override
        public boolean isExpired() {
            Date dataCurenta = new Date();
            return super.getExpireDate().before(dataCurenta);
        }
      };

    }
}
