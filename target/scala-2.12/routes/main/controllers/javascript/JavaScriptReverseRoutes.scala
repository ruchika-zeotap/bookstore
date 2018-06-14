
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ruchikagupta/Desktop/Projects/sample-backend/conf/routes
// @DATE:Wed Jun 13 14:36:36 IST 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package controllers.javascript {

  // @LINE:1
  class ReverseBookController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def healthCheck: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookController.healthCheck",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/healthcheck"})
        }
      """
    )
  
    // @LINE:4
    def deleteBook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookController.deleteBook",
      """
        function(bookId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/books/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("bookId", bookId0))})
        }
      """
    )
  
    // @LINE:3
    def updateBooksList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookController.updateBooksList",
      """
        function(bookId0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/books/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("bookId", bookId0))})
        }
      """
    )
  
    // @LINE:2
    def addBooks: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookController.addBooks",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/books"})
        }
      """
    )
  
    // @LINE:1
    def getBooksList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookController.getBooksList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/books"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseAuthorController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def updateAuthorList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorController.updateAuthorList",
      """
        function(authorId0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/author/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("authorId", authorId0))})
        }
      """
    )
  
    // @LINE:6
    def getAuthorList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorController.getAuthorList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/author"})
        }
      """
    )
  
    // @LINE:9
    def deleteAuthor: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorController.deleteAuthor",
      """
        function(authorId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/author/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("authorId", authorId0))})
        }
      """
    )
  
    // @LINE:7
    def addAuthor: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorController.addAuthor",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/author"})
        }
      """
    )
  
  }

  // @LINE:11
  class ReverseBookAuthorMappingController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def deleteBookAuthorMapping: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookAuthorMappingController.deleteBookAuthorMapping",
      """
        function(mappingId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/book-author/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("mappingId", mappingId0))})
        }
      """
    )
  
    // @LINE:11
    def addMapping: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookAuthorMappingController.addMapping",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/book-author"})
        }
      """
    )
  
    // @LINE:13
    def updateBookAuthorMappingList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookAuthorMappingController.updateBookAuthorMappingList",
      """
        function(mappingId0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/book-author/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("mappingId", mappingId0))})
        }
      """
    )
  
    // @LINE:12
    def getMappingList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BookAuthorMappingController.getMappingList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "bookstore/api/v1/book-author"})
        }
      """
    )
  
  }


}
