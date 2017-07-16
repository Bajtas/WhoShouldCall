namespace WSCData.Migrations
{
    using System.Data.Entity.Migrations;
    using WSCData.Migrations.Seed;

    internal sealed class Configuration : DbMigrationsConfiguration<WSCData.Models.WSCDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
        }

        protected override void Seed(WSCData.Models.WSCDbContext context)
        {
            if (System.Diagnostics.Debugger.IsAttached == false)
                System.Diagnostics.Debugger.Launch();

            SeederFactory.CreateSeederFor("ROLES").Seed(context);
            SeederFactory.CreateSeederFor("USERS").Seed(context);
        }
    }
}
