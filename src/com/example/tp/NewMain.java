package com.example.tp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Démonstration de Externalizable sur un fil de discussion.
 */
public class NewMain {

	private static final String FICHIER = "data/chat.ser";

	public static void main(String[] args) {

		// 1) Fil de discussion
		List<ChatMessage> fil = new ArrayList<>();
		fil.add(new ChatMessage("Preston", "Bonjour à tous"));
		fil.add(new ChatMessage("Rina", "Salut Preston !"));
		fil.add(new ChatMessage("Marcus", "Prêt pour la réunion ?"));

		// 2) Dossier de sortie
		new File("data").mkdirs();

		// 3) Écriture
		try {
			ChatHistory.save(FICHIER, fil);
			System.out.println("[OK] Écriture -> " + new File(FICHIER).getAbsolutePath());
		} catch (IOException ex) {
			System.err.println("[KO] Écriture : " + ex.getMessage());
			return;
		}

		// 4) Lecture
		List<ChatMessage> relu;
		try {
			relu = ChatHistory.load(FICHIER);
			System.out.println("[OK] Relecture (" + relu.size() + " messages)");
		} catch (IOException | ClassNotFoundException ex) {
			System.err.println("[KO] Lecture : " + ex.getMessage());
			return;
		}

		// 5) Affichage
		relu.forEach(m -> System.out.printf("  (%d car.) %s%n", m.getTaille(), m));
	}
}