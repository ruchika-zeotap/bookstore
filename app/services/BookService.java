package services;

import Exceptions.BookNotFoundException;
import Exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import models.Book;
import models.BookAuthorMapping;
import play.Logger;
import play.libs.Json;
import repositories.DatabaseRepository;

import java.util.*;

public class BookService extends BaseService {


    private final Logger.ALogger logger = Logger.of(this.getClass());
    private DatabaseRepository databaseRepository;

    @Inject
    public BookService(DatabaseRepository databaseRepository){
        this.databaseRepository = databaseRepository;
    }

    public JsonNode getBooksList() throws  BookNotFoundException {
        List<Book> booksList =  databaseRepository.getBooksList();
        ObjectNode booksListNode = Json.newObject();
        List<BookAuthorMapping> mappingList = databaseRepository.getBookAuthorMappingList();

        for (Book book : booksList) {
            ObjectNode bookNode = Json.newObject();
            bookNode.put("name", book.getName());
            bookNode.put("version", book.getVersion());
            List <BookAuthorMapping> mapList = BookAuthorMapping.find.query().where().eq("book_id", book).findList();
            List <String> authors = new ArrayList<>();
            for (BookAuthorMapping map: mapList) {
                authors.add(map.getAuthor().getName());
            }
            bookNode.put("authors", Json.toJson(authors));
            booksListNode.putPOJO(book.getId().toString(), bookNode);
        }
        if (booksListNode == null) {
            throw new BookNotFoundException("No author found");
        } else {
            return booksListNode;
        }
    }

    public ObjectNode addBook(JsonNode request) throws InvalidRequestException {
        if (isValidRequest(request, "name", "version")) {
            String bookName = request.get("name").asText();
            String version = request.get("version").asText();
            Book newBook = new Book();
            try {
                newBook.setName(bookName);
                newBook.setVersion(version);
                newBook.save();
                return getSuccessJson();
            } catch (Exception e) {
                logger.error("Add book error: " + e);
                return getErrorJson();
            }
        } else {
            throw new InvalidRequestException("Invalid request. Mandatory parameters are name, version");
        }
    }

    public  ObjectNode updateBooks(JsonNode request, Integer id) throws InvalidRequestException, BookNotFoundException {
        Book newBook = databaseRepository.getObject(Book.class, id);
        if (newBook == null) {
            throw new BookNotFoundException("Book not found for given id");
        }
        if (isValidRequest(request, "name") || isValidRequest(request, "version")) {
            try {
                if (request.has("name")) {
                    String bookName = request.get("name").asText();
                    newBook.setName(bookName);
                }
                if (request.has("version")) {
                    String version = request.get("version").asText();
                    newBook.setVersion(version);
                }
                newBook.save();
                return getSuccessJson();
            } catch (Exception e) {
                logger.error("Update book error: " + e);
                return getErrorJson();
            }
        } else {
            throw new InvalidRequestException("Invalid request.");
        }
    }

    public  ObjectNode deleteBook(Integer id) throws BookNotFoundException {
        Book book = databaseRepository.getObject(Book.class, id);
        if (book == null) {
            throw new BookNotFoundException("Author not found for given id");
        }
        try {
            book.delete();
            return getSuccessJson();
        } catch (Exception e) {
            logger.error("Delete book error: " + e);
            return getErrorJson();
        }
    }
}