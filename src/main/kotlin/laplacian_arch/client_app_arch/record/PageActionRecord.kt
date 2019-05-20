package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.PageAction
import laplacian_arch.client_app_arch.model.Page
import laplacian.metamodel.model.NamedParam
import laplacian.metamodel.record.NamedParamRecord
import laplacian_arch.client_app_arch.model.RestApiCall
import laplacian_arch.client_app_arch.model.GraphqlQueryCall
import laplacian_arch.client_app_arch.model.PageActionExample
import laplacian.util.*
/**
 * An page action is the handler which is called when a certain event happens in this page.

 */
data class PageActionRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the page which aggregates this page_action
     */
    override val page: Page,
    private val _record: Record = __record.normalizeCamelcase()
): PageAction, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this page_action.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The identifier of this page_action.
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerHyphenize()
        }

    /**
     * The description of this page_action.
     */
    override val description: String
        get() = getOrThrow("description") {
            "The ${name} action in ${page.name}"
        }

    /**
     * params
     */
    override val params: List<NamedParam>
        = NamedParamRecord.from(_record.getList("params", emptyList()), _context)

    /**
     * rest_api_call
     */
    override val restApiCall: RestApiCall?
        = getOrNull<Record>("rest_api_call")?.let{ RestApiCallRecord(it, _context, this) }

    /**
     * graphql_query_call
     */
    override val graphqlQueryCall: GraphqlQueryCall?
        = getOrNull<Record>("graphql_query_call")?.let{ GraphqlQueryCallRecord(it, _context, this) }

    /**
     * examples
     */
    override val examples: List<PageActionExample>
        = PageActionExampleRecord.from(_record.getList("examples", emptyList()), _context, this)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, page: Page) = records.map {
            PageActionRecord(it, _context, page = page)
        }
    }
}