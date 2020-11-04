package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna, krajnja;
    boolean pocetna_pripada, krajnja_pripada;

    public Interval(double p, double k,boolean p_pripada,boolean k_pripada){
        if(p>k) throw new IllegalArgumentException ("Pocetna tacka je veca od krajnje");
        pocetna=p; krajnja=k; pocetna_pripada=p_pripada; krajnja_pripada=k_pripada;
    }

    public Interval(){
        pocetna=0;
        krajnja=0;
        pocetna_pripada=false;
        krajnja_pripada=false;
    }

    public boolean isNull(){
        if(pocetna==0 && krajnja==0 && !pocetna_pripada && !krajnja_pripada) return true;
        return false;
    }

    public boolean isIn(double tacka){
        if((tacka > pocetna || Math.abs(tacka-pocetna)<0.00001) && (
                tacka < krajnja || Math.abs(tacka-pocetna)<0.00001)) return true;
        return false;
    }

    public Interval intersect(Interval i){
        if(i.pocetna>this.krajnja || this.pocetna>i.krajnja) return new Interval();
        Interval i1 = new Interval(Math.max(i.pocetna,this.pocetna),Math.min(i.krajnja,this.krajnja),
                false,false);

        if(this.pocetna > i.pocetna) i1.pocetna_pripada=this.pocetna_pripada;
        else i1.pocetna_pripada=i.pocetna_pripada;

        if(this.krajnja < i.krajnja) i1.krajnja_pripada=this.krajnja_pripada;
        else i1.krajnja_pripada=i.krajnja_pripada;

        return i1;
    }

    public static Interval intersect(Interval i1, Interval i2){
        return i1.intersect(i2);
    }

    @Override
    public String toString() {
        if(pocetna_pripada && krajnja_pripada) return "[" + pocetna + "," + krajnja + "]";
        else if(pocetna_pripada && !krajnja_pripada) return "[" + pocetna + "," + krajnja + ")";
        else if(!pocetna_pripada && krajnja_pripada) return "(" + pocetna + "," + krajnja + "]";
        else if(isNull()) return "()";
        return "(" + pocetna + "," + krajnja + ")";
    }

    @Override
    public boolean equals(Object o) {
        Interval i = (Interval) o;
        if(pocetna_pripada == i.pocetna_pripada && krajnja_pripada == i.krajnja_pripada
        && Math.abs(pocetna-i.pocetna)<0.00001 && Math.abs(krajnja-i.krajnja)<0.00001) return true;

        else return false;
    }
}
