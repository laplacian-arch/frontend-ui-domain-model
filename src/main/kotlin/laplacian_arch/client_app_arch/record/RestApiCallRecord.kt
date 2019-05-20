package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.RestApiCall
import laplacian_arch.client_app_arch.model.PageAction
import laplacian_arch.service_api_arch.model.Service
import laplacian_arch.service_api_arch.record.ServiceRecord
import laplacian_arch.service_api_arch.model.RestResource
import laplacian_arch.service_api_arch.record.RestResourceRecord
import laplacian_arch.service_api_arch.model.RestOperation
import laplacian_arch.service_api_arch.record.RestOperationRecord
import laplacian.util.*
/**
 * rest_api_call
 */
data class RestApiCallRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the action which aggregates this rest_api_call
     */
    override val action: PageAction,
    private val _record: Record = __record.normalizeCamelcase()
): RestApiCall, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The service_name of this rest_api_call.
     */
    override val serviceName: String
        get() = getOrThrow("serviceName")

    /**
     * The resource_name of this rest_api_call.
     */
    override val resourceName: String
        get() = getOrThrow("resourceName")

    /**
     * The http_method of this rest_api_call.
     */
    override val httpMethod: String
        get() = getOrThrow("httpMethod")

    /**
     * The path of this rest_api_call.
     */
    override val path: String
        get() = getOrThrow("path") {
            "/"
        }

    /**
     * service
     */
    override val service: Service
        get() = ServiceRecord.from(_context).find {
            it.name == serviceName
        } ?: throw IllegalStateException(
            "There is no service which meets the following condition(s): "
            + "RestApiCall.service_name == service.name (=$serviceName) "
            + "Possible values are: " + ServiceRecord.from(_context).map {
              "(${ it.name })"
            }.joinToString()
        )

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, action: PageAction) = records.map {
            RestApiCallRecord(it, _context, action = action)
        }
    }
}