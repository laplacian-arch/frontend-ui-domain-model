package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.NamedValue


import laplacian.util.*

/**
 * A page item represents a (sometimes potentially) visible UI component in the page.

 */
interface PageItem {
    /**
     * 名称
     */
    val name: String
    /**
     * 識別子
     */
    val identifier: String
    /**
     * The widget_group of this page_item.
     */
    val widgetGroup: String
    /**
     * The widget_name of this page_item.
     */
    val widgetName: String
    /**
     * The page this item belongs to. (It is null when this item is not a top-level item)

     */
    val page: Page?
    /**
     * widget
     */
    val widget: Widget
    /**
     * widget_params
     */
    val widgetParams: List<NamedValue>
    /**
     * events
     */
    val events: List<NamedValue>
    /**
     * The list of items this item contains.

     */
    val children: List<PageItem>
    /**
     * The item which contains this one. (It is null when this item is a top-level item)

     */
    val parent: PageItem?
}