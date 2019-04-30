package laplacian_arch.client_app_arch.model
import com.github.jknack.handlebars.Context

import laplacian.metamodel.model.NamedValue

import laplacian.util.*

/**
 * A container for records of page
 */
class PageList(
    list: List<Page>,
    val context: Context
) : List<Page> by list {
}