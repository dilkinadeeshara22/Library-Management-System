import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    
    static class Book {
        String title;
        String author;
        int copies;

        Book(String title, String author, int copies) {
            this.title = title;
            this.author = author;
            this.copies = copies;
        }
    }

    
    static class Member {
        String name;

        Member(String name) {
            this.name = name;
        }
    }

   
    static ArrayList<Book> catalog = new ArrayList<>();
    static ArrayList<Member> subscribers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        catalog.add(new Book("The Hobbit", "J.R.R. Tolkien", 4));
        catalog.add(new Book("To Kill a Mockingbird", "Harper Lee", 2));
        catalog.add(new Book("1984", "George Orwell", 3));
        subscribers.add(new Member("Liam Carter"));

        int choice = 0;
        while (choice != 6) {
            System.out.println("\n--- Library Directory System ---");
            System.out.println("1. Show Catalog");
            System.out.println("2. Add New Member");
            System.out.println("3. Show Registered Members");
            System.out.println("4. Issue Book (By Number)");
            System.out.println("5. Return Book (By Number)");
            System.out.println("6. Exit System");
            System.out.print("Enter command: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please input a valid selection number.");
                continue;
            }

            if (choice == 1) {
                displayCatalog();
            } else if (choice == 2) {
                System.out.print("Enter member's full name: ");
                String name = scanner.nextLine();
                subscribers.add(new Member(name));
                System.out.println("Successfully added member: " + name);
            } else if (choice == 3) {
                displayMembers();
            } else if (choice == 4) {
                displayCatalog();
                System.out.print("Enter book list number to borrow: ");
                try {
                    int num = Integer.parseInt(scanner.nextLine());
                    borrowAsset(num - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Must enter an integer.");
                }
            } else if (choice == 5) {
                displayCatalog();
                System.out.print("Enter book list number to return: ");
                try {
                    int num = Integer.parseInt(scanner.nextLine());
                    returnAsset(num - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Must enter an integer.");
                }
            } else if (choice == 6) {
                System.out.println("Shutting down directory interface...");
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
    }

    
    static void displayCatalog() {
        System.out.println("\n=== Current Book Inventory ===");
        if (catalog.isEmpty()) {
            System.out.println("Inventory is currently empty.");
            return;
        }
        for (int i = 0; i < catalog.size(); i++) {
            Book b = catalog.get(i);
            System.out.println((i + 1) + ". " + b.title + " | Author: " + b.author + " (In Stock: " + b.copies + ")");
        }
    }

   
    static void displayMembers() {
        System.out.println("\n=== Registered Members ===");
        if (subscribers.isEmpty()) {
            System.out.println("No active members are registered.");
            return;
        }
        for (int i = 0; i < subscribers.size(); i++) {
            Member m = subscribers.get(i);
            System.out.println((i + 1) + ". " + m.name);
        }
    }

    
    static void borrowAsset(int index) {
        if (index < 0 || index >= catalog.size()) {
            System.out.println("Error: That book index does not exist.");
            return;
        }
        Book b = catalog.get(index);
        if (b.copies > 0) {
            b.copies = b.copies - 1;
            System.out.println("Success! You have checked out: " + b.title);
        } else {
            System.out.println("Sorry, all copies of \"" + b.title + "\" are checked out.");
        }
    }

    
    static void returnAsset(int index) {
        if (index < 0 || index >= catalog.size()) {
            System.out.println("Error: That book index does not exist.");
            return;
        }
        Book b = catalog.get(index);
        b.copies = b.copies + 1;
        System.out.println("Success! Returned copy of: " + b.title);
    }
}