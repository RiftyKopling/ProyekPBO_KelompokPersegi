package sistembangungeo;

import javax.swing.*;

class PrismaBujursangkar extends Persegi {

    public double tinggi;
    public double volume;
    public double luasPermukaan;

    private JTextArea outputArea;

    public PrismaBujursangkar(double sisi, double tinggi) {
        super(sisi);
        this.tinggi = tinggi;
        this.jenisBangun = "Bangun Ruang";
        super.hitungLuas();
        super.hitungKeliling();
    }

    @Override
    double hitungLuas() {
        luasPermukaan = (2 * super.luas) + (super.keliling * tinggi);
        return luasPermukaan;
    }

    double hitungVolume() {
        volume = super.luas * tinggi;
        return volume;
    }

    double hitungVolume(double sisi, double tinggi) {
        return sisi * sisi * tinggi;
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
        appendToGUI("\n-> Start geometry thread - " + nomorAntrean + " (Prisma)");

        Thread threadVolume = new Thread(() -> {
            appendToGUI(
                    "\n   [FINISH] Thread - " + nomorAntrean + " (Prisma Volume) -> " + hitungVolume()
            );
        });

        Thread threadLuas = new Thread(() -> {
            appendToGUI(
                    "\n   [FINISH] Thread - " + nomorAntrean + " (Prisma Luas) -> " + hitungLuas()
            );
        });

        threadVolume.start();
        threadLuas.start();

        try {
            threadVolume.join();
            threadLuas.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}