package xyz.umeo.plugin

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

private var second = 0
private var minute = 0
private var hour = 0
private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
private var mcTimer: Timer? = null

fun insertZero(num: Int): String {
    return if (num < 10) {
        "0$num"
    } else {
        num.toString()
    }
}

fun mcTimerStart(p:Player) {
    mcTimer = timer(period = 1000) {
        if (second >= 60) {
            minute += 1
            second -= 60
        }
        if (minute >= 60) {
            hour += 1
            minute -= 60
        }
        for (players in list) {
            players.sendActionBar(hour.toString() + ":" + insertZero(minute) + ":" + insertZero(second))
        }
        second++
    }

    p.sendMessage("스톱워치를 시작합니다.\n제작 : U.meo(메오쿤)")

}

fun mcTimerStop(p:Player) {
    p.sendMessage("스톱워치를 정지합니다.")
    second = 0
    mcTimer?.cancel()
}
