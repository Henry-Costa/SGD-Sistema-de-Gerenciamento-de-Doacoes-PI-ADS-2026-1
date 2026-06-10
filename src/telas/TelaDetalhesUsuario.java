package telas;

import componentes.BotaoPadrao;

import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaDetalhesUsuario
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioLogado;

    private Usuario usuario;

    private JCheckBox checkAdministrador;

    public TelaDetalhesUsuario(
            Usuario usuarioLogado,
            Usuario usuario
    ) {

        super("Detalhes do Usuário");

        this.usuarioLogado = usuarioLogado;

        this.usuario = usuario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo =
                new JLabel(
                        "Usuário"
                );

        titulo.setBounds(
                30,
                20,
                300,
                40
        );

        titulo.setForeground(
                TEXTO
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        add(titulo);

        /*
         * USERNAME
         */

        JLabel lblUsername =
                new JLabel(
                        "Usuário:"
                );

        lblUsername.setBounds(
                50,
                90,
                120,
                25
        );

        lblUsername.setForeground(
                TEXTO
        );

        add(lblUsername);

        JTextField campoUsername =
                new JTextField(
                        usuario.getUsername()
                );

        campoUsername.setBounds(
                180,
                90,
                250,
                30
        );

        campoUsername.setEditable(
                false
        );

        add(campoUsername);

        /*
         * ADMINISTRADOR
         */

        checkAdministrador =
                new JCheckBox(
                        "Administrador"
                );

        checkAdministrador.setBounds(
                180,
                140,
                200,
                30
        );

        checkAdministrador.setSelected(
                usuario.isAdministrador()
        );

        checkAdministrador.setBackground(
                PAINEL
        );

        checkAdministrador.setForeground(
                TEXTO
        );

        add(checkAdministrador);

        /*
         * BOTÃO SALVAR
         */

        if(usuarioLogado.isAdministrador()) {

            BotaoPadrao btnSalvar =
                    new BotaoPadrao(
                            "Salvar"
                    );

            btnSalvar.setBounds(
                    50,
                    230,
                    180,
                    40
            );

            btnSalvar.addActionListener(
                    e -> salvar()
            );

            add(btnSalvar);

            /*
             * BOTÃO EXCLUIR
             */

            BotaoPadrao btnExcluir =
                    new BotaoPadrao(
                            "Excluir"
                    );

            btnExcluir.setBounds(
                    250,
                    230,
                    180,
                    40
            );

            btnExcluir.addActionListener(
                    e -> excluir()
            );

            add(btnExcluir);
        }

        /*
         * BOTÃO FECHAR
         */

        BotaoPadrao btnFechar =
                new BotaoPadrao(
                        "Fechar"
                );

        btnFechar.setBounds(
                150,
                290,
                180,
                40
        );

        btnFechar.addActionListener(
                e -> dispose()
        );

        add(btnFechar);
    }

    private void salvar() {

        try {

            usuario.setAdministrador(
                    checkAdministrador
                            .isSelected()
            );

            usuario.atualizar();

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário atualizado com sucesso!"
            );

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void excluir() {

        try {

            if(usuario.getId()
                    == usuarioLogado.getId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Você não pode excluir seu próprio usuário."
                );

                return;
            }

            int resposta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Deseja realmente excluir este usuário?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                    );

            if(resposta
                    != JOptionPane.YES_OPTION) {

                return;
            }

            Usuario.excluirUsuario(
                    usuario.getId()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário excluído com sucesso!"
            );

            dispose();

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}