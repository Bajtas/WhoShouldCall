using System.Collections.Generic;
using System.Linq;
using WSCData.Models;
using WSCData.Models.Entities;

namespace WSCData.Migrations.Seed
{
    class FoodProvidersSeeder : IDataSeeder
    {
        private List<FoodProvider> _foodProviders = new List<FoodProvider>
        {
            new FoodProvider {Name = "Pierogarnia Rzeszowska", Rating=10},
            new FoodProvider {Name = "Ararat Kebab", Rating=7},
            new FoodProvider {Name = "Sajgon", Rating=5},
            new FoodProvider {Name = "Dara Kebab", Rating=8},
            new FoodProvider {Name = "Boston", Rating=9},
            new FoodProvider {Name = "Kulinarne Pyszności", Rating=9},
        };

        public static readonly string NAME = "FOOD_PROVIDERS";

        public List<FoodProvider> FoodProviders
        {
            get { return _foodProviders; }
            set { _foodProviders = value; }
        }

        public void Seed(WSCDbContext dbContext)
        {
            foreach (var foodProvider in _foodProviders)
            {
                if (!dbContext.FoodProviders.Contains(foodProvider))
                {
                    dbContext.FoodProviders.Add(foodProvider);
                }
            }
        }
    }
}