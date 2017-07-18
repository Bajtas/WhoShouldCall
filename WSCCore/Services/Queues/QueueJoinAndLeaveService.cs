using System;
using System.Linq;
using System.Security.Principal;
using WSCData.Models;
using WSCData.Models.Entities;

namespace WSCCore.Services
{
    public class QueueJoinAndLeaveService : QueueService
    {
        public QueueJoinAndLeaveService()
            : base()
        {
            
        }

        public QueueJoinAndLeaveService(WSCDbContext ctx)
            : base(ctx)
        {

        }

        public void UserJoinToQueue(IPrincipal user, Queue queue)
        {
            var dbUser = _ctx.Users.Where(u => u.UserName == user.Identity.Name).FirstOrDefault();
            if (dbUser != null)
            {
                dbUser.Queue = queue;
                queue.Users.Add(dbUser);
            }
        }

        public void UserRemoveFromQueue(IPrincipal user, Queue queue)
        {
            var dbUser = _ctx.Users.Where(u => u.UserName == user.Identity.Name).FirstOrDefault();

            if (dbUser != null)
                dbUser.Queue = null;
            if (queue != null && queue.Users.Count > 0)
                queue.Users.Remove(dbUser);
        }

        public void UserRemoveFromQueue(IPrincipal user)
        {
            var dbUser = _ctx.Users.Where(u => u.UserName == user.Identity.Name).FirstOrDefault();
            var queue = _ctx.Queues.Where(q => q.Users.Where(u => u.UserName == user.Identity.Name).FirstOrDefault().Id == dbUser.Id).FirstOrDefault();

            if (dbUser != null)
                dbUser.Queue = null;
            if (queue != null && queue.Users.Count > 0)
                queue.Users.Remove(dbUser);
        }
    }
}