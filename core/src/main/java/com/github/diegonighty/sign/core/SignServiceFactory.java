package com.github.diegonighty.sign.core;

import com.github.diegonighty.sign.api.gui.SignGUIService;
import org.bukkit.Bukkit;

import java.util.regex.Pattern;

public class SignServiceFactory {

	private static final String FORMAT = SignServiceFactory.class
			.getPackage()
			.getName()
			.concat(".%%VERSION%%.SignGUIService_%%VERSION%%");

	private static final String VERSION = Bukkit.getServer().getClass().getName()
			.split(Pattern.quote("."))[3];

	public static SignGUIService create() {
		String className = FORMAT.replace("%%VERSION%%", VERSION);

		try {
			Class<?> clazz = Class.forName(className);
			return (SignGUIService) clazz.getDeclaredConstructor().newInstance();
		} catch (ReflectiveOperationException e) {
			throw notSupported();
		}
	}

	private static IllegalStateException notSupported() {
		return new IllegalStateException("SignConversations doesn't support " + VERSION + " version");
	}

}
