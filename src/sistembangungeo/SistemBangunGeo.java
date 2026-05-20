package sistembangungeo;

import javax.swing.*;
import java.awt.event.*;

public class SistemBangunGeo extends JFrame {

    // GLOBAL ATTRIBUTE
    private double sisiGlobal = 0;
    // COMPONENT
    JLabel title = new JLabel("SISTEM BANGUN GEO");

    JButton btnPersegi = new JButton("Persegi");

    JButton btnLimas = new JButton("Limas Persegi");

    JButton btnPrisma = new JButton("Prisma Bujur Sangkar");

    JButton btnThread = new JButton("Test Multithreading");

    JTextArea hasil = new JTextArea();

    public SistemBangunGeo() {
        setTitle("Sistem Bangun Geo");
        setSize(650, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // POSITION
        title.setBounds(220, 20, 300, 30);
        btnPersegi.setBounds(50, 80, 220, 40);
        btnLimas.setBounds(330, 80, 220, 40);
        btnPrisma.setBounds(50, 140, 220, 40);
        btnThread.setBounds(330, 140, 220, 40);
        hasil.setBounds(50, 220, 500, 180);
        hasil.setEditable(false);

        // ADD
        add(title);
        add(btnPersegi);
        add(btnLimas);
        add(btnPrisma);
        add(btnThread);
        add(hasil);

        // BUTTON PERSEGI
        btnPersegi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sisiGlobal = Double.parseDouble(JOptionPane.showInputDialog("Masukkan sisi"));
                    Persegi p = new Persegi(sisiGlobal);

                    Thread t = new Thread(p);
                    t.start();
                    t.join();
                    hasil.setText("=== PERSEGI ===\n\n" + "Sisi : " + p.sisi + "\n" + "Luas : " + p.luas + "\n" + "Keliling : " + p.keliling);
                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Input Salah"
                    );
                }
            }
        });

        // BUTTON LIMAS
        btnLimas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (sisiGlobal == 0) {
                        JOptionPane.showMessageDialog(null,"Hitung Persegi dulu!");
                        return;
                    }
                    double tinggi = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi limas"));
                    double tinggiSisi = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi sisi"));

                    LimasPersegi lp = new LimasPersegi(sisiGlobal,tinggi,tinggiSisi);
                    Thread t = new Thread(lp);
                    t.start();
                    t.join();

                    hasil.setText("=== LIMAS PERSEGI ===\n\n" + "Sisi : " + lp.sisi + "\n" + "Luas Alas : " + lp.luas + "\n" + "Keliling Alas : " + lp.keliling + "\n" +
                            "Volume : " + lp.getVolume() + "\n" +
                            "Luas Permukaan : " + lp.getLuasPermukaan()
                    );
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Input Salah"
                    );
                }
            }
        });

        // BUTTON PRISMA
        btnPrisma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (sisiGlobal == 0) {
                        JOptionPane.showMessageDialog(null,"Hitung Persegi dulu!");
                        return;
                    }
                    double tinggi = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi prisma"));

                    PrismaBujursangkar pb = new PrismaBujursangkar(sisiGlobal,tinggi);

                    Thread t = new Thread(pb);
                    t.start();
                    t.join();

                    hasil.setText("=== PRISMA BUJUR SANGKAR ===\n\n" +"Sisi : " + pb.sisi + "\n" +
                            "Luas Alas : " + pb.luas + "\n" +
                            "Keliling Alas : " + pb.keliling + "\n" +
                            "Volume : " + pb.getVolume() + "\n" +
                            "Luas Permukaan : " + pb.getLuasPermukaan()
                    );
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Input Salah");
                }
            }
        });

        // BUTTON MULTITHREADING TEST
        btnThread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Jumlah Thread"));
                    long start = System.currentTimeMillis();
                    for (int i = 1; i <= jumlah; i++) {
                        Persegi p = new Persegi(i);
                        p.setOutputArea(hasil);
                        Thread t =  new Thread(p);
                        t.start();
                    }
                    Thread.sleep(200);
                    long end = System.currentTimeMillis();
                    hasil.setText("=== TEST MULTITHREADING ===\n\n" + "Jumlah Data : " + jumlah + "\n" +
                            "Estimasi Waktu : " + (end - start) + " ms\n"
                    );
                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Input Salah"
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemBangunGeo();
    }
}