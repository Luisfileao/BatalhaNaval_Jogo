package batalhanaval;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class JanelaPrincipal extends javax.swing.JFrame {

    private JPanel paineisPlayer[] = new JPanel[50];
    private JPanel paineisIA[] = new JPanel[50];
    private int controlaAtirarBtn = 0;
    private Partida partida = new Partida();

    private Timer timer = new Timer(1500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ataqueIA();
        }
    });

    //construtor default que contém os métodos: initComponents e MinhasConfiguracoes
    public JanelaPrincipal() {
        initComponents();
        MinhasConfiguracoes();
    }

    //configurações básicas para o frame e seus componentes
    public void MinhasConfiguracoes() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setExtendedState(NORMAL);
        AtirarBTN.setEnabled(false);
        TextFieldDisparo.setEnabled(false);

        TrocaBloqueia();
        AgrupaPainel();

        Legenda1.setBackground(Color.green);
        Legenda2.setBackground(Color.red);
        Legenda3.setBackground(Color.blue);
    }

    //adição de todos as checkbox ao um ButtonGroup especifico da embarcação
    public void TrocaBloqueia() {
        ButtonGroup portaAviaosGroup = new ButtonGroup();
        ButtonGroup fragataGroup = new ButtonGroup();
        ButtonGroup CruzadorGroup = new ButtonGroup();

        portaAviaosGroup.add(HoriPortaAvioes);
        portaAviaosGroup.add(VertiPortaAvioes);
        fragataGroup.add(HoriFragata);
        fragataGroup.add(VertiFragata);
        CruzadorGroup.add(HoriCruzador);
        CruzadorGroup.add(VertiCruzador);
    }

    //agrupa paineis e seta os valores de cada posicao e dos paineis dos jogadores
    public void AgrupaPainel() {
        JPanel paineisPlayer1[] = {A1Painel, A2Painel, A3Painel, A4Painel, A5Painel, A6Painel, A7Painel, A8Painel, A9Painel, A10Painel,
            B1Painel, B2Painel, B3Painel, B4Painel, B5Painel, B6Painel, B7Painel, B8Painel, B9Painel, B10Painel,
            C1Painel, C2Painel, C3Painel, C4Painel, C5Painel, C6Painel, C7Painel, C8Painel, C9Painel, C10Painel,
            D1Painel, D2Painel, D3Painel, D4Painel, D5Painel, D6Painel, D7Painel, D8Painel, D9Painel, D10Painel,
            E1Painel, E2Painel, E3Painel, E4Painel, E5Painel, E6Painel, E7Painel, E8Painel, E9Painel, E10Painel
        };
        JPanel paineisIA1[] = {IAPainelA1, IAPainelA2, IAPainelA3, IAPainelA4, IAPainelA5, IAPainelA6, IAPainelA7, IAPainelA8, IAPainelA9, IAPainelA10,
            IAPainelB1, IAPainelB2, IAPainelB3, IAPainelB4, IAPainelB5, IAPainelB6, IAPainelB7, IAPainelB8, IAPainelB9, IAPainelB10,
            IAPainelC1, IAPainelC2, IAPainelC3, IAPainelC4, IAPainelC5, IAPainelC6, IAPainelC7, IAPainelC8, IAPainelC9, IAPainelC10,
            IAPainelD1, IAPainelD2, IAPainelD3, IAPainelD4, IAPainelD5, IAPainelD6, IAPainelD7, IAPainelD8, IAPainelD9, IAPainelD10,
            IAPainelE1, IAPainelE2, IAPainelE3, IAPainelE4, IAPainelE5, IAPainelE6, IAPainelE7, IAPainelE8, IAPainelE9, IAPainelE10
        };
        this.paineisPlayer = paineisPlayer1;
        this.paineisIA = paineisIA1;

        SetarNomePaineisPlayer();
        SetarNomePaineisIA();
    }

    //altera cor de um quadrado escolhido pelo usuario de acordo com seu status
    public void AlteraCorPanel(String posicao, Color cor, JPanel paineis[]) {
        String nome = paineis[0].getName();
        String palavra = nome.substring(2);

        if (palavra.equalsIgnoreCase("Painel")) {
            this.partida.AlteraValorQuadradoPlayer(posicao, 1);
            for (int i = 0; i < paineis.length; i++) {
                if (paineis[i].getName().equalsIgnoreCase(posicao + "Painel")) {
                    paineis[i].setBackground(cor);
                }
            }
        } else {
            this.partida.AlteraValorQuadradoIA(posicao, 1);
            for (int i = 0; i < paineis.length; i++) {
                if (paineis[i].getName().equalsIgnoreCase(posicao + "IA")) {
                    paineis[i].setBackground(cor);
                }
            }
        }
    }

    //recebe a coordenada do player e, completa os quadrados de cada embarcação de acordo com seu tamanho e direção no painel do player
    public void CompletaQuadrados(String posicao, int navegacao, JRadioButton Horizontal, JRadioButton Vertical) {
        char separadoLetra = posicao.charAt(0);
        int separadoNumero = Integer.parseInt(posicao.substring(1));
        int separadoLetraAscii = (int) (separadoLetra);

        if (Horizontal.isSelected()) {
            for (int i = 0; i < navegacao; i++) {
                partida.AlteraValorQuadradoPlayer("" + separadoLetra + separadoNumero++, 1);
                AlteraCorPanel("" + separadoLetra + separadoNumero, Color.green, this.paineisPlayer);
            }
        } else if (Vertical.isSelected()) {
            for (int i = 0; i < navegacao; i++) {
                separadoLetraAscii++;
                char texto = (char) (separadoLetraAscii);
                partida.AlteraValorQuadradoPlayer("" + texto + separadoNumero, 1);
                AlteraCorPanel("" + texto + separadoNumero, Color.green, this.paineisPlayer);
            }
        }
    }

    //recebe a coordenada da IA e, completa os quadrados de cada embarcação de acordo com seu tamanho e direção no painel da IA
    public void CompletaQuadradosIA(String posicao, int navegacao, boolean Horizontal, boolean Vertical) {
        char separadoLetra = posicao.charAt(0);
        int separadoNumero = Integer.parseInt(posicao.substring(1));
        int separadoLetraAscii = (int) (separadoLetra);

        if (Horizontal) {
            for (int i = 0; i < navegacao; i++) {
                separadoNumero++;
                partida.AlteraValorQuadradoIA("" + separadoLetra + separadoNumero, 1);
            }
        } else if (Vertical) {
            for (int i = 0; i < navegacao; i++) {
                separadoLetraAscii++;
                char texto = (char) (separadoLetraAscii);
                partida.AlteraValorQuadradoIA("" + texto + separadoNumero, 1);
            }
        }
    }

    //verifica se as embarcações podem ser inteiramente colocadas no tabuleiro 
    public boolean TestaLimiteArea(String posicao, int navegacao, JRadioButton horizontal, JRadioButton vertical) {
        char separadoLetra = posicao.charAt(0);
        int separadoNumero = Integer.parseInt(posicao.substring(1));

        if (horizontal.isSelected()) {
            if (separadoNumero >= 10 - (navegacao - 1)) {
                JOptionPane.showMessageDialog(this, "Tamanho insuficiente para o posicionamento da Tropa.");
                return false;
            } else {
                return true;
            }
        } else {
            int valorLetra = (int) separadoLetra;
            valorLetra += navegacao;

            char letraEMaiusculo = 'E';
            int valorEMaiusculo = (int) letraEMaiusculo;
            char letraEMinusculo = 'e';
            int valorEMinusculo = (int) letraEMinusculo;

            if (valorLetra <= valorEMaiusculo || (valorLetra >= 97 && valorLetra <= valorEMinusculo)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Tamanho insuficiente para o posicionamento da Tropa.");
                return false;
            }
        }
    }

    //recebe a coordenada escolhida e verifica se ela não irá sobrescrever uma embarcação já posicionada
    public boolean TestaCompletaQuadradosPlayer(String posicao, int navegacao, JRadioButton horizontal, JRadioButton vertical, JTextField txtfield) {
        char separadoLetra = posicao.charAt(0);
        int separadoNumero = Integer.parseInt(posicao.substring(1));
        int numeroSeparadoLetra = (int) separadoLetra;
        int controlador = 0;

        if (horizontal.isSelected()) {
            for (int i = 1; i <= navegacao; i++) {
                if (this.partida.VerificaaQuadradoPlayer("" + separadoLetra + (separadoNumero + i)) == 1) {
                    controlador++;
                }
            }
        } else if (vertical.isSelected()) {
            for (int i = 1; i <= navegacao; i++) {
                char letra = (char) (numeroSeparadoLetra + i);
                if (this.partida.VerificaaQuadradoPlayer("" + letra + separadoNumero) == 1) {
                    controlador++;
                }
            }
        }

        if (controlador > 0) {
            JOptionPane.showMessageDialog(this, "Alguma das posições selecionadas para a embarcação já está ocupada.");
            txtfield.setText("");
            return false;
        }
        return true;
    }

    //recebe a coordenada escolhida e verifica se ela não irá sobrescrever uma embarcação já posicionada
    public boolean TestaCompletaQuadradosIA(String posicao, int navegacao, boolean horizontal, boolean vertical) {
        char separadoLetra = posicao.charAt(0);
        int separadoNumero = Integer.parseInt(posicao.substring(1));
        int numeroSeparadoLetra = (int) separadoLetra;
        int controlador = 0;

        if (horizontal) {
            for (int i = 1; i <= navegacao; i++) {
                if (this.partida.VerificaaQuadradoIA("" + separadoLetra + (separadoNumero + i)) == 1) {
                    controlador++;
                }
            }
        } else if (vertical) {
            for (int i = 1; i <= navegacao; i++) {
                char letra = (char) (numeroSeparadoLetra + i);
                if (this.partida.VerificaaQuadradoIA("" + letra + separadoNumero) == 1) {
                    controlador++;
                }
            }
        }
        if (controlador > 0) {
            return false;
        }
        return true;
    }

    //posiciona as tropas do player no tabuleiro
    public void ModificaQuadrado(JTextField txtfield, int navegacao) {
        if (this.partida.VerificaaQuadradoPlayer(txtfield.getText()) == 0) {
            if (navegacao == 3 && TestaLimiteArea(txtfield.getText(), navegacao, HoriPortaAvioes, VertiPortaAvioes) && TestaCompletaQuadradosPlayer(txtfield.getText(), navegacao, HoriPortaAvioes, VertiPortaAvioes, txtfield)) {
                AlteraCorPanel(txtfield.getText(), Color.green, paineisPlayer);
                CompletaQuadrados(txtfield.getText(), navegacao, HoriPortaAvioes, VertiPortaAvioes);
                txtfield.setEnabled(false);
                HoriPortaAvioes.setEnabled(false);
                VertiPortaAvioes.setEnabled(false);

                controlaAtirarBtn++;
            }
            if (navegacao == 2 && TestaLimiteArea(txtfield.getText(), navegacao, HoriFragata, VertiFragata) && TestaCompletaQuadradosPlayer(txtfield.getText(), navegacao, HoriFragata, VertiFragata, txtfield)) {
                AlteraCorPanel(txtfield.getText(), Color.green, paineisPlayer);
                CompletaQuadrados(txtfield.getText(), navegacao, HoriFragata, VertiFragata);
                txtfield.setEnabled(false);
                HoriFragata.setEnabled(false);
                VertiFragata.setEnabled(false);

                controlaAtirarBtn++;
            }
            if (navegacao == 1 && TestaLimiteArea(txtfield.getText(), navegacao, HoriCruzador, VertiCruzador) && TestaCompletaQuadradosPlayer(txtfield.getText(), navegacao, HoriCruzador, VertiCruzador, txtfield)) {
                AlteraCorPanel(txtfield.getText(), Color.green, paineisPlayer);
                CompletaQuadrados(txtfield.getText(), navegacao, HoriCruzador, VertiCruzador);
                txtfield.setEnabled(false);
                HoriCruzador.setEnabled(false);
                VertiCruzador.setEnabled(false);

                controlaAtirarBtn++;
            }

            if (navegacao == 0) {
                AlteraCorPanel(txtfield.getText(), Color.green, paineisPlayer);
                txtfield.setEnabled(false);

                controlaAtirarBtn++;
            }
        } else if (txtfield.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Posição Indisponível ou Inexistente");
            txtfield.setText("");
        }
    }

    //seta o nome de cada Panel no tabuleiro do player
    public void SetarNomePaineisPlayer() {
        int numeroQuadrado = 0;
        for (int i = 0; i < 10; i++) {
            numeroQuadrado++;
            this.paineisPlayer[i].setName("A" + numeroQuadrado + "Painel");
        }

        numeroQuadrado = 0;
        for (int i = 10; i < 20; i++) {
            numeroQuadrado++;
            this.paineisPlayer[i].setName("B" + numeroQuadrado + "Painel");
        }

        numeroQuadrado = 0;
        for (int i = 20; i < 30; i++) {
            numeroQuadrado++;
            this.paineisPlayer[i].setName("C" + numeroQuadrado + "Painel");
        }

        numeroQuadrado = 0;
        for (int i = 30; i < 40; i++) {
            numeroQuadrado++;
            this.paineisPlayer[i].setName("D" + numeroQuadrado + "Painel");
        }

        numeroQuadrado = 0;
        for (int i = 40; i < 50; i++) {
            numeroQuadrado++;
            this.paineisPlayer[i].setName("E" + numeroQuadrado + "Painel");
        }
    }

    //seta o nome de cada Panel no tabuleiro da IA
    public void SetarNomePaineisIA() {
        int numeroQuadrado = 0;
        for (int i = 0; i < 10; i++) {
            numeroQuadrado++;
            this.paineisIA[i].setName("A" + numeroQuadrado + "IA");
        }

        numeroQuadrado = 0;
        for (int i = 10; i < 20; i++) {
            numeroQuadrado++;
            this.paineisIA[i].setName("B" + numeroQuadrado + "IA");
        }

        numeroQuadrado = 0;
        for (int i = 20; i < 30; i++) {
            numeroQuadrado++;
            this.paineisIA[i].setName("C" + numeroQuadrado + "IA");
        }

        numeroQuadrado = 0;
        for (int i = 30; i < 40; i++) {
            numeroQuadrado++;
            this.paineisIA[i].setName("D" + numeroQuadrado + "IA");
        }

        numeroQuadrado = 0;
        for (int i = 40; i < 50; i++) {
            numeroQuadrado++;
            this.paineisIA[i].setName("E" + numeroQuadrado + "IA");
        }
    }

    //Informa, no frame, a vez do jogador
    public void InformaVez() {
        if (partida.isVez()) {
            this.LabelVez.setText("Player");
        } else {
            this.LabelVez.setText("IA");
        }
    }

    //posiciona as tropas da IA no tabuleiro
    public void PosiscionarTropasIA() {
        Random gerador = new Random();

        SorteiaPosicaoTropasIA(3);
        SorteiaPosicaoTropasIA(2);
        SorteiaPosicaoTropasIA(1);
        SorteiaPosicaoTropasIA(0);
    }

    //com o metodo Random, sorteia a direcao e a coordenada para embarcação
    public void SorteiaPosicaoTropasIA(int navegacao) {
        Random gerador = new Random();
        int indiceDirecao = gerador.nextInt(1, 3);  //1 - Horizontal /2 - Vertical

        if (indiceDirecao == 1) {
            char letraFileira = (char) gerador.nextInt(65, 70);
            int numeroColuna = gerador.nextInt(1, 10 - (navegacao - 1));

            while (this.partida.VerificaaQuadradoIA("" + letraFileira + numeroColuna) == 1 || TestaCompletaQuadradosIA("" + letraFileira + numeroColuna, navegacao, true, false) == false) {
                letraFileira = (char) gerador.nextInt(65, 70);
                numeroColuna = gerador.nextInt(1, 10 - (navegacao - 1));
            }

            partida.AlteraValorQuadradoIA("" + letraFileira + numeroColuna, 1);
            CompletaQuadradosIA("" + letraFileira + numeroColuna, navegacao, true, false);

        } else {
            char letraFileira = (char) gerador.nextInt(65, 70 - navegacao);
            int numeroColuna = gerador.nextInt(1, 11);

            while (this.partida.VerificaaQuadradoIA("" + letraFileira + numeroColuna) == 1 || TestaCompletaQuadradosIA("" + letraFileira + numeroColuna, navegacao, false, true) == false) {
                letraFileira = (char) gerador.nextInt(65, 70 - navegacao);
                numeroColuna = gerador.nextInt(1, 11);
            }

            partida.AlteraValorQuadradoIA("" + letraFileira + numeroColuna, 1);
            CompletaQuadradosIA("" + letraFileira + numeroColuna, navegacao, false, true);
        }
    }

    //realiza o ataque da IA ao player, se acertar muda o quadrado pra vermelho e, se errar, muda para azul
    public void ataqueIA() {
        timer.stop();

        Random gerador = new Random();
        char letraFileira = (char) gerador.nextInt(65, 70);
        int numeroColuna = gerador.nextInt(1, 11);
        String posicao = "" + letraFileira + numeroColuna;

        if (this.partida.VerificaaQuadradoPlayer(posicao) == 1) {
            AlteraCorPanel(posicao, Color.red, paineisPlayer);
            this.partida.AlteraValorQuadradoPlayer(posicao, 2);
            this.partida.AlteraVez();
            this.partida.atualizaPlacar(false);
            int pontos = Integer.parseInt(pontosIA.getText()) + 1;
            this.pontosIA.setText(pontos + "");

            if (this.partida.verificaVencedor()) {
                JOptionPane.showMessageDialog(this, "IA venceu!!!");
            } else {
                InformaVez();
                AtirarBTN.setEnabled(true);
                TextFieldDisparo.setEnabled(true);
            }
        } else if (this.partida.VerificaaQuadradoPlayer(posicao) == 0) {
            AlteraCorPanel(posicao, Color.blue, paineisPlayer);
            this.partida.AlteraValorQuadradoPlayer(posicao, 2);
            this.partida.AlteraVez();
            InformaVez();
            AtirarBTN.setEnabled(true);
            TextFieldDisparo.setEnabled(true);
        } else if (partida.VerificaaQuadradoPlayer(posicao) == 2 || partida.VerificaaQuadradoPlayer(posicao) == -1) {
            ataqueIA();
        }

        labelAcao.setText("-");
    }

    //realiza o ataque do player a IA, se acertar muda o quadrado pra vermelho e, se errar, muda para azul
    public void ataquePlayer() {
        if (this.partida.VerificaaQuadradoIA(TextFieldDisparo.getText()) == 1) {
            AlteraCorPanel(TextFieldDisparo.getText(), Color.red, paineisIA);
            this.partida.AlteraValorQuadradoIA(TextFieldDisparo.getText(), 2);
            this.partida.AlteraVez();
            this.partida.atualizaPlacar(true);
            int pontos = Integer.parseInt(pontosPlayer.getText()) + 1;
            this.pontosPlayer.setText(pontos + "");

            if (this.partida.verificaVencedor()) {
                JOptionPane.showMessageDialog(this, "Player venceu!!!");
                AtirarBTN.setEnabled(false);
                TextFieldDisparo.setEnabled(false);
            } else {
                TextFieldDisparo.setText("");
                InformaVez();
                AtirarBTN.setEnabled(false);                
                TextFieldDisparo.setEnabled(false);

                labelAcao.setText("IA está jogando...");
                timer.start();
            }
        } else if (this.partida.VerificaaQuadradoIA(TextFieldDisparo.getText()) == 0) {
            AlteraCorPanel(TextFieldDisparo.getText(), Color.blue, paineisIA);
            this.partida.AlteraValorQuadradoIA(TextFieldDisparo.getText(), 2);
            this.partida.AlteraVez();
            TextFieldDisparo.setText("");
            InformaVez();
            AtirarBTN.setEnabled(false);            
            TextFieldDisparo.setEnabled(false);

            labelAcao.setText("IA está jogando...");
            timer.start();
        } else if (partida.VerificaaQuadradoIA(TextFieldDisparo.getText()) == 2 || partida.VerificaaQuadradoIA(TextFieldDisparo.getText()) == -1) {
            JOptionPane.showMessageDialog(this, "Esta posição é inexistente ou já foi selecionada!");
            TextFieldDisparo.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel41 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        A1Painel = new javax.swing.JPanel();
        B1Painel = new javax.swing.JPanel();
        C1Painel = new javax.swing.JPanel();
        D1Painel = new javax.swing.JPanel();
        B2Painel = new javax.swing.JPanel();
        A2Painel = new javax.swing.JPanel();
        A3Painel = new javax.swing.JPanel();
        A4Painel = new javax.swing.JPanel();
        A5Painel = new javax.swing.JPanel();
        A6Painel = new javax.swing.JPanel();
        A7Painel = new javax.swing.JPanel();
        A8Painel = new javax.swing.JPanel();
        A9Painel = new javax.swing.JPanel();
        E1Painel = new javax.swing.JPanel();
        A10Painel = new javax.swing.JPanel();
        C2Painel = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        B4Painel = new javax.swing.JPanel();
        B5Painel = new javax.swing.JPanel();
        B6Painel = new javax.swing.JPanel();
        B7Painel = new javax.swing.JPanel();
        B8Painel = new javax.swing.JPanel();
        B10Painel = new javax.swing.JPanel();
        B9Painel = new javax.swing.JPanel();
        C3Painel = new javax.swing.JPanel();
        C4Painel = new javax.swing.JPanel();
        C5Painel = new javax.swing.JPanel();
        C6Painel = new javax.swing.JPanel();
        C7Painel = new javax.swing.JPanel();
        C8Painel = new javax.swing.JPanel();
        C10Painel = new javax.swing.JPanel();
        D2Painel = new javax.swing.JPanel();
        E2Painel = new javax.swing.JPanel();
        B3Painel = new javax.swing.JPanel();
        C9Painel = new javax.swing.JPanel();
        D3Painel = new javax.swing.JPanel();
        D4Painel = new javax.swing.JPanel();
        D5Painel = new javax.swing.JPanel();
        D6Painel = new javax.swing.JPanel();
        D7Painel = new javax.swing.JPanel();
        D8Painel = new javax.swing.JPanel();
        D9Painel = new javax.swing.JPanel();
        D10Painel = new javax.swing.JPanel();
        E3Painel = new javax.swing.JPanel();
        E4Painel = new javax.swing.JPanel();
        E5Painel = new javax.swing.JPanel();
        E6Painel = new javax.swing.JPanel();
        E7Painel = new javax.swing.JPanel();
        E8Painel = new javax.swing.JPanel();
        E9Painel = new javax.swing.JPanel();
        E10Painel = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        IAPainelA1 = new javax.swing.JPanel();
        IAPainelB1 = new javax.swing.JPanel();
        IAPainelC1 = new javax.swing.JPanel();
        IAPainelD1 = new javax.swing.JPanel();
        IAPainelB2 = new javax.swing.JPanel();
        IAPainelA2 = new javax.swing.JPanel();
        IAPainelA3 = new javax.swing.JPanel();
        IAPainelA4 = new javax.swing.JPanel();
        IAPainelA5 = new javax.swing.JPanel();
        IAPainelA6 = new javax.swing.JPanel();
        IAPainelA7 = new javax.swing.JPanel();
        IAPainelA8 = new javax.swing.JPanel();
        IAPainelA9 = new javax.swing.JPanel();
        IAPainelE1 = new javax.swing.JPanel();
        IAPainelA10 = new javax.swing.JPanel();
        IAPainelC2 = new javax.swing.JPanel();
        IAPainelB4 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        IAPainelB5 = new javax.swing.JPanel();
        IAPainelB6 = new javax.swing.JPanel();
        IAPainelB7 = new javax.swing.JPanel();
        IAPainelB8 = new javax.swing.JPanel();
        IAPainelB10 = new javax.swing.JPanel();
        IAPainelB9 = new javax.swing.JPanel();
        IAPainelC3 = new javax.swing.JPanel();
        IAPainelC4 = new javax.swing.JPanel();
        IAPainelC5 = new javax.swing.JPanel();
        IAPainelC6 = new javax.swing.JPanel();
        IAPainelC7 = new javax.swing.JPanel();
        IAPainelC8 = new javax.swing.JPanel();
        IAPainelC10 = new javax.swing.JPanel();
        IAPainelD2 = new javax.swing.JPanel();
        IAPainelE2 = new javax.swing.JPanel();
        IAPainelB3 = new javax.swing.JPanel();
        IAPainelC9 = new javax.swing.JPanel();
        IAPainelD3 = new javax.swing.JPanel();
        IAPainelD4 = new javax.swing.JPanel();
        IAPainelD5 = new javax.swing.JPanel();
        IAPainelD6 = new javax.swing.JPanel();
        IAPainelD7 = new javax.swing.JPanel();
        IAPainelD8 = new javax.swing.JPanel();
        IAPainelD9 = new javax.swing.JPanel();
        IAPainelD10 = new javax.swing.JPanel();
        IAPainelE3 = new javax.swing.JPanel();
        IAPainelE4 = new javax.swing.JPanel();
        IAPainelE5 = new javax.swing.JPanel();
        IAPainelE6 = new javax.swing.JPanel();
        IAPainelE7 = new javax.swing.JPanel();
        IAPainelE8 = new javax.swing.JPanel();
        IAPainelE9 = new javax.swing.JPanel();
        IAPainelE10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        pontosPlayer = new javax.swing.JLabel();
        pontosIA = new javax.swing.JLabel();
        TextFieldPortaAvioes = new javax.swing.JTextField();
        TextFieldFragata = new javax.swing.JTextField();
        TextFieldCruzador = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        TextFieldSubmarino = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        IniciarBTN = new javax.swing.JButton();
        HoriPortaAvioes = new javax.swing.JRadioButton();
        VertiPortaAvioes = new javax.swing.JRadioButton();
        HoriFragata = new javax.swing.JRadioButton();
        VertiFragata = new javax.swing.JRadioButton();
        HoriCruzador = new javax.swing.JRadioButton();
        VertiCruzador = new javax.swing.JRadioButton();
        TextFieldDisparo = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        AtirarBTN = new javax.swing.JButton();
        Legenda1 = new javax.swing.JPanel();
        Legenda2 = new javax.swing.JPanel();
        Legenda3 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        LabelVez = new javax.swing.JLabel();
        ReiniciarBtn = new javax.swing.JButton();
        labelAcao = new javax.swing.JLabel();

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setText("E");

        jButton1.setText("jButton1");

        jRadioButton5.setText("H");

        jRadioButton6.setText("V");

        jLabel41.setText("jLabel41");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout A1PainelLayout = new javax.swing.GroupLayout(A1Painel);
        A1Painel.setLayout(A1PainelLayout);
        A1PainelLayout.setHorizontalGroup(
            A1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A1PainelLayout.setVerticalGroup(
            A1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B1PainelLayout = new javax.swing.GroupLayout(B1Painel);
        B1Painel.setLayout(B1PainelLayout);
        B1PainelLayout.setHorizontalGroup(
            B1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        B1PainelLayout.setVerticalGroup(
            B1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C1PainelLayout = new javax.swing.GroupLayout(C1Painel);
        C1Painel.setLayout(C1PainelLayout);
        C1PainelLayout.setHorizontalGroup(
            C1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C1PainelLayout.setVerticalGroup(
            C1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D1PainelLayout = new javax.swing.GroupLayout(D1Painel);
        D1Painel.setLayout(D1PainelLayout);
        D1PainelLayout.setHorizontalGroup(
            D1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D1PainelLayout.setVerticalGroup(
            D1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B2PainelLayout = new javax.swing.GroupLayout(B2Painel);
        B2Painel.setLayout(B2PainelLayout);
        B2PainelLayout.setHorizontalGroup(
            B2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        B2PainelLayout.setVerticalGroup(
            B2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A2PainelLayout = new javax.swing.GroupLayout(A2Painel);
        A2Painel.setLayout(A2PainelLayout);
        A2PainelLayout.setHorizontalGroup(
            A2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A2PainelLayout.setVerticalGroup(
            A2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A3PainelLayout = new javax.swing.GroupLayout(A3Painel);
        A3Painel.setLayout(A3PainelLayout);
        A3PainelLayout.setHorizontalGroup(
            A3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A3PainelLayout.setVerticalGroup(
            A3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A4PainelLayout = new javax.swing.GroupLayout(A4Painel);
        A4Painel.setLayout(A4PainelLayout);
        A4PainelLayout.setHorizontalGroup(
            A4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A4PainelLayout.setVerticalGroup(
            A4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A5PainelLayout = new javax.swing.GroupLayout(A5Painel);
        A5Painel.setLayout(A5PainelLayout);
        A5PainelLayout.setHorizontalGroup(
            A5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A5PainelLayout.setVerticalGroup(
            A5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A6PainelLayout = new javax.swing.GroupLayout(A6Painel);
        A6Painel.setLayout(A6PainelLayout);
        A6PainelLayout.setHorizontalGroup(
            A6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A6PainelLayout.setVerticalGroup(
            A6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A7PainelLayout = new javax.swing.GroupLayout(A7Painel);
        A7Painel.setLayout(A7PainelLayout);
        A7PainelLayout.setHorizontalGroup(
            A7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A7PainelLayout.setVerticalGroup(
            A7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A8PainelLayout = new javax.swing.GroupLayout(A8Painel);
        A8Painel.setLayout(A8PainelLayout);
        A8PainelLayout.setHorizontalGroup(
            A8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        A8PainelLayout.setVerticalGroup(
            A8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A9PainelLayout = new javax.swing.GroupLayout(A9Painel);
        A9Painel.setLayout(A9PainelLayout);
        A9PainelLayout.setHorizontalGroup(
            A9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        A9PainelLayout.setVerticalGroup(
            A9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E1PainelLayout = new javax.swing.GroupLayout(E1Painel);
        E1Painel.setLayout(E1PainelLayout);
        E1PainelLayout.setHorizontalGroup(
            E1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E1PainelLayout.setVerticalGroup(
            E1PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A10PainelLayout = new javax.swing.GroupLayout(A10Painel);
        A10Painel.setLayout(A10PainelLayout);
        A10PainelLayout.setHorizontalGroup(
            A10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        A10PainelLayout.setVerticalGroup(
            A10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C2PainelLayout = new javax.swing.GroupLayout(C2Painel);
        C2Painel.setLayout(C2PainelLayout);
        C2PainelLayout.setHorizontalGroup(
            C2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C2PainelLayout.setVerticalGroup(
            C2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B4PainelLayout = new javax.swing.GroupLayout(B4Painel);
        B4Painel.setLayout(B4PainelLayout);
        B4PainelLayout.setHorizontalGroup(
            B4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        B4PainelLayout.setVerticalGroup(
            B4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B5PainelLayout = new javax.swing.GroupLayout(B5Painel);
        B5Painel.setLayout(B5PainelLayout);
        B5PainelLayout.setHorizontalGroup(
            B5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        B5PainelLayout.setVerticalGroup(
            B5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B6PainelLayout = new javax.swing.GroupLayout(B6Painel);
        B6Painel.setLayout(B6PainelLayout);
        B6PainelLayout.setHorizontalGroup(
            B6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        B6PainelLayout.setVerticalGroup(
            B6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B7PainelLayout = new javax.swing.GroupLayout(B7Painel);
        B7Painel.setLayout(B7PainelLayout);
        B7PainelLayout.setHorizontalGroup(
            B7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        B7PainelLayout.setVerticalGroup(
            B7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B8PainelLayout = new javax.swing.GroupLayout(B8Painel);
        B8Painel.setLayout(B8PainelLayout);
        B8PainelLayout.setHorizontalGroup(
            B8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        B8PainelLayout.setVerticalGroup(
            B8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B10PainelLayout = new javax.swing.GroupLayout(B10Painel);
        B10Painel.setLayout(B10PainelLayout);
        B10PainelLayout.setHorizontalGroup(
            B10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        B10PainelLayout.setVerticalGroup(
            B10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B9PainelLayout = new javax.swing.GroupLayout(B9Painel);
        B9Painel.setLayout(B9PainelLayout);
        B9PainelLayout.setHorizontalGroup(
            B9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        B9PainelLayout.setVerticalGroup(
            B9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C3PainelLayout = new javax.swing.GroupLayout(C3Painel);
        C3Painel.setLayout(C3PainelLayout);
        C3PainelLayout.setHorizontalGroup(
            C3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        C3PainelLayout.setVerticalGroup(
            C3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C4PainelLayout = new javax.swing.GroupLayout(C4Painel);
        C4Painel.setLayout(C4PainelLayout);
        C4PainelLayout.setHorizontalGroup(
            C4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C4PainelLayout.setVerticalGroup(
            C4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C5PainelLayout = new javax.swing.GroupLayout(C5Painel);
        C5Painel.setLayout(C5PainelLayout);
        C5PainelLayout.setHorizontalGroup(
            C5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C5PainelLayout.setVerticalGroup(
            C5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C6PainelLayout = new javax.swing.GroupLayout(C6Painel);
        C6Painel.setLayout(C6PainelLayout);
        C6PainelLayout.setHorizontalGroup(
            C6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C6PainelLayout.setVerticalGroup(
            C6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C7PainelLayout = new javax.swing.GroupLayout(C7Painel);
        C7Painel.setLayout(C7PainelLayout);
        C7PainelLayout.setHorizontalGroup(
            C7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C7PainelLayout.setVerticalGroup(
            C7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C8PainelLayout = new javax.swing.GroupLayout(C8Painel);
        C8Painel.setLayout(C8PainelLayout);
        C8PainelLayout.setHorizontalGroup(
            C8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        C8PainelLayout.setVerticalGroup(
            C8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C10PainelLayout = new javax.swing.GroupLayout(C10Painel);
        C10Painel.setLayout(C10PainelLayout);
        C10PainelLayout.setHorizontalGroup(
            C10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        C10PainelLayout.setVerticalGroup(
            C10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D2PainelLayout = new javax.swing.GroupLayout(D2Painel);
        D2Painel.setLayout(D2PainelLayout);
        D2PainelLayout.setHorizontalGroup(
            D2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        D2PainelLayout.setVerticalGroup(
            D2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E2PainelLayout = new javax.swing.GroupLayout(E2Painel);
        E2Painel.setLayout(E2PainelLayout);
        E2PainelLayout.setHorizontalGroup(
            E2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        E2PainelLayout.setVerticalGroup(
            E2PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B3PainelLayout = new javax.swing.GroupLayout(B3Painel);
        B3Painel.setLayout(B3PainelLayout);
        B3PainelLayout.setHorizontalGroup(
            B3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        B3PainelLayout.setVerticalGroup(
            B3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C9PainelLayout = new javax.swing.GroupLayout(C9Painel);
        C9Painel.setLayout(C9PainelLayout);
        C9PainelLayout.setHorizontalGroup(
            C9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        C9PainelLayout.setVerticalGroup(
            C9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D3PainelLayout = new javax.swing.GroupLayout(D3Painel);
        D3Painel.setLayout(D3PainelLayout);
        D3PainelLayout.setHorizontalGroup(
            D3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D3PainelLayout.setVerticalGroup(
            D3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D4PainelLayout = new javax.swing.GroupLayout(D4Painel);
        D4Painel.setLayout(D4PainelLayout);
        D4PainelLayout.setHorizontalGroup(
            D4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D4PainelLayout.setVerticalGroup(
            D4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D5PainelLayout = new javax.swing.GroupLayout(D5Painel);
        D5Painel.setLayout(D5PainelLayout);
        D5PainelLayout.setHorizontalGroup(
            D5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D5PainelLayout.setVerticalGroup(
            D5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D6PainelLayout = new javax.swing.GroupLayout(D6Painel);
        D6Painel.setLayout(D6PainelLayout);
        D6PainelLayout.setHorizontalGroup(
            D6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D6PainelLayout.setVerticalGroup(
            D6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D7PainelLayout = new javax.swing.GroupLayout(D7Painel);
        D7Painel.setLayout(D7PainelLayout);
        D7PainelLayout.setHorizontalGroup(
            D7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D7PainelLayout.setVerticalGroup(
            D7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D8PainelLayout = new javax.swing.GroupLayout(D8Painel);
        D8Painel.setLayout(D8PainelLayout);
        D8PainelLayout.setHorizontalGroup(
            D8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D8PainelLayout.setVerticalGroup(
            D8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D9PainelLayout = new javax.swing.GroupLayout(D9Painel);
        D9Painel.setLayout(D9PainelLayout);
        D9PainelLayout.setHorizontalGroup(
            D9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D9PainelLayout.setVerticalGroup(
            D9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D10PainelLayout = new javax.swing.GroupLayout(D10Painel);
        D10Painel.setLayout(D10PainelLayout);
        D10PainelLayout.setHorizontalGroup(
            D10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        D10PainelLayout.setVerticalGroup(
            D10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E3PainelLayout = new javax.swing.GroupLayout(E3Painel);
        E3Painel.setLayout(E3PainelLayout);
        E3PainelLayout.setHorizontalGroup(
            E3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E3PainelLayout.setVerticalGroup(
            E3PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E4PainelLayout = new javax.swing.GroupLayout(E4Painel);
        E4Painel.setLayout(E4PainelLayout);
        E4PainelLayout.setHorizontalGroup(
            E4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E4PainelLayout.setVerticalGroup(
            E4PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E5PainelLayout = new javax.swing.GroupLayout(E5Painel);
        E5Painel.setLayout(E5PainelLayout);
        E5PainelLayout.setHorizontalGroup(
            E5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E5PainelLayout.setVerticalGroup(
            E5PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E6PainelLayout = new javax.swing.GroupLayout(E6Painel);
        E6Painel.setLayout(E6PainelLayout);
        E6PainelLayout.setHorizontalGroup(
            E6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E6PainelLayout.setVerticalGroup(
            E6PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E7PainelLayout = new javax.swing.GroupLayout(E7Painel);
        E7Painel.setLayout(E7PainelLayout);
        E7PainelLayout.setHorizontalGroup(
            E7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E7PainelLayout.setVerticalGroup(
            E7PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E8PainelLayout = new javax.swing.GroupLayout(E8Painel);
        E8Painel.setLayout(E8PainelLayout);
        E8PainelLayout.setHorizontalGroup(
            E8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E8PainelLayout.setVerticalGroup(
            E8PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E9PainelLayout = new javax.swing.GroupLayout(E9Painel);
        E9Painel.setLayout(E9PainelLayout);
        E9PainelLayout.setHorizontalGroup(
            E9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E9PainelLayout.setVerticalGroup(
            E9PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E10PainelLayout = new javax.swing.GroupLayout(E10Painel);
        E10Painel.setLayout(E10PainelLayout);
        E10PainelLayout.setHorizontalGroup(
            E10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        E10PainelLayout.setVerticalGroup(
            E10PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(A1Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(A2Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(A3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(A4Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B4Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C4Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(A5Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B5Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C5Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(A6Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B6Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C6Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(A7Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C7Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(A8Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B8Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C8Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(B9Painel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A9Painel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(D1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(C1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(E1Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(B2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(D2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(E2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(B3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(168, 168, 168)
                                .addComponent(B7Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(C3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(286, 286, 286)
                                .addComponent(C9Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(D3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D4Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D5Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D6Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D7Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D8Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D9Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(E3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(E4Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(E5Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(E6Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(E7Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(E8Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(E9Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(A10Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B10Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(C10Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D10Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E10Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(A10Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B10Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C10Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(A9Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A8Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A7Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A6Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A5Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A4Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A3Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B4Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B5Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B6Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B7Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B8Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(B9Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(B3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(C2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C3Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C4Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C5Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C6Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C7Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C8Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C9Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(D2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(D1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(D3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D5Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D6Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D7Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D8Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D9Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D10Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(E10Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E9Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E8Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E7Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E6Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E5Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E4Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(E1Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E2Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E3Painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel56.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout IAPainelA1Layout = new javax.swing.GroupLayout(IAPainelA1);
        IAPainelA1.setLayout(IAPainelA1Layout);
        IAPainelA1Layout.setHorizontalGroup(
            IAPainelA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA1Layout.setVerticalGroup(
            IAPainelA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB1Layout = new javax.swing.GroupLayout(IAPainelB1);
        IAPainelB1.setLayout(IAPainelB1Layout);
        IAPainelB1Layout.setHorizontalGroup(
            IAPainelB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelB1Layout.setVerticalGroup(
            IAPainelB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC1Layout = new javax.swing.GroupLayout(IAPainelC1);
        IAPainelC1.setLayout(IAPainelC1Layout);
        IAPainelC1Layout.setHorizontalGroup(
            IAPainelC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC1Layout.setVerticalGroup(
            IAPainelC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD1Layout = new javax.swing.GroupLayout(IAPainelD1);
        IAPainelD1.setLayout(IAPainelD1Layout);
        IAPainelD1Layout.setHorizontalGroup(
            IAPainelD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD1Layout.setVerticalGroup(
            IAPainelD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB2Layout = new javax.swing.GroupLayout(IAPainelB2);
        IAPainelB2.setLayout(IAPainelB2Layout);
        IAPainelB2Layout.setHorizontalGroup(
            IAPainelB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelB2Layout.setVerticalGroup(
            IAPainelB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA2Layout = new javax.swing.GroupLayout(IAPainelA2);
        IAPainelA2.setLayout(IAPainelA2Layout);
        IAPainelA2Layout.setHorizontalGroup(
            IAPainelA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA2Layout.setVerticalGroup(
            IAPainelA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA3Layout = new javax.swing.GroupLayout(IAPainelA3);
        IAPainelA3.setLayout(IAPainelA3Layout);
        IAPainelA3Layout.setHorizontalGroup(
            IAPainelA3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA3Layout.setVerticalGroup(
            IAPainelA3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA4Layout = new javax.swing.GroupLayout(IAPainelA4);
        IAPainelA4.setLayout(IAPainelA4Layout);
        IAPainelA4Layout.setHorizontalGroup(
            IAPainelA4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA4Layout.setVerticalGroup(
            IAPainelA4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA5Layout = new javax.swing.GroupLayout(IAPainelA5);
        IAPainelA5.setLayout(IAPainelA5Layout);
        IAPainelA5Layout.setHorizontalGroup(
            IAPainelA5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA5Layout.setVerticalGroup(
            IAPainelA5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA6Layout = new javax.swing.GroupLayout(IAPainelA6);
        IAPainelA6.setLayout(IAPainelA6Layout);
        IAPainelA6Layout.setHorizontalGroup(
            IAPainelA6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA6Layout.setVerticalGroup(
            IAPainelA6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA7Layout = new javax.swing.GroupLayout(IAPainelA7);
        IAPainelA7.setLayout(IAPainelA7Layout);
        IAPainelA7Layout.setHorizontalGroup(
            IAPainelA7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA7Layout.setVerticalGroup(
            IAPainelA7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA8Layout = new javax.swing.GroupLayout(IAPainelA8);
        IAPainelA8.setLayout(IAPainelA8Layout);
        IAPainelA8Layout.setHorizontalGroup(
            IAPainelA8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelA8Layout.setVerticalGroup(
            IAPainelA8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA9Layout = new javax.swing.GroupLayout(IAPainelA9);
        IAPainelA9.setLayout(IAPainelA9Layout);
        IAPainelA9Layout.setHorizontalGroup(
            IAPainelA9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelA9Layout.setVerticalGroup(
            IAPainelA9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE1Layout = new javax.swing.GroupLayout(IAPainelE1);
        IAPainelE1.setLayout(IAPainelE1Layout);
        IAPainelE1Layout.setHorizontalGroup(
            IAPainelE1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE1Layout.setVerticalGroup(
            IAPainelE1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelA10Layout = new javax.swing.GroupLayout(IAPainelA10);
        IAPainelA10.setLayout(IAPainelA10Layout);
        IAPainelA10Layout.setHorizontalGroup(
            IAPainelA10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelA10Layout.setVerticalGroup(
            IAPainelA10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC2Layout = new javax.swing.GroupLayout(IAPainelC2);
        IAPainelC2.setLayout(IAPainelC2Layout);
        IAPainelC2Layout.setHorizontalGroup(
            IAPainelC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC2Layout.setVerticalGroup(
            IAPainelC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB4Layout = new javax.swing.GroupLayout(IAPainelB4);
        IAPainelB4.setLayout(IAPainelB4Layout);
        IAPainelB4Layout.setHorizontalGroup(
            IAPainelB4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelB4Layout.setVerticalGroup(
            IAPainelB4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB5Layout = new javax.swing.GroupLayout(IAPainelB5);
        IAPainelB5.setLayout(IAPainelB5Layout);
        IAPainelB5Layout.setHorizontalGroup(
            IAPainelB5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelB5Layout.setVerticalGroup(
            IAPainelB5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB6Layout = new javax.swing.GroupLayout(IAPainelB6);
        IAPainelB6.setLayout(IAPainelB6Layout);
        IAPainelB6Layout.setHorizontalGroup(
            IAPainelB6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelB6Layout.setVerticalGroup(
            IAPainelB6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB7Layout = new javax.swing.GroupLayout(IAPainelB7);
        IAPainelB7.setLayout(IAPainelB7Layout);
        IAPainelB7Layout.setHorizontalGroup(
            IAPainelB7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelB7Layout.setVerticalGroup(
            IAPainelB7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB8Layout = new javax.swing.GroupLayout(IAPainelB8);
        IAPainelB8.setLayout(IAPainelB8Layout);
        IAPainelB8Layout.setHorizontalGroup(
            IAPainelB8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelB8Layout.setVerticalGroup(
            IAPainelB8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB10Layout = new javax.swing.GroupLayout(IAPainelB10);
        IAPainelB10.setLayout(IAPainelB10Layout);
        IAPainelB10Layout.setHorizontalGroup(
            IAPainelB10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelB10Layout.setVerticalGroup(
            IAPainelB10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB9Layout = new javax.swing.GroupLayout(IAPainelB9);
        IAPainelB9.setLayout(IAPainelB9Layout);
        IAPainelB9Layout.setHorizontalGroup(
            IAPainelB9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelB9Layout.setVerticalGroup(
            IAPainelB9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC3Layout = new javax.swing.GroupLayout(IAPainelC3);
        IAPainelC3.setLayout(IAPainelC3Layout);
        IAPainelC3Layout.setHorizontalGroup(
            IAPainelC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelC3Layout.setVerticalGroup(
            IAPainelC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC4Layout = new javax.swing.GroupLayout(IAPainelC4);
        IAPainelC4.setLayout(IAPainelC4Layout);
        IAPainelC4Layout.setHorizontalGroup(
            IAPainelC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC4Layout.setVerticalGroup(
            IAPainelC4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC5Layout = new javax.swing.GroupLayout(IAPainelC5);
        IAPainelC5.setLayout(IAPainelC5Layout);
        IAPainelC5Layout.setHorizontalGroup(
            IAPainelC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC5Layout.setVerticalGroup(
            IAPainelC5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC6Layout = new javax.swing.GroupLayout(IAPainelC6);
        IAPainelC6.setLayout(IAPainelC6Layout);
        IAPainelC6Layout.setHorizontalGroup(
            IAPainelC6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC6Layout.setVerticalGroup(
            IAPainelC6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC7Layout = new javax.swing.GroupLayout(IAPainelC7);
        IAPainelC7.setLayout(IAPainelC7Layout);
        IAPainelC7Layout.setHorizontalGroup(
            IAPainelC7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC7Layout.setVerticalGroup(
            IAPainelC7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC8Layout = new javax.swing.GroupLayout(IAPainelC8);
        IAPainelC8.setLayout(IAPainelC8Layout);
        IAPainelC8Layout.setHorizontalGroup(
            IAPainelC8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelC8Layout.setVerticalGroup(
            IAPainelC8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC10Layout = new javax.swing.GroupLayout(IAPainelC10);
        IAPainelC10.setLayout(IAPainelC10Layout);
        IAPainelC10Layout.setHorizontalGroup(
            IAPainelC10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelC10Layout.setVerticalGroup(
            IAPainelC10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD2Layout = new javax.swing.GroupLayout(IAPainelD2);
        IAPainelD2.setLayout(IAPainelD2Layout);
        IAPainelD2Layout.setHorizontalGroup(
            IAPainelD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelD2Layout.setVerticalGroup(
            IAPainelD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE2Layout = new javax.swing.GroupLayout(IAPainelE2);
        IAPainelE2.setLayout(IAPainelE2Layout);
        IAPainelE2Layout.setHorizontalGroup(
            IAPainelE2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IAPainelE2Layout.setVerticalGroup(
            IAPainelE2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelB3Layout = new javax.swing.GroupLayout(IAPainelB3);
        IAPainelB3.setLayout(IAPainelB3Layout);
        IAPainelB3Layout.setHorizontalGroup(
            IAPainelB3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelB3Layout.setVerticalGroup(
            IAPainelB3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelC9Layout = new javax.swing.GroupLayout(IAPainelC9);
        IAPainelC9.setLayout(IAPainelC9Layout);
        IAPainelC9Layout.setHorizontalGroup(
            IAPainelC9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelC9Layout.setVerticalGroup(
            IAPainelC9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD3Layout = new javax.swing.GroupLayout(IAPainelD3);
        IAPainelD3.setLayout(IAPainelD3Layout);
        IAPainelD3Layout.setHorizontalGroup(
            IAPainelD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD3Layout.setVerticalGroup(
            IAPainelD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD4Layout = new javax.swing.GroupLayout(IAPainelD4);
        IAPainelD4.setLayout(IAPainelD4Layout);
        IAPainelD4Layout.setHorizontalGroup(
            IAPainelD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD4Layout.setVerticalGroup(
            IAPainelD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD5Layout = new javax.swing.GroupLayout(IAPainelD5);
        IAPainelD5.setLayout(IAPainelD5Layout);
        IAPainelD5Layout.setHorizontalGroup(
            IAPainelD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD5Layout.setVerticalGroup(
            IAPainelD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD6Layout = new javax.swing.GroupLayout(IAPainelD6);
        IAPainelD6.setLayout(IAPainelD6Layout);
        IAPainelD6Layout.setHorizontalGroup(
            IAPainelD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD6Layout.setVerticalGroup(
            IAPainelD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD7Layout = new javax.swing.GroupLayout(IAPainelD7);
        IAPainelD7.setLayout(IAPainelD7Layout);
        IAPainelD7Layout.setHorizontalGroup(
            IAPainelD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD7Layout.setVerticalGroup(
            IAPainelD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD8Layout = new javax.swing.GroupLayout(IAPainelD8);
        IAPainelD8.setLayout(IAPainelD8Layout);
        IAPainelD8Layout.setHorizontalGroup(
            IAPainelD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD8Layout.setVerticalGroup(
            IAPainelD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD9Layout = new javax.swing.GroupLayout(IAPainelD9);
        IAPainelD9.setLayout(IAPainelD9Layout);
        IAPainelD9Layout.setHorizontalGroup(
            IAPainelD9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD9Layout.setVerticalGroup(
            IAPainelD9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelD10Layout = new javax.swing.GroupLayout(IAPainelD10);
        IAPainelD10.setLayout(IAPainelD10Layout);
        IAPainelD10Layout.setHorizontalGroup(
            IAPainelD10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelD10Layout.setVerticalGroup(
            IAPainelD10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE3Layout = new javax.swing.GroupLayout(IAPainelE3);
        IAPainelE3.setLayout(IAPainelE3Layout);
        IAPainelE3Layout.setHorizontalGroup(
            IAPainelE3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE3Layout.setVerticalGroup(
            IAPainelE3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE4Layout = new javax.swing.GroupLayout(IAPainelE4);
        IAPainelE4.setLayout(IAPainelE4Layout);
        IAPainelE4Layout.setHorizontalGroup(
            IAPainelE4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE4Layout.setVerticalGroup(
            IAPainelE4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE5Layout = new javax.swing.GroupLayout(IAPainelE5);
        IAPainelE5.setLayout(IAPainelE5Layout);
        IAPainelE5Layout.setHorizontalGroup(
            IAPainelE5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE5Layout.setVerticalGroup(
            IAPainelE5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE6Layout = new javax.swing.GroupLayout(IAPainelE6);
        IAPainelE6.setLayout(IAPainelE6Layout);
        IAPainelE6Layout.setHorizontalGroup(
            IAPainelE6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE6Layout.setVerticalGroup(
            IAPainelE6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE7Layout = new javax.swing.GroupLayout(IAPainelE7);
        IAPainelE7.setLayout(IAPainelE7Layout);
        IAPainelE7Layout.setHorizontalGroup(
            IAPainelE7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE7Layout.setVerticalGroup(
            IAPainelE7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE8Layout = new javax.swing.GroupLayout(IAPainelE8);
        IAPainelE8.setLayout(IAPainelE8Layout);
        IAPainelE8Layout.setHorizontalGroup(
            IAPainelE8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE8Layout.setVerticalGroup(
            IAPainelE8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE9Layout = new javax.swing.GroupLayout(IAPainelE9);
        IAPainelE9.setLayout(IAPainelE9Layout);
        IAPainelE9Layout.setHorizontalGroup(
            IAPainelE9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE9Layout.setVerticalGroup(
            IAPainelE9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IAPainelE10Layout = new javax.swing.GroupLayout(IAPainelE10);
        IAPainelE10.setLayout(IAPainelE10Layout);
        IAPainelE10Layout.setHorizontalGroup(
            IAPainelE10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        IAPainelE10Layout.setVerticalGroup(
            IAPainelE10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(IAPainelD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(IAPainelE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelB2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelE2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel56Layout.createSequentialGroup()
                                .addComponent(IAPainelC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(286, 286, 286)
                                .addComponent(IAPainelC9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel56Layout.createSequentialGroup()
                                    .addComponent(IAPainelB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelB4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(IAPainelB7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(112, 112, 112))
                                .addGroup(jPanel56Layout.createSequentialGroup()
                                    .addComponent(IAPainelD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel56Layout.createSequentialGroup()
                                    .addComponent(IAPainelE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelE4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelE5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelE6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelE7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelE8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IAPainelE9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(IAPainelA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IAPainelA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IAPainelA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelA4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelA5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelB5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelA6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelB6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelA7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelA8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelB8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(IAPainelB9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(IAPainelA10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IAPainelB10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IAPainelC10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(IAPainelD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(IAPainelA10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IAPainelB10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IAPainelC10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel56Layout.createSequentialGroup()
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelA9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(IAPainelB4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IAPainelB9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(IAPainelB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IAPainelC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IAPainelC9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(IAPainelD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IAPainelD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(IAPainelD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IAPainelE10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IAPainelE4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(IAPainelE1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IAPainelE2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IAPainelE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("3");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("4");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("5");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("6");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("7");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("8");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("9");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("A");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("10");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("B");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("C");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("D");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("E");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("E");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setText("D");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setText("C");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setText("B");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setText("A");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setText("1");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setText("2");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setText("3");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setText("4");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setText("5");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setText("6");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setText("7");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setText("8");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setText("9");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setText("10");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Acertos Player:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Acertos IA:");

        pontosPlayer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pontosPlayer.setText("0");

        pontosIA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pontosIA.setText("0");

        TextFieldPortaAvioes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        TextFieldFragata.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        TextFieldCruzador.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel36.setText("Porta-aviões (4x) :");

        jLabel37.setText("Fragata (3x) :");

        jLabel38.setText("Cruzador (2x) :");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Posição Inicial :");

        TextFieldSubmarino.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel40.setText("Submarino (1x) :");

        IniciarBTN.setBackground(new java.awt.Color(51, 51, 255));
        IniciarBTN.setForeground(new java.awt.Color(255, 255, 255));
        IniciarBTN.setText("INICIAR");
        IniciarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarBTNActionPerformed(evt);
            }
        });

        HoriPortaAvioes.setText("H");
        HoriPortaAvioes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        VertiPortaAvioes.setText("V");

        HoriFragata.setText("H");

        VertiFragata.setText("V");

        HoriCruzador.setText("H");

        VertiCruzador.setText("V");

        TextFieldDisparo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel42.setText("Disparo em :");

        AtirarBTN.setBackground(new java.awt.Color(51, 51, 255));
        AtirarBTN.setForeground(new java.awt.Color(255, 255, 255));
        AtirarBTN.setText("ATIRAR");
        AtirarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtirarBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Legenda1Layout = new javax.swing.GroupLayout(Legenda1);
        Legenda1.setLayout(Legenda1Layout);
        Legenda1Layout.setHorizontalGroup(
            Legenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        Legenda1Layout.setVerticalGroup(
            Legenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Legenda2Layout = new javax.swing.GroupLayout(Legenda2);
        Legenda2.setLayout(Legenda2Layout);
        Legenda2Layout.setHorizontalGroup(
            Legenda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        Legenda2Layout.setVerticalGroup(
            Legenda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Legenda3Layout = new javax.swing.GroupLayout(Legenda3);
        Legenda3.setLayout(Legenda3Layout);
        Legenda3Layout.setHorizontalGroup(
            Legenda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        Legenda3Layout.setVerticalGroup(
            Legenda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel43.setText("Tropas Posicionadas");

        jLabel44.setText("Tropas Destruídas");

        jLabel45.setText("Disparos Errados");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Vez:");

        LabelVez.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelVez.setText("-");

        ReiniciarBtn.setBackground(new java.awt.Color(51, 51, 255));
        ReiniciarBtn.setForeground(new java.awt.Color(255, 255, 255));
        ReiniciarBtn.setText("REINICIAR");
        ReiniciarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarBtnActionPerformed(evt);
            }
        });

        labelAcao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelAcao.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pontosPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(pontosIA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel6)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel8)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel9)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(IniciarBTN)
                        .addGap(196, 196, 196))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel42)
                            .addComponent(jLabel40)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Legenda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Legenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Legenda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel45))
                            .addComponent(LabelVez)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextFieldDisparo, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(TextFieldPortaAvioes, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TextFieldFragata, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TextFieldCruzador)
                                    .addComponent(TextFieldSubmarino))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(HoriPortaAvioes)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(VertiPortaAvioes))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(HoriFragata)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(VertiFragata))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(HoriCruzador)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(VertiCruzador))
                                    .addComponent(AtirarBTN))))
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelAcao, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ReiniciarBtn))
                        .addGap(33, 33, 33))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel22)
                .addGap(41, 41, 41)
                .addComponent(jLabel23)
                .addGap(42, 42, 42)
                .addComponent(jLabel24)
                .addGap(40, 40, 40)
                .addComponent(jLabel25)
                .addGap(44, 44, 44)
                .addComponent(jLabel26)
                .addGap(41, 41, 41)
                .addComponent(jLabel27)
                .addGap(42, 42, 42)
                .addComponent(jLabel28)
                .addGap(41, 41, 41)
                .addComponent(jLabel29)
                .addGap(43, 43, 43)
                .addComponent(jLabel30)
                .addGap(34, 34, 34)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldPortaAvioes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(HoriPortaAvioes)
                            .addComponent(VertiPortaAvioes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldFragata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37)
                            .addComponent(HoriFragata)
                            .addComponent(VertiFragata))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldCruzador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(HoriCruzador)
                            .addComponent(VertiCruzador))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldSubmarino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IniciarBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(LabelVez))
                        .addGap(18, 18, 18)
                        .addComponent(labelAcao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel10)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(jLabel23))
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel30)
                                .addComponent(jLabel31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TextFieldDisparo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42)
                                    .addComponent(AtirarBTN))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Legenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Legenda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Legenda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ReiniciarBtn))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addGap(23, 23, 23)
                                    .addComponent(jLabel20)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel17)
                                    .addGap(15, 15, 15))))
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(pontosPlayer))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(pontosIA))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //executa quando o usuario clicar no botão "Reiniciar"
    private void ReiniciarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarBtnActionPerformed
        this.partida.reiniciarJogo();
        this.pontosPlayer.setText("0");
        this.pontosIA.setText("0");

        TextFieldPortaAvioes.setText("");
        TextFieldFragata.setText("");
        TextFieldCruzador.setText("");
        TextFieldSubmarino.setText("");
        LabelVez.setText("-");

        TextFieldPortaAvioes.setEnabled(true);
        TextFieldFragata.setEnabled(true);
        TextFieldCruzador.setEnabled(true);
        TextFieldSubmarino.setEnabled(true);
        IniciarBTN.setEnabled(true);
        HoriPortaAvioes.setEnabled(true);
        HoriFragata.setEnabled(true);
        HoriCruzador.setEnabled(true);
        VertiPortaAvioes.setEnabled(true);
        VertiFragata.setEnabled(true);
        VertiCruzador.setEnabled(true);

        ButtonGroup portaAviaosGroup = new ButtonGroup();
        ButtonGroup fragataGroup = new ButtonGroup();
        ButtonGroup CruzadorGroup = new ButtonGroup();

        portaAviaosGroup.add(HoriPortaAvioes);
        portaAviaosGroup.add(VertiPortaAvioes);

        fragataGroup.add(HoriFragata);
        fragataGroup.add(VertiFragata);

        CruzadorGroup.add(HoriCruzador);
        CruzadorGroup.add(VertiCruzador);

        portaAviaosGroup.clearSelection();
        fragataGroup.clearSelection();
        CruzadorGroup.clearSelection();

        TextFieldDisparo.setText("");
        TextFieldDisparo.setEnabled(false);

        AtirarBTN.setEnabled(false);

        controlaAtirarBtn = 0;

        for (int i = 0; i < paineisIA.length; i++) {
            paineisIA[i].setBackground(Color.WHITE);
        }

        for (int i = 0; i < paineisPlayer.length; i++) {
            paineisPlayer[i].setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_ReiniciarBtnActionPerformed

    //executa quando o usuario clicar no botão "Atirar"
    private void AtirarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtirarBTNActionPerformed
        ataquePlayer();
    }//GEN-LAST:event_AtirarBTNActionPerformed

    //executa quando o usuario clicar no botão "Iniciar"
    private void IniciarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarBTNActionPerformed
        if ((HoriCruzador.isSelected() || VertiCruzador.isSelected()) && (HoriFragata.isSelected() || VertiFragata.isSelected()) && (HoriPortaAvioes.isSelected() || VertiPortaAvioes.isSelected())) {
            ModificaQuadrado(this.TextFieldPortaAvioes, 3);
            ModificaQuadrado(this.TextFieldFragata, 2);
            ModificaQuadrado(this.TextFieldCruzador, 1);
            ModificaQuadrado(this.TextFieldSubmarino, 0);
        } else {
            JOptionPane.showMessageDialog(this, "Escolha o botão entre Horizontal e Vertical");
        }

        if (this.controlaAtirarBtn >= 4) {
            this.IniciarBTN.setEnabled(false);
            InformaVez();
            this.AtirarBTN.setEnabled(this.partida.isVez());
            this.TextFieldDisparo.setEnabled(this.partida.isVez());
            PosiscionarTropasIA();

            if (this.partida.isVez() == false) {
                labelAcao.setText("IA está jogando...");
                timer.start();
            }
        }
    }//GEN-LAST:event_IniciarBTNActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel A10Painel;
    private javax.swing.JPanel A1Painel;
    private javax.swing.JPanel A2Painel;
    private javax.swing.JPanel A3Painel;
    private javax.swing.JPanel A4Painel;
    private javax.swing.JPanel A5Painel;
    private javax.swing.JPanel A6Painel;
    private javax.swing.JPanel A7Painel;
    private javax.swing.JPanel A8Painel;
    private javax.swing.JPanel A9Painel;
    private javax.swing.JButton AtirarBTN;
    private javax.swing.JPanel B10Painel;
    private javax.swing.JPanel B1Painel;
    private javax.swing.JPanel B2Painel;
    private javax.swing.JPanel B3Painel;
    private javax.swing.JPanel B4Painel;
    private javax.swing.JPanel B5Painel;
    private javax.swing.JPanel B6Painel;
    private javax.swing.JPanel B7Painel;
    private javax.swing.JPanel B8Painel;
    private javax.swing.JPanel B9Painel;
    private javax.swing.JPanel C10Painel;
    private javax.swing.JPanel C1Painel;
    private javax.swing.JPanel C2Painel;
    private javax.swing.JPanel C3Painel;
    private javax.swing.JPanel C4Painel;
    private javax.swing.JPanel C5Painel;
    private javax.swing.JPanel C6Painel;
    private javax.swing.JPanel C7Painel;
    private javax.swing.JPanel C8Painel;
    private javax.swing.JPanel C9Painel;
    private javax.swing.JPanel D10Painel;
    private javax.swing.JPanel D1Painel;
    private javax.swing.JPanel D2Painel;
    private javax.swing.JPanel D3Painel;
    private javax.swing.JPanel D4Painel;
    private javax.swing.JPanel D5Painel;
    private javax.swing.JPanel D6Painel;
    private javax.swing.JPanel D7Painel;
    private javax.swing.JPanel D8Painel;
    private javax.swing.JPanel D9Painel;
    private javax.swing.JPanel E10Painel;
    private javax.swing.JPanel E1Painel;
    private javax.swing.JPanel E2Painel;
    private javax.swing.JPanel E3Painel;
    private javax.swing.JPanel E4Painel;
    private javax.swing.JPanel E5Painel;
    private javax.swing.JPanel E6Painel;
    private javax.swing.JPanel E7Painel;
    private javax.swing.JPanel E8Painel;
    private javax.swing.JPanel E9Painel;
    private javax.swing.JRadioButton HoriCruzador;
    private javax.swing.JRadioButton HoriFragata;
    private javax.swing.JRadioButton HoriPortaAvioes;
    private javax.swing.JPanel IAPainelA1;
    private javax.swing.JPanel IAPainelA10;
    private javax.swing.JPanel IAPainelA2;
    private javax.swing.JPanel IAPainelA3;
    private javax.swing.JPanel IAPainelA4;
    private javax.swing.JPanel IAPainelA5;
    private javax.swing.JPanel IAPainelA6;
    private javax.swing.JPanel IAPainelA7;
    private javax.swing.JPanel IAPainelA8;
    private javax.swing.JPanel IAPainelA9;
    private javax.swing.JPanel IAPainelB1;
    private javax.swing.JPanel IAPainelB10;
    private javax.swing.JPanel IAPainelB2;
    private javax.swing.JPanel IAPainelB3;
    private javax.swing.JPanel IAPainelB4;
    private javax.swing.JPanel IAPainelB5;
    private javax.swing.JPanel IAPainelB6;
    private javax.swing.JPanel IAPainelB7;
    private javax.swing.JPanel IAPainelB8;
    private javax.swing.JPanel IAPainelB9;
    private javax.swing.JPanel IAPainelC1;
    private javax.swing.JPanel IAPainelC10;
    private javax.swing.JPanel IAPainelC2;
    private javax.swing.JPanel IAPainelC3;
    private javax.swing.JPanel IAPainelC4;
    private javax.swing.JPanel IAPainelC5;
    private javax.swing.JPanel IAPainelC6;
    private javax.swing.JPanel IAPainelC7;
    private javax.swing.JPanel IAPainelC8;
    private javax.swing.JPanel IAPainelC9;
    private javax.swing.JPanel IAPainelD1;
    private javax.swing.JPanel IAPainelD10;
    private javax.swing.JPanel IAPainelD2;
    private javax.swing.JPanel IAPainelD3;
    private javax.swing.JPanel IAPainelD4;
    private javax.swing.JPanel IAPainelD5;
    private javax.swing.JPanel IAPainelD6;
    private javax.swing.JPanel IAPainelD7;
    private javax.swing.JPanel IAPainelD8;
    private javax.swing.JPanel IAPainelD9;
    private javax.swing.JPanel IAPainelE1;
    private javax.swing.JPanel IAPainelE10;
    private javax.swing.JPanel IAPainelE2;
    private javax.swing.JPanel IAPainelE3;
    private javax.swing.JPanel IAPainelE4;
    private javax.swing.JPanel IAPainelE5;
    private javax.swing.JPanel IAPainelE6;
    private javax.swing.JPanel IAPainelE7;
    private javax.swing.JPanel IAPainelE8;
    private javax.swing.JPanel IAPainelE9;
    private javax.swing.JButton IniciarBTN;
    private javax.swing.JLabel LabelVez;
    private javax.swing.JPanel Legenda1;
    private javax.swing.JPanel Legenda2;
    private javax.swing.JPanel Legenda3;
    private javax.swing.JButton ReiniciarBtn;
    private javax.swing.JTextField TextFieldCruzador;
    private javax.swing.JTextField TextFieldDisparo;
    private javax.swing.JTextField TextFieldFragata;
    private javax.swing.JTextField TextFieldPortaAvioes;
    private javax.swing.JTextField TextFieldSubmarino;
    private javax.swing.JRadioButton VertiCruzador;
    private javax.swing.JRadioButton VertiFragata;
    private javax.swing.JRadioButton VertiPortaAvioes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JLabel labelAcao;
    private javax.swing.JLabel pontosIA;
    private javax.swing.JLabel pontosPlayer;
    // End of variables declaration//GEN-END:variables
}
