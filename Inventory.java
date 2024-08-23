package flowershop;

import java.util.ArrayList;
import java.util.List;

public class Inventory 

{
    private List<Flower> flowers;

    public Inventory() 
    
    {
        this.flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower)
    
    {
        this.flowers.add(flower);
    }

    public Flower findFlowerByName(String name) 
    
    {
        for (Flower flower : flowers) 
        
        {
            if (flower.getName().equalsIgnoreCase(name)) 
            
            {
                return flower;
            }
        }
        return null;
    }

    public List<Flower> getFlowers() 
    
    {
        return new ArrayList<>(flowers);
    }
}
