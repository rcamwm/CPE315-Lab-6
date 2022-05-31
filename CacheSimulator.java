public class CacheSimulator {
    private final static int BYTES_IN_WORD = 4;

    private int id; 
    private int cacheSize;
    private int blockSize;

    private CacheLine[] cache;

    private int offsetBits;
    private int indexBits;

    private int hits;
    private int checks;

    /***
     * @param id cache number to print
     * @param cacheSize total cache size in bits
     * @param associativity number of sets per index
     * @param blockSize block size in words
     */
    CacheSimulator(int id, int cacheSize, int associativity, int blockSize)
    {
        this.id = id;
        this.cacheSize = cacheSize;
        this.blockSize = blockSize; 

        int blockCount = this.cacheSize / (BYTES_IN_WORD * this.blockSize); // Byte addressable
        this.offsetBits = (int)(Math.log(BYTES_IN_WORD * blockSize) / Math.log(2)); 
        this.indexBits = (int)(Math.log(blockCount) / Math.log(2)); 

        CacheLine.setAssociativity(associativity);
        this.cache = new CacheLine[blockCount];
        for (int i = 0; i < blockCount; i++)
            this.cache[i] = new CacheLine();

        this.hits = 0;
        this.checks = 0;
    }

    public void accessMemoryAddress(int address)
    {
        this.checks++;
        int index = (address >> this.offsetBits) & ((1 << this.indexBits) - 1);
        int tag = address >> (this.offsetBits + this.indexBits);
        if (this.cache[index].checkForTag(tag))
        {
            this.hits++;
        }
    }

    public void printResults()
    {
        System.out.println("Cache #" + this.id);
        System.out.print("Cache size: " + this.cacheSize + "B	");
        System.out.print("Associativity: " + CacheLine.getAssociativity() + "	");
        System.out.println("Block size: " + this.blockSize);

        System.out.print("Hits: " + this.hits + "	");
        double hitRate = (double)(this.hits) / (double)(this.checks);
        System.out.println(String.format("Hit Rate: %.2f", hitRate * 100) + "% ");

        System.out.println("---------------------------");
    }
}
