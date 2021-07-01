package xyz.umeo.plugin

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

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
                    if (args[0] == "start") {
                        mcTimerStart(p)
                    } else if (args[0] == "stop") {
                        mcTimerStop(p)
                    }
                }
                "heal" -> {
                    heal(p)
                    return true
                }
            }
        }
        else {
            sender.sendMessage("콘솔에서는 사용이 불가능한 명령어입니다!")
        }
        return true
    }
}
