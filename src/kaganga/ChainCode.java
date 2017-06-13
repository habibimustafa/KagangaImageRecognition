/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaganga;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @authorFelipe Custódio, Gabriel Scalici
 */
public class ChainCode {

    // image properties
    int h; // height
    int w; // width

    // shape properties
    int height;
    int width;

    // bitmaps
    int pixels[][]; // image with treshold 1 or 0
    int visited[][]; // stores visited pixels

    // initial coordinates of the shape
    int begin[];

    // final coordinates of the shape
    int end[];

    // perimeter
    int points;
    double perimeter;
    String chainResult = "";

    public ChainCode(BufferedImage image){

        // read input file
//        System.out.print("Filename: ");
//        String filename = new String();
//        filename = Input.readString();
//        File shape = new File(filename);
//        BufferedImage image = null;

        // setar propriedades da image para uso posterior
        h = image.getHeight(); // height
        w = image.getWidth(); // width

        // initialize coordinates vectors
        begin = new int[2];
        end = new int[2];
        points = 0;
        perimeter = 0;

        // treshold image
        pixels = new int[h][w];
        visited = new int [h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[i][j] = image.getRGB(j, i);
                if (pixels[i][j] != -1) {
                     // shades of gray -> black
                    pixels[i][j] = 1;
                } else {
                     // background -> white
                    pixels[i][j] = 0;
                }
                // set pixel as unvisited
                visited[i][j] = 0;
            }
        }   
    }

    public void firstPixel() {
        boolean flag = false;
        // locate first black pixel
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (pixels[i][j] == 1 && !(flag)) {
                    // get coordinates
                    begin[0] = i;
                    begin[1] = j;
                    flag = true;
                }
            }
        }
    }

    public void lastPixel() {
        boolean flag = false;
        // find first pixel from down-up
        for (int i = h - 1; i >= 0; i--) {
            for (int j = w - 1; j >= 0; j--) {
                if (pixels[i][j] == 1 && !(flag)) {
                    // get coordinates
                    end[0] = i;
                    end[1] = j;
                    flag = true;
                }
            }
        }
    }

    public void setHeight() {
        // y of last pixel - y of first pixel
        height = (end[0] - begin[0] + 1);                    
    }

    public void setWidth() {

        // get x coordinates of first and final pixels
        int aux[] = new int[2];
        boolean flag = false;
        // find first pixel to the left
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (pixels[j][i] == 1 && !(flag)) {
                    // get x coordinate
                    aux[0] = i;
                    flag = true;
                }
            }
        }

        flag = false;
        // find first pixel to the right
        for (int i = w - 1; i >= 0; i--) {
            for (int j = h - 1; j >= 0; j--) {
                if (pixels[j][i] == 1 && !(flag)) {
                    // get x coordinate
                    aux[1] = i;
                    flag = true;
                }
            }
        }

        // x of last pixel - x of first pixel
        width = (aux[1] - aux[0] + 1);

    }

    public void border() {

        for (int i = 0; i < h; i++) {
        	for (int j = 0; j < w; j++) {
        		if (pixels[i][j] == 1) {
        			// if a neighbor of a pixel is empty, that pixel
                    // is on the border of the shape 
        			if (borderPixel(i, j)) points++;
        		}
        	}
        }
    }

    public boolean borderPixel(int i, int j) {

        // only check black pixels
    	if (pixels[i][j] == 0) return false;
    	
        // check left
    	if (j == 0) return true; // image border = shape border
    	if (j > 0) {
    		if (pixels[i][j - 1] == 0) {
    			return true;
    		}
    	}

    	// check up
    	if (i == 0) return true;
    	if (i > 0) {
    		if (pixels[i - 1][j] == 0) {
    			return true;
    		}
    	}

    	// check right
    	if (j == w) return true;
    	if (j < w) {
    		if (pixels[i][j + 1] == 0) {
    			return true;
    		}
    	}

    	// check down
    	if (i == h) return true;
    	if (i < h) {
    		if (pixels[i + 1][j] == 0) {
    			return true;
    		}
    	}

    	// no empty pixel around = not border pixel
    	return false;
    }

    public int[] borderNeighbors(int i, int j) {
    	
    	int index[] = new int[2];
    	boolean flag = false;

    	// check around pixel for unvisited border pixels
        // calculates chain codes distance

    	// check east
    	if (borderPixel(i, j+1) && !flag && !flag && visited[i][j+1] == 0) {
    		j = j + 1;
    		System.out.print("0 ");
    		perimeter += 1;
                chainResult += "0";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check southeast
    	if (borderPixel(i+1, j+1) && !flag && visited[i+1][j+1] == 0) {
    		i = i + 1;
    		j = j + 1;
    		System.out.print("1 ");
    		perimeter += Math.sqrt(2);
                chainResult += "1";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check south
    	if (borderPixel(i+1, j) && !flag && visited[i+1][j] == 0) {
    		i = i + 1;
    		System.out.print("2 ");
    		perimeter += 1;
                chainResult += "2";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check southwest
    	if (borderPixel(i+1, j-1) && !flag && visited[i+1][j-1] == 0) {
    		i = i + 1;
    		j = j - 1;
    		System.out.print("3 ");
    		perimeter += Math.sqrt(2);
                chainResult += "3";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check west
    	if (borderPixel(i, j-1) && !flag && visited[i][j-1] == 0) {
    		j = j - 1;
    		System.out.print("4 ");
    		perimeter += 1;
                chainResult += "4";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check northwest
    	if (borderPixel(i-1, j-1) && !flag && visited[i-1][j-1] == 0) {
    		i = i - 1;
    		j = j - 1;
    		System.out.print("5 ");
    		perimeter += Math.sqrt(2);
                chainResult += "5";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check north
    	if (borderPixel(i-1, j) && !flag && visited[i-1][j] == 0) {
    		i = i - 1;
    		System.out.print("6 ");
    		perimeter += 1;
                chainResult += "6";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
    	// check northeast
    	if (borderPixel(i-1, j+1) && !flag && visited[i-1][j+1] == 0) {
    		i = i - 1;
    		j = j + 1;
    		System.out.print("7 ");
    		perimeter += Math.sqrt(2);
                chainResult += "7";
    		flag = true;
    		index[0] = i;
    		index[1] = j;
    		return index;
    	}
        // no neighbor border pixels 
    	index[0] = i;
    	index[1] = j;
    	return index;
    }

    public void chainCodes(int i, int j) {

    	/*
    	i e j = index of current pixel
    	index[0], index[1] = next border pixel (if exists)
    	*/

    	// coordinates of current pixel
    	int[] index = new int[2];

    	// check for border pixels around 
    	index = borderNeighbors(i, j);

    	// set pixel as visited
    	visited[i][j] = 1;

    	// if next border pixel is visited, we're back to the first pixel 
    	if (visited[index[0]][index[1]] == 0) {
    		chainCodes(index[0], index[1]);
    	} else {
    		System.out.println();
    	}
    }

}
