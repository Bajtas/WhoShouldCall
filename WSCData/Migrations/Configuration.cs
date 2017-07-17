namespace WSCData.Migrations
{
    using System.Data.Entity.Migrations;
    using WSCData.Migrations.Seed;
    using static WSCData.WSCConsts;

    internal sealed class Configuration : DbMigrationsConfiguration<WSCData.Models.WSCDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
        }

        protected override void Seed(WSCData.Models.WSCDbContext context)
        {
            DebugSeeders(false);

            SeederFactory.CreateSeederFor(SEEDERS.ROLES_SEEDER).Seed(context);
            SeederFactory.CreateSeederFor(SEEDERS.USERS_SEEDER).Seed(context);
            SeederFactory.CreateSeederFor(SEEDERS.FOOD_PROVIDERS_SEEDER).Seed(context);
        }

        private void DebugSeeders(bool debugFlag)
        {
            if (debugFlag && !System.Diagnostics.Debugger.IsAttached)
                System.Diagnostics.Debugger.Launch();
        }
    }
}
