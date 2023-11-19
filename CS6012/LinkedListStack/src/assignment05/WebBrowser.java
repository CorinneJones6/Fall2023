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
     *
     * @param history
     */
    public WebBrowser(SinglyLinkedList<URL> history) {
        backStack_ = history;
        frontStack_ = new LinkedListStack<>();
    }

    /**
     *
     * @param webpage
     */
    public void visit(URL webpage) {

        backStack_.insertFirst(webpage);
        frontStack_.clear();

    }

    /**
     *
     * @return
     * @throws NoSuchElementException
     */
    public URL back() throws NoSuchElementException {
        if (backStack_.isEmpty()) {
            throw new NoSuchElementException("No webpage history.");
        }

        currentWebpage_ = backStack_.deleteFirst();

        frontStack_.push(currentWebpage_);

        return backStack_.getFirst();
    }

    /**
     *
     * @return
     * @throws NoSuchElementException
     */
    public URL forward() throws NoSuchElementException {
        if (backStack_.isEmpty()) {
            throw new NoSuchElementException("No webpage history.");
        }

        URL forwardURL = frontStack_.pop();

        backStack_.insertFirst(forwardURL);

        return forwardURL;
    }

    /**
     *
     * @return
     */
    public SinglyLinkedList<URL> history() {
        return backStack_;
    }
}
