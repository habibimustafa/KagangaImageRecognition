package kaganga;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class BackPropagation extends Thread {
    private double input[][];
    private double target[][];
    
    // hidden layer
    private double hidden[];
    private double hidden_net[];
    private double hidden_bias[];
    private double hidden_bobot[][];
    
    // output layer
    private double output[];
    private double output_net[];
    private double output_bias[];
    private double output_bobot[][];
    
    private double alpha, teta;
    
    private boolean stop = false;
    private JButton btnStart;
    private JButton btnStop;
    
    private JTextField textEpoch;
    private JTextField textMSE;
    private JList listActivity;
    private DefaultListModel<String> listModel;

    public void setInput(double[][] input) {
        this.input = input;
    }

    public void setTarget(double[][] target) {
        this.target = target;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setTeta(double teta) {
        this.teta = teta;
    }

    public void setHidden(int hidden) {
        this.hidden      = new double[hidden];
        this.hidden_net  = new double[hidden];
        this.hidden_bias = new double[hidden];
        this.hidden_bobot= new double[this.input[0].length][hidden];
    }

    public void setOutput(int output) {
        this.output      = new double[output];
        this.output_net  = new double[output];
        this.output_bias = new double[output];
        this.output_bobot= new double[this.hidden.length][output];
    }
    
    public double sigmoid_biner(double x){
        return (1.0 / (1.0 + Math.exp(-x)));
    }
    
    public double sigmoid_bipolar(double x){
        return (2 * sigmoid_biner(x))-1;
    }
    
    public double turunan_sigmoid_bipolar(double x) {
        return 0.5 * (1 + sigmoid_bipolar(x)) * (1 - sigmoid_bipolar(x));
    }

    public double v_rand(){
//        double arr[] = {-1, 0, 1};
//        return Math.floor(Math.random() * arr.length) -1;
        return Math.random();
//        Random rd = new Random();
//        return rd.nextDouble();
    }
    
    public void normalisasi_input(){
        for(int i=0; i < this.input.length; i++)
            for(int j=0; j < this.input[i].length; j++)
                this.input[i][j] = this.sigmoid_biner(this.input[i][j]);    
    }
    
    public void init_hidden(){
        //inisialisasi bias hidden
        for (int i=0; i < hidden.length; i++) { 
            hidden_bias[i] = this.v_rand();
        }

        // inisiasi bobot pada input-hidden layer
        for (int i=0; i < hidden.length; i++) { 
            for (int j=0; j < input[0].length; j++) { 
                    hidden_bobot[j][i] = this.v_rand();
            }
        }
    }
    
    public void init_output(){
        //inisialisasi bias hidden
        for (int i=0; i < output.length; i++) { 
            output_bias[i] = v_rand();
        }

        // inisiasi bobot pada input-hidden layer
        for (int i=0; i < output.length; i++) { 
            for (int j=0; j < hidden.length; j++) { 
                    output_bobot[j][i] = v_rand();
            }
        }
    }
    
    public void trainStop(){
        this.stop = true;
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }
    
    public void setActivity(JTextField textEpoch, JTextField textMSE, JList listActivity){
        this.textEpoch = textEpoch;
        this.textMSE = textMSE;
        this.listActivity = listActivity;
        
        listModel = new DefaultListModel<>();
        this.listActivity.setModel(listModel);
    }
    
    public void setButton(JButton btnStart, JButton btnStop){
        this.btnStart = btnStart;
        this.btnStop = btnStop;
    }
    
    public void run(){
        init_hidden();
        init_output();
        
        double err = 0, mse = 0;
        int epc = 1, max_epc = 1000000;

        stop = false;
        if(btnStart != null) btnStart.setEnabled(false);
        if(btnStop != null) btnStop.setEnabled(true);
        if(listModel != null) listModel.clear();
        
        do {
            mse = 0;
            // looping pattern
            for (int i=0; i<input.length; i++){

                //feed-forward layer input - hidden :
                for (int j=0; j<hidden.length; j++) { 
                    hidden_net[j] = 0;
                    for (int k=0; k<input[i].length; k++) { 
                        hidden_net[j] += (input[i][k] * hidden_bobot[k][j]); 
                    }
                    hidden_net[j] += hidden_bias[j];
                    // proses aktivasi
                    hidden[j] = sigmoid_bipolar(hidden_net[j]);
                }
                
                //feed-forwared layer hidden - output :
                for (int j=0; j<output.length; j++) { 
                    output_net[j] = 0;
                    for (int k=0; k<hidden.length; k++) { 
                        output_net[j] += (hidden[k] * output_bobot[k][j]);
                    }
                    output_net[j] += output_bias[j];
                    // proses aktivasi
                    output[j] =  sigmoid_bipolar(output_net[j]);
                }
            
                //menghitung nilai error
                //hitung koreksi bobot hidden-output
                err = 0;
                double delta[] = new double[output.length];
                for (int j=0; j<output.length; j++) { 
                    double ee = target[i][j] - output[j];
                    err  += Math.pow(ee, 2);
                    delta[j] = ee * turunan_sigmoid_bipolar(output_net[j]);
                }

                // err *= 0.5;
                // mse += err;
                mse = 0.5 * err;
                
                //update bobot dan bias dari hidden-output
                for (int j=0; j<output.length; j++) { 
                    for (int k=0; k<hidden.length; k++) { 
                        output_bobot[k][j] += (alpha * delta[j] * hidden[k]);
                    }
                    output_bias[j] += (alpha * delta[j]);
                }
                
                //hitung koreksi bobot input-hidden
                double delta2[] = new double[hidden.length];
                for (int j=0; j<hidden.length; j++) {
                    int err_hidden = 0;
                    for (int k=0; k<output.length; k++) { 
                        err_hidden += (delta[k] * output_bobot[j][k]);
                    }
                    delta2[j] = err_hidden * turunan_sigmoid_bipolar(hidden_net[j]);
                }
                
                //update bobot dan bias layer input-hidden :
                for (int j=0; j<hidden.length; j++) { 
                    for (int k=0; k<input[i].length; k++) {
                        hidden_bobot[k][j] += (alpha * delta2[j] * input[i][k]);
                    }
                    hidden_bias[j] += (alpha * delta2[j]);
                }
                                
            } // end of pattern loop
            //mse /= 4;
            epc++;
            
//            System.out.println(epc+" "+String.format("%.12f", mse));
            if(textEpoch != null) textEpoch.setText(String.valueOf(epc));
            if(textMSE != null) textMSE.setText(String.format("%.12f", mse));
            if(listModel != null) listModel.addElement(epc+" | "+String.format("%.12f", mse));
            if(listActivity != null) listActivity.ensureIndexIsVisible( listActivity.getModel().getSize() -1 );
            
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            
        } while ((mse > teta) && (epc < max_epc) && !stop);

        if(btnStart != null) btnStart.setEnabled(true);
        if(btnStop != null) btnStop.setEnabled(false);
    }
    
    public double[] test(double[] pola_huruf){

        for(int i=0; i < pola_huruf.length; i++)
            pola_huruf[i] = this.sigmoid_biner(pola_huruf[i]);    
        
        for (int i=0; i<hidden.length; i++) { 
            hidden_net[i] = 0;
//            System.err.println(pola_huruf.length+" "+hidden_bobot.length);
            for (int j=0; j<pola_huruf.length; j++) { 
                hidden_net[i] += (pola_huruf[j] * hidden_bobot[j][i]);
            }
            hidden_net[i] += hidden_bias[i];
            hidden[i] = sigmoid_bipolar(hidden_net[i]);
        }

        for (int i=0; i < output.length; i++) { 
            output_net[i] = 0;
            for (int j=0; j < hidden.length; j++) { 
                output_net[i] += (hidden[j] * output_bobot[j][i]);
            }
            output_net[i] += output_bias[i];
            output[i] = sigmoid_bipolar(output_net[i]);
        }
        
        final int[] o = new int[output.length];
        for (int i=0; i<output.length; ++i)
            o[i] = (int) Math.round(output[i]);        
        //System.out.println(java.util.Arrays.toString(o));
        
        double r[] = new double[o.length];
        for (int i=0; i<o.length; ++i) r[i] = (double) o[i];
        return r;

    }
    
    
    public static void main(String[] args) {
        double data_input[][] = {
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

        double data_target[][] = {
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
        
        BackPropagation BP = new BackPropagation();
        BP.setInput(data_input);
        BP.setTarget(data_target);
        BP.setAlpha(0.1);
        BP.setTeta(0.0001);
        BP.setHidden(15);
        BP.setOutput(data_target[0].length);
        BP.start();
        
        double[] testhuruf = {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1};
        BP.test(data_input[1]);
    }
    
    
}
