package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.ClientFeature
import laplacian_arch.client_app_arch.model.Client
import laplacian_arch.client_app_arch.model.Feature
import laplacian.util.*
/**
 * client_feature
 */
data class ClientFeatureRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the client which aggregates this client_feature
     */
    override val client: Client,
    private val _record: Record = __record.normalizeCamelcase()
): ClientFeature, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The feature_name of this client_feature.
     */
    override val featureName: String
        get() = getOrThrow("featureName")

    /**
     * feature
     */
    override val feature: Feature
        get() = FeatureRecord.from(_context).find {
            it.name == featureName
        } ?: throw IllegalStateException(
            "There is no feature which meets the following condition(s): "
            + "ClientFeature.feature_name == feature.name (=$featureName) "
            + "Possible values are: " + FeatureRecord.from(_context).map {
              "(${ it.name })"
            }.joinToString()
        )

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, client: Client) = records.map {
            ClientFeatureRecord(it, _context, client = client)
        }
    }
}