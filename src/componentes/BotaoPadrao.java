package componentes;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de botão genérico com o estilo gráfico do sistema já aplicado
 */
public class BotaoPadrao extends JButton {

    public BotaoPadrao(String texto) {

        super(texto);

        setFocusPainted(false);
        setBackground(new Color(70, 130, 180));
        setForeground(Color.WHITE);

        setFont(new Font("Arial", Font.BOLD, 14));

        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}