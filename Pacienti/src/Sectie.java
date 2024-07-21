public class Sectie {
    private int codSectie;
    private String denumire;
    private int nrLocuri;

    public Sectie() {
    }

    public Sectie(int codSectie, String denumire, int nrLocuri) {
        this.codSectie = codSectie;
        this.denumire = denumire;
        this.nrLocuri = nrLocuri;
    }

    public int getCodSectie() {
        return codSectie;
    }

    public void setCodSectie(int codSectie) {
        this.codSectie = codSectie;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    @Override
    public String toString() {
        return "Sectie{" +
                "codSectie=" + codSectie +
                ", denumire='" + denumire + '\'' +
                ", nrLocuri=" + nrLocuri +
                '}'+"\n";
    }
}
