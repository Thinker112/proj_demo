package cn.yyb.behavioral.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 12:24
 */
public class BookShelfIterator implements Iterator{

    private final BookShelf bookShelf;

    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
