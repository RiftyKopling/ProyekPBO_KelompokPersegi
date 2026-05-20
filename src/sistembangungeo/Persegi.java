package sistembangungeo;

import javax.swing.*;

class Persegi extends Bangun implements Runnable {

    public double sisi;
    public double luas;
    public double keliling;
    
    private JTextArea outputArea;

    public Persegi(double sisi) {
        this.sisi = sisi;
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

    @Override
    void hitungLuas() {
        luas = sisi * sisi;
    }

    @Override
    void hitungKeliling() {
        keliling = 4 * sisi;
    }

    @Override
    public void run() {
        Thread threadLuas =
                new Thread(() -> {
                    hitungLuas();
                    String pesan = "Thread Luas : " + Thread.currentThread().getName() + "\n";
                    System.out.println(pesan);
                    appendToGUI(pesan); // Output ke GUI
                });

        Thread threadKeliling =
                new Thread(() -> {
                    hitungKeliling();
                    String pesan = "Thread Keliling : " + Thread.currentThread().getName() + "\n";
                    System.out.println(pesan);
                    appendToGUI(pesan);
                });

        threadLuas.start();
        threadKeliling.start();

        try {
            threadLuas.join();
            threadKeliling.join();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}