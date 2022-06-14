package com.github.diegonighty.sign.example;

import com.github.diegonighty.sign.api.conversation.response.Responses;
import com.github.diegonighty.sign.api.conversation.SignConversation;
import com.github.diegonighty.sign.api.conversation.ConversationHandler;
import com.github.diegonighty.sign.core.SignServiceFactory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

	private ConversationHandler service;

	@Override
	public void onEnable() {
		this.service = SignServiceFactory.create();
		service.enable(this);
	}

	private void openSign(Player player) {
		SignConversation conversation = SignConversation.builder()
				.lineAt(2, "^^^^^^^^^^^^")
				.lineAt(3, "Enter nickname")
				.onComplete((author, lines) -> Responses.closeWithMessage("Entered nickname is " + lines[0]))
				.create();

		service.startConversation(player, conversation);
	}
}
