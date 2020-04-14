/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuispbo;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class DataKaryawan extends JFrame{

static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kuis";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;       
    
       
        JLabel lNIK = new JLabel("NIK ");
        JTextField tfNIK = new JTextField();
        JLabel lNama= new JLabel("NAMA");
        JTextField tfNama = new JTextField();    
       
        JLabel lJenkel = new JLabel("JENKEL ");
        JRadioButton rbPria = new JRadioButton(" Laki-Laki ");
        JRadioButton rbWanita = new JRadioButton(" Perempuan ");
        
        JLabel lGolongan = new JLabel("GOLONGAN ");
        JTextField tfGolongan = new JTextField();
        JLabel lJabatan = new JLabel("JABATAN");
        JLabel tfJabatan = new JLabel("");
        JLabel lTunjangan = new JLabel("TUNJANGAN ");
        JLabel tfTunjangan = new JLabel("");
        JLabel lGaji = new JLabel("GAJI");
        JLabel tfGaji = new JLabel("");

        JButton btnSimpan = new JButton("SIMPAN");
        JButton btnHapus = new JButton("HAPUS");
        JButton btnKeluar = new JButton("KELUAR");
        JButton btnRefresh = new JButton("REFRESH");
        JButton btnCari = new JButton("CARI");


        JTable table;
        DefaultTableModel tableModel;
        JScrollPane scrollPane;
        Object namaKolom[] = {"NIK","NAMA","JENKEL","GOLONGAN","JABATAN","TUNJANGAN","GAJI"};


        public DataKaryawan(){
            try{
                    Class.forName(JDBC_DRIVER);
                    koneksi = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
                    System.out.println("Koneksi Berhasil");
                }catch(ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Koneksi Gagal");
                }  

        tableModel = new DefaultTableModel(namaKolom,0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(800, 600);

       
        add(lNIK);
        lNIK.setBounds(20,100, 100, 20);
        add(tfNIK);
        tfNIK.setBounds(120, 100, 100, 20);
        add(lNama);
        lNama.setBounds(20,130, 100, 20);
        add(tfNama);
        tfNama.setBounds(120, 130, 100, 20);
        add(lJenkel);
        lJenkel.setBounds(20, 160,100, 20);
        add(rbPria);
        rbPria.setBounds(120, 160,100, 20);
        add(rbWanita);
        rbWanita.setBounds(240, 160,100, 20);
        add(lGolongan);
        lGolongan.setBounds(20,190, 100, 20);
        add(tfGolongan);
        tfGolongan.setBounds(120, 190, 100, 20);
        add(lJabatan);
        lJabatan.setBounds(20,220, 100, 20);
        add(tfJabatan);
        tfJabatan.setBounds(120, 220, 100, 20);
        add(lTunjangan);
        lTunjangan.setBounds(20,250, 100, 20);
        add(tfTunjangan);
        tfTunjangan.setBounds(120, 250, 100, 20);
        add(lGaji);
        lGaji.setBounds(20,280, 100, 20);
        add(tfGaji);
        tfGaji.setBounds(120, 280, 100, 20);

         add(btnSimpan);
        btnSimpan.setBounds(450,120,80,20);
        add(btnHapus);
        btnHapus.setBounds(450,150,80,20);
        add(btnCari);
        btnCari.setBounds(450,180,80,20);
        add(btnKeluar);
        btnKeluar.setBounds(450,210,80,20);
        add(btnRefresh);
        btnRefresh.setBounds(450,240,80,20);


        add(scrollPane);
        scrollPane.setBounds(20,320,760,200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        tfGolongan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String a = tfGolongan.getText();  
                        updateFieldState(a);
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String a = tfGolongan.getText();  
                        updateFieldState(a);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        String a = tfGolongan.getText();
                        //tfJabatan.setText(a);
                        updateFieldState(a);
                    }
                    protected void updateFieldState(String a) {

                        int Cost=0;
                        int Tunjangan=0;
                        String Jabatan=null;
                        if (a == "a") {
                            Jabatan = "Direkur";
                            Tunjangan = 100000;
                            Cost = 2000000;
                        } else  if (a == "b") {
                             Jabatan = "Karyawan";
                             Tunjangan = 50000;
                             Cost = 1800000;
                        } else {
                             Jabatan = "Magang";
                             Tunjangan = 0;
                             Cost = 500000;
                        }

                        int Gaji = Cost+Tunjangan;

                        tfJabatan.setText(Jabatan);
                        tfTunjangan.setText(Integer.toString(Tunjangan));
                        tfGaji.setText(Integer.toString(Gaji));
                        }

                });



        if (this.getBanyakData() != 0) { // kalau banyak datanya tidfak sama dengan 0
                    String dataKaryawan[][] = this.readKaryawan(); //ambil method readMahasiswa di model
                    table.setModel((new JTable(dataKaryawan, namaKolom)).getModel());
                    //menampilkan data yang ada didalam database ke tabel
                } else {
                    JOptionPane.showMessageDialog(null, "Data Tidak Ada");
                }
        btnSimpan.addActionListener((ActionEvent e) -> {
                    if (tfNIK.getText().equals("") ) {
                        JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
                    } else {
                        String nik = tfNIK.getText();
                        String nama = tfNama.getText();
                        String jenkel = null;
                        if (rbWanita.isSelected()) {
                            jenkel = "Perempuan";
                        } else if (rbPria.isSelected() ) {
                            jenkel = "Laki laki";
                        }
                        String golongan = tfGolongan.getText();
                        String jabatan = tfJabatan.getText();
                        String tunjangan = tfTunjangan.getText();
                        String gaji = tfGaji.getText();


                        this.insertKaryawan(nik, nama, jenkel, golongan, jabatan, tunjangan, gaji);
                        String dataKaryawan[][] = readKaryawan();
                        table.setModel(new JTable(dataKaryawan,namaKolom).getModel());
                    }
                });


        table.addMouseListener(new MouseAdapter() {
                   @Override
                   public void mouseClicked(MouseEvent e){ 
                       int baris = table.getSelectedRow();
                       int kolom = table.getSelectedColumn(); 
                       String dataterpilih = table.getValueAt(baris, 0).toString();
                       btnHapus.addActionListener((ActionEvent f) -> {
                          deleteKaryawan(dataterpilih);
                          String dataKaryawan[][] = readKaryawan();
                        table.setModel(new JTable(dataKaryawan,namaKolom).getModel());
                        });

                   }
                });


        btnRefresh.addActionListener((ActionEvent e) -> {
                  tfNIK.setText("");
                  tfNama.setText("");
                  tfGolongan.setText("");
                  tfJabatan.setText("");
                  tfTunjangan.setText("");
                  tfGaji.setText("");

                });



        btnKeluar.addActionListener((ActionEvent e) -> {
                  new GUI();
                   dispose();
                });

        }

        int getBanyakData() {
                int jmlData = 0;
                try{
                    statement = koneksi.createStatement();
                    String query = "SELECT * from `karyawan`";
                    ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next()){
                        jmlData++;
                    }
                    return jmlData;
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                    System.out.println("SQL error");
                    return 0;
                }
            }

        String[][] readKaryawan() {
                try{
                    int jmlData = 0;
                    String data[][]=new String[getBanyakData()][7];
                    String query = "Select * from `karyawan`";
                    ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next()){
                        data[jmlData][0] = resultSet.getString("Nik");
                        data[jmlData][1] = resultSet.getString("Namakry");
                        data[jmlData][2] = resultSet.getString("Jenkel");
                        data[jmlData][3] = resultSet.getString("Golongan");
                        data[jmlData][4] = resultSet.getString("Jabatan");
                        data[jmlData][5] = resultSet.getString("Tunjangan");
                        data[jmlData][6] = resultSet.getString("Gaji");

                        jmlData++;
                    }
                    return data;
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                    System.out.println("SQL error");
                    return null;
                }
            }

        public void insertKaryawan(String nik, String namakry, String jenkel, String golongan, String jabatan, String tunjangan, String gaji) {
                try{
                    String query = "INSERT INTO `karyawan`(`Nik`,`Namakry`,`Jenkel`,`Golongan`,`Jabatan`,`Tunjangan`,`Gaji`) VALUES ('"+nik+"','"+namakry+"','"+jenkel+"', '"+golongan+"','"+jabatan+"','"+tunjangan+"','"+gaji+"')";
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query);
                System.out.println("Berhasil Ditambahkan");
                JOptionPane.showMessageDialog(null,"data berhasil ditambahkan");
                }catch(Exception sql){
                    System.out.println(sql.getMessage());
                    JOptionPane.showMessageDialog(null, sql.getMessage());
                }

            }

        void deleteKaryawan(String nik) {
                try{
                    String query = "DELETE FROM `karyawan` WHERE `Nik` = '"+nik+"'";
                    statement = koneksi.createStatement();
                    statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "berhasil dihapus" );
                }catch(SQLException sql){
                    System.out.println(sql.getMessage());
                }
            }

        }
