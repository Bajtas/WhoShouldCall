using System;
using System.Collections.Generic;

namespace WSCData.Models.Entities
{
    public class Order
    {
        public int Id { get; set; }
        public DateTime Date { get; set; }
        public virtual Queue Queue { get; set; }
        public virtual List<User> Users { get; set; }
        public virtual List<Food> Food { get; set; }
        public virtual FoodProvider FoodProvider { get; set; }
    }
}