package telas;

import javax.swing.*;
import java.awt.*;

/**
 * Classe abstrata base para todas as telas do sistema.
 * 
 * Responsável por definir:
 * - tamanho padrão
 * - cores
 * - configurações visuais
 * 
 * @author Henry
 * @version 1.0
 */
public abstract class TelaBase extends JFrame {

    private static final long serialVersionUID = 1L;

    protected final Color FUNDO = new Color(35, 35, 45);

    protected final Color PAINEL = new Color(45, 45, 60);

    protected final Color BOTAO = new Color(70, 130, 180);

    protected final Color TEXTO = Color.WHITE;

    public TelaBase(String titulo) {

        setTitle(titulo);

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        getContentPane().setBackground(FUNDO);

        setLayout(null);
    }

    protected abstract void inicializarComponentes();
}