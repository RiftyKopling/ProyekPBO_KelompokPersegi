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
        this.jenisBangun = "Bangun Datar";
    }

    // OVERLOADING CONSTRUCTOR
    public Persegi() {
        this.sisi = 1;
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        luas = sisi * sisi;
        return luas;
    }

    // OVERLOADING
    @Override
    double hitungLuas(double sisi) {
        return sisi * sisi;
    }

    @Override
    double hitungKeliling() {
        keliling = 4 * sisi;
        return keliling;
    }

    @Override
    double hitungKeliling(double sisi) {
        return 4 * sisi;
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
        // 1. Announce start immediately
        appendToGUI("\n-> Start geometry thread - " + nomorAntrean + " (Persegi)");

        Thread threadLuas = new Thread(() -> {
            appendToGUI(
                    "\n   [FINISH] Thread - " + nomorAntrean + " (Persegi Luas) -> " + hitungLuas()
            );
        });
        Thread threadKeliling = new Thread(() -> {
            appendToGUI(
                    "\n   [FINISH] Thread - " + nomorAntrean + " (Persegi Keliling) -> " + hitungKeliling()
            );
        });

        threadLuas.start();
        threadKeliling.start();

        try {
            threadLuas.join();
            threadKeliling.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}