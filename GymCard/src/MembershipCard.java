import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class MembershipCard implements ICard, Cloneable, Serializable {
    private static int id;
    private String cardHolder;
    private float loyalPoints;
    private Date expireDate;


    public MembershipCard() {
        id = -1;
        cardHolder="";
        loyalPoints=0;
        expireDate=null;
    }
    public MembershipCard(String cardHolder,float loyalPoints, Date expireDate){
        id++;
        this.cardHolder=cardHolder;
        this.loyalPoints=loyalPoints;
        this.expireDate=expireDate;

        if(this.cardHolder==null||this.loyalPoints<0)
            throw new RuntimeException("Conditii neindeplinite");

    }

    public static int getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public float getLoyalPoints() {
        return loyalPoints;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
        if(this.cardHolder==null)
            throw new RuntimeException("");
    }

    public void setLoyalPoints(float loyalPoints) {
        this.loyalPoints = loyalPoints;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MembershipCard copie = (MembershipCard) super.clone();

        copie.cardHolder=this.cardHolder;
        copie.loyalPoints=this.loyalPoints;
        copie.expireDate=this.expireDate;

        return copie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipCard that = (MembershipCard) o;
        return Float.compare(loyalPoints, that.loyalPoints) == 0 && Objects.equals(cardHolder, that.cardHolder) && Objects.equals(expireDate, that.expireDate);
    }

    @Override
    public int hashCode() { //adresa din memorie
        return Objects.hash(cardHolder, loyalPoints, expireDate);
    }

    @Override
    public String toString() {
        return "MembershipCard{" +
                "cardHolder='" + cardHolder + '\'' +
                ", loyalPoints=" + loyalPoints +
                ", expireDate=" + expireDate +
                '}';
    }


}//end