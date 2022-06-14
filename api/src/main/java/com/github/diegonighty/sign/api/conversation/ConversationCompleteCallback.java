package com.github.diegonighty.sign.api.conversation;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface ConversationCompleteCallback {

	ConversationResponse then(Player player, String[] lines);

}
