package com.example.tp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Instant;

/**
 * Message de chat dont la (dé)sérialisation est entièrement pilotée à la main.
 * Externalizable permet de choisir précisément les octets écrits/lus.
 */
public class ChatMessage implements Externalizable {

	private static final long serialVersionUID = 2024L;
	private static final int VERSION_FORMAT = 1;

	private String auteur;
	private String contenu;
	private Instant horodatage;
	private transient int taille;

	/** Obligatoire pour Externalizable. */
	public ChatMessage() {
	}

	public ChatMessage(String auteur, String contenu) {
		this.auteur = auteur;
		this.contenu = contenu;
		this.horodatage = Instant.now();
		this.taille = contenu.length();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(VERSION_FORMAT);
		out.writeUTF(auteur);
		out.writeUTF(contenu);
		out.writeLong(horodatage.toEpochMilli());

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		int v = in.readInt();
		if (v != VERSION_FORMAT) {
			throw new IOException("Format inattendu : " + v);
		}
		auteur = in.readUTF();
		contenu = in.readUTF();
		horodatage = Instant.ofEpochMilli(in.readLong());
		taille = contenu.length();
	}

	public String getAuteur() {
		return auteur;
	}

	public String getContenu() {
		return contenu;
	}

	public Instant getHorodatage() {
		return horodatage;
	}

	public int getTaille() {
		return taille;
	}

	@Override
	public String toString() {
		return horodatage + " <" + auteur + "> : " + contenu;
	}
}