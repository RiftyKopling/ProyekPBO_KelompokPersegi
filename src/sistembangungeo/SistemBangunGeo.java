package sistembangungeo;

import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.event.*;

public class SistemBangunGeo extends JFrame {
    
    // GLOBAL ATTRIBUTE
    private double sisiGlobal = 0;
    LimasPersegi lp;
    PrismaBujursangkar pb;
    Persegi p;
    
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
        JScrollPane scrollHasil = new JScrollPane(hasil);
        scrollHasil.setBounds(50, 220, 500, 180);
        
        // ADD
        add(title);
        add(btnPersegi);
        add(btnLimas);
        add(btnPrisma);
        add(btnThread);
        add(scrollHasil);

        // BUTTON PERSEGI
        btnPersegi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sisiGlobal = Double.parseDouble(JOptionPane.showInputDialog("Masukkan sisi"));
                    p = new Persegi(sisiGlobal);

                    Thread t = new Thread(p);
                    t.start();
                    t.join();
                    hasil.setText("=== PERSEGI / " + p.jenisBangun + " ===\n\n" + "Sisi : " + p.sisi + "\n" + "Luas : " + p.luas + "\n" + "Keliling : " + p.keliling);
                }

                catch (HeadlessException | InterruptedException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Input Salah"
                    );
                }
            }
        });

        // BUTTON LIMAS
        btnLimas.addActionListener(new ActionListener() {
            double tinggiLimas;
            double tinggiSisi;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (sisiGlobal == 0) {
                        JOptionPane.showMessageDialog(null,"Hitung Persegi dulu!");
                        return;
                    }
                    tinggiLimas = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi limas"));
                    tinggiSisi = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi sisi"));

                    lp = new LimasPersegi(sisiGlobal,tinggiLimas,tinggiSisi);
                    Thread t = new Thread(lp);
                    t.start();
                    t.join();

                    hasil.setText("=== LIMAS PERSEGI / " + lp.jenisBangun + " ===\n\n" + "Sisi : " + lp.sisi + "\n" + "Luas Alas : " + lp.luas + "\n" + "Keliling Alas : " + lp.keliling + "\n" +
                            "Volume : " + lp.getVolume() + "\n" +
                            "Luas Permukaan : " + lp.getLuasPermukaan()
                    );
                }
                catch (HeadlessException | InterruptedException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Input Salah"
                    );
                }
            }
        });

        // BUTTON PRISMA
        btnPrisma.addActionListener(new ActionListener() {
            double tinggiPrisma;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (sisiGlobal == 0) {
                        JOptionPane.showMessageDialog(null,"Hitung Persegi dulu!");
                        return;
                    }
                    tinggiPrisma = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi prisma"));

                    pb = new PrismaBujursangkar(sisiGlobal,tinggiPrisma);

                    Thread t = new Thread(pb);
                    t.start();
                    t.join();

                    hasil.setText("=== PRISMA BUJUR SANGKAR / " + pb.jenisBangun + " ===\n\n" +"Sisi : " + pb.sisi + "\n" +
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
                    String input = JOptionPane.showInputDialog("Masukkan Jumlah Proses Multithread:");
                    if (input == null) {
                        return;
                    }
                    int jumlahProses = Integer.parseInt(input);

                    hasil.setText("Running Threads...\n");

                    java.util.Random rand = new java.util.Random();

                    new Thread(() -> {
                        try {
                            for (int i = 1; i <= jumlahProses; i++) {
//                                final int idProses = i;

                                final double randomSisi = 1 + rand.nextInt(20);
                                final double randomTinggi = 1 + rand.nextInt(20);
                                final double randomTinggiSisi = randomTinggi + 2;

                                Thread tPersegi = new Thread(() -> {
                                    Persegi p = new Persegi(randomSisi);
                                    p.setOutputArea(hasil);
                                    p.run();
                                });

                                Thread tPrisma = new Thread(() -> {
                                    PrismaBujursangkar prisma = new PrismaBujursangkar(randomSisi, randomTinggi);
                                    prisma.setOutputArea(hasil);
                                    prisma.run();
                                });

                                Thread tLimas = new Thread(() -> {
                                    LimasPersegi limas = new LimasPersegi(randomSisi, randomTinggi, randomTinggiSisi);
                                    limas.setOutputArea(hasil);
                                    limas.run();
                                });

                                tPersegi.start();
                                tPrisma.start();
                                tLimas.start();

                                Thread.sleep(200);
                                
                                hasil.setCaretPosition(hasil.getDocument().getLength());
                            }

                            hasil.append("\nAll Threads Killed. Perhitungan Selesai!\n");
                            
                            hasil.setCaretPosition(hasil.getDocument().getLength());

                        } catch (InterruptedException ie) {
                            hasil.append("Terjadi interupsi pada thread!\n");
                        }
                        
                    }).start();
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
        SistemBangunGeo sb = new SistemBangunGeo();
        sb.setVisible(true);
        sb.setLocationRelativeTo(null);
        sb.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}