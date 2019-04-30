package laplacian_arch.client_app_arch.model

import laplacian_arch.service_api_arch.model.Service


import laplacian.util.*

/**
 * client
 */
interface Client {
    /**
     * The name of this client.
     */
    val name: String
    /**
     * The identifier of this client.
     */
    val identifier: String
    /**
     * The version of this client.
     */
    val version: String
    /**
     * client_features
     */
    val clientFeatures: List<ClientFeature>
    /**
     * features
     */
    val features: List<Feature>
        get() = clientFeatures.map{ it.feature }
    /**
     * service_dependencies
     */
    val serviceDependencies: List<ServiceDependency>
    /**
     * services
     */
    val services: List<Service>
        get() = serviceDependencies.map{ it.service }
}