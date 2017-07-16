using System.Collections.Generic;
using System.Linq;
using System.Text;
using WSCData.Models.Entities;

namespace WSCCore.Services
{
    public static class UserStatisticService
    {
        public static List<FoodProvider> GetFavoriteFoodProviders(User user)
        {
            List<FoodProvider> ret = new List<FoodProvider>();
            if (user.Orders?.Count > 0)
            {
                var groupedFoodProviders = user.Orders.GroupBy(o => o.FoodProvider.Id)
                                            .OrderBy(g => g.Count())
                                            .Reverse();

                var orderCounter = groupedFoodProviders.FirstOrDefault()?.Count();
                foreach (var foodProviderGroup in groupedFoodProviders.Where(fp => fp.Count() == orderCounter))
                {
                    var foodProvider = foodProviderGroup.FirstOrDefault();
                    ret.Add(foodProvider.FoodProvider);
                }
            }

            return ret;
        }

        public static string GetFavoriteFoodProvidersNames(User user)
        {
            StringBuilder strBldr = new StringBuilder();
            List<FoodProvider> foodProviders = GetFavoriteFoodProviders(user);
            for (int i = 0; i < foodProviders.Count; i++)
            {
                strBldr.Append(foodProviders[i].Name);
                if (i + 1 < foodProviders.Count)
                    strBldr.Append('/');
            }

            return strBldr.ToString();
        }
    }
}