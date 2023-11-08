package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric <T> extends Book{
    private T holder;
    private GregorianCalendar dueDate;

    private boolean checkedOut;

    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
        holder=null;
        dueDate=null;
        checkedOut=false;
    }

    public T getHolder(){
        return holder;
    }
    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    public boolean getCheckStatus(){
        return checkedOut;
    }
    public void setCheckOut(T name, GregorianCalendar gc){
        checkedOut=true;
        holder=name;
        dueDate=gc;
    }
    public void setCheckIn(){
        checkedOut=false;
        holder=null;
        dueDate=null;
    }
}
