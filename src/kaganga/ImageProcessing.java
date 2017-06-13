/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaganga;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aulia
 */
public class ImageProcessing {
    
    public BufferedImage toGrayscale(BufferedImage imgSource, BufferedImage imgResult){
        int width   = imgSource.getWidth();
        int height  = imgSource.getHeight();

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color c      = new Color(imgSource.getRGB(j, i));
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
                imgResult.setRGB(j,i,newColor.getRGB());
            }
        }
        
        return imgResult;
    }
    
    public BufferedImage toBinary(BufferedImage imgSource, BufferedImage imgResult){
        int width   = imgSource.getWidth();
        int height  = imgSource.getHeight();

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color c      = new Color(imgSource.getRGB(j, i));
                int red      = (int) c.getRed();
                int green    = (int) c.getGreen();
                int blue     = (int) c.getBlue();
                int gray     = (red + green + blue) / 3;
                if(gray <= 128) gray = 0;
                if(gray > 128) gray = 255;
                Color newColor = new Color(gray, gray, gray);
                imgResult.setRGB(j,i,newColor.getRGB());
            }
        }
        
        return imgResult;
    }
    
    public String getChainCode(BufferedImage imgSource){
        ChainCode c = new ChainCode(imgSource);
        // get key coordinates
        c.firstPixel();
        c.lastPixel();

        // generate chain codes
        // get coordinates of first border pixel after initial
        int[] index = new int[2];
        index = c.borderNeighbors(c.begin[0], c.begin[1]);
        c.chainCodes(index[0], index[1]);
        return c.chainResult;    
    }
    
    public String toNormalChain(char[] chainArray){
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
        // for (int i = 0; i < gabungan.length; i++) {
        //    System.out.print(gabungan[i][0]);
        //    System.out.print(gabungan[i][1]);
        //}
        //System.out.println();
        
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
        
        //System.out.println("// Eliminasi");
        //for (int z = 0; z < elim_gabungan.length; z++) {
        //    System.out.println(elim_gabungan[z][0] + " " + elim_gabungan[z][1]);
        //}
        
        // System.out.println("// Akumulasi");
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
                // System.out.println(akumulasi_frekuensi);
                frekuensi.add(akumulasi_frekuensi);
            }
            if ((y == elim_gabungan.length - 2) && (sama > 0)) {
                angkaarah.add(elim_gabungan[y + 1][0]);
                akumulasi_frekuensi += elim_gabungan[y + 1][1];
                frekuensi.add(akumulasi_frekuensi);
            }
        }

        //System.out.println("angkaarah : " + angkaarah);
        //System.out.println("frekuensi : " + frekuensi);

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
        //System.out.println("angka arah : " + angkaarah);
        //System.out.println("frekuensi : " + hitung_normal);
        //System.out.println("frekuensi bulat : " + hitung_normal_bulat);

        String hasil_akhir = "";
        for (int s = 0; s < angkaarah.size(); s++) {
            for (int t = 0; t < hitung_normal_bulat.get(s); t++) {
                hasil_akhir += angkaarah.get(s);
            }
        }
        
        return hasil_akhir;
    }
}
