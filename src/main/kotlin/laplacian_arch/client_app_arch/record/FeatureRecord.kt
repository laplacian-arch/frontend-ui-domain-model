package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.Feature
import laplacian_arch.client_app_arch.model.FeatureList
import laplacian_arch.client_app_arch.model.Page
import laplacian_arch.client_app_arch.model.ViewModelItem
import laplacian_arch.client_app_arch.model.ViewModelOperation
import laplacian.metamodel.model.Entity
import laplacian.metamodel.record.EntityRecord
import laplacian.util.*
/**
 * A feature of this application client.

 */
data class FeatureRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): Feature, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this feature.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The identifier of this feature.
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * The base_path of this feature.
     */
    override val basePath: String
        get() = getOrThrow("basePath") {
            identifier
        }

    /**
     * Whether this feature is the entry point of this application.

     */
    override val initial: Boolean
        get() = getOrThrow("initial") {
            false
        }

    /**
     * The list of pages the feature contains.

     */
    override val pages: List<Page>
        get() = PageRecord.from(_context).filter {
            it.featureName == name
        }

    /**
     * The data structure of the client side application state concerning thie feature.

     */
    override val state: List<ViewModelItem>
        = ViewModelItemRecord.from(_record.getList("state", emptyList()), _context, this)

    /**
     * The queries to the client side application state.

     */
    override val stateQueries: List<ViewModelOperation>
        = ViewModelOperationRecord.from(_record.getList("state_queries", emptyList()), _context, this)

    /**
     * The list of functions which mutate the current client side application state.

     */
    override val stateMutation: List<ViewModelOperation>
        = ViewModelOperationRecord.from(_record.getList("state_mutation", emptyList()), _context, this)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): FeatureList {
            return _context.get("features") as FeatureList
        }
    }
}