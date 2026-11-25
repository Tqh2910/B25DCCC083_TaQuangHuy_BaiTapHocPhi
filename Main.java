import java.util.*;

abstract class SinhVien {
    protected String maHe;
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maHe, String maSV, int soTinChi) {
        this.maHe = maHe;
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi();
    }
}

class SinhVienCQ extends SinhVien {
    public SinhVienCQ(String maSV, int soTinChi) {
        super("CQ", maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000;
    }
}

class SinhVienCLC extends SinhVien {
    public SinhVienCLC(String maSV, int soTinChi) {
        super("CLC", maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return Math.round(soTinChi * 450000.0 * 1.5);
    }
}

class SinhVienLT extends SinhVien {
    public SinhVienLT(String maSV, int soTinChi) {
        super("LT", maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000 + 2000000;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;

        int n = Integer.parseInt(sc.next().replace("\uFEFF", ""));
        List<SinhVien> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            if (maHe.equals("CQ")) {
                list.add(new SinhVienCQ(maSV, soTinChi));
            } else if (maHe.equals("CLC")) {
                list.add(new SinhVienCLC(maSV, soTinChi));
            } else if (maHe.equals("LT")) {
                list.add(new SinhVienLT(maSV, soTinChi));
            }
        }

        for (SinhVien sv : list) {
            System.out.println(sv);
        }
    }
}
