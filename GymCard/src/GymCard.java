import java.util.Date;

public class GymCard extends MembershipCard{

    enum CardType{
      BRONZ, SILVER, GOLD
    }
private CardType membershipType;

    public GymCard() {
        super();
        this.membershipType=null;
    }

    public GymCard(String cardHolder, float loyalPoints, Date expireDate, CardType membershipType) {
        super(cardHolder, loyalPoints, expireDate);
        this.membershipType = membershipType;
    }

    public CardType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(CardType membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        GymCard copie=(GymCard) super.clone();
        copie.membershipType = this.membershipType;
        return copie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GymCard gymCard = (GymCard) o;
        if(super.equals(gymCard) && this.membershipType.equals(gymCard.membershipType)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int nrPrim = 7;
        int rezultat = super.hashCode();
        //operator ternar
        rezultat = nrPrim * rezultat * ((membershipType==null)? 0 :membershipType.hashCode());
        return rezultat;
    }


    @Override
    public boolean isExpired() {
        //obtinem data
        Date dataCurenta = new Date();

        return super.getExpireDate().before(dataCurenta);

    }

    @Override
    public String toString() {
        return "GymCard{" +
                "membershipType=" + membershipType +
                '}';
    }


}
