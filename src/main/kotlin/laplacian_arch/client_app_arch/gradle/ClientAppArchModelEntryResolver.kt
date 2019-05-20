package laplacian_arch.client_app_arch.gradle
import laplacian.gradle.task.generate.ExecutionContext
import laplacian.gradle.task.generate.ModelEntryResolver
import laplacian_arch.client_app_arch.model.PageList
import laplacian_arch.client_app_arch.record.PageRecord
import laplacian_arch.client_app_arch.model.ClientList
import laplacian_arch.client_app_arch.record.ClientRecord
import laplacian_arch.client_app_arch.model.WidgetList
import laplacian_arch.client_app_arch.record.WidgetRecord
import laplacian_arch.client_app_arch.model.FeatureList
import laplacian_arch.client_app_arch.record.FeatureRecord
import laplacian.util.*

class ClientAppArchModelEntryResolver: ModelEntryResolver {

    override fun resolves(key: String, model: Map<String, RecordList>): Boolean {
        return arrayOf(
            "pages",
            "clients",
            "widgets",
            "features"
        ).any { it == key }
    }

    override fun resolve(key: String, model: Map<String, RecordList>, context: ExecutionContext): Any? {
        return when (key) {
            "pages" -> PageList(
                model.getList<Record>("pages")
                     .mergeWithKeys()
                     .map{ PageRecord(it, context.currentModel) },
                context.currentModel
            )
            "clients" -> ClientList(
                model.getList<Record>("clients")
                     .mergeWithKeys()
                     .map{ ClientRecord(it, context.currentModel) },
                context.currentModel
            )
            "widgets" -> WidgetList(
                model.getList<Record>("widgets")
                     .mergeWithKeys()
                     .map{ WidgetRecord(it, context.currentModel) },
                context.currentModel
            )
            "features" -> FeatureList(
                model.getList<Record>("features")
                     .mergeWithKeys()
                     .map{ FeatureRecord(it, context.currentModel) },
                context.currentModel
            )
            else -> throw IllegalArgumentException("Unknown key: $key")
        }
    }
}