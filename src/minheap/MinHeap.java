package minheap;

import java.util.Arrays;
import java.util.Scanner;

public class MinHeap {

    private int[] Heap;
    private int index;
    private int size;

    public MinHeap(int size) {
        this.size = size;
        this.index = 0;
        Heap = new int[size];
    }

    private int parent(int i) {
        return (i - 1) / 3;
    }

    private int leftChild(int i) {
        return (i * 3) + 1;
    }

    private int middleChild(int i) {
        return (i * 3) + 2;
    }

    private int rightChild(int i) {
        return (i * 3) + 3;
    }

    private boolean isLeaf(int i) {
        if (rightChild(i) >= size || leftChild(i) >= size || middleChild(i) >= size) {
            return true;
        }
        return false;
    }

    public void insert(int element) {
        if (index >= size) {
            return;
        }
        Heap[index] = element;
        int current = index;

        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        index++;
    }

    private void minHeapify(int i) {
        // Düğüm yaprak olmayan bir düğüm ise ve alt düğümlerden herhangi biri daha küçük ise:
        if (!isLeaf(i)) {

            if (Heap[i] > Heap[leftChild(i)] || Heap[i] > Heap[rightChild(i)] || Heap[i] > Heap[middleChild(i)]) {

                if (Heap[leftChild(i)] < Heap[rightChild(i)] && Heap[leftChild(i)] < Heap[middleChild(i)]) {
                    swap(i, leftChild(i));
                    minHeapify(leftChild(i));

                } else if (Heap[rightChild(i)] < Heap[leftChild(i)] && Heap[rightChild(i)] < Heap[middleChild(i)]) {
                    swap(i, rightChild(i));
                    minHeapify(rightChild(i));

                } else {
                    swap(i, middleChild(i));
                    minHeapify(middleChild(i));
                }
            }
        }
    }

    // minHeapify kullanarak min heap yapısını oluşturma:
    public void minHeap() {
        for (int i = (index - 1 / 3); i >= 1; i--) {
            minHeapify(i);
        }
    }

    // yazdırma fonksiyonu
    public void printHeap() {
        for (int i = 0; i < (index / 3); i++) {
            System.out.print("Parent : " + Heap[i]);
            if (leftChild(i) < index) {
                System.out.print(" , Left : " + Heap[leftChild(i)]);
            }
            if (middleChild(i) < index) {
                System.out.print(" , Midle :" + Heap[middleChild(i)]);
            }
            if (rightChild(i) < index) {
                System.out.print(" , Right :" + Heap[rightChild(i)]);
            }

            System.out.println();
        }
    }

    private void swap(int x, int y) {
        int tmp;
        tmp = Heap[x];
        Heap[x] = Heap[y];
        Heap[y] = tmp;
    }

    public static void main(String[] arg) {
        
        int heapSize;
        
        Scanner sc= new Scanner(System.in);
        
        System.out.print("min heap'in boyutunu girin: ");
        
        heapSize=sc.nextInt();
        
        MinHeap minHeap = new MinHeap(heapSize);
        
        
        for(int i=1;i<=heapSize;i++){
            System.out.print(i + ". eleman: ");
            int data=sc.nextInt();
            minHeap.insert(data);
            
        }

        System.out.println("The Min Heap is : " + Arrays.toString(minHeap.Heap));
        minHeap.printHeap();
        
        

        int dizi[] = new int[heapSize];

        for (int i = 0; i < dizi.length; i++) {
            System.out.print("Diziye kaydedilecek sayıları girin: ");
            dizi[i] = sc.nextInt();
        }

        System.out.print("{");
        for (int s : dizi) {
            System.out.print(s + " , ");
        }
        System.out.print("}");
        
        
        if (dizi==minHeap) {
            System.out.println("dizi minHeap yapıdadır");
        }else{
            System.out.println("dizi minHeap yapıda değildir");
        }

    }
}
