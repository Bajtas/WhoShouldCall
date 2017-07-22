using System.Collections.Generic;
using WSCData.Models.Entities;

namespace WSCData.Models.ViewModels
{
    public class QueueDetailsViewModel
    {
        public Queue Queue { get; set; }
        public List<FoodProvider> FoodProviders { get; set; }
    }
}