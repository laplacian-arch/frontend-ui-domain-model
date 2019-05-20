package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.PageActionExample
import laplacian_arch.client_app_arch.model.PageAction
import laplacian.util.*
/**
 * page_action_example
 */
data class PageActionExampleRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the action which aggregates this page_action_example
     */
    override val action: PageAction,
    private val _record: Record = __record.normalizeCamelcase()
): PageActionExample, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The title of this page_action_example.
     */
    override val title: String
        get() = getOrThrow("title")

    /**
     * The given_params of this page_action_example.
     */
    override val givenParams: String? by _record

    /**
     * The given_state of this page_action_example.
     */
    override val givenState: String? by _record

    /**
     * The then of this page_action_example.
     */
    override val then: String
        get() = getOrThrow("then")

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, action: PageAction) = records.map {
            PageActionExampleRecord(it, _context, action = action)
        }
    }
}