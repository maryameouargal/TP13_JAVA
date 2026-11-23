package com.example.tp;

import java.io.*;
import java.util.List;

/**
 * Outils de (dé)sérialisation d'une liste d'Employee.
 */
public final class SerializationUtil {

	private SerializationUtil() {
	}

	/** Écrit la liste dans un fichier binaire. */
	public static void serializeEmployees(String path, List<Employee> data) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
			out.writeObject(data);
		}
	}

	/** Relit la liste depuis un fichier binaire. */
	@SuppressWarnings("unchecked")
	public static List<Employee> deserializeEmployees(String path) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
			return (List<Employee>) in.readObject();
		}
	}
}