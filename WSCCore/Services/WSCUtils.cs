using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using WSCData.Models.Entities;

namespace WSCCore.Services
{
    public static class WSCUtils
    {
        public static bool IsUserInModeratorsGroup(List<User> users, string userName)
        {
            var user = users?.Where(u => u.UserName == userName).FirstOrDefault();
            if (user == null || string.IsNullOrEmpty(userName))
                return false;

            return user.UserName.Equals(userName);
        }

        public static List<SelectListItem> ConvertListToSelectedList(List<FoodProvider> foodProviders)
        {
            List<SelectListItem> ret = new List<SelectListItem>();
            foreach (var food in foodProviders)
            {
                ret.Add(new SelectListItem
                {
                    Text = food.Name,
                    Value = food.Name + "| Rating: " + food.Rating
                });
            }

            return ret;
        }
    }
}
