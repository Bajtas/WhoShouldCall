using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using WSCData.Migrations.Seed;
using WSCData.Models;
using WSCData.Models.Entities;
using static WSCData.WSCConsts;

namespace WSCData.Migrations.Seeders
{
    class UsersSeeder : IDataSeeder
    {
        private List<UserWrapper> _users = new List<UserWrapper>
        {
            new UserWrapper {UserName = "bzuber", Email="bzuber@ss.pl", Password="123", Role=ROLES.ADMIN},
            new UserWrapper {UserName = "mfornal", Email="mfornal@ss.pl", Password="123", Role=ROLES.MODERATOR},
            new UserWrapper {UserName = "kpepek", Email="kpepek@ss.pl", Password="123", Role=ROLES.USER},
            new UserWrapper {UserName = "ktrojni", Email="ktrojni@ss.pl", Password="123", Role=ROLES.USER},
            new UserWrapper {UserName = "mkrasnia", Email="mkrasnia@ss.pl", Password="123", Role=ROLES.USER}
        };

        public static readonly string NAME = "USERS";

        public void Seed(WSCDbContext dbContext)
        {
            var store = new UserStore<User>(dbContext);
            var manager = new UserManager<User>(store);

            foreach (var user in _users)
            {
                if (!dbContext.Users.Any(u => u.UserName == user.UserName))
                {
                    var newUser = new User { UserName = user.UserName, Email = user.Email };
                    manager.Create(newUser, user.Password);
                    dbContext.SaveChanges();
                    manager.AddToRole(newUser.Id, user.Role);
                }
            }
        }

        internal class UserWrapper
        {
            public string UserName { get; set; }
            public string Email { get; set; }
            public string Password { get; set; }
            public string Role { get; set; }
        }
    }
}
