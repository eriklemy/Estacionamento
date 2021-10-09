package Modelagem;
import java.util.ArrayList;

public class Marca {
	// atributos da classe marca 
	private String nome;
	private ArrayList<Modelo> modelo; // recebe uma lista de modelos

	// sobrecarga dos metodos construtores da classe 
	public Marca() {
		super();
		this.nome = "Sem cadastro";
		this.modelo = new ArrayList<Modelo>();
	}
	
	// caso tenha apenas a passagem do nome 
	public Marca(String nome) {
		super();
		this.nome = nome;
		this.modelo = new ArrayList<Modelo>();
	}

	// caso tenha passagem de nome e de modelos 
	public Marca(String nome, Modelo modelo) {
		super();
		this.nome = nome;
		this.modelo.add(modelo); 	// adiciona o modelo a lista de modelos 
	}

	// caso tenha passagem de nome e de modelos 
	public Marca(String nome, ArrayList<Modelo> modelo) {
		super();
		this.nome = nome;
		this.modelo = modelo;
	}

	// getters e setters 
	public String getNome() {	return nome; }
	public void setNome(String nome) {	this.nome = nome; }
	public ArrayList<Modelo> getModelo() {	return modelo; }
	public void setModelo(ArrayList<Modelo> modelo) {	this.modelo = modelo; }
	public void addModelo(Modelo modelo) {	this.modelo.add(modelo); } // adiciona o modelo a lista de modelos 
	
	@Override
	// redefinicao / sobreposição do metodo toString da Object
	public String toString() {
		String msg = nome;
		if (nome != "Sem cadastro") {
			msg = "marca: " + nome;
		}
		return msg;	
	}
}