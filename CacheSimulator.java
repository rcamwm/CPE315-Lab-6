public class CacheSimulator {
    private int cacheSize;
    private int associativity;
    private int blockSize;
    private int blockCount;
    private CacheLine[] cache;

    private int offsetBits;
    private int indexBits;

    private int hits;
    private int checks;

    /***
     * 
     * @param cacheSize total cache size in bits
     * @param associativity the number of sets per index
     * @param blockSize block size in words (= 2^m, m is offset bits)
     */
    CacheSimulator(int cacheSize, int associativity, int blockSize)
    {
        this.cacheSize = cacheSize;
        this.associativity = associativity;
        this.blockSize = blockSize; 
        this.blockCount = this.cacheSize / this.blockSize; // = 2^n

        this.offsetBits = (int)(Math.log(blockSize) / Math.log(2)); // = m
        this.indexBits = (int)(Math.log(blockCount) / Math.log(2)); // = n

        this.cache = new CacheLine[this.blockCount];
        for (int i = 0; i < this.blockCount; i++)
            this.cache[i] = new CacheLine(this.associativity);

        this.hits = 0;
        this.checks = 0;
    }

    // 1fffff58 = 111111111111111111 11101011000 || 111111111111111111 1110101100 0
    // 1fffff56 = 111111111111111111 11101010110 || 111111111111111111 1110101011 0
    // 1fffff57 = 111111111111111111 11101010111 || 111111111111111111 1110101011 1
    // 1fffff59 = 111111111111111111 11101011001 || 111111111111111111 1110101100 1
    public void accessMemoryAddress(int address)
    {
        this.checks++;

        int index = (address >> this.offsetBits) % this.blockCount;
        int tag = address >> (this.offsetBits + this.indexBits);
        if (this.cache[index].checkForTag(tag))
        {
            this.hits++;
        }
    }

    public void printResults()
    {
        System.out.print("Cache size: " + this.cacheSize + "B	");
        System.out.print("Associativity: " + this.associativity + "	");
        System.out.println("Block size: " + this.blockSize);

        System.out.print("Hits: " + this.hits + "	");
        double hitRate = (double)(this.hits) / (double)(this.checks);
        System.out.println(String.format("Hit Rate: %.2f", hitRate * 100) + "% ");

        System.out.println("---------------------------");
    }

    public void printResults(int cacheNumber)
    {
        System.out.println("Cache #" + cacheNumber);
        printResults();
    }
}
