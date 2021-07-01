package xyz.umeo.plugin

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

private val getHealList = arrayListOf("noonmaru") //Just I like and subscribed 각별 youtube.

fun heal(p:Player) {
    if(getHealList.contains(p.name)) {
        p.sendMessage("세상에 공짜는 없습니다.\n호의가 계속되면 그게 둘리인 줄 알아요.")
        p.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 240, 60, true))
    }
    else {
        p.sendMessage("게임의 원활한 초반부를 위해 음식을 지급합니다!")
        for(i in 1..8) p.inventory.addItem(ItemStack(Material.COOKED_BEEF))
        if(!getHealList.contains(p.name)) getHealList.add(p.name)
    }
}
