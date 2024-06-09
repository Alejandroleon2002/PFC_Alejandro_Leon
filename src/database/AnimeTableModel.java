/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.ImageIcon;
import model.Anime;

public class AnimeTableModel extends AbstractTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            
            case 8:
                return ImageIcon.class;
                case 9:
                return ImageIcon.class;
                 case 10:
                return ImageIcon.class;
            default:
                return Object.class;
        }
    }
    private List<Anime> animes;
    private final String[] columnNames = {"Nombre", "Año", "Director", "Estudio", "Categoria", "Genero", "ID Anime", "Descripcion", "Detalles","Modificar","Eliminar"};

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return animes != null ? animes.size() : 0;
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
        Anime anime = animes.get(rowIndex);
        switch (columnIndex) {
            case 0: return anime.getNombre();
            case 1: return anime.getAnyo();
            case 2: return anime.getDirector();
            case 3: return anime.getEstudio();
            case 4: return anime.getCategoria().getNombre();
            case 5: return anime.getGenero().getNombre();
            case 6: return anime.getIdAnime();
            case 7: return anime.getDescripcion();
            case 8: return new ImageIcon(getClass().getResource("/imagenes/datos.png"));
            case 9: return new ImageIcon(getClass().getResource("/imagenes/editar.png"));
            case 10: return new ImageIcon(getClass().getResource("/imagenes/papeleras.png"));
            default: return null;
        }
        
    }
}