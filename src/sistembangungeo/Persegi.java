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
        this.sisi = sisi;
        super.jenisBangun = "Bangun Datar";
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        luas = this.sisi * this.sisi;
        return luas;
    }

    // OVERLOADING
    @Override
    double hitungLuas(double sisi) {
        luas = sisi * sisi;
        return luas;
    }

    @Override
    double hitungKeliling() {
        keliling = 4 * this.sisi;
        return keliling;
    }

    @Override
    double hitungKeliling(double sisi) {
        keliling = 4 * sisi;
        return keliling;
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

        luas = hitungLuas();
        keliling = hitungKeliling();

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
