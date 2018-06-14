
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ruchikagupta/Desktop/Projects/sample-backend/conf/routes
// @DATE:Wed Jun 13 14:36:36 IST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseBookController BookController = new controllers.ReverseBookController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAuthorController AuthorController = new controllers.ReverseAuthorController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseBookAuthorMappingController BookAuthorMappingController = new controllers.ReverseBookAuthorMappingController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseBookController BookController = new controllers.javascript.ReverseBookController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAuthorController AuthorController = new controllers.javascript.ReverseAuthorController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseBookAuthorMappingController BookAuthorMappingController = new controllers.javascript.ReverseBookAuthorMappingController(RoutesPrefix.byNamePrefix());
  }

}
