package cn.yyb.behavioral.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 12:39
 */
public class Client {
    //    public static void main(String[] args) {
//        BookShelf bookShelf = new BookShelf(10);
//        bookShelf.appendBook(new Book("西游记"));
//        bookShelf.appendBook(new Book("红楼梦"));
//        bookShelf.appendBook(new Book("水浒传"));
//
//        Iterator iterator = bookShelf.iterator();
//        while (iterator.hasNext()) {
//            Book book = (Book) iterator.next();
//            System.out.println("book = " + book.getName());
//        }
//
//    }
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.appendBook(new Book("西游记"));
        bookShelf.appendBook(new Book("红楼梦"));
        bookShelf.appendBook(new Book("水浒传"));

        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println("book = " + book.getName());
        }
    }

}
