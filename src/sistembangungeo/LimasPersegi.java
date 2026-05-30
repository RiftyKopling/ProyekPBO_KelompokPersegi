package sistembangungeo;

import javax.swing.*;

class LimasPersegi extends Persegi {

    public double tinggi;
    public double tinggiSisi;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public LimasPersegi(double sisi, double tinggi) {
        super(sisi);
        this.tinggi = tinggi;
        super.jenisBangun = "Bangun Ruang";
        super.hitungLuas();
        super.hitungKeliling();
        hitungTinggiSisi(); // ini bisa dihapus
    }
    
    double hitungTinggiSisi() { // ini masih rancu
        tinggiSisi = Math.sqrt((this.tinggi * this.tinggi) + ((super.sisi /2) * (super.sisi /2)));
        return tinggiSisi;
    }
    
    double hitungTinggiSisi(double sisi, double tinggi) { // ini masih rancu
        tinggiSisi = Math.sqrt((tinggi * tinggi) + ((sisi /2) * (sisi /2)));
        return tinggiSisi;
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        luasPermukaan = super.luas + (4 * (super.sisi * this.tinggiSisi) / 2 ); // this.tinggiSisi bisa diganti dengan this.hitungTinggiSisi(), menurutmu gimana kyaz
        return luasPermukaan;
    }
    
    // OVERLOADING
    double hitungLuas(double sisi, double tinggi) {
        luasPermukaan = super.hitungLuas(sisi) + (4 * (sisi * hitungTinggiSisi(sisi, tinggi)) / 2 );
        return luasPermukaan;
    }

    // OVERLOADING METHOD
    double hitungVolume() {
        volume = super.luas * tinggi / 3;
        return volume;
    }

    // OVERLOADING
    double hitungVolume(double sisi, double tinggi) {
        volume = (super.hitungLuas(sisi) * tinggi) / 3;
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
        appendToGUI("\n+ Start geometry thread - " + nomorAntrean + " (Limas Persegi)\n");
        
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
                                  
                                    - [FINISH] Thread - %d (Limas Persegi)
                                        Sisi : %.2f , Tinggi : %.2f
                                        Volume: %.2f
                                        Luas Permukaan: %.2f
                                  """,
                nomorAntrean, this.sisi, this.tinggi, this.volume, this.luasPermukaan
            ));
    }
}