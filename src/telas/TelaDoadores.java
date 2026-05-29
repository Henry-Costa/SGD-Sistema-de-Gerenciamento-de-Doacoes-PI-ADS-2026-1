package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Doador;
import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Tela responsável pelo gerenciamento de doadores.
 * 
 * Permite:
 * - visualizar doadores
 * - pesquisar
 * - cadastrar novos doadores
 * 
 * @author Henry
 * @version 1.0
 */
public class TelaDoadores extends TelaBase {

    private static final long serialVersionUID = 1L;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    private CampoTextoPadrao campoPesquisa;
    
    private Usuario usuario;

    public TelaDoadores(Usuario usuario) {

        super("SGD - Doadores");

        this.usuario = usuario;

        inicializarComponentes();

        carregarTabela(Doador.listarDoadores());
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo = new JLabel("Doadores");

        titulo.setBounds(30, 20, 300, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        add(titulo);

        /*
         * PESQUISA
         */

        campoPesquisa = new CampoTextoPadrao();

        campoPesquisa.setBounds(30, 80, 300, 35);

        add(campoPesquisa);

        BotaoPadrao btnPesquisar =
                new BotaoPadrao("Pesquisar");

        btnPesquisar.setBounds(350, 80, 150, 35);

        btnPesquisar.addActionListener(e -> pesquisar());

        add(btnPesquisar);

        /*
         * TABELA
         */

        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("Nome");

        modeloTabela.addColumn("E-mail");

        modeloTabela.addColumn("Telefone");

        modeloTabela.addColumn("Documento");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll =
                new JScrollPane(tabela);

        scroll.setBounds(30, 140, 820, 300);

        add(scroll);

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao("Cadastrar Doador");

        btnCadastrar.setBounds(30, 470, 220, 40);

        btnCadastrar.addActionListener(e -> {

            dispose();

            new TelaCadastroDoador(usuario).setVisible(true);

        });

        add(btnCadastrar);

        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao("Voltar");

        btnVoltar.setBounds(270, 470, 180, 40);

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaMenu(usuario).setVisible(true);

        });

        add(btnVoltar);
    }

    /**
     * Carrega os dados na tabela.
     *
     * @param doadores Lista de doadores.
     */
    private void carregarTabela(ArrayList<Doador> doadores) {

        modeloTabela.setRowCount(0);

        for(Doador doador : doadores) {

            modeloTabela.addRow(new Object[] {

                    doador.getNome(),
                    doador.getEmail(),
                    doador.getTelefone(),
                    doador.getDocumento()

            });
        }
    }

    /**
     * Realiza pesquisa por nome.
     */
    private void pesquisar() {

        String nome = campoPesquisa.getText();

        carregarTabela(
                Doador.pesquisarPorNome(nome)
        );
    }
}