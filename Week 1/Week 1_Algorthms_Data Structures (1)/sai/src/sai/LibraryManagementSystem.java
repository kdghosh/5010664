package sai;


import java.util.Arrays;
import java.util.Comparator;

class Book 
{
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) 
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() 
    {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

public class LibraryManagementSystem 
{
    private Book[] books;

    public LibraryManagementSystem(Book[] books) 
    {
        this.books = books;
    }

    // Linear Search
    public Book linearSearch(String title) 
    {
        for (Book book : books) 
        {
            if (book.title.equalsIgnoreCase(title)) 
            {
                return book;
            }
        }
        return null;
    }

    // Binary Search
    public Book binarySearch(String title) 
    {
        Arrays.sort(books, Comparator.comparing(book -> book.title.toLowerCase()));
        int left = 0;
        int right = books.length - 1;

        while (left <= right) 
        {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].title.compareToIgnoreCase(title);

            if (comparison == 0) 
            {
                return books[mid];
            } 
            else if (comparison < 0)
            {
                left = mid + 1;
            } 
            else 
            {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) 
    {
        Book[] books = 
        {
            new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(2, "1984", "George Orwell"),
            new Book(3, "To Kill a Mockingbird", "Harper Lee"),
            new Book(4, "The Catcher in the Rye", "J.D. Salinger"),
            new Book(5, "Pride and Prejudice", "Jane Austen")
        };

        LibraryManagementSystem library = new LibraryManagementSystem(books);

        System.out.println("Linear Search for '1984':");
        Book book1 = library.linearSearch("1984");
        if (book1 != null)
        {
            System.out.println(book1);
        } 
        else
        {
            System.out.println("Book not found");
        }

        System.out.println("Binary Search for 'The Great Gatsby':");
        Book book2 = library.binarySearch("The Great Gatsby");
        if (book2 != null) 
        {
            System.out.println(book2);
        } 
        else
        {
            System.out.println("Book not found");
        }
    }
}
