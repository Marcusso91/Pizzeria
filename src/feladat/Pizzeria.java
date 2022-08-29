package feladat;

import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;

public class Pizzeria extends JFrame {

    private DefaultTableModel dtm;

    public Pizzeria() {
        
        /* - Download The Connector For JAVA & MYSQL database */
         /* LINK -> https://dev.mysql.com/downloads/connector/j/5.1.html */
         /* OPEN XAMPP and Strat Apache & Mysql */
        /* Go to -> http://localhost/phpmyadmin and create a new database */

        initComponents();
        this.dtm = (DefaultTableModel) this.tabla.getModel();
        
         // set icon image on frame
        this.setIconImage(new ImageIcon("pizza.png").getImage());
        this.dtm = (DefaultTableModel) this.tabla.getModel();

        // i make the frame visible
        this.setVisible(true);

        //center the frame
        this.setLocationRelativeTo(null);

        try {

            // invite DataBaseConnection class to create connection with the database
            Connection conn = DataBaseConnection.getConnection();
            Statement st = conn.createStatement();

            /* create a selectquery (pizza_nev,rendelesi_ido,pizza_merete,rendeles_ar) from three table with 
            "INNER JOIN" and put in the "tabla" jTable with result set */
            ResultSet rs = st.executeQuery("SELECT pizza_nev,rendelesi_ido,pizza_merete,rendeles_ar FROM ((rendeles INNER JOIN pizza ON rendeles.pizza_id=pizza.pizza_id) INNER JOIN pizza_meret ON rendeles.pizza_meret_id=pizza_meret.pizza_meret_id)");
            Object rowData[] = new Object[4];
            while (rs.next()) {

                for (int i = 0; i < 4; i++) {

                    rowData[i] = rs.getString(i + 1);
                }
                this.dtm.addRow(rowData);
            }

        } catch (SQLException se) {

            // sql error message
            JOptionPane.showMessageDialog(null, se.getMessage(), "Adatbázis hiba!", JOptionPane.ERROR_MESSAGE);

        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        kilepes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        rendeles = new javax.swing.JMenuItem();
        pizza_hozzaadasa = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        feltetek_lekerdezese = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizzéria");
        setResizable(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Név", "Rendelési idő", "Pizza méret", "Pizza ár"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Pizzéria");

        jMenu1.setText("Fájl");

        kilepes.setText("Kilépés");
        kilepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kilepesActionPerformed(evt);
            }
        });
        jMenu1.add(kilepes);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Szerkesztés");

        rendeles.setText("Rendelés");
        rendeles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rendelesActionPerformed(evt);
            }
        });
        jMenu2.add(rendeles);

        pizza_hozzaadasa.setText("Pizza hozzáadása");
        pizza_hozzaadasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pizza_hozzaadasaActionPerformed(evt);
            }
        });
        jMenu2.add(pizza_hozzaadasa);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Lekérdezés");

        feltetek_lekerdezese.setText("Feltétek lekérdezése");
        feltetek_lekerdezese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feltetek_lekerdezeseActionPerformed(evt);
            }
        });
        jMenu3.add(feltetek_lekerdezese);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rendelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rendelesActionPerformed

        addRendeles ar = new addRendeles();

    }//GEN-LAST:event_rendelesActionPerformed

    private void pizza_hozzaadasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pizza_hozzaadasaActionPerformed

        // show a new form
        addPizza ap = new addPizza();

    }//GEN-LAST:event_pizza_hozzaadasaActionPerformed

    private void feltetek_lekerdezeseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feltetek_lekerdezeseActionPerformed

        // check if any row in the jtable is selected
        int sor = (int) this.tabla.getSelectedRow();
        if (sor != -1) {
            String rendelesi_ido = (String) this.dtm.getValueAt(sor, 1);
            String pizza_nev = (String) this.dtm.getValueAt(sor, 0);

            try {
                
                Connection conn = DataBaseConnection.getConnection();
                Statement st = conn.createStatement();
                
                //create select query rendeles_id and pizza_id and that it no longer needs to be queried in the new form
                ResultSet rs = st.executeQuery("SELECT rendeles_id FROM rendeles WHERE rendelesi_ido='" + rendelesi_ido + "'");
                rs.next();
                int rendeles_id = rs.getInt(1);
                ResultSet rs2 = st.executeQuery("SELECT pizza_id FROM pizza WHERE pizza_nev='" + pizza_nev + "'");
                rs2.next();
                int pizza_id = rs2.getInt(1);
                
                // show the new form
                getFeltetek gf = new getFeltetek(rendeles_id, pizza_id);

            } catch (SQLException ex) {
                
                // error message
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázis hiba!", JOptionPane.ERROR_MESSAGE);

            }

        } else {

            JOptionPane.showMessageDialog(null, "Nincs kijelölve sor!", "Rendszerüzenet!", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_feltetek_lekerdezeseActionPerformed

    private void kilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kilepesActionPerformed

        // create custom exit button and it ask's if you want to exit
        int dialog_value = (int) JOptionPane.showConfirmDialog(null, "Biztosan ki akar lépni a programból?", "Rendszerüzenet", JOptionPane.YES_NO_OPTION);
        if (dialog_value == 0) {

            System.exit(0);
        }

    }//GEN-LAST:event_kilepesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem feltetek_lekerdezese;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem kilepes;
    private javax.swing.JMenuItem pizza_hozzaadasa;
    private javax.swing.JMenuItem rendeles;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
