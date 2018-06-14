package controllers;

import Exceptions.BookNotFoundException;
import Exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.BookService;

public class BookController extends Controller {

    private final Logger.ALogger logger = Logger.of(this.getClass());

    private BookService bookService;

    @Inject
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Result getBooksList() {
        try {
            return ok(bookService.getBooksList());
        } catch(BookNotFoundException e) {
            return noContent();
        } catch (Exception e) {
            return internalServerError(Json.newObject().put("status", "false"));
        }
    }

    public Result addBooks() {
        JsonNode request = request().body().asJson();
        logger.info("Add book request: " + request);
        try {
            return ok(bookService.addBook(request)) ;
        } catch (InvalidRequestException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result updateBooksList(Integer id) {
        JsonNode request = request().body().asJson();
        logger.info("Update book request: " + request);
        try {
            return ok(bookService.updateBooks(request, id));
        } catch (InvalidRequestException | BookNotFoundException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result deleteBook(Integer id) {
        try {
            return ok(bookService.deleteBook(id));
        } catch (BookNotFoundException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result healthCheck() {
        return ok("OK");
    }

}

