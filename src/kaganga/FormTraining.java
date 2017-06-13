/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaganga;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author aulia
 */
public class FormTraining extends javax.swing.JInternalFrame {
    
    public BackPropagation BP;
    private KagangaDB kgDB;
    BufferedImage imgColor, imgGray, imgBin, imgEdge;

//    private double data_input[][];
    private double data_input[][] = {
            {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1}, // a
            {1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1}, // b
            {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1}, // c
            {1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1}, // d
            {1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1}, // e
            {1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1}, // f
            {-1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1}, // g
            {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1}, // h
            {-1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, -1}, // i
            {-1, -1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1}, // j
            {1, -1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 1}, // k
            {1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1}, // l
            {1, -1, -1, -1, 1, 1, 1, -1, 1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1}, // m
            {1, -1, -1, -1, 1, 1, 1, -1, -1, 1, 1, -1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1}, // n
            {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1}, // o
            {1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1}, // p
            {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, 1, -1, 1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1}, // q
            {1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 1}, // r
            {-1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, -1}, // s
            {1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1}, // t
            {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1}, // u
            {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1}, // v
            {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1}, // w
            {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1}, // x
            {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1}, // y
            {1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1} // z
        };

//    private double data_target[][];
    private double data_target[][] = {
            {-1, -1, -1, -1, -1}, //a
            {-1, -1, -1, -1,  1}, //b
            {-1, -1, -1,  1, -1}, //c
            {-1, -1, -1,  1,  1}, //d
            {-1, -1,  1, -1, -1}, //e
            {-1, -1,  1, -1,  1}, //f
            {-1, -1,  1,  1, -1}, //g
            {-1, -1,  1,  1,  1}, //h
            {-1,  1, -1, -1, -1}, //i
            {-1,  1, -1, -1,  1}, //j
            {-1,  1, -1,  1, -1}, //k
            {-1,  1, -1,  1,  1}, //l
            {-1,  1,  1, -1, -1}, //m
            {-1,  1,  1, -1,  1}, //n
            {-1,  1,  1,  1, -1}, //o
            {-1,  1,  1,  1,  1}, //p
            { 1, -1, -1, -1, -1}, //q
            { 1, -1, -1, -1,  1}, //r
            { 1, -1, -1,  1, -1}, //s
            { 1, -1, -1,  1,  1}, //t
            { 1, -1,  1, -1, -1}, //u
            { 1, -1,  1, -1,  1}, //v
            { 1, -1,  1,  1, -1}, //w
            { 1, -1,  1,  1,  1}, //x
            { 1,  1, -1, -1, -1}, //y
            { 1,  1, -1, -1,  1}, //z
        };
            
    
    /**
     * Creates new form FormTraining
     */
    public FormTraining() {
        initComponents();
        initData();
//            System.out.println("// Data Input");
//            for(int i=0; i < this.data_input.length; i++)
//                System.out.println(Arrays.toString(this.data_input[i]));
//
//            System.out.println("// Data Target");
//            for(int i=0; i < this.data_target.length; i++)
//                System.out.println(Arrays.toString(this.data_target[i]));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAlpha = new javax.swing.JLabel();
        textAlpha = new javax.swing.JTextField();
        jLabelTeta = new javax.swing.JLabel();
        textTeta = new javax.swing.JTextField();
        jLabelEpoch = new javax.swing.JLabel();
        textEpoch = new javax.swing.JTextField();
        jLabelMSE = new javax.swing.JLabel();
        textMSE = new javax.swing.JTextField();
        btnLatih = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listActivity = new javax.swing.JList<>();
        jLabelActivity = new javax.swing.JLabel();
        btnStop = new javax.swing.JButton();
        btnUji = new javax.swing.JButton();
        btnGrayscale = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnBinary = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();
        lbHasil = new javax.swing.JLabel();
        btnChainCode = new javax.swing.JButton();
        btnNormalisasi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChainCode = new javax.swing.JTextArea();
        txtNormalisasi = new javax.swing.JTextField();
        jLabelActivity1 = new javax.swing.JLabel();
        jLabelActivity2 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Training");

        jLabelAlpha.setText("Learning Rate (Alpha)");

        textAlpha.setText("0.1");

        jLabelTeta.setText("Treshold (Teta)");

        textTeta.setText("0.0001");

        jLabelEpoch.setText("Epoch");

        textEpoch.setEditable(false);
        textEpoch.setText("0");

        jLabelMSE.setText("MSE");

        textMSE.setEditable(false);
        textMSE.setText("0");

        btnLatih.setText("Latih!");
        btnLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLatihActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listActivity);

        jLabelActivity.setText("Log Aktivitas");

