package laplacian_arch.client_app_arch.model
import com.github.jknack.handlebars.Context

import laplacian_arch.service_api_arch.model.Service

import laplacian.util.*

/**
 * A container for records of client
 */
class ClientList(
    list: List<Client>,
    val context: Context
) : List<Client> by list {
}