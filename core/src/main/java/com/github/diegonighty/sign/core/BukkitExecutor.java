package com.github.diegonighty.sign.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;

public class BukkitExecutor implements Executor {

	private final Plugin plugin;

	public BukkitExecutor(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void execute(Runnable command) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, command);
	}

	public static class BukkitExecutorProvider {

		private static BukkitExecutor executor;

		public static synchronized BukkitExecutor get() {
			if (executor == null) {
				executor = new BukkitExecutor(JavaPlugin.getProvidingPlugin(BukkitExecutorProvider.class));
			}

			return executor;
		}
	}
}
