using System;
using System.Linq;
using System.Security.Principal;
using WSCData.Models;

namespace WSCCore.Services.Queues
{
    public class QueueUserStateManagerService : QueueService
    {
        public QueueUserStateManagerService()
            : base()
        {

        }

        public QueueUserStateManagerService(WSCDbContext ctx)
            : base(ctx)
        {

        }

        public bool IsUserTurnToCall(int queueId, string userName)
        {
            bool ret = false;
            using (var separatedCtx = new WSCDbContext())
            {
                var queue = separatedCtx.Queues.Find(queueId);
                var user = queue.Users.Where(u => u.UserName == userName).FirstOrDefault();
                if (user != null)
                {
                    if (user.LastCall == null)
                    {
                        ret = true;
                    }
                    else
                    {
                        var userToCall = queue.Users.Where(u => u.LastCall != null).OrderBy(u => u.LastCall).FirstOrDefault();
                        ret = userToCall?.UserName == user.UserName;
                    }
                }
            }

            return ret;
        }

        public bool IsUserTurnToCall(int queueId, int posInQueue, string userName)
        {
            bool ret = false;
            using (var separatedCtx = new WSCDbContext())
            {
                var queue = separatedCtx.Queues.Find(queueId);
                var user = queue.Users.Where(u => u.UserName == userName).FirstOrDefault();
                if (user != null && posInQueue == 1)
                    ret = true;
            }

            return ret;
        }

        public bool CanUserBeACaller(IPrincipal user, string userName)
        {
            return user.Identity.IsAuthenticated && user.Identity.Name == userName;
        }

        public void ConfirmCall(string userName)
        {
            var user = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            user.IsCallConfirmed = true;
        }

        public bool IsUserCaller(string userName)
        {
            var user = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            return user.IsCaller;
        }

        public void SetUserAsModerator(int? queueId, string userName)
        {
            var user = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            var queue = _ctx.Queues.Find(queueId);
            queue.Moderators.Add(user);
        }

        public void RemoveModerator(int? queueId, string userName)
        {
            var user = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            var queue = _ctx.Queues.Find(queueId);
            queue.Moderators.Remove(user);
        }

        internal void FinalizeCaller(string userName)
        {
            var user = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            if (user != null)
            {
                user.IsCaller = false;
                user.LastCallBackup = user.LastCall;
            }
        }

        internal void RemoveUser(int queueId, string userName)
        {
            var user = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            if (user != null)
            {
                var queue = _ctx.Queues.Find(queueId);
                if (queue != null)
                {
                    queue.Users?.Remove(user);
                }
                user.Queue = null;
            }
        }
    }
}