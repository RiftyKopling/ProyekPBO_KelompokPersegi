package sistembangungeo;

import javax.swing.*;

class PrismaBujursangkar extends Persegi {

//    public double tinggi;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public PrismaBujursangkar(double sisi) {
        super(sisi);
//        this.tinggi = tinggi; // ini ga kepakai kalau kata pak edo, karena tinggi make dari sisi
        super.jenisBangun = "Bangun Ruang";
        super.hitungLuas();
        super.hitungKeliling();
    }

    @Override
    double hitungLuas() {
        luasPermukaan = 4 * super.luas;
        return luasPermukaan;
    }
    
    double hitungLuas(double sisi) {
        luasPermukaan = 4 * super.hitungLuas(sisi);
        return luasPermukaan;
    }
    
    double hitungVolume() {
        volume = super.luas * super.sisi; // ini masih rancu bisa juga super.sisi * super.sisi * super.sisi
        return volume;
    }

    double hitungVolume(double sisi) {
        volume = super.hitungLuas(sisi) *  sisi; // ini masih rancu bisa juga sisi * sisi * sisi
        return volume;
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
        appendToGUI("\n+ Start geometry thread - " + nomorAntrean + " (Prisma Bujur Sangkar)\n");
        
        Thread threadVolume = new Thread(() -> {
            this.volume = hitungVolume();
        });

        Thread threadLuas = new Thread(() -> {
            this.luasPermukaan = hitungLuas();
        });
        
        threadVolume.start();
        threadLuas.start();
        
        try {
            threadVolume.join();
            threadLuas.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        appendToGUI(String.format("""
                                  
                                    - [FINISH] Thread - %d (Prisma Bujur Sangkar)
                                        Sisi : %.2f
                                        Volume: %.2f
                                        Luas Permukaan: %.2f
                                  """,
                nomorAntrean,this.sisi, this.volume, this.luasPermukaan
        ));
    }
}