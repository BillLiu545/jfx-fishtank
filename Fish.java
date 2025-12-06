
public class Fish
{
    private String type; private int num;
    public Fish(String type, int num)
    {
        this.type = type; this.num = num;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getNum()
    {
        return num;
    }
    
    public void setNum(int newNum)
    {
        this.num = newNum;
    }
    
    public String toString()
    {
        return num + " " + type;
    }
}
