package assignment05;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    private static SinglyLinkedList<URL> backStack_;
    private static LinkedListStack<URL> frontStack_;
    URL currentWebpage_;

    /**
     * WebBrowser constructor
     */
    public WebBrowser() {
        backStack_ = new SinglyLinkedList<>();
        frontStack_ = new LinkedListStack<>();
    }

    /**
     * @param history - added to the backStack
     */
    public WebBrowser(SinglyLinkedList<URL> history) {
        backStack_ = history;
        frontStack_ = new LinkedListStack<>();
        if(!history.isEmpty()) {
            currentWebpage_ = backStack_.deleteFirst();
        }
    }

    /**
     * @param webpage - add the webpage to the backStack
     */
    public void visit(URL webpage) {
        if(currentWebpage_!=null) {
            backStack_.insertFirst(currentWebpage_);
        }
        frontStack_.clear();
        currentWebpage_=webpage;
    }

    /**
     * @return - the previous URL visited
     * @throws NoSuchElementException - if there isn't a previous URL
     */
    public URL back() throws NoSuchElementException {
        if (backStack_.isEmpty()) {
            throw new NoSuchElementException("No webpage history.");
        }
        frontStack_.push(currentWebpage_);

        currentWebpage_ = backStack_.deleteFirst();

        return currentWebpage_;
    }

    /**
     * @return - the forward URL
     * @throws NoSuchElementException - if the forward URL doesn't exist
     */
    public URL forward() throws NoSuchElementException {
        if (frontStack_.isEmpty()) {
            throw new NoSuchElementException("No webpage history.");
        }
        backStack_.insertFirst(currentWebpage_);

        currentWebpage_=frontStack_.pop();

        return currentWebpage_;
    }

    /**
     * @return - all the items on the backStack
     */
    public SinglyLinkedList<URL> history() {
        return backStack_;
    }
}
