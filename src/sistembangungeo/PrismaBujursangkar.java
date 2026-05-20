package sistembangungeo;

// =========================================
// FILE : PrismaBujurSangkar.java
// =========================================

class PrismaBujursangkar extends Persegi {

    private double tinggi;

    private double volume;
    private double luasPermukaan;

    public PrismaBujursangkar(double sisi, double tinggi) {
        super(sisi);
        this.tinggi = tinggi;
    }

    public double getVolume() {
        return volume;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    // POLYMORPHISM
    @Override
    void hitungLuas() {
        super.hitungLuas();
        super.hitungKeliling();
        luasPermukaan = (2 * super.luas) + (super.keliling * tinggi);
    }

    @Override
    void hitungKeliling() {
        super.hitungKeliling();
    }

    void hitungVolume() {
        super.hitungLuas();
        volume = super.luas * tinggi;
    }

    // MULTITHREADING
    @Override
    public void run() {
        
        Thread threadVolume = new Thread(() -> {
            hitungVolume();
            System.out.println(
                    "Thread Volume Prisma : "
                    + Thread.currentThread().getName()
            );
        });

        Thread threadLuasPermukaan = new Thread(() -> {
            hitungLuas();
            System.out.println(
                    "Thread Luas Permukaan Prisma : "
                    + Thread.currentThread().getName()
            );
        });

        threadVolume.start();
        threadLuasPermukaan.start();

        try {
            threadVolume.join();
            threadLuasPermukaan.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}