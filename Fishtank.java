import javafx.collections.*;
import javafx.scene.control.*;
import java.util.*;
public class Fishtank extends TreeMap<Integer, Fish>
{
    private String[] fishTypes =
    {
        "Betta Splendens",
        "Neon Tetra",
        "Angelfish",
        "Guppy",
        "Blue Discus",
        "Pearl Gourami",
        "Zebra Danio"
    };
    public boolean validFish(String type)
    {
        for (int i = 0; i < fishTypes.length; i++)
        {
            if (type.equals(fishTypes[i]))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsFish(String type)
    {
        Collection<Fish> fishList = values();
        Iterator<Fish> iter = fishList.iterator();
        while (iter.hasNext())
        {
            Fish next = iter.next();
            if (next.getType().equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public Fish add_input()
    {
        TextInputDialog typeDialog = new TextInputDialog();
        typeDialog.setTitle("Add a New Fish");
        typeDialog.setHeaderText("Add a New Fish");
        typeDialog.setHeaderText("Please enter fish type: ");
        Optional<String> typeOpt = typeDialog.showAndWait();
        String type = typeOpt.get();
        TextInputDialog numDialog = new TextInputDialog();
        numDialog.setTitle("Add a New Fish");
        numDialog.setHeaderText("Add a New Fish");
        numDialog.setHeaderText("Please enter no. of fish to be added: ");
        Optional<String> numOpt = numDialog.showAndWait();
        String num_str = numOpt.get();
        Integer num = null;
        try
        {
            num = Integer.parseInt(num_str);
        }
        catch (Exception ex)
        {
            num = 0;
        }
        Fish added = addFish(type, num);
        return added;
    }
    
    public Fish addFish(String type, int num)
    {
        if (validFish(type) == false)
        {
            return null;
        }
        Fish added = new Fish(type, num);
        if (containsFish(type))
        {
            Collection<Fish> fishList = values();
            Iterator<Fish> iter = fishList.iterator();
            while (iter.hasNext())
            {
                Fish next = iter.next();
                if (next.getType().equals(type))
                {
                    int old_num = next.getNum();
                    int sum = old_num + num;
                    next.setNum(sum);
                    break;
                }
            }
        }
        else
        {
            int ind = size();
            put(ind+1, added);
        }
        return added;
    }
    
    public Fish remove_input()
    {
        TextInputDialog typeDialog = new TextInputDialog();
        typeDialog.setTitle("Add a New Fish");
        typeDialog.setHeaderText("Add a New Fish");
        typeDialog.setHeaderText("Please enter fish type: ");
        Optional<String> typeOpt = typeDialog.showAndWait();
        String type = typeOpt.get();
        TextInputDialog numDialog = new TextInputDialog();
        numDialog.setTitle("Add a New Fish");
        numDialog.setHeaderText("Add a New Fish");
        numDialog.setHeaderText("Please enter no. of fish to be removed: ");
        Optional<String> numOpt = numDialog.showAndWait();
        String num_str = numOpt.get();
        Integer num = null;
        try
        {
            num = Integer.parseInt(num_str);
        }
        catch (Exception ex)
        {
            num = 0;
        }
        Fish removed = removeFish(type, num);
        return removed;
    }
    
    public Fish removeFish(String type, int num)
    {
        Fish removed = null;
        if (containsFish(type))
        {
            Collection<Fish> fishList = values();
            Iterator<Fish> iter = fishList.iterator();
            int fish_ind = 1;
            while (iter.hasNext())
            {
                Fish next = iter.next();
                if (next.getType().equals(type))
                {
                    int old_num = next.getNum();
                    int diff = old_num - num;
                    next.setNum(diff);
                    if (diff  <= 0)
                    {
                        remove(fish_ind);
                    }
                    removed = new Fish(type, num);
                    break;
                }
                fish_ind += 1;
            }
        }
        return removed;
    }
    
    public ObservableList<Fish> toObservableList()
    {
        Collection<Fish> fishes = values();
        ObservableList<Fish> oList = FXCollections.observableArrayList();
        Iterator<Fish> iter = fishes.iterator();
        while (iter.hasNext())
        {
            Fish next = iter.next();
            oList.add(next);
        }
        return oList;
    }
    
    public static void main(String[] args)
    {
        Fishtank tank = new Fishtank();
        System.out.println(tank.addFish("Goldfish", 1));
        System.out.println(tank.addFish("Neon Tetra", 1));
        System.out.println(tank.addFish("Neon Tetra", 2));
        System.out.println(tank.removeFish("Neon Tetra", 1));
        System.out.println(tank.removeFish("Neon Tetra", 1));
        System.out.println(tank.removeFish("Neon Tetra", 1));
        System.out.println(tank.removeFish("Neon Tetra", 1));
    }
}
