namespace WSCData.Migrations
{
    using System.Data.Entity.Migrations;
    using WSCData.Migrations.Seed;
    using WSCData.Models;
    using static WSCData.Utils.WSCConsts;

    internal sealed class Configuration : DbMigrationsConfiguration<WSCData.Models.WSCDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
        }

        protected override void Seed(WSCDbContext context)
        {
            DebugSeeders(false);
            SeedBasicData(context);
            SeedRelationships(context);
        }

        private void SeedBasicData(WSCDbContext context)
        {
            SeederFactory.CreateSeederFor(SEEDERS.ROLES_SEEDER).Seed(context);
            SeederFactory.CreateSeederFor(SEEDERS.USERS_SEEDER).Seed(context);
            SeederFactory.CreateSeederFor(SEEDERS.QUEUES_SEEDER).Seed(context);
            SeederFactory.CreateSeederFor(SEEDERS.FOOD_PROVIDERS_SEEDER).Seed(context);
            SeederFactory.CreateSeederFor(SEEDERS.FOOD_SEEDER).Seed(context);
        }

        private void SeedRelationships(WSCDbContext context)
        {
            
        }

        private void DebugSeeders(bool debugFlag)
        {
            if (debugFlag && !System.Diagnostics.Debugger.IsAttached)
                System.Diagnostics.Debugger.Launch();
        }
    }
}
