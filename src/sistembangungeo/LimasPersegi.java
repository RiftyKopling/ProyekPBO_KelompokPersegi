package sistembangungeo;

class LimasPersegi extends Persegi {

    private double tinggi;
    private double tinggiSisi;
    private double volume;
    private double luasPermukaan;

    public LimasPersegi(double sisi,double tinggi,double tinggiSisi) {
        super(sisi);
        this.tinggi = tinggi;
        this.tinggiSisi = tinggiSisi;
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
        luasPermukaan = super.luas + (4 * (0.5 * super.sisi * tinggiSisi));
    }

    @Override
    void hitungKeliling() {
        super.hitungKeliling();
    }

    void hitungVolume() {
        volume = (super.luas * tinggi) / 3;
    }

    // MULTITHREADING
    @Override
    public void run() {
        Thread threadVolume = new Thread(() -> {
            hitungVolume();
            System.out.println(
                    "Thread Volume Limas : "
                    + Thread.currentThread().getName()
            );
        });

        Thread threadLuasPermukaan = new Thread(() -> {
            hitungLuas();
            System.out.println(
                    "Thread Luas Permukaan Limas : "
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