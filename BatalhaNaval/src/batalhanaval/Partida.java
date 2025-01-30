package batalhanaval;

import java.util.Random;

public class Partida {

    private boolean vez;
    private int acertosPlayer;
    private int acertosIA;
    private Quadrado quadradosPlayers[][];
    private Quadrado quadradosIA[][];

    public Partida() {
        this.vez = SorteiaVez();
        this.acertosPlayer = 0;
        this.acertosIA = 0;
        this.quadradosPlayers = new Quadrado[5][10];
        this.quadradosIA = new Quadrado[5][10];

        ConfiguraQuadrado(quadradosIA);
        ConfiguraQuadrado(quadradosPlayers);
    }

    // criamos uma subclasse para armazenar valores de cada quadrado
    private static class Quadrado {

        private int valor;
        private String nome;

        public Quadrado(String nome) {
            this.valor = 0;
            this.nome = nome;
        }

        public int getValor() {
            return valor;
        }

        public String getNome() {
            return nome;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

    }

    // get e set dos atributos da classe partida
    public boolean isVez() {
        return vez;
    }

    public void setVez(boolean vez) {
        this.vez = vez;
    }

    public int getAcertosPlayer() {
        return acertosPlayer;
    }

    public void setAcertosPlayer(int acertosPlayer) {
        this.acertosPlayer = acertosPlayer;
    }

    public int getAcertosIA() {
        return acertosIA;
    }

    public void setAcertosIA(int acertosIA) {
        this.acertosIA = acertosIA;
    }

    public Quadrado[][] getQuadradosPlayers() {
        return quadradosPlayers;
    }

    public void setQuadradosPlayers(Quadrado[][] quadradosPlayers) {
        this.quadradosPlayers = quadradosPlayers;
    }

    public Quadrado[][] getQuadradosIA() {
        return quadradosIA;
    }

    public void setQuadradosIA(Quadrado[][] quadradosIA) {
        this.quadradosIA = quadradosIA;
    }

    //setando o nome de cada quadrado de acordo com sua posição
    public static void ConfiguraQuadrado(Quadrado quadrados[][]) {
        for (int i = 0; i < 10; i++) {
            int p = i + 1;
            Quadrado quadConfig = new Quadrado("");
            quadConfig.setNome("A" + p);
            quadrados[0][i] = quadConfig;
        }

        for (int i = 0; i < 10; i++) {
            int p = i + 1;
            Quadrado quadConfig = new Quadrado("");
            quadConfig.setNome("B" + p);
            quadrados[1][i] = quadConfig;
        }

        for (int i = 0; i < 10; i++) {
            int p = i + 1;
            Quadrado quadConfig = new Quadrado("");
            quadConfig.setNome("C" + p);
            quadrados[2][i] = quadConfig;
        }

        for (int i = 0; i < 10; i++) {
            int p = i + 1;
            Quadrado quadConfig = new Quadrado("");
            quadConfig.setNome("D" + p);
            quadrados[3][i] = quadConfig;
        }

        for (int i = 0; i < 10; i++) {
            int p = i + 1;
            Quadrado quadConfig = new Quadrado("");
            quadConfig.setNome("E" + p);
            quadrados[4][i] = quadConfig;
        }
    }

    //sorteia o jogador que irá começar o jogo
    public static boolean SorteiaVez() {
        Random gerador = new Random();
        //1 - true 2 - false
        int indice = gerador.nextInt(1, 3);
        if (indice == 1) {
            return true;
        }
        return false;
    }

