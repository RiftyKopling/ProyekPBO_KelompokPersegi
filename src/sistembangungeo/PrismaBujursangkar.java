package sistembangungeo;

// =========================================

import javax.swing.*;

class PrismaBujursangkar extends Persegi {

    private double tinggi;

    private double volume;
    private double luasPermukaan;
    
    private JTextArea outputArea;

    public PrismaBujursangkar(double sisi, double tinggi) {
        super(sisi);
        this.tinggi = tinggi;
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
        luasPermukaan = (2 * super.luas) + (super.keliling * tinggi);
    }

    @Override
    void hitungKeliling() {
        super.hitungKeliling();
    }

    void hitungVolume() {
        super.hitungLuas();
        volume = super.luas * tinggi;
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
            appendToGUI( "\nThread Volume Prisma : " + Thread.currentThread().getName() + " Volume : " + volume);
        });

        Thread threadLuasPermukaan = new Thread(() -> {
            hitungLuas();
            appendToGUI( "\nThread Luas Permukaan Prisma : " + Thread.currentThread().getName() + " Luas Permukaan : " + luasPermukaan);
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