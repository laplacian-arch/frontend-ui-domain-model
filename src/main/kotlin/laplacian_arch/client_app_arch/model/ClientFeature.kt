package laplacian_arch.client_app_arch.model


import laplacian.util.*

/**
 * client_feature
 */
interface ClientFeature {
    /**
     * The feature_name of this client_feature.
     */
    val featureName: String
    /**
     * client
     */
    val client: Client
    /**
     * feature
     */
    val feature: Feature
}