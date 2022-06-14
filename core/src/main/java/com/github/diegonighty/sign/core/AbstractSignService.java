package com.github.diegonighty.sign.core;

import com.github.diegonighty.sign.api.conversation.SignConversation;
import com.github.diegonighty.sign.api.conversation.ConversationHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractSignService implements ConversationHandler {

	protected final static String CHANNEL = "sign-conversations";
	private final Map<UUID, SignConversation> conversations = new HashMap<>();

	@Override
	public void enable(JavaPlugin plugin) {
		plugin.getServer().getPluginManager()
				.registerEvent(
						PlayerJoinEvent.class,
						BLANK_LISTENER,
						EventPriority.NORMAL,
						(ignored, rawEvent) -> {
							PlayerJoinEvent event = (PlayerJoinEvent) rawEvent;
							injectPlayer(event.getPlayer());
						},
						plugin
				);

		addInterceptors();
	}

	@Override
	public void startConversation(Player player, SignConversation conversation) {
		Location signLocation = player.getLocation();
		signLocation.setY(1);

		player.sendBlockChange(signLocation, Material.OAK_SIGN.createBlockData());
		player.sendSignChange(signLocation, conversation.lines().toArray(new String[0]));

		openFakeSign(player, signLocation);
		register(player, conversation);
	}

	protected SignConversation register(Player player, SignConversation conversation) {
		conversations.put(player.getUniqueId(), conversation);
		return conversation;
	}

	public SignConversation getAndRemove(Player player) {
		return conversations.remove(player.getUniqueId());
	}

	protected abstract void openFakeSign(Player player, Location location);

	protected abstract void addInterceptors();

	protected abstract void injectPlayer(Player player);

}
