public class Cat
{
    private double originWeight;
    private double weight;

    private CatColour catColour;

    private double foodEaten;

    private static int count;

    private final byte NUMBER_OF_EYES = 2;
    private final double MIN_WEIGHT = 1000.0;
    private final double MAX_WEIGHT = 9000.0;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        count = count + 1;
    }

    // Исправленный конструктор
    public Cat(double weight)   {
        this.weight = originWeight = weight;
        count = count + (catIsAlive() ? 1 : 0);
    }

    public static Integer getCount()    {
        return count;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public void setFoodEaten(double foodEaten) {
        this.foodEaten = foodEaten;
    }

    public CatColour getCatColour() {
        return catColour;
    }
    public void setCatColour(CatColour catColour) {
        this.catColour = catColour;
    }

    public void meow()
    {
        if (catIsAlive()) {
            weight = weight - 1;
            System.out.println("Meow");
            if (!catIsAlive())
                count = count - 1;
        }
        else
        {
            System.out.println("Неживая кошка не может мяукать !");
        }

    }

    public void feed(double amount)
    {
        if (catIsAlive()) {
            weight = weight + amount;
            foodEaten = foodEaten + amount;
            if (!catIsAlive())
                count = count - 1;
        }
        else
        {
            System.out.println("Неживая кошка не может есть !");
        }

    }

    public void drink(double amount)
    {
        if (catIsAlive()) {
            weight = weight + amount;
            if (!catIsAlive())
                count = count - 1;
        }
        else
        {
            System.out.println("Неживая кошка не может пить !");
        }

    }

    public double getFoodEaten()
    {
        return foodEaten;
    }

    public void pee()
    {
        if (catIsAlive()) {
            weight = weight - 50.0;
            System.out.println("Кошка сходила в туалет, текущий вес кошки, гр : " + weight);
            if (!catIsAlive())
                count = count - 1;
        }
        else
        {
            System.out.println("Неживая кошка не может ходить в туалет !");
        }

    }

    public double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public Cat copyThisCat()    {

        Cat copiedCat = new Cat(this.getWeight());
        copiedCat.setFoodEaten(this.getFoodEaten());
        copiedCat.setOriginWeight(this.getOriginWeight());
        copiedCat.setCatColour(this.getCatColour());

        return copiedCat;

    }

    private boolean catIsAlive()    {
        return (weight>=MIN_WEIGHT && weight<=MAX_WEIGHT);
    }

}