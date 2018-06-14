package controllers;

import Exceptions.AuthorNotFoundException;
import Exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AuthorService;
import services.BookService;

public class AuthorController extends Controller {

    private final Logger.ALogger logger = Logger.of(this.getClass());

    private AuthorService authorService;

    @Inject
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Result getAuthorList() {
        try {
            return ok(authorService.getAuthorList());
        } catch(AuthorNotFoundException e) {
            return noContent();
        } catch (Exception e) {
            return internalServerError(Json.newObject().put("status", "false"));
        }
    }

    public Result addAuthor() {
        JsonNode request = request().body().asJson();
        logger.info("Add author request: " + request);
        try {
            return ok(authorService.addAuthor(request)) ;
        } catch (InvalidRequestException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result updateAuthorList(Integer id) {
        JsonNode request = request().body().asJson();
        logger.info("Update author request: " + request);
        try {
            return ok(authorService.updateAuthor(request, id));
        } catch (InvalidRequestException | AuthorNotFoundException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result deleteAuthor(Integer id) {
        try {
            return ok(authorService.deleteAuthor(id));
        } catch (AuthorNotFoundException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

}

