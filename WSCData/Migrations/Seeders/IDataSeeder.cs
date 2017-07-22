using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WSCData.Models;

namespace WSCData.Migrations.Seed
{
    interface IDataSeeder
    {
        void Seed(WSCDbContext dbContext);
    }
}
