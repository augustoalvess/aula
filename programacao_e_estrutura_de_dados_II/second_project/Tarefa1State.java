public class Tarefa1State {

	public char[][] caixa;

	public Tarefa1State(int l, int c, int[][] bloqueados) {
		this.caixa = new char[l][c];
		for (int x = 0; x < bloqueados.length; x++) {
			this.caixa[bloqueados[x][0]][bloqueados[x][1]] = 'x';
		}
	}

	public Tarefa1State(Tarefa1State s, char[] pecas) {
		this.caixa = s.caixa;
		for (char e: pecas) {
			int[][][] bloco = this.obterBlocoLivre(e);
			if (bloco != null) {
				if (e == 'a') {
					this.caixa[bloco[0][0][0]][bloco[0][0][1]] = e;
					this.caixa[bloco[1][0][0]][bloco[1][0][1]] = e;
					this.caixa[bloco[1][1][0]][bloco[1][1][1]] = e;
				}
				if (e == 'b') {
					this.caixa[bloco[0][0][0]][bloco[0][0][1]] = e;
					this.caixa[bloco[0][1][0]][bloco[0][1][1]] = e;
					this.caixa[bloco[1][1][0]][bloco[1][1][1]] = e;
				}
				if (e == 'c') {
					this.caixa[bloco[0][0][0]][bloco[0][0][1]] = e;
					this.caixa[bloco[0][1][0]][bloco[0][1][1]] = e;
					this.caixa[bloco[1][0][0]][bloco[1][0][1]] = e;
				}
				if (e == 'd') {
					this.caixa[bloco[0][1][0]][bloco[0][1][1]] = e;
					this.caixa[bloco[1][0][0]][bloco[1][0][1]] = e;
					this.caixa[bloco[1][1][0]][bloco[1][1][1]] = e;
				}
			}
		}
	}


	public int[][][] obterBlocoLivre(char e) {
		int[][][] bloco = new int[2][2][2];
		for (int l = 0; l < this.caixa.length; l++) {
			for (int c = 0; c < this.caixa[0].length; c++) {
				if (l+1 < this.caixa.length && c+1 < this.caixa[0].length) {
					// cada elemento possui uma orientação diferente:
					// a=a  b=bb c=cc d= d
					//   aa    b   c    dd
					if (e == 'a') {
						if (this.caixa[l][c] == 0 && this.caixa[l+1][c] == 0 && this.caixa[l+1][c+1] == 0) {
							bloco[0][0] = new int[]{l, c};
							bloco[1][0] = new int[]{l+1, c};
							bloco[1][1] = new int[]{l+1, c+1};
							return bloco;
						}
					}
					if (e == 'b') {
						if (this.caixa[l][c] == 0 && this.caixa[l][c+1] == 0 && this.caixa[l+1][c+1] == 0) {
							bloco[0][0] = new int[]{l, c};
							bloco[0][1] = new int[]{l, c+1};
							bloco[1][1] = new int[]{l+1, c+1};
							return bloco;
						}
					}
					if (e == 'c') {
						if (this.caixa[l][c] == 0 && this.caixa[l][c+1] == 0 && this.caixa[l+1][c] == 0) {
							bloco[0][0] = new int[]{l, c};
							bloco[0][1] = new int[]{l, c+1};
							bloco[1][0] = new int[]{l+1, c};
							return bloco;
						}
					}
					if (e == 'd') {
						if (this.caixa[l][c+1] == 0 && this.caixa[l+1][c] == 0 && this.caixa[l+1][c+1] == 0) {
							bloco[0][1] = new int[]{l, c+1};
							bloco[1][0] = new int[]{l+1, c};
							bloco[1][1] = new int[]{l+1, c+1};
							return bloco;
						}
					}
				}
			}
		}
		return null;
	}

	public boolean isSolution() {
		if (obterBlocoLivre('a') == null && obterBlocoLivre('b') == null && obterBlocoLivre('c') == null && obterBlocoLivre('d') == null) {
			return true;
		}
		for (int l = 0; l < this.caixa.length; l++) {
			for (int c = 0; c < this.caixa[0].length; c++) {
				if (this.caixa[l][c] == 0) {
					return false;
				}
			}
		}
		return true;
    }

	public String toString() {
		String resultado = "";
		for (int l = 0; l < this.caixa.length; l++) {
			for (int c = 0; c < this.caixa[0].length; c++) {
				if (caixa[l][c] != 0) {
					resultado += " " + caixa[l][c] + " ";
					continue;
				}
				resultado += " - ";
			}
			resultado += "\n";
		}
		return resultado;
    }
}