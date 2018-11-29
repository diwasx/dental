/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombheri;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.Vector;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Lanaya
 */
public class table {
    
    
    public static void showData() throws Exception{
    // The Connection is obtained
    
        String sql="select * from customer order by date desc,regno desc,firstn limit 100;";
        sqlConn sc=new sqlConn();
        ResultSet rs = sc.stmt.executeQuery(sql);

        // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs));
        table.setRowSelectionAllowed(false);
        //        int modelRow = convertRowIndexToModel(row);
        table.setAutoCreateRowSorter(true);
        //        int modelRow = convertRowIndexToModel(row);
        table.setAutoCreateRowSorter(true);
//        table.setPreferredSize(new Dimension(1800,1800));
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(180);

        
        // Closes the Connection
        
        JOptionPane.showMessageDialog(null, new JScrollPane(table));        

    }
    
    public static void searchN(String a,String b) throws Exception{
    // The Connection is obtained
    
        String sql="select * from customer where firstn='"+a+"' and lastn='"+b+"';";
        sqlConn sc=new sqlConn();
        ResultSet rs = sc.stmt.executeQuery(sql);

        // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs));
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(180);      

        // Closes the Connection

        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        

    }
    
    public static void searchP(String a) throws Exception{
    // The Connection is obtained
    
        String sql="select * from customer where contno="+a+";";
        sqlConn sc=new sqlConn();
        ResultSet rs = sc.stmt.executeQuery(sql);

        // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs));
        //        int modelRow = convertRowIndexToModel(row);
        table.setAutoCreateRowSorter(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(180);
        // Closes the Connection

        JOptionPane.showMessageDialog(null, new JScrollPane(table));

    }
    public static void searchD(String a) throws Exception{
    // The Connection is obtained
    
        String sql="select * from customer where date='"+a+"';";
        sqlConn sc=new sqlConn();
        ResultSet rs = sc.stmt.executeQuery(sql);

        // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs));
        //        int modelRow = convertRowIndexToModel(row);
        table.setAutoCreateRowSorter(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(180);
        // Closes the Connection

        JOptionPane.showMessageDialog(null, new JScrollPane(table));

    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
//        for (int column = 1; column <= columnCount; column++) {
//            columnNames.add(metaData.getColumnName(column));
//            
//        }
            columnNames.add("Date");
            columnNames.add("regNo");
            columnNames.add("FName");
            columnNames.add("LName");
            columnNames.add("age");
            columnNames.add("Phone");
            columnNames.add("Address");
        
        

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
}
