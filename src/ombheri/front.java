/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombheri;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static ombheri.activity.regMain;

/**
 *
 * @author Lanaya
 */
public class front extends javax.swing.JFrame {

    /**
     * Creates new form front
     */
    
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    sqlConn sqlcon=new sqlConn();
    String text;
    int a;
    String b;
    String c;
    String d;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    String dat=dtf.format(localDate);
    
    Action action = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
//            System.out.println("some action");
            searchbtnActionPerformed(e);
            
        }
    };
    
    public void Backupdbtosql() {
    try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        CodeSource codeSource = front.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        String dbName = "dental";
        String dbUser = "root";
        String dbPass = "test10";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        String folderPath = jarDir + "\\backup";

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(folderPath);
        f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
//         String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";
         String savePath = "\"" + jarDir + "\\backup\\" + "backup" + dat + ".sql\"";
//         System.out.println(savePath);

        /*NOTE: Used to create a cmd command*/
        String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

        /*NOTE: Executing the command here*/
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Backup Successful saved as backup" + dat + ".sql");
            } else {
                JOptionPane.showMessageDialog(null, "Backup Error ");
            }

    } catch (URISyntaxException | IOException | InterruptedException ex) {
        JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
    }
}
    public void Restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = front.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
             String dbName = "dental";
             String dbUser = "root";
             String dbPass = "test10";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + s);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }

    
    public void lastReg(){
        
        String sql="SELECT * FROM customer ORDER BY regno DESC LIMIT 1;";
        try{
            ResultSet rs=sqlcon.stmt.executeQuery(sql);
            
            if(rs.next()){
                a=rs.getInt(2);
                b=rs.getString(3);
                c=rs.getString(4);
                d=rs.getString(6);
                
            }       
            else
                a=1;
            
        }catch(Exception e){
            System.out.println(e);
        }
        text=(Integer.toString(a));
    }
    
    public void refreshFront(){
        this.dispose();
        front f=new front();
        f.setVisible(true);
    }
    public front() {
        
        this.lastReg();
        initComponents();
        this.setTitle("OM Bheri Dental");
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); 
        recReg.setText(text);
        regno.setText(text);
        firstn.setText(b);
        lastn.setText(c);
        contno.setText(d);
        
        regno.addActionListener( action );
