using System;
using System.Linq;
using WSCData.Models;

namespace WSCCore.Services
{
    public class QueueService : IDisposable
    {
        protected readonly WSCDbContext _ctx;

        public QueueService(WSCDbContext ctx)
        {
            _ctx = ctx;
        }

        public QueueService()
        {
            _ctx = new WSCDbContext();
        }

        public bool IsUserInQueue(string userName, int queueId)
        {
            bool ret = false;
            var queue = _ctx.Queues.Find(queueId);
            if (queue != null && queue.Users.Any(u => u.UserName == userName))
                ret = true;
            return ret;
        }

        public void Dispose()
        {
            _ctx?.Dispose();
        }
    }
}