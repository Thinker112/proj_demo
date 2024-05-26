package cn.yyb.behavioral.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 12:23
 */
public class BookShelf implements Aggregate{

    private final Book[] books;

    private int last;

    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    public Book getBookAt(int index){
        return books[index];
    }

    public void appendBook(Book book){
        this.books[last] = book;
        last++;
    }

    public int getLength(){
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
