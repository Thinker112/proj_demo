package cn.yyb.behavioral.Iterator;

import java.util.ArrayList;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 12:23
 */
public class BookShelf implements Aggregate{

    private final ArrayList<Book> books;

    private int last;

    public BookShelf() {
        this.books = new ArrayList<Book>();
    }

    public Book getBookAt(int index){
        return books.get(index);
    }

    public void appendBook(Book book){
        books.add(book);
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
