package kuispbo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Kuispbo {

    public static void main(String[] args) {
        
        GUI g = new GUI();
        /*new ViewBuku();*/
    }
   
}

class GUI extends JFrame {
      JButton btnAnggota= new JButton("Input Data Mhs");
      JButton btnBuku = new JButton("Input Data Buku");
      JButton btnKaryawan = new JButton("Input Data Karyawan");
      JButton btnPeminjaman = new JButton("Input Data Peminjaman");
   
   

   public GUI() {
setTitle("KUIS YAALLAH");
setDefaultCloseOperation(3);
setSize(440,350);
       
       
       
       
setLayout(null);

        add(btnAnggota);
        add(btnKaryawan);
        add(btnBuku);
        add(btnPeminjaman);
       
        
        btnAnggota.setBounds(20,20,180,60);
        btnBuku.setBounds (20,160,180,60);
        btnKaryawan.setBounds (220,20,180,60);
        btnPeminjaman.setBounds(220,160,180,60);
        setVisible(true);
        
        btnAnggota.addActionListener((ActionEvent e) -> {
           DataAnggota anggota = new DataAnggota();
           dispose();
        });
        
        btnBuku.addActionListener((ActionEvent e) -> {
           DataBuku buku = new DataBuku();
           dispose();
        });
        
        btnKaryawan.addActionListener((ActionEvent e) -> {
           DataKaryawan karyawan = new DataKaryawan();
           dispose();
        });
        
        btnPeminjaman.addActionListener((ActionEvent e) -> {
           DataPinjam pinjam = new DataPinjam();
           dispose();
        });
    }
}