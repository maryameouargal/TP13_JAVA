package com.example.tp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Démonstration : sérialisation puis désérialisation d'employés.
 */
public class Main {

	private static final String FILE = "data/employees.ser";

	public static void main(String[] args) {

		// 1) Jeu de données
		List<Employee> staff = new ArrayList<>();
		staff.add(new Employee("Preston", 4000.0, "pwdPreston"));
		staff.add(new Employee("Rina", 3500.5, "pwdRina"));
		staff.add(new Employee("Marcus", 3800.75, "pwdMarcus"));

		// 2) Créer le dossier data/ s'il n'existe pas
		new java.io.File("data").mkdirs();

		// 3) Écriture
		try {
			SerializationUtil.serializeEmployees(FILE, staff);
			System.out.println("[OK] Sérialisation -> " + new java.io.File(FILE).getAbsolutePath());
		} catch (IOException ex) {
			System.err.println("[KO] Écriture : " + ex.getMessage());
			return;
		}
		// 4) Relecture
		List<Employee> loaded;
		try {
			loaded = SerializationUtil.deserializeEmployees(FILE);
			System.out.println("[OK] Désérialisation (" + loaded.size() + " objets)");
		} catch (IOException | ClassNotFoundException ex) {
			System.err.println("[KO] Lecture : " + ex.getMessage());
			return;
		}

		// 5) Affichage
		loaded.forEach(e -> System.out.println("  " + e));

		// 6) Remarque transient
		System.out.println("\n>> 'password' est transient : null après relecture.");
	}
}