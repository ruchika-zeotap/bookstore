
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ruchikagupta/Desktop/Projects/sample-backend/conf/routes
// @DATE:Wed Jun 13 14:36:36 IST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package controllers {

  // @LINE:1
  class ReverseBookController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def healthCheck(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "bookstore/api/v1/healthcheck")
    }
  
    // @LINE:4
    def deleteBook(bookId:Integer): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "bookstore/api/v1/books/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("bookId", bookId)))
    }
  
    // @LINE:3
    def updateBooksList(bookId:Integer): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "bookstore/api/v1/books/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("bookId", bookId)))
    }
  
    // @LINE:2
    def addBooks(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "bookstore/api/v1/books")
    }
  
    // @LINE:1
    def getBooksList(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "bookstore/api/v1/books")
    }
  
  }

  // @LINE:6
  class ReverseAuthorController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def updateAuthorList(authorId:Integer): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "bookstore/api/v1/author/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("authorId", authorId)))
    }
  
    // @LINE:6
    def getAuthorList(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "bookstore/api/v1/author")
    }
  
    // @LINE:9
    def deleteAuthor(authorId:Integer): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "bookstore/api/v1/author/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("authorId", authorId)))
    }
  
    // @LINE:7
    def addAuthor(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "bookstore/api/v1/author")
    }
  
  }

  // @LINE:11
  class ReverseBookAuthorMappingController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def deleteBookAuthorMapping(mappingId:Integer): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "bookstore/api/v1/book-author/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("mappingId", mappingId)))
    }
  
    // @LINE:11
    def addMapping(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "bookstore/api/v1/book-author")
    }
  
    // @LINE:13
    def updateBookAuthorMappingList(mappingId:Integer): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "bookstore/api/v1/book-author/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("mappingId", mappingId)))
    }
  
    // @LINE:12
    def getMappingList(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "bookstore/api/v1/book-author")
    }
  
  }


}
