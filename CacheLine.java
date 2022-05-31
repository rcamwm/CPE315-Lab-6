public class CacheLine 
{
    private int associativity;
    private int[] tagList;
    private int[] LRU;

    CacheLine(int associativity)
    {
        this.associativity = associativity;
        this.tagList = new int[associativity];
        this.LRU = new int[associativity];
    }

    public boolean checkForTag(int tag, int lineNumber)
    {
        int lastUsedIndex = 0;
        for (int i = 0; i < this.associativity; i++)
        {
            if (this.tagList[i] == tag)
            {
                this.LRU[i] = lineNumber;
                return true; // hit
            }
            if (this.LRU[i] < this.LRU[lastUsedIndex])
                lastUsedIndex = i;
        }
        this.tagList[lastUsedIndex] = tag;
        this.LRU[lastUsedIndex] = lineNumber;
        return false; // miss
    }
}
