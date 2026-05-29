package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Beneficiario;
import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela responsável pelo cadastro
 * de beneficiários.
 */
public class TelaCadastroBeneficiario
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private CampoTextoPadrao campoNome;

    private CampoTextoPadrao campoCpf;

    private CampoTextoPadrao campoTelefone;

    private CampoTextoPadrao campoEndereco;

    private CampoTextoPadrao campoFamilia;

    public TelaCadastroBeneficiario(
            Usuario usuario
    ) {

        super("SGD - Cadastro Beneficiário");

        this.usuario = usuario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(PAINEL);

        painel.setBounds(220, 20, 450, 540);

        add(painel);

        JLabel titulo = new JLabel(
                "Cadastrar Beneficiário"
        );

        titulo.setBounds(60, 20, 350, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28)
        );

        painel.add(titulo);

        campoNome =
                criarCampo(painel, "Nome", 90);

        campoCpf =
                criarCampo(painel, "CPF", 160);

        campoTelefone =
                criarCampo(painel, "Telefone", 230);

        campoEndereco =
                criarCampo(painel, "Endereço", 300);

        campoFamilia =
                criarCampo(
                        painel,
                        "Membros da Família",
                        370
                );

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao("Cadastrar");

        btnCadastrar.setBounds(
                130,
                440,
                180,
                40
        );

        btnCadastrar.addActionListener(
                e -> cadastrar()
        );

        painel.add(btnCadastrar);

        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao("Voltar");

        btnVoltar.setBounds(
                130,
                490,
                180,
                40
        );

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaBeneficiarios(usuario).setVisible(true);

        });

        painel.add(btnVoltar);
    }

    private CampoTextoPadrao criarCampo(
            JPanel painel,
            String texto,
            int y
    ) {

        JLabel label = new JLabel(texto);

        label.setBounds(50, y, 250, 20);

        label.setForeground(TEXTO);

        painel.add(label);

        CampoTextoPadrao campo =
                new CampoTextoPadrao();

        campo.setBounds(50, y + 25, 340, 35);

        painel.add(campo);

        return campo;
    }

    /**
     * Realiza o cadastro do beneficiário.
     */
    private void cadastrar() {

        try {

            Beneficiario beneficiario =
                    new Beneficiario(

                            campoNome.getText(),

                            campoCpf.getText(),

                            campoTelefone.getText(),

                            campoEndereco.getText(),

                            Integer.parseInt(
                                    campoFamilia.getText()
                            )
                    );

            Beneficiario.cadastrarBeneficiario(
                    beneficiario
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Beneficiário cadastrado!"
            );

            dispose();

            new TelaBeneficiarios(usuario).setVisible(true);

        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}