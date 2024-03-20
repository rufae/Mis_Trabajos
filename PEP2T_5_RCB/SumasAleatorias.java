package PEP2T_5_RCB;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SumasAleatorias {
    Integer num_intentos = 0;
    Integer num_aciertos = 0;
    Integer num_fallos = 0;
    public void SumasAleatorias(){
        JFrame marco = new JFrame("Adivina el resultado de la suma");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(620, 200);
        marco.setLayout(new BorderLayout());

        /* PANEL NORTE */

        JPanel panelnorte = new JPanel();
        panelnorte.setBorder(new EmptyBorder(10, 50, 10, 50));

        JLabel num1 = new JLabel("Número 1:");
        JTextField num1_mostrar = new JTextField(10);
        panelnorte.add(num1);
        panelnorte.add(num1_mostrar);

        JLabel num2 = new JLabel("Número 2:");
        JTextField num2_mostrar = new JTextField(10);
        panelnorte.add(num2);
        panelnorte.add(num2_mostrar);

        JLabel resul = new JLabel("Resultado:");
        JTextField resul_mostrar = new JTextField(10);
        panelnorte.add(resul);
        panelnorte.add(resul_mostrar);

        /* PANEL SUR */

        JPanel panelsur = new JPanel();
        panelsur.setBorder(new EmptyBorder(10,50,10,50));

        JLabel intentos = new JLabel("Intentos:    0");
        intentos.setBorder(new EmptyBorder(0, 20, 0, 20));
        panelsur.add(intentos);

        JLabel acertados = new JLabel("Acertados:    0");
        acertados.setBorder(new EmptyBorder(0, 20, 0, 20));
        panelsur.add(acertados);

        JLabel fallados = new JLabel("Fallados:    0");
        fallados.setBorder(new EmptyBorder(0, 20, 0, 20));
        panelsur.add(fallados);

        /* PANEL MEDIO */

        JPanel panelcenter = new JPanel();
        panelcenter.setBorder(new EmptyBorder(10,50,10,50));

        JButton generar = new JButton("Generar");
        generar.addActionListener(e -> {
            int numero1 = (int) (Math.random() * 999 + 1);
            int numero2 = (int) (Math.random() * 999 + 1);
            num1_mostrar.setText(String.valueOf(numero1));
            num2_mostrar.setText(String.valueOf(numero2));
        });
        panelcenter.add(generar);

        Component filler = Box.createHorizontalStrut(20);
        panelcenter.add(filler);

        JButton verificar = new JButton("Verificar");
        verificar.addActionListener(e -> {
            String resultado = JOptionPane.showInputDialog(marco, "Ingrese el resultado de la suma:");
            try {
                Integer resultadoInt = Integer.parseInt(resultado);
                Integer numero1 = Integer.parseInt(num1_mostrar.getText());
                Integer numero2 = Integer.parseInt(num2_mostrar.getText());

                num_intentos++;
                intentos.setText("Intentos:    " + num_intentos);

                if (resultadoInt.equals(numero1 + numero2)){
                    num_aciertos++;
                    acertados.setText("Acertados:    " + num_aciertos);
                    resul_mostrar.setText(String.valueOf(resultadoInt));
                }else {
                    num_fallos++;
                    fallados.setText("Fallados:    " + num_fallos);
                    resul_mostrar.setText(String.valueOf(numero1 + numero2));
                }

            } catch (NumberFormatException ex) {
            }
        });
        panelcenter.add(verificar);

        filler = Box.createHorizontalStrut(20);
        panelcenter.add(filler);

        JButton salir = new JButton("Salir");
        salir.addActionListener(e -> marco.dispose());
        panelcenter.add(salir);

        /* FINAL */

        marco.getContentPane().add(panelnorte, BorderLayout.NORTH);
        marco.getContentPane().add(panelcenter, BorderLayout.CENTER);
        marco.getContentPane().add(panelsur, BorderLayout.SOUTH);

        marco.setVisible(true);

    }
}
