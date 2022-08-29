package feladat;

import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class addRendeles extends JFrame {

    private DefaultListModel dlm;

    public addRendeles() {

        initComponents();
        this.dlm = new DefaultListModel();
        this.pizza_nev.addItem("Válasszon");
        this.pizza_meret.addItem("Válasszon");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        try {

            //create connection with the database
            Connection conn = DataBaseConnection.getConnection();
            Statement st = conn.createStatement();

            //create a select query pizza_nev and pizza_meret from the database and adding to the JComboBox
            ResultSet rs = st.executeQuery("SELECT pizza_nev FROM pizza");
            while (rs.next()) {

                this.pizza_nev.addItem(rs.getString(1));
            }
            ResultSet rs2 = st.executeQuery("SELECT pizza_merete FROM pizza_meret");
            while (rs2.next()) {

                this.pizza_meret.addItem(rs2.getString(1));
            }

            /* check if any item is selected from "pizza_nev" jComboBox and create select query their associated "feltet" in feltet table
            and adding to the "extrak" jList */
            this.pizza_nev.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {

                    dlm.clear();

                    if (e.getStateChange() == ItemEvent.SELECTED && !e.getItem().equals("Válasszon")) {
                        String nev = (String) e.getItem();

                        try {
                            Statement st2 = conn.createStatement();
                            ResultSet rs = st.executeQuery("SELECT pizza_id FROM pizza WHERE pizza_nev='" + nev + "'");
                            rs.next();
                            int pizza_id = rs.getInt(1);
                            ResultSet rs2 = st.executeQuery("SELECT feltet_id FROM feltet_pizza WHERE pizza_id=" + pizza_id);

                            while (rs2.next()) {

                                ResultSet rs3 = st2.executeQuery("SELECT feltet_nev FROM feltet WHERE feltet_id=" + rs2.getInt(1));
                                rs3.next();
                                String feltet_nev = rs3.getString(1);
                                dlm.addElement(feltet_nev);
                            }
                            ResultSet rs4 = st2.executeQuery("SELECT feltet_nev FROM feltet");
                            while (rs4.next()) {

                                String feltet_nev = rs4.getString(1);
                                if (dlm.contains(feltet_nev)) {

                                    dlm.removeElement(feltet_nev);
                                } else if (!feltet_nev.contains("alap")) {

                                    dlm.addElement(feltet_nev);
                                }
                            }
                            extrak.setModel(dlm);
                            st.close();
                            rs.close();
                            rs2.close();

                        } catch (SQLException ex) {

                            // sql error message
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázis hiba!", JOptionPane.WARNING_MESSAGE);

                        }
                    }
                }
            });

            // closing connection,statment and result set
            st.close();
            rs.close();
            rs2.close();

        } catch (SQLException ex) {

            // error mesage
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázis hiba!", JOptionPane.WARNING_MESSAGE);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pizza_nev = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pizza_meret = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        extrak = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        hozzaad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rendelés");
        setResizable(false);

        jLabel1.setText("Pizza név:");

        jLabel2.setText("Pizza méret:");

        jScrollPane1.setViewportView(extrak);

        jLabel3.setText("Extrák:");

        hozzaad.setText("Hozzáad");
        hozzaad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hozzaadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(hozzaad))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pizza_meret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pizza_nev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pizza_nev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pizza_meret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(hozzaad)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hozzaadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hozzaadActionPerformed

        String pizza_m = (String) this.pizza_meret.getSelectedItem();
        String pizza_nev = (String) this.pizza_nev.getSelectedItem();

        // I add the data of the ordered pizza to the database
        try {
            Connection conn = DataBaseConnection.getConnection();
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT pizza_id FROM pizza WHERE pizza_nev='" + pizza_nev + "'");
            rs.next();
            int pizza_id = rs.getInt(1);
            ResultSet rs2 = st.executeQuery("SELECT pizza_meret_id,pizza_meret_ar FROM pizza_meret WHERE pizza_merete=" + pizza_m);
            rs2.next();
            int pizza_meret_id = rs2.getInt("pizza_meret_id");
            int pizza_ar = rs2.getInt(2);
            int index[] = this.extrak.getSelectedIndices();
            int rendeles_ar = (index.length * 150) + pizza_ar;
            st.execute("INSERT INTO rendeles (pizza_id,rendelesi_ido,pizza_meret_id,rendeles_ar) VALUES ('" + pizza_id + "'," + "NOW(),'" + pizza_meret_id + "','" + rendeles_ar + "')");

            ResultSet rs4 = st.executeQuery("SELECT max(rendeles_id) FROM rendeles");
            rs4.next();
            int rendeles_id = rs4.getInt(1);
            String feltet = "";
            for (int i = 0; i < index.length; i++) {

                feltet = this.extrak.getModel().getElementAt(index[i]);
                ResultSet rs3 = st2.executeQuery("SELECT feltet_id FROM feltet WHERE feltet_nev='" + feltet + "'");
                rs3.next();
                int feltet_id = rs3.getInt(1);
                st.execute("INSERT INTO feltet_pizza_extra (rendeles_id,feltet_id) VALUES ('" + rendeles_id + "','" + feltet_id + "')");
            }

            // pops up a window when successfully added the data to the database
            JOptionPane.showMessageDialog(null, "Sikeres rendelés!", "Rendszerüzenet!", JOptionPane.INFORMATION_MESSAGE);

            // closing connection, statement and result set
            rs.close();
            rs2.close();
            rs4.close();
            st.close();
            st2.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázis hiba!", JOptionPane.WARNING_MESSAGE);

        }
        // closing current frame (addRendeles) jFrame
        this.dispose();
    }//GEN-LAST:event_hozzaadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> extrak;
    private javax.swing.JButton hozzaad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> pizza_meret;
    private javax.swing.JComboBox<String> pizza_nev;
    // End of variables declaration//GEN-END:variables
}