        btnStop.setText("Stop!");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnUji.setText("Uji Data");
        btnUji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUjiActionPerformed(evt);
            }
        });

        btnGrayscale.setText("Citra Keabuan");
        btnGrayscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrayscaleActionPerformed(evt);
            }
        });

        btnOpen.setText("Buka Citra");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnBinary.setText("Citra Biner");
        btnBinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBinaryActionPerformed(evt);
            }
        });

        lbImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbHasil.setFont(new java.awt.Font("Segoe UI Semibold", 0, 200)); // NOI18N
        lbHasil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHasil.setToolTipText("");
        lbHasil.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnChainCode.setText("Chain Code");
        btnChainCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChainCodeActionPerformed(evt);
            }
        });

        btnNormalisasi.setText("Normalisasi");
        btnNormalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNormalisasiActionPerformed(evt);
            }
        });

        txtChainCode.setBackground(new java.awt.Color(240, 240, 240));
        txtChainCode.setColumns(20);
        txtChainCode.setLineWrap(true);
        txtChainCode.setRows(5);
        txtChainCode.setPreferredSize(new java.awt.Dimension(300, 94));
        jScrollPane2.setViewportView(txtChainCode);

        txtNormalisasi.setEnabled(false);

        jLabelActivity1.setText("Pengujian");

        jLabelActivity2.setText("Hasil Uji");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTeta)
                        .addComponent(textTeta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelEpoch)
                        .addComponent(jLabelMSE)
                        .addComponent(textAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textMSE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLatih, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textEpoch, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrayscale, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBinary, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChainCode, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUji, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelActivity)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelActivity1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelActivity2)
                            .addComponent(lbHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBinary, btnChainCode, btnGrayscale, btnLatih, btnNormalisasi, btnOpen, btnStop, btnUji, jLabelAlpha, jLabelEpoch, jLabelMSE, jLabelTeta, jScrollPane2, textAlpha, textEpoch, textMSE, textTeta, txtNormalisasi});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTeta, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEpoch, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textEpoch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMSE, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textMSE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLatih, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop))
                    .addComponent(jScrollPane1))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGrayscale)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBinary)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChainCode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNormalisasi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUji, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelActivity1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelActivity2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initData(){
        kgDB = new KagangaDB();
        kgDB.connect();
        try {
            ResultSet rs;

            // get input
            rs = kgDB.getAll();
            List<String> di = new ArrayList<String>();
            List<String> tg = new ArrayList<String>();
            while(rs.next()){ 
                di.add(rs.getString("data")
                    .substring(rs.getString("data").length() - 20));
                tg.add(String.format("%3s", 
                    Integer.toBinaryString(rs.getInt("target"))).replace(' ', '0'));
            }
               
            this.data_input  = new double[di.size()][20];
            for(int i=0; i < this.data_input.length; i++)
                for(int j=0; j < this.data_input[i].length; j++)
                    this.data_input[i][j] = (double) (di.get(i).charAt(j) - '0');
            
            this.data_target = new double[tg.size()][3];
            for(int i = 0; i < this.data_target.length; i++)
                for(int j = 0; j < this.data_target[i].length; j++){
                    this.data_target[i][j] = (double) (tg.get(i).charAt(j) - '0');
                    this.data_target[i][j] = (this.data_target[i][j]==0)?-1:1;
                }
            
            System.out.println("// Data Input");
            for (double[] dp : this.data_input) {
                System.out.println(Arrays.toString(dp));
            }

            System.out.println("// Data Target");
            for (double[] dt : this.data_target) {
                System.out.println(Arrays.toString(dt));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormTraining.class.getName()).log(Level.SEVERE, null, ex);
        }
        kgDB.disconnect();    
    }
    private void btnLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLatihActionPerformed
            BP = new BackPropagation();
            BP.setInput(data_input);
            BP.normalisasi_input();
            BP.setTarget(data_target);
            BP.setAlpha(Double.valueOf(textAlpha.getText()));
            BP.setTeta(Double.valueOf(textTeta.getText()));
            BP.setHidden(15);
            BP.setOutput(data_target[0].length);
            BP.setActivity(textEpoch, textMSE, listActivity);
            BP.setButton(btnLatih, btnStop);
            BP.start();    
    }//GEN-LAST:event_btnLatihActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if(BP != null) BP.trainStop();
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnUjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUjiActionPerformed
        String datauji = String.format("%20s", txtNormalisasi.getText()).replace(' ', '0');
        double datatest[] = new double[20];
        for(int i=0; i < datauji.length(); i++)
            datatest[i] = (double) (datauji.charAt(i) - '0');
        
        double[] testresult = BP.test(datatest); int indeks = 0;
        for (int i=0; i<testresult.length; ++i){ 
            int n = (testresult[i] < 1)? 0: 1;
            indeks += n * Math.pow(2, 2-i);
            // System.err.println("P: "+indeks);
        }
