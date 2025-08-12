import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math; // Importa a classe Math para usar sqrt()

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;
    private JPanel panel;
    private String[] botoes = {
        "%", "sqrt", "^2", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "C", "="
    };

    private double numero1 = 0, numero2 = 0, resultado = 0;
    private char operacao = ' ';

    public Calculadora() {
        setTitle("Calculadora Java");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Alterado para 5 linhas

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            botao.addActionListener(this);

            if (texto.equals("+") || texto.equals("-") || texto.equals("*") || texto.equals("/") || texto.equals("%") || 
            texto.equals("sqrt") || texto.equals("^2")) {
                botao.setBackground(new Color(255, 140, 0));
                botao.setForeground(Color.WHITE);
            } else if (texto.equals("=")) {
                botao.setBackground(new Color(34, 139, 34));
                botao.setForeground(Color.WHITE);
            } else if (texto.equals("C")) {
                botao.setBackground(new Color(178, 34, 34));
                botao.setForeground(Color.WHITE);
            
            } else {
                botao.setBackground(new Color(200, 200, 200));
            }

            panel.add(botao);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
            display.setText(display.getText() + comando);
        } else if (comando.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (comando.equals("C")) {
            display.setText("");
            numero1 = 0;
            numero2 = 0;
            operacao = ' ';
        } else if (comando.equals("sqrt")) {
            if (!display.getText().isEmpty()) {
                numero1 = Double.parseDouble(display.getText());
                resultado = Math.sqrt(numero1);
                exibirResultado(resultado);
            }
        } else if (comando.equals("^2")) {
            if (!display.getText().isEmpty()) {
                numero1 = Double.parseDouble(display.getText());
                resultado = numero1 * numero1;
                exibirResultado(resultado);
            }
        } else if (comando.equals("=")) {
            if (!display.getText().isEmpty() && operacao != ' ') {
                numero2 = Double.parseDouble(display.getText());
                switch (operacao) {
                    case '+':
                        resultado = numero1 + numero2;
                        break;
                    case '-':
                        resultado = numero1 - numero2;
                        break;
                    case '*':
                        resultado = numero1 * numero2;
                        break;
                    case '/':
                        if (numero2 != 0) {
                            resultado = numero1 / numero2;
                        } else {
                            display.setText("Erro");
                            return;
                        }
                        break;
                    case '%':
                        resultado = numero1 * (numero2 / 100);
                        break;
                }
                exibirResultado(resultado);
                numero1 = resultado;
                operacao = ' ';
            }
        } else {
            if (!display.getText().isEmpty()) {
                numero1 = Double.parseDouble(display.getText());
                operacao = comando.charAt(0);
                display.setText("");
            }
        }
    }

    // Método auxiliar para exibir o resultado formatado
    private void exibirResultado(double res) {
        if (res == (int) res) {
            display.setText(String.valueOf((int) res));
        } else {
            display.setText(String.valueOf(res));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora());
    }
}