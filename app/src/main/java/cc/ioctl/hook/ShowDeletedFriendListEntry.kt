/*
 * QAuxiliary - An Xposed module for QQ/TIM
 * Copyright (C) 2019-2022 qwq233@qwq2333.top
 * https://github.com/cinit/QAuxiliary
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by QAuxiliary contributors.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 * <https://github.com/cinit/QAuxiliary/blob/master/LICENSE.md>.
 */
package cc.ioctl.hook

import android.content.Context
import android.view.View
import io.github.qauxv.base.ISwitchCellAgent
import io.github.qauxv.base.IUiItemAgent
import io.github.qauxv.base.IUiItemAgentProvider
import io.github.qauxv.base.Invalidatable
import io.github.qauxv.base.annotation.UiItemAgentEntry
import io.github.qauxv.config.ConfigManager
import io.github.qauxv.dsl.FunctionEntryRouter

@UiItemAgentEntry
object ShowDeletedFriendListEntry : IUiItemAgent, IUiItemAgentProvider, ISwitchCellAgent {
    override val titleProvider: (IUiItemAgent, Context) -> String = { _, _ -> "显示历史好友入口" }
    override val summaryProvider: (IUiItemAgent, Context) -> String? = { _, _ -> "在好友列表下方显示历史好友入口" }
    override val valueProvider: ((IUiItemAgent, Context) -> String?)? = null
    override val validator: ((IUiItemAgent) -> Boolean)? = null
    override val switchProvider: ISwitchCellAgent = this
    override val onClickListener: ((IUiItemAgent, Context, View, Invalidatable) -> Unit)? = null
    override val extraSearchKeywordProvider: ((IUiItemAgent, Context) -> List<String>?)? = null
    override val uiItemAgent: IUiItemAgent = this
    override val uiItemLocation: Array<String> = FunctionEntryRouter.Locations.PASSIVE_AUXILIARY_CATEGORY
    override var isChecked = isEnable
    override val isCheckable: Boolean = true
    var isEnable: Boolean
        get() = ConfigManager.getDefaultConfig().getBooleanOrDefault("ShowDeletedFriendListEntry", false)
        set(value) {
            ConfigManager.getDefaultConfig().putBoolean("ShowDeletedFriendListEntry", value)
        }
}
