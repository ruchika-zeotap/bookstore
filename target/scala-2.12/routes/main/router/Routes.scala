
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ruchikagupta/Desktop/Projects/sample-backend/conf/routes
// @DATE:Wed Jun 13 14:36:36 IST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  BookController_0: controllers.BookController,
  // @LINE:6
  AuthorController_1: controllers.AuthorController,
  // @LINE:11
  BookAuthorMappingController_2: controllers.BookAuthorMappingController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    BookController_0: controllers.BookController,
    // @LINE:6
    AuthorController_1: controllers.AuthorController,
    // @LINE:11
    BookAuthorMappingController_2: controllers.BookAuthorMappingController
  ) = this(errorHandler, BookController_0, AuthorController_1, BookAuthorMappingController_2, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, BookController_0, AuthorController_1, BookAuthorMappingController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/books""", """controllers.BookController.getBooksList"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/books""", """controllers.BookController.addBooks"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/books/""" + "$" + """bookId<[^/]+>""", """controllers.BookController.updateBooksList(bookId:Integer)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/books/""" + "$" + """bookId<[^/]+>""", """controllers.BookController.deleteBook(bookId:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/author""", """controllers.AuthorController.getAuthorList"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/author""", """controllers.AuthorController.addAuthor"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/author/""" + "$" + """authorId<[^/]+>""", """controllers.AuthorController.updateAuthorList(authorId:Integer)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/author/""" + "$" + """authorId<[^/]+>""", """controllers.AuthorController.deleteAuthor(authorId:Integer)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/book-author""", """controllers.BookAuthorMappingController.addMapping"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/book-author""", """controllers.BookAuthorMappingController.getMappingList"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/book-author/""" + "$" + """mappingId<[^/]+>""", """controllers.BookAuthorMappingController.updateBookAuthorMappingList(mappingId:Integer)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/book-author/""" + "$" + """mappingId<[^/]+>""", """controllers.BookAuthorMappingController.deleteBookAuthorMapping(mappingId:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bookstore/api/v1/healthcheck""", """controllers.BookController.healthCheck"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_BookController_getBooksList0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/books")))
  )
  private[this] lazy val controllers_BookController_getBooksList0_invoker = createInvoker(
    BookController_0.getBooksList,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookController",
      "getBooksList",
      Nil,
      "GET",
      this.prefix + """bookstore/api/v1/books""",
      """""",
      Seq()
    )
  )

  // @LINE:2
  private[this] lazy val controllers_BookController_addBooks1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/books")))
  )
  private[this] lazy val controllers_BookController_addBooks1_invoker = createInvoker(
    BookController_0.addBooks,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookController",
      "addBooks",
      Nil,
      "POST",
      this.prefix + """bookstore/api/v1/books""",
      """""",
      Seq()
    )
  )

  // @LINE:3
  private[this] lazy val controllers_BookController_updateBooksList2_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/books/"), DynamicPart("bookId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BookController_updateBooksList2_invoker = createInvoker(
    BookController_0.updateBooksList(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookController",
      "updateBooksList",
      Seq(classOf[Integer]),
      "PUT",
      this.prefix + """bookstore/api/v1/books/""" + "$" + """bookId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_BookController_deleteBook3_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/books/"), DynamicPart("bookId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BookController_deleteBook3_invoker = createInvoker(
    BookController_0.deleteBook(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookController",
      "deleteBook",
      Seq(classOf[Integer]),
      "DELETE",
      this.prefix + """bookstore/api/v1/books/""" + "$" + """bookId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val controllers_AuthorController_getAuthorList4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/author")))
  )
  private[this] lazy val controllers_AuthorController_getAuthorList4_invoker = createInvoker(
    AuthorController_1.getAuthorList,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorController",
      "getAuthorList",
      Nil,
      "GET",
      this.prefix + """bookstore/api/v1/author""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_AuthorController_addAuthor5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/author")))
  )
  private[this] lazy val controllers_AuthorController_addAuthor5_invoker = createInvoker(
    AuthorController_1.addAuthor,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorController",
      "addAuthor",
      Nil,
      "POST",
      this.prefix + """bookstore/api/v1/author""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_AuthorController_updateAuthorList6_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/author/"), DynamicPart("authorId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AuthorController_updateAuthorList6_invoker = createInvoker(
    AuthorController_1.updateAuthorList(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorController",
      "updateAuthorList",
      Seq(classOf[Integer]),
      "PUT",
      this.prefix + """bookstore/api/v1/author/""" + "$" + """authorId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_AuthorController_deleteAuthor7_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/author/"), DynamicPart("authorId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AuthorController_deleteAuthor7_invoker = createInvoker(
    AuthorController_1.deleteAuthor(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorController",
      "deleteAuthor",
      Seq(classOf[Integer]),
      "DELETE",
      this.prefix + """bookstore/api/v1/author/""" + "$" + """authorId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_BookAuthorMappingController_addMapping8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/book-author")))
  )
  private[this] lazy val controllers_BookAuthorMappingController_addMapping8_invoker = createInvoker(
    BookAuthorMappingController_2.addMapping,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookAuthorMappingController",
      "addMapping",
      Nil,
      "POST",
      this.prefix + """bookstore/api/v1/book-author""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_BookAuthorMappingController_getMappingList9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/book-author")))
  )
  private[this] lazy val controllers_BookAuthorMappingController_getMappingList9_invoker = createInvoker(
    BookAuthorMappingController_2.getMappingList,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookAuthorMappingController",
      "getMappingList",
      Nil,
      "GET",
      this.prefix + """bookstore/api/v1/book-author""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_BookAuthorMappingController_updateBookAuthorMappingList10_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/book-author/"), DynamicPart("mappingId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BookAuthorMappingController_updateBookAuthorMappingList10_invoker = createInvoker(
    BookAuthorMappingController_2.updateBookAuthorMappingList(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookAuthorMappingController",
      "updateBookAuthorMappingList",
      Seq(classOf[Integer]),
      "PUT",
      this.prefix + """bookstore/api/v1/book-author/""" + "$" + """mappingId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_BookAuthorMappingController_deleteBookAuthorMapping11_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/book-author/"), DynamicPart("mappingId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BookAuthorMappingController_deleteBookAuthorMapping11_invoker = createInvoker(
    BookAuthorMappingController_2.deleteBookAuthorMapping(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookAuthorMappingController",
      "deleteBookAuthorMapping",
      Seq(classOf[Integer]),
      "DELETE",
      this.prefix + """bookstore/api/v1/book-author/""" + "$" + """mappingId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_BookController_healthCheck12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bookstore/api/v1/healthcheck")))
  )
  private[this] lazy val controllers_BookController_healthCheck12_invoker = createInvoker(
    BookController_0.healthCheck,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BookController",
      "healthCheck",
      Nil,
      "GET",
      this.prefix + """bookstore/api/v1/healthcheck""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_BookController_getBooksList0_route(params) =>
      call { 
        controllers_BookController_getBooksList0_invoker.call(BookController_0.getBooksList)
      }
  
    // @LINE:2
    case controllers_BookController_addBooks1_route(params) =>
      call { 
        controllers_BookController_addBooks1_invoker.call(BookController_0.addBooks)
      }
  
    // @LINE:3
    case controllers_BookController_updateBooksList2_route(params) =>
      call(params.fromPath[Integer]("bookId", None)) { (bookId) =>
        controllers_BookController_updateBooksList2_invoker.call(BookController_0.updateBooksList(bookId))
      }
  
    // @LINE:4
    case controllers_BookController_deleteBook3_route(params) =>
      call(params.fromPath[Integer]("bookId", None)) { (bookId) =>
        controllers_BookController_deleteBook3_invoker.call(BookController_0.deleteBook(bookId))
      }
  
    // @LINE:6
    case controllers_AuthorController_getAuthorList4_route(params) =>
      call { 
        controllers_AuthorController_getAuthorList4_invoker.call(AuthorController_1.getAuthorList)
      }
  
    // @LINE:7
    case controllers_AuthorController_addAuthor5_route(params) =>
      call { 
        controllers_AuthorController_addAuthor5_invoker.call(AuthorController_1.addAuthor)
      }
  
    // @LINE:8
    case controllers_AuthorController_updateAuthorList6_route(params) =>
      call(params.fromPath[Integer]("authorId", None)) { (authorId) =>
        controllers_AuthorController_updateAuthorList6_invoker.call(AuthorController_1.updateAuthorList(authorId))
      }
  
    // @LINE:9
    case controllers_AuthorController_deleteAuthor7_route(params) =>
      call(params.fromPath[Integer]("authorId", None)) { (authorId) =>
        controllers_AuthorController_deleteAuthor7_invoker.call(AuthorController_1.deleteAuthor(authorId))
      }
  
    // @LINE:11
    case controllers_BookAuthorMappingController_addMapping8_route(params) =>
      call { 
        controllers_BookAuthorMappingController_addMapping8_invoker.call(BookAuthorMappingController_2.addMapping)
      }
  
    // @LINE:12
    case controllers_BookAuthorMappingController_getMappingList9_route(params) =>
      call { 
        controllers_BookAuthorMappingController_getMappingList9_invoker.call(BookAuthorMappingController_2.getMappingList)
      }
  
    // @LINE:13
    case controllers_BookAuthorMappingController_updateBookAuthorMappingList10_route(params) =>
      call(params.fromPath[Integer]("mappingId", None)) { (mappingId) =>
        controllers_BookAuthorMappingController_updateBookAuthorMappingList10_invoker.call(BookAuthorMappingController_2.updateBookAuthorMappingList(mappingId))
      }
  
    // @LINE:14
    case controllers_BookAuthorMappingController_deleteBookAuthorMapping11_route(params) =>
      call(params.fromPath[Integer]("mappingId", None)) { (mappingId) =>
        controllers_BookAuthorMappingController_deleteBookAuthorMapping11_invoker.call(BookAuthorMappingController_2.deleteBookAuthorMapping(mappingId))
      }
  
    // @LINE:17
    case controllers_BookController_healthCheck12_route(params) =>
      call { 
        controllers_BookController_healthCheck12_invoker.call(BookController_0.healthCheck)
      }
  }
}
