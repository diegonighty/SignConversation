package com.github.diegonighty.sign.api.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public interface SignGUIService {

	Listener BLANK_LISTENER = new Listener() {};

	void startConversation(Player player, SignConversation conversation);

	void injectPlayer(Player player);

	void enable(JavaPlugin plugin);

}
