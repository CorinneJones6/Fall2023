package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a library book, extending from library book.
 * The holder and dueDate are set to null, checkedOut is false, given values at check out.
 *
 */
public class LibraryBook<T> extends Book{
    private T holder;
    private GregorianCalendar dueDate;
    private boolean checkedOut;

    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
        holder=null;
        dueDate=null;
        checkedOut=false;
    }
    /**
     * @return the holder
     */
    public T getHolder(){
        return holder;
    }
    /**
     * @return the dueDate
     */
    public GregorianCalendar getDueDate(){
        return dueDate;
    }
    /**
     * @return checkedOut
     */
    public boolean getCheckStatus(){
        return checkedOut;
    }
    /**
     * Sets the library book specific member variables to the holder and dueDate, sets checkedOut to true.
     *
     * @param holder
     *           -- the person identifier, name or phone number
     * @param gc
     *           -- the gregorian calendar specifying the due date
     */
    public void setCheckOut(T holder, GregorianCalendar gc){
        checkedOut = true;
        this.holder = holder;
        dueDate = gc;
    }
    /**
     * Returns library book specific member variables to null & checkedOut to false.
     */
    public void setCheckIn(){
        checkedOut = false;
        holder = null;
        dueDate = null;
    }
}
