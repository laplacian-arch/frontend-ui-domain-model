package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian.gradle.task.generate.model.Project
import laplacian_arch.client_app_arch.model.ViewModelOperation
import laplacian_arch.client_app_arch.model.Feature
import laplacian.metamodel.model.NamedParam
import laplacian.metamodel.record.NamedParamRecord
import laplacian.metamodel.model.Entity
import laplacian.metamodel.record.EntityRecord
import laplacian.util.*
/**
 * view_model_operation
 */
data class ViewModelOperationRecord (
    private val __record: Record,
    private val _context: Context,
    /**
     * the feature which aggregates this view_model_operation
     */
    override val feature: Feature,
    private val _record: Record = __record.normalizeCamelcase()
): ViewModelOperation, Record by _record {
    /**
     * The laplacian module project definition.
     */
    private val project: Project
        get() = _context.get("project") as Project


    /**
     * The name of this view_model_operation.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The description of this view_model_operation.
     */
    override val description: String? by _record

    /**
     * The return_type of this view_model_operation.
     */
    override val returnType: String
        get() = getOrThrow("returnType") {
            entity?.let{ it.className + if (multiple) "[]" else "" }
        }

    /**
     * Defines this view_model_operation is multiple or not.
     */
    override val multiple: Boolean
        get() = getOrThrow("multiple") {
            false
        }

    /**
     * The return_entity_name of this view_model_operation.
     */
    override val returnEntityName: String? by _record

    /**
     * The snippet of this view_model_operation.
     */
    override val snippet: String
        get() = getOrThrow("snippet")

    /**
     * params
     */
    override val params: List<NamedParam>
        = NamedParamRecord.from(_record.getList("params", emptyList()), _context)

    /**
     * entity
     */
    override val entity: Entity?
        get() = EntityRecord.from(_context).find {
            it.name == returnEntityName
        }

    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, feature: Feature) = records.map {
            ViewModelOperationRecord(it, _context, feature = feature)
        }
    }
}