package xyz.umeo.plugin

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun heal(p:Player) {
    p.sendMessage("ManHunt의 원활한 초반부를 위해 음식을 지급합니다!")
    for(i in 1..8) p.inventory.addItem(ItemStack(Material.COOKED_BEEF))
}