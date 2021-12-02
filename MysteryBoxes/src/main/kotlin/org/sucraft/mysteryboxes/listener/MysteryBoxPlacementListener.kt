/*
 * Copyright (c) SuCraft 2021 sucraft.org
 */

package org.sucraft.mysteryboxes.listener

import org.bukkit.block.ShulkerBox
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.sucraft.core.common.sucraft.plugin.SuCraftComponent
import org.sucraft.mysteryboxes.data.MysteryBoxData
import org.sucraft.mysteryboxes.main.SuCraftMysteryBoxesPlugin


object MysteryBoxFixListener : SuCraftComponent<SuCraftMysteryBoxesPlugin>(SuCraftMysteryBoxesPlugin.getInstance()) {

	// Events

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	fun onInventoryOpen(event: InventoryOpenEvent) {
		if (event.inventory.type != InventoryType.SHULKER_BOX) return
		val shulkerBox = (event.inventory.holder as? ShulkerBox) ?: return
		MysteryBoxData.fixMysteryBoxTitleIfNecessary(shulkerBox.block)
	}

}