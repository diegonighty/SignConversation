package com.github.diegonighty.sign.api.conversation.response;

import com.github.diegonighty.sign.api.conversation.ConversationResponse;
import org.bukkit.entity.HumanEntity;

public class Responses {

	public static ConversationResponse close() {
		return HumanEntity::closeInventory;
	}

	public static ConversationResponse closeWithMessage(String message) {
		return player -> {
			player.closeInventory();
			player.sendMessage(message);
		};
	}

}
