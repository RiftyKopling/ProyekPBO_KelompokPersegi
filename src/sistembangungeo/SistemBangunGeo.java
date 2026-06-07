package sistembangungeo;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * @author Morxidia
 */

public class SistemBangunGeo extends JFrame {

    // GLOBAL ATTRIBUTE
    private double sisiGlobal = 0;
    Bangun bangun;
    Persegi persegi;
    LimasPersegi limasPersegi;
    PrismaBujursangkar prismaBujur;

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
        JScrollPane scrollHasil = new JScrollPane(hasil);
        scrollHasil.setBounds(50, 220, 500, 180);
        hasil.setEditable(false);

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

                    // POLYMORPHISM
                    bangun = new Persegi(sisiGlobal);
                    persegi = (Persegi) bangun;
                    Thread t = new Thread(persegi);
                    t.start();
                    t.join();
                    hasil.setText("=== PERSEGI / " + persegi.jenisBangun + " ===\n\n"
                            + "Sisi : " + persegi.sisi
                            + "\nLuas : " + persegi.hitungLuas()
                            + "\nKeliling : " + persegi.hitungKeliling()
                            + "\nOverloading Luas(10) : " + persegi.hitungLuas(10));

                }
                catch (HeadlessException | InterruptedException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input Salah");
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
                        JOptionPane.showMessageDialog(null, "Hitung Persegi dulu!");
                        return;
                    }
                    tinggiLimas = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi limas"));
                    tinggiSisi = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi sisi"));

                    // POLYMORPHISM
                    bangun = new LimasPersegi(sisiGlobal, tinggiLimas);
                    limasPersegi = (LimasPersegi) bangun;
                    Thread t = new Thread(limasPersegi);
                    t.start();
                    t.join();
                    hasil.setText("=== LIMAS PERSEGI / " + limasPersegi.jenisBangun + " ===\n\n"
                            + "Sisi : " + limasPersegi.sisi
                            + "\nLuas Alas : " + limasPersegi.hitungLuas(limasPersegi.sisi)
                            + "\nKeliling Alas : " + limasPersegi.hitungKeliling()
                            + "\nVolume : " + limasPersegi.hitungVolume()
                            + "\nLuas Permukaan : " + limasPersegi.hitungLuas()
                            + "\nOverloading Volume : " + limasPersegi.hitungVolume(5, 10));

                }
                catch (HeadlessException | InterruptedException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input Salah");
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
                        JOptionPane.showMessageDialog(null, "Hitung Persegi dulu!");
                        return;
                    }
                    tinggiPrisma = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi prisma"));
                    // POLYMORPHISM
                    bangun = new PrismaBujursangkar(sisiGlobal);
                    prismaBujur = (PrismaBujursangkar) bangun;
                    Thread t = new Thread(prismaBujur);
                    t.start();
                    t.join();
                    hasil.setText("=== PRISMA BUJUR SANGKAR / " + prismaBujur.jenisBangun + " ===\n\n"
                            + "Sisi : " + prismaBujur.sisi
                            + "\nLuas Alas : " + prismaBujur.hitungLuas(prismaBujur.sisi)
                            + "\nKeliling Alas : " + prismaBujur.hitungKeliling()
                            + "\nVolume : " + prismaBujur.hitungVolume()
                            + "\nLuas Permukaan : " + prismaBujur.hitungLuas());
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Input Salah");
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

                    new Thread(() -> {
                        int jumlahProses;
                        long startTime, endTime, executionTime;
                        try {
                            startTime = System.nanoTime();
                            
                            jumlahProses = Integer.parseInt(input);
                            hasil.setText("Starting Polymorphic Race...\n----------------------------");

                            java.util.Random rand = new java.util.Random();
                            java.util.List<Persegi> daftarBangun = new java.util.ArrayList<>();

                            // Using polymorphism instead batch excecution
                            for (int i = 1; i <= jumlahProses; i++) {
                                double randomSisi = 1 + rand.nextInt(20);
                                double randomTinggi = 1 + rand.nextInt(20);
                                double randomTinggiSisi = randomTinggi + 2;

                                int pilihan = rand.nextInt(3);

                                Persegi bangunYangDipilih;
                                if (pilihan == 0) {
                                    bangunYangDipilih = new Persegi(randomSisi);
                                } else if (pilihan == 1) {
                                    bangunYangDipilih = new PrismaBujursangkar(randomSisi);
                                } else {
                                    bangunYangDipilih = new LimasPersegi(randomSisi, randomTinggi);
                                }

                                bangunYangDipilih.setOutputArea(hasil);
                                bangunYangDipilih.setNomorAntrean(i);

                                daftarBangun.add(bangunYangDipilih);
                            }

                            java.util.List<Thread> activeThreads = new java.util.ArrayList<>();
                            for (Persegi bangun : daftarBangun) {
                                Thread thread = new Thread(bangun);
                                activeThreads.add(thread);
                                thread.start();
                            }

                            // make the excecution after to be waiting after all the thread is finished
                            for (Thread t : activeThreads) {
                                t.join();
                            }
                            
                            endTime = System.nanoTime();
                            executionTime = endTime - startTime;
                            
                            javax.swing.SwingUtilities.invokeLater(() -> {
                                hasil.append("\n\n----------------------------\nPerhitungan Selesai!");
                                if (executionTime >= 1_000_000_000L) {
                                    double seconds = executionTime / 1_000_000_000.0;
                                    hasil.append(String.format("\nTime execution %.5f s", seconds));

                                } else if (executionTime >= 1_000_000L) {
                                    double milliseconds = executionTime / 1_000_000.0;
                                    hasil.append(String.format("\nTime execution %.2f ms", milliseconds));

                                } else if (executionTime >= 1_000L) {
                                    double microseconds = executionTime / 1_000.0;
                                    hasil.append(String.format("\nTime execution %.2f us", microseconds));

                                } else {
                                    hasil.append(String.format("\nTime execution %d ns", executionTime));
                                }
                                hasil.setCaretPosition(hasil.getDocument().getLength());
                            });

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Input Salah");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        try{
            // Now runs SistemBangunGeo directly instead of View!
            SwingUtilities.invokeLater(() -> {
                new SistemBangunGeo().setVisible(true);
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}