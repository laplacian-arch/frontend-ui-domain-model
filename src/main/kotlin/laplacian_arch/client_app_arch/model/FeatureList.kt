package laplacian_arch.client_app_arch.model
import com.github.jknack.handlebars.Context

import laplacian.metamodel.model.Entity

import laplacian.util.*

/**
 * A container for records of feature
 */
class FeatureList(
    list: List<Feature>,
    val context: Context
) : List<Feature> by list {
    /**
     * relating_entities
     */
    val relatingEntities: List<Entity>
        get() = flatMap{ it.relatingEntities }.distinctBy{ it.fqn }
}