//        searchbtn.addActionListener( action );

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
        searchbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        recReg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        firstn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        contno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        entry = new javax.swing.JMenuItem();
        itemRefresh = new javax.swing.JMenuItem();
        showData = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        itemToday = new javax.swing.JMenuItem();
        itemYes = new javax.swing.JMenuItem();
        itemMonth = new javax.swing.JMenuItem();
        itemYear = new javax.swing.JMenuItem();
        item7 = new javax.swing.JMenuItem();
        itemMan = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        regno.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        regno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        regno.setText("Registration Number");
        regno.setBorder(null);
        regno.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        regno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                regnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                regnoFocusLost(evt);
            }
        });
        regno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regnoActionPerformed(evt);
            }
        });

        searchbtn.setText("Search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Reg no.");

        recReg.setEditable(false);

        jLabel2.setText("First Name");

        firstn.setEditable(false);

        jLabel3.setText("Last Name");

        lastn.setEditable(false);

        jLabel4.setText("Phone no.");

        contno.setEditable(false);

        jLabel5.setText("Recent Patient");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Registration Number");

        jMenu1.setText("File  ");

        entry.setText("New Entry");
        entry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryActionPerformed(evt);
            }
        });
        jMenu1.add(entry);

        itemRefresh.setText("Refresh");
        itemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRefreshActionPerformed(evt);
            }
        });
        jMenu1.add(itemRefresh);

        showData.setText("Last 100 Patient ");
        showData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDataActionPerformed(evt);
            }
        });
        jMenu1.add(showData);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit  ");

        jMenuItem2.setText("Treatment list");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem1.setText("Doctor list");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Search patient  ");

        jMenuItem4.setText("by Name");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem3.setText("by Phone no.");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem5.setText("by Date Register");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Check activity  ");

        itemToday.setText("Today");
        itemToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTodayActionPerformed(evt);
            }
        });
        jMenu4.add(itemToday);

        itemYes.setText("Yesterday");
        itemYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemYesActionPerformed(evt);
            }
        });
        jMenu4.add(itemYes);

        itemMonth.setText("This Month in AD");
        itemMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMonthActionPerformed(evt);
            }
        });
        jMenu4.add(itemMonth);

        itemYear.setText("This Year in AD");
        itemYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemYearActionPerformed(evt);
            }
        });
        jMenu4.add(itemYear);

        item7.setText("Last 7 days");
        item7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item7ActionPerformed(evt);
            }
        });
        jMenu4.add(item7);

        itemMan.setText("Last N number of days");
        itemMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemManActionPerformed(evt);
            }
        });
        jMenu4.add(itemMan);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Data");

        jMenuItem6.setText("Backup Data");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Restore Data");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recReg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contno, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(recReg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(firstn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lastn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regnoActionPerformed

    private void regnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regnoFocusGained
        // TODO add your handling code here:
        if(regno.getText().equals("Registration Number")){
            regno.setText("");
        }
    }//GEN-LAST:event_regnoFocusGained

    private void regnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regnoFocusLost
        // TODO add your handling code here:
        if(regno.getText().equals("")){
            regno.setText("Registration Number");
        }
    }//GEN-LAST:event_regnoFocusLost

    private void entryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryActionPerformed
        // TODO add your handling code here:
        
        custForm cf=new custForm();
        cf.setVisible(true);
        cf.generate();
        this.dispose();
        

        
    }//GEN-LAST:event_entryActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        // TODO add your handling code here:
        String sql="select * from customer where regno="+regno.getText()+";";
        try{
            ResultSet rs=sqlcon.stmt.executeQuery(sql);
            
//            while(rs.next())  
            
            if (rs.next()) {
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));  
                
                custView cv=new custView();
                cv.setVisible(true);
                cv.getFront(this);
                cv.getData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));                  
                
            }else{
//                String message = "\"Patient not found\"\n";
//                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
//                JOptionPane.ERROR_MESSAGE);
//                System.out.println("patient not found");

                JPanel panelError=new JPanel();
                
                String mes = "\"Patient Not Found\"\n"
                + "\"Do you want to add new?\"\n";
                
                int result = JOptionPane.showConfirmDialog(null, mes,"Patient Not Found", JOptionPane.OK_CANCEL_OPTION);
                try{
                    if (result == JOptionPane.OK_OPTION) {
                    System.out.println("OK button pressed");
                    custForm cf=new custForm();
                    cf.setVisible(true);
                    cf.generate();
                    this.dispose();
                    
                    }
                }catch(Exception e){
                    System.out.println(e);
                    String message = "\"Something Went Wrong\"\n"
                    + "\"Call Diwash :P\"\n";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }catch(Exception e){
            String message = "\"Data entered is incorrect or empty\"\n"
            + "\"Check for default value or wrong data type\"\n";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
            JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        
    }//GEN-LAST:event_searchbtnActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        searchName s=new searchName();
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        searchPhn s=new searchPhn();
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void showDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDataActionPerformed
        // TODO add your handling code here:
        try{
            table.showData();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_showDataActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }//GEN-LAST:event_exitActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        doctor d=new doctor();
        d.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        treatment t=new treatment();
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void itemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRefreshActionPerformed
        // TODO add your handling code here:
        this.refreshFront();
        
    }//GEN-LAST:event_itemRefreshActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        searchDate d=new searchDate();
        d.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void itemYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemYesActionPerformed
        // TODO add your handling code here:
        searchActivity sa=new searchActivity();
        sa.setVisible(true);
        String sql="SELECT *\n" +
        "FROM activity\n" +
        "WHERE DAY(date) = DAY(CURRENT_DATE())-1;";
        sa.setT("Yesterday");
        try{
            sa.tableRun(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        sa.changeText();
    }//GEN-LAST:event_itemYesActionPerformed

    private void itemTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTodayActionPerformed
        // TODO add your handling code here:
        searchActivity sa=new searchActivity();
        sa.setVisible(true);
        String sql="SELECT *\n" +
        "FROM activity\n" +
        "WHERE DAY(date) = DAY(CURRENT_DATE());";
        sa.setT("Today");
        try{
            sa.tableRun(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        sa.changeText();
        
    }//GEN-LAST:event_itemTodayActionPerformed

    private void itemMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMonthActionPerformed
        // TODO add your handling code here:
        searchActivity sa=new searchActivity();
        sa.setVisible(true);
        String sql="SELECT *\n" +
        "FROM activity\n" +
        "WHERE MONTH(date) = MONTH(CURRENT_DATE())\n" +
        "AND YEAR(date) = YEAR(CURRENT_DATE());";
        sa.setT("This Month in AD");

        try{
            sa.tableRun(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        sa.changeText();
    }//GEN-LAST:event_itemMonthActionPerformed

    private void itemYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemYearActionPerformed
        // TODO add your handling code here:
        searchActivity sa=new searchActivity();
        sa.setVisible(true);
        String sql="SELECT *\n" +
        "FROM activity\n" +
        "WHERE YEAR(date) = YEAR(CURRENT_DATE());";
        sa.setT("This year in AD");
        try{
            sa.tableRun(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        sa.changeText();
    }//GEN-LAST:event_itemYearActionPerformed

    private void item7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item7ActionPerformed
        // TODO add your handling code here:
        searchActivity sa=new searchActivity();
        sa.setVisible(true);
        String sql="SELECT * FROM activity WHERE date >= ( CURDATE() - INTERVAL 7 DAY );";
        sa.setT("Last 7 days");

        try{
            sa.tableRun(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        sa.changeText();
    }//GEN-LAST:event_item7ActionPerformed

    private void itemManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemManActionPerformed
        // TODO add your handling code here:
        
        JPanel addPanel = new JPanel();
//        addPanel.setBounds(200, 200, 600, 600);
        JTextField d=new JTextField(15);
        addPanel.add(new JLabel("N: "));
        addPanel.add(d, BorderLayout.NORTH);
        int result = JOptionPane.showConfirmDialog(null, addPanel,"Enter Last N number of day!", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            searchActivity sa=new searchActivity();
            sa.setVisible(true);
            int temp=Integer.parseInt(d.getText());
            String sql="SELECT * FROM activity WHERE date >= ( CURDATE() - INTERVAL "+temp+" DAY );";
            try{
                sa.tableRun(sql);
            }catch(Exception e){
                System.out.println(e);
            }
            sa.changeText();
            
        }

    }//GEN-LAST:event_itemManActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        this.Backupdbtosql();
            
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        JTextField txtF = new JTextField(15);
       
        JPanel addPanel = new JPanel();
        addPanel.add(new JLabel("Restore file:"));
        addPanel.add(txtF);

        int result = JOptionPane.showConfirmDialog(null, addPanel, 
                 "Data Restore File name", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
//            System.out.println(txtF.getText());
            this.Restoredbfromsql(txtF.getText());
        }
        this.refreshFront();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new front().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField contno;
    private javax.swing.JMenuItem entry;
    private javax.swing.JMenuItem exit;
    private javax.swing.JTextField firstn;
    private javax.swing.JMenuItem item7;
    private javax.swing.JMenuItem itemMan;
    private javax.swing.JMenuItem itemMonth;
    private javax.swing.JMenuItem itemRefresh;
    private javax.swing.JMenuItem itemToday;
    private javax.swing.JMenuItem itemYear;
    private javax.swing.JMenuItem itemYes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JTextField lastn;
    private javax.swing.JTextField recReg;
    private javax.swing.JTextField regno;
    private javax.swing.JButton searchbtn;
    private javax.swing.JMenuItem showData;
    // End of variables declaration//GEN-END:variables
}
