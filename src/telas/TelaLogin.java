package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;
import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela responsável pela autenticação de usuários.
 */

public class TelaLogin extends TelaBase {

    private CampoTextoPadrao campoUsuario;
    private JPasswordField campoSenha;

    public TelaLogin() {
        super("SGD - Login");
        
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel cardLogin = new JPanel();

        cardLogin.setLayout(null);
        cardLogin.setBounds(250, 120, 400, 300);

        cardLogin.setBackground(PAINEL);

        JLabel titulo = new JLabel("Sistema de Doações");

        titulo.setBounds(85, 30, 250, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblUsuario = new JLabel("Usuário");

        lblUsuario.setBounds(50, 100, 100, 20);

        lblUsuario.setForeground(TEXTO);

        campoUsuario = new CampoTextoPadrao();

        campoUsuario.setBounds(50, 125, 300, 35);

        JLabel lblSenha = new JLabel("Senha");

        lblSenha.setBounds(50, 170, 100, 20);

        lblSenha.setForeground(TEXTO);

        campoSenha = new JPasswordField();

        campoSenha.setBounds(50, 195, 300, 35);

        campoSenha.setBackground(new Color(60, 60, 75));

        campoSenha.setForeground(Color.WHITE);

        campoSenha.setCaretColor(Color.WHITE);

        campoSenha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        BotaoPadrao btnEntrar = new BotaoPadrao("Entrar");

        btnEntrar.setBounds(125, 250, 150, 35);

        btnEntrar.addActionListener(e -> realizarLogin());

        cardLogin.add(titulo);

        cardLogin.add(lblUsuario);
        cardLogin.add(campoUsuario);

        cardLogin.add(lblSenha);
        cardLogin.add(campoSenha);

        cardLogin.add(btnEntrar);
        
        add(cardLogin);
    }

    private void realizarLogin() {

        String usuarioDigitado = campoUsuario.getText();

        String senhaDigitada = new String(campoSenha.getPassword());

        /*
         * LOGIN ADMIN TEMPORÁRIO   Retirar quando tiver integração com banco de dados
         */

        if(usuarioDigitado.equals("adm")
                && senhaDigitada.equals("123")) {

            Usuario admin = new Usuario(
                    "adm",
                    "123",
                    true
            );

            dispose();

            new TelaMenu(admin).setVisible(true);

            return;
        }

        /*
         * LOGIN NORMAL
         */

        Usuario usuario = Usuario.autenticar(
                usuarioDigitado,
                senhaDigitada
        );

        if(usuario != null) {

            dispose();

            new TelaMenu(usuario).setVisible(true);

        } else {

            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha inválidos!");
        }
    }
}