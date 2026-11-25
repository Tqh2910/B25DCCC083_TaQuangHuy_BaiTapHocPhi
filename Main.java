import java.util.*;

class SinhVien {
    private String ma;
    private String ten;
    private String ngaySinh;

    public SinhVien(String ma, String ten, String ngaySinh) {
        this.ma = ma;
        this.ten = chuanHoaTen(ten);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaTen(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    private String chuanHoaNgaySinh(String s) {
        String[] parts = s.trim().split("/");
        String day = parts[0].trim();
        String month = parts[1].trim();
        String year = parts[2].trim();

        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        return day + "/" + month + "/" + year;
    }

    @Override
    public String toString() {
        return ma + " " + ten + " " + ngaySinh;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        
        String firstLine = sc.nextLine().replace("\uFEFF", "").trim();
        while (firstLine.isEmpty() && sc.hasNextLine()) {
            firstLine = sc.nextLine().replace("\uFEFF", "").trim();
        }
        int n = Integer.parseInt(firstLine);
        List<SinhVien> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine().trim();
            while (ma.isEmpty() && sc.hasNextLine()) {
                ma = sc.nextLine().trim();
            }
            String ten = sc.nextLine();
            String ngaySinh = sc.nextLine().trim();
            list.add(new SinhVien(ma, ten, ngaySinh));
        }

        for (SinhVien sv : list) {
            System.out.println(sv);
        }
    }
}
