package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.GraphqlQueryCall
import laplacian_arch.client_app_arch.model.PageAction
import laplacian_arch.service_api_arch.model.Service
import laplacian_arch.service_api_arch.record.ServiceRecord
import laplacian_arch.service_api_arch.model.GraphqlQuery
import laplacian_arch.service_api_arch.record.GraphqlQueryRecord
import laplacian.util.*
/**
 * graphql_query_call
 */
data class GraphqlQueryCallRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the action which aggregates this graphql_query_call
     */
    override val action: PageAction,
    private val _record: Record = __record.normalizeCamelcase()
): GraphqlQueryCall, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The service_name of this graphql_query_call.
     */
    override val serviceName: String
        get() = getOrThrow("serviceName")

    /**
     * The name of this graphql_query_call.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * service
     */
    override val service: Service
        get() = ServiceRecord.from(_context).find {
            it.name == serviceName
        } ?: throw IllegalStateException(
            "There is no service which meets the following condition(s): "
            + "GraphqlQueryCall.service_name == service.name (=$serviceName) "
            + "Possible values are: " + ServiceRecord.from(_context).map {
              "(${ it.name })"
            }.joinToString()
        )

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, action: PageAction) = records.map {
            GraphqlQueryCallRecord(it, _context, action = action)
        }
    }
}