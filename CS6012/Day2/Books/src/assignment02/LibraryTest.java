package assignment02;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testEmpty() {
//        Library lib = new Library();
        Library<String> lib=new Library<>();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook<String>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

//        var lib = new Library();
        Library<String> lib=new Library<>();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        var booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 1);
        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        // test a medium library
//        var lib = new Library();
        Library<String> lib=new Library<>();
        lib.addAll("Mushroom_Publishing.txt");

        //CASE: tests attempt to check out an already checked out book
        var checkout1 = lib.checkout(9781843190004L, "Jane Doe", 1, 1, 2008);
        var checkout2 = lib.checkout(9781843190004L, "John Doe", 1, 1, 2008);
        assertFalse(checkout2);

        //CASE: tests attempt to check in from a checked out book, and then an attempt to check that same book in by isbn
        var checkin1 = lib.checkin("Jane Doe");
        var checkin2=lib.checkin(9781843190004L);

        assertTrue(checkin1);
        assertFalse(checkin2);

        //CASE: returns a null book if isbn doesn't exist
        var lookup1=lib.lookup(737834798L);
        assertNull(lookup1);

    }

    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest(){
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new Library<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBook<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }

    @Test
    public void overDueBookListSmallTest(){
        // Create a library, add some books to it
        Library<String> lib=new Library<>();


        lib.add(1L, "Author1", "Book1");
        lib.add(2L, "Author2", "Book2");
        lib.add(3L, "Author3", "Book3");
        lib.add(4L, "Author4", "Book4");

        //Checkout the books with a due date
        lib.checkout(1L, "holder1", 10, 1, 2023);
        lib.checkout(3L, "holder3", 2, 1, 2024);
        lib.checkout(2L, "holder2", 9, 10, 2023);
        lib.checkout(4L, "holder4", 9, 1, 2023);

        // Create an instance of OrderByDueDate
         var overdueList=lib.getOverdueList(11, 1, 2023);

        // Verify that the list is sorted in ascending order of due dates
        assertEquals(overdueList.get(0).getIsbn(), 4);
        assertEquals(overdueList.get(1).getIsbn(), 2);
        assertEquals(overdueList.get(2).getIsbn(), 1);
        assertEquals(overdueList.size(), 3);
    }
    @Test
    public void overDueBookListLargeTest() {
        Library<String> lib=new Library<>();
        lib.addAll("Mushroom_Publishing.txt");

        //Have someone check out a book
       lib.checkout(9781843190042L, "Jane Doe", 1, 1, 2008);
       lib.checkout(9781843190073L, "Jane Doe", 1, 1, 2008);
       lib.checkout(9781843190110L, "Jane Doe", 1, 1, 2008);
       lib.checkout(9781843190349L, "Jane Doe", 1, 1, 2024);
       lib.checkout(9781843190363L, "Jane Doe", 1, 1, 2024);

        var overdueList = lib.getOverdueList(11,1,2022);
        assertEquals(overdueList.size(), 3);
    }

    @Test
    public void orderedByAuthorTest(){
        //initialize my library to test
        Library<String> lib = new Library<>();
        lib.addAll("Mushroom_Publishing.txt");

        //test 1 - testing sort by author
        var sortedByAuthor = lib.getOrderedByAuthor();
        //System.out.println(sortedByAuthor);
        assertEquals(sortedByAuthor.get(0).getAuthor(), "Alan Burt Akers");

        //test 2 - check if the size of the sorted list matches the original library size
        assertEquals(sortedByAuthor.size(), lib.size());

        //test 3 - check that the sort function is working in ascending order
        for (int i = 1; i < sortedByAuthor.size(); i++) {
            String author1 = sortedByAuthor.get(i - 1).getAuthor();
            String author2 = sortedByAuthor.get(i).getAuthor();
            assertTrue(author1.compareTo(author2) <= 0);
        }

        //test 4 - an empty library doesn't cause an error if put through the sorted function and stays empty
        Library<String> emptyLib = new Library<>();
        var sortedEmptyLib = emptyLib.getOrderedByAuthor();
        assertTrue(sortedEmptyLib.isEmpty());

        //test 5 - check that an empty library throws an out of bounds error if there is an attempt to index in
        assertThrows(IndexOutOfBoundsException.class, () -> {sortedEmptyLib.get(0);});

        //test 6 - books with the same author get sorted by title
        String sameAuthor = "Moyra Caldecott";
        int firstIndex = sortedByAuthor.indexOf(new LibraryBook<String>(9781843190028L, sameAuthor, "Crystal Legends"));
        int lastIndexWithSameAuthor = sortedByAuthor.lastIndexOf(new LibraryBook<String>(9781843190004L, sameAuthor, "Weapons of the Wolfhound"));
        assertTrue(firstIndex < lastIndexWithSameAuthor);

        //test 7 - check that adding a book (to the end) and then sorting puts it in the right spot
        int origSize = lib.size();
        lib.add(22L, "AAA New Author", "New Title");
        assertEquals(lib.size(), origSize+1);
        var updatedSortedByAuthor = lib.getOrderedByAuthor();
        assertEquals(updatedSortedByAuthor.get(0).getAuthor(), "AAA New Author");
    }

}