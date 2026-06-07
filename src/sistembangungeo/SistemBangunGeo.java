package sistembangungeo;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * @author Morxidia
 */

public class SistemBangunGeo extends JFrame {

    // GLOBAL ATTRIBUTE


    public SistemBangunGeo() {
        // deprecated, all Bangun instance is move to it's dedicated page
        
        // Bangun bangun;

        // COMPONENT
        JLabel title = new JLabel("SISTEM BANGUN GEO");
        JButton btnPersegi = new JButton("Persegi");
        JButton btnLimas = new JButton("Limas Persegi");
        JButton btnPrisma = new JButton("Prisma Bujur Sangkar");
        JButton btnThread = new JButton("Test Multithreading");
        JTextArea hasil = new JTextArea();

        JPanel progressPanel = new JPanel();
        JScrollPane progressScroll;

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
        
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
        progressPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        progressScroll = new JScrollPane(progressPanel);
        progressScroll.setBounds(570, 20, 350, 380);
        progressScroll.setVisible(false); // Sembunyikan di awal

        // ADD
        add(title);
        add(btnPersegi);
        add(btnLimas);
        add(btnPrisma);
        add(btnThread);
        add(scrollHasil);
        add(progressScroll);
        
        // using array for making sisiGLobal work, hide it from Charlibaldi
        double[] sisiGlobal = new double[1];
        sisiGlobal[0] = 0;

        // BUTTON PERSEGI
        btnPersegi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTampilan(progressScroll);
                try {
                    
                    Double sisiPersegi = Double.parseDouble(JOptionPane.showInputDialog("Masukkan sisi"));
                    sisiGlobal[0] = sisiPersegi;
                    // POLYMORPHISM
                    Bangun bangun = new Persegi(sisiPersegi);
                    Persegi persegi = (Persegi) bangun;
                    Thread t = new Thread(persegi);
                    t.start();
                    t.join();
                    hasil.setText("=== PERSEGI / " + persegi.jenisBangun + " ===\n\n"
                            + "Sisi : " + persegi.sisi
                            + "\nLuas : " + persegi.hitungLuas()
                            + "\nKeliling : " + persegi.hitungKeliling());

                }
                catch (HeadlessException | InterruptedException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input Salah");
                }
            }
        });

        // BUTTON LIMAS
        btnLimas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTampilan(progressScroll);
                try {
                    if (sisiGlobal[0] == 0) {
                        JOptionPane.showMessageDialog(null, "Hitung Persegi dulu!");
                        return;
                    }
                    Double tinggiLimas = Double.parseDouble(JOptionPane.showInputDialog("Masukkan tinggi limas"));

                    // POLYMORPHISM
                    Bangun bangun = new LimasPersegi(sisiGlobal[0], tinggiLimas);
                    LimasPersegi limasPersegi = (LimasPersegi) bangun;
                    Thread t = new Thread(limasPersegi);
                    t.start();
                    t.join();
                    hasil.setText("=== LIMAS PERSEGI / " + limasPersegi.jenisBangun + " ===\n\n"
                            + "Sisi : " + limasPersegi.sisi
                            + "\nLuas Alas : " + limasPersegi.hitungLuas(limasPersegi.sisi)
                            + "\nKeliling Alas : " + limasPersegi.hitungKeliling()
                            + "\nVolume : " + limasPersegi.hitungVolume()
                            + "\nLuas Permukaan : " + limasPersegi.hitungLuas());

                }
                catch (HeadlessException | InterruptedException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input Salah");
                }
            }
        });

        // BUTTON PRISMA
        btnPrisma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTampilan(progressScroll);
                try {
                    if (sisiGlobal[0] == 0) {
                        JOptionPane.showMessageDialog(null, "Hitung Persegi dulu!");
                        return;
                    }
                    // POLYMORPHISM
                    Bangun bangun = new PrismaBujursangkar(sisiGlobal[0]);
                    PrismaBujursangkar prismaBujur = (PrismaBujursangkar) bangun;
                    Thread t = new Thread(prismaBujur);
                    t.start();
                    t.join();
                    hasil.setText("=== PRISMA BUJUR SANGKAR / " + prismaBujur.jenisBangun + " ===\n\n"
                            + "Sisi : " + prismaBujur.sisi
                            // using new instance for not make mistake of luasPermukaan and luasAlas, sure hide it from Charlibaldi
                            + "\nLuas Alas : " + (new Persegi(sisiGlobal[0])).hitungLuas(sisiGlobal[0])
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
                    if (input == null || input.trim().isEmpty()) {
                        return;
                    }
                    int jumlahProses = Integer.parseInt(input);

                    // Ekspansi Window untuk memunculkan Progress Panel
                    setSize(980, 500);
                    progressPanel.removeAll();
                    progressScroll.setVisible(true);

                    new Thread(() -> {
                        long startTime, endTime, executionTime;
                        try {
                            startTime = System.nanoTime();
                            
                            hasil.setText("Starting Polymorphic Race...\n----------------------------");

                            java.util.Random rand = new java.util.Random();
                            java.util.List<Thread> activeThreads = new java.util.ArrayList<>();

                            for (int i = 1; i <= jumlahProses; i++) {
                                final int nomorAntrean = i;
                                
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
                                bangunYangDipilih.setNomorAntrean(nomorAntrean);

                                // Siapkan Progress Bar untuk thread ini
                                JProgressBar pBar = new JProgressBar(0, 100);
                                pBar.setStringPainted(true);
                                pBar.setString("Menunggu...");

                                // Tambahkan label dan progress bar secara dinamis ke panel
                                SwingUtilities.invokeLater(() -> {
                                    progressPanel.add(new JLabel("Proses " + nomorAntrean + " (" + bangunYangDipilih.getClass().getSimpleName() + ")"));
                                    progressPanel.add(pBar);
                                    progressPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                                    progressPanel.revalidate();
                                    progressPanel.repaint();
                                });

                                // Buat Wrapper Thread untuk mensimulasikan animasi Progress Bar
                                Thread thread = new Thread(() -> {
                                    try {
                                        // Simulasi tugas loading (agar progress bar terlihat bergerak balapan)
                                        int delayVisual = 10 + rand.nextInt(30); 
                                        for (int p = 0; p <= 100; p += 2) {
                                            final int progress = p;
                                            SwingUtilities.invokeLater(() -> {
                                                pBar.setValue(progress);
                                                pBar.setString(progress + "%");
                                            });
                                            Thread.sleep(delayVisual);
                                        }

                                        // Eksekusi Runnable Asli dari Objek Bangun
                                        bangunYangDipilih.run();

                                        // Status Selesai
                                        SwingUtilities.invokeLater(() -> {
                                            pBar.setString("Selesai!");
                                        });

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                });

                                activeThreads.add(thread);
                                thread.start();
                            }

                            // join all thread before calculate the time excecution
                            for (Thread t : activeThreads) {
                                t.join();
                            }
                            
                            endTime = System.nanoTime();
                            executionTime = endTime - startTime;
                            
                            SwingUtilities.invokeLater(() -> {
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Input tidak valid!");
                }
            }
        });

        setVisible(true);
    }
    
    private void resetTampilan(JScrollPane scrollPane) {
        setSize(650, 500); 
        scrollPane.setVisible(false);
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