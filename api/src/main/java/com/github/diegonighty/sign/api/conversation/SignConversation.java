package com.github.diegonighty.sign.api.conversation;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignConversation {

	public static SignConversationBuilder builder() {
		return new SignConversationBuilder();
	}

	private final ConversationCompleteCallback callback;
	private final List<String> lines;

	private final boolean cancelClose;

	private SignConversation(ConversationCompleteCallback callback, List<String> lines, boolean cancelClose) {
		this.callback = callback;
		this.lines = lines;
		this.cancelClose = cancelClose;
	}

	public static class SignConversationBuilder {
		private ConversationCompleteCallback callback;
		private List<String> lines = new ArrayList<>();
		private boolean cancelClose;

		public SignConversationBuilder onComplete(ConversationCompleteCallback callback) {
			this.callback = callback;
			return this;
		}

		public SignConversationBuilder setLines(List<String> lines) {
			this.lines = lines;
			return this;
		}

		public SignConversationBuilder addLine(String line) {
			lines.add(line);
			return this;
		}

		public SignConversationBuilder addLines(String... line) {
			lines.addAll(Arrays.asList(line));
			return this;
		}

		public SignConversationBuilder preventClose() {
			this.cancelClose = true;
			return this;
		}

		public SignConversationBuilder lineAt(int index, String line) {
			lines.set(index, line);
			return this;
		}

		public SignConversation create() {
			Preconditions.checkNotNull(callback, "Callback cannot be null!");

			return new SignConversation(callback, lines, cancelClose);
		}
	}

	public ConversationCompleteCallback callback() {
		return callback;
	}

	public List<String> lines() {
		return lines;
	}

	public boolean isCancelClose() {
		return cancelClose;
	}
}
