package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.ServiceDependency
import laplacian_arch.client_app_arch.model.Client
import laplacian_arch.service_api_arch.model.Service
import laplacian_arch.service_api_arch.record.ServiceRecord
import laplacian.util.*
/**
 * service_dependency
 */
data class ServiceDependencyRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the client which aggregates this service_dependency
     */
    override val client: Client,
    private val _record: Record = __record.normalizeCamelcase()
): ServiceDependency, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The service_name of this service_dependency.
     */
    override val serviceName: String
        get() = getOrThrow("serviceName")

    /**
     * service
     */
    override val service: Service
        get() = ServiceRecord.from(_context).find {
            it.name == serviceName
        } ?: throw IllegalStateException(
            "There is no service which meets the following condition(s): "
            + "ServiceDependency.service_name == service.name (=$serviceName) "
            + "Possible values are: " + ServiceRecord.from(_context).map {
              "(${ it.name })"
            }.joinToString()
        )

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, client: Client) = records.map {
            ServiceDependencyRecord(it, _context, client = client)
        }
    }
}