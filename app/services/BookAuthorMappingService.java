package services;

import Exceptions.AuthorNotFoundException;
import Exceptions.BookNotFoundException;
import Exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import models.BookAuthorMapping;
import models.Book;
import models.Author;
import play.Logger;
import play.libs.Json;
import repositories.DatabaseRepository;
import Exceptions.MappingNotFoundException;

import java.util.List;

public class BookAuthorMappingService extends BaseService {


    private final Logger.ALogger logger = Logger.of(this.getClass());
    private DatabaseRepository databaseRepository;

    @Inject
    public BookAuthorMappingService(DatabaseRepository databaseRepository){
        this.databaseRepository = databaseRepository;
    }

    private Integer getBookId(Book book) {
        return book.getId();
    }

    private Integer getAuthorId(Author author) {
        return author.getId();
    }

    private Book getBookObject(Integer bookId) {
        return databaseRepository.getObject(Book.class, bookId);
    }

    private Author getAuthorObject(Integer authorId) {
        return databaseRepository.getObject(Author.class, authorId);
    }


    public JsonNode getMappingList() throws MappingNotFoundException {
        List<BookAuthorMapping> mappingList =  databaseRepository.getBookAuthorMappingList();
        ObjectNode mappingListNode = Json.newObject();
        for (BookAuthorMapping mapping : mappingList) {
            ObjectNode mappingNode = Json.newObject();
            mappingNode.put("book_id", this.getBookId(mapping.getBook()));
            mappingNode.put("author_id", this.getAuthorId(mapping.getAuthor()));
            mappingListNode.putPOJO(mapping.getId().toString(), mappingNode);
        }
        if (mappingListNode == null) {
            throw new MappingNotFoundException("No mapping found");
        } else {
            return mappingListNode;
        }
    }

    public ObjectNode addBookAuthorMapping(JsonNode request) throws AuthorNotFoundException, BookNotFoundException, InvalidRequestException {
        if (isValidRequest(request, "book_id", "author_id")) {
            Integer bookId = request.get("book_id").asInt();
            Integer authorId = request.get("author_id").asInt();
            BookAuthorMapping newMapping = new BookAuthorMapping();
            Book book = this.getBookObject(bookId);
            Author author = this.getAuthorObject(authorId);
            if (book != null) {
                newMapping.setBook(book);
            } else {
                throw new BookNotFoundException("Book id doesn't exist");
            }
            if (author != null) {
                newMapping.setAuthor(author);
            } else {
                throw new AuthorNotFoundException("Author id doesn't exist");
            }
            try {
                newMapping.save();
                return getSuccessJson();
            } catch (Exception e) {
                logger.error("Add mapping error: " + e);
                return getErrorJson();
            }
        } else {
            throw new InvalidRequestException("Invalid request. mandatory fields are book_id, author_id");
        }
    }

    public  ObjectNode updateMapping(JsonNode request, Integer id) throws MappingNotFoundException, InvalidRequestException{
        BookAuthorMapping mapping = databaseRepository.getObject(BookAuthorMapping.class, id);
        if (mapping == null) {
            throw new MappingNotFoundException("Author not found for given id");
        }
        if (isValidRequest(request, "book_id") || isValidRequest(request, "author_id")) {
            try {
                if (request.hasNonNull("book_id")) {
                    Integer bookId = request.get("book_id").asInt();
                    mapping.setBook(this.getBookObject(bookId));
                }
                if (request.hasNonNull("author_id")) {
                    Integer authorId = request.get("author_id").asInt();
                    mapping.setAuthor(this.getAuthorObject(authorId));
                }
                mapping.save();
                return getSuccessJson();
            } catch (Exception e) {
                logger.error("Update mapping error: " + e);
                return getErrorJson();
            }
        } else {
            throw new InvalidRequestException("Invalid request");
        }
    }

    public  ObjectNode deleteAuthorBookMapping(Integer id) throws MappingNotFoundException {
        BookAuthorMapping mapping = databaseRepository.getObject(BookAuthorMapping.class, id);
        if (mapping == null) {
            throw new MappingNotFoundException("Mapping not found");
        }
        try {
            mapping.delete();
            return getSuccessJson();
        } catch (Exception e) {
            logger.error("Delete mapping error: " + e);
            return getErrorJson();
        }
    }


}