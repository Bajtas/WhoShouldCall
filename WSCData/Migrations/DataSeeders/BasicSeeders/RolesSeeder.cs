using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System.Linq;
using WSCData.Migrations.Seed;
using WSCData.Models;
using static WSCData.WSCConsts;

namespace WSCData.Migrations.BasicSeeders
{
    class RolesSeeder : IDataSeeder
    {
        private static readonly string[] roles = { ROLES.ADMIN, ROLES.MODERATOR, ROLES.USER };

        public static readonly string NAME = SEEDERS.ROLES_SEEDER;
        
        public void Seed(WSCDbContext dbContext)
        {
            using (var store = new RoleStore<IdentityRole>(dbContext))
            {
                var manager = new RoleManager<IdentityRole>(store);
                foreach (string role in roles)
                {
                    if (!dbContext.Roles.Any(r => r.Name == role))
                    {
                        manager.Create(new IdentityRole { Name = role });
                    }
                }
            }
        }
    }
}