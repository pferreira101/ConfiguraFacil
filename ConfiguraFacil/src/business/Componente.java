package business;

import java.util.Vector;

public class Componente {
	private int _id;
	private String _designacao;
	private double _preco;
	private String _tipo;
	public Vector<Componente> _complementares = new Vector<Componente>();
	public Vector<Componente> _incompativeis = new Vector<Componente>();
}