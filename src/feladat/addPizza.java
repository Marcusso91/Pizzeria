package feladat;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class addPizza extends JFrame {

    private DefaultListModel dlm;

    public addPizza() {

        initComponents();
        this.dlm = new DefaultListModel();
        this.setVisible(true);

        // center the frame
        this.setLocationRelativeTo(null);

        // invite DataBaseConnection class to create connection to the database
        Connection conn = DataBaseConnection.getConnection();

        try {

            /* create selectquery all feltet_nev from feltet table and we will add it to the jList with result set */
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT feltet_nev FROM feltet");
            while (rs.next()) {

                this.dlm.addElement(rs.getString(1));
            }
            this.hozzavalok.setModel(dlm);

            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázis hiba!", JOptionPane.WARNING_MESSAGE);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pizza_nev = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        hozzavalok = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        hozzaadas = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        pizza_ar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pizza hozzáadás");

        jLabel1.setText("Pizza név:");

        jScrollPane1.setViewportView(hozzavalok);

        jLabel2.setText("Hozzávalók:");

        hozzaadas.setText("Hozzáadás");
        hozzaadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hozzaadasActionPerformed(evt);
            }
        });

        jLabel3.setText("Ár:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(133, 133, 133)
                            .addComponent(hozzaadas))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel1)
                                    .addGap(19, 19, 19))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pizza_nev, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pizza_ar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pizza_nev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pizza_ar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addComponent(hozzaadas)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hozzaadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hozzaadasActionPerformed

        // we handle the possible excepions
        try {
            /* we throwing exceptions if the jTextField is empty or the pizza_nev jtextfield is not contains letter
             or if the pizza_ar jtextfield is not contains number*/
            if (this.pizza_nev.getText().isEmpty() || this.pizza_ar.getText().isEmpty()) {

                throw new MyException("A mező nem lehet üres!");

            } else if (!this.isName(this.pizza_nev.getText().trim().toLowerCase())) {

                throw new MyException("A név formátuma nem megfelelő!");

            } else if (!this.isNumber(this.pizza_ar.getText().trim())) {

                throw new MyException("Az ár fomátuma nem megfelelő!");
            }

            // check if the "pizza név" already exist
            String pizza_nev = this.pizza_nev.getText();
            Connection conn = DataBaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs3 = st.executeQuery("SELECT pizza_nev FROM pizza WHERE pizza_nev='" + pizza_nev + "'");
            if (rs3.next()) {

                throw new MyException("Ilyen pizza már van az adatbázisban: " + pizza_nev);
            }

            // insert "pizza név" and "pizza ár" column in the pizza table 
            int pizza_ar = Integer.parseInt(this.pizza_ar.getText());
            int index[] = this.hozzavalok.getSelectedIndices();
            Statement st2 = conn.createStatement();
            st.execute("INSERT INTO pizza (pizza_nev,pizza_ar) VALUES ('" + pizza_nev + "'," + pizza_ar + ")");
            ResultSet rs = st.executeQuery("SELECT pizza_id FROM pizza WHERE pizza_nev='" + pizza_nev + "'");
            rs.next();
            int pizza_id = rs.getInt(1);

            for (int i = 0; i < index.length; i++) {

                String feltet_nev = this.hozzavalok.getModel().getElementAt(index[i]);
                ResultSet rs2 = st2.executeQuery("SELECT feltet_id FROM feltet WHERE feltet_nev='" + feltet_nev + "'");
                rs2.next();
                int feltet_id = rs2.getInt(1);
                st.execute("INSERT INTO feltet_pizza (feltet_id,pizza_id) VALUES (" + feltet_id + "," + pizza_id + ")");
            }
            // closing connetion and the result set
            st.close();
            rs.close();
            
            // pops up a window when successfully added the data to the database
            JOptionPane.showMessageDialog(null, "A " + pizza_nev + " pizza sikeresen hozzáadva!", "Rendszerüzenet!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            
            // sql error message
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Adatbázis hiba!", JOptionPane.WARNING_MESSAGE);

        } catch (MyException me) {

            // error message
            JOptionPane.showMessageDialog(null, me.getHibauzenet(), "Kitöltési hiba!", JOptionPane.ERROR_MESSAGE);
        }

        // close current frame (addPizza) jFrame
        this.dispose();
    }//GEN-LAST:event_hozzaadasActionPerformed

    // create function if the string is contains only number and return will be right back with false if it' not
    private boolean isNumber(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i))) {

                return false;
            }
        }
        return true;
    }

    // create function if the string is contains only letter and return will be right back with false if it' not
    private boolean isName(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isLetter(s.charAt(i))) {

                return false;
            }
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hozzaadas;
    private javax.swing.JList<String> hozzavalok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pizza_ar;
    private javax.swing.JTextField pizza_nev;
    // End of variables declaration//GEN-END:variables
}
