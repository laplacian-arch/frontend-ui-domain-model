package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.Widget
import laplacian_arch.client_app_arch.model.WidgetList
import laplacian.metamodel.model.NamedParam
import laplacian.metamodel.record.NamedParamRecord
import laplacian.util.*
/**
 * A widget represents a type of a visible UI component.
 */
data class WidgetRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): Widget, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The group of this widget.
     */
    override val group: String
        get() = getOrThrow("group")

    /**
     * The name of this widget.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The template string which defines appearance of this widget.

     */
    override val template: String
        get() = getOrThrow("template")

    /**
     * The configurable parameters with whtch the appearance and behavior of this widget can be changed.

     */
    override val params: List<NamedParam>
        = NamedParamRecord.from(_record.getList("params", emptyList()), _context)

    /**
     * The events which can be used to hook certain actions.

     */
    override val events: List<NamedParam>
        = NamedParamRecord.from(_record.getList("events", emptyList()), _context)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): WidgetList {
            return _context.get("widgets") as WidgetList
        }
    }
}