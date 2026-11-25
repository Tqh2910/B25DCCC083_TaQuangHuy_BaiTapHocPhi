import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.round(hocPhi * 0.5);
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.round(hocPhi * 0.7);
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String maHe;
    protected String maSV;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maHe, String maSV, int soTinChi) {
        this.maHe = maHe;
        this.maSV = maSV;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public abstract long hocPhi();

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long soTienGiam() {
        if (mienGiam == null) return 0;
        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - soTienGiam();
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaHe() {
        return maHe;
    }

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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
        List<SinhVien> listSV = new ArrayList<>();
        Map<String, SinhVien> mapSV = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            SinhVien sv = null;
            if (maHe.equals("CQ")) {
                sv = new SinhVienCQ(maSV, soTinChi);
            } else if (maHe.equals("CLC")) {
                sv = new SinhVienCLC(maSV, soTinChi);
            } else if (maHe.equals("LT")) {
                sv = new SinhVienLT(maSV, soTinChi);
            }

            if (sv != null) {
                listSV.add(sv);
                mapSV.put(maSV, sv);
            }
        }

        int m = sc.nextInt();
        List<String> notFoundList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maCS = sc.next();

            SinhVien sv = mapSV.get(maSV);
            if (sv == null) {
                notFoundList.add("NOT FOUND " + maSV);
            } else {
                if (maCS.equals("CS")) {
                    sv.setMienGiam(new ConChinhSach());
                } else if (maCS.equals("HN")) {
                    sv.setMienGiam(new HoNgheo());
                } else if (maCS.equals("HB")) {
                    sv.setMienGiam(new HocBong());
                }
            }
        }

        for (String s : notFoundList) {
            System.out.println(s);
        }

        for (SinhVien sv : listSV) {
            System.out.println(sv);
        }

        String[] dsHe = {"CQ", "CLC", "LT"};
        for (String he : dsHe) {
            int count = 0;
            long tongPhaiNop = 0;
            for (SinhVien sv : listSV) {
                if (sv.getMaHe().equals(he)) {
                    count++;
                    tongPhaiNop += sv.phaiNop();
                }
            }
            if (count > 0) {
                System.out.println(he + " " + count + " " + tongPhaiNop);
            }
        }
    }
}
