import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SupermarketApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Login process
        System.out.println("+-----------------------------------------------------+");
        System.out.println("                      LOG IN                          ");
        System.out.println("+-----------------------------------------------------+");

        // Default login credentials
        String defaultUsername = "admin";
        String defaultPassword = "12345";
        String defaultCaptcha = "ABC123";

        boolean isLoggedIn = false;

        // Loop for login attempts
        while (!isLoggedIn) {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("Captcha (" + defaultCaptcha + "): ");
            String captcha = scanner.nextLine();

            if (username.equals(defaultUsername) && password.equals(defaultPassword) && captcha.equals(defaultCaptcha)) {
                System.out.println("Login berhasil!");
                isLoggedIn = true;
            } else {
                System.out.println("Login gagal, silakan coba lagi.\n");
            }
        }

        // Welcome message
        System.out.println("+----------------------------------------------------+");
        System.out.println("         Selamat Datang di Supermarket Sejahtera!     ");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tanggal dan Waktu : " + currentDateTime.format(formatter));
        System.out.println("+----------------------------------------------------+\n");

        // Invoice input
        System.out.println("Masukkan detail belanja Anda:");
        System.out.print("No. Faktur      : ");
        String noFaktur = scanner.nextLine();

        System.out.print("Kode Barang     : ");
        String kodeBarang = scanner.nextLine();

        System.out.print("Nama Barang     : ");
        String namaBarang = scanner.nextLine();

        System.out.print("Harga Barang    : ");
        double hargaBarang = scanner.nextDouble();

        System.out.print("Jumlah Beli     : ");
        int jumlahBeli = scanner.nextInt();

        double totalHarga = hargaBarang * jumlahBeli;

        // Display invoice
        System.out.println("\n+----------------------------------------------------+");
        System.out.println("                     INVOICE                         ");
        System.out.println("+----------------------------------------------------+");
        System.out.println("No. Faktur      : " + noFaktur);
        System.out.println("Kode Barang     : " + kodeBarang);
        System.out.println("Nama Barang     : " + namaBarang);
        System.out.println("Harga Barang    : Rp " + hargaBarang);
        System.out.println("Jumlah Beli     : " + jumlahBeli);
        System.out.println("TOTAL           : Rp " + totalHarga);
        System.out.println("+----------------------------------------------------+");

        // Input nama kasir
        scanner.nextLine(); // Consume newline left by nextInt()
        System.out.print("Nama Kasir      : ");
        String namaKasir = scanner.nextLine();

        System.out.println("Kasir           : " + namaKasir);
        System.out.println("+----------------------------------------------------+");

        // Close scanner
        scanner.close();
    }
}
