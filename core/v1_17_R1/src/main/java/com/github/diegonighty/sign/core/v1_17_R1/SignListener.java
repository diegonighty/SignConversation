package com.github.diegonighty.sign.core.v1_17_R1;

import com.github.diegonighty.sign.api.gui.SignConversation;
import com.github.diegonighty.sign.core.AbstractSignService;
import com.github.diegonighty.sign.core.packet.PacketInterceptor;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import org.bukkit.entity.Player;

public class SignListener implements PacketInterceptor<PacketPlayInUpdateSign> {

	private final AbstractSignService signService;

	public SignListener(AbstractSignService signService) {
		this.signService = signService;
	}

	@Override
	public PacketPlayInUpdateSign in(Player player, PacketPlayInUpdateSign packet) {
		SignConversation conversation = signService.getAndRemove(player);

		if (conversation != null) {
			conversation.callback()
					.then(player, packet.c())
					.response(player);
		}

		return packet;
	}
}
