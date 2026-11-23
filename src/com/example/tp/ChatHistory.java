package com.example.tp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persiste une liste de ChatMessage dans un fichier binaire.
 */
public final class ChatHistory {

	private ChatHistory() {
	} // utilitaire non instanciable

	/** Écrit le nombre de messages puis chaque message. */
	public static void save(String path, List<ChatMessage> history) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
			out.writeInt(history.size());
			for (ChatMessage m : history) {
				m.writeExternal(out);
			}
		}
	}

	/** Lit le nombre de messages puis reconstruit chaque message. */
	public static List<ChatMessage> load(String path) throws IOException, ClassNotFoundException {
		List<ChatMessage> result = new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
			int n = in.readInt();
			for (int i = 0; i < n; i++) {
				ChatMessage m = new ChatMessage();
				m.readExternal(in);
				result.add(m);
			}
		}
		return result;
	}
}