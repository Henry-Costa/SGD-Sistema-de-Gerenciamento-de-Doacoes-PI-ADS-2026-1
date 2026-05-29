package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Beneficiario;
import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Tela responsável pelo gerenciamento
 * de beneficiários.
 */
public class TelaBeneficiarios extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    private CampoTextoPadrao campoPesquisa;

    public TelaBeneficiarios(Usuario usuario) {

        super("SGD - Beneficiários");

        this.usuario = usuario;

        inicializarComponentes();

        carregarTabela(
                Beneficiario.listarBeneficiarios()
        );
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo =
                new JLabel("Beneficiários");

        titulo.setBounds(30, 20, 300, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        add(titulo);

        /*
         * PESQUISA
         */

        campoPesquisa = new CampoTextoPadrao();

        campoPesquisa.setBounds(
                30,
                80,
                300,
                35
        );

        add(campoPesquisa);

        BotaoPadrao btnPesquisar =
                new BotaoPadrao("Pesquisar");

        btnPesquisar.setBounds(
                350,
                80,
                150,
                35
        );

        btnPesquisar.addActionListener(
                e -> pesquisar()
        );

        add(btnPesquisar);

        /*
         * TABELA
         */

        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("Nome");

        modeloTabela.addColumn("CPF");

        modeloTabela.addColumn("Telefone");

        modeloTabela.addColumn("Família");

        modeloTabela.addColumn("Status");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll =
                new JScrollPane(tabela);

        scroll.setBounds(
                30,
                140,
                820,
                300
        );

        add(scroll);

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao(
                        "Cadastrar Beneficiário"
                );

        btnCadastrar.setBounds(
                30,
                470,
                250,
                40
        );

        btnCadastrar.addActionListener(e -> {

            dispose();

            new TelaCadastroBeneficiario(usuario)
                    .setVisible(true);

        });

        add(btnCadastrar);

        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao("Voltar");

        btnVoltar.setBounds(
                300,
                470,
                180,
                40
        );

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaMenu(usuario)
                    .setVisible(true);

        });

        add(btnVoltar);
    }

    /**
     * Carrega os dados na tabela.
     *
     * @param beneficiarios Lista carregada.
     */
    private void carregarTabela(
            ArrayList<Beneficiario> beneficiarios
    ) {

        modeloTabela.setRowCount(0);

        for(Beneficiario b : beneficiarios) {

            modeloTabela.addRow(new Object[] {

                    b.getNome(),
                    b.getCpf(),
                    b.getTelefone(),
                    b.getMembrosFamilia(),
                    b.isAtivo()
                            ? "Ativo"
                            : "Inativo"

            });
        }
    }

    /**
     * Pesquisa beneficiários por nome.
     */
    private void pesquisar() {

        carregarTabela(

                Beneficiario.pesquisarPorNome(
                        campoPesquisa.getText()
                )
        );
    }
}