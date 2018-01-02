package test.experiments;

import heap.Element;
import heap.binary.BinaryHeap;
import heap.binomial.BinomialHeap;
import heap.leftist.LeftistHeap;
import heap.pairing.PairingHeap;
import heap.skew.SkewHeap;


import java.io.*;
import java.util.ArrayList;

public class ExpBinaryHeap {




    public static void testBinaryRemove(int amount, Writer writer) throws IOException {



        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            BinaryHeap<Integer> bh = new BinaryHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int random = (int) (Math.random() * (amount - 1));
            startTime = System.nanoTime();
            elements.get(random).remove();
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        writer.write(amount + "," +average + "\n");

    }

    public static void testBinaryUpdate(int amount, Writer w) throws IOException {
        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            BinaryHeap<Integer> bh = new BinaryHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int minimum = 0;
            int maximum = 3 * amount;
            int randomNum;
            int random = (int) (Math.random() * (amount - 1));
            randomNum = minimum + (int)(Math.random() * maximum);
            startTime = System.nanoTime();
            elements.get(random).update(randomNum);
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        w.write(amount + "," +average + "\n");
    }

    public static void testBinomialRemove(int amount, Writer writer) throws IOException {



        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            BinomialHeap<Integer> bh = new BinomialHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int random = (int) (Math.random() * (amount - 1));
            startTime = System.nanoTime();
            elements.get(random).remove();
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        writer.write(amount + "," +average + "\n");

    }

    public static void testBinomialHeapUpdates(int amount, Writer w) throws IOException {
        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            BinomialHeap<Integer> bh = new BinomialHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int minimum = 0;
            int maximum = 3 * amount;
            int randomNum;
            int random = (int) (Math.random() * (amount - 1));
            randomNum = minimum + (int)(Math.random() * maximum);
            startTime = System.nanoTime();
            elements.get(random).update(randomNum);
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        w.write(amount + "," +average + "\n");
    }

    public static void testLeftistHeapRemove(int amount, Writer writer) throws IOException {



        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            LeftistHeap<Integer> bh = new LeftistHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int random = (int) (Math.random() * (amount - 1));
            startTime = System.nanoTime();
            elements.get(random).remove();
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        writer.write(amount + "," +average + "\n");

    }

    public static void testLeftistHeapUpdates(int amount, Writer w) throws IOException {
        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            LeftistHeap<Integer> bh = new LeftistHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int minimum = 0;
            int maximum = 3 * amount;
            int randomNum;
            int random = (int) (Math.random() * (amount - 1));
            randomNum = minimum + (int)(Math.random() * maximum);
            startTime = System.nanoTime();
            elements.get(random).update(randomNum);
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        w.write(amount + "," +average + "\n");
    }

    public static void testSkewHeapRemove(int amount, Writer writer) throws IOException {



        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            SkewHeap<Integer> bh = new SkewHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int random = (int) (Math.random() * (amount - 1));
            startTime = System.nanoTime();
            elements.get(random).remove();
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        writer.write(amount + "," +average + "\n");

    }


    public static void testSkewHeapUpdate(int amount, Writer w) throws IOException {
        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            SkewHeap<Integer> bh = new SkewHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int minimum = 0;
            int maximum = 3 * amount;
            int randomNum;
            int random = (int) (Math.random() * (amount - 1));
            randomNum = minimum + (int)(Math.random() * maximum);
            startTime = System.nanoTime();
            elements.get(random).update(randomNum);
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        w.write(amount + "," +average + "\n");
    }

    public static void testPairingHeapRemove(int amount, Writer writer) throws IOException {



        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            PairingHeap<Integer> bh = new PairingHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int random = (int) (Math.random() * (amount - 1));
            startTime = System.nanoTime();
            elements.get(random).remove();
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        writer.write(amount + "," +average + "\n");

    }

    public static void testPairingHeapUpdate(int amount, Writer w) throws IOException {
        long startTime, stopTime, elapsedTime, average;
        average = 0;

        for (int k = 0; k<100; k++){
            PairingHeap<Integer> bh = new PairingHeap<>();
            ArrayList<Element<Integer>> elements = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                elements.add(bh.insert(i));
            }
            int minimum = 0;
            int maximum = 3 * amount;
            int randomNum;
            int random = (int) (Math.random() * (amount - 1));
            randomNum = minimum + (int)(Math.random() * maximum);
            startTime = System.nanoTime();
            elements.get(random).update(randomNum);
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            average += elapsedTime;
        }

        average /= 100;
        w.write(amount + "," +average + "\n");
    }





    public static void main(String[] args) {



        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsBinaryRem.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("binair" +  i);
                    testBinaryRemove(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsBinaryUp.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("binair" +  i);
                    testBinaryUpdate(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./resultsBinomialHeapRem.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("Binoremoe" +  i);
                    testBinomialRemove(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsBinomialHeapUpda.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("Binoremoe" +  i);
                    testBinomialHeapUpdates(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsLeftistHeapRem.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("left" +  i);
                    testLeftistHeapRemove(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsLeftistHeapUpda.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("left" +  i);
                    testLeftistHeapUpdates(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsSkewHeapRem.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("skew" +  i);
                    testSkewHeapRemove(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsSkewHeapUpda.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("skew" +  i);
                    testSkewHeapUpdate(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsPairingHeapRem.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("pairing" +  i);
                    testPairingHeapRemove(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("resultsPairingHeapUp.txt"), "utf-8"))) {
                for (int i= 100; i < 20000; i +=100){
                    System.out.println("pairing" +  i);
                    testPairingHeapUpdate(i, w);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }











    }


}
