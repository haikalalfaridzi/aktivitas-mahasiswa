package Model;

public class Mahasiswa {
    private String nim; 
    private String nama;
    private String aktivitas;

    public Mahasiswa(String nim, String nama, String aktivitas) {
        this.nim = nim;
        this.nama = nama;
        this.aktivitas = aktivitas;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getAktivitas(){
        return aktivitas;
    }
    
    public void setAktivitas(String aktivitas){
        this.aktivitas = aktivitas;
    }
}