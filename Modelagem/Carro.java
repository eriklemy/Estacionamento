package Modelagem;
import java.time.LocalDateTime;

public class Carro implements Comparable<Carro> {
	// atributos que definem o estado classe Carro no estacionamento
	private Modelo modelo;
	private String placa;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private float valor;

	// sobrecarga de metodos construtores 
	// nenhuma passagem de parametro 
	public Carro() {
		super();   								// referencia a chamada do construtor
		this.modelo = new Modelo();
		this.placa = "";
		this.entrada = LocalDateTime.now();     // dia/hora de hoje 
		this.saida = LocalDateTime.now();
		this.valor = 0;                           
	}
	
	// apenas a passagem da placa
	public Carro(String placa) {
		super();
		this.modelo = new Modelo();
		this.placa = placa;
		this.entrada = LocalDateTime.now();
		this.saida = LocalDateTime.now();
		this.valor = 0;
	}

	// placa e modelo 
	public Carro(String placa, Modelo modelo) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.entrada = LocalDateTime.now();
		this.saida = LocalDateTime.now();
		this.valor = 0;
	}

	// placa, hora de entrada e valor 
	public Carro(String placa, LocalDateTime entrada) {
		super();
		this.placa = placa;
		this.entrada = entrada;
		this.saida = LocalDateTime.now();
		this.valor = 0;
	}
	
	// placa, hora de entrada passando dia, mes, ano, hora e minuto 
	public Carro(String placa, int dia, int mes, int ano, int hora, int minuto) {
		this.placa = placa;
		this.entrada = LocalDateTime.of(ano, mes, dia, hora, minuto);
		this.saida = LocalDateTime.now();
		this.valor = 0;
	}
	
	// placa, modelo, entrada/saida e valor 
	public Carro(String placa, Modelo modelo, LocalDateTime entrada, LocalDateTime saida, float valor) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.entrada = entrada;
		this.saida = LocalDateTime.now();
		this.valor = valor;
	}

	// getters e setters
	public Modelo getModelo() 						{	return modelo; }
	public void setModelo(Modelo modelo) 			{	this.modelo = modelo; } 
	public String getPlaca() 						{	return placa; }
	public void setPlaca(String placa) 				{	this.placa = placa; }
	public LocalDateTime getEntrada() 				{	return entrada; }
	public void setEntrada(LocalDateTime entrada) 	{	this.entrada = entrada; }
	public LocalDateTime getSaida() 				{	return saida; }
	public void setSaida(LocalDateTime saida) 	  	{	this.saida = saida; }
	public float getValor() 						{	return valor; }
	public void setValor(float valor) 				{	this.valor += valor; }  // incrementa o valor a ser pago 

	@Override
	// redefinicao(sobreposição) do metodo toString da Object
	public String toString() {
		String msg = "veiculo modelo " + modelo.getNome();
		if (modelo.getNome() == "Sem cadastro") {
			msg += "modelo não cadastrado!";
		}
		return msg;	
	}

	@Override
	// atributo de comparação hora de entrada
	// sobreposição do compareTo -> ira devolver um numero inteiro negativo, zero ou positivo
	public int compareTo(Carro carro) {
		return entrada.compareTo(carro.getEntrada());
	}
}