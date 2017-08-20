
using System.Collections.Generic;
using System.Linq;
using WSCData.Migrations.Seed;
using WSCData.Models;
using WSCData.Models.Entities;
using static WSCData.Utils.WSCConsts;

namespace WSCData.Migrations.BasicSeeders
{
    internal class QueuesSeeder : IDataSeeder
    {
        private List<Queue> _queues = new List<Queue>
        {
            new Queue {Name = "Queue1"},
            new Queue {Name = "Queue2"},
        };

        public static readonly string NAME = SEEDERS.QUEUES_SEEDER;

        public void Seed(WSCDbContext dbContext)
        {
            foreach (var queue in _queues)
            {
                if (!dbContext.Queues.Any(q => q.Name == queue.Name))
                {
                    dbContext.Queues.Add(queue);
                }
            }
        }
    }
}