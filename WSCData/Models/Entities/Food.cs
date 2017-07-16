namespace WSCData.Models.Entities
{
    public class Food
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }
        public FoodRating Rating { get; set; }
    }
}