    //verifica o valor(status) do quadrado escolhido pelo jogador e retorna o inteiro:
    // -1 e 2 -> quadrado inexistente ou ja selecionado
    // 0 -> quadrado "vazio", sem embarcação
    // 1 -> quadrado "ocupado", com embarcação
    public int VerificaaQuadradoPlayer(String posicao) {
        for (int linha = 0; linha < this.quadradosPlayers.length; linha++) {
            for (int coluna = 0; coluna < this.quadradosPlayers[0].length; coluna++) {
                if (this.quadradosPlayers[linha][coluna].getNome().equalsIgnoreCase(posicao)) {
                    if (this.quadradosPlayers[linha][coluna].getValor() == 0) {
                        return 0;
                    } else if (this.quadradosPlayers[linha][coluna].getValor() == 1) {
                        return 1;
                    } else if (this.quadradosPlayers[linha][coluna].getValor() == 2) {
                        return 2;
                    }
                }
            }
        }
        return -1;
    }

    //verifica o valor(status) do quadrado escolhido pela IA e retorna o inteiro:
    // -1 e 2 -> quadrado inexistente ou ja selecionado
    // 0 -> quadrado "vazio", sem embarcação
    // 1 -> quadrado "ocupado", com embarcação
    public int VerificaaQuadradoIA(String posicao) {
        for (int linha = 0; linha < this.quadradosIA.length; linha++) {
            for (int coluna = 0; coluna < this.quadradosIA[0].length; coluna++) {
                if (this.quadradosIA[linha][coluna].getNome().equalsIgnoreCase(posicao)) {
                    if (this.quadradosIA[linha][coluna].getValor() == 0) {
                        return 0;
                    } else if (this.quadradosIA[linha][coluna].getValor() == 1) {
                        return 1;
                    } else if (this.quadradosIA[linha][coluna].getValor() == 2) {
                        return 2;
                    }
                }
            }
        }
        return -1;
    }

    //seta o valor do quadrado selecionado
    public void AlteraValorQuadradoPlayer(String posicao, int valor) {
        for (int linha = 0; linha < this.quadradosPlayers.length; linha++) {
            for (int coluna = 0; coluna < this.quadradosPlayers[0].length; coluna++) {
                if (this.quadradosPlayers[linha][coluna].getNome().equalsIgnoreCase(posicao)) {
                    this.quadradosPlayers[linha][coluna].setValor(valor);
                }
            }
        }
    }

    //seta o valor do quadrado selecionado
    public void AlteraValorQuadradoIA(String posicao, int valor) {
        for (int linha = 0; linha < this.quadradosIA.length; linha++) {
            for (int coluna = 0; coluna < this.quadradosIA[0].length; coluna++) {
                if (this.quadradosIA[linha][coluna].getNome().equalsIgnoreCase(posicao)) {
                    this.quadradosIA[linha][coluna].setValor(valor);
                }
            }
        }
    }

    //altera vez dos jogadores
    public void AlteraVez() {
        if (this.vez) {
            this.vez = false;
        } else {
            this.vez = true;
        }
    }

    //atualiza pontuacao dos jogadores ao acertar um quadrado ocupado com embarcação
    public void atualizaPlacar(boolean i) {
        if (i) {
            this.acertosPlayer++;
        } else {
            this.acertosIA++;
        }
    }

    //verifica os acertos de cada jogador e se alguem obter uma pontuação de 10, retornará true
    public boolean verificaVencedor() {
        if (this.acertosPlayer == 10 || this.acertosIA == 10) {
            return true;
        }
        return false;
    }

    //reinicia o jogo, altera todos os valores para o estado inicial de jogo
    public void reiniciarJogo() {
        for (int linha = 0; linha < this.quadradosPlayers.length; linha++) {
            for (int coluna = 0; coluna < this.quadradosPlayers[0].length; coluna++) {
                this.quadradosPlayers[linha][coluna].setValor(0);
            }
        }

        for (int linha = 0; linha < this.quadradosIA.length; linha++) {
            for (int coluna = 0; coluna < this.quadradosIA[0].length; coluna++) {
                this.quadradosIA[linha][coluna].setValor(0);
            }
        }

        SorteiaVez();
        this.acertosPlayer = 0;
        this.acertosIA = 0;
    }
}
