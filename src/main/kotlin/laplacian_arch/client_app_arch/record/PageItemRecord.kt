package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.PageItem
import laplacian_arch.client_app_arch.model.Page
import laplacian_arch.client_app_arch.model.Widget
import laplacian.metamodel.model.NamedValue
import laplacian.metamodel.record.NamedValueRecord
import laplacian.util.*
/**
 * A page item represents a (sometimes potentially) visible UI component in the page.

 */
data class PageItemRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the page which aggregates this page_item
     */
    override val page: Page? = null,
    /**
     * the parent which aggregates this page_item
     */
    override val parent: PageItem? = null,
    private val _record: Record = __record.normalizeCamelcase()
): PageItem, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * 名称
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * 識別子
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * The widget_group of this page_item.
     */
    override val widgetGroup: String
        get() = getOrThrow("widgetGroup")

    /**
     * The widget_name of this page_item.
     */
    override val widgetName: String
        get() = getOrThrow("widgetName")

    /**
     * widget
     */
    override val widget: Widget
        get() = WidgetRecord.from(_context).find {
            it.group == widgetGroup &&
            it.name == widgetName
        } ?: throw IllegalStateException(
            "There is no widget which meets the following condition(s): "
            + "PageItem.widget_group == widget.group (=$widgetGroup) "
            + "PageItem.widget_name == widget.name (=$widgetName) "
            + "Possible values are: " + WidgetRecord.from(_context).map {
              "(${ it.group },${ it.name })"
            }.joinToString()
        )

    /**
     * widget_params
     */
    override val widgetParams: List<NamedValue>
        = NamedValueRecord.from(_record.getList("widget_params", emptyList()), _context)

    /**
     * events
     */
    override val events: List<NamedValue>
        = NamedValueRecord.from(_record.getList("events", emptyList()), _context)

    /**
     * The list of items this item contains.

     */
    override val children: List<PageItem>
        = PageItemRecord.from(_record.getList("children", emptyList()), _context, this)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, page: Page? = null) = records.map {
            PageItemRecord(it, _context, page = page)
        }
        fun from(records: RecordList, _context: Context, parent: PageItem? = null) = records.map {
            PageItemRecord(it, _context, parent = parent)
        }
    }
}