using System.Collections.Generic;

namespace WSCData.Models.Entities
{
    public class Food
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }
        public FoodRating Rating { get; set; }
        public virtual List<User> Users { get; set; }
        public virtual List<Order> Orders { get; set; }
    }
}