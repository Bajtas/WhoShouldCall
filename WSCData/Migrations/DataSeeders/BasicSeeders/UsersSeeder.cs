using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using WSCData.Migrations.Seed;
using WSCData.Models;
using WSCData.Models.Entities;
using WSCTools.WSCExceptions;
using static WSCData.WSCConsts;

namespace WSCData.Migrations.BasicSeeders
{
    class UsersSeeder : IDataSeeder
    {
        private List<UserWrapper> _users = new List<UserWrapper>
        {
            new UserWrapper {UserName = "bz", Email="bz@ss.pl", Password="123", Role=ROLES.ADMIN},
            new UserWrapper {UserName = "mf", Email="mf@ss.pl", Password="123", Role=ROLES.MODERATOR},
            new UserWrapper {UserName = "kp", Email="kp@ss.pl", Password="123", Role=ROLES.USER},
            new UserWrapper {UserName = "kt", Email="kt@ss.pl", Password="123", Role=ROLES.USER},
            new UserWrapper {UserName = "mk", Email="mk@ss.pl", Password="123", Role=ROLES.USER}
        };

        public static readonly string NAME = SEEDERS.USERS_SEEDER;

        public void Seed(WSCDbContext dbContext)
        {
            foreach (var user in _users)
            {
                if (!dbContext.Users.Any(u => u.UserName == user.UserName))
                {
                    using (var store = new UserStore<User>(dbContext))
                    {
                        var manager = new UserManager<User>(store);
                        manager.PasswordValidator = SetUpPasswordValidator();
                        var userToInsert = new User { UserName = user.UserName, Email = user.Email };
                        var userResult = manager.Create(userToInsert, user.Password);
                        if (userResult.Succeeded)
                            manager.AddToRole(userToInsert.Id, user.Role);
                        else
                            throw new UnableToAddUserException(userResult.Errors);
                    }
                }
            }
        }

        private IIdentityValidator<string> SetUpPasswordValidator()
        {
            return new PasswordValidator
            {
                RequiredLength = 3,
                RequireNonLetterOrDigit = false,
                RequireDigit = false,
                RequireLowercase = false,
                RequireUppercase = false,
            };
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
