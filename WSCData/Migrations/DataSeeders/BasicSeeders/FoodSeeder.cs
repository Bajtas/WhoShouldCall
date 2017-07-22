using System.Collections.Generic;
using System.Linq;
using WSCData.Migrations.Seed;
using WSCData.Models;
using WSCData.Models.Entities;
using static WSCData.WSCConsts;

namespace WSCData.Migrations.BasicSeeders
{
    class FoodSeeder : IDataSeeder
    {
        private List<Food> _food = new List<Food>
        {
            new Food {Name = "Pierogi ze szpinakiem", Price=9.50M},
            new Food {Name = "Drobiowy, frytki, surówka", Price=12.5M},
            new Food {Name = "Placki po węgiersku", Price=13.00M},
            new Food {Name = "Kebab studencki", Price=6.00M}
        };

        public static readonly string NAME = SEEDERS.FOOD_SEEDER;

        public List<Food> Food
        {
            get { return _food; }
            set { _food = value; }
        }

        public void Seed(WSCDbContext dbContext)
        {
            foreach (var food in _food)
            {
                if (!dbContext.Foods.Any(f => f.Name == food.Name))
                {
                    dbContext.Foods.Add(food);
                }
            }
        }
    }
}