/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombheri;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lanaya
 */
public class activityPrint extends javax.swing.JFrame {

    static String regMain;
    sqlConn sc =new sqlConn();
    static String d1;
    static String f1;
    static String l1;
    static String a1;
    static String c1;
    static String ad1;
    static String payAmt;
    JTable tableAct;
    JFrame acti;
    JFrame fra;
  
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();
    String dat=dtf.format(localDate);
    
    public void getFrame(JFrame f){
        fra=f;
    }
    public void setValue(){
        regno.setText(regMain);
        firstn.setText(f1);
        lastn.setText(l1);
        age.setText(a1);
        contno.setText(c1);
    }
    public void getAct(JFrame v){
        acti=v;
    }
    public void refreshFrame(){
        
        this.dispose();    
        
        activityPrint o=new activityPrint();
//        regno.setText(regMain);
//        firstn.setText(f1);
//        lastn.setText(l1);
//        age.setText(a1);
//        contno.setText(c1);
        o.setVisible(true);
        
   
            
    }
    
    public void addOption() throws Exception{

        String sqlTreat="select tname from treatment;";
        String sqlDoc="select dname from doctor;";

        sqlConn sc=new sqlConn();
        ResultSet rs1 = sc.stmt.executeQuery(sqlTreat);
        sqlConn sc1=new sqlConn();
        ResultSet rs2= sc1.stmt.executeQuery(sqlDoc);
        
 
        JTextField dateText = new JTextField(6);
        dateText.setText(dat);
        JTextField remarkText = new JTextField(15);
        JTextField priceText = new JTextField(5);
        JComboBox treatBox=new JComboBox();
        JComboBox docBox=new JComboBox();
        while (rs1.next())
        {      
           treatBox.addItem(rs1.getString("tname"));
           
        }
        
        while (rs2.next())
        {      
           docBox.addItem(rs2.getString("dname"));
        }

        JPanel addPanel = new JPanel();
//        addPanel.setBounds(200, 200, 600, 600);
        
        addPanel.add(new JLabel("Date:"));
        addPanel.add(dateText, BorderLayout.NORTH);
        
        addPanel.add(new JLabel("Treatment:"));
        addPanel.add(treatBox, BorderLayout.NORTH);

        addPanel.add(Box.createHorizontalStrut(15)); // a spacer
        addPanel.add(new JLabel("Doctor:"));
        addPanel.add(docBox, BorderLayout.NORTH); // comboBox added to transparent frame
        
        addPanel.add(new JLabel("Price:"));
        addPanel.add(priceText, BorderLayout.NORTH);
        priceText.setText("0");
        
        addPanel.add(new JLabel("Remark:"));
        addPanel.add(remarkText, BorderLayout.NORTH); 
        
        

        int result = JOptionPane.showConfirmDialog(null, addPanel, 
                 "Fill this box and select Treatment and Doctor Name", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            String treatVal = String.valueOf(treatBox.getSelectedItem());
            String docVal = String.valueOf(docBox.getSelectedItem());
            String datVal=dateText.getText();
            String priceVal=priceText.getText();
            String remarkVal=remarkText.getText();
            
            String sqlAdd="insert into activity values("+regMain+",'"+datVal+"','"+treatVal+"','"+docVal+"',"+priceVal+",'"+remarkVal+"');";
            
            try{
                System.out.println(sqlAdd);
                sc.stmt.executeUpdate(sqlAdd);
                System.out.println("Successfully entered data");
                JOptionPane.showMessageDialog(null, "Successfully entered data");
                this.refreshFrame();

            }catch(Exception e){
                System.out.println(e);
                String message = "\"Enter Value is incorrect or empty\"\n"
                + "\"Check data type\"\n";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void EditOption() throws Exception{

        try{
            String sqlTreat="select tname from treatment;";

            String sqlDoc="select dname from doctor;";
            String datVal=tableAct.getValueAt(tableAct.getSelectedRow(), 0).toString();
            String treatVal=tableAct.getValueAt(tableAct.getSelectedRow(), 1).toString();
            String docVal=tableAct.getValueAt(tableAct.getSelectedRow(), 2).toString();
            String priceVal=tableAct.getValueAt(tableAct.getSelectedRow(), 3).toString();
            String remarkVal=tableAct.getValueAt(tableAct.getSelectedRow(), 4).toString();

            sqlConn sc=new sqlConn();
            ResultSet rs1 = sc.stmt.executeQuery(sqlTreat);
            sqlConn sc1=new sqlConn();
            ResultSet rs2= sc1.stmt.executeQuery(sqlDoc);


            JTextField dateText = new JTextField(6);
            JTextField remarkText = new JTextField(22);
            JTextField priceText = new JTextField(5);
            JComboBox treatBox=new JComboBox();
            JComboBox docBox=new JComboBox();
            while (rs1.next())
            {      
    //           String name = rs1.getString("tname");         
               treatBox.addItem(rs1.getString("tname"));

            }

            while (rs2.next())
            {      
               docBox.addItem(rs2.getString("dname"));
            }

            treatBox.setSelectedItem(treatVal);
            docBox.setSelectedItem(docVal);
            dateText.setText(datVal);
            priceText.setText(priceVal);
            remarkText.setText(remarkVal);

            JPanel editPanel = new JPanel();
//        editPanel.setBounds(200, 200, 600, 600);
        
            editPanel.add(new JLabel("Date:"));
            editPanel.add(dateText, BorderLayout.NORTH);

            editPanel.add(new JLabel("Treatment:"));
            editPanel.add(treatBox, BorderLayout.NORTH);

            editPanel.add(Box.createHorizontalStrut(15)); // a spacer
            editPanel.add(new JLabel("Doctor:"));
            editPanel.add(docBox, BorderLayout.NORTH); // comboBox added to transparent frame

            editPanel.add(new JLabel("Price:"));
            editPanel.add(priceText, BorderLayout.NORTH);

            editPanel.add(new JLabel("Remark:"));
            editPanel.add(remarkText, BorderLayout.NORTH); 



            int result = JOptionPane.showConfirmDialog(null, editPanel, 
                     "Edit Panel", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String treatV = String.valueOf(treatBox.getSelectedItem());
                String docV = String.valueOf(docBox.getSelectedItem());
                String datV=dateText.getText();
                String priceV=priceText.getText();
                String remarkV=remarkText.getText();
//                String sqlEdit="update activity set tname='"+x+"',dname='"+y+"' where regno="+regMain+" and tname='"+treatVal+"' and dname='"+docVal+"';";
                String sqlEdit="update activity set date='"+datV+"',tname='"+treatV+"',dname='"+docV+"',price="+priceV+",remark='"+remarkV+"' where regno="+regMain+" and date='"+datVal+"'and tname='"+treatVal+"'and dname='"+docVal+"'and price="+priceVal+" and remark='"+remarkVal+"';";


                try{
                    System.out.println(sqlEdit);
                    sc.stmt.executeUpdate(sqlEdit);
                    System.out.println("Successfully entered data");
                    JOptionPane.showMessageDialog(null, "Successfully entered data");
                    this.refreshFrame();

                }catch(Exception e){
                    System.out.println(e);
                    String message = "\"Enter Value is incorrect or empty\"\n"
                    + "\"Check data type\"\n";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception e){
//            System.out.println(e);
//            System.out.println("Activity not selected");
            String message = "\"Activity not selected\"\n"
            + "\"Click on row to highlight\"\n";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
            JOptionPane.ERROR_MESSAGE);
            
        }
    }


    
    
    public void tableRun() throws Exception{
        System.out.println(regMain);
        String sql="select date,tname,dname,price,remark from activity where regno="+regMain+" and payment='not done';";
        sqlConn sc=new sqlConn();
        ResultSet rs = sc.stmt.executeQuery(sql);

        // It creates and displays the table
        tableAct = new JTable(buildTableModel(rs));
        

        tableAct.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableAct.getColumnModel().getColumn(1).setPreferredWidth(55);
        tableAct.getColumnModel().getColumn(2).setPreferredWidth(55);
        tableAct.getColumnModel().getColumn(3).setPreferredWidth(10);
        tableAct.getColumnModel().getColumn(4).setPreferredWidth(250);
        
        tableAct.setBounds(12, 130, 900, 470);
        tableAct.setFont(new Font("Monospaced", Font.TRUETYPE_FONT, 13));
        tableAct.setRowHeight(30);
        tableAct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        tableAct.setBackground(red);
        tableAct.setRowSelectionAllowed(false);
        tableAct.setCellSelectionEnabled(false);
        
       
        tableAct.setShowGrid(true);
        
       
        
        this.add(tableAct);        
//        JOptionPane.showMessageDialog(null, new JScrollPane(tableAct));
        
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();

//            columnNames.add("RegNo");
            columnNames.add("Date");
            columnNames.add("Treatment");
            columnNames.add("DoctorName");
            columnNames.add("Price");
            columnNames.add("Remark");
            
     
        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
    
    public activityPrint() {
        
        try{
            
            this.tableRun();
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        this.setTitle("Print this for Billing");
        
        initComponents();
//        labelSign.setVisible(true);
        this.setValue();
        System.out.println(payAmt);
        txtPay.setText(payAmt);
        
        //printing part
//        PrinterJob pjob = PrinterJob.getPrinterJob();
//        PageFormat preformat = pjob.defaultPage();
//        preformat.setOrientation(PageFormat.PORTRAIT);
//        PageFormat postformat = pjob.pageDialog(preformat);
//        //If user does not hit cancel then print.
//        if (preformat != postformat) {
//            //Set print component
//            pjob.setPrintable(new Printer(this), postformat);
//            if (pjob.printDialog()) {
//                try {
//                    pjob.print();
//                } catch (PrinterException ex) {
//                    Logger.getLogger(activityPrint.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
        
        
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                procedureCall();
            }
        });

    }
    public  void procedureCall(){
        try{
            this.dispose();
            acti.setVisible(true);
        }catch(NullPointerException n){
            System.out.println("do you know that you are beautiful? :)");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regno = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        lastn = new javax.swing.JTextField();
        contno = new javax.swing.JTextField();
        firstn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelSign = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPay = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 670));
        setResizable(false);

        regno.setEditable(false);
        regno.setText("Reg No.");

        age.setEditable(false);
        age.setText("Age");

        lastn.setEditable(false);
        lastn.setText("Last Name");

        contno.setEditable(false);
        contno.setText("Contact no");
        contno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contnoActionPerformed(evt);
            }
        });

        firstn.setEditable(false);
        firstn.setText("First Name");

        jLabel1.setText("Age");

        jLabel2.setText("Phone No.");

        jLabel3.setText("RegNo.");

        jLabel5.setText("Doctor Names");

        jLabel6.setText("Treatment Lists");

        jLabel7.setText("Price");

        jLabel8.setText("Remark");

        jLabel9.setText("Date");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(157, 520));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        labelSign.setText("signature");

        jLabel4.setText("Total Amount");

        txtPay.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Om Bheri Dental Clinic Pvt Limited, Kohalpur-11 Banke ");

        jLabel11.setText("Pan no: 305581167       Phone no:081-541845");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(firstn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lastn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contno, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelSign)
                                .addGap(106, 106, 106))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(346, 346, 346))))
            .addGroup(layout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contno, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(labelSign)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contnoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(activityPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(activityPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(activityPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(activityPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new activityPrint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField age;
    private javax.swing.JTextField contno;
    private javax.swing.JTextField firstn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelSign;
    private javax.swing.JTextField lastn;
    private javax.swing.JTextField regno;
    private javax.swing.JTextField txtPay;
    // End of variables declaration//GEN-END:variables
}
