package Principal;
import Modelagem.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Principal {
	// Atributos de classe
	private static Carro[] vagas = new Carro[100]; // o estacionamento tem 100 vagas numeradas de 0..99
	private static ArrayList<Marca> marcas = new ArrayList<Marca>(); 
	private static ArrayList<Carro> historico = new ArrayList<Carro>();
	
	public static void main(String[] args) {
		int opcao;
		Scanner teclado = new Scanner(System.in);
		
		// iniciando objetos da classe Carro com sua respectivas placas 
		Carro c1 = new Carro("ABC-0000", 13, 5, 2021, 14, 0); 						// dia, mes, ano
		Carro c2 = new Carro("ABC-1111", LocalDateTime.of(2021, 5, 14, 15, 0)); 	// sistema ingles 
		Carro c3 = new Carro("ABC-2222", LocalDateTime.of(2021, 5, 14, 12, 0));
		
		// inclusao hardcoded de alguns Modelos, Marcas e Carros para teste das funcionalidades do programa
		Marca ferrari = new Marca("Ferrari");
		ferrari.addModelo(new Modelo("F599 GTO"));
		ferrari.addModelo(new Modelo("Portofino"));
		ferrari.addModelo(new Modelo("GTC4"));

		Marca porche = new Marca("Porche");
		porche.addModelo(new Modelo("911"));
		porche.addModelo(new Modelo("718 Spyder"));
		porche.addModelo(new Modelo("718 Cayman GT4"));
		
		Marca lambo = new Marca("Lamborghini");
		lambo.addModelo(new Modelo("Aventador"));
		lambo.addModelo(new Modelo("Gallardo"));
		lambo.addModelo(new Modelo("Veneno"));

		// adiciona as marcas de teste ao arrayList de marcas
		marcas.add(ferrari);
		marcas.add(porche);
		marcas.add(lambo);

		// cadastro de carro e modelo para teste 
		//c1.setModelo(ferrari.getModelo().get(1));	  diferente abordagem para atribuir o modelo hardcoded
		c1.setModelo(ferrari.getModelo().iterator().next());
		c2.setModelo(porche.getModelo().iterator().next());
		c3.setModelo(lambo.getModelo().iterator().next());

		// adicionando o carro de teste no vetor de vagas 
		vagas[0] = c1;
		vagas[1] = c2;
		vagas[2] = c3;

		// adicionando veiculo a ser removido no historico, remove da vaga[1] e [2]
		saidaCarro(1);
		saidaCarro(2);

		do {
			System.out.println("\n=== Menu de opcoes ===\n");
			System.out.println("    0 - sair");
			System.out.println("    1 - cadastrar entrada de carro");
			System.out.println("    2 - efetuar saida de carro");
			System.out.println("    3 - cadastrar marca"); 
			System.out.println("    4 - cadastrar modelo");  
			System.out.println("    5 - mostrar relatorio"); 
			System.out.println("    6 - mostrar vagas ocupas"); 
			System.out.println("    7 - listar marcas e modelos cadastrados"); 
						
			System.out.print("\n    Opcao? ");
			opcao = teclado.nextInt();
			teclado.nextLine();

			switch (opcao) {
			case 0:
				System.exit(0);
			case 1:
				// Imprime os modelos ja cadastrados 
				System.out.println("marcas e modelos cadastrados: ");
				showNumerado();

				System.out.println("Indique:\n<Numero> Marca\n<0> Cadastrar");
				Scanner marcaScanner = new Scanner(System.in);
				int posMarca;
				// breve verificação para validar a entrada e devolver o valor correto da marca para o cadastro
				do{
					posMarca = marcaScanner.nextInt() - 1;
					if(posMarca > marcas.size() - 1){
						System.out.println("Entrada Invalida\nDigite novamente: ");
					}
				} while(posMarca > marcas.size() - 1);
				
				Marca marca;
				if(posMarca < 0) {	 marca = cadastraMarca(); }
				else { marca = marcas.get(posMarca); }

				System.out.println("Indique:\n<Numero> Modelo\n<0> Cadastrar");
				Scanner modeloScanner = new Scanner(System.in);
				int posModelo;

				// breve verificação para validar a entrada e devolver o valor correto do veiculo para o cadastro
				do {
					posModelo = modeloScanner.nextInt() - 1;
					if(posModelo > marca.getModelo().size() - 1) {
						System.out.println("Entrada invalida\nDigite novamente: ");
					}
				} while(posModelo > marca.getModelo().size() - 1);
				Modelo modelo;

				if(posModelo < 0) {	modelo = cadastraModelo(marca); } 
				else { modelo = marca.getModelo().get(posModelo); }

				entradaCarro(modelo);
				break;
			case 2:
				System.out.println("\n--- Remover Veiculo da vaga ---\n");
				showVagasOcupadas();
				System.out.print("    Vaga a Remover Veiculo: ");
				int vagaRemover;

				// loop para tratamento de dados
				do {  
					vagaRemover = teclado.nextInt();
					if((vagaRemover >= 0) && (vagas[vagaRemover] == null)) 
						System.out.println("Não há veiculo nesta Vaga\nDigite novamente: ");
					
				} while((vagaRemover >= 0) && (vagas[vagaRemover] == null));

				if(vagaRemover >= 0) {
					System.out.println("O valor a se pagar é de: R$ " + saidaCarro(vagaRemover));
				}
				break;
			case 3:
				cadastraMarca();
				break;
			case 4:
				System.out.println("Escolha a Marca que o modelo pertence: ");
				// mostra os nomes das marcas
				for(int i = 0; i < marcas.size(); i++) {
					System.out.println(i+1 + "- " + marcas.get(i).getNome());
				}
				Scanner mScanner = new Scanner(System.in);
				cadastraModelo(marcas.get(mScanner.nextInt() - 1));
				break;
			case 5:
				// ordena por hora ordem de entrada no estacionamento 
				Collections.sort(historico, new Comparator<Carro>() {
					@Override	// compara data/hora de entrada
					public int compare(Carro c1, Carro c2) {
						return c1.getEntrada().compareTo(c2.getEntrada());
					}				
				});   
				showRelatorio();
				break;
			case 6:
				showVagasOcupadas();
				break;
			case 7:
				showNotnum();
				break;
			default:
				System.out.println("Opcao inexistente!");;
				break;
			}
		} while (opcao != 0);
		teclado.close();
	}

	// Imprime os modelos com indices para facilitar na escolha 
	private static void showNumerado(){
		for(int i = 0; i < marcas.size(); i++) {
			System.out.printf("<%d> - %s\n", i+1, marcas.get(i).getNome());
			for(int j = 0; j < marcas.get(i).getModelo().size(); j++) {
				System.out.printf("    <%d> - Modelo: %s\n", j+1, marcas.get(i).getModelo().get(j));	
			}
		}
    }
	
	// Imprime os modelos de forma não numerada apenas para visualização
	private static void showNotnum(){
		for (Marca marca : marcas) {
			System.out.println(marca.getNome());
			for (Modelo modelo : marca.getModelo()) {
				System.out.println("    Modelo: " + modelo.getNome());	
			}
		}
    }
	
	// Adiciona um veiculo no estacionamento
	private static void entradaCarro(Modelo modelo) {
		System.out.print("\n    Placa: ");
		Scanner placaScanner = new Scanner(System.in);
		String placa = placaScanner.nextLine();

		// inicia um novo objeto da classe Carro
		Carro c = new Carro(placa, modelo);
		for(int i = 0; i < vagas.length; i++) {
			if(vagas[i] == null) {
				vagas[i] = c; 	// adiciona na posição vazia do vetor
				break;          // para apos adicionar na primeira posição vazia
			}
		}
		System.out.println("Cadastro realizado com Sucesso!!");
		//placaScanner.close();
	}
	
	// Função que calcula o valor a se pagar pelo tempo no estacionamento
	private static float saidaCarro(int vagaRemover) {
		// variaveis de preço 
		float precoFixo = 10;
		float incrementoPreco = 2;
		float valorPagamento = 0;

		// Intervalos de tempo entre entrada e saida 
		float intervaloDia = Duration.between(vagas[vagaRemover].getEntrada(), vagas[vagaRemover].getSaida()).toDaysPart();
		int intervaloHora = Duration.between(vagas[vagaRemover].getEntrada(), vagas[vagaRemover].getSaida()).toHoursPart();
		int intervaloMin = Duration.between(vagas[vagaRemover].getEntrada(), vagas[vagaRemover].getSaida()).toMinutesPart();

		// calcula o valor a ser pago 
		if (intervaloHora >= 1){
			precoFixo *= intervaloHora;
			precoFixo += 240*intervaloDia;   			// 240 preço diario 
			if(intervaloMin > 0 && intervaloMin < 60){
				if(intervaloMin >= 15 && intervaloMin < 30){
					incrementoPreco *= 2; 				// passa a ser 4 reais
				} else if(intervaloMin >= 30 && intervaloMin < 45){
					incrementoPreco *= 3;  				// passa a ser 6 reais
				} else incrementoPreco *= 4; 			// passa a ser 8 reais
				vagas[vagaRemover].setValor(precoFixo + incrementoPreco);  
			} 
		} else vagas[vagaRemover].setValor(precoFixo);
		valorPagamento = vagas[vagaRemover].getValor();

		// atualiza a hora de saida do veiculo adiciona no historico e remove do vetor
		vagas[vagaRemover].setSaida(LocalDateTime.now());
		historico.add(vagas[vagaRemover]);
		vagas[vagaRemover] = null;
		System.out.println("    Veiculo deixou a vaga!");	
		return valorPagamento;
	}

	private static Marca cadastraMarca() {
		System.out.println("\n--- Cadastro de Marcas ---");
		System.out.println("    marca: ");
		Scanner teclado = new Scanner(System.in);
		String nome = teclado.nextLine();
		Marca marca = new Marca();
		
		// consulta se esta cadastrado, se não estiver ira cadastrar 
		if(!consultarMarcaModelo(nome)){
			marca.setNome(nome);
			marcas.add(marca);	
			System.out.println("    cadastrado com sucesso!!");
		} 

		// teclado.close();
		return marca; // retorna a posição da marca cadastrada dentro do arrayList de marcas
	}

	// Função sobrecarregada para cadastro de modelo no caso de não haver o modelo no momento do cadastro de entrada 
	private static Modelo cadastraModelo(Marca marca) {
		System.out.println("\n--- Cadastro de Modelos ---");
		System.out.println("    Modelo: ");
		Scanner teclado = new Scanner(System.in);
		String nome = teclado.nextLine();
		Modelo modelo = new Modelo();

		// consulta se ja esta cadastrado, se não estiver ira cadastrar
		if(!consultarMarcaModelo(nome)){
			modelo.setNome(nome);
			marca.addModelo(modelo);
		}

		// teclado.close();
		return modelo; // retorna a posição do modelo dentra da marca no arrayList de marcas
	}
	
	// Consulta se a marca entrada ja esta cadastrada 
	private static boolean consultarMarcaModelo(String chave) {
		boolean achou = false;
		// loop que compara o nome da marca pedida e verifica se já esta no sistema 
		for (Marca marca : marcas) {
			if(marca.getNome().toUpperCase().contains(chave.toUpperCase())){
				achou = true; // flag true se encontrar
				System.out.println("    Marca ja cadastrada!!");
			} 
			for(Modelo modelo: marca.getModelo()){ // verifica se modelo ja esta cadastrado
				if(modelo.getNome().toUpperCase().contains(chave.toUpperCase())){
					achou = true;
					System.out.println("     Modelo ja cadastrado!!");
				}
			}
		}
		if (!achou)	    		// caso não encontrar avisa que esta cadastrando
			System.out.println("    " + chave + " cadastrado com sucesso!");
	
		return achou;
	}
	
	// Consultar a qual Marca o modelo pertence 
	private static String consultar(String chave) {
		for(Marca marca: marcas){ 
			for (Modelo modelo : marca.getModelo()) { 
				// se encontrar o modelo dentro de marca ira devolver o nome da marca
				if(modelo.getNome().toUpperCase().contains(chave.toUpperCase())){
					return marca.getNome(); 
				}
			}
		}
		return null;
	}

	// Relatorio do dia desejado
	private static void showRelatorio() {
        Scanner teclado = new Scanner(System.in);
		int dia, mes, ano;

		System.out.print("    Data de deseja consultar (DD/MM/YYYY): ");
		String data = teclado.nextLine();
        String[] dataIn = data.split("/"); // separando a string em tres partes de um vetor 
        dia = Integer.parseInt(dataIn[0]); // primeira parte é o DD
        mes = Integer.parseInt(dataIn[1]); // segunda parte é o MM
        ano = Integer.parseInt(dataIn[2]); // terceira parte é o YYYY
		
		String[] titulo = {"Placa", "Modelo", "Marca", "Entrada", "Saida", "Valor"};
		System.out.printf("\t%-10s\t%-10s\t%-10s\t%s\t%s\t%s\n", titulo[0], titulo[1], titulo[2], titulo[3], titulo[4], titulo[5]);
		for (Carro carro : historico) {
			if((carro.getEntrada().getDayOfMonth() == dia) 
				&& (carro.getEntrada().getMonthValue() == mes) 
				&& (carro.getEntrada().getYear() == ano)){
				System.out.printf("\t%-10s\t%-10s\t%-10s\t%d:%d\t%d:%d\t%-10s\n", carro.getPlaca(),
																				carro.getModelo().getNome(),
																				consultar(carro.getModelo().getNome()),
																				(carro.getEntrada().getHour()), carro.getEntrada().getMinute(),
																				carro.getSaida().getHour(), carro.getSaida().getMinute(),
																				carro.getValor());
			}
		}
		// teclado.close();
	}

	// Vagas atuais ocupadas
	private static void showVagasOcupadas() {
		System.out.println("- veiculos nas vagas: \n   vaga  modelo(placa)\n-----------------------");
		for(int i = 0; i < vagas.length; i++) {
			if (vagas[i] != null) {
				System.out.println("    " + i + " - " + vagas[i].getModelo().getNome() + "(" + vagas[i].getPlaca() + ")"); 
			}
		}
		System.out.println("-----------------------"); 
	}
}	
