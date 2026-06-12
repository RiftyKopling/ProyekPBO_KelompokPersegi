package sistembangungeo;

import javax.swing.*;

class Persegi extends Bangun implements Runnable {

    public double sisi;
    public double luas;
    public double keliling;

    private JTextArea outputArea;
    
    public int nomorAntrean;

    // CONSTRUCTOR
    public Persegi(double sisi) {
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi tidak boleh <= 0");
        }
        this.sisi = sisi;
        super.jenisBangun = "Bangun Datar";
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        this.luas = this.sisi * this.sisi;
        return this.luas;
    }

    // OVERLOADING
    @Override
    double hitungLuas(double sisi) {
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi tidak boleh <= 0");
        }
        this.luas = sisi * sisi;
        return this.luas;
    }

    @Override
    double hitungKeliling() {
        this.keliling = 4 * this.sisi;
        return this.keliling;
    }

    @Override
    double hitungKeliling(double sisi) {
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi tidak boleh <= 0");
        }
        this.keliling = 4 * sisi;
        return this.keliling;
    }

    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }
    
    public void setNomorAntrean(int nomorAntrean) {
        this.nomorAntrean = nomorAntrean;
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
                + nomorAntrean + " (Persegi)\n");

        this.luas = hitungLuas();
        this.keliling = hitungKeliling();

        appendToGUI(String.format("""
                - [FINISH] Thread - %d (Persegi)
                    Sisi : %.2f
                    Luas : %.2f
                    Keliling : %.2f
                """,
                nomorAntrean,
                sisi,
                luas,
                keliling));
    }
}
