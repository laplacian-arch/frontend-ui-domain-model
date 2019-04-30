package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian_arch.client_app_arch.model.Client

import laplacian_arch.client_app_arch.model.ClientList


import laplacian_arch.client_app_arch.model.ClientFeature


import laplacian_arch.client_app_arch.model.Feature


import laplacian_arch.client_app_arch.model.ServiceDependency


import laplacian_arch.service_api_arch.model.Service

import laplacian_arch.service_api_arch.record.ServiceRecord


import laplacian.util.*

/**
 * client
 */
data class ClientRecord (
    private val __record: Record,
    private val _context: Context,

    private val _record: Record = __record.normalizeCamelcase()
): Client, Record by _record {

    /**
     * The name of this client.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The identifier of this client.
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * The version of this client.
     */
    override val version: String
        get() = getOrThrow("version")


    /**
     * client_features
     */
    override val clientFeatures: List<ClientFeature>
        = ClientFeatureRecord.from(getList("client_features", emptyList()), _context, this)


    /**
     * service_dependencies
     */
    override val serviceDependencies: List<ServiceDependency>
        = ServiceDependencyRecord.from(getList("service_dependencies", emptyList()), _context, this)


    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): ClientList {
            return _context.get("clients") as ClientList
        }
    }
}