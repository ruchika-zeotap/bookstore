
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/ruchikagupta/Desktop/Projects/sample-backend/conf/routes
// @DATE:Wed Jun 13 14:36:36 IST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
