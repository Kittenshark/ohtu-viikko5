
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        taulukonPituudenAsetus(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti >= 0) {
        taulukonPituudenAsetus(kapasiteetti, OLETUSKASVATUS);
        }
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        taulukonPituudenAsetus(kapasiteetti, kasvatuskoko);
    }
    
    public void taulukonPituudenAsetus(int kapasiteetti, int kasvatuskoko) {
        taulukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            return lisaaTaulukkoon(luku);
        }
        return false;
    }
    
    public boolean lisaaTaulukkoon(int luku) {
        taulukko[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm >= taulukko.length) {
            kasvataLiianPientaTaulukkoa();
        }
        return true;
    }
    
    public void kasvataLiianPientaTaulukkoa() {
        int[] taulukkoOld = taulukko;
        taulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, taulukko);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
        return false;
    }
    
    public int haetaanLuvunPaikka(int luku) {
       for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return i;
            }
        }
       return -1;
    }
    
    public void siirraJononAlkiot(int luvunSijainti) {
        int apu = 0;
        for (int i = luvunSijainti; i < alkioidenLkm - 1; i++) {
                apu = taulukko[i];
                taulukko[i] = taulukko[i + 1];
                taulukko[i + 1] = apu;
            }
            alkioidenLkm--;
    }

    public boolean poista(int luku) {
        int luvunSijainti = haetaanLuvunPaikka(luku);
        if (luvunSijainti >= 0) {
            taulukko[luvunSijainti] = 0;
            siirraJononAlkiot(luvunSijainti);
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanhaTaulu, int[] uusiTaulu) {
        for (int i = 0; i < vanhaTaulu.length; i++) {
            uusiTaulu[i] = vanhaTaulu[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }
        if (alkioidenLkm == 1) {
            return "{" + taulukko[0] + "}";
        }
        return jonoMerkkijonoksi();
    }
    
    public String jonoMerkkijonoksi() {
        String merkkijonotuloste = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            merkkijonotuloste += taulukko[i] + ", ";
        }
        return merkkijonotuloste += taulukko[alkioidenLkm - 1] +"}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        x = lisaaJoukkoonArvoja(a, x);
        x = lisaaJoukkoonArvoja(b, x);
        return x;
    }
    
    public static IntJoukko lisaaJoukkoonArvoja(IntJoukko joukko, IntJoukko x) {
        int[] taulu = joukko.toIntArray();
        for (int i = 0; i < taulu.length; i++) {
            x.lisaa(taulu[i]);
        }
        return x;
    }
    
    public static IntJoukko joukkojenLeikkaus(int[] aTaulu, int[] bTaulu, IntJoukko joukko) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    joukko.lisaa(bTaulu[j]);
                }
            }
        }
        return joukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        return joukkojenLeikkaus(aTaulu, bTaulu, joukko);
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        lisaaJoukkoonArvoja(joukko, a);

        return poistaJoukostaArvoja(joukko, b);
    }
    
    public static IntJoukko poistaJoukostaArvoja(IntJoukko joukko, IntJoukko x) {
        int[] taulu = x.toIntArray();
        for (int i = 0; i < taulu.length; i++) {
            joukko.poista(i);
        }
        return joukko;
    }
        
}