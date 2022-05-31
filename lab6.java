/*
Cameron McGiffert 
CPE315 Section 1
Lab 6
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class lab6 {
    public static void main(String[] args)
    {
        final boolean DEBUG = true;
        if (DEBUG)
        {
            String[] filenames = {"mem_stream.1", "mem_stream.2", "testinput"};
            String filename = filenames[0];
            runCacheConfigutations(filename);
        }
        else 
        {
            
        }
    }

    private static void runCacheConfigutations(String filename)
    {
        // runCacheSimulator(filename, 1, 2048, 1, 1);
        runCacheSimulator(filename, 2, 2048, 1, 2);
        // runCacheSimulator(filename, 3, 2048, 1, 4);
        // runCacheSimulator(filename, 4, 2048, 2, 1);
        // runCacheSimulator(filename, 5, 2048, 4, 1);
        // runCacheSimulator(filename, 6, 2048, 4, 4);
        // runCacheSimulator(filename, 7, 4096, 1, 1);
    }

    private static void runCacheSimulator(String filename, int num, int cacheSize, int associativity, int blockSize)
    {
        CacheSimulator simulator = new CacheSimulator(cacheSize, associativity, blockSize);
        try
        {
            File source = new File(filename);
            Scanner scan = new Scanner(source);
            while (scan.hasNextLine())
            {
                int address = getAddress(scan.nextLine());
                simulator.accessMemoryAddress(address);
            }
            scan.close();
            simulator.printResults(num);
        }
        catch (FileNotFoundException exception)
        {
            exception.printStackTrace();
        }
    }

    private static int getAddress(String inputLine)
    {
        String hexAddress = inputLine.split("	")[1];
        return Integer.decode("0x" + hexAddress);
    }
}