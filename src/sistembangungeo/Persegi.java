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
        appendToGUI("\n+ Start geometry thread - " + nomorAntrean + " (Persegi)\n");

        /*
            Uncoment the delay for make the thread have it's computational time
            but it will remove the finish Interuption when in the caller list
            so no interupt in the caller list
         */
//        try {
//            Thread.sleep((long) (Math.random() * 900) + 100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        
        Thread threadVolume = new Thread(() -> {
            this.luas = this.hitungLuas();
        });

        Thread threadLuas = new Thread(() -> {
            this.keliling = this.hitungKeliling();
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
                                  
                                    - [FINISH] Thread - %d (Persegi)
                                        Sisi : %.2f
                                        Luas: %.2f
                                        Keliling: %.2f
                                  """,
                nomorAntrean, this.sisi, this.luas, this.keliling
        ));
    }
}