//        int resultindex = Arrays.asList(this.data_target).indexOf(testresult);
        String[] aksara = {"A", "E", "E-", "EU", "I", "O", "U"};
        String huruf = (indeks < 0 || indeks > 6)? "" : aksara[indeks];
        lbHasil.setText(huruf);
            
                
        JOptionPane.showInternalMessageDialog(this, Arrays.toString(testresult), "Uji Data", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(Arrays.toString(testresult));
    }//GEN-LAST:event_btnUjiActionPerformed

    private void setImageIcon(BufferedImage imgSource){
        Image fitImg    = imgSource.getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon  = new ImageIcon(fitImg);
        lbImage.createImage(icon.getIconWidth(), icon.getIconHeight());
        lbImage.setIcon(icon);    
    }    
    
    private void btnBinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBinaryActionPerformed
        int width   = imgGray.getWidth();
        int height  = imgGray.getHeight();
        imgBin      = imgGray;

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color c      = new Color(imgGray.getRGB(j, i));
                int red      = (int) c.getRed();
                int green    = (int) c.getGreen();
                int blue     = (int) c.getBlue();
                int gray     = (red + green + blue) / 3;
                if(gray <= 128) gray = 0;
                if(gray > 128) gray = 255;
                Color newColor = new Color(gray, gray, gray);
                imgBin.setRGB(j,i,newColor.getRGB());
            }
        }

        setImageIcon(imgBin);
    }//GEN-LAST:event_btnBinaryActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")+"/Documents/Habibi/Kuliah/Pengenalan Pola/Kaganga/Kaganga BMP"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                imgColor = ImageIO.read(selectedFile);
                setImageIcon(imgColor);
                txtChainCode.setText("");
                txtNormalisasi.setText("");
            } catch (IOException ex) {
                Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnGrayscaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrayscaleActionPerformed
        int width   = imgColor.getWidth(btnOpen);
        int height  = imgColor.getHeight(btnOpen);
        imgGray     = imgColor;

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color c      = new Color(imgColor.getRGB(j, i));
                int red      = (int) c.getRed();
                int green    = (int) c.getGreen();
                int blue     = (int) c.getBlue();
                int gray     = (red + green + blue) / 3;
                Color newColor = new Color(gray, gray, gray);
                imgGray.setRGB(j,i,newColor.getRGB());
            }
        }

        setImageIcon(imgGray);
    }//GEN-LAST:event_btnGrayscaleActionPerformed

    private void btnChainCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChainCodeActionPerformed
        ChainCode c;
        try {
            c = new ChainCode(imgBin);

            // get key coordinates
            c.firstPixel();
            c.lastPixel();

            // generate chain codes
            // get coordinates of first border pixel after initial
            int[] index = new int[2];
            index = c.borderNeighbors(c.begin[0], c.begin[1]);
            c.chainCodes(index[0], index[1]);
            txtChainCode.setText(c.chainResult);

        } catch(NullPointerException ex){
            JOptionPane.showInternalMessageDialog(this, "Error: "+ex, "JST Testing", JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showInternalMessageDialog(this, "Error: "+ex, "JST Testing", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnChainCodeActionPerformed

    private void btnNormalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNormalisasiActionPerformed
        char[] chainArray = txtChainCode.getText().toCharArray();

        List<Integer> frek = new ArrayList<Integer>();
        int temp = 0;
        int t_text = chainArray.length;
        int t_array = 2;
        int[][] gabungan = new int[t_text][t_array];
        for (int i = 0; i < t_text; i++) {
            String tampung_arah = null;
            int tampung_frek = 0;
            tampung_arah = String.valueOf(chainArray[i]);
            if (i < t_text - 1) {
                if (chainArray[i] != chainArray[i + 1]) {
                    temp++;
                    if (i < 1) {
                        frek.add(i, 1);
                        tampung_frek = 1;
                    } else {
                        frek.add(i, temp);
                        tampung_frek = temp;
                    }
                    temp = 0;
                } else {
                    frek.add(i, 0);
                    tampung_frek = 0;
                    temp++;
                }
            } else {
                temp++;
                frek.add(i, temp);
                tampung_frek = temp;
            }
            gabungan[i][0] = Integer.parseInt(tampung_arah);
            gabungan[i][1] = tampung_frek;
        }
        for (int i = 0; i < gabungan.length; i++) {
            System.out.print(gabungan[i][0]);
            System.out.print(gabungan[i][1]);
        }
        System.out.println();

        //hassil eliminasi frek 0& 1
        List<Integer> p_elim = new ArrayList<>();
        int i_eli = 0;
        int[][] hasil_elim = new int[t_text][i_eli];
        for (int k = 0; k < gabungan.length; k++) {
            if (!frek.isEmpty() && frek.get(k) > 1) {
                p_elim.add(i_eli, k);
                i_eli++;
            }
        }

        int elim_gabungan[][] = new int[p_elim.size()][2];
        for (int j = 0; j < p_elim.size(); j++) {
            elim_gabungan[j] = gabungan[p_elim.get(j)];
        }

        System.out.println("----------------------------------eliminasi-------------------");

        for (int z = 0; z < elim_gabungan.length; z++) {
            System.out.println(elim_gabungan[z][0] + " " + elim_gabungan[z][1]);
        }

        System.out.println("-------------------------------------------eliminasi-----------------");
        int akumulasi_angkaarah = 0;
        int akumulasi_frekuensi = 0;
        int sama = 0;
        List<Integer> angkaarah = new ArrayList<>();
        List<Integer> frekuensi = new ArrayList<>();
        for (int y = 0; y < elim_gabungan.length - 1; y++) {
            //           System.out.println(elim_gabungan[y][0]+" : "+elim_gabungan[y+1][0]);
            if (elim_gabungan[y][0] == elim_gabungan[y + 1][0]) {
                akumulasi_angkaarah = elim_gabungan[y][0];
                akumulasi_frekuensi += elim_gabungan[y][1];
                sama += 1;
            } else {
                angkaarah.add(elim_gabungan[y][0]);
                if (sama > 0) {
                    akumulasi_frekuensi += elim_gabungan[y][1];
                    frekuensi.add(akumulasi_frekuensi);
                } else {
                    frekuensi.add(elim_gabungan[y][1]);
                }
                akumulasi_frekuensi = 0;
                sama = 0;
            }
            if ((y == elim_gabungan.length - 2) && (sama == 0)) {
                angkaarah.add(elim_gabungan[y + 1][0]);
                akumulasi_frekuensi = elim_gabungan[y + 1][1];
                //                System.out.println(akumulasi_frekuensi);
                frekuensi.add(akumulasi_frekuensi);
            }
            if ((y == elim_gabungan.length - 2) && (sama > 0)) {
                angkaarah.add(elim_gabungan[y + 1][0]);
                akumulasi_frekuensi += elim_gabungan[y + 1][1];
                frekuensi.add(akumulasi_frekuensi);
            }
        }

        System.out.println("angkaarah : " + angkaarah);
        System.out.println("frekuensi : " + frekuensi);

        //normalissi
        float jumlah_frek = 0;
        for (int m = 0; m < frekuensi.size(); m++) {
            jumlah_frek += frekuensi.get(m);
        }

        //perhitungan frek
        List<Float> hitung_normal = new ArrayList<>();
        List<Integer> hitung_normal_bulat = new ArrayList<>();
        for (int n = 0; n < frekuensi.size(); n++) {
            float frekdouble = frekuensi.get(n);
            hitung_normal.add((frekdouble / jumlah_frek) * 20);
            hitung_normal_bulat.add(Math.round((frekdouble / jumlah_frek) * 20));
        }
        System.out.println("angka arah : " + angkaarah);
        System.out.println("frekuensi : " + hitung_normal);
        System.out.println("frekuensi bulat : " + hitung_normal_bulat);

        String hasil_akhir = "";
        for (int s = 0; s < angkaarah.size(); s++) {
            for (int t = 0; t < hitung_normal_bulat.get(s); t++) {
                hasil_akhir += angkaarah.get(s);
            }
        }
        txtNormalisasi.setText(hasil_akhir);
        System.out.println("hasil normalisasi : " + hasil_akhir);
    }//GEN-LAST:event_btnNormalisasiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBinary;
    private javax.swing.JButton btnChainCode;
    private javax.swing.JButton btnGrayscale;
    private javax.swing.JButton btnLatih;
    private javax.swing.JButton btnNormalisasi;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnUji;
    private javax.swing.JLabel jLabelActivity;
    private javax.swing.JLabel jLabelActivity1;
    private javax.swing.JLabel jLabelActivity2;
    private javax.swing.JLabel jLabelAlpha;
    private javax.swing.JLabel jLabelEpoch;
    private javax.swing.JLabel jLabelMSE;
    private javax.swing.JLabel jLabelTeta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbHasil;
    private javax.swing.JLabel lbImage;
    private javax.swing.JList<String> listActivity;
    private javax.swing.JTextField textAlpha;
    private javax.swing.JTextField textEpoch;
    private javax.swing.JTextField textMSE;
    private javax.swing.JTextField textTeta;
    private javax.swing.JTextArea txtChainCode;
    private javax.swing.JTextField txtNormalisasi;
    // End of variables declaration//GEN-END:variables
}
