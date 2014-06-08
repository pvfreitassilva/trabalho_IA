import java.util.Random;

public class Tabuleiro {
	int tabuleiro[][];
	int rainhas;
		
	public Tabuleiro (int rainhas){
		int i,j;
		this.rainhas = rainhas;
		tabuleiro = new int[rainhas][rainhas];
		for(i=0;i<rainhas;i++)
			for(j=0;j<rainhas;j++)
				tabuleiro[i][j]=0;
	}
	
	public void distribuiRainhas(){
		int i;
		Random gerador = new Random();
		for(i=0;i<rainhas;i++)
			tabuleiro[i][gerador.nextInt(rainhas)]=1;
	}
	
	public void imprimeTabuleiro(){
		int i,j;
		String linha = new String();
		for(i=0;i<rainhas;i++)
			linha = linha.concat("----");
		linha = linha.concat("-");
		System.out.println(linha);
		for(i=0;i<rainhas;i++){
			System.out.print("| ");
			for(j=0;j<rainhas;j++)
				System.out.print(tabuleiro[i][j]+" | ");
			System.out.println('\n'+linha);
		}
	}
}