package sistembangungeo;

abstract class Bangun {

    public String jenisBangun;

    public String getJenisBangun() {
        return jenisBangun;
    }

    // ABSTRACT METHOD
    abstract double hitungLuas();
    abstract double hitungLuas(double sisi);
    abstract double hitungKeliling();
    abstract double hitungKeliling(double sisi);
}