package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.Page
import laplacian_arch.client_app_arch.model.PageList
import laplacian_arch.client_app_arch.model.Feature
import laplacian_arch.client_app_arch.model.PageItem
import laplacian_arch.client_app_arch.model.PageAction
import laplacian_arch.client_app_arch.model.PageState
import laplacian.metamodel.model.NamedValue
import laplacian.metamodel.record.NamedValueRecord
import laplacian.util.*
/**
 * A page of this application.

 */
data class PageRecord (
    private val __record: Record,
    private val _context: Context,
    private val _record: Record = __record.normalizeCamelcase()
): Page, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this page.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The feature_name of this page.
     */
    override val featureName: String
        get() = getOrThrow("featureName")

    /**
     * The identifier of this page.
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * The path of this page.
     */
    override val path: String? by _record

    /**
     * Whether this page is the default entry point of the feature.

     */
    override val initial: Boolean
        get() = getOrThrow("initial") {
            false
        }

    /**
     * Whether this page is shown in a modal window.

     */
    override val modal: Boolean
        get() = getOrThrow("modal") {
            false
        }

    /**
     * The condition which must be satisfied before rendering the content of this page.

     */
    override val renderPageWhen: String? by _record

    /**
     * The feature containing this page.

     */
    override val feature: Feature
        get() = FeatureRecord.from(_context).find {
            it.name == featureName
        } ?: throw IllegalStateException(
            "There is no feature which meets the following condition(s): "
            + "Page.feature_name == feature.name (=$featureName) "
            + "Possible values are: " + FeatureRecord.from(_context).map {
              "(${ it.name })"
            }.joinToString()
        )

    /**
     * The list of UI items this page consists of.

     */
    override val items: List<PageItem>
        = PageItemRecord.from(_record.getList("items", emptyList()), _context, this)

    /**
     * The list of handlers which is called when a certain event in this page happens.

     */
    override val actions: List<PageAction>
        = PageActionRecord.from(_record.getList("actions", emptyList()), _context, this)

    /**
     * A subset of the client side application state that relevant to the function of this page.

     */
    override val states: List<PageState>
        = PageStateRecord.from(_record.getList("states", emptyList()), _context, this)

    /**
     * The list of event which may happen in this page.

     */
    override val events: List<NamedValue>
        = NamedValueRecord.from(_record.getList("events", emptyList()), _context)

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(_context: Context): PageList {
            return _context.get("pages") as PageList
        }
    }
}