package com.driver;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }


    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity getBookById ( @PathVariable("id") int id){
        Book result = null ;
        for ( int i = 0 ; i < bookList.size() ; i++ ){
            Book book = bookList.get(i) ;
            if ( book.getId() == id ){
                result = book ;
            }
        }
        return new ResponseEntity( result , HttpStatus.FOUND) ;
    }


    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById ( @PathVariable("id") int id){
        for ( int i = 0 ; i < bookList.size() ; i++ ){
            Book book = bookList.get(i) ;
            if ( book.getId() == id ){
                bookList.remove(i) ;
                break ;
            }
        }
        return new ResponseEntity( "Deleted Successfully" , HttpStatus.FOUND) ;
    }


    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity getAllBooks (){
        return new ResponseEntity( bookList , HttpStatus.FOUND) ;
    }


    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAllBooks (){
        bookList = new ArrayList<>() ;
        return new ResponseEntity( "Deleted Successfully" , HttpStatus.FOUND) ;
    }


    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity getBooksByAuthor ( @RequestParam("author") String name ){
        ArrayList < Book > aut = new ArrayList<>() ;
        for ( int i = 0 ; i < bookList.size() ; i++ ){
            if ( bookList.get(i).getAuthor().equals(name)){
                aut.add(bookList.get(i));
            }
        }
        return new ResponseEntity( aut , HttpStatus.FOUND) ;
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity getBooksByGenre ( @RequestParam("author") String name ){
        ArrayList < Book > aut = new ArrayList<>() ;
        for ( int i = 0 ; i < bookList.size() ; i++ ){
            if ( bookList.get(i).getGenre().equals(name)){
                aut.add(bookList.get(i));
            }
        }
        return new ResponseEntity( aut , HttpStatus.FOUND) ;
    }
}


/*
You need to implement the logic for following functionalities:

Save a book: /books/create-book - Return created book object as response
Get book object by Id: /books/get-book-by-id/{id} - Return book object as response
Get all books: /books/get-all-books - Return book object as response
Get books by author: /books/get-books-by-author?author=author+name
Get books by genre: /books/get-books-by-genre?genre=genre+name
Delete book by id: /books/delete-book-by-id/{id}
Delete all books: /books/delete-all-books\

Book Class - id, name, author, genre, constructors and getters-setters

Note:

/create-book should be a post request taking name, author and genre as part of the request body and id(should always be
a natural number) should be generated every time we create a new entry
For delete requests you don't have to return body of the deleted object, a success message is fine
Use ResponseEntity object to return response in the controller
Make sure you test all APIs in postman before you submit.
 */