package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Beneficiario;
import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaDetalhesBeneficiario
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private Beneficiario beneficiario;

    private CampoTextoPadrao campoNome;

    private CampoTextoPadrao campoCpf;

    private CampoTextoPadrao campoTelefone;

    private CampoTextoPadrao campoEndereco;

    private CampoTextoPadrao campoFamilia;

    private JCheckBox checkAtivo;

    public TelaDetalhesBeneficiario(
            Usuario usuario,
            Beneficiario beneficiario
    ) {

        super("Detalhes do Beneficiário");

        this.usuario = usuario;

        this.beneficiario = beneficiario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(PAINEL);

        painel.setBounds(
                220,
                20,
                450,
                560
        );

        add(painel);

        JLabel titulo =
                new JLabel(
                        "Detalhes do Beneficiário"
                );

        titulo.setBounds(
                30,
                20,
                380,
                40
        );

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        painel.add(titulo);

        /*
         * NOME
         */

        adicionarLabel(
                painel,
                "Nome",
                80
        );

        campoNome =
                new CampoTextoPadrao();

        campoNome.setText(
                beneficiario.getNome()
        );

        campoNome.setBounds(
                50,
                105,
                340,
                35
        );

        painel.add(campoNome);

        /*
         * CPF
         */

        adicionarLabel(
                painel,
                "CPF",
                150
        );

        campoCpf =
                new CampoTextoPadrao();

        campoCpf.setText(
                beneficiario.getCpf()
        );

        campoCpf.setEditable(false);

        campoCpf.setBounds(
                50,
                175,
                340,
                35
        );

        painel.add(campoCpf);

        /*
         * TELEFONE
         */

        adicionarLabel(
                painel,
                "Telefone",
                220
        );

        campoTelefone =
                new CampoTextoPadrao();

        campoTelefone.setText(
                beneficiario.getTelefone()
        );

        campoTelefone.setBounds(
                50,
                245,
                340,
                35
        );

        painel.add(campoTelefone);

        /*
         * ENDEREÇO
         */

        adicionarLabel(
                painel,
                "Endereço",
                290
        );

        campoEndereco =
                new CampoTextoPadrao();

        campoEndereco.setText(
                beneficiario.getEndereco()
        );

        campoEndereco.setBounds(
                50,
                315,
                340,
                35
        );

        painel.add(campoEndereco);

        /*
         * MEMBROS FAMÍLIA
         */

        adicionarLabel(
                painel,
                "Membros da Família",
                360
        );

        campoFamilia =
                new CampoTextoPadrao();

        campoFamilia.setText(
                String.valueOf(
                        beneficiario.getMembrosFamilia()
                )
        );

        campoFamilia.setBounds(
                50,
                385,
                340,
                35
        );

        painel.add(campoFamilia);

        /*
         * STATUS
         */

        checkAtivo =
                new JCheckBox(
                        "Beneficiário Ativo"
                );

        checkAtivo.setBounds(
                50,
                430,
                200,
                30
        );

        checkAtivo.setSelected(
                beneficiario.isAtivo()
        );

        checkAtivo.setBackground(
                PAINEL
        );

        checkAtivo.setForeground(
                TEXTO
        );

        painel.add(checkAtivo);

        /*
         * SOMENTE LEITURA
         */

        if(!usuario.isAdministrador()) {

            campoNome.setEditable(false);

            campoTelefone.setEditable(false);

            campoEndereco.setEditable(false);

            campoFamilia.setEditable(false);

            checkAtivo.setEnabled(false);
        }

        /*
         * BOTÃO SALVAR
         */

        if(usuario.isAdministrador()) {

            BotaoPadrao btnSalvar =
                    new BotaoPadrao(
                            "Salvar"
                    );

            btnSalvar.setBounds(
                    50,
                    480,
                    150,
                    40
            );

            btnSalvar.addActionListener(
                    e -> salvar()
            );

            painel.add(btnSalvar);
        }

        /*
         * BOTÃO FECHAR
         */

        BotaoPadrao btnFechar =
                new BotaoPadrao(
                        "Fechar"
                );

        btnFechar.setBounds(
                usuario.isAdministrador()
                        ? 240
                        : 145,
                480,
                150,
                40
        );

        btnFechar.addActionListener(
                e -> dispose()
        );

        painel.add(btnFechar);
    }

    private void adicionarLabel(
            JPanel painel,
            String texto,
            int y
    ) {

        JLabel label =
                new JLabel(texto);

        label.setBounds(
                50,
                y,
                200,
                20
        );

        label.setForeground(TEXTO);

        painel.add(label);
    }

    private void salvar() {

        try {

            beneficiario.setNome(
                    campoNome.getText()
            );

            beneficiario.setTelefone(
                    campoTelefone.getText()
            );

            beneficiario.setEndereco(
                    campoEndereco.getText()
            );

            beneficiario.setMembrosFamilia(

                    Integer.parseInt(
                            campoFamilia.getText()
                    )
            );

            beneficiario.setAtivo(
                    checkAtivo.isSelected()
            );

            Beneficiario.atualizarBeneficiario(
                    beneficiario
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Beneficiário atualizado com sucesso!"
            );
        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}