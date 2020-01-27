@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.common.actions

import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

interface UiScrollableActions : UiBaseActions {

    fun scrollToStart() {
        view.perform(UiScrollableActionType.SCROLL_TO_START) {
            val scrollable = UiScrollable(UiSelector().resourceId(resourceName))
            scrollable.setAsVerticalList()
            scrollable.scrollToBeginning(Int.MAX_VALUE)
        }
    }

    fun scrollToEnd() {
        view.perform(UiScrollableActionType.SCROLL_TO_END) {
            val scrollable = UiScrollable(UiSelector().resourceId(resourceName))
            scrollable.setAsVerticalList()
            scrollable.scrollToEnd(Int.MAX_VALUE)
        }
    }

    fun <T> scrollToView(to: UiBaseView<T>) {
        view.perform(UiScrollableActionType.SCROLL_TO_VIEW) {
            val scrollable = UiScrollable(UiSelector().resourceId(resourceName))
            do {
                if (findObject(to.view.selector.bySelector) != null)
                    return@perform
            } while (scrollable.scrollForward())
            do {
                if (findObject(to.view.selector.bySelector) != null)
                return@perform
            } while (scrollable.scrollBackward())
            to.isDisplayed()
        }
    }

    enum class UiScrollableActionType : UiOperationType {
        SCROLL_TO_START,
        SCROLL_TO_END,
        SCROLL_TO_VIEW
    }
}