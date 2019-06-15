/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSortingAlgorithms;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author sala203
 */
public class JavaSortingAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int length = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la longitud del array: "));
        int[] integers = new int[length];
        
        for (int i = 0; i < integers.length; i++) {
            integers[i] = 1 + (int)(Math.random() * ((length - 1) + 1));
        }
        
        System.out.println(Arrays.toString(integers) + "\n");
        
        bubble(integers);
        insertion(integers);
        selection(integers);
        shell(integers);
        callQuickSort(integers, 0, length - 1);
    }
    
    /**
     * 
     * @param paramMethod
     * @param paramArray
     * @param paramEndTime
     * @param paramStartTime 
     */
    public static void showResults(String paramMethod, int[] paramArray, long paramEndTime, long paramStartTime) {
        long timeElapsed = paramEndTime - paramStartTime;
        System.out.println(paramMethod + " - Array:  " + Arrays.toString(paramArray));
        System.out.println(paramMethod + " - Execution time in nanoseconds: " + timeElapsed);
        System.out.println(paramMethod + " - Execution time in milliseconds: " + timeElapsed / 1000000 + "\n");
    }
    
    /**
     * 
     * @param paramArray 
     */
    public static void bubble(int[] paramArray) {
        long startTime = System.nanoTime();
        int i, j, aux;
        for (i = 0; i < paramArray.length - 1; i++) {
            for (j = 0; j < paramArray.length - i - 1; j++) {
                if (paramArray[j + 1] < paramArray[j]) {
                    aux = paramArray[j + 1];
                    paramArray[j + 1] = paramArray[j];
                    paramArray[j] = aux;
                }
            }
        }
        long endTime = System.nanoTime();
        showResults("Bubble", paramArray, endTime, startTime);
    }
    
    /**
     * 
     * @param paramArray
     */
    public static void insertion(int[] paramArray){
        long startTime = System.nanoTime();
        int aux;
        for (int i = 1; i < paramArray.length; i++) {
            aux = paramArray[i];
            for (int j = i - 1; j >= 0 && paramArray[j] > aux; j--) {
                paramArray[j + 1] = paramArray[j];
                paramArray[j] = aux;
            }
        }
       long endTime = System.nanoTime();
       showResults("Insertion", paramArray, endTime, startTime);
    }
    
    /**
     * 
     * @param paramArray 
     */
    public static void selection(int[] paramArray) {
        long startTime = System.nanoTime();
        int i, j, min, pos, tmp;
        for (i = 0; i < paramArray.length - 1; i++) {
              min = paramArray[i];
              pos = i;
              for (j = i + 1; j < paramArray.length; j++){
                    if (paramArray[j] < min) {
                        min = paramArray[j];
                        pos = j;
                    }
              }
              if (pos != i){
                  tmp = paramArray[i];
                  paramArray[i] = paramArray[pos];
                  paramArray[pos] = tmp;
              }
        }
        long endTime = System.nanoTime();
        showResults("Selection", paramArray, endTime, startTime);
    }

    /**
     * 
     * @param paramArray 
     */
    public static void shell(int[] paramArray){
        long startTime = System.nanoTime();
        int salto, aux, i;
        boolean change;
        for(salto = paramArray.length/2; salto != 0; salto/=2){
            change = true;
            while(change) { // Mientras se intercambie algún elemento
                change = false;
                for(i = salto; i < paramArray.length; i++) { // se da una pasada
                    if(paramArray[i-salto] > paramArray[i]) { // y si están desordenados
                          aux=paramArray[i]; // se reordenan
                          paramArray[i] = paramArray[i-salto];
                          paramArray[i-salto] = aux;
                          change = true; // y se marca como cambio.
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        showResults("Shell", paramArray, endTime, startTime);
    }
    
    public static void callQuickSort(int[] paramArray, int paramLeft, int paramRight) {
        long startTime = System.nanoTime();
        quickSort(paramArray, paramLeft, paramRight);
        long endTime = System.nanoTime();
        showResults("QuickSort", paramArray, endTime, startTime);
    }
    
    public static void quickSort(int[] paramArray, int paramLeft, int paramRight) {
        int piv = paramArray[paramLeft];
        int i = paramLeft;
        int j = paramRight;
        int aux;

        while(i < j) {
            while(paramArray[i] <= piv && i<j) i++;
            while(paramArray[j] > piv) j--;
            if (i < j) {
               aux = paramArray[i];
               paramArray[i] = paramArray[j];
               paramArray[j] = aux;
            }
        }
        paramArray[paramLeft] = paramArray[j];
        paramArray[j] = piv;
        if(paramLeft < j-1)
           quickSort(paramArray, paramLeft, j-1);
        if(j+1 < paramRight)
           quickSort(paramArray, j+1, paramRight);
    }
}
