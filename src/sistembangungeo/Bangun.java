package sistembangungeo;

abstract class Bangun {
    public String jenisBangun;
    
    public String getJenisBangun(){
        return this.jenisBangun;
    }
    
    abstract void hitungLuas();
    abstract void hitungKeliling();
}
