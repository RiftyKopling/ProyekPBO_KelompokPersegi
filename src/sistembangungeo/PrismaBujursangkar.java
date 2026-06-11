package sistembangungeo;

import javax.swing.*;

class PrismaBujursangkar extends Persegi {

//    public double tinggi;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public PrismaBujursangkar(double sisi) {
        super(sisi);
        super.jenisBangun = "Bangun Ruang";
        super.hitungLuas();
        super.hitungKeliling();
    }

    @Override
    double hitungLuas() {
        this.luasPermukaan = 4 * super.luas;
        return this.luasPermukaan;
    }
    
    double hitungLuas(double sisi) {
        this.luasPermukaan = 4 * super.hitungLuas(sisi);
        return this.luasPermukaan;
    }
    
    double hitungVolume() {
        this.volume = super.luas * super.sisi; 
        return this.volume;
    }

    double hitungVolume(double sisi) {
        this.volume = super.hitungLuas(sisi) *  sisi; 
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
                + nomorAntrean + " (Prisma Bujur Sangkar)\n");

        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuas();

        appendToGUI(String.format("""
                - [FINISH] Thread - %d (Prisma Bujur Sangkar)
                    Sisi : %.2f
                    Volume : %.2f
                    Luas Permukaan : %.2f
                """,
                nomorAntrean,
                sisi,
                volume,
                luasPermukaan
        ));
    }
}