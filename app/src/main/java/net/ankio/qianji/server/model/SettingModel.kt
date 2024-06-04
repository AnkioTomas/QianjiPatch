/*
 * Copyright (C) 2024 ankio(ankio@ankio.net)
 * Licensed under the Apache License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-3.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package net.ankio.qianji.server.model

import kotlinx.coroutines.launch
import net.ankio.qianji.utils.HookUtils

class SettingModel {
    var app = ""
    var key = ""
    var value = ""

    companion object {
        fun set(settingModel: SettingModel) {
            HookUtils.getScope().launch {
                HookUtils.getService().sendMsg("setting/set", settingModel)
            }
        }

        suspend fun get(
            app: String,
            key: String,
        ): String {
            val data = HookUtils.getService().sendMsg("setting/get", mapOf("app" to app, "key" to key))
            return runCatching { data as String }.getOrNull() ?: ""
        }
    }
}
