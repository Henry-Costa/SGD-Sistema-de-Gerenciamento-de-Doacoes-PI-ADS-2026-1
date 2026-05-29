package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Doador;
import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela responsável pelo cadastro de doadores.
 * 
 * @author Henry
 * @version 1.0
 */
public class TelaCadastroDoador extends TelaBase {

    private static final long serialVersionUID = 1L;

    private CampoTextoPadrao campoNome;

    private CampoTextoPadrao campoEmail;

    private CampoTextoPadrao campoTelefone;

    private CampoTextoPadrao campoDocumento;
    
    private Usuario usuario;

    public TelaCadastroDoador(Usuario usuario) {

        super("SGD - Cadastro de Doador");

        this.usuario = usuario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(PAINEL);

        painel.setBounds(220, 40, 450, 500);

        add(painel);

        JLabel titulo =
                new JLabel("Cadastrar Doador");

        titulo.setBounds(90, 20, 300, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(new Font("Arial",
                Font.BOLD,
                28));

        painel.add(titulo);

        campoNome = criarCampo(
                painel,
                "Nome",
                90
        );

        campoEmail = criarCampo(
                painel,
                "E-mail",
                160
        );

        campoTelefone = criarCampo(
                painel,
                "Telefone",
                230
        );

        campoDocumento = criarCampo(
                painel,
                "CPF/CNPJ",
                300
        );
        
        /*
         * BOTÂO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao("Cadastrar");

        btnCadastrar.setBounds(140, 360, 170, 40);

        btnCadastrar.addActionListener(e -> cadastrar());

        painel.add(btnCadastrar);
        
        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao("Voltar");

        btnVoltar.setBounds(140, 410, 170, 40);

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaDoadores(usuario)
                    .setVisible(true);

        });

        painel.add(btnVoltar);
    }

    private CampoTextoPadrao criarCampo(
            JPanel painel,
            String texto,
            int y
    ) {

        JLabel label = new JLabel(texto);

        label.setBounds(50, y, 200, 20);

        label.setForeground(TEXTO);

        painel.add(label);

        CampoTextoPadrao campo =
                new CampoTextoPadrao();

        campo.setBounds(50, y + 25, 340, 35);

        painel.add(campo);

        return campo;
    }
    
    

    /**
     * Realiza o cadastro do doador.
     */
    private void cadastrar() {

        try {

            Doador doador = new Doador(

                    campoNome.getText(),

                    campoEmail.getText(),

                    campoTelefone.getText(),

                    campoDocumento.getText()

            );

            Doador.cadastrarDoador(doador);

            JOptionPane.showMessageDialog(this,
                    "Doador cadastrado com sucesso!");

            dispose();

            new TelaCadastroDoador(usuario).setVisible(true);

        }
        catch(IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());
        }
    }
}