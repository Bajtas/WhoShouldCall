
using System.Collections.Generic;
using WSCData.Models;
using WSCData.Models.Entities;

namespace WSCData.Migrations.Seed
{
    internal class QueuesSeeder : IDataSeeder
    {
        private List<Queue> _queues = new List<Queue>
        {
            new Queue {Name = "Queue1"},
            new Queue {Name = "Queue2"}
        };

        public static readonly string NAME = "QUEUES";

        public void Seed(WSCDbContext dbContext)
        {
            foreach (var queue in _queues)
            {
                
            }
        }
    }
}