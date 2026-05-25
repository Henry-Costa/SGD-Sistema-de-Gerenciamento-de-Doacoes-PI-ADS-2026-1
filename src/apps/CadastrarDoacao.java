package apps;
import classesCustomizadas.*;

public class CadastrarDoacao {

	public static void main(String[] args) {
		
		System.out.print("Criando doador...");
		Doador f = new Doador("Francesco", "teste.teste@teste.com", "+55 (19) 99999-9999", "123.123.987.78");
		System.out.println("ok");
		System.out.println();
		
		System.out.println("------Doador-----------");
		f.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
		
		
		System.out.print("Criando Beneficiário...");
		Beneficiario h = new Beneficiario("Henry", "12345678909","11999999999", "Rua 1", 5);
		System.out.println("ok");
		
		System.out.println("------Beneficiário-----");
		h.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
		
		System.out.print("Criando Campanha...");
		Campanha campanha1 = new Campanha("Campanha do agasalho", false, true, false);
		System.out.println("ok");
		
		System.out.println("------Campanha---------");
		campanha1.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
		
		System.out.print("Cadastrando doação 1...");
		float valorMonetario = 500;
		Doacao doacao1 = new Doacao(f, h, campanha1, valorMonetario);
		h.registrarRecebimento(doacao1);
		System.out.println("ok");
		
		System.out.println("------Doação-1---------");
		doacao1.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
		
		System.out.print("Cadastrando doação 2...");
		double alimento = 10.500;
		Doacao doacao2 = new Doacao(f, h, campanha1, alimento);
		h.registrarRecebimento(doacao2);
		System.out.println("ok");
		
		System.out.println("------Doação-2---------");
		doacao2.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
		
		System.out.print("Cadastrando doação 3...");
		int agasalhos = 2;
		Doacao doacao3 = new Doacao(f, h, campanha1, agasalhos);
		h.registrarRecebimento(doacao3);
		System.out.println("ok");
		
		System.out.println("------Doação-3---------");
		doacao3.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
		
		System.out.println("------Beneficiário-----");
		h.mostrarInfo();
		System.out.println("-----------------------");
		System.out.println();
	}

}
