package Vista;

import Reglas.Tarea;
import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;

public class TaskTrack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Tarea> tareas = new ArrayList<Tarea>();

        while (true) {
            JOptionPane.showMessageDialog(null, "¡Bienvenido a TaskTrack!");
            String[] opciones = {"Crear nueva tarea", "Ver tareas pendientes", "Salir"};
            int opcion = JOptionPane.showOptionDialog(
                null,
                "Elige una opción:",
                "TaskTrack",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (opcion == 0) {
                String descripcion = JOptionPane.showInputDialog("Ingresa la descripción de la tarea:");
                String fechaLimiteString = JOptionPane.showInputDialog("Ingresa la fecha límite (dd/MM/yyyy):");

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaLimite = dateFormat.parse(fechaLimiteString);
                    Tarea tarea = new Tarea(descripcion, fechaLimite);
                    tareas.add(tarea);
                    JOptionPane.showMessageDialog(null, "Tarea creada exitosamente.");
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar la fecha. Intenta de nuevo.");
                }
            } else if (opcion == 1) {
                StringBuilder tareasPendientes = new StringBuilder("Tareas pendientes:\n");

                if (tareas.size() > 0) {
                    for (Tarea tarea : tareas) {
                        tareasPendientes.append("- ").append(tarea.getDescripcion()).append(" (Fecha límite: ").append(tarea.getFechaLimite()).append(")\n");
                        Date fechaActual = new Date();

                        if (tarea.getFechaLimite().before(fechaActual)) {
                            tareasPendientes.append("   ¡Esta tarea está vencida!\n");
                            String respuesta = JOptionPane.showInputDialog("¿Desea reagendar la tarea? (SI/NO)").trim().toLowerCase();

                            if (respuesta.equals("si")) {
                                String nuevaFechaLimiteString = JOptionPane.showInputDialog("Ingresa la nueva fecha límite (dd/MM/yyyy):");
                                try {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    Date nuevaFechaLimite = dateFormat.parse(nuevaFechaLimiteString);
                                    tarea.setFechaLimite(nuevaFechaLimite);
                                    tareasPendientes.append("Tarea reagendada exitosamente.\n");
                                } catch (ParseException e) {
                                    tareasPendientes.append("Error al ingresar la fecha. La tarea no se ha reagendado.\n");
                                }
                            }
                        }
                    }
                } else {
                    tareasPendientes.append("No hay tareas pendientes.\n");
                }

                JOptionPane.showMessageDialog(null, tareasPendientes.toString());
            } else if (opcion == 2) {
                JOptionPane.showMessageDialog(null, "Gracias por usar TaskTrack. ¡Hasta luego!");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.");
            }
        }
    }
}
