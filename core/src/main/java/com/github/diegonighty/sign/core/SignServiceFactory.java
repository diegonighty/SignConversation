package com.github.diegonighty.sign.core;

import com.github.diegonighty.sign.api.conversation.ConversationHandler;
import org.bukkit.Bukkit;

import java.util.regex.Pattern;

public class SignServiceFactory {

	private static final String FORMAT = SignServiceFactory.class
			.getPackage()
			.getName()
			.concat(".%%VERSION%%.ConversationHandler_%%VERSION%%");

	private static final String VERSION = Bukkit.getServer().getClass().getName()
			.split(Pattern.quote("."))[3];

	public static ConversationHandler create() {
		String className = FORMAT.replace("%%VERSION%%", VERSION);

		try {
			Class<?> clazz = Class.forName(className);
			return (ConversationHandler) clazz.getDeclaredConstructor().newInstance();
		} catch (ReflectiveOperationException e) {
			throw notSupported(className);
		}
	}

	private static IllegalStateException notSupported(String className) {
		return new IllegalStateException("SignConversations doesn't support " + VERSION + " version: " + className);
	}

}
