package sistembangungeo;

class Persegi extends Bangun implements Runnable {

    public double sisi;
    public double luas;
    public double keliling;

    public Persegi(double sisi) {
        this.sisi = sisi;
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
                    System.out.println(
                            "Thread Luas : " +
                            Thread.currentThread().getName()
                    );
                });

        Thread threadKeliling =
                new Thread(() -> {
                    hitungKeliling();

                    System.out.println(
                            "Thread Keliling : " +
                            Thread.currentThread().getName()
                    );
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