/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Capitulo;

public class CapituloTableModel extends AbstractTableModel {
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 5:
                return ImageIcon.class;
            default:
                return Object.class;
        }
    }
    
    private List<Capitulo> capitulos;
    private final String[] columnNames = {"ID Capítulo", "ID Anime", "Número del Capítulo", "Título", "Duración", "Comentar"};

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return capitulos != null ? capitulos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Capitulo capitulo = capitulos.get(rowIndex);
        switch (columnIndex) {
            case 0: return capitulo.getCapituloId();
            case 1: return capitulo.getAnimeId();
            case 2: return capitulo.getNumeroCapitulo();
            case 3: return capitulo.getTitulo();
            case 4: return capitulo.getDuracion();
            case 5: return new ImageIcon(getClass().getResource("/imagenes/File-Text.jpg"));
            default: return null;
        }
    }

    
}
