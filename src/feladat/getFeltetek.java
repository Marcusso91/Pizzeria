package feladat;

import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;

public class getFeltetek extends JFrame {
    
    private DefaultTableModel dtm;
    private Vector rowData;
    
    public getFeltetek(int rendeles_id, int pizza_id) {
        
        initComponents();
        
        rowData = new Vector<Object>();
        this.dtm = (DefaultTableModel) this.tabla.getModel();
        this.setVisible(true);

        //center the frame
        this.setLocationRelativeTo(null);

        // invite DataBaseConnection class to create connection with the database
        Connection conn = DataBaseConnection.getConnection();

        // create select query "feltet" make it from two tables from mysql
        String sz = "feltet_pizza_extra WHERE rendeles_id=";
        this.addFeltet(conn, rendeles_id, sz);
        sz = "feltet_pizza WHERE pizza_id=";
        this.addFeltet(conn, pizza_id, sz);

        // adding feltet from the vector to the jTable
        this.dtm.addRow(this.rowData);
        
        try {
            // closing connection
            conn.close();
            
        } catch (SQLException ex) {
            
           // sql error message
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázishiba!", JOptionPane.WARNING_MESSAGE);
           
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Feltétek");
        setResizable(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Feltét név"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // create function for selec
    public void addFeltet(Connection conn, int id, String sz) {
        
        try {
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();

            /* create select query all feltet name from two table and put in the "tabla" jTable with result set */
            ResultSet rs = st.executeQuery("SELECT feltet_id FROM " + sz + id);
            
            while (rs.next()) {
                
                int feltet_id = rs.getInt(1);
                ResultSet rs2 = st2.executeQuery("SELECT feltet_nev FROM feltet WHERE feltet_id=" + feltet_id);
                rs2.next();
                this.rowData.add(rs2.getString(1));
                
            }
            // closing connection and the statement
            rs.close();
            
        } catch (SQLException ex) {

            // sql error message
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázishiba!", JOptionPane.WARNING_MESSAGE);
          
            
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
