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
        luasPermukaan = 4 * super.luas;
        return luasPermukaan;
    }
    
    double hitungLuas(double sisi) {
        luasPermukaan = 4 * super.hitungLuas(sisi);
        return luasPermukaan;
    }
    
    double hitungVolume() {
        volume = super.luas * super.sisi; 
        return volume;
    }

    double hitungVolume(double sisi) {
        volume = super.hitungLuas(sisi) *  sisi; 
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
        appendToGUI("\n+ Start geometry thread - "
                + nomorAntrean + " (Prisma Bujur Sangkar)\n");

        volume = hitungVolume();
        luasPermukaan = hitungLuas();

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