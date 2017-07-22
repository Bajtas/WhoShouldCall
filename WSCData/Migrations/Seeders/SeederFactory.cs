using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WSCData.Migrations.Seeders;

namespace WSCData.Migrations.Seed
{
    static class SeederFactory
    {
        public static IDataSeeder CreateSeederFor(string seederName)
        {
            if (RolesSeeder.NAME == seederName)
                return new RolesSeeder();
            else if (UsersSeeder.NAME == seederName)
                return new UsersSeeder();
            else if (QueuesSeeder.NAME == seederName)
                return new QueuesSeeder();
            else if (FoodProvidersSeeder.NAME == seederName)
                return new FoodProvidersSeeder();
            return null;
        }
    }
}
