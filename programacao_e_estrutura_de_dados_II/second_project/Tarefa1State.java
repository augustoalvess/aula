public class Tarefa1State {
	public int l;
	public int c;
	public char[][] caixa;

	public Tarefa1State(int l, int c) {
		this.l = l;
		this.c = c;
		this.caixa = new char[l][c];
	}

	public Tarefa1State(char e) {
		// Aqui preciso fazer a lógica de inserção em L do elemento na mátriz,
		// Somente se o elemento cabe nos espaços da matriz, ou seja, se não
		// existe elementos nos espaços necessários para montar um L.
	}

	public boolean isSolution() {

		// Medir os espaços vazios, verificar se não cabe mais nenhuma peça, então é solução.

        return true;
    }

	public String toString() {
		String resultado = "";
		for (int x = 0; x < l; x++) {
			for (int y = 0; y < c; y++) {
				if (caixa[x][y] != 0) {
					resultado += " " + caixa[x][y] + " ";
				}
				resultado += " - ";
			}
			resultado += "\n";
		}
		return resultado;
    }
}