package com.github.diegonighty.sign.api.conversation;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public interface ConversationHandler {

	Listener BLANK_LISTENER = new Listener() {};

	void startConversation(Player player, SignConversation conversation);

	void enable(JavaPlugin plugin);

}
