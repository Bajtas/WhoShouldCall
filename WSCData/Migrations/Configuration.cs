namespace WSCData.Migrations
{
    using System.Data.Entity.Migrations;

    internal sealed class Configuration : DbMigrationsConfiguration<WSCData.Models.WSCDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(WSCData.Models.WSCDbContext context)
        {
            //var user = new ApplicationUser { UserName = "admin@gmail.com", Email = "admin@gmail.com" };
            //var result = HttpContext.GetOwinContext().GetUserManager<ApplicationUserManager>().(user, "Test123!");
        }
    }
}
