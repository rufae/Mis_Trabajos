package PEP3T_4_RCB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class Swing {
    private JFrame marco;
    private JTextField codigo_matr;
    private JTextField nombre_asignatura;
    private JTextField notauno;
    private JTextField notados;
    private JPanel Jtexto;
    private JLabel mensajeLabel;

    public Swing() {

    }

    public void inicio() {
        Acciones acciones = new Acciones(this);

        marco = new JFrame("Programa FCT Centro Educativo");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(400, 300);
        marco.setLocationRelativeTo(null);

        JPanel inicio = new JPanel();
        inicio.setLayout(new BorderLayout());

        JPanel panelnorte = new JPanel();
        JPanel panelmedio = new JPanel(new GridLayout(4, 1));
        JPanel panelsur = new JPanel();
        panelsur.setLayout(new BoxLayout(panelsur, BoxLayout.Y_AXIS));

        JPanel matricula = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel nombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel notas = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel Jbotones = new JPanel();
        Jtexto = new JPanel();

        JLabel titulo = new JLabel("GESTIÓN DE LA TABLA NOTAS");
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel cod_matricula = new JLabel("Código Matrícula: ");
        codigo_matr = new JTextField("123", 8);
        codigo_matr.setForeground(Color.GRAY);
        textfields(codigo_matr, "123");

        JLabel nombre_asig = new JLabel("Nombre Asignatura: ");
        nombre_asignatura = new JTextField("Escriba el nombre de la asignatura", 25);
        nombre_asignatura.setForeground(Color.GRAY);
        textfields(nombre_asignatura, "Escriba el nombre de la asignatura");

        JLabel nota1 = new JLabel("Nota 1: ");
        notauno = new JTextField("X,XX", 8);
        notauno.setForeground(Color.GRAY);
        textfields(notauno, "X,XX");

        JLabel nota2 = new JLabel("Nota 2: ");
        notados = new JTextField("X,XX", 8);
        notados.setForeground(Color.GRAY);
        textfields(notados, "X,XX");

        JButton Insertar = new JButton("Insertar");
        JButton Modificar = new JButton("Modificar");
        JButton Borrar = new JButton("Borrar");
        JButton Consultar = new JButton("Consultar");

        panelnorte.add(titulo);

        matricula.add(cod_matricula);
        matricula.add(codigo_matr);
        matricula.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        nombre.add(nombre_asig);
        nombre.add(nombre_asignatura);
        nombre.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        notas.add(nota1);
        notas.add(notauno);
        notas.add(nota2);
        notas.add(notados);
        notas.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        panelmedio.add(matricula);
        panelmedio.add(nombre);
        panelmedio.add(notas);

        Jbotones.add(Insertar);
        Jbotones.add(Modificar);
        Jbotones.add(Borrar);
        Jbotones.add(Consultar);
        Jbotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        mensajeLabel = new JLabel(" ");
        Jtexto.add(mensajeLabel);

        panelsur.add(Jbotones);
        panelsur.add(Jtexto);

        inicio.add(panelnorte, BorderLayout.NORTH);
        inicio.add(panelmedio, BorderLayout.CENTER);
        inicio.add(panelsur, BorderLayout.SOUTH);

        marco.add(inicio);

        codigo_matr.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                // Verifica si el JTextField de código de matrícula está vacío
                if (codigo_matr.getText().trim().isEmpty()) {
                    // Si está vacío, llama al método limpiar_campos
                    acciones.limpiar_campos(codigo_matr, nombre_asignatura, notauno, notados);
                }
                if (!codigo_matr.getText().trim().isEmpty()) {
                    // Si tiene contenido, llama al método rellenarCamposPorMatricula
                    String codigoMatricula = codigo_matr.getText().trim();
                    acciones.rellenarCamposPorMatricula(codigoMatricula);
                }
            }
        });

        Insertar.addActionListener(e -> {
            try {
                acciones.Insertar();
                textfields(codigo_matr, "123");
                textfields(nombre_asignatura, "Escriba el nombre de la asignatura");
                textfields(notauno, "X,XX");
                textfields(notados, "X,XX");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Modificar.addActionListener(e -> {
            try {
                acciones.Modificar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Borrar.addActionListener(e -> {
            try {
                acciones.Borrar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Consultar.addActionListener(e -> acciones.rellenarCamposPorMatricula(String.valueOf(codigo_matr)));

        marco.setVisible(true);
    }

    public void textfields(JTextField textField, String sugerencia){
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(sugerencia)) {
                    textField.setText(""); // Borrar la frase de fondo
                    textField.setForeground(Color.BLACK); // Cambiar el color de la fuente a negro
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(sugerencia); // Restaurar la frase de fondo si el campo está vacío
                    textField.setForeground(Color.GRAY); // Restaurar el color de la fuente a gris
                }
            }
        });
    }

    public JFrame getMarco() {
        return marco;
    }

    public JPanel getJtexto(){
        return Jtexto;
    }

    public JLabel getMensajeLabel() {
        return mensajeLabel;
    }

    public JTextField getCodigo_matr() {
        return codigo_matr;
    }

    public JTextField getNombre_asignatura() {
        return nombre_asignatura;
    }

    public JTextField getNotauno() {
        return notauno;
    }

    public JTextField getNotados() {
        return notados;
    }

}