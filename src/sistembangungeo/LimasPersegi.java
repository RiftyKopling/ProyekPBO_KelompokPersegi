package sistembangungeo;

import javax.swing.*;

class LimasPersegi extends Persegi {

    public double tinggi;
    public double tinggiSisi;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public LimasPersegi(double sisi, double tinggi, double tinggiSisi) {
        super(sisi);
        this.tinggi = tinggi;
        this.tinggiSisi = tinggiSisi;
        this.jenisBangun = "Bangun Ruang";
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        luasPermukaan = super.hitungLuas() + (4 * (0.5 * super.sisi * tinggiSisi));
        return luasPermukaan;
    }

    // OVERLOADING
    @Override
    double hitungLuas(double sisi) {
        return sisi * sisi;
    }

    @Override
    double hitungKeliling() {
        return super.hitungKeliling();
    }

    @Override
    double hitungKeliling(double sisi) {
        return 4 * sisi;
    }

    // OVERLOADING METHOD
    double hitungVolume() {
        volume = super.luas * tinggi / 3;
        return volume;
    }

    // OVERLOADING
    double hitungVolume(double sisi, double tinggi) {
        return (sisi * sisi * tinggi) / 3;
    }

    public double getVolume() {
        return volume;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
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
        Thread threadVolume = new Thread(() -> {
            appendToGUI(
                    "\nThread Volume Limas : "
                    + Thread.currentThread().getName()
                    + " Volume : "
                    + hitungVolume()
            );
        });

        Thread threadLuas = new Thread(() -> {
            appendToGUI(
                    "\nThread Luas Permukaan Limas : "
                    + Thread.currentThread().getName()
                    + " Luas : "
                    + hitungLuas()
            );
        });

        threadVolume.start();
        threadLuas.start();

        try {
            threadVolume.join();
            threadLuas.join();

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}