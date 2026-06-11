package sistembangungeo;

import javax.swing.*;

class LimasPersegi extends Persegi implements Runnable {

    public double tinggi;
    public double apotema;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public LimasPersegi(double sisi, double tinggi) {
        super(sisi);
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi tidak boleh <= 0");
        }
        this.tinggi = tinggi;
        super.jenisBangun = "Bangun Ruang";
        super.hitungLuas();
        super.hitungKeliling();
        this.hitungTinggiSisi();
    }
    
    double hitungTinggiSisi() { 
        this.apotema = Math.sqrt(Math.pow(this.tinggi,2) + Math.pow(this.sisi/2, 2));
        return this.apotema;
    }
    
    double hitungTinggiSisi(double sisi, double tinggi) {
        this.apotema = Math.sqrt(Math.pow(sisi/2, 2) + Math.pow(tinggi,2));
        return this.apotema;
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        this.luasPermukaan = super.luas + (4 * ((super.sisi * this.apotema) / 2)); // this.tinggiSisi bisa diganti dengan this.hitungTinggiSisi(), menurutmu gimana kyaz
        return this.luasPermukaan;
    }
    
    // OVERLOADING
    double hitungLuas(double sisi, double tinggi) {
        this.luasPermukaan = super.hitungLuas(sisi) + (4 * ((sisi * this.hitungTinggiSisi(sisi, tinggi)) / 2));
        return this.luasPermukaan;
    }

    // OVERLOADING METHOD
    double hitungVolume() {
        this.volume = super.luas * this.tinggi / 3;
        return this.volume;
    }

    // OVERLOADING
    double hitungVolume(double sisi, double tinggi) {
        this.volume = (super.hitungLuas(sisi) * tinggi) / 3;
        return this.volume;
    }

    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    private void appendToGUI(String text) {
        if (outputArea != null) {
            SwingUtilities.invokeLater(() -> {
                outputArea.append(text);
            });
        }
    }

    @Override
    public void run() {
        appendToGUI("\n+ Start geometry thread - "
                + nomorAntrean + " (Limas Persegi)\n");

        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuas();

        appendToGUI(String.format("""

                    - [FINISH] Thread - %d (Limas Persegi)
                        Sisi : %.2f
                        Tinggi : %.2f
                        Apotema : %.2f
                        Volume : %.2f
                        Luas Permukaan : %.2f
                """,
                nomorAntrean,
                this.sisi,
                this.tinggi,
                this.apotema,
                this.volume,
                this.luasPermukaan
        ));
    }
}