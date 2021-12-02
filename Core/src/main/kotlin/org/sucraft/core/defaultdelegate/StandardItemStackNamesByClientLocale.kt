/*
 * Copyright (c) SuCraft 2021 sucraft.org
 */

package org.sucraft.core.defaultdelegate

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.json.JSONObject
import org.sucraft.core.common.bukkit.item.GuaranteedItemMetaGetter
import org.sucraft.core.common.sucraft.delegate.MinecraftClientLocale
import org.sucraft.core.common.sucraft.delegate.StandardItemStackNames


object StandardItemStackNamesByClientLocale : StandardItemStackNames {

	private var hasBeenRead = false
	private val standardNamesByKey: MutableMap<String, String> = HashMap()

	private fun readAllFromConfiguration() {
		val json = MinecraftClientLocale.get().getJSON()
		for (name in JSONObject.getNames(json)) {
			val split = name.split("\\.".toRegex()).toTypedArray()
			if (split.size == 3 && split[1] == "minecraft" && (split[0] == "item" || split[0] == "block")) {
				val value = json.get(name)
				if (value is String) {
					standardNamesByKey[split[2]] = value
				}
			}
		}
	}

	private fun readAllFromConfigurationIfNeeded() {
		if (hasBeenRead) return
		readAllFromConfiguration()
	}

	fun get(key: String): String? {
		readAllFromConfigurationIfNeeded()
		return standardNamesByKey[key]
	}

	fun get(key: NamespacedKey) = get(key.key)

	override fun get(type: Material) = get(type.key) ?: type.name

	override fun get(itemStack: ItemStack): String {
		val itemMeta = GuaranteedItemMetaGetter.get(itemStack)
		if (itemMeta.hasLocalizedName()) {
			return itemStack.itemMeta.localizedName
		}
		//TODO should be made more interesting
		return get(itemStack.type)
	}

}