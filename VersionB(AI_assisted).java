import java.util.ArrayList;

// Individual class modeling library book entities
class Book {
    String bookId;
    String title;
    String author;
    boolean isAvailable;

    Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    void displayDetails() {
        String status = isAvailable ? "In Library" : "Checked Out";
        System.out.println("[" + bookId + "] " + title + " by " + author + " Status: " + status);
    }
}

// Individual class modeling reader profiles
class Member {
    String memberId;
    String name;
    ArrayList<Book> activeLoans = new ArrayList<>();

    Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    void borrowBook(Book book) {
        if (!book.isAvailable) {
            System.out.println(name + " cannot borrow \"" + book.title + "\" (Currently unavailable).");
            return;
        }
        book.isAvailable = false;
        activeLoans.add(book);
        System.out.println(name + " has successfully borrowed \"" + book.title + "\".");
    }

    void returnBook(Book book) {
        if (!activeLoans.contains(book)) {
            System.out.println("Record Error: " + name + " did not borrow \"" + book.title + "\".");
            return;
        }
        book.isAvailable = true;
        activeLoans.remove(book);
        System.out.println(name + " has successfully returned \"" + book.title + "\".");
    }

    void viewIssuedItems() {
        System.out.println("Books checked out by " + name + ":");
        if (activeLoans.isEmpty()) {
            System.out.println("  None.");
        } else {
            for (Book b : activeLoans) {
                System.out.println("  * " + b.title);
            }
        }
    }
}

// Coordinating controller class
class Library {
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();

    void registerBook(Book book) {
        bookList.add(book);
        System.out.println("Log: Book registered (" + book.title + ")");
    }

    void registerMember(Member member) {
        memberList.add(member);
        System.out.println("Log: Member registered (" + member.name + ")");
    }

    Book findBook(String id) {
        for (Book b : bookList) {
            if (b.bookId.equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }

    Member findMember(String id) {
        for (Member m : memberList) {
            if (m.memberId.equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }

    void printFullCatalog() {
        System.out.println("\n--- Current Library Asset Catalog ---");
        if (bookList.isEmpty()) {
            System.out.println("No records in directory.");
            return;
        }
        for (Book b : bookList) {
            b.displayDetails();
        }
    }
}

// Primary execution demo class
public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        // 1. Log in active assets
        System.out.println("=== Step 1: Initializing Assets ===");
        Book b1 = new Book("B101", "The Catcher in the Rye", "J.D. Salinger");
        Book b2 = new Book("B102", "The Great Gatsby", "F. Scott Fitzgerald");
        Book b3 = new Book("B103", "Fahrenheit 451", "Ray Bradbury");
        
        library.registerBook(b1);
        library.registerBook(b2);
        library.registerBook(b3);

        System.out.println("\n=== Step 2: Registering Users ===");
        Member m1 = new Member("M201", "Sophia Bennett");
        Member m2 = new Member("M202", "Ethan Hunt");
        
        library.registerMember(m1);
        library.registerMember(m2);

        // View baseline database
        library.printFullCatalog();

        System.out.println("\n=== Step 3: Simulating Borrow Operations ===");
        
        m1.borrowBook(b1);
        m2.borrowBook(b1); 
        m2.borrowBook(b2);

        library.printFullCatalog();

        System.out.println();
        m1.viewIssuedItems();
        m2.viewIssuedItems();

        System.out.println("\n=== Step 4: Simulating Return Operations ===");
        m1.returnBook(b1);

        library.printFullCatalog();
    }
}