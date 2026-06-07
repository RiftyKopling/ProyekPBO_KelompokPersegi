package sistembangungeo;

import javax.swing.*;

class LimasPersegi extends Persegi implements Runnable {

    public double tinggi;
    // apotema, represent the slant height, or the height of the triangle from pyramid.
    public double apotema;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public LimasPersegi(double sisi, double tinggi) {
        super(sisi);
        this.tinggi = tinggi;
        super.jenisBangun = "Bangun Ruang";
        super.hitungLuas();
        super.hitungKeliling();
        this.hitungTinggiSisi();
    }
    
    double hitungTinggiSisi() { 
        // pytagoras theorem c^2 = squareroot(a^2 + b^2), if you don't know just read the book retard
        // Apotema Alas adalah jarak dari pusat alas ke tengah sisi alas (sisi/2)
        double apotemaAlas = this.sisi/2;
        // Menggunakan Teorema Phytagoras pada segitiga siku-siku yang dibentuk oleh:
        // tinggi limas (A) dan apotema alas (B) untuk mencari tinggi sisi tegak (c)
        double A = Math.pow(this.tinggi,2);
        double B = Math.pow(apotemaAlas, 2);
        apotema = Math.sqrt(A + B);
        return apotema;
    }
    
    double hitungTinggiSisi(double sisi, double tinggi) {
        // pytagoras theorem c^2 = squareroot(a^2 + b^2), if you don't know just read the book retard
        // Apotema Alas adalah jarak dari pusat alas ke tengah sisi alas (sisi/2)
        double apotemaAlas = sisi/2;
        // Menggunakan Teorema Phytagoras pada segitiga siku-siku yang dibentuk oleh:
        // tinggi limas (A) dan apotema alas (B) untuk mencari tinggi sisi tegak (c)
        double A = Math.pow(tinggi,2);
        double B = Math.pow(apotemaAlas, 2);
        apotema = Math.sqrt(A + B);
        return apotema;
    }

    // OVERRIDING
    @Override
    double hitungLuas() {
        double triangleArea =  (sisi * this.apotema) / 2;
        double luasSelimut = 4 * triangleArea;
        luasPermukaan = super.luas + luasSelimut; // this.tinggiSisi bisa diganti dengan this.hitungTinggiSisi(), menurutmu gimana kyaz
        return luasPermukaan;
    }
    
    // OVERLOADING
    double hitungLuas(double sisi, double tinggi) {
        double apotemaLine =  hitungTinggiSisi(sisi, tinggi);
        double triangleArea =  (sisi * apotemaLine) / 2;
        double luasSelimut = 4 * triangleArea;
        luasPermukaan = super.hitungLuas(sisi) + luasSelimut;
        return luasPermukaan;
    }

    // OVERLOADING METHOD
    double hitungVolume() {
        volume = super.luas * tinggi / 3;
        return volume;
    }

    // OVERLOADING
    double hitungVolume(double sisi, double tinggi) {
        double tempLuasAlas = super.hitungLuas(sisi);
        volume = (tempLuasAlas * tinggi) / 3;
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
                                        Sisi : %.2f , Tinggi : %.2f, apotema: %.2f
                                        Volume: %.2f
                                        Luas Permukaan: %.2f
                                  """,
                nomorAntrean, this.sisi, this.tinggi, this.apotema, this.volume, this.luasPermukaan
            ));
    }
}