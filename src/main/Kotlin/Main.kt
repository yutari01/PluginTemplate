package xyz.umeo.plugin

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

open class Main: JavaPlugin(), Listener, CommandExecutor{
    override fun onEnable(){
        logger.info("U.meo의 Plugin 활성화 되었습니다.")
        Bukkit.getPluginManager().registerEvents(this,this)
    }
    override fun onDisable(){
        logger.info("U.meo의 Plugin 비활성화 되었습니다.")
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent){
        event.player.sendMessage("➜ 서버 접속을 환영합니다!")
        event.player.showBossBar(BossBar.bossBar(text("mc.umeo.xyz를 플레이 중!"), 1f, BossBar.Color.PURPLE, BossBar.Overlay.PROGRESS ))
    }

    @EventHandler
    fun onRunnerDie(event: PlayerDeathEvent) {
        if (event.entity.type == EntityType.PLAYER && event.entity.name == "U_meo") {
            event.entity.gameMode = GameMode.SPECTATOR
        }
    }

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
