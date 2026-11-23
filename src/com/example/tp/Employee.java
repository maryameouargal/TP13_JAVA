package com.example.tp;

import java.io.Serializable;

/**
 * Employé sérialisable. Le mot de passe est marqué transient : il ne sera pas
 * écrit dans le flux.
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 2024L;

	private final int id;
	private final String name;
	private final double salary;
	private transient String password;

	public static int comp = 0;

	public Employee( String name, double salary, String password) {
		this.id = ++comp;
		this.name = name;
		this.salary = salary;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		String pwd = (password == null) ? "NON SÉRIALISÉ" : password;
		return "Employee{id=" + id + ", name='" + name + '\'' + ", salary=" + salary + ", password=" + pwd + '}';
	}
}