public class Pacient {
    private long cnp;
    private String numePacient;
    private int varsta;
    private int codSetie;

    public Pacient() {
    }

    public Pacient(long cnp, String numePacient, int varsta, int codSetie) {
        this.cnp = cnp;
        this.numePacient = numePacient;
        this.varsta = varsta;
        this.codSetie = codSetie;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getCodSetie() {
        return codSetie;
    }

    public void setCodSetie(int codSetie) {
        this.codSetie = codSetie;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "cnp=" + cnp +
                ", numePacient='" + numePacient + '\'' +
                ", varsta=" + varsta +
                ", codSetie=" + codSetie +
                '}'+"\n";
    }
}
