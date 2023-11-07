package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book{
    private String holder;
    private GregorianCalendar dueDate;

    private boolean checkedOut;

    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
        holder=null;
        dueDate=null;
        checkedOut=false;
    }
    public String getHolder(){
        return holder;
    }
    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    public boolean getCheckStatus(){
        return checkedOut;
    }
    public void setCheckOut(String name, GregorianCalendar gc){
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
