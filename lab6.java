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
        CacheSimulator[] simulators = 
        {
            new CacheSimulator(1, 2048, 1, 1),
            new CacheSimulator(2, 2048, 1, 2),
            new CacheSimulator(3, 2048, 1, 4),
            // new CacheSimulator(4, 2048, 2, 1),
            // new CacheSimulator(5, 2048, 4, 1),
            // new CacheSimulator(6, 2048, 4, 4),
            new CacheSimulator(7, 4096, 1, 1)
        };
        simulateCache(filename, simulators);
    }

    private static void simulateCache(String filename, CacheSimulator[] simulators)
    {
        try
        {
            File source = new File(filename);
            Scanner scan = new Scanner(source);
            while (scan.hasNextLine())
            {
                int address = getAddress(scan.nextLine());
                for (int i = 0; i < simulators.length; i++)
                    simulators[i].accessMemoryAddress(address);
            }
            scan.close();
            for (int i = 0; i < simulators.length; i++)
                simulators[i].printResults();
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