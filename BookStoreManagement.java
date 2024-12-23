import java.sql.*;
import java.util.*;

// Interface
interface BookOperations {
    void addBook(Book book);
    void viewBooks();
    void updateBook(int bookId, String title, double price);
    void deleteBook(int bookId);
}

// Kelas Utama
public class BookStoreManagement implements BookOperations {
    private Connection connection;

    public BookStoreManagement() {
        try {
            // Ganti URL koneksi ke PostgreSQL
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/BookStore", // Format PostgreSQL
                "postgres", // Username PostgreSQL
                "Anggun123" // Password PostgreSQL
            );
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    @Override
    public void addBook(Book book) {
        try {
            String query = "INSERT INTO books (title, author, price, publishedDate, publishYear) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDouble(3, book.getPrice());
            stmt.setTimestamp(4, new Timestamp(book.getPublishedDate().getTime())); // Tanggal upload data
            stmt.setInt(5, book.getPublishYear()); // Tahun terbit
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    @Override
    public void viewBooks() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                java.util.Date publishedDate = new java.util.Date(rs.getTimestamp("publishedDate").getTime());
                int publishYear = rs.getInt("publishYear");
                System.out.println("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author") + ", Price: " + rs.getDouble("price") +
                        ", Published Date: " + publishedDate + ", Publish Year: " + publishYear);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
    }

    @Override
    public void updateBook(int bookId, String title, double price) {
        try {
            String query = "UPDATE books SET title = ?, price = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setDouble(2, price);
            stmt.setInt(3, bookId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(int bookId) {
        try {
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, bookId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BookStoreManagement store = new BookStoreManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Book Store Management Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter publish year: ");
                    int publishYear = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Tanggal upload data adalah waktu saat ini
                    long timestamp = System.currentTimeMillis();
                    java.util.Date publishedDate = new java.util.Date(timestamp);

                    // Buat objek Book
                    Book book = new Book(title, author, price, publishedDate, publishYear);
                    store.addBook(book);
                    break;

                case 2:
                    store.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter book ID to update: ");
                    int bookIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    store.updateBook(bookIdToUpdate, newTitle, newPrice);
                    break;

                case 4:
                    System.out.print("Enter book ID to delete: ");
                    int bookIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    store.deleteBook(bookIdToDelete);
                    break;

                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Superclass
class Item {
    private String title;
    private double price;

    public Item(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}

// Subclass
class Book extends Item {
    private String author;
    private java.util.Date publishedDate; // Tanggal upload data
    private int publishYear; // Tahun terbit

    public Book(String title, String author, double price, java.util.Date publishedDate, int publishYear) {
        super(title, price);
        this.author = author;
        this.publishedDate = publishedDate;
        this.publishYear = publishYear;
    }

    public String getAuthor() {
        return author;
    }

    public java.util.Date getPublishedDate() {
        return publishedDate;
    }

    public int getPublishYear() {
        return publishYear;
    }
}
