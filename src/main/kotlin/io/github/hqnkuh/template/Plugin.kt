package io.github.hqnkuh.template

import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {
    companion object {
        lateinit var plugin: Plugin
    }

    override fun onEnable() { // プラグインが有効になった時の処理
        plugin = this
    }

    override fun onDisable() { // プラグインが無効になった時の処理
        plugin = this
    }
}