package services;

import Exceptions.AuthorNotFoundException;
import Exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import models.Author;
import models.BookAuthorMapping;
import play.Logger;
import play.libs.Json;
import repositories.DatabaseRepository;

import java.util.ArrayList;
import java.util.List;
import services.BaseService;

public class AuthorService extends BaseService {


    private final Logger.ALogger logger = Logger.of(this.getClass());
    private DatabaseRepository databaseRepository;

    @Inject
    public AuthorService(DatabaseRepository databaseRepository){
        this.databaseRepository = databaseRepository;
    }

    public JsonNode getAuthorList() throws AuthorNotFoundException {
        List<Author> authorList =  databaseRepository.getAuthorList();
        ObjectNode authorListNode = Json.newObject();
        for (Author author : authorList) {
            ObjectNode authorNode = Json.newObject();
            authorNode.put("name", author.getName());
            authorNode.put("gender", author.getGender());
            authorNode.put("age", author.getAge());
            List <BookAuthorMapping> mapList = BookAuthorMapping.find.query().where().eq("author_id", author).findList();
            List <String> books = new ArrayList<>();
            for (BookAuthorMapping map: mapList) {
                books.add(map.getBook().getName());
            }
            authorNode.put("books", Json.toJson(books));
            authorListNode.set(author.getId().toString(), authorNode);
        }
        if (authorListNode == null) {
            throw new AuthorNotFoundException("No author found");
        } else {
            return authorListNode;
        }
    }

    public ObjectNode addAuthor(JsonNode request) throws InvalidRequestException {

        if (isValidRequest(request, "name", "gender", "age")) {
            String authorName = request.get("name").asText();
            String gender = request.get("gender").asText();
            Integer age = request.get("age").asInt();
            Author newAuthor = new Author();
            try {
                newAuthor.setName(authorName);
                newAuthor.setGender(gender);
                newAuthor.setAge(age);
                newAuthor.save();
                return getSuccessJson();
            } catch (Exception e) {
                logger.error("Add author error: " + e);
                return getErrorJson();
            }
        } else {
            throw new InvalidRequestException("Invalid request. Mandatory parameters are name, age, gender");
        }
    }

    public  ObjectNode updateAuthor(JsonNode request, Integer id) throws InvalidRequestException, AuthorNotFoundException {
        Author newAuthor = databaseRepository.getObject(Author.class, id);
        if (newAuthor == null) {
            throw new AuthorNotFoundException("Author not found for given id");
        }
        if (isValidRequest(request, "name") || isValidRequest(request, "gender") || isValidRequest(request, "age")) {
            try {
                if (request.hasNonNull("name")) {
                    String authorName = request.get("name").asText();
                    newAuthor.setName(authorName);
                }
                if (request.hasNonNull("gender")) {
                    String gender = request.get("gender").asText();
                    newAuthor.setGender(gender);
                }
                if (request.hasNonNull("age")) {
                    Integer age = request.get("age").asInt();
                    newAuthor.setAge(age);
                }
                newAuthor.save();
                return getSuccessJson();
            } catch (Exception e) {
                logger.error("Update author error: " + e);
                return getErrorJson();
            }
        } else {
            throw  new InvalidRequestException("Invalid request");
        }
    }

    public  ObjectNode deleteAuthor(Integer id) throws AuthorNotFoundException {
        Author author = databaseRepository.getObject(Author.class, id);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found for given id");
        }
        try {
            author.delete();
            return getSuccessJson();
        } catch (Exception e) {
            logger.error("Delete author error: " + e);
            return getErrorJson();
        }
    }
}