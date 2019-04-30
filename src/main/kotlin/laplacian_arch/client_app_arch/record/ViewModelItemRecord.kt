package laplacian_arch.client_app_arch.record
import com.github.jknack.handlebars.Context
import laplacian_arch.client_app_arch.model.ViewModelItem


import laplacian.metamodel.model.Entity

import laplacian.metamodel.record.EntityRecord


import laplacian_arch.client_app_arch.model.Feature


import laplacian.util.*

/**
 * view_model_item
 */
data class ViewModelItemRecord (
    private val __record: Record,
    private val _context: Context,

    /**
     * the feature which aggregates this view_model_item
     */
    override val feature: Feature? = null,

    /**
     * the parent which aggregates this view_model_item
     */
    override val parent: ViewModelItem? = null,

    private val _record: Record = __record.normalizeCamelcase()
): ViewModelItem, Record by _record {

    /**
     * The name of this view_model_item.
     */
    override val name: String
        get() = getOrThrow("name")

    /**
     * The identifier of this view_model_item.
     */
    override val identifier: String
        get() = getOrThrow("identifier") {
            name.lowerUnderscorize()
        }

    /**
     * The description of this view_model_item.
     */
    override val description: String
        get() = getOrThrow("description") {
            name
        }

    /**
     * define wether this item has multiple values or not
     */
    override val multiple: Boolean
        get() = getOrThrow("multiple") {
            false
        }

    /**
     * The name of the entity which is used as the type of this item. (This property can not be used with the type property.)

     */
    override val entityName: String? by _record

    /**
     * The type of this view_model_item.
     */
    override val type: String
        get() = getOrThrow("type") {
            entity?.let{ it.className + if (multiple) "[]" else "" }
        }

    /**
     * The default_value of this view_model_item.
     */
    override val defaultValue: String? by _record


    /**
     * The entity which is used as the type of this item. (This can be null if this item has a simple-type.)

     */
    override val entity: Entity?
        get() = EntityRecord.from(_context).find {
            it.name == entityName
        }


    /**
     * children
     */
    override val children: List<ViewModelItem>
        = ViewModelItemRecord.from(getList("children", emptyList()), _context, this)


    companion object {
        /**
         * creates record list from list of map
         */
        fun from(records: RecordList, _context: Context, feature: Feature? = null) = records.map {
            ViewModelItemRecord(it, _context, feature = feature)
        }
        fun from(records: RecordList, _context: Context, parent: ViewModelItem? = null) = records.map {
            ViewModelItemRecord(it, _context, parent = parent)
        }
    }
}