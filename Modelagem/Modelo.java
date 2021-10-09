package ESTACIONAMENTO.Modelagem;

public class Modelo {
	private String nome;

	// sobrecarga dos metodos construtores 
	public Modelo() {
		super();
		this.nome = "sem cadastro";
	}

	// para caso tenha apenas a passagem do nome do Modelo
	public Modelo(String nome) {
		super();
		this.nome = nome;
	}

	// getter e setter 
	public String getNome() {return nome; }	
	public void setNome(String nome) {	this.nome = nome; }

	@Override
	// sobreposição do metodo toString para se adequar a classe 
	public String toString() {
		String msg = nome;
		return msg;
	}
}