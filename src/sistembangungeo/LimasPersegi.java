package sistembangungeo;

import javax.swing.*;

class LimasPersegi extends Persegi {

    private double tinggi;
    private double tinggiSisi;
    private double volume;
    private double luasPermukaan;
    
    private JTextArea outputArea;

    public LimasPersegi(double sisi,double tinggi,double tinggiSisi) {
        super(sisi);
        this.tinggi = tinggi;
        this.tinggiSisi = tinggiSisi;
        this.jenisBangun = "Bangun Ruang";
    }

    public double getVolume() {
        return volume;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    // POLYMORPHISM
    @Override
    void hitungLuas() {
        super.hitungLuas();
        super.hitungKeliling();
        luasPermukaan = super.luas + (4 * (0.5 * super.sisi * tinggiSisi));
    }

    @Override
    void hitungKeliling() {
        super.hitungKeliling();
    }

    void hitungVolume() {
        super.hitungLuas();
        volume = (super.luas * tinggi) / 3;
    }
    
    // Tambahkan setter
    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    // Method helper untuk append text ke GUI
    private void appendToGUI(String text) {
        if (outputArea != null) {
            SwingUtilities.invokeLater(() -> {
                outputArea.append(text);
            });
        }
    }
    
    // MULTITHREADING
    @Override
    public void run() {
        Thread threadVolume = new Thread(() -> {
            hitungVolume();
            appendToGUI("\nThread Volume Limas : " + Thread.currentThread().getName() + " Volume : " + volume);
        });

        Thread threadLuasPermukaan = new Thread(() -> {
            hitungLuas();
            appendToGUI("\nThread Luas Permukaan Limas : " + Thread.currentThread().getName() + " Luas Permukaan : " + luasPermukaan);
        });

        threadVolume.start();
        threadLuasPermukaan.start();

        try {
            threadVolume.join();
            threadLuasPermukaan.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}