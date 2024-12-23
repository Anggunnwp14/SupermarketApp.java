import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimestampExample {
    public static void main(String[] args) {
        // Timestamp saat ini dalam epoch
        long timestamp = Instant.now().toEpochMilli();
        System.out.println("Timestamp (epoch): " + timestamp);

        // Timestamp dalam format tanggal dan waktu
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Formatted Timestamp: " + formattedTime);
    }
}
