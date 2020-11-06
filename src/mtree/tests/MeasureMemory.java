package mtree.tests;

import mtree.utils.Utils;

public class MeasureMemory {
    
    
    
    public static void main(String[] args){
        long currentTime = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() - currentTime >100)
            {
                currentTime = System.currentTimeMillis();
                Utils.computeUsedMemory();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                System.out.println("Peak memory usage = "+Utils.peakUsedMemory);
            }
            
        }
        
        
    }

}
