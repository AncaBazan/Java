public class PacientPerSectie {
    private int cod;
    private double varstaMedie;

    public PacientPerSectie() {
    }

    public PacientPerSectie(int cod, double varstaMedie) {
        this.cod = cod;
        this.varstaMedie = varstaMedie;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getVarstaMedie() {
        return varstaMedie;
    }

    public void setVarstaMedie(double varstaMedie) {
        this.varstaMedie = varstaMedie;
    }

    @Override
    public String toString() {
        return "PacientPerSectie{" +
                "cod=" + cod +
                ", varstaMedie=" + varstaMedie +
                '}'+"\n";
    }
}
