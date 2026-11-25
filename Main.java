import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

abstract class SinhVien implements Comparable<SinhVien> {
    protected String maHe;
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maHe, String maSV, int soTinChi) {
        this.maHe = maHe;
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public String getMaSV() {
        return maSV;
    }

    public String getMaHe() {
        return maHe;
    }

    @Override
    public int compareTo(SinhVien o) {
        if (this.hocPhi() != o.hocPhi()) {
            return Long.compare(o.hocPhi(), this.hocPhi());
        }
        return this.maSV.compareTo(o.maSV);
    }

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

class QuanLyHocPhi {
    private List<SinhVien> ds;
    private Set<String> maDaCo;

    public QuanLyHocPhi() {
        ds = new ArrayList<>();
        maDaCo = new HashSet<>();
    }

    public void themSinhVien(String maHe, String maSV, String tinChiStr) throws DuLieuKhongHopLeException {
        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException("Mã hệ không hợp lệ");
        }

        int soTinChi;
        try {
            soTinChi = Integer.parseInt(tinChiStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("Số tín chỉ không phải số nguyên");
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException("Số tín chỉ ngoài [1, 30]");
        }

        if (maDaCo.contains(maSV)) {
            throw new DuLieuKhongHopLeException("Trùng mã sinh viên hợp lệ");
        }

        SinhVien sv;
        if (maHe.equals("CQ")) {
            sv = new SinhVienCQ(maSV, soTinChi);
        } else if (maHe.equals("CLC")) {
            sv = new SinhVienCLC(maSV, soTinChi);
        } else {
            sv = new SinhVienLT(maSV, soTinChi);
        }

        ds.add(sv);
        maDaCo.add(maSV);
    }

    public void sapXep() {
        Collections.sort(ds);
    }

    public List<SinhVien> getDanhSach() {
        return ds;
    }

    public long tongHocPhi() {
        long tong = 0;
        for (SinhVien sv : ds) {
            tong += sv.hocPhi();
        }
        return tong;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;

        int n = Integer.parseInt(sc.next().replace("\uFEFF", ""));
        QuanLyHocPhi ql = new QuanLyHocPhi();
        List<String> danhSachInvalid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            String tinChiStr = sc.next();

            try {
                ql.themSinhVien(maHe, maSV, tinChiStr);
            } catch (DuLieuKhongHopLeException e) {
                danhSachInvalid.add("INVALID " + maSV);
            }
        }

        for (String inv : danhSachInvalid) {
            System.out.println(inv);
        }

        ql.sapXep();
        for (SinhVien sv : ql.getDanhSach()) {
            System.out.println(sv);
        }

        System.out.println("TONG " + ql.tongHocPhi());
    }
}
