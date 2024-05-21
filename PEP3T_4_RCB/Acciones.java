package PEP3T_4_RCB;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Acciones {
    private Swing swing;

    public Acciones(Swing swing) {
        this.swing = swing;
    }
    public void Insertar() throws SQLException {

        boolean comprobado = comprobacion_campos(swing.getCodigo_matr(), swing.getNombre_asignatura(), swing.getNotauno(), swing.getNotados());

        if (comprobado){
            // Consulta sql
            ConexionDB conexBd = new ConexionDB();
            conexBd.conexiondb();
            Connection conexion = conexBd.getConexBd();

            PreparedStatement statement = conexion.prepareStatement("INSERT INTO NOTAS (Cod_Matricula, Nombre_Asig, Nota1, Nota2) VALUES (?, ?, ?, ?)");
            statement.setString(1, swing.getCodigo_matr().getText());
            statement.setString(2, swing.getNombre_asignatura().getText());
            statement.setString(3, swing.getNotauno().getText());
            statement.setString(4, swing.getNotados().getText());

            // Ejecutar la consulta
            statement.executeUpdate();

            statement.close();
            conexion.close();

            mostrarMensaje("Registro insertado");

            limpiar_campos(swing.getCodigo_matr(), swing.getNombre_asignatura(), swing.getNotauno(), swing.getNotados());
        }

    }
    public void Modificar() throws SQLException {

        boolean comprobado = comprobacion_campos(swing.getCodigo_matr(), swing.getNombre_asignatura(), swing.getNotauno(), swing.getNotados());

        if (comprobado){
            // Consulta sql
            ConexionDB conexBd = new ConexionDB();
            conexBd.conexiondb();
            Connection conexion = conexBd.getConexBd();

            PreparedStatement statement = conexion.prepareStatement("UPDATE NOTAS SET Nombre_Asig = ?, Nota1 = ?, Nota2 = ? WHERE Cod_Matricula = ?");
            statement.setString(1, swing.getNombre_asignatura().getText());
            statement.setString(2, swing.getNotauno().getText());
            statement.setString(3, swing.getNotados().getText());
            statement.setString(4, swing.getCodigo_matr().getText());

            // Ejecutar la consulta
            statement.executeUpdate();

            statement.close();
            conexion.close();

            mostrarMensaje("Registro modificado");

            limpiar_campos(swing.getCodigo_matr(), swing.getNombre_asignatura(), swing.getNotauno(), swing.getNotados());
        }

    }
    public void Borrar() throws SQLException {

        if(!swing.getCodigo_matr().equals("123")){
            // Consulta sql
            ConexionDB conexBd = new ConexionDB();
            conexBd.conexiondb();
            Connection conexion = conexBd.getConexBd();

            PreparedStatement statement = conexion.prepareStatement("DELETE FROM NOTAS WHERE Cod_Matricula = ?");
            statement.setString(1, swing.getCodigo_matr().getText());

            // Ejecutar la consulta
            statement.executeUpdate();

            statement.close();
            conexion.close();

            mostrarMensaje("Registro eliminado");

            limpiar_campos(swing.getCodigo_matr(), swing.getNombre_asignatura(), swing.getNotauno(), swing.getNotados());
        }

    }
    public void rellenarCamposPorMatricula(String codigoMatricula) {
        // Consulta SQL para obtener los datos del alumno
        // Supongamos que tienes un método en la clase ConexionDB para ejecutar la consulta y obtener los datos

        try {
            ConexionDB conexBd = new ConexionDB();
            conexBd.conexiondb();
            Connection conexion = conexBd.getConexBd();

            PreparedStatement statement = conexion.prepareStatement("SELECT Nombre_Asig, Nota1, Nota2 FROM NOTAS WHERE Cod_Matricula = ?");
            statement.setString(1, codigoMatricula);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombreAsignatura = resultSet.getString("Nombre_Asig");
                String nota1 = resultSet.getString("Nota1");
                String nota2 = resultSet.getString("Nota2");

                // Rellenar los JTextField restantes con los datos del alumno
                swing.getNombre_asignatura().setText(nombreAsignatura);
                swing.getNotauno().setText(nota1);
                swing.getNotados().setText(nota2);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Maneja adecuadamente las excepciones en tu aplicación
        }
    }

    public void limpiar_campos(JTextField codigo_matr, JTextField nombre_asignatura, JTextField notauno, JTextField notados){
        codigo_matr.setText("123");
        codigo_matr.setForeground(Color.GRAY);

        nombre_asignatura.setText("Escriba el nombre de la asignatura");
        nombre_asignatura.setForeground(Color.GRAY);

        notauno.setText("X,XX");
        notauno.setForeground(Color.GRAY);
        notados.setText("X,XX");
        notados.setForeground(Color.GRAY);
    }
    public boolean comprobacion_campos(JTextField codigo_matr, JTextField nombre_asignatura, JTextField notauno, JTextField notados) {
        float nota1, nota2;

        // Verificación de campos vacíos
        if (codigo_matr.getText().equals("123") || nombre_asignatura.getText().equals("Escriba el nombre de la asignatura") || notauno.getText().equals("X,XX") || notados.getText().equals("X,XX")) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Devuelve false si hay campos vacíos
        }

        // Verificación de tipos de datos insertados
        try {
            nota1 = Float.parseFloat(notauno.getText());
            nota2 = Float.parseFloat(notados.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Las notas deben ser números decimales válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Devuelve false si las notas no son números válidos
        }

        if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
            JOptionPane.showMessageDialog(null, "Las notas deben estar entre 0 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Si todas las comprobaciones pasan, devuelve true
        return true;
    }

    private void mostrarMensaje(String texto) {
        swing.getMensajeLabel().setText(texto);
        swing.getJtexto().revalidate();

        // Crear un temporizador para ocultar el diálogo después de 2.5 segundos
        Timer timer = new Timer(3500, e -> {
            swing.getMensajeLabel().setText(" ");
            swing.getJtexto().revalidate();
        });

        timer.setRepeats(false); // El temporizador se ejecutará una sola vez
        timer.start();
    }
}
