namespace WSCData.Models.Entities
{
    public class FoodProviderRating
    {
        public int Id { get; set; }
        public int Rate { get; set; }
        public User User { get; set; }
        public FoodProvider FoodProvider { get; set; }
    }
}