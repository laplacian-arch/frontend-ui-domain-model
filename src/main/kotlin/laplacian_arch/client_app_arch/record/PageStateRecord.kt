package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian_arch.client_app_arch.model.PageState


import laplacian_arch.client_app_arch.model.Page


import laplacian.util.*

/**
 * page_state
 */
data class PageStateRecord (
    private val __record: Record,
    private val _context: Context,

    /**
     * the page which aggregates this page_state
     */
    override val page: Page,

    private val _record: Record = __record.normalizeCamelcase()
): PageState, Record by _record {

    /**
     * The name of this page_state.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The type of this page_state.
     */
    override val type: String
        get() = getOrThrow("type")

    /**
     * The expression of this page_state.
     */
    override val expression: String
        get() = getOrThrow("expression")



    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, page: Page) = records.map {
            PageStateRecord(it, _context, page = page)
        }
    }
}