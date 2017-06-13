package kaganga;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aulia
 */
public class FormPreprocessing extends javax.swing.JInternalFrame {

    BufferedImage imgColor, imgGray, imgBin, imgEdge;
    DefaultTableModel tableModel;

    /**
     * Creates new form FormPreprocessing
     */
    public FormPreprocessing() {
        initComponents();
        tableModel = (DefaultTableModel) tableData.getModel();
        initData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOpen = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();
        btnGrayscale = new javax.swing.JButton();
        btnBinary = new javax.swing.JButton();
        btnEdgeDetection = new javax.swing.JButton();
        btnChainCode = new javax.swing.JButton();
        btnNormalisasi = new javax.swing.JButton();
        txtNormalisasi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChainCode = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        lbTarget = new javax.swing.JLabel();
        cboTarget = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();

        setClosable(true);
        setTitle("Pre Processing");

        btnOpen.setText("Buka Citra");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        lbImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGrayscale.setText("Citra Keabuan");
        btnGrayscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrayscaleActionPerformed(evt);
            }
        });

        btnBinary.setText("Citra Biner");
        btnBinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBinaryActionPerformed(evt);
            }
        });

        btnEdgeDetection.setText("Deteksi Tepi");
        btnEdgeDetection.setVisible(false);
        btnEdgeDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdgeDetectionActionPerformed(evt);
            }
        });

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

        txtNormalisasi.setEnabled(false);

        txtChainCode.setBackground(new java.awt.Color(240, 240, 240));
        txtChainCode.setColumns(20);
        txtChainCode.setLineWrap(true);
        txtChainCode.setRows(5);
        txtChainCode.setPreferredSize(new java.awt.Dimension(300, 94));
        jScrollPane1.setViewportView(txtChainCode);

        btnSimpan.setText("Simpan Data");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        lbTarget.setText("Target Aksara");

        cboTarget.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTarget.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aksara A", "Aksara E", "Aksara E-", "Aksara EU", "Aksara I", "Aksara O", "Aksara U" }));

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Aksara", "Data", "Target"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tableData.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableData);
        if (tableData.getColumnModel().getColumnCount() > 0) {
            tableData.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableData.getColumnModel().getColumn(0).setMaxWidth(80);
            tableData.getColumnModel().getColumn(1).setPreferredWidth(80);
            tableData.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGrayscale, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBinary, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnChainCode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdgeDetection, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTarget)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGrayscale, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBinary, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChainCode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdgeDetection, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNormalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTarget)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setImageIcon(BufferedImage imgSource){
        Image fitImg    = imgSource.getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon  = new ImageIcon(fitImg);
        lbImage.createImage(icon.getIconWidth(), icon.getIconHeight());
        lbImage.setIcon(icon);    
    }
    
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
                //               int red      = (int)(c.getRed() * 0.299);
                //               int green    = (int)(c.getGreen() * 0.587);
                //               int blue     = (int)(c.getBlue() *0.114);
                //               int gray     = red + green + blue;

                int red      = (int) c.getRed();
                int green    = (int) c.getGreen();
                int blue     = (int) c.getBlue();
                int gray     = (red + green + blue) / 3;

                //               if(gray < 0) gray = 0;
                //               if(gray > 255) gray = 255;
                Color newColor = new Color(gray, gray, gray);
                imgGray.setRGB(j,i,newColor.getRGB());
            }
        }

        setImageIcon(imgGray);
    }//GEN-LAST:event_btnGrayscaleActionPerformed

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

    private void btnEdgeDetectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdgeDetectionActionPerformed
        //create the detector
        CannyEdgeDetector detector = new CannyEdgeDetector();

        //adjust its parameters as desired
        detector.setLowThreshold(0.5f);
        detector.setHighThreshold(1f);

        //apply it to an image
        detector.setSourceImage(imgBin);
        detector.process();
        imgEdge = detector.getEdgesImage();

        setImageIcon(imgEdge);
    }//GEN-LAST:event_btnEdgeDetectionActionPerformed

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
            JOptionPane.showInternalMessageDialog(this, "Error:"+ex, "Pre Processing", JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showInternalMessageDialog(this, "Error:"+ex, "Pre Processing", JOptionPane.ERROR_MESSAGE);
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

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        KagangaDB db = new KagangaDB();
        db.connect();
        int id = db.insert(String.format("%20s", txtNormalisasi.getText()).replace(' ', '0'), cboTarget.getSelectedIndex());
        if(id > -1){
            Object[] row = new Object[4];
            row[0]       = tableModel.getRowCount() + 1;
            row[1]       = cboTarget.getSelectedItem().toString().replace("Aksara ", "");
            row[2]       = String.format("%20s", txtNormalisasi.getText()).replace(' ', '0');
            row[3]       = String.format("%3s", Integer.toBinaryString(cboTarget.getSelectedIndex())).replace(' ', '0');
            tableModel.addRow(row);
            JOptionPane.showInternalMessageDialog(this, "Data Tersimpan!", "Pre Processing", JOptionPane.INFORMATION_MESSAGE);
        }
        db.disconnect();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void initData(){
        try {
            KagangaDB db = new KagangaDB();
            db.connect();
            ResultSet rs = db.getAll();
            String[] aksara = {"A", "E", "E-", "EU", "I", "O", "U"};
            int num = 0;
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0]       = ++num;
                row[1]       = aksara[rs.getInt("target")];
                row[2]       = String.format("%20s", rs.getString("data"));
                row[3]       = String.format("%3s", Integer.toBinaryString(rs.getInt("target"))).replace(' ', '0');
                tableModel.addRow(row);
            }
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBinary;
    private javax.swing.JButton btnChainCode;
    private javax.swing.JButton btnEdgeDetection;
    private javax.swing.JButton btnGrayscale;
    private javax.swing.JButton btnNormalisasi;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cboTarget;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbTarget;
    private javax.swing.JTable tableData;
    private javax.swing.JTextArea txtChainCode;
    private javax.swing.JTextField txtNormalisasi;
    // End of variables declaration//GEN-END:variables
}
