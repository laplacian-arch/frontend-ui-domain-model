package laplacian_arch.client_app_arch.model
import com.github.jknack.handlebars.Context

import laplacian.metamodel.model.NamedParam

import laplacian.util.*

/**
 * A container for records of widget
 */
class WidgetList(
    list: List<Widget>,
    val context: Context
) : List<Widget> by list {
}