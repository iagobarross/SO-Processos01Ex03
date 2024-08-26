/*EXERCÍCIO 3 – Exclusivo Linux
No universo do Sistema operacional Linux existem diversas distribuições. Os comandos Java
System.getProperty(“os.name”) e System.getProperty(“os.version”) trazem dados sobre o
Kernel Linux, mas não sobre a distribuição.
Criar em Eclipse, um novo Java Project com uma classe chamada DistroController.java no
package controller e uma classe Main.java no package view.

A classe DistroController.java deve ter 2 métodos.
1) O primeiro, chamado os, que identifica e retorna o nome do Sistema Operacional (Fazê-lo
privado)
2) O segundo, chamado exibeDistro, que verifica o SO e, se for Linux, selecione o comando para
exibir as propriedades da distribuição. Deve-se exibir o nome e a versão da distribuição. Caso o
SO não seja Linux, exibir uma mensagem comunicando.
A classe Main.java deve ter um chamado para a exibição das informações
*/
package controller;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {

	public DistroController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void exibeDistro() {
		String os = os();
		if (os.contains("Linux")) {
			StringBuffer name = new StringBuffer();
			String cmd = "cat /etc/os-release";
			try {
				Process p = Runtime.getRuntime().exec(cmd);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				
				while(linha != null) {
					if(linha.contains("NAME") && !linha.contains("PRETTY") && !linha.contains("CODENAME")) {
						String[] div = linha.split("=");
						name.append(div[1] + " ");
						linha = buffer.readLine();
					} else if(linha.contains("VERSION") && !linha.contains("ID") && !linha.contains("CODENAME")) {
						String[] div = linha.split("=");
						name.append(div[1]);
						linha = buffer.readLine();
					} else {
						linha = buffer.readLine();
					}
				}
				
				System.out.println(name);
			} catch(Exception e) {
				String msg = e.getMessage();
				System.err.println(msg);
			}
		} else {
			System.out.println("Sistema Operacional incompatível.");
		}
	}

}