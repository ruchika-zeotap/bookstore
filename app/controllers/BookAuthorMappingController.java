package controllers;

import Exceptions.AuthorNotFoundException;
import Exceptions.BookNotFoundException;
import Exceptions.InvalidRequestException;
import Exceptions.MappingNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AuthorService;
import services.BookAuthorMappingService;

public class BookAuthorMappingController extends Controller {

    private final Logger.ALogger logger = Logger.of(this.getClass());

    private BookAuthorMappingService bookAuthorMappingService;

    @Inject
    public BookAuthorMappingController(BookAuthorMappingService bookAuthorMappingService) {
        this.bookAuthorMappingService = bookAuthorMappingService;
    }

    public Result getMappingList() {
        try {
            return ok(bookAuthorMappingService.getMappingList());
        } catch(MappingNotFoundException e) {
            return noContent();
        } catch (Exception e) {
            return internalServerError(Json.newObject().put("status", "false"));
        }
    }

    public Result addMapping() {
        JsonNode request = request().body().asJson();
        logger.info("Add mapping request: " + request);
        try {
            return ok(bookAuthorMappingService.addBookAuthorMapping(request)) ;
        } catch (BookNotFoundException | AuthorNotFoundException | InvalidRequestException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result updateBookAuthorMappingList(Integer id) {
        JsonNode request = request().body().asJson();
        logger.info("Update mapping request: " + request);
        try {
            return ok(bookAuthorMappingService.updateMapping(request, id));
        } catch (InvalidRequestException | MappingNotFoundException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result deleteBookAuthorMapping(Integer id) {
        try {
            return ok(bookAuthorMappingService.deleteAuthorBookMapping(id));
        } catch (MappingNotFoundException e) {
            logger.error(e.getMessage());
            return badRequest(e.getMessage());
        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }

}

