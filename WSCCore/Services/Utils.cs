using System.Collections.Generic;
using System.Linq;
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
    }
}