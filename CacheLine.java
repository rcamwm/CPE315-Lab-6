import java.util.LinkedList;

public class CacheLine 
{
    private static int associativity = 0;
    private LinkedList<Integer> tagList;
    private int occupied;

    public static void setAssociativity(int newAssociativity) { associativity = newAssociativity; }
    public static int getAssociativity() { return associativity; }

    CacheLine()
    {
        this.tagList = new LinkedList<>();
        this.occupied = 0;
    }

    public boolean checkForTag(int tag)
    {
        boolean wasMiss = true;
        for (int i = 0; i < this.occupied; i++)
        {
            if (this.tagList.get(i) == tag)
            {
                this.tagList.remove(i);
                this.tagList.add(0, tag);
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
        if (this.occupied < associativity)
        {
            this.occupied++;
            this.tagList.add(tag);
        }
        else
        {
            this.tagList.removeLast();
            this.tagList.add(0, tag);
        }
    }
}
