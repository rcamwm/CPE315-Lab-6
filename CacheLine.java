import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CacheLine 
{
    private int associativity;
    private List<Integer> tagList;
    private List<Integer> LRU;
    private int occupied;

    CacheLine(int associativity)
    {
        this.associativity = associativity;
        this.tagList = new ArrayList<>();
        this.LRU = new LinkedList<>(); // [0] most recent, [4] last used
        this.occupied = 0;
    }

    public boolean checkForTag(int tag)
    {
        boolean wasMiss = true;
        for (int i = 0; i < this.occupied; i++)
        {
            if (this.tagList.get(i) == tag)
            {
                this.LRU.remove((Integer)i);
                this.LRU.add(0, i);
                return true; // hit
            }
        }
        if (wasMiss)
        {
            addTag(tag);
        }
        return false; // miss
    }

    private void addTag(int tag)
    {
        if (this.occupied < this.associativity)
        {
            this.LRU.add(this.occupied);
            this.occupied++;
            this.tagList.add(tag);
        }
        else
        {
            int lastUsedIndex = this.LRU.get(this.associativity - 1);
            this.tagList.set(lastUsedIndex, tag);
            this.LRU.remove((Integer)lastUsedIndex);
            this.LRU.add(0, lastUsedIndex);
        }
    }
}
