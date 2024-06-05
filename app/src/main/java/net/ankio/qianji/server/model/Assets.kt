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

class Assets {
    var currency: String = ""

    // 账户列表
    var id = 0
    var name: String = "" // 账户名

    /**
     * 这里的图标是url链接或存储的base64图片
     */
    var icon: String = "" // 图标
    var sort = 0
    var type: Int = 0 // 账户类型
    var extras: String = "" // 额外信息，例如银行卡的卡号等

    companion object {
        fun sync2server(
            data: List<Assets>,
            md5: String,
        ) {
            HookUtils.getScope().launch {
                HookUtils.getService().sendMsg("assets/sync", hashMapOf("data" to data, "md5" to md5))
            }
        }
    }
}
