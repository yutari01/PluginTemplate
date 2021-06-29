package xyz.umeo.plugin

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.concurrent.timer


open class Main: JavaPlugin(), Listener, CommandExecutor{
    override fun onEnable(){
        logger.info("Plugin 활성화 되었습니다.")
    }

    override fun onDisable(){
        logger.info("Plugin 비활성화 되었습니다.")
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent){
        event.joinMessage(null)
    } //Not Working.

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player){
            val p : Player = sender
            when (command.name){
                "stopwatch" -> {
                    p.sendMessage("스톱워치를 시작합니다.")
                    p.sendMessage("제작 : U.meo(메오쿤)")
                    val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
                    var second = 0
                    var minute = 0
                    var hour = 0
                    timer(period = 1000, initialDelay = 1000) {
                        second++
                        if (second >= 60) {
                            minute += 1
                            second -= 60
                        }
                        if (minute >= 60) {
                            hour += 1
                            minute -= 60
                        }
                        fun insertZero(num: Int): String { return if (num<10) { "0$num" } else { num.toString()}}
                        //p.sendMessage(hour.toString()+":"+insertZero(minute)+":"+insertZero(second))
                        for (players in list){
                            players.sendActionBar(hour.toString()+":"+insertZero(minute)+":"+insertZero(second))
                        }

                    }
                }
            }
        }
        else {
            sender.sendMessage("콘솔에서는 사용이 불가능한 명령어입니다!")
        }
        return true
    }

    /*
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player){
            val p : Player = sender
            when (command.name){
                "hello" -> {
                    p.sendMessage("안녕하세요! " + p.name + "!" )
                }
            }
        }
        else {
            sender?.sendMessage("콘솔에서는 사용이 불가능한 명령어입니다!")
        }
        return true
    }
    */

}
