package org.sucraft.modules.preferences.event

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.sucraft.modules.preferences.Preferences

abstract class InventoryClickEvent : Event() {

    abstract val player: Player
    abstract val prefence: Preference

    open val isEnabledBy
        get() = preference.isEnabledBy(player)

    override fun getHandlers() = handlerList

    companion object {
        @Suppress("NON_FINAL_MEMBER_IN_OBJECT")
		@JvmStatic
        open val handlerList = HandlerList()
    }
}
