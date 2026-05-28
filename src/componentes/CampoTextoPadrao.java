package componentes;

import javax.swing.*;
import java.awt.*;

public class CampoTextoPadrao extends JTextField {

    public CampoTextoPadrao() {

        setBackground(new Color(60, 60, 75));
        setForeground(Color.WHITE);

        setCaretColor(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setFont(new Font("Arial", Font.PLAIN, 14));
    }
}